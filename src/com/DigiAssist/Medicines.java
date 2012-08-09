package android.digiassist.beans;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import android.digiassist.generic.CalendarMedHandler;
import android.util.Log;

public class Medicines {
	
	protected static final String LOG_TAG = "Medicines";
	public HashMap<String, String> medication_calendar=new HashMap<String, String>();  
	
	public String patient_number; 
	public String patient_id;
	public String treatment_id;
	public String medical_id;
	public String startDate;
	public String endDate;
	public String medicine;
	public String units;
	public String via_admin;
	public String duration;
	public String dosage;
	public String freq;
	public String indicators; 
	public String contra_indicators;
	public String image;
	public String dose_image;
	
	public Medicines(){}
	
	public Medicines(InputStream inputFile){
		 try
			{
					
				SAXParserFactory spf = SAXParserFactory.newInstance(); 
				SAXParser sp = spf.newSAXParser(); 
				XMLReader xr = sp.getXMLReader(); 
				CalendarMedHandler myCalendarPatientHandler = new CalendarMedHandler();
				xr.setContentHandler(myCalendarPatientHandler); 
				xr.parse(new InputSource(inputFile));
				medication_calendar = myCalendarPatientHandler.getHashMap();
			}
			catch(ParserConfigurationException eParserConfiguration)
			{
				Log.e(LOG_TAG,eParserConfiguration.toString(),eParserConfiguration);
			}
			catch(SAXParseException eSAXParse)
			{
				Log.e(LOG_TAG,eSAXParse.toString(),eSAXParse);
			}
			catch(SAXException eSAX)
			{
				Log.e(LOG_TAG,eSAX.toString(),eSAX);
			}
			catch(IOException eIO)
			{
				Log.e(LOG_TAG,eIO.toString(),eIO);
			}
	}
	 
    public Vector<String> medicacionHoy(int mYear, int mMonth, int mDay){
    	Vector<String> resultado=new Vector<String>();
    	SimpleDateFormat dFormat= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    	SimpleDateFormat dFormat2H=new SimpleDateFormat("HH");
    	SimpleDateFormat dFormat2M=new SimpleDateFormat("mm");
    	
    	 try
 	    {    		 
 	    	String pad1 = (mMonth + 1) < 10 ? "0" : "";
 	    	String pad2 = mDay < 10 ? "0" : "";
 	    	       
 	    	String min=mYear + "-" + pad1 + (mMonth + 1) + "-" + pad2 + mDay + "T00:00:00";//params.getParamValue("start-min");
 	    	String max=mYear + "-" + pad1 + (mMonth + 1) + "-" + pad2 + mDay + "T23:59:59";//params.getParamValue("start-max");
 	    	Date time1=new Date();
 	    	Date time2=new Date();
 	    	time1=dFormat.parse(min);
 	    	time2 = dFormat.parse(max);
 	    			
 	    	 Iterator it = medication_calendar.entrySet().iterator();
 	    	 Date time1Calendar=new Date();
 	    	 Date time2Calendar=new Date();
 	    	 int i=0;
 	    	 boolean encontrado=false;
 	    	 Map.Entry e;
 	    	 String valor;
 	    	 String [] itemsAux=new String[medication_calendar.size()];
 	    	 
 	    	 while (it.hasNext()&&!encontrado) { 
 	    		 e = (Map.Entry)it.next();
 	    	     treatment_id=(String)e.getKey();
 	    	     valor=(String)e.getValue();
 	    	     StringTokenizer tokens= new StringTokenizer(valor,"|");
 	    	    while(tokens.hasMoreTokens()){
 	               medical_id=tokens.nextToken();
 	               startDate=tokens.nextToken();
 	               endDate=tokens.nextToken();
 	               medicine=tokens.nextToken();
 	               units=tokens.nextToken();
 	               via_admin=tokens.nextToken();
 	               duration=tokens.nextToken();
 	               dosage=tokens.nextToken();
 	               freq=tokens.nextToken();
 	               indicators=tokens.nextToken();
 	               contra_indicators=tokens.nextToken();
 	               image=tokens.nextToken();
 	               dose_image=tokens.nextToken();
 	               
 	    	        time1Calendar=dFormat.parse(startDate);
 	    	        time2Calendar=dFormat.parse(endDate);
 	    	        String hh= dFormat2H.format(time1Calendar);
 	    	        String mm=dFormat2M.format(time1Calendar);
 	    	       
 	    	        
 	    	        if(((time1.compareTo(time1Calendar))<0) || ((time1.compareTo(time2Calendar))<0) || ((time1.compareTo(time1Calendar))==0) || ((time1.compareTo(time2Calendar))==0)){

 	    	        	if(freq=="24"){
 	    	        		resultado.add(hh+":"+mm+"|"+medicine+"|"+dosage);
 	    	        	}else{
 	    	        		resultado.add(hh+":"+mm+"|"+medicine+"|"+dosage);
 	    	        		int nuevahh=Integer.parseInt(hh)+Integer.parseInt(freq);
 	    	        		while(nuevahh<24){
 	    	        			
 	    	        			resultado.add(nuevahh+":"+mm+"|"+medicine+"|"+dosage);
 	    	        			nuevahh=nuevahh+Integer.parseInt(freq);
 	    	        		}
 	    	        	}	
 	    	        		
 	    	        	}

 	    	        }
 	    	 }

 	    	       }catch(Exception e){
 	    	        	Log.e(LOG_TAG,e.toString(),e);
 	    	        }
      			
    				
    	
    	return resultado; 
 	    }
	
    /**
     * Función que me devuelve los medicamentos que toma un paciente
     * @return Vector con los nombres de los medicines que toma el paciente
     */
    public Vector medicinesPatients(){
		Vector resultado=new Vector();
		Iterator it=medication_calendar.entrySet().iterator();
		Map.Entry e;
		
		while(it.hasNext()){
			 e = (Map.Entry)it.next();
			 treatment_id=(String)e.getKey();
			 String valores=(String)e.getValue();
			 StringTokenizer st=new StringTokenizer(valores,"|");
			 while (st.hasMoreTokens()){
				   medical_id=st.nextToken();
	               startDate=st.nextToken();
	               endDate=st.nextToken();
	               medicine=st.nextToken();
	               units=st.nextToken();
	               via_admin=st.nextToken();
	               duration=st.nextToken();
	               dosage=st.nextToken();
	               freq=st.nextToken();
	               indicators=st.nextToken();
	               contra_indicators=st.nextToken();
	               image=st.nextToken();
	               dose_image=st.nextToken();
			 }
			 if(!resultado.contains(medicine)){
				 resultado.add(medicine);
				 
			 }
		}
		
		
	return resultado;
	}
	
	/**
	 * Funcion que devuelve informacion sobre los medicamentos que toma un paciente
	 * @return Vector con los siguinetes datos: medicine|startDate|duration|dosage|freq|indicators|contra_indicators del paciente
	 */
    public Vector listaMedicacion(){
		Vector resultado=new Vector();
		Iterator it=medication_calendar.entrySet().iterator();
		Map.Entry e;
		
		while(it.hasNext()){
			 e = (Map.Entry)it.next();
			 treatment_id=(String)e.getKey();
			 String valores=(String)e.getValue();
			 StringTokenizer st=new StringTokenizer(valores,"|");
			 while (st.hasMoreTokens()){
				   medical_id=st.nextToken();
	               startDate=st.nextToken();
	               endDate=st.nextToken();
	               medicine=st.nextToken();
	               units=st.nextToken();
	               via_admin=st.nextToken();
	               duration=st.nextToken();
	               dosage=st.nextToken();
	               freq=st.nextToken();
	               indicators=st.nextToken();
	               contra_indicators=st.nextToken();
	               image=st.nextToken();
	               dose_image=st.nextToken();
			 }
			 if(!resultado.contains(medicine)){
				 resultado.add(medicine+"|"+startDate+"|"+duration+"|"+dosage+"|"+freq+"|"+indicators+"|"+contra_indicators);
				 
			 }
		}
		return resultado;
	}
	
	
	
	
	
	
	
	
	public String getFreq() {
		return freq;
	}

	public void setFreq(String freq) {
		this.freq = freq;
	}

	public HashMap<String, String> getCalendar_medicacion() {
		return medication_calendar;
	}

	public void setCalendario_medicacion(
			HashMap<String, String> medication_calendar) {
		this.medication_calendar = medication_calendar;
	}

	public String getContraindicators() {
		return contra_indicators;
	}

	public void setContraindicators(String contra_indicators) {
		this.contra_indicators = contra_indicators;
	}

	public String getDuracion() {
		return duration;
	}

	public void setDuracion(String duration) {
		this.duration = duration;
	}

	public String getFarmaco() {
		return medicine;
	}

	public void setFarmaco(String medicine) {
		this.medicine = medicine;
	}

	public String getFechaFin() {
		return endDate;
	}

	public void setFechaFin(String endDate) {
		this.endDate = endDate;
	}

	public String getFechaInicio() {
		return startDate;
	}

	public void setFechaInicio(String startDate) {
		this.startDate = startDate;
	}

	public String getId_medico() {
		return medical_id;
	}

	public void setId_medico(String medical_id) {
		this.medical_id = medical_id;
	}

	public String getId_paciente() {
		return patient_id;
	}

	public void setId_paciente(String patient_id) {
		this.patient_id = patient_id;
	}

	public String getId_tratamiento() {
		return treatment_id;
	}

	public void setId_tratamiento(String treatment_id) {
		this.treatment_id = treatment_id;
	}

	public String getImagen() {
		return image;
	}

	public void setImagen(String image) {
		this.image = image;
	}

	public String getImagen_dosis() {
		return dose_image;
	}

	public void setImagen_dosis(String dose_image) {
		this.dose_image = dose_image;
	}

	public String getIndicaciones() {
		return indicators;
	}

	public void setIndicaciones(String indicators) {
		this.indicators = indicators;
	}

	public String getNombre_pacente() {
		return patient_number;
	}

	public void setNombre_pacente(String patient_number) {
		this.patient_number = patient_number;
	}

	public String getPosologia() {
		return dosage;
	}

	public void setPosologia(String dosage) {
		this.dosage = dosage;
	}

	public String getUnidades() {
		return units;
	}

	public void setUnidades(String units) {
		this.units = units;
	}

	public String getVia_admin() {
		return via_admin;
	}

	public void setVia_admin(String via_admin) {
		this.via_admin = via_admin;
	}

	
}
