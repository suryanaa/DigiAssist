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

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

public class AddReminderActivity extends Activity {

	  private Button btnSave, btnCancel;
	  private EditText txtMedicationName, txtMedicationDosage;
	  private DatePicker dpStartDate, dpEndDate;
	  private TimePicker tpStartTime, tpEndTime;
	  private Spinner spinFreq, spinFreqUnit;
	  
/*	  SharedPreferences reminderData = this.getSharedPreferences(
		      "testReminderData", Context.MODE_PRIVATE);
	  String dateTimeKey = "com.example.app.datetime";
	  long l = reminderData.getLong(dateTimeKey, new Date().getTime()); 
*/
	  
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);
        Calendar c = Calendar.getInstance();
        btnSave = (Button) findViewById(R.id.buttonSave);
        btnCancel = (Button) findViewById(R.id.buttonCancel);
        txtMedicationName = (EditText) findViewById(R.id.textMedicationName);
        txtMedicationDosage = (EditText) findViewById(R.id.textMedicationDosage);
        dpStartDate = (DatePicker) findViewById(R.id.startDatePicker);
        dpStartDate.init(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), null);
        dpEndDate = (DatePicker) findViewById(R.id.endDatePicker);
        Calendar tmp = Calendar.getInstance();
        tmp.add(Calendar.DAY_OF_MONTH, 1);
        dpEndDate.init(c.get(Calendar.YEAR), c.get(Calendar.MONTH), tmp.get(Calendar.DAY_OF_MONTH), null);
        tpStartTime = (TimePicker) findViewById(R.id.startTimePicker);        
        tpEndTime = (TimePicker) findViewById(R.id.endTimePicker);
        spinFreq = (Spinner) findViewById(R.id.spinnerRepeatNum);
        spinFreq.setSelection(0);
        spinFreqUnit = (Spinner) findViewById(R.id.spinnerRepeatUnit);  
        spinFreqUnit.setSelection(0);

        tpStartTime.setIs24HourView(true);
        tpStartTime.setCurrentHour(c.get(Calendar.HOUR_OF_DAY));
        tpStartTime.setCurrentMinute(c.get(Calendar.MINUTE));
        tpEndTime.setIs24HourView(true);
        tpEndTime.setCurrentHour(c.get(Calendar.HOUR_OF_DAY));
        tpEndTime.setCurrentMinute(c.get(Calendar.MINUTE));
        
        btnSave.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				AddReminderActivity.this.saveFormData();				
			}
		});
    }
    
    private void saveFormData() {
    	// Get form data
    	String medicationName = txtMedicationName.getText().toString();
    	String medicationDosage = txtMedicationDosage.getText().toString();
    	int startMonth = dpStartDate.getMonth();
    	int startDay = dpStartDate.getDayOfMonth();
    	int startYear = dpStartDate.getYear();
    	int endMonth = dpEndDate.getMonth();
    	int endDay = dpEndDate.getDayOfMonth();
    	int endYear = dpEndDate.getYear();
    	int startHour = tpStartTime.getCurrentHour();
    	int startMin = tpStartTime.getCurrentMinute();
    	int endHour = tpEndTime.getCurrentHour();
    	int endMin = tpEndTime.getCurrentMinute();
    	String freqList[] = getResources().getStringArray(R.array.medication_frequency_num);
    	String freqUnitList[] = getResources().getStringArray(R.array.medication_frequency_unit);
    	int freq = Integer.parseInt(freqList[spinFreq.getSelectedItemPosition()]);
    	String freqUnit = freqUnitList[spinFreq.getSelectedItemPosition()];
    	MedicationReminder record = new MedicationReminder(medicationName, medicationDosage, 
    			startDay, startMonth, startYear, startHour, startMin, 
    			endDay, endMonth, endYear, endHour, endMin, freq, freqUnit);
    	
    	// Setup alarm
    	// Figure out the time to trigger the alarm
    	// Create an intent and populate it with messages to send
    	Intent i = new Intent(this, AlarmReceiver.class);
    	i.putExtra(Constants.KEY_MEDICATION_RECORD_ID, record.id);
    	i.putExtra(Constants.KEY_MEDICATION_RECORD, record.toString());
    	PendingIntent pi = PendingIntent.getBroadcast(this, Constants.COMMAND_TRIGGER_ALARM, 
    			i, PendingIntent.FLAG_UPDATE_CURRENT);
    	AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
    	// Create a calendar in UTC and convert the start time to UTC using this
    	Calendar c = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
    	c.setTimeInMillis(record.startDateTime.getTimeInMillis());
    	// Calculate the repeat interval
    	long interval = 0;
    	if(freqUnit.equalsIgnoreCase("Hours")) {
    		interval = freq * 60 * 60 * 1000; 
    	}
    	else if(freqUnit.equalsIgnoreCase("Days")) {
    		interval = freq * 24 * 60 * 60 * 1000;
    	}
    	else if(freqUnit.equalsIgnoreCase("Weeks")) {
    		interval = freq * 7 * 24 * 60 * 60 * 1000; 
    	}
    	// Set the alarm
    	alarm.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), interval, pi);

    	// Store form data for later viewing
    	SharedPreferences sp = getSharedPreferences(Constants.DATA_STORE, 0);
    	SharedPreferences.Editor editor = sp.edit();
    	editor.putString(record.getId(), record.toString());
    	editor.commit();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_add_reminder, menu);
        return true;
    }
}
