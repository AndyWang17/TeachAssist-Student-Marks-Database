import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;


public class student implements Comparable<student>  {
	int id;
	int password;
	ArrayList<Integer> courses;
	String firstName;
	String lastName;
	String dateOfbirth;
	boolean sex;
	int grade;
	double telephone;
	double mobileTel;
	String email;
	String address;
	ArrayList <Double> marks;
	//double markA; double markB; double markC; double markD;
	double volunteerHours;
	int homeroom;
	boolean literacyTest;
	
	public student(int id){
		this.id = id;
	}
	public student(int id, ArrayList<Integer> courses,ArrayList <Double> marks, String firstName, String lastName,String dateOfbirth,
	boolean sex,int grade, double telephone, double mobileTel, String email, String address, double volunteerHours,
	boolean literacyTest) throws FileNotFoundException {
		this.id = id;
		this.password = 0;
		this.firstName = firstName; 
		this.lastName = lastName; 
		this.dateOfbirth = dateOfbirth;
		this.sex = sex;
		this.grade = grade;
		this.telephone = telephone; 
		this.mobileTel = mobileTel;
		this.email = email;
		this.address= address;
		this.volunteerHours = volunteerHours; 
		this.courses = courses;
		this.homeroom = course.homeRm(courses);
		this.literacyTest = literacyTest;
		this.marks = marks;
	}
	public student(int id,int password, ArrayList<Integer> courses,ArrayList <Double> marks, String firstName, String lastName,String dateOfbirth,
	boolean sex, int grade,double telephone, double mobileTel, String email, String address, double volunteerHours,
	int homeroom, boolean literacyTest) {
		this.id = id;
		this.password = password;
		this.firstName = firstName; 
		this.lastName = lastName; 
		this.dateOfbirth = dateOfbirth;
		this.sex = sex;
		this.grade = grade;
		this.telephone = telephone; 
		this.mobileTel = mobileTel;
		this.email = email;
		this.address= address;
		this.volunteerHours = volunteerHours; 
		this.courses = courses;
		this.homeroom = homeroom;
		this.literacyTest = literacyTest;
		this.marks = marks;
	}
	public void studentChange(int id, ArrayList<Integer> courses,String firstName, String lastName,String dateOfbirth,
			boolean sex, int grade, double telephone, double mobileTel, String email, String address, double volunteerHours,
			boolean literacyTest) throws FileNotFoundException {
				this.id = id;
				this.firstName = firstName; 
				this.lastName = lastName; 
				this.dateOfbirth = dateOfbirth;
				this.sex = sex;
				this.grade = grade;
				this.telephone = telephone; 
				this.mobileTel = mobileTel;
				this.email = email;
				this.address= address;
				this.volunteerHours = volunteerHours; 
				this.courses = courses;
				this.homeroom = course.homeRm(courses);
				this.literacyTest = literacyTest;
				marks.clear();
				for(int x =0;x<courses.size();x++){
					marks.add(0.0);
			}
		}
	public int getPassword(){
		return this.password;
	}
	
	public void markInput(int courseIndex,double studentmark){
		marks.set(courseIndex,studentmark);
		
		
	}
	
	public int compareTo (student other)
	{
	return Integer.compare(this.id, other.id);
	
	}
	
	public ArrayList<Integer> getCourseId(){
		return this.courses;
	}

	
	public ArrayList<Double> getMarks(){
		return this.marks;
	}
	
	public static void printTitle(){
		System.out.printf("%-20s%-20s%-20s%-35s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%n",
				"Student ID", "Password", "Course","Mark","First Name", "Last Name", "Date of birth",
				"Sex", "Grade","Homeroom", "Telephone","Mobile phone", "Address", "Email","Volunteer hours", "Literacy test");
	}
	
	public static void printTitle2(){ //Without Password
		System.out.printf("%-20s%-20s%-20s%-20s%-35s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%n",
				"Student ID", "First Name", "Last Name", "Course","Mark",
				"Sex", "Grade","Homeroom","Telephone","Mobile phone", "Address", "Email");
	}
	public String printStudent() {
		String course = "";
		String marks = "";
		String sexOut = "";
		String passfail = "";
		for (Integer x : this.courses) {
			course += x + ", ";
		}
		for (double x : this.marks) {
			marks += x + "%, ";
		}
		if (this.sex)
			sexOut="male";
		else if(!this.sex)
		sexOut="female";
		if (this.literacyTest)
			passfail = "Pass";
		else if (!this.literacyTest)
			passfail = "Fail";
		return String.format ("%-20d%-20s%-20s%-20s%-35s%-20s%-20d%-20d%-20.0f%-20.0f%-20s%-20s%n",
				this.id,this.firstName,this.lastName,course,marks,
			sexOut,this.grade,this.homeroom,this.telephone,this.mobileTel,
				this.address,this.email);
	}
	
	public String toString() {
		
		String course = "";
		String sexOut = "";
		String passfail = "";
		for (Integer x : this.courses) {
			course += x + ", ";
		}
		String marks ="";
		for (double x : this.marks) {
			marks += x + "%, ";
		}
		if (this.sex)
			sexOut="male";
		else if(!this.sex)
		sexOut="female";
		if (this.literacyTest)
			passfail = "Pass";
		else if (!this.literacyTest)
			passfail = "Fail";
		return String.format ("%-20d%-20d%-20s%-35s%-20s%-20s%-20s%-20s%-20d%-20d%-20.0f%-20.0f%-20s%-20s%-20.0f%-20s%n",
				this.id,this.password,course,marks,this.firstName,this.lastName,
				this.dateOfbirth,sexOut,this.grade,this.homeroom,this.telephone,this.mobileTel,
				this.address,this.email,this.volunteerHours,passfail);
	}
	public void getSchedule() throws FileNotFoundException{
		ArrayList<course>courses=new ArrayList <course> ();
		for(int courseID: this.courses)
		{
			course courseSearch =new course (courseID);
			ArrayList<course>database= new ArrayList<course>();
			database = main.readFileCourse();
			/*for(course coursescheck: database){
				System.out.print(coursescheck);
			}
			*/
			int index = database.indexOf(courseSearch);
			courses.add(database.get(index)) ;
		}
		Collections.sort(courses,course.periodOrder);
		String output=new String();
		System.out.printf("%-20s%-20s%-20s%-20s%-20s%n%n","Period:","Course name:","Course ID:", "Room number:", "Teacher");
		for (course currentCourse :courses){
			System.out.println(currentCourse.printSchedule());
		}
	}

	public void passwordChange(int password) {
		this.password=password;
		System.out.println("Your password is sucessfully updated.");
		System.out.println("Please re-enter to login");
		System.out.println("");
	}
	
	public static void log(student newone) throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter("data1.txt",
				true), true);
		String output = new String();
		output = newone.id + "," + newone.password + ",";
		
		newone.firstName=main.spaceIn(newone.firstName);
		newone.lastName =main.spaceIn(newone.lastName);
		newone.dateOfbirth=main.spaceIn(newone.dateOfbirth);
		newone.address=main.spaceIn(newone.address);
		newone.email=main.spaceIn(newone.email);

		for (Integer x : newone.courses) {
			output = output + x + "|";
		}
		output=output+",";
		for (Double x : newone.marks) {
			output = output + x + "|";
		}
		output = output+ "," + newone.firstName + "," +newone.lastName +"," 
		+newone.dateOfbirth  + ","+ newone.sex  + "," + newone.grade + "," + newone.homeroom + "," + newone.telephone  + "," + newone.mobileTel + "," + newone.address
				+ "," +newone.email + "," +  newone.volunteerHours + "," + newone.literacyTest + "/";
		out.write(output);
		out.close();
	}
	public void timeTable(){
		
	}
	public boolean equals (Object other)
	{
	if (!(other instanceof student))
	return false;
	student otherStudent = (student) other;
	return this.id==otherStudent.id;
	}
}
