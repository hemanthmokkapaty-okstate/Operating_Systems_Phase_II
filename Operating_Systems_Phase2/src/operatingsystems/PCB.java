package operatingsystems;

import java.io.*;
import java.lang.*;
import java.math.*;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;




public class PCB extends SYSTEM
{	
	public static PMT program_map_table;
	public static PMT Input_map_table;
	public static PMT Output_map_table;
	public static SMT Segment_Map_Table;
	//PMT pmt = new PMT();
	SMT [] smt = new SMT[3];



}








class FMBV
{
public static boolean[] FMBV = new boolean[32];	
	public static void FMBV_Initialize()
	{
		FMBV[4] = true;
		FMBV[7] = true;
		FMBV[9] = true;
		FMBV[16] = true;
		FMBV[19] = true;
	    FMBV[30] = true;	
	}
}
/*
class SMT
{
	public static int segment_code;
	public static PMT[] pmt_reference;
	SMT(int segment_code,PMT[] pmt_reference)
	{
		this.segment_code = segment_code;
		this.pmt_reference = pmt_reference;
	}
	static SMT[] segment_map_table = new SMT[3];
}
*/
/*
class PMT
{
	public static int page_no;
	public static int frame_no;
	public static int ref_bit;
	public static int dirty_bit;
	
	
	PMT(int page_no,int frame_no,int ref_bit,int dirty_bit)
	{
		this.page_no = page_no;
		this.frame_no = frame_no;
		this.ref_bit = ref_bit;
		this.dirty_bit = dirty_bit;
		
	}
	static PMT[] pmt1 = new PMT[DISK.Program_Segment_Length];
	
*/
	


