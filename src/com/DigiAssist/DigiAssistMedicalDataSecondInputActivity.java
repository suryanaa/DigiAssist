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

import com.DigiAssist.R;
import com.DigiAssist.DigiAssistMedicalDataThirdInputActivity;


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
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DigiAssistMedicalDataSecondInputActivity extends Activity implements OnInitListener, OnUtteranceCompletedListener {
	
	private int MY_DATA_CHECK_CODE = 0;
	
	private TextToSpeech tts;
	
	private EditText respRate;
	private TextView respError;
	private TextView respError1;
	private EditText sysBP;
	private EditText diaBP;
	private TextView BPError;
	private TextView BPError1;
	private EditText oxySat;
	private TextView oSError;
	//private TextView sysError1;
	//private TextView diaError1;
	private TextView oSError1;
	private boolean check = false;
	private boolean checktwo = false;
	private boolean checkthree = false;
	private boolean checkfour =false;
	private boolean checkfive=false;
	private Button speakSubmitButton;
	private TextWatcher textFilterWatcher;
	private ImageView help;
	//private ImageView home;
	private Button home;
	private Button Next2;
	private Button Back2;
	private Button helpBP;
	private Button helpoxysat;
	private Button helpResprate;
	
	private Boolean MoveToNextScreen1=false;
	private Boolean uComplete = false;
    public void onCreate(Bundle savedInstanceState) {
		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        sysBP = (EditText) findViewById(R.id.SysBPText);
        diaBP = (EditText) findViewById(R.id.DiaBPText);
        oxySat = (EditText) findViewById(R.id.OxyText);
        BPError = (TextView) findViewById(R.id.errorBPTextView);
        BPError1 = (TextView) findViewById(R.id.errorBPTextView1);
        //diaError = (TextView) findViewById(R.id.errorDiaBPTextView);
        //diaError1 = (TextView) findViewById(R.id.errorDiaBPTextView1);
        oSError = (TextView) findViewById(R.id.errorOxyTextView);
        oSError1 = (TextView) findViewById(R.id.errorOxyTextView1);
        this.helpBP = (Button) this.findViewById(R.id.helpBP);
        this.helpoxysat  = (Button) this.findViewById(R.id.helpoxysat);
        this.helpResprate = (Button) this.findViewById(R.id.helpResprate);
        
        respRate = (EditText) findViewById(R.id.pulText);
        respError = (TextView) findViewById(R.id.errorRespTextView);
        respError1 = (TextView) findViewById(R.id.errorRespTextView1);
       // this.help = (ImageView)this.findViewById(R.id.imageHelp);
       // this.home = (ImageView)this.findViewById(R.id.imageHome);
        this.home = (Button)this.findViewById(R.id.imageHome);

        this.Next2 = (Button) this.findViewById(R.id.arrowfront2);
        this.Back2 = (Button) this.findViewById(R.id.arrowback2);
        
        Next2.setOnClickListener(clickListener);
        Back2.setOnClickListener(clickListener);
        home.setOnClickListener(clickListener);

        helpBP.setOnClickListener(clickListener);
        helpoxysat.setOnClickListener(clickListener);
        helpResprate.setOnClickListener(clickListener);
        
        sysBP.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			
			public void onFocusChange(View v, boolean hasFocus) {
				if(sysBP!=null && !sysBP.getText().toString().equalsIgnoreCase("null")
						&& !sysBP.getText().toString().equalsIgnoreCase(null)
						&& !sysBP.getText().toString().equalsIgnoreCase("")) {
					
 					int val = 0;
					try {
						val = Integer.valueOf(sysBP.getText().toString()).intValue();
				    } catch(NumberFormatException x) {
						
					}
					
			        if(val < 80 || val > 170) {
			        	System.out.println("inside");
			        	//sysError.setText("Invalid value of Systolic Blood Pressure: Normal value is 120 mm Hg");
			        	//sysError.setTextColor(Color.RED);
			        	//temp.setBackgroundColor(0xF4FA58);
			        	tts.speak("Invalid systolic blood pressure value", TextToSpeech.QUEUE_ADD , null);
 			        	BPError.setText("Invalid systolic blood pressure");
 			        	BPError1.setText("Normal Systolic Blood Pressure: 80-170 mm Hg");
 			        	BPError1.setTextColor(Color.RED);
 			        	BPError.setTextColor(Color.RED);
 			        	sysBP.setTextColor(Color.RED);
			        	check = true;
			        	//sysBP.setBackgroundColor(Color.RED);

			        }
			        else {
			        	System.out.println("inside else");
			        	BPError.setText("Normal range: 80-170 mmHg / 50-100 mmHg");
			        	BPError1.setText("");
			        	BPError.setTextColor(Color.BLACK);
			        	sysBP.setTextColor(Color.BLACK);
 			        	//temp.setFocusable(true);
			        	//sysError.setText("");
			        	//temp.setFocusable(true);
			        	check = false;
			        }
				}
				else{
					BPError.setText("Invalid value of Systolic BP");
			        	BPError1.setText("Normal SystolicBP: 80-170 mm Hg");
			        	BPError1.setTextColor(Color.RED);
			        	BPError.setTextColor(Color.RED);
			        	sysBP.setTextColor(Color.RED);
		        	check = true;
					//BPError.setText("Invalid value of Systolic Blood Pressure: Normal value is 120 mm Hg");
		        	//temp.setBackgroundColor(0xF4FA58);
		        	check = true;
				}
			}
		});
        
        oxySat.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			public void onFocusChange(View v, boolean hasFocus) {
				if(oxySat!=null && !oxySat.getText().toString().equalsIgnoreCase("null")
						&& !oxySat.getText().toString().equalsIgnoreCase(null)
						&& !oxySat.getText().toString().equalsIgnoreCase("")){
					
 					int val = 0;
					try {
						val = Integer.valueOf(oxySat.getText().toString()).intValue();
				    } catch(NumberFormatException x) {
						
					}

					if(val < 20 || val > 100) {
			        	System.out.println("inside");
			        	//oSError.setText("Invalid value of Oxygen Saturation: Normal range is between 96% to 100%");
			        	//temp.setBackgroundColor(0xF4FA58);
			        	tts.speak("Invalid oxygen saturation value", TextToSpeech.QUEUE_ADD , null);
 			        	oSError.setText("Invalid oxygen saturation value");
 			        	oSError1.setText("Normal range: 20-100%");
 			        	oSError1.setTextColor(Color.RED);
 			        	oSError.setTextColor(Color.RED);
 			        	oxySat.setTextColor(Color.RED);
 			        	//temp.setBackgroundColor(0xF4FA58);
 			        	//bGError.setBackgroundColor(Color.RED);
 			        	//check = true;
			        	checktwo = true;

			        }
			        else {
			        	System.out.println("inside else");
			        	oSError.setText("Normal range:40-139 mg/dl");
 			        	oSError1.setText("");
 			        	oSError.setTextColor(Color.BLACK);
 			        	oxySat.setTextColor(Color.BLACK);
			        	//oSError.setText("");
			        	//temp.setFocusable(true);
			        	checktwo = false;
			        }
				}
				else{
					oSError.setText("Invalid Oxygen Saturation value");
					oSError1.setText("Normal range : 20-100%");
					oSError1.setTextColor(Color.RED);
			        oSError.setTextColor(Color.RED);
		        	checktwo = true;
				}
			}
		});
        
        respRate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			public void onFocusChange(View v, boolean hasFocus) {
				if(respRate!=null && !respRate.getText().toString().equalsIgnoreCase("null")
						&& !respRate.getText().toString().equalsIgnoreCase(null)
						&& !respRate.getText().toString().equalsIgnoreCase("")){
					
 					int val = 0;
					try {
						val = Integer.valueOf(respRate.getText().toString()).intValue();
				    } catch(NumberFormatException x) {
						
					}

					if(val < 12 || val > 18) {
			        	System.out.println("inside");
			        	tts.speak("Invalid respiratory rate", TextToSpeech.QUEUE_ADD , null);
			        	respError.setText("Invalid respiratory rate");
			        	respError1.setText("Normal range: 12-18 beats per minute");
			        	respError.setTextColor(Color.RED);
			        	respError.setTextColor(Color.RED);
 			        	respRate.setTextColor(Color.RED);
			        	checkfour = true;
			        }
			        else {
			        	System.out.println("inside else");
			        	oSError.setText("Normal range:12-18 beats per minute");
 			        	oSError1.setText("");
 			        	oSError.setTextColor(Color.BLACK);
 			        	respRate.setTextColor(Color.BLACK);
 			        	checkfour = false;
			        }
				}
				else {
					oSError.setText("Invalid respiratory rate: " +
							"Normal range is between 12 to 18 beats per minute");
					checkfour = true;
				}
			}
		});

        diaBP.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			
			public void onFocusChange(View v, boolean hasFocus) {
				if(diaBP!=null && !diaBP.getText().toString().equalsIgnoreCase("null")
						&& !diaBP.getText().toString().equalsIgnoreCase(null)
						&& !diaBP.getText().toString().equalsIgnoreCase("")){
					
 					int val = 0;
					try {
						val = Integer.valueOf(diaBP.getText().toString()).intValue();
				    } catch(NumberFormatException x) {
						
					}

					if(val < 80 || val > 170) {
			        	System.out.println("inside");
			        	tts.speak("Invalid Diastolic Blood Pressure", TextToSpeech.QUEUE_ADD , null);
			        	BPError.setText("Invalid Diastolic Blood Pressure");
 			        	BPError1.setText("Normal Diastolic Blood Pressure: 80-170 mm Hg");
 			        	BPError1.setTextColor(Color.RED);
 			        	BPError.setTextColor(Color.RED);
 			        	diaBP.setTextColor(Color.RED);
			        	checkfive = true;
			        }
			        else {
			        	BPError.setText("Normal range: 80-170 mmHg / 50-100 mmHg");
			        	BPError1.setText("");
			        	BPError.setTextColor(Color.BLACK);
			        	diaBP.setTextColor(Color.BLACK);
 			        	checkfive = false;

			        }
				}
				else {
					BPError.setText("Invalid value of Systolic BP");
			        	BPError1.setText("Normal SystolicBP: 80-170 mm Hg");
			        	BPError1.setTextColor(Color.RED);
			        	BPError.setTextColor(Color.RED);
			        	diaBP.setTextColor(Color.RED);
		        	checkfive = true;
				}
			}
		});
 
		speakSubmitButton = (Button) findViewById(R.id.arrowfront2);
        speakSubmitButton.setOnClickListener(clickListener);
                Intent checkIntent = new Intent();
		checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
		startActivityForResult(checkIntent, MY_DATA_CHECK_CODE);
		
		
    }
	View.OnClickListener clickListener = new View.OnClickListener(){
        public void  onClick  (View  v){
        	switch(v.getId()){
        	case R.id.arrowfront2:
                if(check==false && checktwo==false && checkthree==false  && checkfour==false && checkfive==false){
                    AlertDialog.Builder alertboxSave = new AlertDialog.Builder(DigiAssistMedicalDataSecondInputActivity.this);
                     // set the message to display
                    alertboxSave.setMessage("Data Saved\n"+
                     "Medical Data for Ms Luis has been saved.\n");
          
                     CharSequence okSave = "OK";
                     // set a positive/yes button and create a listener
                    alertboxSave.setPositiveButton(okSave, new DialogInterface.OnClickListener() {
          
                         // close the help when the button is clicked
                         
                         public void onClick(DialogInterface arg0, int arg1) {
                             arg0.dismiss();
                         }
                     });
          
                     // display box
                   alertboxSave.show();
                    HashMap<String, String> myHashAlarm = new HashMap();
                myHashAlarm.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID,
                "Yes");
                boolean now = false;
                String sysValue = sysBP.getText().toString();
                String diaValue = diaBP.getText().toString();
                String OxyValue = oxySat.getText().toString();
                String respValue = respRate.getText().toString();
                
                //System.out.println(tempValue);
                
                if (sysValue!=null && sysValue.length()>0) {
                    //Toast.makeText(DigiAssistMedicalDataSecondInputActivity.this, "Saying: Systolic Blood Pressure", Toast.LENGTH_LONG).show();
                    tts.speak("Systolic blood pressure value is"+sysValue, TextToSpeech.QUEUE_ADD, null);
                }
                if (diaValue!=null && diaValue.length()>0) {
                    if(tts.isSpeaking())
                    {
                    //Toast.makeText(DigiAssistMedicalDataSecondInputActivity.this, "Saying: Diastolic Blood Pressure", Toast.LENGTH_LONG).show();
                    tts.speak("Diastolic blood pressure value is"+diaValue, TextToSpeech.QUEUE_ADD, null);
                    //now = true;
                    }
                }
                if (OxyValue!=null && OxyValue.length()>0) {
                    //if(tts.isSpeaking())
                    {
                    //Toast.makeText(DigiAssistMedicalDataSecondInputActivity.this, "Saying: Oxygen Saturation", Toast.LENGTH_LONG).show();
                    tts.speak("Oxygen saturation value is"+OxyValue, TextToSpeech.QUEUE_ADD, myHashAlarm);
                    now = true;
                    }
                }
                if (respValue!=null && respValue.length()>0) {
                    //Toast.makeText(DigiAssistMedicalDataSecondInputActivity.this, "Saying: Respiratory Rate", Toast.LENGTH_LONG).show();
                    tts.speak("Respiratory rate is"+respValue, TextToSpeech.QUEUE_ADD, null);
                    MoveToNextScreen1 = true;
                }
                
                tts.speak("Medical data for Ms. Luis has been saved.", TextToSpeech.QUEUE_ADD, myHashAlarm);
                
                }
                break;
	    	case R.id.imageHome:	    		
	    		break;
	    	case R.id.helpBP:
        		AlertDialog.Builder alertboxBP = new AlertDialog.Builder(DigiAssistMedicalDataSecondInputActivity.this);
 	            // set the message to display
        		 alertboxBP.setMessage("HELP\n"+
 	            "Systlolic Blood pressure range is 80-170 mmHg. and Diastolic blood pressure range is 50-100 mmHg. You can either type the numeric values or speak them");
 	 
 	            CharSequence okBP = "OK";
 	            // set a positive/yes button and create a listener
 	           alertboxBP.setPositiveButton(okBP, new DialogInterface.OnClickListener() {
 	 
 	                // close the help when the button is clicked
 	                
 					public void onClick(DialogInterface arg0, int arg1) {
 	                	arg0.dismiss();
 	                }
 	            });
 	 
 	            // display box
 	          alertboxBP.show();
 	    		break;
        	case R.id.helpResprate:
        		AlertDialog.Builder alertboxresprate = new AlertDialog.Builder(DigiAssistMedicalDataSecondInputActivity.this);
 	            // set the message to display
        		alertboxresprate.setMessage("HELP\n"+
 	            "Respiration rate range is 10-40 beats per minute. You can either type the numeric values or speak them");
 	 
 	            CharSequence okresprate = "OK";
 	            // set a positive/yes button and create a listener
 	           alertboxresprate.setPositiveButton(okresprate, new DialogInterface.OnClickListener() {
 	 
 	                // close the help when the button is clicked
 					public void onClick(DialogInterface arg0, int arg1) {
 	                	arg0.dismiss();
 	                }
 	            });
 	 
 	            // display box
 	          alertboxresprate.show();
 	    		break;
        	case R.id.helpoxysat:
        		AlertDialog.Builder alertboxOxysat = new AlertDialog.Builder(DigiAssistMedicalDataSecondInputActivity.this);
 	            // set the message to display
        		alertboxOxysat.setMessage("HELP\n"+
 	            "Oxygen saturation range is 20-100%. You can either type the numeric values or speak them");
 	 
 	            CharSequence okoxysat = "OK";
 	            // set a positive/yes button and create a listener
 	           alertboxOxysat.setPositiveButton(okoxysat, new DialogInterface.OnClickListener() {
 	 
 	                // close the help when the button is clicked
 	                public void onClick(DialogInterface arg0, int arg1) {
 	                	arg0.dismiss();
 	                }
 	            });
 	 
 	            // display box
 	          alertboxOxysat.show();
 	    		break;
	    	case R.id.arrowback2:
	    		Intent msg1 = new Intent(DigiAssistMedicalDataSecondInputActivity.this,DigiAssistMedicalDataFirstInputActivity.class);
				
	    		DigiAssistMedicalDataSecondInputActivity.this.startActivity(msg1);
	    		//startActivity(new Intent("com.DigiAssist.DigiAssistMedicalDataFirstInputActivity"));
	    		//Intent msgback = new Intent(DigiAssistMedicalDataSecondInputActivity.this,DigiAssistMedicalDataFirstInputActivity.class);
	    		//DigiAssistMedicalDataSecondInputActivity.this.startActivity(msgback);
	    		break;
	    	case R.id.imageHelp:	    		
	    	 	// prepare the alert box
	            AlertDialog.Builder alertbox = new AlertDialog.Builder(DigiAssistMedicalDataSecondInputActivity.this);
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

	
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		tts.stop();
		return super.onTouchEvent(event);
		
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
		
	if (arg0.equalsIgnoreCase("Yes"))
		{
			//Toast.makeText(DigiAssistMedicalDataSecondInputActivity.this, "Medical Information for Ms. Luis is saved", Toast.LENGTH_LONG).show();
			Intent msg = new Intent(DigiAssistMedicalDataSecondInputActivity.this,DigiAssistActivity.class);
			DigiAssistMedicalDataSecondInputActivity.this.startActivity(msg);

		}
	
			
	}
	


		
}