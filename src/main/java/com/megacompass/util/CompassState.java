package com.megacompass.util;

import com.megacompass.MegaCompass;

public enum CompassState {
	INACTIVE(0), SEARCHING(1), FOUND(2), NOT_FOUND(3);

	private final int id;

	CompassState(int id) {
		this.id = id;
	}

	public int getID() {
		return id;
	}

	public static CompassState fromID(int id) {
		for (CompassState state : values()) {
			if (state.id == id) {
				return state;
			}
		}
        // Point 4: Warning for unknown state
        MegaCompass.LOGGER.warn("Unknown compass state ID: {}. Defaulting to INACTIVE.", id);
		return INACTIVE;
	}
}
