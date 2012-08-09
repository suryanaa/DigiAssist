package android.digiassist.bean;

public class PatientInfo {

	private String patientId;
    private String lastName;
    private String number;
    private final int LIMIT_CHARACTERS = 26;
    
    public PatientInfo(){}
    
    public boolean completed()
    {
		boolean result = true;
		if(lastName==null)
		    result = false;
		return result;
        }
    
    public String toString()
    {
		StringBuffer sb = new StringBuffer();
		sb.append("Patient ITEM");
		sb.append("\n +Patient ID: " + patientId);
		sb.append("\n +Number: " + number);
		sb.append("\n +Last Name: " + lastName);
		return sb.toString();
    }

    public void setCod_Paciente(String patientId)
    {
		this.patientId = patientId;
	}

    public String getPatientId() 
    {
		return patientId;
	}

    public void setNumber(String nombre) 
    {
		this.number = nombre;
	}

    public String getNumber() 
    {
		return number;
	}

    public void setLastName(String lastName) 
    {
		this.lastName = lastName;
	}

    public String getLastName() 
    {
		return lastName;
	}    
}
