package operatingsystems;

/*
Description:The Loader will be responsible for loading each user program into Main memory.
Each user program is loaded into main memory from location 0.A User program has to be converted from 
HEX to BINARY(Either explicitly or implicitly).We created a String Buffer that can load the Binary data 
and in turn load the data into Main Memory. 
 */


import java.io.*;
import java.lang.*;
import java.math.*;
import java.io.File;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.*;


//LOADER class that loads the data from the File System
public class LOADER extends SYSTEM {
 
	
	
	LOADER()
	{
		
	}
	
	
	public static void First_Page_Loading()
	{
		Page_Number = (PC/8)+1;
		System.out.println("Page_Number:"+Page_Number);
		Page_Index = (PC/8)*8;
		System.out.println("Page_Index:"+Page_Index);
		
		int fmbv_index =0;
		while(FMBV.FMBV[fmbv_index]!=true)
		{
			fmbv_index++;
		}
		fmbv_index = fmbv_index +1;
		Frame_Number = fmbv_index;
		Frame_Index = fmbv_index*8;
		System.out.println("Frame_Number:"+Frame_Number);
		System.out.println("Frame_Index:"+Frame_Index);	
		
		for(int i=0;i<8;i++)
		{
			MEMORY.MEM[Frame_Index] = DISK.DISK[Page_Index];
			Frame_Index++;
			Page_Index++;
		}
		FMBV.FMBV[fmbv_index-1] = false;
		
		for(int i=0;i<100;i++)
		{
			System.out.println(i+" "+MEMORY.MEM[i]);
		}
		 
		for(int i =0;i<32;i++)
		{
		System.out.println(i+" "+FMBV.FMBV[i]);
		}	
		
		Total_Frames = Math.min(6, (DISK.Program_Segment_Length)+2);
		System.out.println("Total Frames needed:"+Total_Frames);
		PMT[] pmt1 = new PMT[Total_Frames];
		pmt1[0] = new PMT(Page_Number,Frame_Number,true,false);
		
		System.out.println("Page map table values:"+pmt1[0].page_no+ " "+pmt1[0].frame_no+" "+pmt1[0].ref_bit+" "+pmt1[0].dirty_bit);
		SMT[] segment_map_table = new SMT[3];
		segment_map_table[0] = new SMT(0,pmt1);
		System.out.println("segment map table values:"+segment_map_table[0].segment_code+" "+segment_map_table[0].pmt_reference[0].page_no+" "+segment_map_table[0].pmt_reference[0].frame_no+" "+segment_map_table[0].pmt_reference[0].ref_bit+" "+segment_map_table[0].pmt_reference[0].dirty_bit);	
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	//ArrayList to store the each line of the input job
	public static ArrayList<String> lines = new ArrayList<String>();
	// ArrayList to store the hexadecimal format of numbers
	public static ArrayList<String> HD = new ArrayList<String>();
	//ArrayList to store the Binary format of numbers
	public static ArrayList<String> BIN = new ArrayList<String>();
	
	//Default LOADER Constructor to Initialize variables used in the system
	LOADER()
	{
		 
		 String each_line = null;

	        try {
	            // FileReader reads text files in the default encoding.
	            FileReader fileReader = 
	                new FileReader(FileName);

	            // Always wrap FileReader in BufferedReader.
	            BufferedReader bufferedReader = 
	                new BufferedReader(fileReader);

	            while((each_line = bufferedReader.readLine()) != null) {
	                
	            	if(each_line.length()>16)
	            	{
	            		ERROR_HANDLER.ERROR(102);
	            	}
	                lines.add(each_line);       
	            }   
	            
	            String first_line = lines.get(0);
	            String[] First_line_values = first_line.split("\\s+");
	            Job_Id = Hex_to_Dec(First_line_values[0]);
	            if(Job_Id!=1)
	            {
	            	ERROR_HANDLER.ERROR(106);
	            }
	            Base_Address = Hex_to_Dec(First_line_values[1]);
	            if(Base_Address!=0)
	            {
	            	ERROR_HANDLER.ERROR(104);
	            }
	            PC = Hex_to_Dec(First_line_values[2]);
	            Program_length = Hex_to_Dec(First_line_values[3]);
	            if(PC>Program_length)
	            {
	            	ERROR_HANDLER.ERROR(2);
	            }
	            Trace_Flag = Hex_to_Dec(First_line_values[4]);     
	            if(Trace_Flag!=1&&Trace_Flag!=0)
	            {
	            	ERROR_HANDLER.ERROR(107);
	            }
	            
	            // Always close files.
	            bufferedReader.close();         
	        }
	        catch(FileNotFoundException ex) {
	        ERROR_HANDLER.ERROR(101);
	        }
	        catch(IOException ex) {
	        	ERROR_HANDLER.ERROR(101);
	        }
	        catch(IndexOutOfBoundsException ex)
	        {
	        	
	        }
	    }
	
	//Loading the hexadecimal elements into the HexaDecimal Arraylist
	public void HexLoad()
	{
		String one_line="";
		for(int line_index=1;line_index<lines.size();line_index++)
		{
		 one_line=one_line + lines.get(line_index);
		}
		int each_word=0;
		while(each_word<one_line.length())
		{
			if(one_line.length()%4!=0)
			{
				ERROR_HANDLER.ERROR(1);
			}
			HD.add(one_line.substring(each_word,Math.min(each_word+4,one_line.length())));
			each_word = each_word+4;
		}
		
		if(HD.size()!=Program_length)
		{
			ERROR_HANDLER.ERROR(105);
		}
		
		for(int i=0;i<HD.size();i++)
		{
			if(!HD.get(i).matches("-?[0-9a-fA-F]+"))
			{
				ERROR_HANDLER.ERROR(103);
			}
			
			String first_word = Hex_to_Bin_8_bit(HD.get(i).substring(0, 2));
			String second_word = Hex_to_Bin_8_bit(HD.get(i).substring(2,4));
			BIN.add(first_word+second_word);
		}	
	}
*/	
		 
	}
	
	

	
	
	
	
	
	

