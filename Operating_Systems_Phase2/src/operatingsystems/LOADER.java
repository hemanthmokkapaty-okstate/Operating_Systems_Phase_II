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
 
	public static PCB pcb = new PCB();
	public static int PC_Page_Replacement;
	public static int EA_Page_Replacement;
	public static String Temporary[] = new String[8];
	
	static ArrayList<Integer> pagesInMemory = new ArrayList();
	
	
	
	LOADER()
	{
		
	}
	
	
	public static void First_Page_Loading()
	{
		if(PC<8)
		{
			First_Page_Number = 0;
			First_Page_Index = 0;
		}
		else if(PC>8)
		{
			First_Page_Number = (PC/8);
			First_Page_Index = (PC/8)*8;
		}
		System.out.println("First Page_Number:"+First_Page_Number);
		System.out.println("First _Page_Index:"+First_Page_Index);
		
		/*int fmbv_index =0;
		while(FMBV.FMBV[fmbv_index]!=true)
		{
			fmbv_index++;
		}
		fmbv_index = fmbv_index +1;
		First_Frame_Number = fmbv_index;
		First_Frame_Index = (fmbv_index*8);
		System.out.println("First_Frame_Number:"+First_Frame_Number);
		System.out.println("First_Frame_Index:"+First_Frame_Index);	
		*/
		
		
		int frameNumber= PCB.FreeFrames.get(0);
		
		
		First_Frame_Index= frameNumber*8;
		
		
		pagesInMemory.add(First_Page_Number);
		
		for(int i=0;i<8;i++)
		{
			MEMORY.MEM[First_Frame_Index] = DISK.DISK[First_Page_Index];
			First_Frame_Index++;
			First_Page_Index++;
		}
		//FMBV.FMBV[fmbv_index-1] = false;
		
		Total_Frames = Math.min(6, (DISK.Program_Segment_Length)+2);
		System.out.println("Total Frames needed:"+Total_Frames);
		//Page_Index = First_Page_Index;
		PC_Page_Number = First_Page_Number;
		//Frame_Index = First_Frame_Index;
		PC_Frame_Number = frameNumber;
		LOADER.pcb.smt[0] = new SMT();
		
		for(int i=0;i<DISK.Program_Segment_Length;i++)
		{
			LOADER.pcb.smt[0].pmt.add(new PMT(i,-1,0,0,0));
		}
		
		LOADER.pcb.smt[0].pmt.get(PC_Page_Number).page_no = PC_Page_Number;
		LOADER.pcb.smt[0].pmt.get(PC_Page_Number).frame_no = frameNumber;
		LOADER.pcb.smt[0].pmt.get(PC_Page_Number).valid_bit = 1;
		LOADER.pcb.smt[0].pmt.get(PC_Page_Number).ref_bit = 0;
		LOADER.pcb.smt[0].pmt.get(PC_Page_Number).dirty_bit = 0;
		
		for(int i=0;i<DISK.Program_Segment_Length;i++)
		{
			System.out.println("PMT Values:"+LOADER.pcb.smt[0].pmt.get(i).page_no+" "+LOADER.pcb.smt[0].pmt.get(i).frame_no+ " "+LOADER.pcb.smt[0].pmt.get(i).ref_bit+" "+LOADER.pcb.smt[0].pmt.get(i).dirty_bit);
		}
		
		
		PCB.FreeFrames.remove(0);
		
	}
	
	public static String Input_Loading()
	{
	int input_page_Index = DISK.Input_Start_Index;
	//int fmbv_index =0;
	int pageNumber1= input_page_Index/8;
	
	
	int frame_Number = PCB.FreeFrames.get(0);
	
	
	
	/*
	while(FMBV.FMBV[fmbv_index]!=true)
	{
		fmbv_index++;
	}
	fmbv_index = fmbv_index+1;
	*/
	
	//int final_input_index =fmbv_index;
	int input_frame_index = frame_Number*8;
	
	System.out.println("Input frame Index:"+frame_Number);
	for(int i=0;i<8;i++)
	{
		MEMORY.MEM[input_frame_index] = DISK.DISK[input_page_Index];
		input_frame_index++;
		input_page_Index++;
	}
	String input_value = MEMORY.MEM[frame_Number*8];
	System.out.println("Input_Value Is:"+input_value);
	/*
	for(int i=0;i<100;i++)
	{
		System.out.println(i+" "+MEMORY.MEM[i]);
	}
	*/
	FMBV.FMBV[frame_Number-1] = false;
	for(int i =0;i<32;i++)
	{
	System.out.println(i+" "+FMBV.FMBV[i]);
	}	
	LOADER.pcb.smt[1] = new SMT();	
	LOADER.pcb.smt[1].pmt.add(new PMT(1,-1,0,0,0));
	LOADER.pcb.smt[1].pmt.get(0).page_no =  DISK.Input_Start_Index/8;
	LOADER.pcb.smt[1].pmt.get(0).frame_no = frame_Number;
	LOADER.pcb.smt[1].pmt.get(0).valid_bit = 1;
	LOADER.pcb.smt[1].pmt.get(0).ref_bit = -1;
	LOADER.pcb.smt[1].pmt.get(0).dirty_bit = 0;
	
	System.out.println("SMT 1 Values:"+LOADER.pcb.smt[1].pmt.get(0).page_no+" "+LOADER.pcb.smt[1].pmt.get(0).frame_no+ " "+LOADER.pcb.smt[1].pmt.get(0).ref_bit+" "+LOADER.pcb.smt[1].pmt.get(0).dirty_bit);
	
	pagesInMemory.add(pageNumber1);
	
	PCB.FreeFrames.remove(0);
	
	return input_value;
	}	
	
	
	
	public static void Page_Fault_Handling_PC(int address)
	{
		PC_Page_Replacement = address;
		int page_number = (address/8);
		PC_Page_Number = page_number; 
		int page_index = page_number*8;
		PC_Page_Index = page_index;
		int fmbv_index =0;
		int null_pointer=0;
		while(FMBV.FMBV[fmbv_index]!= true)
		{
			fmbv_index++;
			null_pointer++;
			if(null_pointer==32)
			{
				break;
			}
			
		}
		if(null_pointer == 32)
		{
			System.out.println("Frames filled by PC");
			
			FMBV.FMBV_Initialize();
			int fmbv_replaced_index =0;
			while(FMBV.FMBV[fmbv_replaced_index]!= true)
			{
				fmbv_replaced_index++;
			}
			fmbv_replaced_index = fmbv_replaced_index+1;
			
		
			
			int temporary_page_index =0;
			int temporary_frame_index = fmbv_replaced_index *8;
			for(int i =0;i<DISK.Program_Segment_Length;i++)
			{
				if(LOADER.pcb.smt[0].pmt.get(i).frame_no == fmbv_replaced_index)
				{
					temporary_page_index = i*8;
				}
			}
			for(int i=0;i<8;i++)
			{
				Temporary[i] = MEMORY.MEM[temporary_frame_index];
				temporary_frame_index++;
			}
			
			for(int i=0;i<8;i++)
			{
				DISK.DISK[temporary_page_index] = Temporary[i];
				temporary_page_index++;
				
			}
			
			
			
			int final_frame_number = fmbv_replaced_index;
			int frame_number = fmbv_replaced_index;
			int frame_index = frame_number*8;
			PC_Frame_Number = frame_number;
			PC_Frame_Index = frame_index;
			for(int i=0;i<DISK.Program_Segment_Length;i++)
			{
				
				if(LOADER.pcb.smt[0].pmt.get(i).frame_no== PC_Frame_Number)
				{
					LOADER.pcb.smt[0].pmt.get(i).frame_no= -1;
					LOADER.pcb.smt[0].pmt.get(i).valid_bit = 0;
				}
				//System.out.println("PMT Values:"+LOADER.pcb.smt[0].pmt.get(i).page_no+" "+LOADER.pcb.smt[0].pmt.get(i).frame_no+ " "+LOADER.pcb.smt[0].pmt.get(i).valid_bit+" "+LOADER.pcb.smt[0].pmt.get(i).ref_bit+" "+LOADER.pcb.smt[0].pmt.get(i).dirty_bit);
			}
			
			
			for(int i=0;i<8;i++)
			{
				MEMORY.MEM[frame_index] = DISK.DISK[page_index];
				frame_index++;
				page_index++;
			}
			FMBV.FMBV[fmbv_replaced_index-1] = false;
			LOADER.pcb.smt[0].pmt.get(PC_Page_Number).page_no = PC_Page_Number;
			LOADER.pcb.smt[0].pmt.get(PC_Page_Number).frame_no = final_frame_number;
			LOADER.pcb.smt[0].pmt.get(PC_Page_Number).valid_bit = 1;
			LOADER.pcb.smt[0].pmt.get(PC_Page_Number).ref_bit = 0;
			LOADER.pcb.smt[0].pmt.get(PC_Page_Number).dirty_bit = 0;
			for(int i=0;i<DISK.Program_Segment_Length;i++)
			{
				System.out.println("PMT Values:"+LOADER.pcb.smt[0].pmt.get(i).page_no+" "+LOADER.pcb.smt[0].pmt.get(i).frame_no+ " "+LOADER.pcb.smt[0].pmt.get(i).valid_bit+" "+LOADER.pcb.smt[0].pmt.get(i).ref_bit+" "+LOADER.pcb.smt[0].pmt.get(i).dirty_bit);
			}	
			
		}
		else if(null_pointer!=32)
		{
		fmbv_index = fmbv_index+1;
		int final_frame_number = fmbv_index;
		int frame_number = fmbv_index;
		int frame_index = frame_number*8;
		
		
		int temporary_page_index =0;
		int temporary_frame_index = fmbv_index *8;
		for(int i =0;i<DISK.Program_Segment_Length;i++)
		{
			if(LOADER.pcb.smt[0].pmt.get(i).frame_no == frame_number)
			{
				temporary_page_index = i*8;
			}
		}
		for(int i=0;i<8;i++)
		{
			Temporary[i] = MEMORY.MEM[temporary_frame_index];
			temporary_frame_index++;
		}
		
		for(int i=0;i<8;i++)
		{
			DISK.DISK[temporary_page_index] = Temporary[i];
			temporary_page_index++;
			
		}
		
		
		
		PC_Frame_Number = frame_number;
		PC_Frame_Index = frame_index;
		
		for(int i=0;i<DISK.Program_Segment_Length;i++)
		{
			
			if(LOADER.pcb.smt[0].pmt.get(i).frame_no== PC_Frame_Number)
			{
				LOADER.pcb.smt[0].pmt.get(i).frame_no= -1;
				LOADER.pcb.smt[0].pmt.get(i).valid_bit = 0;
			}
			//System.out.println("PMT Values:"+LOADER.pcb.smt[0].pmt.get(i).page_no+" "+LOADER.pcb.smt[0].pmt.get(i).frame_no+ " "+LOADER.pcb.smt[0].pmt.get(i).valid_bit+" "+LOADER.pcb.smt[0].pmt.get(i).ref_bit+" "+LOADER.pcb.smt[0].pmt.get(i).dirty_bit);
		}
		
		
		for(int i=0;i<8;i++)
		{
			MEMORY.MEM[frame_index] = DISK.DISK[page_index];
			frame_index++;
			page_index++;
		}
		FMBV.FMBV[fmbv_index-1] = false;
		LOADER.pcb.smt[0].pmt.get(PC_Page_Number).page_no = PC_Page_Number;
		LOADER.pcb.smt[0].pmt.get(PC_Page_Number).frame_no = final_frame_number;
		LOADER.pcb.smt[0].pmt.get(PC_Page_Number).valid_bit = 1;
		LOADER.pcb.smt[0].pmt.get(PC_Page_Number).ref_bit = 0;
		LOADER.pcb.smt[0].pmt.get(PC_Page_Number).dirty_bit = 0;
		for(int i=0;i<DISK.Program_Segment_Length;i++)
		{
			System.out.println("PMT Values:"+LOADER.pcb.smt[0].pmt.get(i).page_no+" "+LOADER.pcb.smt[0].pmt.get(i).frame_no+ " "+LOADER.pcb.smt[0].pmt.get(i).valid_bit+" "+LOADER.pcb.smt[0].pmt.get(i).ref_bit+" "+LOADER.pcb.smt[0].pmt.get(i).dirty_bit);
		}
		}
		
	}
	
	
	
	
	
	
	
	public static void Page_Fault_Handling_EA(int address)
	{	int high =  LOADER.pcb.smt[0].pmt.get(0).frame_no;
		EA_Page_Replacement = address;
		int page_number = (address/8);
		EA_Page_Number = page_number; 
		int page_index = page_number*8;
		EA_Page_Index = page_index;
		int fmbv_index =0;
		int null_pointer=0;
		while(FMBV.FMBV[fmbv_index]!= true)
		{
			fmbv_index++;
			null_pointer++;
			if(null_pointer == 32)
			{
				break;
			}	
		}
		
		if(null_pointer == 32)
		{
			System.out.println("Frames filled by EA");
			
			FMBV.FMBV_Initialize();
			int fmbv_replaced_index =0;
			while(FMBV.FMBV[fmbv_replaced_index]!= true)
			{
				fmbv_replaced_index++;
			}
			fmbv_replaced_index = fmbv_replaced_index+1;
			
			
			
			
			
			
			//Storing the value of the page to be replaced into the buffer and then back to the disk
			
			
			// copying to disk
			
			int temporary_page_index =0;
			int temporary_frame_index = fmbv_replaced_index *8;
			for(int i =0;i<DISK.Program_Segment_Length;i++)
			{
				if(LOADER.pcb.smt[0].pmt.get(i).frame_no == fmbv_replaced_index)
				{
					temporary_page_index = i*8;
				}
			}
			for(int i=0;i<8;i++)
			{
				Temporary[i] = MEMORY.MEM[temporary_frame_index];
				temporary_frame_index++;
			}
			
			for(int i=0;i<8;i++)
			{
				DISK.DISK[temporary_page_index] = Temporary[i];
				temporary_page_index++;
				
			}
			
			//end of temporary code
			
			
			FMBV.FMBV[fmbv_replaced_index-1] = false;
			int final_frame_number = fmbv_replaced_index;
			int frame_number = fmbv_replaced_index;
			int frame_index = frame_number*8;
			EA_Frame_Number = frame_number;
			EA_Frame_Index = frame_index;
			
		
			
			
			for(int i=0;i<DISK.Program_Segment_Length;i++)
			{
				
				if(LOADER.pcb.smt[0].pmt.get(i).frame_no== EA_Frame_Number)
				{
					LOADER.pcb.smt[0].pmt.get(i).frame_no= -1;
					LOADER.pcb.smt[0].pmt.get(i).valid_bit = 0;
					
					
					
					
					
					//change
					
					
				}
				//System.out.println("PMT Values:"+LOADER.pcb.smt[0].pmt.get(i).page_no+" "+LOADER.pcb.smt[0].pmt.get(i).frame_no+ " "+LOADER.pcb.smt[0].pmt.get(i).valid_bit+" "+LOADER.pcb.smt[0].pmt.get(i).ref_bit+" "+LOADER.pcb.smt[0].pmt.get(i).dirty_bit);
			}
			
			//LOADER.pcb.smt[0].pmt.get(i).frame_no== EA_Frame_Number
			
		
			
			
			
			
			
			for(int i=0;i<8;i++)
			{
				MEMORY.MEM[frame_index] = DISK.DISK[page_index];
				frame_index++;
				page_index++;
			}
			//FMBV.FMBV[fmbv_index-1] = false;
			LOADER.pcb.smt[0].pmt.get(EA_Page_Number).page_no = EA_Page_Number;
			LOADER.pcb.smt[0].pmt.get(EA_Page_Number).frame_no = final_frame_number;
			LOADER.pcb.smt[0].pmt.get(EA_Page_Number).valid_bit = 1;
			LOADER.pcb.smt[0].pmt.get(EA_Page_Number).ref_bit = 0;
			LOADER.pcb.smt[0].pmt.get(EA_Page_Number).dirty_bit = 0;
			for(int i=0;i<DISK.Program_Segment_Length;i++)
			{
				System.out.println("PMT Values:"+LOADER.pcb.smt[0].pmt.get(i).page_no+" "+LOADER.pcb.smt[0].pmt.get(i).frame_no+ " "+LOADER.pcb.smt[0].pmt.get(i).valid_bit+" "+LOADER.pcb.smt[0].pmt.get(i).ref_bit+" "+LOADER.pcb.smt[0].pmt.get(i).dirty_bit);
			}
			
		}
		
		else if(null_pointer !=32)
		{
			
			
			
			
			
		fmbv_index = fmbv_index+1;	
		int final_frame_number = fmbv_index;
		int frame_number = fmbv_index;
		int frame_index = frame_number*8;
		
		int temporary_page_index =0;
		int temporary_frame_index = fmbv_index *8;
		for(int i =0;i<DISK.Program_Segment_Length;i++)
		{
			if(LOADER.pcb.smt[0].pmt.get(i).frame_no == frame_number)
			{
				temporary_page_index = i*8;
			}
		}
		for(int i=0;i<8;i++)
		{
			Temporary[i] = MEMORY.MEM[temporary_frame_index];
			temporary_frame_index++;
		}
		
		for(int i=0;i<8;i++)
		{
			DISK.DISK[temporary_page_index] = Temporary[i];
			temporary_page_index++;
			
		}
		
		
		
		
		
		
		
		
		
		
		
		EA_Frame_Number = frame_number;
		EA_Frame_Index = frame_index;
		for(int i=0;i<DISK.Program_Segment_Length;i++)
		{
			
			if(LOADER.pcb.smt[0].pmt.get(i).frame_no== EA_Frame_Number)
			{
				LOADER.pcb.smt[0].pmt.get(i).frame_no= -1;
				LOADER.pcb.smt[0].pmt.get(i).valid_bit = 0;
			}
			//System.out.println("PMT Values:"+LOADER.pcb.smt[0].pmt.get(i).page_no+" "+LOADER.pcb.smt[0].pmt.get(i).frame_no+ " "+LOADER.pcb.smt[0].pmt.get(i).valid_bit+" "+LOADER.pcb.smt[0].pmt.get(i).ref_bit+" "+LOADER.pcb.smt[0].pmt.get(i).dirty_bit);
		}
		for(int i=0;i<8;i++)
		{
			MEMORY.MEM[frame_index] = DISK.DISK[page_index];
			frame_index++;
			page_index++;
		}
		FMBV.FMBV[fmbv_index-1] = false;
		LOADER.pcb.smt[0].pmt.get(EA_Page_Number).page_no = EA_Page_Number;
		LOADER.pcb.smt[0].pmt.get(EA_Page_Number).frame_no = final_frame_number;
		LOADER.pcb.smt[0].pmt.get(EA_Page_Number).valid_bit = 1;
		LOADER.pcb.smt[0].pmt.get(EA_Page_Number).ref_bit = 0;
		LOADER.pcb.smt[0].pmt.get(EA_Page_Number).dirty_bit = 0;
		for(int i=0;i<DISK.Program_Segment_Length;i++)
		{
			System.out.println("PMT Values:"+LOADER.pcb.smt[0].pmt.get(i).page_no+" "+LOADER.pcb.smt[0].pmt.get(i).frame_no+ " "+LOADER.pcb.smt[0].pmt.get(i).valid_bit+" "+LOADER.pcb.smt[0].pmt.get(i).ref_bit+" "+LOADER.pcb.smt[0].pmt.get(i).dirty_bit);
		}
		}
	}
	
	
	public static int New_Calculated_Address(int address)
	{
		
		int pagesize= address/8;
		
		EA_Frame_Number= LOADER.pcb.smt[0].pmt.get(pagesize).frame_no;
		
		
		/*
		for(int i=0;i<DISK.Program_Segment_Length;i++)
		{

			if(LOADER.pcb.smt[0].pmt.get(i).page_no == (address/8) && LOADER.pcb.smt[0].pmt.get(i).valid_bit == 1)
			{
            EA_Frame_Number = LOADER.pcb.smt[0].pmt.get(i).frame_no;
			}
		}
		*/
		
		
		int new_address = (EA_Frame_Number*8) + (address%8);
		return new_address;
	}
	
	public static void Segment_Fault_Handling()
	{
		
	}
	
	/*
	public static void Page_Replacement_PC()
	{
		System.out.println("Page Replacement needed now");
		int high =  LOADER.pcb.smt[0].pmt.get(0).frame_no;
		for(int i=1;i<DISK.Program_Segment_Length;i++)
		{
			if(LOADER.pcb.smt[0].pmt.get(i).frame_no>high)
			{
				high = LOADER.pcb.smt[0].pmt.get(i).frame_no;
			}
		}
		System.out.println("high value:"+high);
		FMBV.FMBV[high-1] = true;
		Page_Fault_Handling_PC(PC_Page_Replacement);
	}
	
	public static void Page_Replacement_EA()
	{
		System.out.println("Page Replacement needed now");
		int high =  LOADER.pcb.smt[0].pmt.get(0).frame_no;
		for(int i=1;i<DISK.Program_Segment_Length;i++)
		{
			if(LOADER.pcb.smt[0].pmt.get(i).frame_no>high)
			{
				high = LOADER.pcb.smt[0].pmt.get(i).frame_no;
			}
		}
		FMBV.FMBV[high-1] = true;
		System.out.println("high value:"+high);
		Page_Fault_Handling_EA(EA_Page_Replacement);

		
		
	}
	*/
	
	
	
	
	public static void pagefault_PC (int address)
	
	{
		PC_Page_Replacement = address;
		int page_number = (address/8);
		PC_Page_Number = page_number; 
		int page_index = page_number*8;
		PC_Page_Index = page_index;
		int diskAddress =0;
		int memoryAddress=0;
		int frameNumber=0;
		
		if (PCB.FreeFrames.size()>0)
		{
			//page fault
			LOADER.pcb.smt[0].pmt.get(PC_Page_Number).frame_no= PCB.FreeFrames.get(0);
			frameNumber= LOADER.pcb.smt[0].pmt.get(PC_Page_Number).frame_no;
			memoryAddress= frameNumber*8;
			diskAddress= PC_Page_Number*8;
			
			PCB.FreeFrames.remove(0);
			pagesInMemory.add(PC_Page_Number);
			
			//copy to memory
			for(int i=0; i<8;i++)
			{
				MEMORY.MEM[memoryAddress]= DISK.DISK[diskAddress]; 
				memoryAddress++;
				diskAddress++;
			}
			
			PC_Frame_Number=frameNumber;
			//EA_Frame_Number = frameNumber;
		}
		
		else{
			
			
			System.out.println("hi");
		
			for(int j=0; j< pagesInMemory.size();j++)
				
			{
				
					
				
					
					
					
					
					if (pagesInMemory.get(j)> DISK.Program_Segment_Length-1)
					{
						int frame_value2= LOADER.pcb.smt[1].pmt.get(0).frame_no;
						
						LOADER.pcb.smt[0].pmt.get(PC_Page_Number).frame_no= frame_value2;
						LOADER.pcb.smt[1].pmt.get(0).frame_no=-1;
						
						
						
						
						
						
						
						//copy to disk
						
						int diskAddress1;
						int memoryAddress2;
						
						diskAddress1= pagesInMemory.get(j)*8;
						memoryAddress2= frame_value2*8;
						
						for(int i=0; i<8;i++)
						{
							DISK.DISK[diskAddress1]= MEMORY.MEM[memoryAddress2]; 
							memoryAddress2++;
							diskAddress1++;
						}
						
						
						
						memoryAddress= frame_value2*8;
						diskAddress= PC_Page_Number*8;
						
						//copy to memory
						for(int i=0; i<8;i++)
						{
							MEMORY.MEM[memoryAddress]= DISK.DISK[diskAddress]; 
							memoryAddress++;
							diskAddress++;
						}
						
						//EA_Frame_Number= frame_value;
						pagesInMemory.remove(j);
						pagesInMemory.add(PC_Page_Number);
						PC_Frame_Number = frame_value2;
						
						
						
						
						
						
						
					}
					
					
					
					
					
					
					
					
					
					
					
				else
					
				
				
				
				
				
				
				
				
				
				{	
					//get frame nuber of first page of pages of memory
					int frame_value= LOADER.pcb.smt[0].pmt.get(pagesInMemory.get(j)).frame_no;
					// assign the frame number to PC_Page number
					
					LOADER.pcb.smt[0].pmt.get(PC_Page_Number).frame_no= frame_value;
					LOADER.pcb.smt[0].pmt.get(pagesInMemory.get(j)).frame_no=-1;
					
					
					
					
					//copy to disk
					
					int diskAddress1;
					int memoryAddress2;
					
					diskAddress1= pagesInMemory.get(j)*8;
					memoryAddress2= frame_value*8;
					
					for(int i=0; i<8;i++)
					{
						DISK.DISK[diskAddress1]= MEMORY.MEM[memoryAddress2]; 
						memoryAddress2++;
						diskAddress1++;
					}
					
					
					
					memoryAddress= frame_value*8;
					diskAddress= PC_Page_Number*8;
					
					//copy to memory
					for(int i=0; i<8;i++)
					{
						MEMORY.MEM[memoryAddress]= DISK.DISK[diskAddress]; 
						memoryAddress++;
						diskAddress++;
					}
					
					//EA_Frame_Number= frame_value;
					
					pagesInMemory.remove(j);
					pagesInMemory.add(PC_Page_Number);
					PC_Frame_Number = frame_value;
					
				}
					
					
					
					
					
					
					j= pagesInMemory.size();
				
			}
			
			
			
			
			
			
			
			
		}
		
		
		
	}
	
public static void pagefaut_EA(int address)
	
{
	PC_Page_Replacement = address;
	int page_number = (address/8);
	PC_Page_Number = page_number; 
	int page_index = page_number*8;
	PC_Page_Index = page_index;
	int diskAddress =0;
	int memoryAddress=0;
	int frameNumber=0;
	
	if (PCB.FreeFrames.size()>0)
	{
		//page fault
		LOADER.pcb.smt[0].pmt.get(PC_Page_Number).frame_no= PCB.FreeFrames.get(0);
		frameNumber= LOADER.pcb.smt[0].pmt.get(PC_Page_Number).frame_no;
		memoryAddress= frameNumber*8;
		diskAddress= PC_Page_Number*8;
		
		PCB.FreeFrames.remove(0);
		pagesInMemory.add(PC_Page_Number);
		
		//copy to memory
		for(int i=0; i<8;i++)
		{
			MEMORY.MEM[memoryAddress]= DISK.DISK[diskAddress]; 
			memoryAddress++;
			diskAddress++;
		}
		
		//PC_Frame_Number=frameNumber;
		EA_Frame_Number = frameNumber;
	}
	
	else{
		
	
		for(int j=0; j< pagesInMemory.size();j++)
			
		{
			
			System.out.println("First page Replacement");
			System.out.println(pagesInMemory.get(j));
			
			if(LOADER.pcb.smt[0].pmt.get(pagesInMemory.get(j)).ref_bit==0)
				
			{
				
				//get frame nuber of first page of pages of memory
				int frame_value= LOADER.pcb.smt[0].pmt.get(pagesInMemory.get(j)).frame_no;
				// assign the frame number to PC_Page number
				LOADER.pcb.smt[0].pmt.get(PC_Page_Number).frame_no= frame_value;
				 LOADER.pcb.smt[0].pmt.get(pagesInMemory.get(j)).frame_no=-1;
				
				
				pagesInMemory.remove(j);
				pagesInMemory.add(PC_Page_Number);
				
				//copy to disk
				
				int diskAddress1;
				int memoryAddress2;
				
				diskAddress1= pagesInMemory.get(j)*8;
				memoryAddress2= frame_value*8;
				
				for(int i=0; i<8;i++)
				{
					DISK.DISK[diskAddress1]= MEMORY.MEM[memoryAddress2]; 
					memoryAddress2++;
					diskAddress1++;
				}
				
				
				
				memoryAddress= frame_value*8;
				diskAddress= PC_Page_Number*8;
				
				//copy to memory
				for(int i=0; i<8;i++)
				{
					MEMORY.MEM[memoryAddress]= DISK.DISK[diskAddress]; 
					memoryAddress++;
					diskAddress++;
				}
				
				EA_Frame_Number= frame_value;
				//PC_Frame_Number = frame_value;
				
				j= pagesInMemory.size();
			}
		}
		
		
		
		
		
		
		
		
	}
}
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
		 
	
	
	

	
	
	
	
	
	

