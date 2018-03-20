package operatingSystems;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class ERROR_HANDLER extends SYSTEM {
	
	 public static int Error_Number;
	 public static String message = "";

	 // The ERROR method will take the parameter an error number
	 public static void ERROR(int err) {
	  try (BufferedWriter b = new BufferedWriter(new FileWriter(outfile, true))) {
	   // If the error number is greater than 100 then it is a warning
	   if (err > 100) {
	    switch (err) {
	     
	    case 101:
	    	message = "Input File not found";
	    	outputerror(Job_Id,System_Clock,IO_Clock,message);
	    	System.out.println("Error:"+message);
	    	System.exit(0);
	      
	      break;
	     case 102:
	    	 	message = "Invalid loader Format:Job Lines should not exceed 16 hex digits";
		    	outputerror(Job_Id,System_Clock,IO_Clock,message);
		    	System.out.println("Error:"+message);
		    	System.exit(0);
	      break;
	     case 103:
	    	 	message = "Invalid loader Format:Invalid Hex code in input";
		    	outputerror(Job_Id,System_Clock,IO_Clock,message);
		    	System.out.println("Error:"+message);
		    	System.exit(0);
	      
	      break;
	    }
	   }
	   // If the error number is less than 100 then it is an error
	   else if (err < 100) {
	    switch (err) {
	     case 1:
	    	 	message = "Warning:The length of one of the words is not equal to 4";
		    	outputerror(Job_Id,System_Clock,IO_Clock,message);
		    	System.out.println("Warning:"+message);
		    System.exit(0);
	     
	      break;
	     case 2:
	    	 message = "Warning:PC value exceeds the length of the Program";
		    	outputerror(Job_Id,System_Clock,IO_Clock,message);
		    	System.out.println("Warning:"+message);
		    	System.exit(0);
	      break;
	     case 3:
	     
	     
	      break;
	     case 4:
	     
	      
	      break;
	     case 5:
	      
	      
	      break;
	    }
	   }
	  } catch (IOException e) {
	   e.printStackTrace();
	  }

	 }
	}


