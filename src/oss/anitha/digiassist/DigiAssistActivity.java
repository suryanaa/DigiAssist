package oss.anitha.digiassist;

import java.util.Locale;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
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
				speak(getString(R.string.sendToDoc));
				DigiAssistActivity.this.startActivityForResult(
						new Intent(DigiAssistActivity.this, SendSymptomsActivity.class), 
						Constants.COMMAND_SEND_SYMPTOMS);
			}
		});
        Intent checkIntent = new Intent();
        checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkIntent, Constants.COMMAND_CHECK_TTS_AVAILABILITY);
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
    
    private void showMessage(String message) {
    	Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    
    static void speak(String message) {
    	if(ttsInited) {
    		tts.speak(message, TextToSpeech.QUEUE_FLUSH, null);
    	}
    }
}
