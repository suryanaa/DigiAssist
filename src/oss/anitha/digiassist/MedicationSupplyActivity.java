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
import android.view.Menu;
import android.widget.ExpandableListView;
 
import java.util.ArrayList;
 

public class MedicationSupplyActivity extends Activity {
	private ExpandableListView mExpandableList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication_supply);
        
        mExpandableList = (ExpandableListView)findViewById(R.id.expandable_list);
        
        ArrayList<Parent> arrayParents = new ArrayList<Parent>();
        ArrayList<String> arrayChildren = new ArrayList<String>();
        ArrayList<String> arrayChildrenDal = new ArrayList<String>();
        ArrayList<String> arrayChildrenHep = new ArrayList<String>();
        ArrayList<String> arrayChildrenEno = new ArrayList<String>();
        ArrayList<String> arrayChildrenTin = new ArrayList<String>();
        ArrayList<String> arrayChildrenWar = new ArrayList<String>();

        Parent parentDal = new Parent();
        parentDal.setTitle("Medication : " + "Dalteparin");
        arrayChildrenDal.add("Pills Remaining : " + "30");
        parentDal.setArrayChildren(arrayChildrenDal);
        arrayParents.add(parentDal);
         
        Parent parentExo = new Parent();
        parentExo.setTitle("Medication : " + "Enoxaparin");
        arrayChildrenEno.add("Pills Remaining : " + "10");
        parentExo.setArrayChildren(arrayChildrenEno);
        arrayParents.add(parentExo);
        
        
        Parent parentHep = new Parent();
        parentHep.setTitle("Medication : " + "Heparin");
        arrayChildrenHep.add("Pills Remaining : " + "7");
        arrayChildrenHep.add("Refill Required : " + "Yes");
        parentHep.setArrayChildren(arrayChildrenHep);
        arrayParents.add(parentHep);
        

        Parent parentTin = new Parent();
        parentTin.setTitle("Medication : " + "Tinzaparin");
        arrayChildrenTin.add("Pills Remaining : " + "15");
        parentTin.setArrayChildren(arrayChildrenTin);
        arrayParents.add(parentTin);
        
        Parent parentWar = new Parent();
        parentWar.setTitle("Medication : " + "Warfarin");
        arrayChildrenWar.add("Pills Remaining : " + "20");
        parentWar.setArrayChildren(arrayChildrenWar);
        arrayParents.add(parentWar);
        
        //here we set the parents and the children
        /*for (int i = 0; i < 10; i++){
            //for each "i" create a new Parent object to set the title and the children
            Parent parent1 = new Parent();
            parent1.setTitle("Medication Name " + "Dalteparin");
            arrayChildren.add("Details " + i);
            parent1.setArrayChildren(arrayChildren);
 
            //in this array we add the Parent object. We will use the arrayParents at the setAdapter
            arrayParents.add(parent1);
        }*/
 
        //sets the adapter that provides data to the list.
        mExpandableList.setAdapter(new MyCustomAdapter(MedicationSupplyActivity.this,arrayParents));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_medication_supply, menu);
        return true;
    }

    
}
