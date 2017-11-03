import java.io.*;
import java.util.*;

//import static java.lang.Math.*;
//import static java.lang.System.out;

public class main {
	static final int REG_WAIT_TIME = 1000;
	static final int WAIT_TIME_PENALTY = 10000;
	public static ArrayList<teacher> listOfteacher = new ArrayList<teacher>();
	public static ArrayList<student> listOfstudent = new ArrayList<student>();
	public static ArrayList<course> listOfcourse = new ArrayList<course>();
	static boolean isValidInteger;
	static boolean inputAvailable;

	public static int readPassword() throws IOException {
		//Checks admin txt file for password
		Scanner inFile = new Scanner(new File("admin.txt"));
		Scanner scan = new Scanner(System.in);
		int password = 0;
		boolean flag = false;
		try {
			StringTokenizer split = new StringTokenizer(inFile.next(), "/",
					false);
			if (split.hasMoreTokens()) {
				password = Integer.parseInt(split.nextToken());
			}
		} catch (Exception e) {
			System.out.println("You have not yet set up a password for admin.");
			do {
				try {
					System.out.println("Please enter the default password.");
					password = Integer.parseInt(scan.nextLine());
					isValidInteger = true;

				} catch (Exception e1) {
					System.out
							.println("Error - Not a legal integer, please re-enter");
					isValidInteger = false;
				}

				if (password == 1) {
					flag = true;
					do {
						try {
							System.out
									.println("Please create a new password:");
							password = Integer.parseInt(scan.nextLine());
							isValidInteger = true;
						} catch (Exception e1) {
							System.out
									.println("Error - Not a legal integer, please re-enter");
							isValidInteger = false;
						}
					} while (!isValidInteger);

					PrintWriter out = new PrintWriter(new FileWriter(
							"admin.txt", true), true);
					String output = new String();
					output = "" + password;
					out.write(output);
					out.close();
				} else if (password == 0) {
					System.exit(0);
				} else {
					System.out
							.println("You have entered the wrong password, please try again or exit(0)");
					flag = false;
				}
			} while (!isValidInteger || !flag);
			System.out
		.println("To login in, please re-enter your password ");
		}
		return password;
	}

	public static ArrayList<teacher> readFileTeacher()
	//Reads in all teachers in the data txt file
			throws FileNotFoundException {
		Scanner inFile = new Scanner(new File("data.txt"));
		// Find how many questions in the data file
		ArrayList<teacher> listOfteacher = new ArrayList<teacher>();
		// if (inFile.hasNext()) {
		StringTokenizer split = new StringTokenizer(inFile.next(), "/", false);
		while (split.hasMoreTokens()) {
			String a = split.nextToken();
			// System.out.println(a);
			Scanner splitTeacher = new Scanner(a);
			splitTeacher.useDelimiter(",");
			// Split the teacher object
			int id = Integer.parseInt(splitTeacher.next());
			int password = Integer.parseInt(splitTeacher.next());
			String b = splitTeacher.next();
			StringTokenizer splitCourse = new StringTokenizer(b, "|", false);
			ArrayList<Integer> listOfCourse = new ArrayList<Integer>();
			// System.out.println(b);
			while (splitCourse.hasMoreTokens()) {
				listOfCourse.add(Integer.parseInt(splitCourse.nextToken()));
			}
			String firstName = splitTeacher.next();
			String lastName = splitTeacher.next();
			String dateOfbirth = splitTeacher.next();
			boolean sex = Boolean.parseBoolean(splitTeacher.next());
			double telephone = Double.parseDouble(splitTeacher.next());
			String address = splitTeacher.next();
			String specialty = splitTeacher.next();
			String information = splitTeacher.next();
			firstName = spaceOut(firstName);
			lastName = spaceOut(lastName);
			address = spaceOut(address);
			specialty = spaceOut(specialty);
			information = spaceOut(information);
			dateOfbirth = spaceOut(dateOfbirth);
			listOfteacher.add(new teacher(id, password, listOfCourse,
					firstName, lastName, dateOfbirth, sex, telephone, address,
					specialty, information));
			splitTeacher.close();
		}
		/*
		 * } else { System.out
		 * .println("The text file does not contain any information"); }
		 */

		Collections.sort(listOfteacher);
		inFile.close();
		// splitTeacher.close();
		return listOfteacher;
	}

	public static ArrayList<student> readFileStudent()
	//Reads in all students in the data1 txt file
			throws FileNotFoundException {
		Scanner inFile = new Scanner(new File("data1.txt"));
		// Find how many questions in the data file
		ArrayList<student> listOfstudent = new ArrayList<student>();
		StringTokenizer split = new StringTokenizer(inFile.next(), " /", false);
		while (split.hasMoreTokens()) {
			String a = split.nextToken();
			StringTokenizer splitStudent = new StringTokenizer(a, ",", false);
			// Split the teacher object
			int id = Integer.parseInt(splitStudent.nextToken());
			int password = Integer.parseInt(splitStudent.nextToken());
			String b = splitStudent.nextToken();
			String c = splitStudent.nextToken();
			StringTokenizer splitCourse = new StringTokenizer(b, "|", false);
			ArrayList<Integer> listOfCourse = new ArrayList<Integer>();
			// System.out.println(b);
			while (splitCourse.hasMoreTokens()) {
				listOfCourse.add(Integer.parseInt(splitCourse.nextToken()));
			}

			StringTokenizer splitMarks = new StringTokenizer(c, "|", false);
			ArrayList<Double> listOfMarks = new ArrayList<Double>();
			// System.out.println(c);
			while (splitMarks.hasMoreTokens()) {
				listOfMarks.add(Double.parseDouble(splitMarks.nextToken()));
			}
			String firstName = splitStudent.nextToken();
			String lastName = splitStudent.nextToken();
			String dateOfbirth = splitStudent.nextToken();
			boolean sex = Boolean.parseBoolean(splitStudent.nextToken());
			int grade = Integer.parseInt(splitStudent.nextToken());
			int homeRoom = Integer.parseInt(splitStudent.nextToken());
			double telephone = Double.parseDouble(splitStudent.nextToken());
			double mobileTel = Double.parseDouble(splitStudent.nextToken());
			String address = splitStudent.nextToken();
			String email = splitStudent.nextToken();
			double volunteerHours = Double
					.parseDouble(splitStudent.nextToken());
			boolean literacyTest = Boolean.parseBoolean(splitStudent
					.nextToken());
			// String information = splitStudent.nextToken();
			firstName = spaceOut(firstName);
			lastName = spaceOut(lastName);
			address = spaceOut(address);
			email = spaceOut(email);
			dateOfbirth = spaceOut(dateOfbirth);

			listOfstudent.add(new student(id, password, listOfCourse,
					listOfMarks, firstName, lastName, dateOfbirth, sex, grade,
					telephone, mobileTel, email, address, volunteerHours,
					homeRoom, literacyTest));
			/*
			 * output = output+ "," + newone.firstName + "," +newone.lastName
			 * +"," +newone.dateOfbirth + ","+ newone.sex + "," + newone.grade +
			 * "," + newone.homeroom + "," + newone.avgMarks + "," +
			 * newone.telephone + "," + newone.mobileTel + "," + newone.address
			 * + "," +newone.email + "," + newone.volunteerHours + "," +
			 * newone.literacyTest + "/";
			 */
		}
		Collections.sort(listOfstudent);
		inFile.close();
		return listOfstudent;
	}

	public static ArrayList<course> readFileCourse()
	//Reads in all courses in the data2 txt file
			throws FileNotFoundException {
		ArrayList<course> listOfcourse = new ArrayList<course>();
		Scanner inFile = new Scanner(new File("data2.txt"));
		// Find how many questions in the data file
		StringTokenizer split = new StringTokenizer(inFile.next(), "/", false);
		while (split.hasMoreTokens()) {
			String a = split.nextToken();
			// System.out.println(a);
			// StringTokenizer splitCourse = new StringTokenizer(a, ",",
			// false);
			// Split the teacher object
			String[] splitCourse = a.split(",");
			// System.out.println(splitCourse[1]);
			int courseID = Integer.parseInt(splitCourse[0]);
			String courseName = splitCourse[1];
			int period = Integer.parseInt(splitCourse[2]);
			int roomNo = Integer.parseInt(splitCourse[3]);
			int teacherID = Integer.parseInt(splitCourse[4]);
			// String information = splitStudent.nextToken();
			String b = splitCourse[5];
			StringTokenizer splitStudent = new StringTokenizer(b, "|", false);
			ArrayList<Integer> listOfStudent = new ArrayList<Integer>();
			// System.out.println(b);

			while (splitStudent.hasMoreTokens()) {
				String number = splitStudent.nextToken();
				if (Integer.parseInt(number) != 0) {
					listOfStudent.add(Integer.parseInt(number));
				}
			}
			courseName = main.spaceOut(courseName);
			listOfcourse.add(new course(courseID, courseName, period, roomNo,
					teacherID, listOfStudent));
		}
		Collections.sort(listOfcourse);
		inFile.close();
		return listOfcourse;
	}

	public static void clearTeacher() throws IOException {
		//Deletes all teachers in the data txt file when changes are made
		PrintWriter writer = new PrintWriter("data.txt");
		writer.print("");
		writer.close();
	}

	public static void clearStudent() throws IOException {
		//Deletes data from the data1 txt file when changes are made
		PrintWriter writer = new PrintWriter("data1.txt");
		writer.print("");
		writer.close();
	}

	public static void clearCourse() throws IOException { 
		//Deletes the data2 txt file
		PrintWriter writer = new PrintWriter("data2.txt");
		writer.print("");
		writer.close();
	}

	public static String spaceOut(String input) { 
		//Change underscore to space when read in the data file
		String returner = "";
		if (input.indexOf("_") == -1)
			return input;
		else {
			returner = input.substring(0, input.indexOf("_")) + " "
					+ input.substring(input.indexOf("_") + 1, input.length());
			return spaceOut(returner);
		}
	}

	public static String spaceIn(String input) 
	//Change space to underscore when saving the data file
	{
		String returner = "";
		if (input.indexOf(" ") == -1)
			return input;
		else {
			returner = input.substring(0, input.indexOf(" ")) + "_"
					+ input.substring(input.indexOf(" ") + 1, input.length());
			return spaceIn(returner);
		}

	}

	public static boolean checkCourse(ArrayList<course> listOfcourse,
			ArrayList<Integer> course, int teacherId) {
		//Assures that there are no problems when assigning courses to teachers
		if (course.size() > 4) {
			System.out.println("Error - Too many courses!");
			return false;
		}
		for (int ctr = 0; ctr < course.size() - 1; ctr++) {
			for (int ctr2 = ctr + 1; ctr2 < course.size(); ctr2++) {
				if (course.get(ctr) == course.get(ctr2)) {
					System.out.println("Error - Duplicate courses!");
					return false;
				}
			}
		}
		for (int ctr3 = 0; ctr3 < course.size(); ctr3++) {
			course target = new course(course.get(ctr3));
			int index = listOfcourse.indexOf(target);
			// System.out.println(index);
			if (index == -1) {
				System.out.println("Error - One or more courses do not exist!");
				return false;
			}
			// System.out.println(listOfcourse.get(index).getTeacherID());
			if (listOfcourse.get(index).getTeacherID() != 0
					& listOfcourse.get(index).getTeacherID() != teacherId) {
				System.out
						.println("Error - This course already has a teacher!");
				return false;
			}
		}
		for (int p1 = 0; p1 < course.size() - 1; p1++) {
			for (int p2 = p1 + 1; p2 < course.size(); p2++) {
				course target1 = new course(course.get(p1));
				int index1 = listOfcourse.indexOf(target1);
				course target2 = new course(course.get(p2));
				int index2 = listOfcourse.indexOf(target2);
				if (listOfcourse.get(index1).getPeriod() == listOfcourse.get(
						index2).getPeriod()) {
					System.out
							.println("Two or more courses have the same period!");
					return false;
				}
			}
		}
		return true;
	}

	public static boolean roomConflict(int newPeriod, int newRoom)
			throws FileNotFoundException {
		//Checks to see if two courses have the same room during the same period
		ArrayList<course> courseList = readFileCourse();
		for (int ctr = 0; ctr < courseList.size(); ctr++) {
			if (courseList.get(ctr).roomNo == newRoom) {
				if (courseList.get(ctr).period == newPeriod) {
					System.out
							.println("Error - Another course uses this room for that period!");
					return false;
				}
			}
		}
		return true;
	}

	public static boolean checkCourseStudent(ArrayList<Integer> course)
			throws FileNotFoundException {
		//Checks for problems when assigning courses to students
		listOfcourse = readFileCourse();
		if (course.size() > 4) {
			System.out.println("Error - Too many courses!");
			return false;
		}
		for (int ctr = 0; ctr < course.size() - 1; ctr++) {
			for (int ctr2 = ctr + 1; ctr2 < course.size(); ctr2++) {
				if (course.get(ctr) == course.get(ctr2)) {
					System.out.println("Error - Duplicate courses!");
					return false;
				}
			}
		}
		for (int ctr3 = 0; ctr3 < course.size(); ctr3++) {
			course target = new course(course.get(ctr3));
			int index = listOfcourse.indexOf(target);
			// System.out.println(index);
			if (index == -1) {
				System.out.println("Error - One or more courses do not exist!");
				return false;
			}
			// System.out.println(listOfcourse.get(index).getTeacherID());
		}
		for (int p1 = 0; p1 < course.size() - 1; p1++) {
			for (int p2 = p1 + 1; p2 < course.size(); p2++) {
				course target1 = new course(course.get(p1));
				int index1 = listOfcourse.indexOf(target1);
				course target2 = new course(course.get(p2));
				int index2 = listOfcourse.indexOf(target2);
				if (listOfcourse.get(index1).getPeriod() == listOfcourse.get(
						index2).getPeriod()) {
					System.out
							.println("Two or more courses have the same period!");
					return false;
				}
			}
		}
		return true;
	}

	public static boolean checkTeacherID(int Id) throws FileNotFoundException {
		//Checks if a teacher ID exists
		listOfteacher = readFileTeacher();
		teacher target = new teacher(Id);
		int index = listOfteacher.indexOf(target);
		if (index != -1) {
			System.out.println("Id already exist!");
			return false;
		} else {
			return true;
		}

	}

	public static boolean checkStudentID(int Id) throws FileNotFoundException {
		//Checks for duplicate student numbers
		listOfstudent = readFileStudent();
		student target = new student(Id);
		Scanner scan = new Scanner(System.in);
		int index = listOfstudent.indexOf(target);
		if (index != -1) {
			System.out.println("Id already exist!");
			return false;
		} else {
			return true;
		}
	}

	public static boolean checkCourseID(int Id) throws FileNotFoundException {
		//Checks if a course ID already exists
		listOfcourse = readFileCourse();
		course target = new course(Id);
		Scanner scan = new Scanner(System.in);
		int index = listOfcourse.indexOf(target);
		if (index != -1) {
			System.out.println("Id already exist!");
			return false;
		} else {
			return true;
		}
	}

	public static void newTeacher() throws IOException, InterruptedException {
		//Creates new teacher data
		teacher newTeacher;
		teacher newTeacherOut;
		String firstName;
		String lastName;
		String address;
		String specialty;
		String information;
		int id = 0;
		ArrayList<Integer> courses;
		String dateOfbirth;
		Boolean sex;
		double telephone = 0;
		listOfcourse = readFileCourse();
		boolean x = false;
		boolean check2 = false;
		do {
			Scanner scan = new Scanner(System.in);
			do {
				try {
					System.out.println("Please input teacher's ID:");
					id = Integer.parseInt(scan.nextLine());
					isValidInteger = true;
				} catch (Exception e) {
					System.out
							.println("Error - Not a legal integer, please re-enter");
					isValidInteger = false;
				}
				try {
					check2 = true;
					check2 = checkTeacherID(id);
				} catch (Exception e) {
				}
			} while (!isValidInteger || !check2);
			// System.out.println("Please input teacher's password:");
			// int password = scan.nextInt();
			courses = new ArrayList<Integer>();
			check2 = false;
			do {
				try {
					courses.clear();
					System.out
							.println("Please input teacher's courses codes: (seperated by comma)");
					// teacher's schedule
					String total = scan.nextLine();
					StringTokenizer splitCourse = new StringTokenizer(total,
							",", false);
					while (splitCourse.hasMoreTokens()) {
						courses.add(Integer.parseInt(splitCourse.nextToken()));
					}
					isValidInteger = true;
					check2 = checkCourse(listOfcourse, courses, id);
				} catch (IllegalArgumentException e) {
					System.out
							.println("Error - Not a legal integer, please re-enter");
					isValidInteger = false;
				}
			} while (!isValidInteger || !check2);
			System.out.println("First Name:");
			firstName = scan.nextLine();
			System.out.println("Last Name:");
			lastName = scan.nextLine();
			System.out.println("Date of Birth:");
			dateOfbirth = scan.nextLine();
			System.out.println("Sex:(male or female)");
			sex = true;
			Boolean flag = false;
			do {
				String sexInput = scan.nextLine();
				flag = false;
				if (sexInput.equals("male"))
					sex = true;
				else if (sexInput.equals("female"))
					sex = false;
				else {
					System.out.println("Input not registered, try again!");
					flag = true;
				}
			} while (flag);
			System.out.println("Home address:");
			address = scan.nextLine();
			System.out.println("Specialty:");
			specialty = scan.nextLine();
			do {
				try {
					System.out.println("Telephone Number:");
					telephone = Double.parseDouble(scan.nextLine());
					isValidInteger = true;
				} catch (Exception e) {
					System.out
							.println("Error - Not a legal integer, please re-enter");
					isValidInteger = false;
				}
			} while (!isValidInteger);
			System.out.println("Please input teacher's additional information");
			information = scan.nextLine();
			newTeacher = new teacher(id, courses, firstName, lastName,
					dateOfbirth, sex, telephone, address, specialty,
					information);
			teacher.printTitle();
			System.out.println(newTeacher);
			boolean y = false;
			do {
				int check = 0;
				do {
					try {
						System.out
								.println("Please Confirm that the information above is accurate: Confirm(1); Redo(2); Exit(3)");
						check = Integer.parseInt(scan.nextLine());
						isValidInteger = true;
					} catch (Exception e) {
						System.out
								.println("Error - Not a legal integer, please re-enter");
						isValidInteger = false;
					}
				} while (!isValidInteger);
				if (check == 1) {
					x = false;
					y = false;
				} else if (check == 2) {
					x = true;
					y = false;
				} else if (check == 3) {
					x = true;
					y = true;
					return;
				} else {
				}
			} while (y);
		} while (x);
		firstName = spaceIn(firstName);
		lastName = spaceIn(lastName);
		dateOfbirth = spaceIn(dateOfbirth);
		address = spaceIn(address);
		specialty = spaceIn(specialty);
		information = spaceIn(information);
		newTeacherOut = new teacher(id, courses, firstName, lastName,
				dateOfbirth, sex, telephone, address, specialty, information);

		listOfcourse = readFileCourse();
		course.courseChange(courses, id, listOfcourse);

		Thread.sleep(REG_WAIT_TIME);
		teacher.log(newTeacherOut);
		System.out
				.println("The new teacher information is sucessfully registered.");
		System.out.println("");
	}

	public static void newStudent() throws IOException, InterruptedException {
		//Creates new student data
		student newStudent;
		String firstName;
		String lastName;
		String address;
		int id = 0;
		ArrayList<Integer> courses;
		String dateOfbirth;
		Boolean sex;
		double telephone = 0;
		double mobileTel = 0;
		double volunteerHours = 0;
		int grade = 0;
		Scanner scan = new Scanner(System.in);
		boolean x = false;
		boolean check2 = false;
		do {
			do {
				try {
					System.out.println("Please input student's ID:");
					id = Integer.parseInt(scan.nextLine());
					isValidInteger = true;
				} catch (Exception e) {
					System.out
							.println("Error - Not a legal integer, please re-enter");
					isValidInteger = false;
				}
				try {
					check2 = true;
					check2 = checkStudentID(id);

				} catch (Exception e) {
				}

			} while (!isValidInteger || !check2);
			// System.out.println("Please input student's password:");
			// int password = scan.nextInt();
			courses = new ArrayList<Integer>();
			do {
				try {
					courses.clear();
					System.out
							.println("Please input student's courses codes: (seperated by comma)");
					// teacher's schedule
					check2 = true;
					String total = scan.nextLine();
					StringTokenizer splitCourse = new StringTokenizer(total,
							",", false);
					while (splitCourse.hasMoreTokens()) {
						courses.add(Integer.parseInt(splitCourse.nextToken()));
					}
					isValidInteger = true;
					check2 = checkCourseStudent(courses);
				} catch (Exception e) {
					System.out
							.println("Error - Not a legal integer, please re-enter");
					isValidInteger = false;
				}
			} while (!isValidInteger || !check2);
			System.out.println("First Name:");
			firstName = scan.nextLine();
			System.out.println("Last Name:");
			lastName = scan.nextLine();
			System.out.println("Date of Birth:");
			dateOfbirth = scan.nextLine();
			System.out.println("Sex:(male or female)");
			sex = true;
			Boolean flag = false;
			do {
				flag = false;
				String sexInput = scan.nextLine();
				if (sexInput.equals("male"))
					sex = true;
				else if (sexInput.equals("female"))
					sex = false;
				else {
					System.out.println("Input not registered");
					flag = true;
				}
			} while (flag);
			do {
				try {
					System.out.println("This student's current grade:");
					grade = Integer.parseInt(scan.nextLine());
					isValidInteger = true;
				} catch (Exception e) {
					System.out
							.println("Error - Not a legal integer, please re-enter");
					isValidInteger = false;
					grade = 9;
				}
				if (grade > 12 || grade < 9) {
					System.out.println("Must be grade 9-12");
					isValidInteger = false;
				}
			} while (!isValidInteger);

			System.out.println("Home address:");
			address = scan.nextLine();
			System.out.println("Email:");
			String email = scan.nextLine();
			do {
				try {
					System.out.println("Telephone Number:");
					telephone = Double.parseDouble(scan.nextLine());
					isValidInteger = true;
				} catch (Exception e) {
					System.out
							.println("Error - Not a legal integer, please re-enter");
					isValidInteger = false;
				}
			} while (!isValidInteger);

			do {
				try {
					System.out.println("Mobile telephone number:");
					mobileTel = Double.parseDouble(scan.nextLine());
					isValidInteger = true;
				} catch (Exception e) {
					System.out
							.println("Error - Not a legal integer, please re-enter");
					isValidInteger = false;
				}
			} while (!isValidInteger);
			do {
				try {
					System.out.println("Current volunteer hours:");
					volunteerHours = Double.parseDouble(scan.nextLine());
					isValidInteger = true;
				} catch (Exception e) {
					System.out
							.println("Error - Not a legal integer, please re-enter");
					isValidInteger = false;
				}
			} while (!isValidInteger);
			boolean literacyTest = false;
			boolean stillOn = true;
			System.out.println("Has this student passed the literacy test?");
			do {
				String yesNo = scan.next();
				if (yesNo.equals("yes")) {
					literacyTest = true;
					stillOn = false;
				} else if (yesNo.equals("no")) {
					literacyTest = false;
					stillOn = false;
				} else
					System.out.println("Input not registered");
			} while (stillOn);
			String space = scan.nextLine();
			ArrayList<Double> marks = new ArrayList<Double>(0);
			for (int y = 0; y < courses.size(); y++) {
				marks.add(0.0);
			}
			newStudent = new student(id, courses, marks, firstName, lastName,
					dateOfbirth, sex, grade, telephone, mobileTel, email,
					address, volunteerHours, literacyTest);
			// confirmation
			student.printTitle();
			System.out.println(newStudent);
			boolean y = false;
			int check = 0;
			do {
				do {
					try {
						System.out
								.println("Please Confirm that the information above is accurate: Confirm(1); Redo(2); Exit(3)");
						check = Integer.parseInt(scan.nextLine());
						isValidInteger = true;
					} catch (Exception e) {
						System.out
								.println("Error - Not a legal integer, please re-enter");
						isValidInteger = false;
					}
				} while (!isValidInteger);
				if (check == 1) {
					x = false;
					y = false;
				} else if (check == 2) {
					x = true;
					y = false;
				} else if (check == 3) {
					x = true;
					y = true;
					return;
				} else {
				}
			} while (y);
		} while (x);
		student.log(newStudent);
		listOfcourse = readFileCourse();
		course.studentChange(courses, id, listOfcourse);
		Thread.sleep(REG_WAIT_TIME);
		System.out
				.println("The new student information is sucessfully registered.");
		System.out.println("");
	}

	public static void newCourse() throws IOException, InterruptedException {
		//Creates new course data 
		Scanner scan = new Scanner(System.in);
		int courseID = 0;
		int period = 3;
		course newCourse;
		int roomNo = 0;
		boolean x = false;
		boolean check2 = false;
		do {
			do {
				try {
					System.out.println("Enter the course ID number:");
					courseID = Integer.parseInt(scan.nextLine());
					isValidInteger = true;
					check2 = checkCourseID(courseID);
				} catch (Exception e) {
					System.out
							.println("Error - Not a legal integer, please re-enter");
					isValidInteger = false;
				}
			} while (!isValidInteger || !check2);

			System.out.println("Enter the course's name:");
			String courseName = scan.nextLine();
			boolean noConflict;
			do {
				do {
					try {
						System.out.println("Enter the period of this course:");
						period = Integer.parseInt(scan.nextLine());
						isValidInteger = true;
					} catch (Exception e) {
						System.out
								.println("Error - Not a legal integer, please re-enter");
						isValidInteger = false;
						period = 3;
					}
					if (period > 5 || period < 1) {
						System.out.println("Must be period 1-5");
						isValidInteger = false;
					}
				} while (!isValidInteger);

				do {
					try {
						System.out
								.println("Enter the room number for the course:");
						roomNo = Integer.parseInt(scan.nextLine());
						isValidInteger = true;
					} catch (Exception e) {
						System.out
								.println("Error - Not a legal integer, please re-enter");
						isValidInteger = false;
					}
				} while (!isValidInteger);
				noConflict = roomConflict(period, roomNo);
			} while (!noConflict);
			// System.out.println("Enter the assigned teacher's ID number:");
			// int teacherID = courseIn.nextInt();
			newCourse = new course(courseID, courseName, period, roomNo);
			course.printTitle();
			System.out.println(newCourse);
			boolean y = false;
			int check = 0;
			do {
				do {
					try {
						System.out
								.println("Please Confirm that the information above is accurate: Confirm(1); Redo(2); Exit(3)");
						check = Integer.parseInt(scan.nextLine());
						isValidInteger = true;
					} catch (Exception e) {
						System.out
								.println("Error - Not a legal integer, please re-enter");
						isValidInteger = false;
					}
				} while (!isValidInteger);
				if (check == 1) {
					x = false;
					y = false;
				} else if (check == 2) {
					x = true;
					y = false;
				} else if (check == 3) {
					x = true;
					y = true;
					return;
				} else {
				}
			} while (y);
		} while (x);
		course.log(newCourse);
		Thread.sleep(REG_WAIT_TIME);
		System.out
				.println("The new course information is sucessfully registered.");
		System.out.println("");
	}

	public static void editTeacher() throws IOException, InterruptedException {
		//Deletes and rewrites the data text file
		Scanner scan = new Scanner(System.in);
		listOfteacher = readFileTeacher();
		listOfcourse = readFileCourse();
		teacher target = new teacher(1);
		int id = 0;
		boolean flag = true;
		int index = 0;
		do {
			try {
				System.out
						.println("Please input the teacher's ID that you want to modify");
				id = Integer.parseInt(scan.nextLine());
				isValidInteger = true;
			} catch (Exception e) {
				System.out
						.println("Error - Not a legal integer, please re-enter");
				isValidInteger = false;
			}
		} while (!isValidInteger);
		do {
			target = new teacher(id);
			index = listOfteacher.indexOf(target);
			do {
				try {
					if (index == -1) {
						System.out
								.println("Id is not found in date base, please try again or exist(0)");
						flag = true;
						id = Integer.parseInt(scan.nextLine());
						isValidInteger = true;
					} else {
						flag = false;
						isValidInteger = true;
					}
				} catch (Exception e) {
					System.out
							.println("Error - Not a legal integer, please re-enter");
					isValidInteger = false;
				}
			} while (!isValidInteger);

			if (id == 0) {
				flag = false;
				return;
			}
			// System.out.println(listOfteacher.get(index));
		} while (flag);

		// //////////////////////////////////////////////
		teacher newTeacher;
		teacher newTeacherOut;
		String firstName;
		String lastName;
		String address;
		String specialty;
		String information;
		ArrayList<Integer> courses;
		String dateOfbirth;
		Boolean sex;
		double telephone = 0;
		boolean x = false;
		boolean check2 = false;
		do {
			do {
				try {
					System.out.println("Please input teacher's new id:");
					id = Integer.parseInt(scan.nextLine());
					isValidInteger = true;
				} catch (Exception e) {
					System.out
							.println("Error - Not a legal integer, please re-enter");
					isValidInteger = false;
				}
			} while (!isValidInteger);
			// System.out.println("Please input teacher's password:");
			// int password = scan.nextInt();
			courses = new ArrayList<Integer>();
			do {
				try {
					courses.clear();
					System.out
							.println("Please input teacher's courses codes: (seperated by comma)");
					// teacher's schedule
					String total = scan.nextLine();
					StringTokenizer splitCourse = new StringTokenizer(total,
							",", false);
					while (splitCourse.hasMoreTokens()) {
						courses.add(Integer.parseInt(splitCourse.nextToken()));
					}
					check2 = checkCourse(listOfcourse, courses, id);
					isValidInteger = true;
				} catch (Exception e) {
					System.out
							.println("Error - Not a legal integer, please re-enter");
					isValidInteger = false;
				}
			} while (!isValidInteger || !check2);
			System.out.println("First Name:");
			firstName = scan.nextLine();
			System.out.println("Last Name:");
			lastName = scan.nextLine();
			System.out.println("Date of Birth:");
			dateOfbirth = scan.nextLine();
			System.out.println("Sex:(male or female)");
			sex = true;
			flag = false;
			do {
				String sexInput = scan.nextLine();
				flag = false;
				if (sexInput.equals("male"))
					sex = true;
				else if (sexInput.equals("female"))
					sex = false;
				else {
					System.out.println("input not registered, try again!");
					flag = true;
				}
			} while (flag);
			System.out.println("Home address:");
			address = scan.nextLine();
			System.out.println("Specialty:");
			specialty = scan.nextLine();
			do {
				try {
					System.out.println("Telephone Number:");
					telephone = Double.parseDouble(scan.nextLine());
					isValidInteger = true;
				} catch (Exception e) {
					System.out
							.println("Error - Not a legal integer, please re-enter");
					isValidInteger = false;
				}
			} while (!isValidInteger);
			System.out.println("Please input teacher's addtional information");
			information = scan.nextLine();
			newTeacher = new teacher(id, courses, firstName, lastName,
					dateOfbirth, sex, telephone, address, specialty,
					information);
			teacher.printTitle();
			System.out.println(newTeacher);
			boolean y = false;
			int check = 0;
			do {
				do {
					try {

						System.out
								.println("Please Confirm that the information above is accurate: Confrim(1); Redo(2); Exit(3)");
						isValidInteger = true;
						check = Integer.parseInt(scan.nextLine());

					} catch (Exception e) {
						System.out
								.println("Error - Not a legal integer, please re-enter");
						isValidInteger = false;
					}
				} while (!isValidInteger);
				if (check == 1) {
					x = false;
					y = false;
				} else if (check == 2) {
					x = true;
					y = false;
				} else if (check == 3) {
					x = true;
					y = true;
					return;
				} else {
				}
			} while (y);// ////Don't need this loop
		} while (x);
		firstName = spaceIn(firstName);
		lastName = spaceIn(lastName);
		dateOfbirth = spaceIn(dateOfbirth);
		address = spaceIn(address);
		specialty = spaceIn(specialty);
		information = spaceIn(information);
		listOfteacher.get(index).teacherChange(id, courses, firstName,
				lastName, dateOfbirth, sex, telephone, address, specialty,
				information);
		Thread.sleep(REG_WAIT_TIME);

		// erase teacherId from old courses;

		listOfcourse = readFileCourse();
		course.courseChange(courses, id, listOfcourse);

		clearTeacher();
		for (teacher teacherOut : listOfteacher) {
			teacher.log(teacherOut);
		}
		System.out.println("The teacher information is sucessfully updated.");
		System.out.println("");

	}

	public static void editStudent() throws IOException, InterruptedException {
		//Deletes and rewrites the data1 text file
		Scanner scan = new Scanner(System.in);
		listOfstudent = readFileStudent();
		student target = new student(1);
		int id = 0;
		boolean flag = true;
		int index = 0;
		do {
			try {
				System.out
						.println("Please input the student's ID that you want to modify");
				id = Integer.parseInt(scan.nextLine());
				isValidInteger = true;
			} catch (Exception e) {
				System.out
						.println("Error - Not a legal integer, please re-enter");
				isValidInteger = false;
			}
		} while (!isValidInteger);
		do {
			target = new student(id);
			index = listOfstudent.indexOf(target);
			do {
				try {
					if (index == -1) {
						System.out
								.println("Student Id is not found in database, please try again or exist(0)");
						flag = true;
						id = Integer.parseInt(scan.nextLine());
						isValidInteger = true;
					} else {
						flag = false;
						isValidInteger = true;
					}
				} catch (Exception e) {
					System.out
							.println("Error - Not a legal integer, please re-enter");
					isValidInteger = false;
				}
			} while (!isValidInteger);

			if (id == 0) {
				flag = false;
				return;
			}
		} while (flag);

		// //////////////////////////////////////////////

		student newStudent;
		String firstName;
		String lastName;
		String address;
		ArrayList<Integer> courses;
		String dateOfbirth;
		Boolean sex;
		double telephone = 0;
		double mobileTel = 0;
		double volunteerHours = 0;
		int grade = 0;
		String email = "";
		boolean x = false;
		boolean literacyTest = false;
		do {
			do {
				try {
					System.out.println("Please input student's new id:");
					id = Integer.parseInt(scan.nextLine());
					isValidInteger = true;
				} catch (Exception e) {
					System.out
							.println("Error - Not a legal integer, please re-enter");
					isValidInteger = false;
				}
			} while (!isValidInteger);
			// System.out.println("Please input student's password:");
			// int password = scan.nextInt();
			courses = new ArrayList<Integer>();
			do {
				try {
					System.out
							.println("Please input student's new courses codes: (seperated by comma)");
					// teacher's schedule
					String total = scan.nextLine();
					StringTokenizer splitCourse = new StringTokenizer(total,
							",", false);
					while (splitCourse.hasMoreTokens()) {
						courses.add(Integer.parseInt(splitCourse.nextToken()));
					}
					isValidInteger = true;
				} catch (Exception e) {
					System.out
							.println("Error - Not a legal integer, please re-enter");
					isValidInteger = false;
				}
			} while (!isValidInteger);
			System.out.println("First Name:");
			firstName = scan.nextLine();
			System.out.println("Last Name:");
			lastName = scan.nextLine();
			System.out.println("Date of Birth:");
			dateOfbirth = scan.nextLine();
			System.out.println("Sex:(male or female)");
			sex = true;
			flag = false;
			do {
				flag = false;
				String sexInput = scan.nextLine();
				if (sexInput.equals("male"))
					sex = true;
				else if (sexInput.equals("female"))
					sex = false;
				else {
					System.out.println("input not registered ");
					flag = true;
				}
			} while (flag);
			do {
				try {
					System.out.println("This student's current grade:");
					grade = Integer.parseInt(scan.nextLine());
					isValidInteger = true;
				} catch (Exception e) {
					System.out
							.println("Error - Not a legal integer, please re-enter");
					isValidInteger = false;
					grade = 9;
				}
				if (grade > 12 || grade < 9) {
					System.out.println("Must be grade 9-12");
					isValidInteger = false;
				}
			} while (!isValidInteger);

			System.out.println("Home address:");
			address = scan.nextLine();
			System.out.println("Email:");
			email = scan.nextLine();
			do {
				try {
					System.out.println("Telephone Number:");
					telephone = Double.parseDouble(scan.nextLine());
					isValidInteger = true;
				} catch (Exception e) {
					System.out
							.println("Error - Not a legal integer, please re-enter");
					isValidInteger = false;
				}
			} while (!isValidInteger);

			do {
				try {
					System.out.println("Mobile telephone number:");
					mobileTel = Double.parseDouble(scan.nextLine());
					isValidInteger = true;
				} catch (Exception e) {
					System.out
							.println("Error - Not a legal integer, please re-enter");
					isValidInteger = false;
				}
			} while (!isValidInteger);
			do {
				try {
					System.out.println("Current volunteer hours:");
					volunteerHours = Double.parseDouble(scan.nextLine());
					isValidInteger = true;
				} catch (Exception e) {
					System.out
							.println("Error - Not a legal integer, please re-enter");
					isValidInteger = false;
				}
			} while (!isValidInteger);
			literacyTest = false;
			boolean stillOn = true;
			System.out.println("Has this student passed the literacy test?");
			do {
				String yesNo = scan.next();
				if (yesNo.equals("yes")) {
					literacyTest = true;
					stillOn = false;
				} else if (yesNo.equals("no")) {
					literacyTest = false;
					stillOn = false;
				} else
					System.out.println("Input not registered");
			} while (stillOn);
			String space = scan.nextLine();
			ArrayList<Double> marks = new ArrayList<Double>(0);
			for (int y = 0; y < courses.size(); y++) {
				marks.add(0.0);
			}
			newStudent = new student(id, courses, marks, firstName, lastName,
					dateOfbirth, sex, grade, telephone, mobileTel, email,
					address, volunteerHours, literacyTest);
			// confirmation
			student.printTitle();
			System.out.println(newStudent);
			boolean y = false;
			int check = 0;
			do {
				do {
					try {
						System.out
								.println("Please Confirm that the information above is accurate: Confrim(1); Redo(2); Exit(3)");
						check = Integer.parseInt(scan.nextLine());
						isValidInteger = true;

					} catch (Exception e) {
						System.out
								.println("Error - Not a legal integer, please re-enter");
						isValidInteger = false;
					}
				} while (!isValidInteger);

				if (check == 1) {
					x = false;
					y = false;
				} else if (check == 2) {
					x = true;
					y = false;
				} else if (check == 3) {
					x = true;
					y = true;
					return;
				} else {
				}
			} while (y);
		} while (x);
		listOfstudent.get(index).studentChange(id, courses, firstName,
				lastName, dateOfbirth, sex, grade, telephone, mobileTel, email,
				address, volunteerHours, literacyTest);
		Thread.sleep(REG_WAIT_TIME);

		clearStudent();
		// erase studentId from old courses;
		listOfcourse = readFileCourse();
		course.studentChange(courses, id, listOfcourse);

		for (student studentOut : listOfstudent) {
			student.log(studentOut);
		}

		System.out.println("The student information is sucessfully updated.");
		System.out.println("");

	}

	public static void editCourse() throws InterruptedException, IOException {
		//Deletes and rewrites the data2 text file
		Scanner scan = new Scanner(System.in);
		listOfcourse = readFileCourse();
		course target = new course(1);
		int id = 0;
		boolean flag;
		int index = 0;
		do {
			try {
				System.out
						.println("Please input the course's ID that you want to modify");
				id = Integer.parseInt(scan.nextLine());
				isValidInteger = true;
			} catch (Exception e) {
				System.out
						.println("Error - Not a legal integer, please re-enter");
				isValidInteger = false;
			}
		} while (!isValidInteger);
		do {
			target = new course(id);
			index = listOfcourse.indexOf(target);
			if (index == -1) {
				System.out
						.println("Course Id is not found in database, please try again or exist(0)");
				flag = true;
				index = Integer.parseInt(scan.nextLine()) - 1;
			}
			if (index == -1) {
				flag = false;
				return;
			} else {
				flag = false;
				// System.out.println(listOfteacher.get(index));
			}
		} while (flag);

		// //////////////////////////////////////////////
		// int courseID=0;
		int period = 3;
		course newCourse;
		int roomNo = 0;
		String courseName = "";
		boolean x = false;
		do {
			System.out.println("Enter the course's name:");
			courseName = scan.nextLine();
			do {
				try {
					System.out.println("Enter the period of this course:");
					period = Integer.parseInt(scan.nextLine());
					isValidInteger = true;
				} catch (Exception e) {
					System.out
							.println("Error - Not a legal integer, please re-enter");
					isValidInteger = false;
					period = 3;
				}
				if (period > 5 || period < 1) {
					System.out.println("Must be period 1-5");
					isValidInteger = false;
				}
			} while (!isValidInteger);
			do {
				try {
					System.out.println("Enter the room number for the course:");
					roomNo = Integer.parseInt(scan.nextLine());
					isValidInteger = true;
				} catch (Exception e) {
					System.out
							.println("Error - Not a legal integer, please re-enter");
					isValidInteger = false;
				}
			} while (!isValidInteger);
			// System.out.println("Enter the assigned teacher's ID number:");
			// int teacherID = courseIn.nextInt();
			newCourse = new course(listOfcourse.get(index).getID(), courseName,
					period, roomNo);
			course.printTitle();
			System.out.println(newCourse);
			boolean y = false;
			int check = 0;
			do {
				do {
					try {
						System.out
								.println("Please Confirm that the updated information above is accurate: Confrim(1); Redo(2); Exit(3)");
						check = Integer.parseInt(scan.nextLine());
						isValidInteger = true;
					} catch (Exception e) {
						System.out
								.println("Error - Not a legal integer, please re-enter");
						isValidInteger = false;
					}
				} while (!isValidInteger);
				if (check == 1) {
					x = false;
					y = false;
				} else if (check == 2) {
					x = true;
					y = false;
				} else if (check == 3) {
					x = true;
					y = true;
					return;
				} else {
				}
			} while (y);
		} while (x);
		;
		Thread.sleep(REG_WAIT_TIME);
		listOfcourse.get(index).courseChange(listOfcourse.get(index).getID(),
				courseName, period, roomNo);
		clearCourse();

		for (course courseOut : listOfcourse) {
			course.log(courseOut);
		}

		System.out.println("The course information is sucessfully updated.");
		System.out.println("");
	}

	public static void deleteTeacher() throws IOException {
		//Asks for the teacher you wish to delete
		Scanner scan = new Scanner(System.in);
		listOfteacher = readFileTeacher();
		listOfcourse = readFileCourse();
		boolean x = false;
		int index = 0;
		int id = 0;
		do {
			teacher target = new teacher(1);
			boolean flag = true;
			do {
				try {
					System.out
							.println("Please input the teacher's ID that you want to modify");
					id = Integer.parseInt(scan.nextLine());
					isValidInteger = true;
				} catch (Exception e) {
					System.out
							.println("Error - Not a legal integer, please re-enter");
					isValidInteger = false;
				}
			} while (!isValidInteger);
			do {
				target = new teacher(id);
				index = listOfteacher.indexOf(target);
				do {
					try {
						if (index == -1) {
							System.out
									.println("Id is not found in database, please try again or exist(0)");
							flag = true;
							id = Integer.parseInt(scan.nextLine());
							isValidInteger = true;
						} else {
							flag = false;
							isValidInteger = true;
						}
					} catch (Exception e) {
						System.out
								.println("Error - Not a legal integer, please re-enter");
						isValidInteger = false;
					}
				} while (!isValidInteger);

				if (id == 0) {
					flag = false;
					return;
				}
			} while (flag);

			// ////
			teacher.printTitle();
			System.out.println(listOfteacher.get(index));
			int check = 0;
			do {
				try {
					System.out
							.println("Please Confirm that you want to delete above information: Confrim(1); Redo(2); Exit(3)");
					check = Integer.parseInt(scan.nextLine());
					isValidInteger = true;
				} catch (Exception e) {
					System.out
							.println("Error - Not a legal integer, please re-enter");
					isValidInteger = false;
				}
			} while (!isValidInteger);
			if (check == 1) {
				x = false;

			} else if (check == 2) {
				x = true;

			} else if (check == 3) {
				x = true;

				return;
			} else {
			}
		} while (x);

		// ////
		course.courseChange(listOfteacher.get(index).getCourseId(), 0,
				listOfcourse);

		listOfteacher.remove(index);
		// ///
		clearTeacher();
		for (teacher teacherOut : listOfteacher) {
			teacher.log(teacherOut);
		}
		System.out.println("The teacher information is sucessfully updated.");
		System.out.println("");

		// ///////////////////Problem: delete the id from course!!!!!

	}

	public static void deleteStudent() throws IOException {
		//Asks for the student you wish to delete
		Scanner scan = new Scanner(System.in);
		listOfstudent = readFileStudent();
		listOfcourse = readFileCourse();
		boolean x = false;
		int index = 0;
		int id = 0;
		do {
			student target = new student(1);

			boolean flag = true;
			do {
				try {
					System.out
							.println("Please input the student's ID that you want to modify");
					id = Integer.parseInt(scan.nextLine());
					isValidInteger = true;
				} catch (Exception e) {
					System.out
							.println("Error - Not a legal integer, please re-enter");
					isValidInteger = false;
				}
			} while (!isValidInteger);
			do {
				target = new student(id);
				index = listOfstudent.indexOf(target);
				do {
					try {
						if (index == -1) {
							System.out
									.println("Id is not found in database, please try again or exist(0)");
							flag = true;
							id = Integer.parseInt(scan.nextLine());
							isValidInteger = true;
						} else {
							flag = false;
							isValidInteger = true;
						}
					} catch (Exception e) {
						System.out
								.println("Error - Not a legal integer, please re-enter");
						isValidInteger = false;
					}
				} while (!isValidInteger);

				if (id == 0) {
					flag = false;
					return;
				}
			} while (flag);
			// ////
			student.printTitle();
			System.out.println(listOfstudent.get(index));
			int check = 0;
			do {
				try {
					System.out
							.println("Please Confirm that you want to delete above information: Confrim(1); Redo(2); Exit(3)");
					isValidInteger = true;
					check = Integer.parseInt(scan.nextLine());

				} catch (Exception e) {
					System.out
							.println("Error - Not a legal integer, please re-enter");
					isValidInteger = false;
				}
			} while (!isValidInteger);

			if (check == 1) {
				x = false;

			} else if (check == 2) {
				x = true;

			} else if (check == 3) {
				x = true;

				return;
			} else {
			}
		} while (x);

		// ////
		course.studentRemove(listOfstudent.get(index).getCourseId(), id,
				listOfcourse);

		listOfstudent.remove(index);
		// ///
		clearStudent();
		for (student studentOut : listOfstudent) {
			student.log(studentOut);
		}
		System.out.println("The student information is sucessfully updated.");
		System.out.println("");
	}

	public static void editFromTeacher(teacher self) throws IOException {
		//Allows teacher to modify some of their data
		Scanner scan = new Scanner(System.in);
		listOfteacher = readFileTeacher();
		boolean flag;
		String firstName;
		String lastName;
		String address;
		String specialty;
		String information;
		String dateOfbirth;
		Boolean sex;
		double telephone = 0;
		boolean x = false;
		do {
			System.out.println("First Name:");
			firstName = scan.nextLine();
			System.out.println("Last Name:");
			lastName = scan.nextLine();
			System.out.println("Date of Birth:");
			dateOfbirth = scan.nextLine();
			System.out.println("Sex:(male or female)");
			sex = true;
			flag = false;
			do {
				String sexInput = scan.nextLine();
				flag = false;
				if (sexInput.equals("male"))
					sex = true;
				else if (sexInput.equals("female"))
					sex = false;
				else {
					System.out.println("Input not registered, try again!");
					flag = true;
				}
			} while (flag);
			System.out.println("Home address:");
			address = scan.nextLine();
			System.out.println("Specialty:");
			specialty = scan.nextLine();
			do {
				try {
					System.out.println("Telephone Number:");
					telephone = Double.parseDouble(scan.nextLine());
					isValidInteger = true;
				} catch (Exception e) {
					System.out
							.println("Error - Not a legal integer, please re-enter");
					isValidInteger = false;
				}
			} while (!isValidInteger);
			System.out.println("Please input teacher's addtional information");
			information = scan.nextLine();
			self.teacherChange(firstName, lastName, dateOfbirth, sex,
					telephone, address, specialty, information);
			teacher.printTitle();
			System.out.println(self);
			boolean y = false;
			int check = 0;
			do {
				do {
					try {
						System.out
								.println("Please Confirm that the information above is accurate: Confirm(1); Redo(2); Exit(3)");
						isValidInteger = true;
						check = Integer.parseInt(scan.nextLine());

					} catch (Exception e) {
						System.out
								.println("Error - Not a legal integer, please re-enter");
						isValidInteger = false;
					}
				} while (!isValidInteger);
				if (check == 1) {
					x = false;
					y = false;
				} else if (check == 2) {
					x = true;
					y = false;
				} else if (check == 3) {
					x = true;
					y = true;
					return;
				} else {
				}
			} while (y);
		} while (x);

		clearTeacher();
		for (teacher teacherOut : listOfteacher) {
			teacher.log(teacherOut);
		}
		System.out.println("The teacher information is sucessfully updated.");
		System.out.println("");
	}

	public static void admin() throws IOException, InterruptedException {
		//Holds all admin functions
		Scanner scan = new Scanner(System.in);
		int x = 0;
		while (x == 0) {
			int choice = 0;
			int edit = 0;
			do {
				try {
					System.out
							.println("Check existing teacher profile (1), Create new teacher profile(2), Check existing student profiles(3), ");
					System.out
							.println("Create new student profile(4), Check existing courses(5), Create new courses(6), Change Password (7), ");
					System.out.println("or Log out (8)");
					choice = Integer.parseInt(scan.nextLine());
					isValidInteger = true;
				} catch (Exception e) {
					System.out
							.println("Error - Not a legal integer, please re-enter");
					isValidInteger = false;
				}
			} while (!isValidInteger);

			if (choice == 1) {
				// Read from the data // Create an array to hold teachers
				try {
					listOfteacher = readFileTeacher();
					inputAvailable = true;
				} catch (Exception e) {
					System.out.println("");
					System.out
							.println("Error - The text file does not contain any information");
					System.out.println("");
					inputAvailable = false;
				}

				if (inputAvailable) {
					teacher.printTitle();
					for (teacher thisTeacher : listOfteacher) {
						System.out.println(thisTeacher);
					}
					do {
						try {
							System.out
									.println("Do you want to edit existing teacher profile (1) delete teacher profile (2) or exit to the main menu (3)");
							edit = Integer.parseInt(scan.nextLine());
							isValidInteger = true;
						} catch (Exception e) {
							System.out
									.println("Error - Not a legal integer, please re-enter");
							isValidInteger = false;
						}
					} while (!isValidInteger);
					if (edit == 1) {
						editTeacher();
					} else if (edit == 2) {
						deleteTeacher();
					}
				}
			} else if (choice == 2) {
				newTeacher();
			} else if (choice == 3) {
				try {
					listOfstudent = readFileStudent();
					inputAvailable = true;
				} catch (Exception e) {
					System.out.println("");
					System.out
							.println("Error - The text file does not contain any information");
					System.out.println("");
					inputAvailable = false;
				}

				if (inputAvailable) {
					student.printTitle();
					for (student thisStudent : listOfstudent) {
						System.out.println(thisStudent);
					}
					int edit1 = 0;
					do {
						try {
							System.out.println("");
							System.out
									.println("Do you want to edit existing Student profile (1), delete student profile (2) or exit to the main menu (3)");
							edit1 = Integer.parseInt(scan.nextLine());
							isValidInteger = true;
						} catch (Exception e) {
							System.out
									.println("Error - Not a legal integer, please re-enter");
							isValidInteger = false;
						}
					} while (!isValidInteger);
					if (edit1 == 1) {
						editStudent();
					} else if (edit1 == 2) {
						deleteStudent();
					}
				}
			} else if (choice == 4) {
				newStudent();
			} else if (choice == 5) {
				try {
					listOfcourse = readFileCourse();
					inputAvailable = true;
				} catch (Exception e) {
					System.out.println("");
					System.out
							.println("Error - The text file does not contain any information");
					System.out.println("");
					inputAvailable = false;
				}
				course.printTitle();
				for (course thisCourse : listOfcourse) {
					System.out.println(thisCourse);
				}
				int edit1 = 0;
				do {
					try {
						System.out
								.println("Do you want to edit an existing course profile (1) or exit to the main menu (2)");
						edit1 = Integer.parseInt(scan.nextLine());
						isValidInteger = true;
					} catch (Exception e) {
						System.out
								.println("Error - Not a legal integer, please re-enter");
						isValidInteger = false;
					}
				} while (!isValidInteger);
				if (edit1 == 1) {
					editCourse();
				}
			} else if (choice == 6) {
				newCourse();
			} else if (choice == 7) {
				changeAdminPassword();
			} else if (choice == 8) {
				System.out.println("You have logged out.");
				break;
			} else {
				System.out
						.println("Your input is not valid, please try again.");
			}
		}
	}

	public static void checkStudentProfile(teacher self) throws IOException,
	//Displays student data to a teacher
			InterruptedException {
		listOfcourse = readFileCourse();
		listOfstudent = readFileStudent();
		Scanner scan = new Scanner(System.in);
		ArrayList<Integer> courses = self.getCourseId();
		System.out
				.println("______________________________________________________"
						+ "_______________________________________________________________"
						+ "_____________________________________________________________"
						+ "_____________________________________________________________");
		for (int courseId : courses) {
			course target = new course(courseId);
			int index = listOfcourse.indexOf(target);
			target = listOfcourse.get(index);
			System.out.println(target.printCourseTitle());
			student.printTitle2();
			if (target.getStudentID().size() == 0) {
				System.out.println("No student in this course yet");
				System.out.println("");
			} else {
				for (int studentId : target.getStudentID()) {
					student target2 = new student(studentId);
					int index2 = listOfstudent.indexOf(target2);
					target2 = listOfstudent.get(index2);
					System.out.println(target2.printStudent());
				}
			}
			System.out
					.println("______________________________________________________"
							+ "_______________________________________________________________"
							+ "_____________________________________________________________"
							+ "_____________________________________________________________");
		}
		System.out.println("");
	}

	public static void inputMarks(teacher self) throws IOException,
			InterruptedException {
		//Allows student marks to be changed
		listOfcourse = readFileCourse();
		listOfstudent = readFileStudent();
		Scanner scan = new Scanner(System.in);
		ArrayList<Integer> courses = self.getCourseId();
		int courseId = 0;
		int studentId = 0;
		boolean check = true;
		do {
			do {
				try {
					System.out.println("Please enter the student's course ID");
					courseId = Integer.parseInt(scan.nextLine());
					isValidInteger = true;
				} catch (Exception e) {
					System.out
							.println("Error - Not a legal integer, please re-enter");
					isValidInteger = false;
				}
			} while (!isValidInteger);
			// check this course is the teache's course
			/*
			 * for(int x=0; x<self.getCourseId().size();x++){
			 * if(x==(self.getCourseId().size()-1)
			 * &&courseId!=self.getCourseId().get(x)){ System.out
			 * .println("This is not a valid course ID"); }
			 * 
			 * }
			 */
			check = true;
			if (!self.getCourseId().contains(courseId)) {
				System.out
						.println("Error -This is not one of your course ID, Please enter again");
				check = false;
			}
		} while (!check);

		course target = new course(courseId);
		int index = listOfcourse.indexOf(target);
		target = listOfcourse.get(index);
		do {
			do {
				try {
					System.out.println("Please enter the student's ID number");
					studentId = Integer.parseInt(scan.nextLine());
					isValidInteger = true;
				} catch (Exception e) {
					System.out
							.println("Error - Not a legal integer, please re-enter");
					isValidInteger = false;
				}
			} while (!isValidInteger);

			if (!target.getStudentID().contains(studentId)) {
				System.out.println("This is not a valid student ID");
				check = false;
			}
		} while (!check);
		student targetStudent = new student(studentId);
		int index1 = listOfstudent.indexOf(targetStudent);
		targetStudent = listOfstudent.get(index1);
		ArrayList<Integer> studentCourse = targetStudent.getCourseId();
		int courseIndex = studentCourse.indexOf(courseId);

		// Confirmation////
		double mark = 0;
		boolean inRange = true;
		do {
			try {
				System.out.println("Please enter the student's mark");
				mark = Double.parseDouble(scan.nextLine());
				isValidInteger = true;
				inRange = true;
			} catch (Exception e) {
				System.out
						.println("Error - Not a real number, please re-enter");
				isValidInteger = false;
			}
			if (mark > 100 || mark < 0) {
				System.out.println("Error - Mark out of range (0-100%)");
				inRange = false;
			}
		} while (!isValidInteger || !inRange);
		targetStudent.markInput(courseIndex, mark);
		clearStudent();
		for (student studentOut : listOfstudent) {
			student.log(studentOut);
		}
		System.out.println("The mark is updated");
	}

	public static void outputmarks(student self) throws IOException,
			InterruptedException {
		//Displays student marks
		listOfcourse = readFileCourse();
		ArrayList<course> courses = new ArrayList<course>();
		for (int courseID : self.getCourseId()) {
			course courseSearch = new course(courseID);
			/*
			 * for(course coursescheck: database){
			 * System.out.print(coursescheck);}
			 */
			int index = listOfcourse.indexOf(courseSearch);
			courses.add(listOfcourse.get(index));
		}
		Collections.sort(courses, course.periodOrder);
		String output = new String();
		System.out.printf("%-20s%-20s%-20s%-20s%n%n", "Period:",
				"Course name:", "Course ID:", "Marks");
		ArrayList<Double> marks = new ArrayList<Double>();
		marks = self.getMarks();
		for (int x = 0; x < courses.size(); x++) {
			System.out.println(courses.get(x).printMark(marks.get(x)));
		}
	}

	public static void changeAdminPassword() throws IOException {
		//Allows admins to change admin password
		int passwordTrue = readPassword();
		// ///clear
		PrintWriter writer = new PrintWriter("admin.txt");
		writer.print("");
		writer.close();

		// //
		PrintWriter out = new PrintWriter(new FileWriter("admin.txt", true),
				true);
		
		Scanner scan = new Scanner(System.in);
	
		boolean flag = false;
		int password = 0;
		do {
			do {
				try {
					System.out.println("Please enter your orginal password.");
					password = Integer.parseInt(scan.nextLine());
					isValidInteger = true;
				} catch (Exception e) {
					System.out
							.println("Error - Not a legal integer, please re-enter");
					isValidInteger = false;
				}
			} while (!isValidInteger);
			if (password == passwordTrue) {
				do {
					try {
						System.out.println("Please create your new password:");
						passwordTrue = Integer.parseInt(scan.nextLine());
						isValidInteger = true;
					} catch (Exception e) {
						System.out
								.println("Error - Not a legal integer, please re-enter");
						isValidInteger = false;
					}
				} while (!isValidInteger);
				String output = new String();
				output = ""+ passwordTrue;
				out.write(output);
				out.close();
				flag = true;
			} else if (password == 0) {
				flag = true;
			} else {
				System.out
						.println("You have entered the wrong password, please try again or exit(0)");
				flag = false;
			}
		} while (!flag);
	}
	
	public static void changePassword(teacher self) {
		//Allows a teacher to change their own password
		Scanner scan = new Scanner(System.in);
		do {
			try {
				int passwordSet = 0;
				boolean is0 = false;
				do {
					System.out.println("Please change your password:");
					passwordSet = Integer.parseInt(scan.nextLine());
					// SetUpPassWord//
					if (passwordSet == 0) {
						System.out
								.println("You cannot use '0' as your password.");
						is0 = true;
					} else {
						is0 = false;
					}
				} while (is0);
				self.passwordChange(passwordSet);
				Thread.sleep(REG_WAIT_TIME);
				clearTeacher();
				for (teacher teacherOut : listOfteacher) {
					teacher.log(teacherOut);
				}
				isValidInteger = true;
			} catch (Exception e) {
				System.out
						.println("Error - Not a legal integer, please re-enter");
				isValidInteger = false;
			}
		} while (!isValidInteger);
	}

	public static void changePassword(student self) {
		//Allows a student to change their own password
		Scanner scan = new Scanner(System.in);
		do {
			try {
				int passwordSet = 0;
				boolean is0 = false;
				do {
					System.out.println("Please set up your password:");
					passwordSet = Integer.parseInt(scan.nextLine());
					// SetUpPassWord//
					if (passwordSet == 0) {
						System.out
								.println("You cannot use '0' as your password.");
						is0 = true;
					} else {
						is0 = false;
					}
				} while (is0);
				self.passwordChange(passwordSet);
				Thread.sleep(REG_WAIT_TIME);
				clearStudent();
				for (student studentOut : listOfstudent) {
					student.log(studentOut);
				}
				isValidInteger = true;
			} catch (Exception e) {
				System.out
						.println("Error - Not a legal integer, please re-enter");
				isValidInteger = false;
			}
		} while (!isValidInteger);
	}

	public static void teacher(teacher self) throws IOException,
			InterruptedException {
		//Displays the teacher menu and functions
		Scanner scan = new Scanner(System.in);
		int x = 0;
		boolean isValidInteger = false;
		int choice = 0;
		while (x == 0) {
			do {
				try {
					System.out
							.println("Check your teacher profile (1), Edit your teacher profile(2), Check your student profiles (3), ");
					System.out
							.println("Check your course schedule (4), Record Students Marks (5), Change Password (6) or log out (7)");
					choice = Integer.parseInt(scan.nextLine());
					isValidInteger = true;
				} catch (Exception e) {
					System.out
							.println("Error - Not a legal integer, please re-enter");
					isValidInteger = false;
				}
			} while (!isValidInteger);

			if (choice == 1) {
				// Read from the data // Create an array to hold teachers
				teacher.printTitle();
				System.out.println(self);
				/*
				 * System.out.println("Do you want to exit to the main menu (1)")
				 * ; do{ try{ choice = Integer.parseInt(scan.nextLine());
				 * isValidInteger = true; } catch (Exception e){
				 * System.out.println("Error - Not a valid integer");
				 * isValidInteger = false; } } while (!isValidInteger);
				 */
			} else if (choice == 2) {
				editFromTeacher(self);
			} else if (choice == 3) {
				// System.out.println("This function is not ready yet");
				checkStudentProfile(self);
			} else if (choice == 4) {
				self.getSchedule();
				System.out.println("");
			} else if (choice == 5) {
				inputMarks(self);
			} else if (choice == 6) {
				changePassword(self);
				break;
			} else if (choice == 7) {
				System.out.println("You have logged out.");
				break;
			} else {
				System.out
						.println("Your input is not valid, please try again.");
				Thread.sleep(REG_WAIT_TIME);
			}
		}

	}

	public static void student(student self) throws IOException,
			InterruptedException {
		//Displays the student menu and functions
		Scanner scan = new Scanner(System.in);
		int choice = 0;
		int x = 0;
		while (x == 0) {
			do {
				try {
					System.out
							.println("Check your student profile (1), Check your timetable (2),Check your course marks (3) ");
					System.out.println("Change password (4) or log out (5)");
					choice = Integer.parseInt(scan.nextLine());
					isValidInteger = true;
				} catch (Exception e) {
					// System.out
					// .println("Error - Not a legal integer, please re-enter");
					isValidInteger = false;
					choice = 0;
				}
			} while (!isValidInteger);
			if (choice == 1) {
				// Read from the data // Create an array to hold teachers
				student.printTitle();
				System.out.println(self);
				// System.out.println("Exit to the main menu (1)");
				// choice = Integer.parseInt(scan.nextLine());
			} else if (choice == 2) {
				self.getSchedule();
				System.out.println();
			} else if (choice == 3) {
				outputmarks(self);
			} else if (choice == 4) {
				changePassword(self);
				break;
			} else if (choice == 5) {
				System.out.println("You have logged out.");
				break;
			} else {
				System.out
						.println("Your input is not valid, please try again.");
				Thread.sleep(REG_WAIT_TIME);
			}
		}
	}

	public static void teacherLogin() throws IOException, InterruptedException {
		//Allows teachers to login
		try {
			listOfteacher = readFileTeacher();
			inputAvailable = true;
		} catch (Exception e) {
			System.out.println("");
			System.out
					.println("Error - The text file does not contain any information");
			System.out.println("");
			inputAvailable = false;
		}
		if (inputAvailable) {
			teacher currentTeacher = new teacher(1);
			int id = 0;
			boolean flag = false;
			boolean skipped = false;
			Scanner scan = new Scanner(System.in);
			int password = 0;
			do {
				try {
					System.out.println("Please enter your id:");
					id = Integer.parseInt(scan.nextLine());
					isValidInteger = true;
				} catch (Exception e) {
					System.out
							.println("Error - Not a legal integer, please re-enter");
					isValidInteger = false;
				}
			} while (!isValidInteger);
			do {
				skipped = false;
				teacher target = new teacher(id);
				int index = listOfteacher.indexOf(target);
				if (index == -1) {
					System.out
							.println("ID is not found in database, please try again or exit(0)");
					flag = true;
					id = Integer.parseInt(scan.nextLine());
					index = id - 1;
				} else {
					flag = false;
					currentTeacher = listOfteacher.get(index);
					// System.out.println(listOfteacher.get(index));
				}
				if (index == -1) {
					skipped = true;
					flag = false;
				}

			} while (flag);

			if (!skipped) {
				if (currentTeacher.getPassword() == 0) {
					do {
						try {
							int passwordSet = 0;
							boolean is0 = false;
							do {
								System.out
										.println("Please set up your password:");
								passwordSet = Integer.parseInt(scan.nextLine());
								// SetUpPassWord//
								if (passwordSet == 0) {
									System.out
											.println("You cannot use '0' as your password.");
									is0 = true;
								} else {
									is0 = false;
								}
							} while (is0);
							currentTeacher.passwordChange(passwordSet);
							Thread.sleep(REG_WAIT_TIME);
							clearTeacher();
							for (teacher teacherOut : listOfteacher) {
								teacher.log(teacherOut);
							}
							isValidInteger = true;
						} catch (Exception e) {
							System.out
									.println("Error - Not a legal integer, please re-enter");
							isValidInteger = false;
						}
					} while (!isValidInteger);
				} else {
					do {
						do {
							flag = false;
							try {
								System.out
										.println("Please enter your password:");
								password = Integer.parseInt(scan.nextLine());
								isValidInteger = true;
							} catch (Exception e) {
								System.out
										.println("Error - Not a legal integer, please re-enter");
								isValidInteger = false;
							}
						} while (!isValidInteger);

						if (password == currentTeacher.getPassword()) {
							System.out.println("You have logged in");
							teacher(currentTeacher);
						} else if (password == -1) {
							flag = false;
							System.out.println("You have logged out");
						} else {
							System.out
									.println("You entered the wrong password, please try again, or exit (type -1)");
							flag = true;
						}
					} while (flag);
				}
			}
		}
	}

	public static void studentLogin() throws IOException, InterruptedException {
		//Allows students to login
		Scanner scan = new Scanner(System.in);
		try {
			listOfstudent = readFileStudent();
			inputAvailable = true;
		} catch (Exception e) {
			System.out.println("");
			System.out
					.println("Error - The text file does not contain any information");
			System.out.println("");
			inputAvailable = false;
		}
		if (inputAvailable) {
			student currentStudent = new student(1);
			boolean flag = false;
			int id = 0;
			boolean isValidInteger = false;
			boolean skipped = false;
			int account = 0;
			int password = 0;
			do {
				try {
					System.out.println("Please enter your student id:");
					id = Integer.parseInt(scan.nextLine());
					isValidInteger = true;
				} catch (Exception e) {
					System.out
							.println("Error - Not a legal integer, please re-enter");
					isValidInteger = false;
				}
			} while (!isValidInteger);
			do {
				skipped = false;
				student target = new student(id);
				int index = listOfstudent.indexOf(target);
				if (index == -1) {
					System.out
							.println("The Student ID is not found in database, please try again or exit(0)");
					flag = true;
					id = Integer.parseInt(scan.nextLine());
					index = id - 1;
				} else {
					flag = false;
					currentStudent = listOfstudent.get(index);
					// System.out.println(listOfteacher.get(index));
				}
				if (index == -1) {
					skipped = true;
					flag = false;
				}

			} while (flag);

			if (!skipped) {
				if (currentStudent.getPassword() == 0) {
					do {
						try {
							int passwordSet = 0;
							boolean is0 = false;
							do {
								System.out
										.println("Please set up your password:");
								passwordSet = Integer.parseInt(scan.nextLine());
								// SetUpPassWord//
								if (passwordSet == 0) {
									System.out
											.println("You cannot use '0' as your password.");
									is0 = true;
								} else {
									is0 = false;
								}
							} while (is0);
							currentStudent.passwordChange(passwordSet);
							Thread.sleep(REG_WAIT_TIME);
							clearStudent();
							for (student studentOut : listOfstudent) {
								student.log(studentOut);
							}
							isValidInteger = true;
						} catch (Exception e) {
							System.out
									.println("Error - Not a legal integer, please re-enter");
							isValidInteger = false;
						}
					} while (!isValidInteger);
				} else {
					do {
						do {
							flag = false;
							try {
								System.out
										.println("Please enter your password:");
								password = Integer.parseInt(scan.nextLine());
								isValidInteger = true;
							} catch (Exception e) {
								System.out
										.println("Error - Not a legal integer, please re-enter");
								isValidInteger = false;
							}
						} while (!isValidInteger);

						if (password == currentStudent.getPassword()) {
							System.out.println("You have logged in");
							student(currentStudent);
						} else if (password == -1) {
							flag = false;
							System.out.println("You have logged out");
						} else {
							System.out
									.println("You entered the wrong password, please try again, or exit (type -1)");
							flag = true;
						}
					} while (flag);
				}
			}
		}
	}

	public static void main(String[] args) throws IOException,
			InterruptedException {
		//The program's main menu, allows you to login to one of three account types, or to exit
		int x = 0;
		Scanner scan = new Scanner(System.in);
		boolean isValidInteger = false;
		int account = 0;
		int password = 0;

		while (x == 0) {
			boolean safety = false;
			int count = 0;
			do {// System.out.println("");
				try {
					System.out
							.println("Please choose which account to login into: 1(admin),2(teacher),3(students),4(Exit)");
					account = Integer.parseInt(scan.nextLine());
					isValidInteger = true;
				} catch (Exception e) {
					System.out
							.println("Error - Not a legal integer, please re-enter ");
					isValidInteger = false;
				}
			} while (!isValidInteger);
			while (account == 1) {
				int passwordTrue = readPassword();
				do {
					try {
						System.out.println("Please enter your password:");
						password = Integer.parseInt(scan.nextLine());
						isValidInteger = true;
					} catch (Exception e) {
						System.out
								.println("Error - Not a legal integer, please re-enter");
						isValidInteger = false;
					}
				} while (!isValidInteger);
				if (password == passwordTrue) {
					admin();
					account = 0;
					count = 0;
				} else if (password == 0) {
					break;
				} else {
					System.out
							.println("You have entered the wrong password, please try again or exit(0)");
					count++;
				}

				if (count > 4) {
					System.out
							.println("!!!!!You have entered the wrong password too many times, the database is disabled for 30 seconds!!!!!");
					// scan.close();
					Thread.sleep(WAIT_TIME_PENALTY);
					// scan = new Scanner(System.in);
				}

			}
			if (account == 2) {
				teacherLogin();
			}

			if (account == 3) {
				studentLogin();
			}
			if (account == 4) {
				System.out
						.println("Thank you for using the school database system.");
				break;
			}

		}

		scan.close();
	}
}
