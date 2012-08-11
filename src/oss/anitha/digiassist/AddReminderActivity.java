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
import android.widget.TimePicker;
import android.widget.Toast;

public class AddReminderActivity extends Activity {

	  private static final String TAG = "AddReminderActivity";
	  private Button btnSave, btnCancel;
	  private EditText txtMedicationName, txtMedicationDosage;
	  private DatePicker dpStartDate, dpEndDate;
	  private TimePicker tpStartTime, tpEndTime;
	  
/*	  SharedPreferences reminderData = this.getSharedPreferences(
		      "testReminderData", Context.MODE_PRIVATE);
	  String dateTimeKey = "com.example.app.datetime";
	  long l = reminderData.getLong(dateTimeKey, new Date().getTime()); 
*/
	  
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);
        btnSave = (Button) findViewById(R.id.buttonSave);
        btnCancel = (Button) findViewById(R.id.buttonCancel);
        txtMedicationName = (EditText) findViewById(R.id.textMedicationName);
        txtMedicationDosage = (EditText) findViewById(R.id.textMedicationDosage);
        dpStartDate = (DatePicker) findViewById(R.id.startDatePicker);
        dpEndDate = (DatePicker) findViewById(R.id.endDatePicker);
        tpStartTime = (TimePicker) findViewById(R.id.startTimePicker);        
        tpEndTime = (TimePicker) findViewById(R.id.endTimePicker);
        tpStartTime.setIs24HourView(true);
        tpEndTime.setIs24HourView(true);
        
        btnSave.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				AddReminderActivity.this.saveFormData();				
			}
		});
        btnCancel.setOnClickListener(new View.OnClickListener() {
 			public void onClick(View v) {
 		        // Display data from store
 		        SharedPreferences sp = getSharedPreferences(Constants.DATA_STORE, 0);
 		        String medName = sp.getString(Constants.KEY_MEDICATION_NAME, Constants.KEY_MEDICATION_NAME);
 		        String medDosage = sp.getString(Constants.KEY_MEDICATION_DOSAGE, Constants.KEY_MEDICATION_DOSAGE);
 		        String startDate = sp.getString(Constants.KEY_START_DATE, Constants.KEY_START_DATE);
 		        String endDate = sp.getString(Constants.KEY_END_DATE, Constants.KEY_END_DATE);
 		        String startTime = sp.getString(Constants.KEY_START_TIME, Constants.KEY_START_TIME);
 		        String endTime = sp.getString(Constants.KEY_END_TIME, Constants.KEY_END_TIME);
 		        Toast.makeText(AddReminderActivity.this, medName + "," + medDosage +  
 		        		"," + startDate + "," + endDate + "," + startTime + "," + endTime,
 		        		Toast.LENGTH_LONG).show();
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
    	
    	// Setup alarm
    	// Figure out the time to trigger the alarm
    	Calendar cal = Calendar.getInstance();
    	cal.set(Calendar.YEAR, startYear);
    	cal.set(Calendar.MONTH, startMonth);
    	cal.set(Calendar.DAY_OF_MONTH, startDay);
    	cal.set(Calendar.HOUR, startHour);
    	cal.set(Calendar.MINUTE, startMin);
    	// Create an intent and populate it with messages to send
    	Intent i = new Intent(this, AlarmReceiver.class);
    	i.putExtra(Constants.KEY_MEDICATION_NAME, medicationName);
    	i.putExtra(Constants.KEY_MEDICATION_DOSAGE, medicationDosage);
    	PendingIntent pi = PendingIntent.getBroadcast(this, Constants.COMMAND_TRIGGER_ALARM, i, PendingIntent.FLAG_UPDATE_CURRENT);
    	AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
    	alarm.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pi);

    	// Store form data for later viewing
    	SharedPreferences sp = getSharedPreferences(Constants.DATA_STORE, 0);
    	SharedPreferences.Editor editor = sp.edit();
    	editor.putString(Constants.KEY_MEDICATION_NAME, medicationName);
    	editor.putString(Constants.KEY_MEDICATION_DOSAGE, medicationDosage);
    	String startDate = startMonth + "/" + startDay + "/" + startYear;
    	editor.putString(Constants.KEY_START_DATE, startDate);
    	String endDate = endMonth + "/" + endDay + "/" + endYear;
    	editor.putString(Constants.KEY_END_DATE, endDate);
    	String startTime = startHour + ":" + startMin;
    	editor.putString(Constants.KEY_START_TIME, startTime);
        String endTime = endHour + ":" + endMin;
    	editor.putString(Constants.KEY_END_TIME, endTime);
    	editor.commit();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_add_reminder, menu);
        return true;
    }
        
}
