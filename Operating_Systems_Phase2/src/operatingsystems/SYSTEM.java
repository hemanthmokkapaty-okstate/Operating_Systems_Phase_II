package operatingsystems;

/*	
 * Name:Hemanth Kumar Mokkapaty
 * CWID: A20042630
 * Course Number : CS 5323
 * Operating Systems Phase 2: A simple batch Operating System with Memory Management
 */

/*Description:
The SYSTEM class will be the driver for this simulation.The SYSTEM is to 
contain Ten subsystems : LOADER, CPU, MEMORY, ERROR_HANDLER,PCB,INPUT_SPOOLING,SMT,PMT,OUTPUT_SPOOLING. All the variables declared
in the SYSTEM are static and can be accessed all through the simulation.The objects for every 
subsystem are created and called in the SYSTEM.
*/

import java.io.*;
import java.lang.*;
import java.math.*;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

//System class which acts as the driver to the operating System
public class SYSTEM {
	public static int Job_Id;
	public static int Base_Address;
	public static int Start_Instruction;
	public static int Trace_Flag;
	public static int PC;
	public static int Program_length;
	public static int Effective_Address = 0;
	public static int Effective_Address_By_Frame;
	public static int Page_Index;
	public static int Frame_Index;
	public static int Page_Number;
	public static int Frame_Number;
	public static int PC_Page_Index;
	public static int PC_Frame_Index;
	public static int PC_Page_Number;
	public static int PC_Frame_Number;
	public static int EA_Page_Index;
	public static int EA_Frame_Index;
	public static int EA_Page_Number;
	public static int EA_Frame_Number;
	public static int First_Page_Index;
	public static int First_Frame_Index;
	public static int First_Page_Number;
	public static int First_Frame_Number;
	public static int Total_Frames;
	public static int Output_Disk_Address;
	public static int Output_Memory_Address;
	public static String Instruction_Register;
	public static String FileName;
	public static int System_Clock = 0;
	public static int IO_Clock = 0;
	public static int Final_Clock = 0;
	public static int VtuClock = 0;
	public static float Memory_Numerator = 0;
	public static float Memory_Denominator = 256;
	public static float Memory_Frames_Used = 0;
	public static float Memory_Frames_Available = 32;
	public static float Disk_Numerator = 0;
	public static float Disk_Denominator = 2048;
	public static float Disk_Frames_Used = 0;
	public static float Disk_Frames_Available = 256;
	public static int Memory_Fragmentation = 0;
	public static float Disk_Fragmentation = 0;
	public static String Output;
	public static String outfile = "output.txt";
	public static String trace_file = "tracefile.txt";
	public static int Page_Faults = 0;

	// Method to convert Hexadecimal number to Decimal number
	public static int Hex_to_Dec(String hex) {
		int decimal = Integer.parseInt(hex, 16);
		return decimal;
	}

	// Method to convert Hexadecimal number to Binary Number
	public static String Hex_to_Bin(String hex) {
		int decimal = Integer.parseInt(hex, 16);
		String binary = Integer.toBinaryString(decimal);
		// Append 0's at the beginning if the length of the string is less than
		// 16
		while (binary.length() < 16) {
			binary = "0" + binary;
		}
		return binary;
	}

	// Method to convert Hexadecimal number to 8 bit Binary Number
	public static String Hex_to_Bin_8_bit(String hex) {

		if (!hex.matches("-?[0-9a-fA-F]+")) {
			ERROR_HANDLER.ERROR(103);
		}

		int decimal = Integer.parseInt(hex, 16);
		String binary = Integer.toBinaryString(decimal);
		// Pad 0's to make the length of the string to 8
		while (binary.length() < 8) {
			binary = "0" + binary;
		}
		return binary;
	}

	public static String Hex_to_Bin_16_bit(String hex) {
		int decimal = Integer.parseInt(hex, 16);
		String binary = Integer.toBinaryString(decimal);
		// Pad 0's to make the length of the string to 8
		while (binary.length() < 16) {
			binary = "0" + binary;
		}
		return binary;
	}

	//// Method to convert Decimal number to binary number
	public static String Dec_to_Bin_16_bit(int Dec) {
		String binary = Integer.toBinaryString(Dec);
		// Trim the left most elements if the length of the string is greater
		// than 16
		if (binary.length() > 16) {
			binary = binary.substring((binary.length() - 16), binary.length());
		}
		// Append 0's at the beginning if the length of the string is less than
		// 16
		while (binary.length() < 16) {
			binary = "0" + binary;
		}
		return binary;
	}

	//// Method to convert Decimal number to binary 8 bit number
	public static String Dec_to_Bin_8_bit(int Dec) {
		String binary = Integer.toBinaryString(Dec);
		// Pad 0's to make the length of the string to 8
		while (binary.length() < 8) {
			binary = "0" + binary;
		}
		return binary;
	}

	// Method to convert binary to decimal number
	public static int Bin_to_Dec(String bin) {
		int decimalValue = Integer.parseInt(bin, 2);
		return decimalValue;
	}

	// Method to convert decimal to hxadecimal
	public static String Dec_to_Hex(int dec) {
		String hex_value = Integer.toHexString(dec);
		while (hex_value.length() < 2) {
			hex_value = "0" + hex_value;
		}
		return hex_value;
	}

	// Method to convert Binary to Hexadecimal
	public static String Bin_to_Hex(String bin) {
		if (bin.equals(null)) {
			return null;
		}
		int decimal = Integer.parseInt(bin, 2);
		String hexStr = Integer.toString(decimal, 16);
		while (hexStr.length() < 4) {
			hexStr = "0" + hexStr;
		}
		return hexStr;

	}

	// Method to convert the two's complement binary number to negative number
	public static int twosComplement(String binaryInt) {

		if (binaryInt.charAt(0) == '1') {

			String invertedInt = invertbits(binaryInt);

			int decimalValue = Integer.parseInt(invertedInt, 2);

			decimalValue = (decimalValue + 1) * -1;

			return decimalValue;
		} else {

			return Integer.parseInt(binaryInt, 2);
		}
	}

	// Invert the bits if one's complement occurs
	public static String invertbits(String binaryInt) {
		String result = binaryInt;
		result = result.replace("0", " ");
		result = result.replace("1", "0");
		result = result.replace(" ", "1");
		return result;
	}

	// Main Method
	public static void main(String[] args) {
		FileName = "/Users/hemanth/Desktop/Error_Jobs_2/encryption_trace_off.txt";
		File out = new File(outfile);
		if (out.exists()) {
			out.delete();
		}
		DISK disk = new DISK();

		INPUT_SPOOLING inputspooling = new INPUT_SPOOLING();
		inputspooling.openFile();
		inputspooling.ReadFile();
		inputspooling.closeFile();
		inputspooling.Disk_Segmenting();
		LOADER loader = new LOADER();
		MEMORY memory = new MEMORY();
		PCB pcb = new PCB();
		FMBV fmbv = new FMBV();
		fmbv.FMBV_Initialize();
		LOADER.First_Page_Loading();
		CPU cpu = new CPU();
		CPU.CPU(PC, Trace_Flag);

	}
}
