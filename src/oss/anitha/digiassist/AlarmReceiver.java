package oss.anitha.digiassist;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

	private static final String TAG = "AlarmReceiver";
	
	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i(TAG, "onReceive");
		Bundle data = intent.getExtras();
		String name = data.getString(Constants.KEY_MEDICATION_NAME);
		String dosage = data.getString(Constants.KEY_MEDICATION_DOSAGE);
		Toast.makeText(context, "Time for " + name + ". Dosage is " + dosage, Toast.LENGTH_SHORT).show();
	}
}
