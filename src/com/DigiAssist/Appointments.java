
package android.digiassist.beans;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;

//import android.digiassist.fetchData.GetPatientInfo;
import android.digiassist.generic.CalendarApptsHandler;
import android.util.Log;

public class Appointments {

	protected static final String LOG_TAG = "Appointments";

	public String number;
	public String patient_id;
	public String dateTime;
	public String note;
	public HashMap<String, String> appts_calendar = new HashMap<String, String>();

	SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	SimpleDateFormat dFormat2H = new SimpleDateFormat("HH");
	SimpleDateFormat dFormat2M = new SimpleDateFormat("mm");
	SimpleDateFormat dFormat3 = new SimpleDateFormat("dd");
	SimpleDateFormat dFormat4 = new SimpleDateFormat("yyyy");
	SimpleDateFormat dFormat5 = new SimpleDateFormat("MM");

	private static final int SUNDAY = 1;
	private static final int MONDAY = 2;
	private static final int TUESDAY = 3;
	private static final int WEDNESDAY = 4;
	private static final int THURSDAY = 5;
	private static final int FRIDAY = 6;
	private static final int SATURDAY = 7;

	public Appointments() {}

	public Appointments(InputStream inputFile) 
	{
		try {
				SAXParserFactory spf = SAXParserFactory.newInstance();
				SAXParser sp = spf.newSAXParser();
				XMLReader xr = sp.getXMLReader();
				CalendarApptsHandler myCalendarPatientHandler = new CalendarApptsHandler();
				xr.setContentHandler(myCalendarPatientHandler);
				xr.parse(new InputSource(inputFile));
				appts_calendar = myCalendarPatientHandler.getHashMap();
			} 
		catch (ParserConfigurationException eParserConfiguration) {
		Log.e(LOG_TAG, eParserConfiguration.toString(),	eParserConfiguration);
		} catch (SAXParseException eSAXParse) {
			Log.e(LOG_TAG, eSAXParse.toString(), eSAXParse);
		} catch (SAXException eSAX) {
			Log.e(LOG_TAG, eSAX.toString(), eSAX);
		} catch (IOException eIO) {
			Log.e(LOG_TAG, eIO.toString(), eIO);
		}
	}

	public Vector checkAppointments(int mYear, int mMonth, int mDay) {
		Vector appts = new Vector();
		Vector order_appts = new Vector();
		try {
			String pad1 = (mMonth + 1) < 10 ? "0" : "";
			String pad2 = mDay < 10 ? "0" : "";
			String min = mYear + "-" + pad1 + (mMonth + 1) + "-" + pad2 + mDay
					+ "T00:00:00";
			String max = mYear + "-" + pad1 + (mMonth + 1) + "-" + pad2 + mDay
					+ "T23:59:59";
			Date time1 = new Date();
			Date time2 = new Date();
			time1 = dFormat.parse(min);
			time2 = dFormat.parse(max);
			Iterator it = appts_calendar.entrySet().iterator();
			Date time1Calendar = new Date();
			int i = 0;
			boolean found = false;
			Map.Entry e;
			String key;
			String[] itemsAux = new String[appts_calendar.size()];
			int j = 0;
			while (it.hasNext() && !found) {
				e = (Map.Entry) it.next();
				key = (String) e.getKey();
				time1Calendar = dFormat.parse(key);
				String d = dFormat3.format(time1Calendar);
				String m = dFormat5.format(time1Calendar);
				String a = dFormat4.format(time1Calendar);
				GregorianCalendar date = new GregorianCalendar();
				date.set(Integer.parseInt(a), Integer.parseInt(m) - 1, Integer
						.parseInt(d));

				if (time1.equals(time1Calendar)
						|| (time1Calendar.after(time1) && time1Calendar
								.before(time2))) {
					String time = dFormat2H.format(time1Calendar);
					String minutes = dFormat2M.format(time1Calendar);
					String hm = time + ":" + minutes;
					appts.add(hm + "|" + (String) e.getValue());
					itemsAux[j] = time + "" + minutes;
					j++;

				}

			}
			String[] aux = new String[appts.size()];
			for (int g = 0; g < itemsAux.length; g++) {
				if (itemsAux[g] != null) {
					aux[g] = itemsAux[g];
				}
			}

			aux = orderApptsDayTime(aux);

			for (int h = 0; h < aux.length; h++) {
				String hhmm = aux[h];
				for (int z = 0; z < appts.size(); z++) {
					String appt = (String) appts.get(z);
					StringTokenizer st = new StringTokenizer(appt, "|");
					String timer = st.nextToken();
					StringTokenizer st_2 = new StringTokenizer(timer, ":");
					String time = st_2.nextToken();
					String minutes = st_2.nextToken();
					if (hhmm.equals(time + "" + minutes)) {
						order_appts.add(appt);
					}

				}

			}

		} catch (Exception e) {
			Log.e(LOG_TAG, e.toString(), e);
		}

		return order_appts;
	}
	
	public Vector checkAppointmentsRank(int mYear, int mMonth, int mDay,
			int offset) {
		Vector appts_week = new Vector();

		for (int counter = 0; counter < offset; counter++) {
			appts_week.add("");
		}
		Vector appts_week_final = new Vector();

		String mDayS;

		Vector week = Week();
		try {
			
			GregorianCalendar gc = new GregorianCalendar();
			gc.set(GregorianCalendar.YEAR, mYear);
			gc.set(GregorianCalendar.MONTH, mMonth);
			gc.set(GregorianCalendar.DAY_OF_MONTH, mDay);

			GregorianCalendar gcFin = new GregorianCalendar();
			gcFin.set(GregorianCalendar.YEAR, mYear);
			gcFin.set(GregorianCalendar.MONTH, mMonth);
			gcFin.set(GregorianCalendar.DAY_OF_MONTH, mDay + offset);

			String pad1 = (gc.get(GregorianCalendar.MONTH) + 1) < 10 ? "0" : "";
			String pad2 = gc.get(GregorianCalendar.DAY_OF_MONTH) < 10 ? "0"
					: "";

			String pad3 = (gcFin.get(GregorianCalendar.MONTH) + 1) < 10 ? "0"
					: "";
			String pad4 = gcFin.get(GregorianCalendar.DAY_OF_MONTH) < 10 ? "0"
					: "";

			String min = gc.get(GregorianCalendar.YEAR) + "-" + pad1
					+ (gc.get(GregorianCalendar.MONTH) + 1) + "-" + pad2
					+ gc.get(GregorianCalendar.DAY_OF_MONTH) + "T00:00:00";
			String max = gc.get(GregorianCalendar.YEAR) + "-" + pad3
					+ (gcFin.get(GregorianCalendar.MONTH) + 1) + "-" + pad4
					+ gcFin.get(GregorianCalendar.DAY_OF_MONTH) + "T23:59:59";

			Date time1 = new Date();
			Date time2 = new Date();
			time1 = dFormat.parse(min);
			time2 = dFormat.parse(max);

			Iterator it = appts_calendar.entrySet().iterator();
			Date time1Calendar = new Date();

			int i = 0;
			boolean found = false;
			Map.Entry e;
			String key;

			while (it.hasNext() && !found) {
				e = (Map.Entry) it.next();
				key = (String) e.getKey();
				time1Calendar = dFormat.parse(key);
				String d = dFormat3.format(time1Calendar);
				String m = dFormat5.format(time1Calendar);
				String a = dFormat4.format(time1Calendar);
				String h = dFormat2H.format(time1Calendar);
				String mm = dFormat2M.format(time1Calendar);
				GregorianCalendar date = new GregorianCalendar();
				date.set(Integer.parseInt(a), Integer.parseInt(m) - 1, Integer.parseInt(d));
				mDayS = day(date.get(date.DAY_OF_WEEK));

				if (time1.equals(time1Calendar)
						|| (time1Calendar.after(time1) && time1Calendar
								.before(time2))) {

					String dayWeek = mDayS + " " + d;
					String timemin = h + ":" + mm;
					String aux = "";

					if (offset == 30) {
						appts_week.set(Integer.parseInt(d), dayWeek
								+ " de " + getMonth(Integer.parseInt(m)) + "|"
								+ timemin + "&" + e.getValue());
					} else {
						for (int j = 0; j < week.size(); j++) {
							String hhh = (String) week.get(j);
							if (week.get(j).equals(mDayS)) {
								// aux.equals("")
								aux = (String) appts_week.get(j);
								if (aux.equals("")) {
									appts_week.set(j, dayWeek + "|"
											+ timemin + "&" + e.getValue());
								} else {

									aux += "-" + timemin + "&" + e.getValue();
									appts_week.set(j, aux);
								}
							}
						}
					}
				}
			}
			for (int j = 0; j < appts_week.size(); j++) {
				String str = (String) appts_week.get(j);
				if (!str.equals("")) {
					appts_week_final.add(str);
				}
			}
		} catch (Exception e) {
			Log.e(LOG_TAG, e.toString(), e);
		}
		return appts_week_final;
	}
	
	public String[] orderApptsDayTime(String[] g) {

		int i, j, index, aux;

		for (i = 0; i < g.length - 1; i++) {
			for (j = i + 1, index = i; j < g.length; j++) {
				if (Integer.parseInt(g[j]) < Integer.parseInt(g[index])) {
					index = j;
				}
			}
			aux = Integer.parseInt(g[i]);
			g[i] = g[index];
			if (aux < 1000) {
				g[index] = "0" + aux + "";
			} else {
				g[index] = aux + "";
			}
		}

		return g;

	}

	public Vector Week() {
		Vector week = new Vector();

		week.add("Monday");
		week.add("Tuesday");
		week.add("Wednesday");
		week.add("Thursday");
		week.add("Friday");
		week.add("Saturday");
		week.add("Sunday");

		return week;
	}

	public String getMonth(int i) {
		String month = "";
		switch (i) {
		case 1:
			month = "January";
			break;
		case 2:
			month = "February";
			break;
		case 3:
			month = "March";
			break;
		case 4:
			month = "April";
			break;
		case 5:
			month = "May";
			break;
		case 6:
			month = "June";
			break;
		case 7:
			month = "July";
			break;
		case 8:
			month = "August";
			break;
		case 9:
			month = "September";
			break;
		case 10:
			month = "October";
			break;
		case 11:
			month = "November";
			break;
		case 12:
			month = "December";
			break;
		default:

			break;
		}

		return month;
	}

	private String day(int day) {
		String day_week = "";
		switch (day) {
		case SUNDAY:
			day_week = "Sunday";
			break;
		case MONDAY:
			day_week = "Monday";
			break;
		case TUESDAY:
			day_week = "Tuesday";
			break;
		case WEDNESDAY:
			day_week = "Wednesday";
			break;
		case THURSDAY:
			day_week = "Thursday";
			break;
		case FRIDAY:
			day_week = "Friday";
			break;
		case SATURDAY:
			day_week = "Saturday";

		}

		return day_week;
	}

	public HashMap<String, String> getAppts_calendar() {
		return appts_calendar;
	}

	public void setAppts_calendar(HashMap<String, String> appts_calendar) {
		this.appts_calendar = appts_calendar;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getPatient_id() {
		return patient_id;
	}

	public void setPatient_id(String patient_id) {
		this.patient_id = patient_id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
