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

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.support.v4.app.NavUtils;

public class DigiAssistSettingsActivity extends Activity {

	private ExpandableListView mExpandableList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digi_assist_settings);
        
        mExpandableList = (ExpandableListView)findViewById(R.id.expandable_list);
        ArrayList<Parent> arrayParents = new ArrayList<Parent>();

        Parent p_PersonalInfo = new Parent();
        ArrayList<String> arrayChildren_PersonalInfo = new ArrayList<String>();
        p_PersonalInfo.setTitle("My Details ");
        arrayChildren_PersonalInfo.add("Name : " + "Leah Andreakis");
        arrayChildren_PersonalInfo.add("DOB : " + "2/22/1967");
        arrayChildren_PersonalInfo.add("Phone : " + "503-555-3333");
        arrayChildren_PersonalInfo.add("Address : " + "2345 Hassle Ave, Stresston, OR");
        arrayChildren_PersonalInfo.add("Email : " + "leah.andreakis@gmail.com");

        p_PersonalInfo.setArrayChildren(arrayChildren_PersonalInfo);
        arrayParents.add(p_PersonalInfo);
        
        
        Parent p_DocInfo = new Parent();
        ArrayList<String> arrayChildren_DocInfo = new ArrayList<String>();
        p_DocInfo.setTitle("My Doctor's Details ");
        arrayChildren_DocInfo.add("Name : " + "Nicholas Smith");
        arrayChildren_DocInfo.add("Phone : " + "503-505-3003");
        arrayChildren_DocInfo.add("Address : " + "9875 Tranquil Ln, Relaxton, OR");
        arrayChildren_DocInfo.add("Email : " + "smith@providence.com");
        
        p_DocInfo.setArrayChildren(arrayChildren_DocInfo);
        arrayParents.add(p_DocInfo);
        
        
        Parent p_PharmacyInfo = new Parent();
        ArrayList<String> arrayChildren_PharmInfo = new ArrayList<String>();
        p_PharmacyInfo.setTitle("My Pharamacy Details ");
        arrayChildren_PharmInfo.add("Name : " + "Rite Aid");
        arrayChildren_PharmInfo.add("Phone : " + "503-911-9110");
        arrayChildren_PharmInfo.add("Default Refill Count : " + "100");
        arrayChildren_PharmInfo.add("Address : " + "2323 Evergreen Parkway, Midville, OR");
        p_PharmacyInfo.setArrayChildren(arrayChildren_PharmInfo);
        arrayParents.add(p_PharmacyInfo);
        
        Parent p_VoiceAssist = new Parent();
        ArrayList<String> arrayChildren_VoiceAssist = new ArrayList<String>();
        p_VoiceAssist.setTitle("Voice Assist");
        arrayChildren_VoiceAssist.add("Enabled");
        p_VoiceAssist.setArrayChildren(arrayChildren_VoiceAssist);
        arrayParents.add(p_VoiceAssist);

        Parent p_CloudStorage = new Parent();
        ArrayList<String> arrayChildren_CloudStorage = new ArrayList<String>();
        p_CloudStorage.setTitle("Data Storage");
        arrayChildren_CloudStorage.add("Default Storage : " + "Google Docs");
        arrayChildren_CloudStorage.add("Authentication required : " + "Yes");
        arrayChildren_CloudStorage.add("Login ID : " + "leah.andreakis");
        arrayChildren_CloudStorage.add("password : " + "************");

        p_CloudStorage.setArrayChildren(arrayChildren_CloudStorage);
        arrayParents.add(p_CloudStorage);
        
        mExpandableList.setAdapter(new MyCustomAdapter(DigiAssistSettingsActivity.this,arrayParents));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_digi_assist_settings, menu);
        return true;
    }

    
}
