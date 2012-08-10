package oss.anitha.digiassist;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class DigiAssistActivity extends Activity {

	static final int COMMAND_ADD_REMINDER = 1;
	static final int COMMAND_LIST_MEDICATION = 2;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digi_assist);
        ImageView imgAddReminder = (ImageView) findViewById(R.id.add_reminder);
        imgAddReminder.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				DigiAssistActivity.this.startActivityForResult(
						new Intent(DigiAssistActivity.this, AddReminderActivity.class), 
						COMMAND_ADD_REMINDER);
			}
		});
        
        ImageView imgListMedication = (ImageView) findViewById(R.id.list_medication);
        imgListMedication.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				DigiAssistActivity.this.startActivityForResult(
						new Intent(DigiAssistActivity.this, ListMedicationActivity.class), 
						COMMAND_LIST_MEDICATION);
			}
		});

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_digi_assist, menu);
        return true;
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);
    	showMessage("Add Reminder completed with code " + resultCode);
    }
    
    private void showMessage(String message) {
    	Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
