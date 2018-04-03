package operatingsystems;

import java.io.*;
import java.lang.*;
import java.math.*;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class OUTPUT_SPOOLING extends SYSTEM
{

	OUTPUT_SPOOLING()
	{
		
	}
	
	public static void OUTPUT_SPOOLING(int jobid,int sysclock,int ioclock,int pageclock,int segmentclock,String output)
	{
		try (BufferedWriter b = new BufferedWriter(new FileWriter(outfile,true))) {
			   b.write("Cumulative Job Identification number :"+jobid+"(DEC)");
			   b.newLine();
			   b.write("Nature of termination : Normal");
			   b.newLine();
			   b.write("Input Data Segment for Job Id "+(jobid)+" :");
			   b.newLine();
			   for(int i=0;i<CPU.Input_Segment_Words.size();i++)
			   {
				   b.write(CPU.Input_Segment_Words.get(i));
				   b.write("(BIN)");
				   b.newLine();   
			   }
			   b.write("Output Data Segment for Job Id "+(jobid)+" :");
			   b.newLine();
			   for(int i=0;i<CPU.Output_Segment_Words.size();i++)
			   {
				   b.write(CPU.Output_Segment_Words.get(i));
				   b.write("(BIN)");
				   b.newLine();   
			   }
			   String sclock;
			   int totalclock = sysclock +pageclock + segmentclock;
			   sclock = Integer.toHexString(totalclock);
			   b.write("Clock value at termination: " + sclock+" (HEX)");
			   b.newLine();
			   b.write("Run time for the job");
			   b.newLine();
			   b.write("Execution time: " + (sysclock-ioclock) + "(DECIMAL)");
			   b.newLine();
			   b.write("Input/output time: " + ioclock + "(DECIMAL)");
			   b.newLine();
			   b.write("Page fault handling time: " + pageclock + "(DECIMAL)");
			   b.newLine();
			   b.write("Segment fault handling time: " + segmentclock + "(DECIMAL)");
			   b.newLine();
			   b.write("Memory Utilization:");
			   b.newLine();
			   b.write("Ratio:");
			   b.newLine();
			   b.write("Percentage:");
			   b.newLine();
			   b.write("DISK Utilization:");
			   b.newLine();
			   b.write("Ratio:");
			   b.newLine();
			   b.write("Percentage:");
			   b.newLine();
			   b.write("Memory Fragmentation:");
			   b.newLine();
			   b.write("DISK Fragmentation:");
			   b.newLine();
			   b.close();
			  } catch (IOException e) {
			   e.printStackTrace();
			  }
	}
	
	
	public static void VtuPrint()
	{
		try(BufferedWriter b = new BufferedWriter(new FileWriter(outfile,true)))
		{
			b.write("Page fault called here");
					
		}
		catch (IOException e) {
			   e.printStackTrace();
			  }
	}
	
	
	
	
}