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

import java.util.ArrayList;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;

public class ListMedicationActivity extends Activity {

	private static final String URL_PREFIX = "http://www.drugs.com/";
	private static final String URL_SUFFIX = ".html";
	String strTratamiento;

	public void onCreate(Bundle icicle) 
	{
		super.onCreate(icicle);
        setContentView(R.layout.activity_list_medication);
        ArrayList<String> medicines = getCurrentMedicines();
        LinearLayout layout = (LinearLayout) findViewById(R.id.layout_medicine_info);
        for(int i = 0; i < medicines.size(); i++) {
        	final String medicine = medicines.get(i);
        	Button b = new Button(this);
        	b.setText(medicine);
        	b.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        	b.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					goToUrl(URL_PREFIX + medicine + URL_SUFFIX);
				}
        	});
        	layout.addView(b);
        }
	}
	
	public void goToPage(View view) {
		goToUrl(URL_PREFIX + URL_SUFFIX);
	}
/*
    public void goToDa (View view) {
        goToUrl ( "http://www.drugs.com/mtm/dalteparin.html");
    }

    public void goToEn (View view) {
        goToUrl ( "http://www.drugs.com/cons/enoxaparin-subcutaneous-injection.html");
    }
    
    public void goToHe (View view) {
        goToUrl ( "http://www.drugs.com/cons/heparin-intravenous-subcutaneous.html");
    }
    
    public void goToTi (View view) {
        goToUrl ( "http://www.drugs.com/mtm/tinzaparin.html");
    }
    
    public void goToWa (View view) {
        goToUrl ( "http://www.drugs.com/cdi/warfarin.html");
    }
*/
    private void goToUrl (String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }
	
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_lsit_medication, menu);
        return true;
    }
    
    private ArrayList<String> getCurrentMedicines() {
        SharedPreferences sp = getSharedPreferences(Constants.DATA_STORE, 0);
        ArrayList<String> medicines = new ArrayList<String>();
        Map<String, ?> reminders = sp.getAll();
        for(Map.Entry<String, ?> entry : reminders.entrySet()) {
        	String id = entry.getKey();
        	String record = (String) entry.getValue();
        	MedicationReminder reminder = new MedicationReminder(id, record);
        	medicines.add(reminder.name);
        }
        return medicines;
    }
}
