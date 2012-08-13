package oss.anitha.digiassist;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class MedicationReminder {
	
	private static final String SEPARATOR = ";";
	private static final String ID_PREFIX = "Reminder";
	private SimpleDateFormat dateFormatter = new SimpleDateFormat("MM-dd-yyyy HH:mm");
	private SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm");

	String id;
	String name;
	String dosage;
	Calendar startDateTime;
	Calendar endDateTime;
	int frequency;
	String frequencyUnit;
	
	private String strStartDateTime;
	private String strStartTime;
	private String strEndDateTime;
	private String strEndTime;

	private MedicationReminder() {
		TimeZone.setDefault(TimeZone.getTimeZone("PST"));
		dateFormatter.setTimeZone(TimeZone.getTimeZone("PST"));
	}
	
	public MedicationReminder(String name, String dosage, int startDay, int startMonth, int startYear,
			int startHour, int startMin, int endDay, int endMonth, int endYear, int endHour, int endMin,
			int freq, String freqUnit) {
		this();
		this.id = ID_PREFIX + System.currentTimeMillis();
		this.name = name;
		this.dosage = dosage;
		this.startDateTime = Calendar.getInstance(TimeZone.getTimeZone("PST"));
    	startDateTime.set(Calendar.YEAR, startYear);
    	startDateTime.set(Calendar.MONTH, startMonth);
    	startDateTime.set(Calendar.DAY_OF_MONTH, startDay);
    	startDateTime.set(Calendar.HOUR, startHour);
    	startDateTime.set(Calendar.MINUTE, startMin);
		this.endDateTime = Calendar.getInstance(TimeZone.getTimeZone("PST"));
		endDateTime.set(Calendar.YEAR, endYear);
		endDateTime.set(Calendar.MONTH, endMonth);
		endDateTime.set(Calendar.DAY_OF_MONTH, endDay);
		endDateTime.set(Calendar.HOUR, endHour);
		endDateTime.set(Calendar.MINUTE, endMin);
		this.frequency = freq;
		this.frequencyUnit = freqUnit;
		
		strStartDateTime = dateFormatter.format(startDateTime.getTime());
		strEndDateTime = dateFormatter.format(endDateTime.getTime());
		strStartTime = timeFormatter.format(startDateTime.getTime());
		strEndTime = timeFormatter.format(endDateTime.getTime());
	}
	
	public MedicationReminder(String id, String record) {
		this();
		String[] tokens = record.split(SEPARATOR);
		if(tokens.length != 6) {
			throw new IllegalArgumentException("Record not in the right format: " + record);
		}
		name = tokens[0];
		dosage = tokens[1];
		try {
			this.startDateTime = Calendar.getInstance(TimeZone.getTimeZone("PST"));
			startDateTime.setTime(dateFormatter.parse(tokens[2]));
			this.endDateTime = Calendar.getInstance(TimeZone.getTimeZone("PST"));
			endDateTime.setTime(dateFormatter.parse(tokens[3]));
		} catch (ParseException e) {
			throw new IllegalArgumentException("Could not parse record: " + record);
		}
		frequency = Integer.parseInt(tokens[4]);
		frequencyUnit = tokens[5];
		this.id = id;

		strStartDateTime = dateFormatter.format(startDateTime.getTime());
		strEndDateTime = dateFormatter.format(endDateTime.getTime());
		strStartTime = timeFormatter.format(startDateTime.getTime());
		strEndTime = timeFormatter.format(endDateTime.getTime());
	}
	
	public String getId() {
		return id;
	}
	
	public String getStartDateTime() {
		return strStartDateTime;
	}
	
	public String getEndDateTime() {
		return strEndDateTime;
	}
	
	public String getStartTime() {
		return strStartTime;
	}
	
	public String getEndTime() {
		return strEndTime;
	}

	public String getRecurrence() {
		return frequency + " " + frequencyUnit;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(name).append(SEPARATOR).append(dosage).append(SEPARATOR);
		sb.append(strStartDateTime).append(SEPARATOR);
		sb.append(strEndDateTime).append(SEPARATOR);
		sb.append(frequency).append(SEPARATOR).append(frequencyUnit);
		return sb.toString();
	}
}
