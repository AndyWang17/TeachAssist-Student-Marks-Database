import java.io.*;
import java.util.*;

import static java.lang.Math.*;
import static java.lang.System.out;

public class course implements Comparable<course> {
	int courseId;
	String courseName;
	int teacherId;// don't need to be initialize with the object
	ArrayList<Integer> student;// student number or student object?
	ArrayList<Integer> averageMark;
	int period;
	int roomNo;
	public static final Comparator<course> periodOrder = new courseOrder();

	public course(int id, String courseName, int period, int roomNo) {// create without teacher and student
		this.courseId = id;
		this.courseName = courseName;
		this.teacherId = 0;
		this.period = period;
		this.roomNo = roomNo;
	}

	public course(int id, String courseName, int period, int roomNo,
			int teacherId, ArrayList<Integer> student) {// read with teacher and students
		this.courseId = id;
		this.courseName = courseName;
		this.teacherId = teacherId;
		this.period = period;
		this.roomNo = roomNo;
		this.student = student;
	}

	public course(int id) { // for searching
		this.courseId = id;
	}

	public int getPeriod() {
		return this.period;
	}

	public ArrayList<Integer> getStudentID() {
		return this.student;
	}

	public int getTeacherID() {
		return this.teacherId;
	}

	public int getID() {
		return this.courseId;
	}

	public void courseChange(int id, String courseName, int period, int roomNo) {
		this.courseId = id;
		this.courseName = courseName;
		this.teacherId = 0;
		this.period = period;
		this.roomNo = roomNo;

	}

	public static void courseChange(ArrayList<Integer> course, int teacherId,
			ArrayList<course> courseList) throws IOException {
		// add teacher to course object
		for (int courseId : course) {
			// System.out.println(courseId);
			course newCourse = new course(courseId);
			int index = courseList.indexOf(newCourse);
			newCourse = courseList.get(index);
			newCourse.teacherId = teacherId;
		}
		main.clearCourse();
		for (course courseOut : courseList) {
			log(courseOut);
		}
	}

	public static void studentChange(ArrayList<Integer> course, int studentId,
			ArrayList<course> courseList) throws IOException {
		// add student id to course object
		for (int courseId : course) {
			course newCourse = new course(courseId);
			int index = courseList.indexOf(newCourse);
			newCourse = courseList.get(index);
			newCourse.student.add(studentId);
		}
		main.clearCourse();
		for (course courseOut : courseList) {
			log(courseOut);
		}
	}

	public static void studentRemove(ArrayList<Integer> course, int studentId,
			ArrayList<course> courseList) throws IOException {
		// add student id to course object
		for (int courseId : course) {
			// System.out.println(courseId);
			course newCourse = new course(courseId);
			int index = courseList.indexOf(newCourse);
			// System.out.println(index);
			newCourse = courseList.get(index);
			/*
			 * for(int ID:newCourse.student){ System.out.println(ID); }
			 */
			int indexOfstudent = newCourse.student.indexOf(studentId);
			newCourse.student.remove(indexOfstudent);
		}
		main.clearCourse();
		for (course courseOut : courseList) {
			log(courseOut);
		}
	}

	public int compareTo(course other) {
		return Integer.compare(this.courseId, other.courseId);
	}

	// course conflict check!//

	public static void printTitle() {
		System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%n", "Course ID",
				"Course Name", "Period number", "Room number", "Teacher ID",
				"Student ID");
	}

	public String toString() {
		String student = "";
		try {
			for (Integer x : this.student) {
				student += x + ", ";
			}
		} catch (Exception e) {
			student = "No student yet";
		}
		return String.format("%-20d%-20s%-20d%-20d%-20d%-20s%n", this.courseId,
				this.courseName, this.period, this.roomNo, this.teacherId,
				student);
	}

	public String printSchedule() throws FileNotFoundException {
		 ArrayList<teacher>  listOfteacher = main.readFileTeacher();
		 teacher target = new teacher(this.teacherId);
		 int index = listOfteacher.indexOf(target);
		 String name = listOfteacher.get(index).getTeacherName();
		return String.format("%-20d%-20s%-20d%-20d%-20s", this.period,
				this.courseName, this.courseId, this.roomNo, name);
	}
	
	public String printMark(double mark) {
		return String.format("%-20d%-20s%-20d%.1f%%", this.period,
				this.courseName, this.courseId, mark);
	}
	
	public String printCourseTitle(){
		return String.format("Course Name: %-20s%nCourse ID: %-20d%nRoom Number: %-20d%n",
				this.courseName, this.courseId, this.roomNo);
	}
	public static void log(course newCourse) throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter("data2.txt", true),
				true);
		String output = new String();

		newCourse.courseName = main.spaceIn(newCourse.courseName);
		output = newCourse.courseId + "," + newCourse.courseName + ","
				+ newCourse.period + "," + newCourse.roomNo + ","
				+ newCourse.teacherId + ",";
		try {
			if (newCourse.student.size() == 0) {
				output = output + "0" + ",";
			}

			for (Integer x : newCourse.student) {
				output = output + x + "|" + ",";
			}
		} catch (Exception e) {
			output = output + 0;
		}
		output = output + "/";
		out.write(output);
		out.close();
	}

	public static int homeRm(ArrayList<Integer> course)
			throws FileNotFoundException {
		ArrayList<course> courses = new ArrayList<course>();
		for (int courseID : course) {
			course courseSearch = new course(courseID);
			ArrayList<course> database = new ArrayList<course>();
			database = main.readFileCourse();
			/*
			 * for(course coursescheck: database){
			 * System.out.print(coursescheck); }
			 */
			int index = database.indexOf(courseSearch);
			courses.add(database.get(index));
		}
		Collections.sort(courses, periodOrder);
		return courses.get(0).roomNo;
	}

	public boolean equals(Object other) {
		if (!(other instanceof course))
			return false;
		course otherCourse = (course) other;
		return this.courseId == otherCourse.courseId;
	}

	private static class courseOrder implements Comparator<course> {
		/**
		 * Compares the balances of two BankAccount objects
		 * 
		 * @param first
		 *            the first BankAccount to compare
		 * @param second
		 *            the second BankAccount to compare
		 * @return a value < 0 if the first BankAccount has a lower balance, a
		 *         value > 0 if first BankAccount has a higher balance and 0 if
		 *         the balances of the BankAccount are the same
		 */
		public int compare(course first, course second) {
			if (first.period < second.period)
				return -1;
			else if (first.period > second.period)
				return 1;
			else
				return 0;
		}
	}
}
