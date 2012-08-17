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
import java.util.Locale;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.speech.tts.UtteranceProgressListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class AlarmReceiverActivity extends Activity implements OnClickListener {

	private TextView tvMedicineName;
	private TextView tvMedicineDosage;
	private Button btnDismiss;

	static TextToSpeech tts;
	static boolean ttsInited = false;
	static boolean speaking = false;
	private String speechText = "";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_receiver);
        tvMedicineName = (TextView) findViewById(R.id.tvMedicine);
        tvMedicineDosage = (TextView) findViewById(R.id.tvDosage);
        btnDismiss = (Button) findViewById(R.id.btnDismiss);
        btnDismiss.setOnClickListener(this);

        Intent checkIntent = new Intent();
        checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkIntent, Constants.COMMAND_CHECK_TTS_AVAILABILITY);
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
    	Intent i = getIntent();
    	String id = i.getStringExtra(Constants.KEY_MEDICATION_RECORD_ID);
    	String record = i.getStringExtra(Constants.KEY_MEDICATION_RECORD);
    	MedicationReminder reminder = new MedicationReminder(id, record);
    	// Before proceeding further, check if we are past the end time
    	Calendar endTime = reminder.endDateTime;
    	Calendar now = Calendar.getInstance();
    	if(now.after(endTime)) {
    		// Cancel the alarm with a matching PendingIntent
        	Intent intent = new Intent(this, AlarmReceiver.class);
        	intent.putExtra(Constants.KEY_MEDICATION_RECORD_ID, id);
        	intent.putExtra(Constants.KEY_MEDICATION_RECORD, record);
        	PendingIntent pi = PendingIntent.getBroadcast(this, Constants.COMMAND_TRIGGER_ALARM, 
        			intent, PendingIntent.FLAG_UPDATE_CURRENT);
        	AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
    		alarm.cancel(pi);
    		
    		// Now delete the record from the data store
    		deleteReminder(id);
    	}
    	
    	String name = reminder.name;
    	String dosage = reminder.dosage;
    	tvMedicineName.setText(getString(R.string.view_label_medicine) + " " + name);
    	tvMedicineDosage.setText(getString(R.string.view_label_dosage) + " " + dosage);
    	
    	speechText = getString(R.string.time_for_med) + " " + name + ". ";
    	speechText += getString(R.string.dosage_is) + " " + dosage;
    	speaking = true;
    	new Thread() {
    		@Override
    		public void run() {
    			while(speaking) {
    				speak(speechText);
    				synchronized(tts) {
    					try {
							tts.wait(5000);
						} catch (InterruptedException e) {
						}
    				}
    			}
    		}
    	}.start();
    }
    
    @Override
    public void onPause() {
    	speaking = false;
    	super.onPause();
    }
    
    @Override
    public void onDestroy() {
    	tts.shutdown();
    	super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);
    	if(requestCode == Constants.COMMAND_CHECK_TTS_AVAILABILITY) {
    		tts = new TextToSpeech(this, new OnInitListener() {
				public void onInit(int status) {
					ttsInited = true;
					tts.setLanguage(Locale.US);
					tts.setOnUtteranceCompletedListener(new TextToSpeech.OnUtteranceCompletedListener() {
						public void onUtteranceCompleted(String utteranceId) {
							synchronized(tts) {
								tts.notifyAll();
							}
						}
					});
				}
			});
    	}
    }
    
    static void speak(String message) {
    	if(ttsInited) {
    		tts.speak(message, TextToSpeech.QUEUE_ADD, null);
    	}
    }

	public void onClick(View arg0) {
		speaking = false;
		this.finish();
	}
	
    private void deleteReminder(String id) {
        SharedPreferences sp = getSharedPreferences(Constants.DATA_STORE, 0);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(id);
        editor.commit();
    }

}
