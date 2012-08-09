
package android.digiassist.xml;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Date;
import java.util.Vector;

import org.kxml2.io.KXmlParser;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.digiassist.bean.PatientInfo;
import android.util.Log;
public class ManagerConfigFile {

    private XmlPullParser parser;
   
    private static final String LOG_TAG = "ManagerConfigFile";
    
    public ManagerConfigFile() {
	parser = new KXmlParser();

    }
    
    
    public Vector getDataFromString(String rss) {

	Vector data = new Vector();
	try {
	   
	    StringReader reader = new StringReader(rss);
	    parser.setInput(reader);
	    
	    fillVector(data);

	} catch (XmlPullParserException e) {
	    e.printStackTrace();
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	} 

	return data;
    }


   private void fillVector(Vector data) throws XmlPullParserException, IOException {
	
	while (parser.next() != XmlPullParser.END_DOCUMENT) {

        	if (parser.getName() != null && parser.getName().equals("item") && parser.getEventType()==XmlPullParser.START_TAG) {
        		
        		Log.i(LOG_TAG," PatientInfo "+parser.getName());
        	    PatientInfo item = getPatientItem();
        	    data.add(item);
        	}
    	}
    }

    private PatientInfo getPatientItem() {

    	PatientInfo le = new PatientInfo(); 	

		try {
	
		    while (!le.completed()
			    && parser.getEventType() != XmlPullParser.END_DOCUMENT) {
	
			parser.nextTag();		
			if (parser.getName() != null
				&& parser.getEventType() == XmlPullParser.START_TAG) {
	
						
						
					    if (parser.getName().equals("vCard:Given")) {
							parser.next();
							le.setNumber(parser.getText());
					    } else if (parser.getName().equals("vCard:Family")) {
								parser.next();
								le.setLastName(parser.getText());	
								} 
					}	
					parser.next();					
		    }

	} catch (XmlPullParserException e) {
	    Log.e(LOG_TAG,e.getMessage());
	} catch (IOException e) {
	    Log.e(LOG_TAG,e.getMessage());
	}

	return le;
    }


}
