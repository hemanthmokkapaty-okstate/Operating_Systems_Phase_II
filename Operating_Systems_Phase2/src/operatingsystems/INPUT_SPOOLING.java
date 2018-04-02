package operatingsystems;

import java.io.*;
import java.util.*;
import java.util.Scanner;
import java.lang.*;
import java.io.File;
import java.io.BufferedWriter;
import java.io.IOException;



public class INPUT_SPOOLING extends SYSTEM
{
public static Scanner readLines;
public static int linecount = 0;
public static String each_line = null;
public static int Input_Words;
public static int Output_Words;
public static int Program_Segment_Length;
public static int Input_Start_Index;
public static int Output_Start_Index;
public static int Total_Pages;

	
public void openFile()
{
	try
	{
		readLines = new Scanner(new File(FileName));
	}
	catch(Exception e)
	{
		System.out.println("File not Found");
	}
}	
	
public void ReadFile()
{
	boolean input_flag = false;
	int disk_index =0;
	int input_line_no = 0;
	while(readLines.hasNext())
	{
		each_line = readLines.nextLine();
		System.out.println("each_line:"+each_line);
		linecount++;
		if(each_line.contains("**JOB"))
		{
			String Job_line = each_line;
			String[] Job_line_values = Job_line.split("\\s+");
			Input_Words = Hex_to_Dec(Job_line_values[1]);
			Output_Words = Hex_to_Dec(Job_line_values[2]);
		}
			
		if(linecount ==2)
		{
			String second_line = each_line;
			String[] second_line_values = second_line.split("\\s+");
			Job_Id = Hex_to_Dec(second_line_values[0]);
			Base_Address = Hex_to_Dec(second_line_values[1]);
			PC = Hex_to_Dec(second_line_values[2]);
			Program_length = Hex_to_Dec(second_line_values[3]);
			Trace_Flag = Hex_to_Dec(second_line_values[4]);		
		}
		
		if(each_line.contains("**INPUT"))
		{
			input_flag =true;
			input_line_no = linecount +1; 
		}
		
		
		if(linecount!=1 && linecount!=2 && input_flag!=true)
		{
			int word_count=0;
			while(word_count<each_line.length())
			{
				if(each_line.length()%4!=0)
				{
					ERROR_HANDLER.ERROR(1);
				}
			String each_word= each_line.substring(word_count,Math.min(word_count+4,each_line.length()));
			word_count = word_count +4;
			String first_word = Hex_to_Bin_8_bit(each_word.substring(0, 2));
			String second_word = Hex_to_Bin_8_bit(each_word.substring(2,4));
			String combined_binary_word = first_word + second_word ;
			DISK.DISK[disk_index] = combined_binary_word;
			disk_index++;
			}
		}
		
		
		if(input_line_no == linecount)
		{
			String inputline = each_line;
			String first_word = Hex_to_Bin_8_bit(inputline.substring(0, 2));
			String second_word = Hex_to_Bin_8_bit(inputline.substring(2,4));
			String combined_binary_word = first_word + second_word ;
			disk_index = Program_length + (Program_length%8);
			DISK.DISK[disk_index] = combined_binary_word;
		}
		
	}
}

public static void closeFile()
{
	readLines.close();
}


public static void Disk_Segmenting()
{
	if(Program_length%8==0)
	{
		Program_Segment_Length = (Program_length/8);	
	}
	else if(Program_length%8!=0)
	{
		Program_Segment_Length = (Program_length/8)+1;
	}
	else if(Program_length<8 && Program_length>0)
	{
		Program_Segment_Length = 1;
	}
	System.out.println("Program segment Length:"+Program_Segment_Length+" Pages");
	
	Input_Start_Index = (8*Program_Segment_Length);
	Output_Start_Index = Input_Start_Index +8;
	Output_Disk_Address = Output_Start_Index;
	System.out.println("Input Start:"+Input_Start_Index);
	System.out.println("Output Start:"+Output_Start_Index);
	
}


	
}
