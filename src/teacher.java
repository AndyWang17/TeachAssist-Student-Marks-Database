import java.io.*;
import java.util.*;

import static java.lang.Math.*;
import static java.lang.System.out;

public class teacher implements Comparable<teacher>  {
	int id;
	int password;
	ArrayList<Integer> courses;
	String firstName;
	String lastName;
	String dateOfbirth;
	boolean sex;
	double telephone;
	String address;
	String specialty;
	String information;
	
	public teacher(int id){
		this.id=id;
	}
	public teacher(int id, ArrayList<Integer> courses,String firstName, String lastName,String dateOfbirth,
	boolean sex, double  telephone,String address,String specialty,String information) {
		this.id = id;
		this.password = 0;
		this.firstName = firstName; 
		this.lastName = lastName; 
		this.dateOfbirth = dateOfbirth;
		this.sex = sex;
		this.telephone = telephone; 
		this.address= address;
		this.specialty = specialty; 
		this.courses = courses;
		this.information = information;
	}
	public teacher(int id, int password, ArrayList<Integer> courses,String firstName, String lastName,String dateOfbirth,
			boolean sex, double  telephone,String address,String specialty,String information) {
				this.id = id;
				this.password = password;
				this.firstName = firstName; 
				this.lastName = lastName; 
				this.dateOfbirth = dateOfbirth;
				this.sex = sex;
				this.telephone = telephone; 
				this.address= address;
				this.specialty = specialty; 
				this.courses = courses;
				this.information = information;
			}
	public int getPassword(){
		return this.password;
		
	}
	public ArrayList<Integer> getCourseId(){
		return this.courses;
	}
	//from administration
	public void teacherChange(int id, ArrayList<Integer> courses,String firstName, String lastName,String dateOfbirth,
			boolean sex, double  telephone,String address,String specialty,String information) {
		this.id = id;
		this.firstName = firstName; 
		this.lastName = lastName; 
		this.dateOfbirth = dateOfbirth;
		this.sex = sex;
		this.telephone = telephone; 
		this.address= address;
		this.specialty = specialty; 
		this.courses = courses;
		this.information = information;
	}
	//from teacher 
	public void teacherChange(String firstName, String lastName,String dateOfbirth,
			boolean sex, double  telephone,String address,String specialty,String information) {
		this.id = id;
		this.firstName = firstName; 
		this.lastName = lastName; 
		this.dateOfbirth = dateOfbirth;
		this.sex = sex;
		this.telephone = telephone; 
		this.address= address;
		this.specialty = specialty; 
		this.courses = courses;
		this.information = information;
	}
	public void passwordChange(int password) {
		this.password=password;
		System.out.println("Your password is sucessfully updated.");
		System.out.println("Please re-enter to login");
		System.out.println("");
	}
	public String getTeacherName () {
		String name = this.firstName +" "+ this.lastName;
		return name;
	}
	public void getSchedule() throws FileNotFoundException{
		ArrayList<course>courses=new ArrayList <course> ();
		for(int courseID: this.courses)
		{
			course courseSearch =new course (courseID);
			ArrayList<course>database= new ArrayList<course>();
			database =main.readFileCourse();
			/*for(course coursescheck: database){
				System.out.print(coursescheck);
			}
			*/
			int index = database.indexOf(courseSearch);
			courses.add(database.get(index)) ;
		}
		Collections.sort(courses,course.periodOrder);
		String output=new String();
		System.out.printf("%-20s%-20s%-20s%-20s%n%n","Period:","Course name:","Course ID:", "Room number:");
		for (course currentCourse :courses ){
			System.out.println(currentCourse.printSchedule());
		}
	}
	public int compareTo (teacher other)
	{
	return Integer.compare(this.id, other.id);
	
	}
	public static void printTitle(){
		System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%n",
				"Teacher ID", "Password", "Course" ,"First Name", "Last Name", "Date of birth",
				"Sex", "Telephone", "Address", "Specialty","Additional Information" );
		
	}
	public String toString() {
		
		String course = "";
		String sexOut = "";
		for (Integer x : this.courses) {
			course += x + ", ";
		}
		if (this.sex)
			sexOut="male";
		else if(!this.sex)
		sexOut="female";
		return String.format ("%-20d%-20d%-20s%-20s%-20s%-20s%-20s%-20.0f%-20s%-20s%-20s%n",
				this.id,this.password,course,this.firstName,this.lastName,
				this.dateOfbirth,sexOut,this.telephone, this.address,this.specialty,this.information);
	}
	/*int id;
	int password;
	ArrayList<Integer> courses;
	String firstName;
	String lastName;
	String dateOfbirth;
	boolean sex;
	int telephone;
	String address;
	String specialty;
	String information;
	*/
	public static void log(teacher newone) throws IOException {
		FileWriter out = new FileWriter("data.txt", true);
		String output = new String();
		out.write(newone.id + "," + newone.password + ",");
		newone.firstName=main.spaceIn(newone.firstName);
		newone.lastName =main.spaceIn(newone.lastName);
		newone.dateOfbirth=main.spaceIn(newone.dateOfbirth);
		newone.address=main.spaceIn(newone.address);
		newone.specialty=main.spaceIn(newone.specialty);
		newone.information=main.spaceIn(newone.information);
		for (Integer x : newone.courses) {
			out.write(x + "|");
		}
		out.write("," + newone.firstName + "," +newone.lastName +","
		+newone.dateOfbirth  + ","+ newone.sex  + "," + newone.telephone  + "," +newone.address
				+ "," +newone.specialty + "," +  newone.information + "/");
		out.close();
	}
	public boolean equals (Object other)
	{
	if (!(other instanceof teacher))
	return false;
	teacher otherAccount = (teacher) other;
	return this.id==otherAccount.id;
	}
}
