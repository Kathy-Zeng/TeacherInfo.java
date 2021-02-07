// Kathy Zeng
// 2/1/21
// TeacherInfo.java
// Desciption: This program reads about teachers' info associated with
// students' scores.

import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TeacherInfo
{
	private int[] scores; // Identify each students' scores.
	private int[] grades; // Identify their grades.
	private String[] teacherData; // Use it for teacher name, the course name,
	// and course number.
	private PrintWriter pw; // Use it to create these 2 files in try-catch block. 
	private File inFile; // Use it for term 1.
	private File outFile; // Use it for term 2.

	public TeacherInfo()
	{
		scores = new int[101];
		grades = new int[5];
		teacherData = new String[3];
		pw = null;
		inFile = new File("t1.txt");
		outFile = new File("t2.txt");
	}

	public static void main(String[] args)
	{
		TeacherInfo ti = new TeacherInfo();
		ti.getInput();
		int num = 0; int nums = 0;
		ti.getInfo(num);
		ti.getData(nums);
		ti.printOverallResults();
	}

	// Prints a welcome message and prompts user to enter a course number
	// and a file name. Also need two text files to print scores for both terms.
	public void getInput()
	{
		Scanner src = new Scanner(System.in);
		System.out.println("\n\n\n");
		System.out.print("Welcome the TeacherInfo! This program will collect all of "
			+ "the information about the number of A's, B's to F's that you have in the "
			+ "course that you select\n\n");
		System.out.print("Please enter the name of the teacher’s file "
			+ "including the extension -> ");
		String file = src.next();
		file = file.substring(0, file.length() - 4);
		file += file.concat("-results.txt");
		// inFile
		System.out.print("Please enter the course number for data you "
			+ "would like -> ");
		int courseNum = src.nextInt();
		System.out.println("\n\n");
		String teacher = teacherData[0]; 
		String courseName = teacherData[1];  
		String courseNumber = teacherData[2];
		String roomNum = new String("");

		// Opens and create t1.txt file to store students' scores.
		String info = new String("");
		try
		{
			if(src.hasNextLine() )
				info=src.next();
			src = new Scanner(inFile);
			info = info.substring(0, info.indexOf("Teacher: ", 8) );
			pw.print(info + teacher);
			pw.print("\tClass:  " + courseNumber + "  " + courseName
				+ "\tRoom: \n" + roomNum);
			if(teacher.equals("Elmer J. Fudd") && courseNumber.equals("2440") )
				roomNum = "K302";
			else if(teacher.equals("Elmer J. Fudd") && courseNumber.equals("2370") )
				roomNum = "L201";
			pw.println("Year:  2020-2021\t\tTerm:  T1 \n\n");
			pw.print("Scores: \n");
			for(int i = 0; i < scores.length; i++)
			{
				if(i % 15 == 0)
					pw.printf("%5d" + scores[i]);
				else
					pw.println();
			}
		}
		catch(IOException e)
		{
			System.err.printf("\n\n\nERROR: Cannot file/open file %s", inFile);
			System.exit(1);
		}

		// Opens and create t2.txt file to store students' scores.
		String information = new String("");
		try
		{
			src = new Scanner(outFile);
			if(src.hasNextLine() )
				information=src.next();
			information = information.substring(0, information.indexOf("Teacher: ", 8) );
			pw.println(information + teacher);
			pw.print("\tClass:  " + courseNumber + "  " + courseName);
			pw.println("Room: " + roomNum + "\nYear:  2020-2021\t\t\tTerm:  T1");
			if(teacher.equals("Natasha Romanoff, aka BW") && courseNumber.equals("2320") )
				roomNum = "J407";
			else if(teacher.equals("Natasha Romanoff, aka BW") && courseNumber.equals("2370") )
				roomNum = "L202";
			pw.println("\n");
			pw.print("Scores: \n");
			for(int j = 0; j < scores.length; j++)
			{
				if(j % 7 == 0)
					pw.printf("%5d" + scores[j]);
				else
					pw.println();
			}
		}
		catch(IOException e)
		{
			System.err.printf("\n\n\nERROR: Cannot file/open file %s", outFile);
			System.exit(2);
		}
	}

	// Get number of sections for both files along with idenify teacher's name and
	// course name by using conditional statements. For their conditions, they
	// idenify a course number.   
	
	public int getInfo(int num)
	{
		String teacher = teacherData[0]; 
		String courseName = teacherData[1];  
		String courseNum = teacherData[2];

		// t1.txt
		if(courseNum.equals("2440") )
		{
			teacher = "Elmer J. Fudd";
			courseName = "AP Calculus BC";
			num = 3;
		}
		else if(courseNum.equals("2370") )
		{
			teacher = "Elmer J.Fudd";
			courseName = "Comp Prog Java";
			num = 2; 
		}
		
		// t2.txt
		if(courseNum.equals("2370") )
		{
			teacher = "Natasha Ramonoff, aka BW";
			courseName = "Comp Prog Java";
			num = 3;
		}
		else if(courseNum.equals("2320") )
		{
			teacher = "Natasha Ramonoff, aka BW";
			courseName = "Alg 2/Trig";
			num = 2;
		}
		
		return num;
	}


	// This method prints 2 data versions. Prints a course info and all
	// scores are print from high to low 15 numbers in each line by using
	// %15 in a for loop. In a nested for loop, it sorts out numbers from
	// greatest to least and determine which range of numbers fall in one
	// of a group by using if-else. In an another for loop, all scores
	// are sorted within a group and prints 18 numbers in a line.     
	public void getData(int nums)
	{
		String teacher = teacherData[0]; 
		String courseName = teacherData[1];  
		String courseNum = teacherData[2]; 
		System.out.print("Data for: " + teacher);
		System.out.println("Course number: " + courseNum + "\tCourse: " +
			courseName);
		System.out.println("Number of sections: " + nums);
		System.out.println("Total # of scores = " + scores);
		System.out.println("\n");
		System.out.print("Data version 1: \n");
		for(int i = 0; i < scores.length; i++)
		{
			if(i % 15 == 0)
				System.out.printf("%5d", scores[i]);
			else
				System.out.println();
			for(int j = i + 1; j < scores.length; j++)
			{
				if(scores[i] > scores[j])
				{
					nums = scores[i];
					scores[i] = scores[j];
					scores[j] = nums;
				}
				else if(grades[0] >= 90 && grades[0] <= 100)
					grades[0]++;
				else if(grades[1] >= 80 && grades[1] < 90)
					grades[1]++;
				else if(grades[2] >= 70 && grades[2] < 80)
					grades[2]++;
				else if(grades[3] >= 60 && grades[3] < 70)
					grades[3]++;
				else if(grades[4] >= 0 && grades[4] < 59)
					grades[4]++;
		    }
		}
		System.out.println("\n\n");
		System.out.print("Data version 2: Here are all of the scores "
		+ "grouped together by 90's, 80's etc.");
		for(int k = 0; k < scores.length; k++)
		{
			if(k % 18 == 0)
			{
				if(grades[0] >= 90 && grades[0] <= 100)
					grades[0]++;
				if(grades[1] >= 80 && grades[1] < 90)
					grades[1]++;
				if(grades[2] >= 70 && grades[2] < 80)
					grades[2]++;
				if(grades[3] >= 60 && grades[3] < 70)
					grades[3]++;
				if(grades[4] >= 0 && grades[4] < 59)
					grades[4]++;
				for(int j = 0; j < 6; j++)
				{
					if(grades[4] >= 0 && grades[4] <= 59)
					{
						if(j % 18 == 0)
							System.out.printf("%-5d", scores[j]);
						else
							System.out.println();
					}
				}
				if(k % 18 != 0)
					System.out.println();
				else
					System.out.printf("%-5d", scores[k]);
			}

		}
	}

	// Prints each grade group with precentage and number of students
	// are there. Prints an error message if a user enter 222 as a course
	// number. Prints 3 blank lines. Score formula is use to calculate
	// their score.
	public void printOverallResults()
	{
		String courseNum = teacherData[2];
		int students = 0; int part = 0; int whole = 0; 
		double score = 0.0;
		score = ((double)part/whole * 100);
		if(courseNum.equals("222") )
		System.out.println("Since Natasha Romanoff, aka BW does not teach " 
			+ "course number 222, there is no data to report.");
		else
		{
			System.out.printf("A (90 - 100):%4d%.2f%", students, scores.length);
			System.out.printf("B (80 - 90) :%4d%.2f%", students, scores.length);
			System.out.printf("C (70 - 80) :%4d%.2f%", students, scores.length);
			System.out.printf("D (60 - 70) :%4d%.2f%", students, scores.length);
			System.out.printf("F (0 - 59) :%4d%.2f%", students, scores.length);
		}
		System.out.println("\n\n\n");
	}
}
