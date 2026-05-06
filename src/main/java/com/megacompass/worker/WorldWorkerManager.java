package com.megacompass.worker;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

import com.megacompass.MegaCompass;

public class WorldWorkerManager {
    // Use CopyOnWriteArrayList for thread-safe iteration while modifying
    private static final List<IWorker> workers = new CopyOnWriteArrayList<>();
    private static final int MAX_WORKERS = 100; // Point 13: Limit active workers
    
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

        // Reset index for the tick
        index = 0;
        
        long timeLimit = 50 - (System.currentTimeMillis() - startTime);
        if (timeLimit < 10) {
            timeLimit = 10; // If ticks are lagging, give us at least 10ms to do something.
        }
        long endTime = System.currentTimeMillis() + timeLimit;

        // Point 12: Loop over workers with time limit
        while (System.currentTimeMillis() < endTime) {
            IWorker task = getNext();
            if (task == null) break;

            boolean again = task.doWork();

            if (!task.hasWork()) {
                remove(task);
            } else if (!again) {
                // If the worker says it doesn't need to run again this tick, move to next
            } else {
                // If it wants to run again, stay on this index for the next getNext call
                index--;
            }
        }
    }

    public static void addWorker(IWorker worker) {
        if (workers.size() >= MAX_WORKERS) {
            MegaCompass.LOGGER.warn("Maximum worker limit reached ({}), skipping new worker.", MAX_WORKERS);
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
     * Stop all workers associated with a specific player UUID.
     * Fixes the singleton item issue (Point 1 & 2).
     */
    public static void stopWorkersForPlayer(UUID playerUuid) {
        for (IWorker worker : workers) {
            if (playerUuid.equals(worker.getOwnerUuid())) {
                worker.stop();
                remove(worker);
            }
        }
    }

    // Internal only, used to clear everything when the server shuts down.
    public static void clear() {
        workers.clear();
        index = 0;
    }

    public static interface IWorker {
        boolean hasWork();

        /**
         * Perform a task, returning true from this will have the manager call this
         * function again this tick if there is time left.
         * Returning false will skip calling this worker until next tick.
         */
        boolean doWork();

        /**
         * Returns the UUID of the player who started this worker.
         */
        UUID getOwnerUuid();

        /**
         * Signal the worker to stop immediately.
         */
        void stop();
    }
}
