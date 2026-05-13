package com.megacompass.worker;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

import com.megacompass.MegaCompass;

public class WorldWorkerManager {

    private static final List<IWorker> workers = new CopyOnWriteArrayList<>();
    private static final int MAX_WORKERS = 100;

    private static long startTime = -1;
    private static int index = 0;

    public static void tick(boolean start) {
        if (start) {
            startTime = System.currentTimeMillis();
            return;
        }

        if (workers.isEmpty()) {
            return;
        }

        index = 0;

        long timeLimit = 50 - (System.currentTimeMillis() - startTime);
        if (timeLimit < 10) {
            timeLimit = 10;
        }
        long endTime = System.currentTimeMillis() + timeLimit;

        while (System.currentTimeMillis() < endTime) {
            IWorker task = getNext();
            if (task == null) break;

            boolean again = task.doWork();

            if (!task.hasWork()) {
                remove(task);
            } else if (!again) {
                // Worker non vuole girare di nuovo questo tick, passa al prossimo
            } else {
                index--;
            }
        }
    }

    public static void addWorker(IWorker worker) {
        if (workers.size() >= MAX_WORKERS) {
            MegaCompass.LOGGER.warn("Limite massimo di worker raggiunto ({}), nuovo worker ignorato.", MAX_WORKERS);
            return;
        }
        workers.add(worker);
    }

    private static synchronized IWorker getNext() {
        if (index >= workers.size()) {
            return null;
        }
        return workers.get(index++);
    }

    private static synchronized void remove(IWorker worker) {
        if (workers.remove(worker)) {
            index = Math.max(0, index - 1);
        }
    }

    /**
     * Ferma e rimuove tutti i worker associati a un determinato player.
     * Risolve la race condition precedente iterando in modo sicuro con removeIf.
     */
    public static synchronized void stopWorkersForPlayer(UUID playerUuid) {
        workers.removeIf(worker -> {
            if (playerUuid.equals(worker.getOwnerUuid())) {
                worker.stop();
                return true;
            }
            return false;
        });
        index = 0;
    }

    public static void clear() {
        workers.clear();
        index = 0;
    }

    public interface IWorker {
        boolean hasWork();

        /**
         * Esegue un'unità di lavoro. Restituisce true se vuole girare di nuovo
         * nello stesso tick (se c'è tempo), false per rimandare al tick successivo.
         */
        boolean doWork();

        /** Restituisce l'UUID del player che ha avviato questo worker. */
        UUID getOwnerUuid();

        /** Segnala al worker di fermarsi immediatamente. */
        void stop();
    }
}