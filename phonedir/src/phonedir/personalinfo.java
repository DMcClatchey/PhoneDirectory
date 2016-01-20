package phonedir;

public class personalinfo {
	
	private String firstname = new String();
	private String lastname = new String();
	private String phonenumber = new String();
	
	public personalinfo(String fname, String lname, String phone){
		firstname=fname;
		lastname=lname;
		phonenumber=phone;
	}
	
	public void setfirstname(String fname){
		firstname = fname;
	}
	
	public void setlastname(String lname){
		lastname = lname;
	}
	
	public void setphonenumber(String phonum){
		phonenumber = phonum;
	}
	
	public String getfirstname(){
		return firstname;
	}
	
	public String getlastname(){
		return lastname;
	}
	
	public String getphonenumber(){
		return phonenumber;
	}
	
}
