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
	private String symptoms, MessageBody;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_symptoms);
        btnSendEmailToDoc = (Button) findViewById(R.id.buttonSendToDoc);
        
        btnCancel = (Button) findViewById(R.id.buttonCancel);
        
        btnSendEmailToDoc.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);  
		
				allergy = (CheckBox) findViewById(R.id.chkBoxAllergicReaction);
				if(allergy.isChecked())
					symptoms.concat("@string/allergicReaction").concat(",");
		/*		
				cough = (CheckBox) findViewById(R.id.chkBoxCough);
				if(cough.isChecked())
					symptoms.concat("@string/cough").concat(",");;
				
				depressed = (CheckBox) findViewById(R.id.chkBoxDepressed);
				if(depressed.isChecked())
					symptoms.concat("@string/depressed").concat(",");;
				
				diarrhea = (CheckBox) findViewById(R.id.chkBoxDiarrhea);
				if(diarrhea.isChecked())
					symptoms.concat("@string/diarrhea").concat(",");;
				
				dizzy = (CheckBox) findViewById(R.id.chkBoxDizzy);
				if(dizzy.isChecked())
					symptoms.concat("@string/dizzy").concat(",");;
				
				fever = (CheckBox) findViewById(R.id.chkBoxFever);
				if(fever.isChecked())
					symptoms.concat("@string/fever").concat(",");;
				
				headache = (CheckBox) findViewById(R.id.chkBoxHeadache);
				if(headache.isChecked())
					symptoms.concat("@string/headache").concat(",");;
				
				nasalCongestion = (CheckBox) findViewById(R.id.chkBoxNasalCongestion);
				if(nasalCongestion.isChecked())
					symptoms.concat("@string/nasalCongestion").concat(",");;
				
				nausea = (CheckBox) findViewById(R.id.chkBoxNausea);
				if(nausea.isChecked())
					symptoms.concat("@string/nausea").concat(",");;
				
				other = (CheckBox) findViewById(R.id.chkBoxOther);
				if(other.isChecked())
					symptoms.concat("@string/other").concat(",");;
				
				runny_nose = (CheckBox) findViewById(R.id.chkBoxRunny_nose);
				if(runny_nose.isChecked())
					symptoms.concat("@string/runny_nose").concat(",");;
				
				sneezing = (CheckBox) findViewById(R.id.chkBoxSneezing);
				if(sneezing.isChecked())
					symptoms.concat("@string/sneezing").concat(",");;
				
				sorethroat = (CheckBox) findViewById(R.id.chkBoxSore_Throat);
				if(sorethroat.isChecked())
					symptoms.concat("@string/sorethroat").concat(",");;
				
				weightgain = (CheckBox) findViewById(R.id.chkBoxWeightGain);
				if(weightgain.isChecked())
					symptoms.concat("@string/weightgain").concat(",");;
				
				weightloss = (CheckBox) findViewById(R.id.chkBoxWeightLoss);
				if(weightloss.isChecked())
					symptoms.concat("@string/weightLoss").concat(",");;
				
			*/	
				String aEmailList[] = { "smith@providence.com" };  
				String aEmailCCList[] = { "anitha@pdx.edu"};  
				MessageBody = "Dear Dr Smith, I have been experiencing the following discomforts since starting the new medication regimen.";
				
				allergy = (CheckBox) findViewById(R.id.chkBoxAllergicReaction);
				if(allergy.isChecked())
					MessageBody.concat("@string/allergicReaction").concat(",");
				
				cough = (CheckBox) findViewById(R.id.chkBoxCough);
				if(cough.isChecked())
					MessageBody.concat("@string/cough").concat(",");;
				
				depressed = (CheckBox) findViewById(R.id.chkBoxDepressed);
				if(depressed.isChecked())
					MessageBody.concat("@string/depressed").concat(",");;
				
				diarrhea = (CheckBox) findViewById(R.id.chkBoxDiarrhea);
				if(diarrhea.isChecked())
					MessageBody.concat("@string/diarrhea").concat(",");;
				
				dizzy = (CheckBox) findViewById(R.id.chkBoxDizzy);
				if(dizzy.isChecked())
					MessageBody.concat("@string/dizzy").concat(",");;
				
				fever = (CheckBox) findViewById(R.id.chkBoxFever);
				if(fever.isChecked())
					MessageBody.concat("@string/fever").concat(",");;
				
				headache = (CheckBox) findViewById(R.id.chkBoxHeadache);
				if(headache.isChecked())
					MessageBody.concat("@string/headache").concat(",");;
				
				nasalCongestion = (CheckBox) findViewById(R.id.chkBoxNasalCongestion);
				if(nasalCongestion.isChecked())
					MessageBody.concat("@string/nasalCongestion").concat(",");;
				
				nausea = (CheckBox) findViewById(R.id.chkBoxNausea);
				if(nausea.isChecked())
					MessageBody.concat("@string/nausea").concat(",");;
				
				other = (CheckBox) findViewById(R.id.chkBoxOther);
				if(other.isChecked())
					MessageBody.concat("@string/other").concat(",");;
				
				runny_nose = (CheckBox) findViewById(R.id.chkBoxRunny_nose);
				if(runny_nose.isChecked())
					MessageBody.concat("@string/runny_nose").concat(",");;
				
				sneezing = (CheckBox) findViewById(R.id.chkBoxSneezing);
				if(sneezing.isChecked())
					MessageBody.concat("@string/sneezing").concat(",");;
				
				sorethroat = (CheckBox) findViewById(R.id.chkBoxSore_Throat);
				if(sorethroat.isChecked())
					MessageBody.concat("@string/sorethroat").concat(",");;
				
				weightgain = (CheckBox) findViewById(R.id.chkBoxWeightGain);
				if(weightgain.isChecked())
					MessageBody.concat("@string/weightgain").concat(",");;
				
				weightloss = (CheckBox) findViewById(R.id.chkBoxWeightLoss);
				if(weightloss.isChecked())
					MessageBody.concat("@string/weightLoss").concat(".");;
				
				MessageBody.concat("Please advise.");
					
				emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, aEmailList);  
				emailIntent.putExtra(android.content.Intent.EXTRA_CC, aEmailCCList);  
				emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Side Effects of Medication");  
				emailIntent.setType("plain/text");  
	//			emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "My message body.");  
				emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, MessageBody);  

				//startActivity(emailIntent);
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
