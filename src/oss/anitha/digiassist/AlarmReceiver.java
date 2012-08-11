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
