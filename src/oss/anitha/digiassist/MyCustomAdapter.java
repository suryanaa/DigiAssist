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

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
 
import java.util.ArrayList;
 
public class MyCustomAdapter extends BaseExpandableListAdapter {
 
 
    private LayoutInflater inflater;
    private ArrayList<Parent> mParent;
 
    public MyCustomAdapter(Context context, ArrayList<Parent> parent){
        mParent = parent;
        inflater = LayoutInflater.from(context);
    }
 
 
    
    //counts the number of group/parent items so the list knows how many times calls getGroupView() method
    public int getGroupCount() {
        return mParent.size();
    }
 
    
    //counts the number of children items so the list knows how many times calls getChildView() method
    public int getChildrenCount(int i) {
        return mParent.get(i).getArrayChildren().size();
    }
 
    
    //gets the title of each parent/group
    public Object getGroup(int i) {
        return mParent.get(i).getTitle();
    }
 
    
    //gets the name of each item
    public Object getChild(int i, int i1) {
        return mParent.get(i).getArrayChildren().get(i1);
    }
 
    public long getGroupId(int i) {
        return i;
    }
 
    public long getChildId(int i, int i1) {
        return i1;
    }
 
    public boolean hasStableIds() {
        return true;
    }
 
    
    //in this method you must set the text to see the parent/group on the list
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
 
        if (view == null) {
            view = inflater.inflate(R.layout.list_item_parent, viewGroup,false);
        }
 
        TextView textView = (TextView) view.findViewById(R.id.list_item_text_view);
        //"i" is the position of the parent/group in the list
        textView.setText(getGroup(i).toString());
 
        //return the entire view
        return view;
    }
 
    
    //in this method you must set the text to see the children on the list
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = inflater.inflate(R.layout.list_item_child, viewGroup,false);
        }
 
        TextView textView = (TextView) view.findViewById(R.id.list_item_text_child);
        //"i" is the position of the parent/group in the list and 
        //"i1" is the position of the child
        textView.setText(mParent.get(i).getArrayChildren().get(i1));
        view.setBackgroundColor(Color.CYAN);

        //return the entire view
        return view;
    }
 
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}