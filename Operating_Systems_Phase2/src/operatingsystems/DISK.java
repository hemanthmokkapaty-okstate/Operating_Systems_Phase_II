package operatingsystems;

import java.io.*;
import java.util.*;
import java.util.Scanner;
import java.lang.*;
import java.io.File;
import java.io.BufferedWriter;
import java.io.IOException;



public class DISK extends SYSTEM
{
public static int Input_Words;
public static int Output_Words;
public static int Program_Segment_Length;
public static int Input_Start_Index;
public static int Output_Start_Index;
public static String[] DISK = new String[2048];
public static String[] lines = new String[1000];
public static String[] HD = new String[1000];
public static String[] BIN = new String[1000];
public static String[] Input_Segment = new String[8];
public static String[] Output_Segment = new String[8];
public static int i =0;

public static Scanner readLines;
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
public static void ReadFile()
{
	while(readLines.hasNext())
	{
		lines[i] = readLines.nextLine();
		System.out.println(lines[i]);
		i++;
	}
}

public void closeFile()
{
	readLines.close();
}



public static void variableLoading()
{
	 String first_line = lines[0];
	 System.out.println(first_line);
	 String[] First_line_values = first_line.split("\\s+");
	 Input_Words = Hex_to_Dec(First_line_values[1]);
	 System.out.println("No of Input Words:"+Input_Words);
	 Output_Words = Hex_to_Dec(First_line_values[2]);
	 System.out.println("No of Output Words:"+Output_Words);
	 String second_line = lines[1];
	 System.out.println(second_line);
	 String[] Second_line_values = second_line.split("\\s+");
	 Job_Id = Hex_to_Dec(Second_line_values[0]);
	 System.out.println(Job_Id);
     if(Job_Id!=1)
     {
     	ERROR_HANDLER.ERROR(106);
     }
     Base_Address = Hex_to_Dec(Second_line_values[1]);
     System.out.println(Base_Address);
     if(Base_Address!=0)
     {
     	ERROR_HANDLER.ERROR(104);
     }
     PC = Hex_to_Dec(Second_line_values[2]);
     System.out.println(PC);
     Program_length = Hex_to_Dec(Second_line_values[3]);
     System.out.println(Program_length);
     if(PC>Program_length)
     {
     	ERROR_HANDLER.ERROR(2);
     }
     Trace_Flag = Hex_to_Dec(Second_line_values[4]);  
     System.out.println(Trace_Flag);
     if(Trace_Flag!=1&&Trace_Flag!=0)
     {
     	ERROR_HANDLER.ERROR(107);
     }
}

public static void HexLoad()
{
	String one_line="";
	int index = 2;
	while(!lines[index].contains("INPUT"))
	{
		one_line = one_line + lines[index];
		index++;
	}
	System.out.println(one_line);
	int each_word=0;
	int HD_index =0;
	while(each_word<one_line.length())
	{
		if(one_line.length()%4!=0)
		{
			ERROR_HANDLER.ERROR(1);
		}
	HD[HD_index]= one_line.substring(each_word,Math.min(each_word+4,one_line.length()));
	each_word = each_word +4;
	HD_index++;
	}
	int j=0;
	while(HD[j]!=null)
	{
		System.out.println("HD:"+HD[j]);
		j++;
	}
	int k = 0;
	while(HD[k]!=null)
	{
		String first_word = Hex_to_Bin_8_bit(HD[k].substring(0, 2));
		String second_word = Hex_to_Bin_8_bit(HD[k].substring(2,4));
		BIN[k] = first_word + second_word ;
		k++;
	}
	int l = 0;
	while(BIN[l]!=null)
	{
		//System.out.println(BIN[l]);
		l++;
	}
	
	
}


public static void Segment_Loading()
{
	int i=0;
	int input_index =0;
	int in_index =0;
	while(lines[i]!=null)
	{
		if(lines[i].contains("**INPUT"))
		{
			for(input_index =0;input_index<Input_Words;input_index++)
			{
				Input_Segment[input_index] = Hex_to_Bin_16_bit(lines[i+1]);
				i++;
			}
		}
		i++;
	}
	
	while(Input_Segment[in_index]!=null)
	{
		System.out.println("Input Segment:"+Input_Segment[in_index]);
		in_index++;
	}
	
}


public static void Input_Spooling()
{
	int i = 0;
	while(BIN[i]!=null)
	{
		DISK[i] = BIN[i]; 
		//System.out.println(DISK[i]);
		i++;
	}
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
	
	System.out.println("Input Start:"+Input_Start_Index);
	System.out.println("Output Start:"+Output_Start_Index);
	
	int k = 0;
	int l = Input_Start_Index;
	while(Input_Segment[k]!=null)
	{
		DISK[l] = Input_Segment[k];
		k++;
		l++;
	}
	for(int i=0;i<Output_Start_Index;i++)
	{
		System.out.println(DISK[i]);
	}
	
}

}