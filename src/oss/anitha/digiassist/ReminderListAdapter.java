package oss.anitha.digiassist;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.TimeZone;

import android.content.Context;
import android.content.SharedPreferences;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class ReminderListAdapter extends ArrayAdapter<MedicationReminder> {

	private final Context context;
	private int layoutId;
	ArrayList<MedicationReminder> data;
	
	public ReminderListAdapter(Context context, int layoutId) {
		super(context, layoutId);
		this.context = context;
		this.layoutId = layoutId;
        this.data = getReminders();
        for(int i = 0; i < data.size(); i++) {
        	super.insert(data.get(i), i);
        }
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		MedicationReminderHolder holder = null;
		
		if(row == null) {
			LayoutInflater inflater = ((Activity)context).getLayoutInflater();
			row = inflater.inflate(layoutId, parent, false);
			
			holder = new MedicationReminderHolder();
			holder.name = (TextView) row.findViewById(R.id.tvName);
			holder.dosage = (TextView) row.findViewById(R.id.tvDosage);
			holder.startTime = (TextView) row.findViewById(R.id.tvStartTime);
			holder.deleteBtn = (Button) row.findViewById(R.id.btnDeleteReminder);
			row.setTag(holder);
		}
		else {
			holder = (MedicationReminderHolder) row.getTag();
		}
		
		MedicationReminder reminder = null;
		synchronized(data) {
			reminder = data.get(position);
		}
		holder.name.setText(context.getString(R.string.view_label_medicine) + 
				" " + reminder.name);
		holder.dosage.setText(context.getString(R.string.view_label_dosage) + 
				" " + reminder.dosage);
		holder.startTime.setText(context.getString(R.string.view_label_start) + 
				" " + reminder.getStartTime());
	    final int pos = position;
		holder.deleteBtn.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					deleteReminder(pos);
				}
			});
		return row;
	}
	
    private ArrayList<MedicationReminder> getReminders() {
        SharedPreferences sp = context.getSharedPreferences(Constants.DATA_STORE, 0);
        ArrayList<MedicationReminder> array = new ArrayList<MedicationReminder>();
        Map<String, ?> reminders = sp.getAll();
        for(Map.Entry<String, ?> entry : reminders.entrySet()) {
        	String id = entry.getKey();
        	String record = (String) entry.getValue();
        	MedicationReminder reminder = new MedicationReminder(id, record);
        	Calendar c = Calendar.getInstance();
        	if(c.get(Calendar.YEAR) == reminder.startDateTime.get(Calendar.YEAR) &&
        			c.get(Calendar.MONTH) == reminder.startDateTime.get(Calendar.MONTH)	&&
        			c.get(Calendar.DAY_OF_MONTH) == reminder.startDateTime.get(Calendar.DAY_OF_MONTH)) {
            	array.add(reminder);
        	}
        }
        Collections.sort(array, new Sorter());
        return array;
    }
    
    private void deleteReminder(int position) {
    	if(position >= data.size()) {
    		throw new IllegalArgumentException("Invalid position " + position);
    	}
    	// Get the record and delete it from data store
    	MedicationReminder record = null;
    	synchronized(data) {
        	record = data.get(position);
            SharedPreferences sp = context.getSharedPreferences(Constants.DATA_STORE, 0);
            SharedPreferences.Editor editor = sp.edit();
            editor.remove(record.id);
            editor.commit();
            // Then delete it from the array maintained by this class
            data.remove(position);
            // Delete it from the super class's store
            super.remove(record);
            // And notify the list view about this change
            notifyDataSetChanged();
    	}
    }
	
	static class MedicationReminderHolder {
		TextView name;
		TextView dosage;
		TextView startTime;
		Button deleteBtn;
	}
	
	class Sorter implements Comparator<MedicationReminder> {

		public int compare(MedicationReminder m0, MedicationReminder m1) {
			return (m0.startDateTime.before(m1.startDateTime) ? -1 : 
				(m0.startDateTime.after(m1.startDateTime) ? 1 : 0));
		}
	}
}
