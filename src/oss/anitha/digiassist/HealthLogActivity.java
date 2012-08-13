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
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TimePicker;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class HealthLogActivity extends Activity {
	private Button btnSave, btnCancel;
	private EditText txtTemperature, txtBloodGlucose, txtOxygenSat, txtRespRate, txtPulRate, txtSysBP, txtDiaBP;
	private SeekBar seekBarPainLevel;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_log);
        btnSave = (Button) findViewById(R.id.buttonSave);
        btnCancel = (Button) findViewById(R.id.buttonCancel);
        seekBarPainLevel = (SeekBar) findViewById(R.id.seekBarPainlevel);
        txtTemperature = (EditText) findViewById(R.id.bodyTemperature);
        txtBloodGlucose =  (EditText) findViewById(R.id.bloodGlucose);
        txtOxygenSat = (EditText) findViewById(R.id.oxygenSaturation);
        txtRespRate = (EditText) findViewById(R.id.respiratoryRate);
        txtPulRate = (EditText) findViewById(R.id.pulseRate);
        txtSysBP = (EditText) findViewById(R.id.systolicBP);        
        txtDiaBP = (EditText) findViewById(R.id.diastolicBP);
        
        btnSave.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				HealthLogActivity.this.saveFormData();				
			}
		});btnCancel.setOnClickListener(new View.OnClickListener() {
 			public void onClick(View v) {
 		        // Display data from store
 		        SharedPreferences sp = getSharedPreferences(Constants.STATS_STORE, 0);
 		        String painLevel = sp.getString(Constants.KEY_PAIN_LEVEL, Constants.KEY_PAIN_LEVEL);
 		        String temperature = sp.getString(Constants.KEY_TEMPERATURE, Constants.KEY_TEMPERATURE);
 		        String bloodGlucose = sp.getString(Constants.KEY_BLOOD_GLUCOSE, Constants.KEY_BLOOD_GLUCOSE);
 		        String sysBP = sp.getString(Constants.KEY_SYS_BP, Constants.KEY_SYS_BP);
		        String diasBP = sp.getString(Constants.KEY_DIA_BP, Constants.KEY_DIA_BP);
		        String oxygenSat = sp.getString(Constants.KEY_OXY_SAT, Constants.KEY_OXY_SAT);
 		        String respRate = sp.getString(Constants.KEY_RESP_RATE, Constants.KEY_RESP_RATE);
 		        String pulseRate = sp.getString(Constants.KEY_PULSE_RATE, Constants.KEY_PULSE_RATE);
 		        
 		        Toast.makeText(HealthLogActivity.this, painLevel + "," + temperature +  
 		        		"," + bloodGlucose + "," + sysBP + "," + diasBP + "," + oxygenSat + "," + respRate + "," + pulseRate, 
 		        		Toast.LENGTH_LONG).show();
 			}
        });
    }
       
    private void saveFormData() {
    	// Get form data
	        String painLevel = seekBarPainLevel.toString();
	        String temperature = txtTemperature.toString();
	        String bloodGlucose = txtBloodGlucose.getText().toString();
	        String oxygenSat = txtOxygenSat.getText().toString();
	        String respRate = txtRespRate.getText().toString();
	        String pulseRate = txtPulRate.getText().toString();
	        String sysBP = txtSysBP.getText().toString();
	        String diasBP = txtDiaBP.getText().toString();
        
    	
    	// Store form data for later viewing
    	SharedPreferences sp = getSharedPreferences(Constants.STATS_STORE, 0);
    	SharedPreferences.Editor editor = sp.edit();
    	editor.putString(Constants.KEY_PAIN_LEVEL, painLevel);
    	editor.putString(Constants.KEY_TEMPERATURE, temperature);
    	editor.putString(Constants.KEY_BLOOD_GLUCOSE, bloodGlucose);
    	editor.putString(Constants.KEY_SYS_BP, sysBP);
    	editor.putString(Constants.KEY_DIA_BP, diasBP);
    	editor.putString(Constants.KEY_OXY_SAT, oxygenSat);
    	editor.putString(Constants.KEY_RESP_RATE, respRate);
    	editor.putString(Constants.KEY_PULSE_RATE, pulseRate);
    	editor.commit();
    }
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_medication_log, menu);
        return true;
    }

    
}
