//David McClatchey CSC3410

//Program Purpose: The purpose of this program is to create a phone directory 
//in the form of a linked list. The program has eight different types of
//functionality. The list is always sorted - by last name, then by first name,
//and finally by phone number. There are no duplicate entries in the directory.
//The user is prompted to interact with the phone directory to enter and edit
//entries as desired. The program runs until the user decides to quit.

//Solution/Algorithms: For my solution, I used a main method, 8 functionality
//methods, and I created a class for creating the entries. The sorting
//algorithm I used comes up any time an entry is added or edited. It uses a
//while loop, for loop, and many if statements linearly comparing the new or
//edited entry with the existing entries to find where to insert. I used a 
//switch statement for the user to interact with the program and make a choice
//of what action to take next.

//How to use/expected input/output: Upon starting the program, the user is
//prompted to enter a letter to take a specific action. If that action is
//invalid for some reason, then the user is prompted to try again. At the
//end of executing the program, the user should have a full phone directory
//to display with first names, last names, and phone numbers. This directory
//will be sorted by last name, first name, and then phone number.

//Purpose of classes developed: the phonedir class is the main class used
//where most of the functionality takes place. I created a personal info
//class in order to store the information of an entry and to be able to 
//extract the information from the objects created through the methods in
//the personalinfo class.



package phonedir;
import java.util.*;
import java.util.LinkedList;

public class phonedir {

	public static void main(String[] args) {
		LinkedList<personalinfo> phonedirectory = new LinkedList<personalinfo>();
		int currentindex=0;
		
		char userinput = 'x';
		while(userinput!='q'){
			
		System.out.println("Please choose from the following options ('q' to quit):");
		System.out.println("a     Show all records");
		System.out.println("d     Delete the current record");
		System.out.println("f     Change the first name in the current record");
		System.out.println("l     Change the last name in the current record");
		System.out.println("n     Add a new record");
		System.out.println("p     Change the phone number in the current record");
        System.out.println("q     Quit");
        System.out.println("s     Select a record from the record list to become the current record");
        Scanner s = new Scanner(System.in);
        userinput = s.nextLine().charAt(0);
        
        switch(userinput){
        	case 'a': showrecords(phonedirectory);
        			break;
        	case 'd': deletecurrent(currentindex,phonedirectory);
        			currentindex=-1;
        			System.out.println("No record currently selected");
        			break;
        	case 'f': currentindex=changefname(currentindex, phonedirectory);
        			break;
        	case 'l': currentindex=changelname(currentindex, phonedirectory);
        			break;
        	case 'n': currentindex =newrecord(currentindex, phonedirectory);
        			System.out.println("");
        			System.out.print("Current record is ");
        			System.out.print(phonedirectory.get(currentindex).getfirstname() + " ");
        			System.out.print(phonedirectory.get(currentindex).getlastname() + " ");
        			System.out.println(phonedirectory.get(currentindex).getphonenumber() + " ");
        			System.out.println("");
        		break;
        	case 'p': currentindex=changephonum(currentindex, phonedirectory);
        			break;
        	case 'q': break;
        	case 's': int j = makecurrent(phonedirectory);
        				if(j==-1){//if method returns -1, no match is found
        				System.out.println("No matching record found.");
        				}
        				else {
        				currentindex=j;
        				System.out.println("");
        				System.out.print("Current record is ");
        				System.out.print(phonedirectory.get(currentindex).getfirstname() + " ");
        				System.out.print(phonedirectory.get(currentindex).getlastname() + " ");
        				System.out.println(phonedirectory.get(currentindex).getphonenumber() + " ");
        				System.out.println("");
        				}
        	break;
        	default: System.out.println("Illegal Command");
        	System.out.println();
        	
        }
		}
		System.out.println("The program is finished!");
	}
	
	//Method pre-condition: This method takes in a LinkedList of objects
	//this LinkedList can be empty or have values
	//Method post-condition: The contents of the LinkedList are displayed
	//if there are entries. If there are no entries, a message says so.
	public static void showrecords(LinkedList<personalinfo> pd){
		if(pd.size()==0){
			System.out.println();
			System.out.println("There are no records yet.");
			System.out.println();
		}
		
		else{
		System.out.println();
		System.out.println("First Name         Last Name       Phone Number");
		System.out.println("----------         ------------    ------------");
		
		for(int i=0; i<pd.size(); i++){
		System.out.println(pd.get(i).getfirstname() +"              " + pd.get(i).getlastname() +"      " + pd.get(i).getphonenumber());
		}
		System.out.println();
		}
	}
	
	//Method precondition: this method takes in the current entry's index
	//and the current entire LinkedList. The index can be 0 or greater and
	//if the LinkedList is empty, a message says so.
	//Method postcondition: at the end, an entry is deleted and the index
	//gets changed to -1 inside the main method.
	public static void deletecurrent(int theindex, LinkedList<personalinfo> pd){
		if(pd.size()==0){
			System.out.println();
			System.out.println("There are no records yet.");
			System.out.println();
		}
		else{
		System.out.print("Deleted: ");
		System.out.print(pd.get(theindex).getfirstname() + " ");
		System.out.print(pd.get(theindex).getlastname() + " ");
		System.out.println(pd.get(theindex).getphonenumber() + " ");
		pd.remove(theindex);
		}
	}
	
	//Method precondition: this method takes in the current entry's index
	//and the current entire LinkedList. The index can be 0 or greater and
	//if the LinkedList is empty, a message says so.
	//Method postcondition: By the end of the method, the original entry
	//has been copied with a new first name and then deleted, and the copy
	//is reinserted in the LinkedList in alphabetical order.
	public static int changefname(int theindex, LinkedList<personalinfo> pd){
		if(pd.size()==0){
			System.out.println();
			System.out.println("There are no records yet.");
			System.out.println();
		}
		else{
		Scanner s = new Scanner(System.in);
		String newfirstname = new String();
		System.out.println("Enter new first name");
		newfirstname = s.next();
		//current record gets copied with new information
		personalinfo recordnew = new personalinfo(newfirstname,pd.get(theindex).getlastname(),pd.get(theindex).getphonenumber());
		//current record gets deleted
		pd.remove(theindex);
		
		//copy with new information gets inserted in the proper place:
		int i = 0;
		while(i<pd.size() && recordnew.getlastname().toLowerCase().compareTo(pd.get(i).getlastname().toLowerCase())>0){
			i++;
		}
		if(i==pd.size()){
			pd.add(recordnew);//added at the end
			theindex=i;
		}
		else if(recordnew.getlastname().toLowerCase().compareTo(pd.get(i).getlastname().toLowerCase())<0){
			pd.add(i,recordnew);
			theindex=i;
		}
		else if(recordnew.getlastname().toLowerCase().compareTo(pd.get(i).getlastname().toLowerCase())==0){
			if(recordnew.getfirstname().toLowerCase().compareTo(pd.get(i).getfirstname().toLowerCase())<0){
				pd.add(i,recordnew);
				theindex=i;
			}
			if(recordnew.getfirstname().toLowerCase().compareTo(pd.get(i).getfirstname().toLowerCase())>0){
				pd.add(i+1,recordnew);
				theindex=i+1;
			}
			if(recordnew.getfirstname().toLowerCase().compareTo(pd.get(i).getfirstname().toLowerCase())==0){
				if(recordnew.getphonenumber().compareTo(pd.get(i).getphonenumber())<0){
					pd.add(i,recordnew);
					theindex=i;
				}
				if(recordnew.getphonenumber().compareTo(pd.get(i).getphonenumber())>0){
					pd.add(i+1,recordnew);
					theindex=i+1;
				}
			}
		}
		System.out.println("");
		System.out.print("Current record is ");
		System.out.print(pd.get(theindex).getfirstname() + " ");
		System.out.print(pd.get(theindex).getlastname() + " ");
		System.out.println(pd.get(theindex).getphonenumber() + " ");
		System.out.println("");
		}
		return theindex;
	}
	
	//Method precondition: this method takes in the current entry's index
	//and the current entire LinkedList. The index can be 0 or greater and
	//if the LinkedList is empty, a message says so.
	//Method postcondition: By the end of the method, the original entry
	//has been copied with a new last name and then deleted, and the copy
	//is reinserted in the LinkedList in alphabetical order.
	public static int changelname(int theindex, LinkedList<personalinfo> pd){
		if(pd.size()==0){
			System.out.println();
			System.out.println("There are no records yet.");
			System.out.println();
		}
		else{
		Scanner s = new Scanner(System.in);
		String newlastname = new String();
		System.out.println("Enter new last name");
		newlastname = s.next();
		personalinfo recordnew = new personalinfo(pd.get(theindex).getfirstname(),newlastname,pd.get(theindex).getphonenumber());
		pd.remove(theindex);
		
		int i = 0;
		while(i<pd.size() && newlastname.toLowerCase().compareTo(pd.get(i).getlastname().toLowerCase())>0){
			i++;
		}
		if(i==pd.size()){
			pd.add(recordnew);
			theindex=i;
		}
		else if(newlastname.toLowerCase().compareTo(pd.get(i).getlastname().toLowerCase())<0){
			pd.add(i,recordnew);
			theindex=i;
		}
		else if(newlastname.toLowerCase().compareTo(pd.get(i).getlastname().toLowerCase())==0){
			if(recordnew.getfirstname().toLowerCase().compareTo(pd.get(i).getfirstname().toLowerCase())<0){
				pd.add(i,recordnew);
				theindex=i;
			}
			if(recordnew.getfirstname().toLowerCase().compareTo(pd.get(i).getfirstname().toLowerCase())>0){
				pd.add(i+1,recordnew);
				theindex=i+1;
			}
			if(recordnew.getfirstname().toLowerCase().compareTo(pd.get(i).getfirstname().toLowerCase())==0){
				if(recordnew.getphonenumber().compareTo(pd.get(i).getphonenumber())<0){
					pd.add(i,recordnew);
					theindex=i;
				}
				if(recordnew.getphonenumber().compareTo(pd.get(i).getphonenumber())>0){
					pd.add(i+1,recordnew);
					theindex=i+1;
				}
			}
		}
		
		System.out.println("");
		System.out.print("Current record is ");
		System.out.print(pd.get(theindex).getfirstname() + " ");
		System.out.print(pd.get(theindex).getlastname() + " ");
		System.out.println(pd.get(theindex).getphonenumber() + " ");
		System.out.println("");
		}
		return theindex;
	}
	
	//Method precondition: this method takes in the current entry's index
	//and the current entire LinkedList. The index can be 0 or greater and
	//the LinkedList can be empty or have entries already.
	//Method postcondition: By the end of the method, a new record has been
	//inserted in the proper alphabetical order into the LinkedList. It is 
	//inserted based on last name, then first name, then phone number.
	public static int newrecord(int theindex, LinkedList<personalinfo> pd){
		String fname = new String();
		String lname = new String();
		String phonum = new String();
		
		Scanner c = new Scanner(System.in);
		System.out.println("Please enter the first name");
		fname = c.next();
		System.out.println("Please enter the last name");
		lname = c.next();
		System.out.println("Please enter the phone number");
		phonum = c.next();
		//new object created
		personalinfo recordnew = new personalinfo(fname,lname,phonum);
		
		//case where LinkedList is empty
		if(pd.size()==0){
			pd.add(recordnew);
			theindex=0;
		}
		//following algorithm finds the right spot to insert the new entry
		else{
		int i = 0;
		while(i<pd.size() && lname.toLowerCase().compareTo(pd.get(i).getlastname().toLowerCase())>0){
			i++;
		}
		if(i==pd.size()){
			pd.add(recordnew);
			theindex=i;
		}
		else if(lname.toLowerCase().compareTo(pd.get(i).getlastname().toLowerCase())<0){
			pd.add(i,recordnew);
			theindex=i;
		}
		else if(lname.toLowerCase().compareTo(pd.get(i).getlastname().toLowerCase())==0){
			if(fname.toLowerCase().compareTo(pd.get(i).getfirstname().toLowerCase())<0){
				pd.add(i,recordnew);
				theindex=i;
			}
			if(fname.toLowerCase().compareTo(pd.get(i).getfirstname().toLowerCase())>0){
				pd.add(i+1,recordnew);
				theindex=i+1;
			}
			if(fname.toLowerCase().compareTo(pd.get(i).getfirstname().toLowerCase())==0){
				if(phonum.compareTo(pd.get(i).getphonenumber())<0){
					pd.add(i,recordnew);
					theindex=i;
				}
				if(phonum.compareTo(pd.get(i).getphonenumber())>0){
					pd.add(i+1,recordnew);
					theindex=i+1;
				}
			}
		}
		}
		return theindex;
	}
	
	//Method precondition: this method takes in the current entry's index
	//and the current entire LinkedList. The index can be 0 or greater and
	//if the LinkedList is empty, a message says so.
	//Method postcondition: By the end of the method, the original entry
	//has been copied with a new phone number and then deleted, and the copy
	//is reinserted in the LinkedList in alphabetical order.
	public static int changephonum(int theindex, LinkedList<personalinfo> pd){
		if(pd.size()==0){
			System.out.println();
			System.out.println("There are no records yet.");
			System.out.println();
		}
		else{
		Scanner s = new Scanner(System.in);
		String newphonum = new String();
		System.out.println("Enter new phone number");
		newphonum = s.next();
		
		personalinfo recordnew = new personalinfo(pd.get(theindex).getfirstname(),pd.get(theindex).getlastname(),newphonum);
		pd.remove(theindex);
		
		int i = 0;
		while(i<pd.size() && recordnew.getlastname().toLowerCase().compareTo(pd.get(i).getlastname().toLowerCase())>0){
			i++;
		}
		if(i==pd.size()){
			pd.add(recordnew);
			theindex=i;
		}
		else if(recordnew.getlastname().toLowerCase().compareTo(pd.get(i).getlastname().toLowerCase())<0){
			pd.add(i,recordnew);
			theindex=i;
		}
		else if(recordnew.getlastname().toLowerCase().compareTo(pd.get(i).getlastname().toLowerCase())==0){
			if(recordnew.getfirstname().toLowerCase().compareTo(pd.get(i).getfirstname().toLowerCase())<0){
				pd.add(i,recordnew);
				theindex=i;
			}
			if(recordnew.getfirstname().toLowerCase().compareTo(pd.get(i).getfirstname().toLowerCase())>0){
				pd.add(i+1,recordnew);
				theindex=i+1;
			}
			if(recordnew.getfirstname().toLowerCase().compareTo(pd.get(i).getfirstname().toLowerCase())==0){
				if(recordnew.getphonenumber().compareTo(pd.get(i).getphonenumber())<0){
					pd.add(i,recordnew);
					theindex=i;
				}
				if(recordnew.getphonenumber().compareTo(pd.get(i).getphonenumber())>0){
					pd.add(i+1,recordnew);
					theindex=i+1;
				}
			}
		}
		
		System.out.println("");
		System.out.print("Current record is ");
		System.out.print(pd.get(theindex).getfirstname() + " ");
		System.out.print(pd.get(theindex).getlastname() + " ");
		System.out.println(pd.get(theindex).getphonenumber() + " ");
		System.out.println("");
		}
		return theindex;
	}
	
	//Method precondition: this method takes in the current entire LinkedList
	//in order to have the user enter a record that will correspond with
	//one of the entries in the LinkedList.
	//Method postcondition: By the end of the method, the current index gets
	//returned according to the record entered. If there is no matching
	//record, then the method returns -1.
	public static int makecurrent(LinkedList<personalinfo> pd){
		String fname = new String();
		String lname = new String();
		String phonum = new String();
		
		Scanner c = new Scanner(System.in);
		System.out.println("Please enter the first name");
		fname = c.next();
		System.out.println("Please enter the last name");
		lname = c.next();
		System.out.println("Please enter the phone number");
		phonum = c.next();
		
		int i;
		//loop goes through linearly to find match
		for(i=0; i<pd.size(); i++){
			if(		pd.get(i).getlastname().equals(lname)
				 && pd.get(i).getfirstname().equals(fname)
				 && pd.get(i).getphonenumber().equals(phonum)){
				return i;
			}
		}
			return (-1);
		
		
		
		
	}

}
