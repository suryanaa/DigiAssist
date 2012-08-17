/*******************************************************************
* Copyright© 2012 Anitha Suryanarayan 
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.

* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details. You should have
* received a copy of the GNU General Public License along with this program. 
* If not, see <http://www.gnu.org/licenses/>.
* Author: Anitha Suryanarayan
* Feedback: anitha@pdx.edu
*******************************************************************/
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

	public MedicationReminder(String name, String dosage, int startDay, int startMonth, int startYear,
			int startHour, int startMin, int endDay, int endMonth, int endYear, int endHour, int endMin,
			int freq, String freqUnit) {
		this.id = ID_PREFIX + System.currentTimeMillis();
		this.name = name;
		this.dosage = dosage;
		this.startDateTime = Calendar.getInstance();
    	startDateTime.set(Calendar.YEAR, startYear);
    	startDateTime.set(Calendar.MONTH, startMonth);
    	startDateTime.set(Calendar.DAY_OF_MONTH, startDay);
    	startDateTime.set(Calendar.HOUR_OF_DAY, startHour);
    	startDateTime.set(Calendar.MINUTE, startMin);
		this.endDateTime = Calendar.getInstance();
		endDateTime.set(Calendar.YEAR, endYear);
		endDateTime.set(Calendar.MONTH, endMonth);
		endDateTime.set(Calendar.DAY_OF_MONTH, endDay);
		endDateTime.set(Calendar.HOUR_OF_DAY, endHour);
		endDateTime.set(Calendar.MINUTE, endMin);
		this.frequency = freq;
		this.frequencyUnit = freqUnit;
		
		strStartDateTime = dateFormatter.format(startDateTime.getTime());
		strEndDateTime = dateFormatter.format(endDateTime.getTime());
		strStartTime = timeFormatter.format(startDateTime.getTime());
		strEndTime = timeFormatter.format(endDateTime.getTime());
	}
	
	public MedicationReminder(String id, String record) {
		String[] tokens = record.split(SEPARATOR);
		if(tokens.length != 6) {
			throw new IllegalArgumentException("Record not in the right format: " + record);
		}
		name = tokens[0];
		dosage = tokens[1];
		try {
			this.startDateTime = Calendar.getInstance();
			startDateTime.setTime(dateFormatter.parse(tokens[2]));
			this.endDateTime = Calendar.getInstance();
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
