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