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
	private Scanner fileReader; // Use it to read a file.
	private String inFileName; // Use it to let a user to enter a file name.
	private String outFileName; // Use it to concatenate one of two files.
	private PrintWriter pw; // Use it to write to one of results file.
	private File inFile; // Use it to read t1.txt.
	private File outFile; // Use it to read t2.txt.

	public TeacherInfo()
	{
		scores = new int[101];
		grades = new int[5];
		teacherData = new String[3];
		pw = null;
		fileReader = null;
		inFileName = new String("");
		outFileName = new String("-results.txt");
		inFile = new File("t1.txt");
		outFile = new File("t2.txt");
	}

	public static void main(String[] args)
	{
		TeacherInfo ti = new TeacherInfo();
		ti.getdata();
	}

	public void getdata()
	{
		int courseNum = 0; int counter = 0;
		courseNum = getInput();
		int numSections = 0;
		openFile();
		writeFile();
		createFile(courseNum); 
		readFile(courseNum);
		getData(numSections, courseNum, counter);
		printOverallResults(courseNum);
	}

	// Prints a welcome message and prompts user to enter a course number
	// and a file name. Also need two text files to print scores for both terms.
	public int getInput()
	{
		/// Let user check a course number that exists.
		int courseNum = 0; 
		Scanner src = new Scanner(System.in);
		System.out.println("\n\n\n");
		System.out.print("Welcome the TeacherInfo! This program will collect all of "
			+ "the information about the number of A's, B's to F's that you have in the "
			+ "course that you select\n\n");
		// Prompting the user for the input file name, the output file
		// name will be created then concatenate “-results.txt” to make
		// the output file name. 
		System.out.print("Please enter the name of the teacher’s file "
			+ "including the extension -> ");
		inFileName = src.next();
		if(outFileName.indexOf(" ") != -1)
			outFileName = inFileName.substring(0, inFileName.indexOf(".txt")) + outFileName;
		System.out.print("Please enter the course number for data you "
			+ "would like -> ");
		courseNum = src.nextInt();
		if(courseNum != 2370 || courseNum != 2440 || courseNum != 2320 || outFile.equals("t2.txt"))
		{
			System.out.println("\nSince Natasha Romanoff, aka BW does "
				+ "not teach course number " + courseNum + ", there is "
				+ "no data to report.");
		}
		else if(courseNum != 2370 || courseNum != 2440 || courseNum != 2320 || inFile.equals("t1.txt"))
		{
			System.out.println("\nSince Elmer J. Fudd, does  not teach "
				+ "course number " + courseNum + ", there is no data to "
				+ "report.");
		}
		return courseNum;
	}

	// Need a method to just open a file for inFileName. 
	public void openFile()
	{
		File name = new File(inFileName);
		try
		{
			fileReader = new Scanner(name);
		}

		catch(FileNotFoundException e)
		{
			System.err.printf("The " + inFileName + " can't be created.");
			System.exit(1);
		}
	} 

	// Create a file using try-catch for outFileName.
	public void writeFile()
	{
		File writeFile = new File(outFileName);
		try
		{
			pw = new PrintWriter(writeFile);
		}
		catch(IOException e)
		{
			System.err.print("The " + outFileName + " can't be created.");
			System.exit(2);
		}
	}

	// Read one of a file, t1 or t2.
	public void readFile(int courseNum)
	{ 
		int count = 1; // Use count to count for scores
		String roomNum = new String("");
		String info = new String("");
		String line = new String("");
		//String placeH = new String("");
		//System.out.println(fileReader.nextLine() );
		int score = 0;
		double lineNum = 0.0;
		
		while(fileReader.hasNext() )
		{
			line = fileReader.nextLine();
			if(line.indexOf("Teacher:") != -1 && courseNum == 2370 ||
				courseNum == 2440 || courseNum == 2320)
			{
			teacherData[0] = line.substring(line.indexOf("Teacher:")+1).trim();
			teacherData[1] = line.substring(line.indexOf("Class:")+1).trim();
			teacherData[2] = line.substring(line.indexOf("Room:")+1).trim();
			//placeH = line.substring(line.indexOf("Year: ")).trim();
			
			System.out.println("\n" + teacherData[0]);
			System.out.print(teacherData[1]);
			System.out.print(teacherData[2]);
			//System.out.println(placeH);
			System.out.println("\n");
				
				fileReader.nextLine();
				fileReader.nextLine();
				System.out.println(fileReader.nextLine() );
				//if(courseNum == Integer.parseInt(teacherData[1]) )
				//{
					while(fileReader.hasNextDouble() )
					{
						lineNum = fileReader.nextDouble();
						scores[count] = (int)(lineNum);
						count++;
						System.out.print(lineNum + "\t");
					}
				//}
			}
		}
	}

	// Use printwriter to print teacher info to a result file.
	public void createFile(int courseNum)
	{
		int count = 0; // Use count to count for scores
		String roomNum = new String("");
		String info = new String(""); String line = new String("");
		String placeH = new String("");
		double lineNum = 0.0;
		//System.out.println(placeH);
		if(line.indexOf("Teacher") != -1)
		{
			teacherData[0] = line.substring(line.indexOf("Teacher:")+1).trim();
			teacherData[1] = line.substring(line.indexOf("Class:")+1).trim();
			teacherData[2] = line.substring(line.indexOf("Room:")+1).trim();
			pw.print(teacherData[0]);
			pw.print(teacherData[1]);
			pw.print(teacherData[2]);
			pw.println("\n\n");
			pw.print("Scores: \n");
			pw.println("\n");
			while(fileReader.hasNextDouble() )
			{
				lineNum = fileReader.nextDouble();
				scores[count] = (int)(lineNum);
				count++;
				System.out.print(lineNum + "\t");
			}
		}
		
		if(courseNum != 2370 || courseNum != 2440 || courseNum != 2320 || outFile.equals("t2.txt"))
		{
			pw.println("\nSince Natasha Romanoff, aka BW does "
				+ "not teach course number " + courseNum + ", there is "
				+ "no data to report.");
		}
		else if(courseNum != 2370 || courseNum != 2440 || courseNum != 2320 || inFile.equals("t1.txt"))
		{
			pw.println("\nSince Elmer J. Fudd, does  not teach "
				+ "course number " + courseNum + ", there is no data to "
				+ "report.");
		}
		pw.close();
	}

	// This method prints 2 data versions. Prints a course info and all
	// scores are print from high to low 15 numbers in each line by using
	// %15 in a for loop. In a nested for loop, it sorts out numbers from
	// greatest to least and determine which range of numbers fall in one
	// of a group by using if-else. In an another for loop, all scores
	// are sorted within a group and prints 18 numbers in a line.     
	public void getData(int sections, int courseNums, int count)
	{
		String teacher = new String("");
		String courseName = new String("");   
		if(courseNums == 2370 && inFile.equals("t1.txt"))
			teacher = "Elmer J. Fudd";
		else if(courseNums == 2440)
			teacher = "Elmer J. Fudd";
		else if(courseNums == 2320)
			teacher = "Natasha Romanoff, aka BW";
		else if(courseNums == 2370 && outFile.equals("t2.txt"))
			teacher = "Natasha Romanoff, aka BW";
		System.out.println("\n");
		if(courseNums == 2370 || courseNums == 2440 || courseNums == 2320)
		{
			System.out.print("Data for: " + teacher + "\t");
			System.out.println("Course number: " + courseNums);
			System.out.print("Course: " + courseName);
			sections++;
			System.out.println("Number of sections: " + sections);
			count++;
			System.out.println("Total # of scores = " + count);
			System.out.println("\n");
			System.out.print("Data version 1: ");
			for(int i = 0; i < scores.length; i++)
			{
				if(i % 15 != 0)
					System.out.printf("%5d", scores[i]);
				else
					System.out.println("");
				i++;
				for(int j = i + 1; j < scores.length; j++)
				{
					if(scores[i] < scores[j])
					{
						count = scores[i];
						scores[i] = scores[j];
						scores[j] = count;
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
			+ "grouped together by 90's, 80's etc.\n");
			for(int k = 0; k < scores.length; k++)
			{
				for(int o = k + 1; o < scores.length; o++)
				{
					if(scores[k] > scores[o])
					{
						count = scores[k];
						scores[k] = scores[o];
						scores[o] = count;
					}
				}
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
					/*for(int j = 0; j < 6; j++)
					{
						if(grades[4] >= 0 && grades[4] <= 59)
						{
							if(j % 18 != 0)
								System.out.printf("%-5d", scores[j]);
							else
								System.out.println();
						}
					}*/
					if(k % 18 != 0)
						System.out.println();
					else
						System.out.printf("%-5d", scores[k]);
					k++;
				}
			}
		}
	}

	// Prints each grade group with precentage and number of students
	// are there. Prints an error message if a user enter 222 as a course
	// number. Prints 3 blank lines. Score formula is use to calculate
	// their score.
	public void printOverallResults(int courseNum)
	{
		int students = 0; int part = 0; int whole = 0;
		double score = 0.0;
		score = ((double)part/whole * 100);

		if(courseNum == 2440 || courseNum == 2370 || courseNum == 2320)
		{
			for(int i = 0; i < 5; i++)
			{
				students++;
			}
			System.out.println("\n\n\nData version 3: Total number of each letter grade\n");
			System.out.printf("A (90 - 100):%4d %.2f%%", students, score);
			System.out.printf("%nB (80 - 90) :%4d %.2f%%", students, score);
			System.out.printf("%nC (70 - 80) :%4d %.2f%%", students, score);
			System.out.printf("%nD (60 - 70) :%4d %.2f%%", students, score);
			System.out.printf("%nF (0 - 59) :%4d %.2f%%", students, score);
		}
		
		System.out.println("\n\n\n");
	}
}
