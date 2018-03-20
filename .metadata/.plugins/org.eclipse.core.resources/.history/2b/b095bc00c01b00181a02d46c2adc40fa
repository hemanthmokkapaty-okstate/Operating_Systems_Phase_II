package operatingSystems;
import java.io.*;
import java.lang.*;
import java.math.*;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;




public class MEMORY extends SYSTEM 
{
	
	public static String Z;
	
	
	
	MEMORY()
	{
		
	}
	
	public static String[] MEM = new String[256];
	public static String[] Buffer = new String[4];
	
	public static int BIN_INDEX =0;
	public static int REDUCED_BIN_INDEX =0;
	
	public static int MEM_INDEX =0;
	public static int REDUCED_MEM_INDEX =0;

	
	public void Buffer_Loading(int bin_index,int mem_index)
	{
		try
		{
	for(int i=0;i<4;i++)
	{
		Buffer[i] = LOADER.BIN.get(BIN_INDEX);
		BIN_INDEX++;
	}
	
	
	
	for(int i=0;i<4;i++)
	{
	MEM[MEM_INDEX] = Buffer[i];
	MEM_INDEX++;
	}
	
	
	if(BIN_INDEX<LOADER.BIN.size())
	 {
		 Buffer_Loading(BIN_INDEX,MEM_INDEX);
		 
	 }
		}
		catch(IndexOutOfBoundsException ex)
        {
        	
        }

	}
	
	public static String MEMORY(String X,int Y,String Z)
	{
		if(X.equals("READ"))
		{
			Z = MEMORY.MEM[Y];
			
		}
		
		else if(X.equals("WRITE"))
		{
			
			
		}
		return Z;
	}
	
	
	public void Memory_print()
	{
		for(int i=0;i<LOADER.BIN.size();i++)
		{
			System.out.println(LOADER.HD.get(i));
			System.out.println(MEM[i]);
		}
	}	
	
}




