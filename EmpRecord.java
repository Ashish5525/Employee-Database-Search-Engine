package project4;

public class EmpRecord implements Cloneable, Comparable<EmpRecord>{
	
	String emplID;
	String lastName;
	String firstName;
	String position;
	String site;
	
	public EmpRecord (String id, String last, String first, String pos, String s) {
		
		lastName = last;
		firstName = first;
		position = pos;
		site = s;
		emplID = id;
		
	}
	
	public String getLastName() {
		
		return lastName;
		
	}

	public String getEmplID() {
		
		return emplID;
		
	}

	public void setEmplID(String emplID) {
		
		this.emplID = emplID;
		
	}

	public String getFirstName() {
		
		return firstName;
		
	}

	public void setFirstName(String firstName) {
		
		this.firstName = firstName;
		
	}

	public String getPosition() {
		
		return position;
		
	}

	public void setPosition(String position) {
		
		this.position = position;
		
	}

	public String getSite() {
		
		return site;
		
	}

	public void setSite(String site) {
		
		this.site = site;
		
	}

	public void setLastName(String lastName) {
		
		this.lastName = lastName;
		
	}
	
	public Object clone() throws CloneNotSupportedException {
		
		return super.clone();
		
	}
	
	public String toString() {
		
		return String.format("%-15s%-15s%-15s%-15s%-15s\n", emplID, lastName, firstName, position, site);
		
	}

	@Override
	public int compareTo(EmpRecord o) {
		// TODO Auto-generated method stub
		return this.emplID.compareTo(o.emplID);
		
	}
	


}
