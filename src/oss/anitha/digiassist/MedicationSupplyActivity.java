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
import android.content.SharedPreferences;
import android.view.Menu;
import android.widget.ExpandableListView;
 
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class MedicationSupplyActivity extends Activity {
	private ExpandableListView mExpandableList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication_supply);
        
        mExpandableList = (ExpandableListView)findViewById(R.id.expandable_list);
        
        ArrayList<Parent> arrayParents = new ArrayList<Parent>();
        ArrayList<String> medicines = getCurrentMedicines();
        Random rand = new Random();

        for(int i = 0; i < medicines.size(); i++) {
            Parent p = new Parent();
            p.setTitle(getString(R.string.view_label_medicine) + " " + medicines.get(i));
            ArrayList<String> children = new ArrayList<String>();
            int pillsRem = rand.nextInt(100);
            children.add(getString(R.string.pills_remaining) + " " + pillsRem);
            if(pillsRem <= 10) {
                children.add(getString(R.string.refill_required) + " " + getString(R.string.yes));
            }
            p.setArrayChildren(children);
            arrayParents.add(p);
        }
        
        //sets the adapter that provides data to the list.
        mExpandableList.setAdapter(new MyCustomAdapter(MedicationSupplyActivity.this,arrayParents));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_medication_supply, menu);
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
