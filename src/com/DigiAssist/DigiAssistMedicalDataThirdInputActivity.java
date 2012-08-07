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
package com.DigiAssist;

import java.util.HashMap;

import com.DigiAssist.DigiAssistMedicalDataThirdInputActivity;
import com.DigiAssist.DigiAssistActivity;
import com.DigiAssist.R;

import android.app.Activity;
import android.speech.tts.TextToSpeech.OnUtteranceCompletedListener;
import android.app.AlertDialog;
import android.os.Bundle;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DigiAssistMedicalDataThirdInputActivity extends Activity implements OnInitListener, OnUtteranceCompletedListener {
	
	private int MY_DATA_CHECK_CODE = 0;
	
	private TextToSpeech tts;
	
	private EditText respRate;
	private EditText pulseRate;
	private TextView respError;
	private TextView pulseError;
	private TextView respError1;
	private TextView pulseError1;
	private boolean check = false;
	private boolean checktwo = false;
	private Button speakSubmitButton;
	private Button submitAnotherButton;
	private TextWatcher textFilterWatcher;
	private ImageView help;
	private ImageView home;
	private boolean isAnother = false;
	    
	
    public void onCreate(Bundle savedInstanceState) {
		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third);
        
        respRate = (EditText) findViewById(R.id.respText);
        pulseRate = (EditText) findViewById(R.id.pulText);
        pulseError = (TextView) findViewById(R.id.errorPulTextView);
        respError = (TextView) findViewById(R.id.errorRespTextView);
        respError1 = (TextView) findViewById(R.id.errorRespTextView1);
        pulseError1 = (TextView) findViewById(R.id.errorPulTextView1);
        this.help = (ImageView)this.findViewById(R.id.imageHelp);
        this.home = (ImageView)this.findViewById(R.id.imageHome);
                
       // help.setOnClickListener(clickListener);
        //home.setOnClickListener(clickListener);
        respRate.setRawInputType(Configuration.KEYBOARD_12KEY);
        pulseRate.setRawInputType(Configuration.KEYBOARD_12KEY);
        
        respRate.addTextChangedListener(new TextWatcher() {

			public void afterTextChanged(Editable arg0) {
				if(respRate!=null && !respRate.getText().toString().equalsIgnoreCase("null")
						&& !respRate.getText().toString().equalsIgnoreCase(null)
						&& !respRate.getText().toString().equalsIgnoreCase("")){
//		        	System.out.println(temp);
//		            System.out.println(temp.getText());
//		            System.out.println(temp.getText().toString());
//		            System.out.println(Integer.valueOf(temp.getText().toString()));
//		            System.out.println("hghgh"+Integer.valueOf(temp.getText().toString()).intValue());
					
			        if(Integer.valueOf(respRate.getText().toString()).intValue()<=12 || Integer.valueOf(respRate.getText().toString()).intValue()>=18){
			        	System.out.println("inside");
			        	//respError.setText("Invalid value of Respiratory Rate: Normal range is between 12 to 18 beats per mintue");
			        	//temp.setBackgroundColor(0xF4FA58);
			        	tts.speak("You have entered invalid Respiratory Rate value", TextToSpeech.QUEUE_ADD , null);
 			        	respError.setText("Invalid value of Respiratory rate");
 			        	respError1.setText("Normal range: 80-170 mm Hg");
 			        	respError1.setTextColor(Color.RED);
 			        	respError.setTextColor(Color.RED);
			        	checktwo = true;
			        	//sysBP.setBackgroundColor(Color.RED);
			        	//check = true;

			        }
			        else{
			        	System.out.println("inside else");
			        	respError.setText("Normal range:40-139 mg/dl");
			        	respError1.setText("");
			        	respError.setTextColor(Color.BLACK);
			        	//respError.setText("");
			        	//temp.setFocusable(true);
			        	check = false;

			        }
				}
				else{
					respError.setText("Invalid value of Respiratory Rate: Normal range is between 12 to 18 beats per mintue");
		        	//temp.setBackgroundColor(0xF4FA58);
		        	check = true;
				}
			
				
			}

			
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				System.out.println("doing this here");
				// TODO Auto-generated method stub
				
			}

			
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				System.out.println("doing this1");
				// TODO Auto-generated method stub
				
			}
         });

        pulseRate.addTextChangedListener(new TextWatcher() {

			public void afterTextChanged(Editable arg0) {
				if(pulseRate!=null && !pulseRate.getText().toString().equalsIgnoreCase("null")
						&& !pulseRate.getText().toString().equalsIgnoreCase(null)
						&& !pulseRate.getText().toString().equalsIgnoreCase("")){
//		        	System.out.println(temp);
//		            System.out.println(temp.getText());
//		            System.out.println(temp.getText().toString());
//		            System.out.println(Integer.valueOf(temp.getText().toString()));
//		            System.out.println("hghgh"+Integer.valueOf(temp.getText().toString()).intValue());
					
			        if(Integer.valueOf(pulseRate.getText().toString()).intValue()<=96 || Integer.valueOf(pulseRate.getText().toString()).intValue()>=100){
			        	System.out.println("inside");
			        	//pulseError.setText("Invalid value of Pulse Rate: Normal range is between 60 to 100 beats per mintue");
			        	//temp.setBackgroundColor(0xF4FA58);
			        	tts.speak("You have entered invalid Systolic Blood Pressure value", TextToSpeech.QUEUE_ADD , null);
 			        	pulseError.setText("Invalid value of Systolic BP");
 			        	pulseError1.setText("Normal range: 80-170 mm Hg");
 			        	pulseError1.setTextColor(Color.RED);
 			        	pulseError.setTextColor(Color.RED);
			        	checktwo = true;

			        }
			        else{
			        	System.out.println("inside else");
			        //	pulseError.setText("");
			        	pulseError.setText("Normal range:40-139 mg/dl");
			        	pulseError1.setText("");
			        	pulseError.setTextColor(Color.BLACK);
			        	//temp.setFocusable(true);
			        	checktwo = false;

			        }
				}
				else{
					pulseError.setText("Invalid value of Pulse Rate: Normal range is between 60 to 100 beats per mintue");
		        	//temp.setBackgroundColor(0xF4FA58);
		        	checktwo = true;
				}
			
				
			}

			
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				System.out.println("doing this here");
				// TODO Auto-generated method stub
				
			}

			
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				System.out.println("doing this1");
				// TODO Auto-generated method stub
				
			}
         });        
        speakSubmitButton = (Button) findViewById(R.id.saveexitButton);
        
        speakSubmitButton.setOnClickListener(clickListener);   
                
        submitAnotherButton = (Button) findViewById(R.id.saveanotherButton);
        submitAnotherButton.setOnClickListener(clickListener);
        
        
                Intent checkIntent = new Intent();
		checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
		startActivityForResult(checkIntent, MY_DATA_CHECK_CODE);
		
		
    }
	View.OnClickListener clickListener = new View.OnClickListener(){
        public void  onClick  (View  v){
        	switch(v.getId()){
        	
	    	case R.id.saveexitButton:	    		
	    		if(!check && !checktwo){
	    					HashMap<String, String> myHashAlarm = new HashMap();
	    					myHashAlarm.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID,
	    			        "Yes");
	    					myHashAlarm.put("GetOut","Yes");
	    					boolean now = false;
	    					String respValue = respRate.getText().toString();
	    					String pulValue = pulseRate.getText().toString();
	    					//System.out.println(tempValue);
	    					
	    					if (respValue!=null && respValue.length()>0) {
	    						Toast.makeText(DigiAssistMedicalDataThirdInputActivity.this, "Saying: Respiratory Rate", Toast.LENGTH_LONG).show();
	    						tts.speak("Respiratory rate as"+respValue, TextToSpeech.QUEUE_ADD, null);
	    					}
	    					if (pulValue!=null && pulValue.length()>0) {
	    						Toast.makeText(DigiAssistMedicalDataThirdInputActivity.this, "Saying: Pulse Rate", Toast.LENGTH_LONG).show();
	    						tts.speak("Pulse rate as"+pulValue, TextToSpeech.QUEUE_ADD, myHashAlarm);
	    						now = true;
	    					}

	    	        
	    	        }
	    		break;
	    	case R.id.saveanotherButton:	    		
	    		if(!check && !checktwo){
	    				HashMap<String, String> myHashAlarm = new HashMap();
	    				myHashAlarm.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID,
	    		        "No");
	    				myHashAlarm.put("GetOut","No");
	    				boolean now = false;
	    				String respValue = respRate.getText().toString();
	    				String pulValue = pulseRate.getText().toString();
	    				//System.out.println(tempValue);
	    				
	    				if (respValue!=null && respValue.length()>0) {
	    					Toast.makeText(DigiAssistMedicalDataThirdInputActivity.this, "Saying: Respiratory Rate", Toast.LENGTH_LONG).show();
	    					tts.speak("Respiratory rate as"+respValue, TextToSpeech.QUEUE_ADD, null);
	    				}
	    				if (pulValue!=null && pulValue.length()>0) {
	    					Toast.makeText(DigiAssistMedicalDataThirdInputActivity.this, "Saying: Pulse Rate", Toast.LENGTH_LONG).show();
	    					tts.speak("Pulse rate as"+pulValue, TextToSpeech.QUEUE_ADD, myHashAlarm);
	    					now = true;
	    				}
	            
	            }
		
	    		break;

	    	case R.id.imageHome:	    		
	    		Intent msg = new Intent(DigiAssistMedicalDataThirdInputActivity.this,DigiAssistActivity.class);
	    		DigiAssistMedicalDataThirdInputActivity.this.startActivity(msg);
	    		break;
	    	case R.id.imageHelp:	    		
	    	 	// prepare the alert box
	            AlertDialog.Builder alertbox = new AlertDialog.Builder(DigiAssistMedicalDataThirdInputActivity.this);
	            // set the message to display
	            alertbox.setMessage("HELP: The Sliding Tiles Puzzle allows you to play in two modes." );
	 
	            CharSequence ok = "OK";
	            // set a positive/yes button and create a listener
	            alertbox.setPositiveButton(ok, new DialogInterface.OnClickListener() {
	 
	                // close the help when the button is clicked
	                
					public void onClick(DialogInterface arg0, int arg1) {
	                	arg0.dismiss();
	                }
	            });
	 
	            // display box
	            alertbox.show();
	    		break;
	    	
	    	
	    		
        	}
        	
        	
        }
    };
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == MY_DATA_CHECK_CODE) {
			if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
				// success, create the TTS instance
				tts = new TextToSpeech(this, this);
			} 
			else {
				// missing data, install it
				Intent installIntent = new Intent();
				installIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
				startActivity(installIntent);
			}
		}

	}

	
	public void onInit(int status) {		
		if (status == TextToSpeech.SUCCESS) {
//			Toast.makeText(MedicalDataInputActivity.this, 
//					"Text-To-Speech engine is initialized", Toast.LENGTH_LONG).show();
		}
		else if (status == TextToSpeech.ERROR) {
//			Toast.makeText(MedicalDataInputActivity.this, 
//					"Error occurred while initializing Text-To-Speech engine", Toast.LENGTH_LONG).show();
		}
		tts.setOnUtteranceCompletedListener(this);
	}
	
	public void onUtteranceCompleted(String arg0) {
		// TODO Auto-generated method stub
		System.out.println("here i m "+arg0);
		if(arg0.equalsIgnoreCase("Yes")){
			Intent msg = new Intent(DigiAssistMedicalDataThirdInputActivity.this,DigiAssistActivity.class);
			msg.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			msg.putExtra("EXIT", true);
			DigiAssistMedicalDataThirdInputActivity.this.startActivity(msg);

		}
		if(arg0.equalsIgnoreCase("No")){
			Intent msg = new Intent(DigiAssistMedicalDataThirdInputActivity.this,DigiAssistActivity.class);
			
			DigiAssistMedicalDataThirdInputActivity.this.startActivity(msg);
		}
	}
	


		
}
