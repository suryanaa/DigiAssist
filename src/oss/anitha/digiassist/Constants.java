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

public class Constants {
	  static final String DATA_STORE = "MEDICATION_DATA_STORE";
	  static final String KEY_MEDICATION_NAME = "MedicationName";
	  static final String KEY_MEDICATION_DOSAGE = "MedicationDosage";
	  static final String KEY_START_DATE = "MedicationStartDate";
	  static final String KEY_END_DATE = "MedicationEndDate";
	  static final String KEY_START_TIME = "MedicationStartTime";
	  static final String KEY_END_TIME = "MedicationEndTime";
	  
  	  static final int COMMAND_CHECK_TTS_AVAILABILITY = 0;
	  static final int COMMAND_ADD_REMINDER = 1;
	  static final int COMMAND_LIST_MEDICATION = 2;
	  static final int COMMAND_VIEW_CALENDAR = 3;
	  static final int COMMAND_SEND_SYMPTOMS = 4;
	  static final int COMMAND_TRIGGER_ALARM = 5;
}
