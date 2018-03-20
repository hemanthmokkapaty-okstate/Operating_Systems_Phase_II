/*
 * Name:Hemanth Kumar Mokkapaty
 * CWID: A20042630
 * 
 */



package operatingSystems;

import java.io.*;
import java.lang.*;
import java.math.*;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;



public class SYSTEM {
	public static int Job_Id;
	public static int Base_Address;
	public static int Start_Instruction;
	public static int Trace_Flag;
	public static int PC;
	public static int Program_length;
	public static int Effective_Address;
	public static String Instruction_Register;
	public static String FileName;
	public static int System_Clock = 0;
	public static int IO_Clock =0;
	public static String Output;
	public static String outfile = "output.txt";

	
	
	public static int Hex_to_Dec(String hex)
	{
		int decimal = Integer.parseInt(hex, 16);
		return decimal;
	}
	
	public static String Hex_to_Bin(String hex)
	{
	int decimal = Integer.parseInt(hex,16);    
	String binary = Integer.toBinaryString(decimal);
	while(binary.length()<16)
	{
		binary = "0"+binary;
	}
	return binary;
	}
	
	public static String Hex_to_Bin_8_bit(String hex)
	{
	int decimal = Integer.parseInt(hex,16);    
	String binary = Integer.toBinaryString(decimal);
	while(binary.length()<8)
	{
		binary = "0"+binary;
	}
	return binary;
	}
	
	public static String Dec_to_Bin_16_bit(int Dec)
	{
		String binary = Integer.toBinaryString(Dec);
		if(binary.length()>16)
		{
			binary = binary.substring((binary.length()-16),binary.length());
		}
		
		while(binary.length()<16)
		{
			binary = "0"+binary;
		}
		return binary;
		}
	
	
	public static String Dec_to_Bin_8_bit(int Dec)
	{
		String binary = Integer.toBinaryString(Dec);
		while(binary.length()<8)
		{
			binary = "0"+binary;
		}
		return binary;
		}
	
	public static int Bin_to_Dec(String bin)
	{
		int decimalValue = Integer.parseInt(bin, 2);
		return decimalValue;
	}
	
	public static int getTwosComplement(String binaryInt) {
	    //Check if the number is negative.
	    //We know it's negative if it starts with a 1
	    if (binaryInt.charAt(0) == '1') {
	        //Call our invert digits method
	        String invertedInt = invertDigits(binaryInt);
	        //Change this to decimal format.
	        int decimalValue = Integer.parseInt(invertedInt, 2);
	        //Add 1 to the curernt decimal and multiply it by -1
	        //because we know it's a negative number
	        decimalValue = (decimalValue + 1) * -1;
	        //return the final result
	        return decimalValue;
	    } else {
	        //Else we know it's a positive number, so just convert
	        //the number to decimal base.
	        return Integer.parseInt(binaryInt, 2);
	    }
	}

	public static String invertDigits(String binaryInt) {
	    String result = binaryInt;
	    result = result.replace("0", " "); //temp replace 0s
	    result = result.replace("1", "0"); //replace 1s with 0s
	    result = result.replace(" ", "1"); //put the 1s back in
	    return result;
	}
	
	public static void output(int jobid, int sysclock,int ioclock,String output) {
		  
		  try (BufferedWriter b = new BufferedWriter(new FileWriter(outfile))) {
		   b.write("Cumulative Job Identification number :"+jobid);
		   b.newLine();
		   b.write("Nature of termination : Normal");
		   b.newLine();
		   b.write("Output of the Job : " +output + "(BINARY)");
		   b.newLine();
		   String sclock;
		   sclock = Integer.toHexString(sysclock);
		   b.write("Clock value at termination: " + sclock + " (HEX)virtual time units");
		   b.newLine();
		   b.write("Run time for the job");
		   b.newLine();
		   b.write("Execution time: " + (sysclock-ioclock) + "(DECIMAL)virtual time units");
		   b.newLine();
		   b.write("Input/output time: " + ioclock + "(DECIMAL)virtual time units");
		   b.newLine();
		  } catch (IOException e) {
		   e.printStackTrace();
		  }
		 }
	
	
	 public static void outputerror(int jobid,int sysclock, int ioclock,String error) {
		  try (BufferedWriter b = new BufferedWriter(new FileWriter(outfile))) {
		   b.write("Cumulative Job Identification number :"+jobid);
		   b.newLine();
		   b.write("Nature of termination : Abnormal");
		   b.newLine();
		   b.write(error);
		   b.newLine();
		   String sclock;
		   sclock = Integer.toHexString(sysclock);
		   b.write("Clock value at termination: " + sclock + " (HEX)virtual time units");
		   b.newLine();
		   b.write("Run time for the job");
		   b.newLine();
		   b.write("Execution time: " + System_Clock + " (DECIMAL)virtual time units");
		   b.newLine();
		   b.write("Input/output time: " + ioclock + " (DECIMAL)virtual time units");
		   b.newLine();

		  } catch (IOException e) {
		   e.printStackTrace();
		  }
		catch (NumberFormatException e) {
		   e.printStackTrace();
		  }

		 }

	
	
	
	
	public static void main(String[] args) {
		LOADER loader = new LOADER();
		// TODO Auto-generated method stub
		loader.HexLoad();
		MEMORY memory = new MEMORY();
		memory.Buffer_Loading(Base_Address,Base_Address);
		CPU cpu = new CPU();
		CPU.CPU(PC,Trace_Flag);
	}

	

}
