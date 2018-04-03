package operatingsystems;

/*
 DISK is a secondary memory in any computer system while MEMORY is the primary memory .Every Job has to be 
 completely spooled into the DISK before being used by any other component like CPU.After a Job has been loaded into 
 the DISK it should be divided into Pages of size 8 words each.The DISK is divided into 3 segments mainly Program Segment
 ,Input Segment and Output Segment.
*/



import java.io.*;
import java.util.*;
import java.util.Scanner;
import java.lang.*;
import java.io.File;
import java.io.BufferedWriter;
import java.io.IOException;



public class DISK extends SYSTEM
{
//Creating a Disk Array of Size 8 times the size of the Main Memory	
public static String[] DISK = new String[2048];
}