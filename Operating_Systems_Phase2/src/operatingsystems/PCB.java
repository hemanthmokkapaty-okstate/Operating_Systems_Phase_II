package operatingsystems;

import java.io.*;
import java.lang.*;
import java.math.*;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;




public class PCB extends SYSTEM
{	
}
class FMBV
{
public static boolean[] FMBV = new boolean[32];	
	public void FMBV_Initialize()
	{
		FMBV[4] = true;
		FMBV[7] = true;
		FMBV[9] = true;
		FMBV[16] = true;
		FMBV[19] = true;
	    FMBV[30] = true;	
	}
}
class SMT
{
	public static int segment_code;
	public static PMT[] pmt_reference;
	SMT(int segment_code,PMT[] pmt_reference)
	{
		this.segment_code = segment_code;
		this.pmt_reference = pmt_reference;
	}
}
class PMT
{
	public static int page_no;
	public static int frame_no;
	public static boolean ref_bit;
	public static boolean dirty_bit;
	
	PMT(int page_no,int frame_no,boolean ref_bit,boolean dirty_bit)
	{
		this.page_no = page_no;
		this.frame_no = frame_no;
		this.ref_bit = ref_bit;
		this.dirty_bit = dirty_bit;
		
	}
	
	
}