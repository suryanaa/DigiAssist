package com.DigiAssist;

import android.app.Activity;
import android.os.Bundle;

//import java.util.Calendar;
import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;


import android.content.DialogInterface;
import android.content.Intent;

import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.speech.tts.TextToSpeech.OnUtteranceCompletedListener;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class DigiAssistActivity extends Activity {

	private Button userSubmit;
	//private Button userExit;
	private ImageView help;
	private ImageView datepicker;

	//added ...for date picker
    private TextView mDateDisplay;
    private TextView patientName;
    private TextView BirthDate;

    private int mYear;
    private int mMonth;
    private int mDay;
    EditText patientID;
    static final int DATE_DIALOG_ID = 0;



	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.main);

	    this.userSubmit = (Button)this.findViewById(R.id.savebutton);
//	    this.userExit = (Button)this.findViewById(R.id.ExitButton);
	    this.help = (ImageView)this.findViewById(R.id.imageHelp);
	    this.datepicker = (ImageView) findViewById(R.id.imageCalendar);

	    patientID = (EditText) findViewById(R.id.PatientID);
	    patientID.setOnClickListener(clickListener);

	    patientName = (EditText) findViewById(R.id.NameText);
	    patientName.setOnClickListener(clickListener);

	    BirthDate = (EditText) findViewById(R.id.DateText);
	    BirthDate.setOnClickListener(clickListener);

	    this.mDateDisplay = (TextView) findViewById(R.id.DateText);
        System.out.println("datepicker obj"+datepicker);

	    userSubmit.setOnClickListener(clickListener);
	//    userExit.setOnClickListener(clickListener);
	    help.setOnClickListener(clickListener);
	    datepicker.setOnClickListener(clickListener);
	 // get the current date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        // display the current date (this method is below)
     //  updateDisplay();
        if (getIntent().getBooleanExtra("EXIT", false)) {
        	 finish();
        	}

	}



    View.OnClickListener clickListener = new View.OnClickListener(){
        public void  onClick  (View  v){
        	switch(v.getId()){

	    	case R.id.savebutton:
	    		Intent msg = new Intent(DigiAssistActivity.this,DigiAssistMedicalDataFirstInputActivity.class);

	    			DigiAssistActivity.this.startActivity(msg);
	    	break;

	    	case R.id.imageHelp:
	    		Intent msg1 = new Intent(DigiAssistActivity.this,DigiAssistHelp.class);
	    		DigiAssistActivity.this.startActivity(msg1);
	    		break;

	    	case R.id.imageCalendar:
	    		showDialog(DATE_DIALOG_ID);
	    		break;
	    	case R.id.PatientID:
	    		if (patientID.hasFocus()==true)
	            {
	                if (patientID.getText().toString().compareTo("Patient's ID")==0)
	                {
	                	patientID.setText("");
	                }
	            }
	    		break;
	    	case R.id.NameText:
	    		if (patientName.hasFocus()==true)
	            {
	                if (patientName.getText().toString().compareTo("Patient's Name")==0)
	                {
	                	patientName.setText("");
	                }
	            }
	    		break;

	     	case R.id.DateText:
	    		if (BirthDate.hasFocus()==true)
	            {
	                if (BirthDate.getText().toString().compareTo("Birth date")==0)
	                {
	                	BirthDate.setText("");
	                }
	            }
	    		break;



        	}


        }
    };

    // updates the date in the TextView
    private void updateDisplay() {
        mDateDisplay.setText(
            new StringBuilder()
                    // Month is 0 based so add 1
                    .append(mMonth + 1).append("-")
                    .append(mDay).append("-")
                    .append(mYear).append(" "));
    }

    // the callback received when the user "sets" the date in the dialog
    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {


    	public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                    mYear = year;
                    mMonth = monthOfYear;
                    mDay = dayOfMonth;
                    updateDisplay();
                }



            };


            protected Dialog onCreateDialog(int id) {
                switch (id) {
                case DATE_DIALOG_ID:
                    return new DatePickerDialog(this,
                                mDateSetListener,
                                mYear, mMonth, mDay);
                }
                return null;
            }

}