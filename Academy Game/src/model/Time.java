package model;

public class Time {

	int	currentDay	= 1;
	Hour	currentTime	= Hour.DAWN;

	public Hour currentTime() {
		return currentTime;
	}

	public int currentDay() {
		return currentDay;
	}

	public void nextHour() {
		if (currentTime.ordinal() == Hour.values().length - 1) {
			currentDay++;
			currentTime = Hour.MIDNIGHT;
		} else {
			currentTime = Hour.values()[currentTime.ordinal() + 1];
		}
	}

	public enum Hour {
		MIDNIGHT {},
		EARLY_DARKNESS {},
		DARKNESS {},
		LATE_DARKNESS {},
		DAWN {},
		MORNING_TWILIGHT {},
		SUNRISE {},
		EARLY_MORNING {},
		MORNING {},
		LATE_MORNING {},
		NOON {},
		EARLY_AFTERNOON {},
		AFTERNOON {},
		LATE_AFTERNOON {},
		SUNSET {},
		EVENING_TWILIGHT {},
		DUSK {},
		EARLY_EVENING {},
		EVENING {},
		LATE_EVENING {};

		public String toString() {
			String format = name();
			format = format.toLowerCase();
			format = format.replace("_", " ");
			String[] a = format.split(" ");

			String output = "";
			for (String s : a) {
				output = output + s.substring(0, 1).toUpperCase() + s.substring(1)
						+ " ";
			}
			return output.trim();
		}
	}

}
