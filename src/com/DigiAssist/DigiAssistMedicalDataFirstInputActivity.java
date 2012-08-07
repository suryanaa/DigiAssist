package com.DigiAssist;

import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.speech.tts.TextToSpeech.OnUtteranceCompletedListener;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;

//import com.medical.MedicalDataInputSecondActivity;
//import com.medical.MedicalDataMainActivity;
import com.DigiAssist.R;
import com.DigiAssist.DigiAssistActivity;
import com.DigiAssist.DigiAssistMedicalDataSecondInputActivity;


public class DigiAssistMedicalDataFirstInputActivity extends Activity implements OnInitListener,OnUtteranceCompletedListener {

	private int MY_DATA_CHECK_CODE = 0;
	
	private TextToSpeech tts;
	
	private SeekBar seekBarValue;
	private EditText temp;
	private TextView tempError;
	private TextView tempError1;
	private EditText pulseRate;
	private TextView pulseError;
	private TextView pulseError1;
	private boolean check = false;
	private boolean checkone = false;
	private boolean checktwo = false;
	private boolean checkthree = false;
	private boolean MoveToNextScreen = false;
	private Button speakSubmitButton;
	private Button helpTempButton;
	private Button helpbloddglucosebutton;
	private Button helpPulserate;
	private Button helpPainLevel;
	private TextWatcher textFilterWatcher;
	private ImageView help;
	private ImageView home;
	private EditText bloodGlucose;
	private TextView bGError;
	private TextView bGError1;
	private Button Next1;
	private Button Back1;
	private ImageView imagehelp;
		    
	
    public void onCreate(Bundle savedInstanceState) {
		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first);
        seekBarValue = (SeekBar) findViewById(R.id.Painlevelseekbar);
        temp = (EditText) findViewById(R.id.TempTextfirst);
        temp.setOnClickListener(clickListener);
        tempError = (TextView) findViewById(R.id.errorTempView);
        tempError1 = (TextView) findViewById(R.id.errorTempView1);
        bloodGlucose = (EditText) findViewById(R.id.BloodGlucoseFirst);
        bloodGlucose.setOnClickListener(clickListener);
        bGError = (TextView) findViewById(R.id.errorBGTextView);
        bGError1 = (TextView) findViewById(R.id.errorBGTextView1);
        pulseRate = (EditText) findViewById(R.id.pulText);
        pulseRate.setOnClickListener(clickListener);
        pulseError = (TextView) findViewById(R.id.errorPulTextView);
        pulseError1 = (TextView) findViewById(R.id.errorPulTextView1);
        
		//this.help = (ImageView)this.findViewById(R.id.imageHelp);
		this.helpTempButton = (Button)this.findViewById(R.id.helptemp);
        this.helpbloddglucosebutton = (Button)this.findViewById(R.id.bloddglucosehelp);
        this.helpPulserate= (Button)this.findViewById(R.id.helpPulserate);
        this.helpPainLevel = (Button)this.findViewById(R.id.helpPainLevel);
        this.home = (ImageView)this.findViewById(R.id.imageHome);
		this.imagehelp = (ImageView)this.findViewById(R.id.imageHelp);
        final TextView reading = (TextView) findViewById(R.id.textView4);
        seekBarValue.setMax(10);
        
        
      
        this.Next1 = (Button) this.findViewById(R.id.arrowfront1);
        this.Back1 = (Button) this.findViewById(R.id.arrowback1);
        helpPainLevel.setOnClickListener(clickListener);
        helpTempButton.setOnClickListener(clickListener);
        helpbloddglucosebutton.setOnClickListener(clickListener);
        helpPulserate.setOnClickListener(clickListener);
        Next1.setOnClickListener(clickListener);
        Back1.setOnClickListener(clickListener);
        home.setOnClickListener(clickListener);
        imagehelp.setOnClickListener(clickListener);
        seekBarValue.setOnSeekBarChangeListener(new OnSeekBarChangeListener()
        {
        	
     	   public void onProgressChanged(SeekBar seekBar, int progress,
     	     boolean fromUser) {
     	    reading.setText("  "+progress);
     	   }

			public void onStartTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				
			}

			public void onStopTrackingTouch(SeekBar arg0) {
				// TODO Auto-generated method stub
				
			}

        });
        
        bloodGlucose.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			
			public void onFocusChange(View v, boolean hasFocus) {
 				if(bloodGlucose!=null && !bloodGlucose.getText().toString().equalsIgnoreCase("null")
 						&& !bloodGlucose.getText().toString().equalsIgnoreCase(null)
 						&& !bloodGlucose.getText().toString().equalsIgnoreCase("")){

 					int val = 0;
					try {
						val = Float.valueOf(bloodGlucose.getText().toString()).intValue();
				    } catch(NumberFormatException x) {
						
					}

 			        if(val < 40 || val > 139) {
 			        	System.out.println("inside");
 			        	tts.speak("Invalid Blood Glucose value", TextToSpeech.QUEUE_ADD , null);
 			        	bGError.setText("Invalid Blood Glucose Value");
 			        	bGError1.setText("Normal range: 40 mg/dl- 139 mg/dl");
 			        	bGError1.setTextColor(Color.RED);
 			        	bGError.setTextColor(Color.RED);
 			        	bloodGlucose.setTextColor(Color.RED);
 			        	//temp.setBackgroundColor(0xF4FA58);
 			        	//bGError.setBackgroundColor(Color.RED);
 			        	check = true;

 			        }
 			        else{
 			        	System.out.println("inside else");
 			        	bGError.setText("Normal range:40-139 mg/dl");
 			        	bGError1.setText("");
 			        	bGError.setTextColor(Color.BLACK);
 			        	bloodGlucose.setTextColor(Color.BLACK);
 			        	//temp.setFocusable(true);
 			        	check = false;

 			        }
 				}
 				else{
 					bGError.setText("Invalid value of Blood Glucose: Normal range:40 mg/dl to 139 mg/dl");
 		        	//temp.setBackgroundColor(0xF4FA58);
 		        	check = true;
 				}
			}
		});
        
        temp.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			
			public void onFocusChange(View v, boolean hasFocus) {
				// If focus lost, validate the input text and take action
				if(!hasFocus) {
					if(temp!=null 
							&& !temp.getText().toString().equalsIgnoreCase("null")
							&& !temp.getText().toString().equalsIgnoreCase(null)
							&& !temp.getText().toString().equalsIgnoreCase("")){
						
						int val = 0;
						try {
							val = Integer.valueOf(temp.getText().toString()).intValue();
					    } catch(NumberFormatException x) {
							
						}
				        if(val < 95 || val > 105){
				        	System.out.println("inside");
				        	tts.speak("Invalid temperature value", TextToSpeech.QUEUE_ADD , null);
				        	//tempError.setText("Invalid value of Temperature: Normal range:95F-105F");
				        	tempError.setText("Invalid value of Temperature");
				        	tempError1.setText("Normal range:95F-105F");
				        	tempError1.setTextColor(Color.RED);
				        	tempError.setTextColor(Color.RED);
				        	temp.setTextColor(Color.RED);
				        	//temp.setBackgroundColor(0xF4FA58);
				        	checkone = true;

				        } else {
				        	System.out.println("inside else");
				        	//tempError.setText("");
				        	tempError.setText("Normal range:95F-105F");
				        	tempError1.setText("");
				        	tempError.setTextColor(Color.BLACK);
				        	temp.setTextColor(Color.BLACK);
				        	//temp.setFocusable(true);
				        	checkone = false;

				          }
				        }
				    	else{
				    		tempError.setText("Invalid value of Temperature: Normal range:95F-105F");
	     		        	//temp.setBackgroundColor(0xF4FA58);
	     		        	checkone = true;
					}					
				}
			}
		});

        pulseRate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if(pulseRate!=null && !pulseRate.getText().toString().equalsIgnoreCase("null")
						&& !pulseRate.getText().toString().equalsIgnoreCase(null)
						&& !pulseRate.getText().toString().equalsIgnoreCase("")) {
					
					int val = 0;
					try {
						val = Integer.valueOf(pulseRate.getText().toString()).intValue();
				    } catch(NumberFormatException x) {
						
					}

					if(val < 49 || val > 150) {
			        	System.out.println("inside");
			        	//pulseError.setText("Invalid value of Pulse Rate: Normal range is between 60 to 100 beats per mintue");
			        	//temp.setBackgroundColor(0xF4FA58);
			        	tts.speak("Invalid Pulse rate value", TextToSpeech.QUEUE_ADD , null);
 			        	pulseError.setText("Invalid value of Pulse rate");
 			        	pulseError1.setText("Normal range:50-150 beats per  minute");
 			        	pulseError1.setTextColor(Color.RED);
 			        	pulseError.setTextColor(Color.RED);
 			        	pulseRate.setTextColor(Color.RED);
 			        	//BackgroundColor(Color.YELLOW);
 			        	
			        	checkthree = true;
			        }
			        else {
			        	System.out.println("inside else");
			        //	pulseError.setText("");
			        	pulseError.setText("Normal range:50-150 beats per minute");
			        	pulseError1.setText("");
			        	pulseError.setTextColor(Color.BLACK);
			        	pulseRate.setTextColor(Color.BLACK);
			        	//temp.setFocusable(true);
			        	checkthree = false;

			        }					

				}
				else {
					pulseError.setText("Invalid value : Normal range: 50 - 150 bpm");
		        	//temp.setBackgroundColor(0xF4FA58);
					
		        	checkthree = true;
				}				
			}
		});

        speakSubmitButton = (Button) findViewById(R.id.arrowfront1);
        
        speakSubmitButton.setOnClickListener(clickListener);
                Intent checkIntent = new Intent();
		checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
		startActivityForResult(checkIntent, MY_DATA_CHECK_CODE);


       		
	}
	View.OnClickListener clickListener = new View.OnClickListener(){
        public void  onClick  (View  v){
        	switch(v.getId()){
		case R.id.helptemp:
        		 AlertDialog.Builder alertboxtemp = new AlertDialog.Builder(DigiAssistMedicalDataFirstInputActivity.this);
 	            // set the message to display
        		 alertboxtemp.setMessage("HELP\n"+
 	            "Temperature range is 95-105F. You can either type the numeric values or speak them");
 	 
 	            CharSequence ok = "OK";
 	            // set a positive/yes button and create a listener
 	           alertboxtemp.setPositiveButton(ok, new DialogInterface.OnClickListener() {
 	 
 	                // close the help when the button is clicked
 	                public void onClick(DialogInterface arg0, int arg1) {
 	                	arg0.dismiss();
 	                }
 	            });
 	 
 	            // display box
 	          alertboxtemp.show();
 	    		break;
        	case R.id.bloddglucosehelp:
       		 AlertDialog.Builder alertboxbloodglucose = new AlertDialog.Builder(DigiAssistMedicalDataFirstInputActivity.this);
	            // set the message to display
       		alertboxbloodglucose.setMessage("HELP\n"+
	            "Blood Glucose Range is 40-139mg/dl. You can either type the numeric values or speak them");
	 
	            CharSequence okbloodglucose = "OK";
	            // set a positive/yes button and create a listener
	            alertboxbloodglucose.setPositiveButton(okbloodglucose, new DialogInterface.OnClickListener() {
	 
	                // close the help when the button is clicked
	                public void onClick(DialogInterface arg0, int arg1) {
	                	arg0.dismiss();
	                }
	            });
	 
	            // display box
	            alertboxbloodglucose.show();
	    		break;
        	case R.id.helpPulserate:
          		 AlertDialog.Builder alertboxpulserate = new AlertDialog.Builder(DigiAssistMedicalDataFirstInputActivity.this);
   	            // set the message to display
          		alertboxpulserate.setMessage("HELP\n"+
   	            "Pulse Rate Range is 50-150 Beats per minute. You can either type the numeric values or speak them");
   	 
   	            CharSequence okPulserate = "OK";
   	            // set a positive/yes button and create a listener
   	         alertboxpulserate.setPositiveButton(okPulserate, new DialogInterface.OnClickListener() {
   	 
   	                // close the help when the button is clicked
   	                public void onClick(DialogInterface arg0, int arg1) {
   	                	arg0.dismiss();
   	                }
   	            });
   	 
   	            // display box
   	      alertboxpulserate.show();
   	    		break;
        	case R.id.helpPainLevel:
        		 AlertDialog.Builder alertboxpainlevel = new AlertDialog.Builder(DigiAssistMedicalDataFirstInputActivity.this);
    	            // set the message to display
        		 alertboxpainlevel.setMessage("HELP : Pain Level Range is 0-10.\n " +
    	            "0 means no pain and 10 means severe pain.\n" +
        			"1 , 2 = Mild discomfort\n" +
        			"3 , 4 = Slightly painful\n" +
        			"5 , 6 = Painful \n" +
        			"7 , 8 = Highly Painful\n" +
        			"9 , 10 = Unbearable Pain, need Drugs\n\n" +
    	            " You can either move the slider using your fingers or values or speak the numeric value");
    	 
    	            CharSequence okPain = "OK";
    	            // set a positive/yes button and create a listener
    	            alertboxpainlevel.setPositiveButton(okPain, new DialogInterface.OnClickListener() {
    	 
    	                // close the help when the button is clicked
    	                public void onClick(DialogInterface arg0, int arg1) {
    	                	arg0.dismiss();
    	                }
    	            });
    	 
    	            // display box
    	            alertboxpainlevel.show();
    	    		break;        	
	    	case R.id.arrowfront1:	    		
			if(check==false && checkone==false&& checktwo==false && checkthree==false){
				HashMap<String, String> myHashAlarm = new HashMap();
				myHashAlarm.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID,
			    "Yes");
				boolean now = false;
				int painValue = seekBarValue.getProgress();
				String pain = String.valueOf(painValue);
				String tempValue = temp.getText().toString();
				String bGValue = bloodGlucose.getText().toString();
				String pulValue = pulseRate.getText().toString();
				System.out.println(tempValue);
				
				if (pain!=null && pain.length()>0) {
					//Toast.makeText(DigiAssistMedicalDataFirstInputActivity.this, "Pain Level", Toast.LENGTH_LONG).show();
					tts.speak("Pain value entered is"+pain, TextToSpeech.QUEUE_ADD , null);
					if(painValue>9)
					{
						tts.speak("It seems like an emergency situation, you might consider taking help of senior doctors", TextToSpeech.QUEUE_ADD , null);
					}
					
				}
				if (tempValue!=null && tempValue.length()>0) {
					if(tts.isSpeaking())
					{
				//	Toast.makeText(DigiAssistMedicalDataFirstInputActivity.this, "Body Temperature", Toast.LENGTH_LONG).show();
					tts.speak("Temperature is"+tempValue, TextToSpeech.QUEUE_ADD, null);
					
					//now = false;
					}
				}
				if (bGValue!=null && bGValue.length()>0) {
					//if(tts.isSpeaking())
					{
					//Toast.makeText(DigiAssistMedicalDataFirstInputActivity.this, "Blood Glucose", Toast.LENGTH_LONG).show();
					tts.speak("Glucose value is"+bGValue, TextToSpeech.QUEUE_ADD, null);
					
					//now = true;
					}
				}
				if (pulValue!=null && pulValue.length()>0) {
					//if(tts.isSpeaking())
					{
					//Toast.makeText(DigiAssistMedicalDataFirstInputActivity.this, "Pulse Rate", Toast.LENGTH_LONG).show();
					tts.speak("Pulse rate as"+pulValue, TextToSpeech.QUEUE_ADD, myHashAlarm);
					//tts.playSilence(1000, TextToSpeech.QUEUE_ADD, myHashAlarm);
					
					MoveToNextScreen = true;
				}
				}

				
			}
			break;
	    	case R.id.arrowback1:			
	    	case R.id.imageHome:
	Intent msg = new Intent(DigiAssistMedicalDataFirstInputActivity.this,DigiAssistActivity.class);
				
				DigiAssistMedicalDataFirstInputActivity.this.startActivity(msg);
	    		//startActivity(new Intent("com.DigiAssist.DigiAssistActivity"));
	    	//	Intent msg = new Intent(DigiAssistMedicalDataFirstInputActivity.this,DigiAssistMedicalDataFirstInputActivity.class);
	    		//DigiAssistMedicalDataFirstInputActivity.this.startActivity(msg);
	    		break;
	    	case R.id.imageHelp:	    		
	    	 	// prepare the alert box
	            AlertDialog.Builder alertbox1 = new AlertDialog.Builder(DigiAssistMedicalDataFirstInputActivity.this);
	            // set the message to display
				alertbox1.setMessage("HELP\n" +
	            		"What do you need help with?\n " +
	            		"How to use the app - a  simple guide.\n" +
	            		"Glossary of Terms -  what do the terms mean?\n"+
	            		"Contact Developers - to report a problem or give feedback.\n"+
	            		"How to use the app:\n"+
	            		"You can speak or type the values of the temperature, blood pressure etc.\n"+
	            		"If an invalid value is entered,  an error message is displayed. Enter a valid value and continue to the next field.\n"+
	            		"Use back and forward buttons to go back and modify any values.\n"+
	            		"To save the recorded values, click on the save button.\n"+
	            		"To discard the values entered so far, click on the cancel button.\n"+
	            		"Glossary of Terms:\n"+
	            		"Pain Level is used to gauge the level of discomfort a patient is feeling\n"+
	            		"Body temperature is a measure of the body's ability to generate and get rid of heat.Its one of most important indicators of health.\n"+
	            		"Blood pressure (BP) is the pressure exerted by circulating blood upon the walls of blood vessels, and is one of the principal vital signs.\n"+
	            		"Respiratory Rate - Human respiration rate is measured when a person is at rest and involves counting the number of breaths for one minute by counting how many times the chest rises. Respiration rates may increase with fever, illness, or other medical conditions. Inaccuracies in respiratory measurement have been reported in the literature. One study compared respiratory rate counted using a 90 second count period, to a full minute, and found significant differences in the rates.[citation needed] Another study found that rapid respiratory rates in babies, counted using a stethoscope.\n"+
	            		"Pulse rate is counted by putting slight pressure on any artery in the body where pulsations can be felt. The most convenient location is the wrist (radial pulse).\n"+
	            		"Blood Glucose Level  - The human body naturally tightly regulates blood glucose levels as a part of metabolic homeostasis.\n"+
	            		"Oxygen saturation or dissolved oxygen (DO) is a relative measure of the amount of oxygen that is dissolved or carried in a given medium.\n"+
	            		"Contact Developers\n"+
	            		"Use one or more of these email addresses to report a problem and/or provide feedback about the application.\n"+
	            		"ssaijpaul@pdx.edu\n"+
	            		"hema@pdx.edu\n"+
	            		"asuryanarayan@pdx.edu" );	 

	           CharSequence okhelp = "OK";
	            // set a positive/yes button and create a listener
	            alertbox1.setPositiveButton(okhelp, new DialogInterface.OnClickListener() {
	 
	                // close the help when the button is clicked
	                
					public void onClick(DialogInterface arg0, int arg1) {
	                	arg0.dismiss();
	                }
	            });
	 
	            // display box
	            alertbox1.show();
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








	public void onUtteranceCompleted(String utteranceId) {
		// TODO Auto-generated method stub
			System.out.println("here i m ");
		//	if (arg0 == "end of wakeup message ID") {
			//if(MoveToNextScreen==true)
			{
				Intent msg = new Intent(DigiAssistMedicalDataFirstInputActivity.this,DigiAssistMedicalDataSecondInputActivity.class);
				
				DigiAssistMedicalDataFirstInputActivity.this.startActivity(msg);
	}
			//}
				
		
		
	}


	
	public void onInit(int status) {
		// TODO Auto-generated method stub
		if (status == TextToSpeech.SUCCESS) {
//			Toast.makeText(MedicalDataInputActivity.this, 
//					"Text-To-Speech engine is initialized", Toast.LENGTH_LONG).show();
			//tts.setOnUtteranceCompletedListener(this);
		}
		else if (status == TextToSpeech.ERROR) {
//			Toast.makeText(MedicalDataInputActivity.this, 
//					"Error occurred while initializing Text-To-Speech engine", Toast.LENGTH_LONG).show();
		}
		tts.setOnUtteranceCompletedListener(this);
		
	}


	public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
			int arg3) {
		// TODO Auto-generated method stub
		
	}


	public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

}