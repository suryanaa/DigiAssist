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

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class SendSymptomsActivity extends Activity {

	private Button btnSendEmailToDoc, btnCancel;
	private String Nausea, Fever, Allergic_Reaction, Weight_loss, Weight_gain, Runny_nose, Sneezing,
				Cough, Despressed, Diarrhea, Dizzy, Headache, NasalCongestion, Other, SoreThroat;
	private CheckBox nausea, fever, allergy, weightloss, weightgain, cough,runny_nose, sneezing, 
						depressed, diarrhea, dizzy, headache, nasalCongestion, other, sorethroat;
	//private StringBuilder symptoms;
	private StringBuilder MessageBody  = new StringBuilder();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_symptoms);
        btnSendEmailToDoc = (Button) findViewById(R.id.buttonSendToDoc);
        
        btnCancel = (Button) findViewById(R.id.buttonCancel);
        
        btnSendEmailToDoc.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);  
		
				String aEmailList[] = { "smith@providence.com" };  
				String aEmailCCList[] = { "anitha@pdx.edu"};  
				MessageBody.append("Dear Dr Smith, I have been experiencing the following discomforts " +
						"since starting the new medication regimen.\n\n");
				
				allergy = (CheckBox) findViewById(R.id.chkBoxAllergicReaction);
				if(allergy.isChecked())
					MessageBody.append("Allergic Reaction").append("\n");
				
				cough = (CheckBox) findViewById(R.id.chkBoxCough);
				if(cough.isChecked())
					MessageBody.append("Cough").append("\n");
				
				depressed = (CheckBox) findViewById(R.id.chkBoxDepressed);
				if(depressed.isChecked())
					MessageBody.append("Depressed").append("\n");
				
				diarrhea = (CheckBox) findViewById(R.id.chkBoxDiarrhea);
				if(diarrhea.isChecked())
					MessageBody.append("Diarrhea").append("\n");
				
				dizzy = (CheckBox) findViewById(R.id.chkBoxDizzy);
				if(dizzy.isChecked())
					MessageBody.append("Dizziness").append("\n");
				
				fever = (CheckBox) findViewById(R.id.chkBoxFever);
				if(fever.isChecked())
					MessageBody.append("Fever").append("\n");
				
				headache = (CheckBox) findViewById(R.id.chkBoxHeadache);
				if(headache.isChecked())
					MessageBody.append("Headache").append("\n");
				
				nasalCongestion = (CheckBox) findViewById(R.id.chkBoxNasalCongestion);
				if(nasalCongestion.isChecked())
					MessageBody.append("Nasal Congestion").append("\n");
				
				nausea = (CheckBox) findViewById(R.id.chkBoxNausea);
				if(nausea.isChecked())
					MessageBody.append("Nausea").append("\n");
				/*
				other = (CheckBox) findViewById(R.id.chkBoxOther);
				if(other.isChecked())
					MessageBody.append("Other").append("\n");
				*/
				runny_nose = (CheckBox) findViewById(R.id.chkBoxRunny_nose);
				if(runny_nose.isChecked())
					MessageBody.append("Runny nose").append("\n");
				
				sneezing = (CheckBox) findViewById(R.id.chkBoxSneezing);
				if(sneezing.isChecked())
					MessageBody.append("Sneezing").append("\n");
				
				sorethroat = (CheckBox) findViewById(R.id.chkBoxSore_Throat);
				if(sorethroat.isChecked())
					MessageBody.append("Sore Throat").append("\n");
				
				weightgain = (CheckBox) findViewById(R.id.chkBoxWeightGain);
				if(weightgain.isChecked())
					MessageBody.append("Weight Gain").append("\n");
				
				weightloss = (CheckBox) findViewById(R.id.chkBoxWeightLoss);
				if(weightloss.isChecked())
					MessageBody.append("Weight Loss").append("\n");
				
				MessageBody.append("\nPlease advise.");
					
				emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, aEmailList);  
				emailIntent.putExtra(android.content.Intent.EXTRA_CC, aEmailCCList);  
				emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Side Effects of Medication");  
				emailIntent.setType("plain/text");  
				emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, MessageBody.toString());  

				startActivity(Intent.createChooser(emailIntent, "Send mail"));  

			}
		});
        btnCancel.setOnClickListener(new View.OnClickListener() {
 			public void onClick(View v) {
 			}
        });
    }
    
    private void emailFormData() {
    }
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_send_symptoms, menu);
        return true;
    }

    
}
