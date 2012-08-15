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

import java.util.Locale;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;

public class DigiAssistActivity extends Activity {

	static TextToSpeech tts;
	static boolean ttsInited = false;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digi_assist);
        ImageView imgAddReminder = (ImageView) findViewById(R.id.add_reminder);
        imgAddReminder.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
	    		speak(getString(R.string.add_reminder));
				DigiAssistActivity.this.startActivityForResult(
						new Intent(DigiAssistActivity.this, AddReminderActivity.class), 
						Constants.COMMAND_ADD_REMINDER);
			}
		});
        
        ImageView imgViewCalendar = (ImageView) findViewById(R.id.ViewCalender);
        imgViewCalendar.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				speak(getString(R.string.view_reminders));
				DigiAssistActivity.this.startActivityForResult(
						new Intent(DigiAssistActivity.this, ViewCalendarActivity.class), 
						Constants.COMMAND_VIEW_CALENDAR);
			}
		});
        
        ImageView imgListMedication = (ImageView) findViewById(R.id.list_medication);
        imgListMedication.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				speak(getString(R.string.list_medication));
				DigiAssistActivity.this.startActivityForResult(
						new Intent(DigiAssistActivity.this, ListMedicationActivity.class), 
						Constants.COMMAND_LIST_MEDICATION);
			}
		});

        ImageView imgSendSymptoms = (ImageView) findViewById(R.id.send_symptoms);
        imgSendSymptoms.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				speak(getString(R.string.sendToDoc2));
				DigiAssistActivity.this.startActivityForResult(
						new Intent(DigiAssistActivity.this, SendSymptomsActivity.class), 
						Constants.COMMAND_SEND_SYMPTOMS);
			}
		});
        
        ImageView imgMedicationSupplies = (ImageView) findViewById(R.id.medication_supplies);
        imgMedicationSupplies.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				speak(getString(R.string.medication_supplies));
				DigiAssistActivity.this.startActivityForResult(
						new Intent(DigiAssistActivity.this, MedicationSupplyActivity.class), 
						Constants.COMMAND_MEDICATION_SUPPLIES);
			}
		});
        
        ImageView imgMedicationLog = (ImageView) findViewById(R.id.medication_log);
        imgMedicationLog.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				speak(getString(R.string.medication_log));
				DigiAssistActivity.this.startActivityForResult(
						new Intent(DigiAssistActivity.this, HealthLogActivity.class), 
						Constants.COMMAND_MEDICATION_LOG);
			}
		});
        
        ImageView imgSettings = (ImageView) findViewById(R.id.settings);
        imgSettings.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				speak(getString(R.string.settings));
				DigiAssistActivity.this.startActivityForResult(
						new Intent(DigiAssistActivity.this, DigiAssistSettingsActivity.class), 
						Constants.COMMAND_VIEW_SETTINGS);
			}
		});
        
        ImageView imgHelp = (ImageView) findViewById(R.id.help);
        imgHelp.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				speak(getString(R.string.help));
				DigiAssistActivity.this.startActivityForResult(
						new Intent(DigiAssistActivity.this, DigiAssistHelpActivity.class), 
						Constants.COMMAND_VIEW_HELP);
			}
		});
        
        Intent checkIntent = new Intent();
        checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkIntent, Constants.COMMAND_CHECK_TTS_AVAILABILITY);
    }
    
    @Override
    public void onDestroy() {
    	tts.shutdown();
    	super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_digi_assist, menu);
        return true;
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);
    	if(requestCode == Constants.COMMAND_CHECK_TTS_AVAILABILITY) {
    		tts = new TextToSpeech(this, new OnInitListener() {
				public void onInit(int status) {
					DigiAssistActivity.ttsInited = true;
					DigiAssistActivity.tts.setLanguage(Locale.US);
				}
			});
    	} 
    }
    
    static void speak(String message) {
    	if(ttsInited) {
    		tts.speak(message, TextToSpeech.QUEUE_FLUSH, null);
    	}
    }
}
