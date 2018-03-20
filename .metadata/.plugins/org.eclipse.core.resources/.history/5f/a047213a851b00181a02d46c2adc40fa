package operatingSystems;
import java.util.Scanner;
import java.io.*;
import java.lang.*;
import java.math.*;
import java.io.File;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.*;




public class CPU extends SYSTEM {
public static String Instruction_Type_T;
public static String Unused;
public static String Op_Code;
public static String Displacement_Address;
public static String Index_bit;
public static String Input;
public static boolean Halt_Bit=false;
public static String[] Stack = new String[8];
public static int TOS=0;





CPU()
{

}

public static void CPU(int X,int Y)
{
	try
	{
	
	Instruction_Register=MEMORY.MEMORY("READ",X,null);
	System.out.println("\n");
	System.out.println("Program Counter:"+X);
	System.out.println("Instruction Number:"+(X+1));
	System.out.println("InstructionRegister:"+Instruction_Register);
	Instruction_Type_T = Instruction_Register.substring(0, 1);
	System.out.println("Instruction type:"+Instruction_Type_T);
	//PC = PC+1;
	if(Instruction_Type_T.equals("0"))
	{
		System_Clock = System_Clock + 1;
		String first_half = Instruction_Register.substring(0,8);
		if(!first_half.equals("00000000"))
		{
			while(first_half.length()<16)
			{
				first_half = "0"+first_half;
			}
			Unused = first_half.substring(9,11);
			Op_Code= first_half.substring(11,16);
			if(Op_Code.equals("10011"))
			{
				ZERO_RD();
			}
			else if(Op_Code.equals("10100"))
			{
				ZERO_WR();
				System.out.println(Stack[TOS]);
				Output=Stack[TOS];
				TOS = TOS-1;
				CPU(PC,Trace_Flag);
			}
			
		}
		else if(first_half.equals("00000000"))
		{
			System_Clock = System_Clock + 1;
			ZERO_NOP();
		}
		Unused = Instruction_Register.substring(9,11);
		Op_Code= Instruction_Register.substring(11,16);
		switch(Op_Code)
		{
		case "00000": 
			ZERO_NOP();
			break;
		case "00001":
			ZERO_OR();
			break;
		case "00010":
			ZERO_AND();
			break;
		case "00011":
			ZERO_NOT();
		  break;
		case "00100":
			ZERO_XOR();
			break;
		case "00101":
			ZERO_ADD();
			break;
		case "00110":
			ZERO_SUB();
			break;
		case "00111":
			ZERO_MUL();
			break;
		case "01000":
			ZERO_DIV();
			break;
		case "01001":
			ZERO_MOD();
			break;
		case "01010":
			ZERO_SL();
			break;
		case "01011":
			ZERO_SR();
			break;
		case "01100":
			ZERO_CPG();
			break;
		case "01101":
			ZERO_CPL();
			break;
		case "01110":
			ZERO_CPE();
			break;
		case "01111":
			ZERO_BR();
			break;
		case "10000":
			ZERO_BRT();
			break;
		case "10001":
			ZERO_BRF();
			break;
		case "10010":
			ZERO_CALL();
			break;
		case "10011":
			ZERO_RD();
			break;
		case "10100":
			ZERO_WR();
			break;
		case "10101":
			ZERO_RTN();
			break;
		case "10110":
			ZERO_PUSH();
			break;
		case "10111":
			ZERO_POP();
			break;
		case "11000":
			ZERO_HLT();
			break;
				
		}
		
	}
	else if(Instruction_Type_T.equals("1"))
	{
		System_Clock = System_Clock + 4;
		Unused = Instruction_Register.substring(7,9);
		Displacement_Address = Instruction_Register.substring(9,16);	
		Index_bit = Instruction_Register.substring(6,7);
		if(Index_bit.equals("0"))
		{
			int d_address = Bin_to_Dec(Displacement_Address);
			Effective_Address = d_address;
			System.out.println("Effective Address:"+Effective_Address);
		}
		else if(Index_bit.equals("1"))
		{
			int d_address = Bin_to_Dec(Displacement_Address);
			int Stack_Value = Bin_to_Dec(Stack[TOS]);
			Effective_Address = d_address + Stack_Value;
			System.out.println("Effective Address:"+Effective_Address);
		}
		Op_Code= Instruction_Register.substring(1,6);
		
		switch(Op_Code)
		{
		case "00000": 
			ONE_NOP();
			break;
		case "00001":
			ONE_OR();
			break;
		case "00010":
			ONE_AND();
			break;
		case "00011":
			ONE_NOT();
		  break;
		case "00100":
			ONE_XOR();
			break;
		case "00101":
			ONE_ADD();
			break;
		case "00110":
			ONE_SUB();
			break;
		case "00111":
			ONE_MUL();
			break;
		case "01000":
			ONE_DIV();
			break;
		case "01001":
			ONE_MOD();
			break;
		case "01010":
			ONE_SL();
			break;
		case "01011":
			ONE_SR();
			break;
		case "01100":
			ONE_CPG();
			break;
		case "01101":
			ONE_CPL();
			break;
		case "01110":
			ONE_CPE();
			break;
		case "01111":
			ONE_BR();
			break;
		case "10000":
			ONE_BRT();
			break;
		case "10001":
			ONE_BRF();
			break;
		case "10010":
			ONE_CALL();
			break;
		case "10011":
			ONE_RD();
			break;
		case "10100":
			ONE_WR();
			break;
		case "10101":
			ONE_RTN();
			break;
		case "10110":
			ONE_PUSH();
			break;
		case "10111":
			ONE_POP();
			break;
		case "11000":
			ONE_HLT();
			break;
				
		}
	}
	

}
catch(NullPointerException e)
{
	ERROR_HANDLER.ERROR(101);
}
	}
//}





public static void ZERO_NOP()
{
	//System_Clock = System_Clock +1;
	//CPU(PC,Trace_Flag);
}
public static void ZERO_OR()
{
	
}
public static void ZERO_AND()
{
	
}
public static void ZERO_NOT()
{
	
	PC = PC +1;
	String stack_value = Stack[TOS];
	System.out.println("Top of the stack value:"+stack_value);
	int dec_value = Bin_to_Dec(stack_value);
	System.out.println("Decimal Value:"+dec_value);
	int onescompliment = ~dec_value;
	System.out.println("1's compliment:"+onescompliment);
	String bin_ones_complement = Dec_to_Bin_16_bit(onescompliment);
	System.out.println("binary one's complement:"+bin_ones_complement);
	//String bin_onescomplement_16 = bin_ones_complement.substring(16,32);
	//System.out.println("binary one's complement 16 bit:"+bin_onescomplement_16);
	Stack[TOS] = bin_ones_complement;
	System.out.println("Top of the stack value:"+Stack[TOS]);
	CPU(PC,Trace_Flag);
	
}
public static void ZERO_XOR()
{
	
}
public static void ZERO_ADD()
{
	PC = PC +1;
	int top_stack = Bin_to_Dec(Stack[TOS]);
	int top_stack1 = Bin_to_Dec(Stack[TOS-1]);
	int add = (top_stack+top_stack1);
	String add_bin = Dec_to_Bin_16_bit(add);
	Stack[TOS-1] = add_bin;
	Stack[TOS] = null;
	TOS=TOS-1;
	CPU(PC,Trace_Flag);
}
public static void ZERO_SUB()
{
	
}
public static void ZERO_MUL()
{
	PC = PC+1;
	int top_stack = Bin_to_Dec(Stack[TOS]);
	int top_stack1 = Bin_to_Dec(Stack[TOS-1]);
	int mul = (top_stack*top_stack1);
	String mul_bin = Dec_to_Bin_16_bit(mul);
	Stack[TOS-1] = mul_bin;
	Stack[TOS] = null;
	TOS=TOS-1;
	CPU(PC,Trace_Flag);
	
}
public static void ZERO_DIV()
{
	
}
public static void ZERO_MOD()
{
	
}
public static void ZERO_SL()
{
	
	String stack_top = Stack[TOS];
	int stack_dec = Bin_to_Dec(stack_top);
	int shiftleft_decimal = stack_dec<<1;
	String shiftleft_bin = Dec_to_Bin_16_bit(shiftleft_decimal);
	Stack[TOS] = shiftleft_bin;
	System.out.println("Stack Of TOS:"+Stack[TOS]);
	PC = PC +1;
	CPU(PC,Trace_Flag);
}
public static void ZERO_SR()
{
	
}
public static void ZERO_CPG()
{
	
}

public static void ZERO_CPL()
{
	
}
public static void ZERO_CPE()
{
	
}

public static void ZERO_BR()
{
	
}
public static void ZERO_BRT()
{
	
}
public static void ZERO_BRF()
{
	
}
public static void ZERO_CALL()
{
	
}

public static void ZERO_RD()
{
	
	System_Clock = System_Clock +15;
	System.out.println("Enter the input number:");
	Scanner scanner = new Scanner(System.in); 
	Input = scanner.nextLine();
	int Input_Dec = Integer.parseInt(Input);

	String Bin_Input = Dec_to_Bin_16_bit(Input_Dec);
	System.out.println("Input Binary:"+Bin_Input);
	TOS = TOS+1;
	Stack[TOS] = Bin_Input;
	System.out.println("Top of the stack:"+Stack[TOS]);
	PC = PC+1;		
	CPU(PC,Trace_Flag);
}

public static void ZERO_WR()
{
	
	System_Clock = System_Clock +15;
	PC=PC+1;
	System.out.println("Output value:"+Stack[TOS]);
	TOS = TOS-1;
	CPU(PC,Trace_Flag);
	/*
	System.out.println(Stack[TOS]);
	Output=Stack[TOS];
	TOS = TOS-1;
	CPU(PC,Trace_Flag);
	*/
}


public static void ZERO_RTN()
{
	
	int pc_dec = Bin_to_Dec(Stack[TOS]);
	PC = pc_dec;
	System.out.println("PC value:"+PC);
	Stack[TOS] = null;
	TOS = TOS-1;
	CPU(PC,Trace_Flag);
	
}
public static void ZERO_PUSH()
{
	
}
public static void ZERO_POP()
{
	
}
public static void ZERO_HLT()
{
	
	System.out.println("Final Clock Value:"+System_Clock);
	output(Job_Id,System_Clock,IO_Clock,Output);
	System.exit(0);
}






public static void ONE_NOP()
{
	
}
public static void ONE_OR()
{
	
}
public static void ONE_AND()
{
	
	int dec_Stack = Bin_to_Dec(Stack[TOS]);
	System.out.println("Stack of TOS:"+dec_Stack);
	int dec_EA = Bin_to_Dec(MEMORY.MEM[Effective_Address]);
	System.out.println("Memory of Effective Address:"+dec_EA);
	int And_value = (dec_Stack & dec_EA);
	System.out.println("And Value"+And_value);
	String And_Binary = Dec_to_Bin_16_bit(And_value);
	System.out.println("And Binary:"+And_Binary);
	Stack[TOS] = And_Binary;
	PC = PC+1;
	CPU(PC,Trace_Flag);
	
}
public static void ONE_NOT()
{
	
}
public static void ONE_XOR()
{
	
}
public static void ONE_ADD()
{
	
	int Stack_dec = Bin_to_Dec(Stack[TOS]);
	int EA_dec = Bin_to_Dec(MEMORY.MEM[Effective_Address]);
	int Addition = Stack_dec + EA_dec;
	String Addition_binary = Dec_to_Bin_16_bit(Addition);
	Stack[TOS] = Addition_binary;
	System.out.println("Binary Addition Top of the stack:"+Stack[TOS]);
	PC = PC +1;
	CPU(PC,Trace_Flag);
}
public static void ONE_SUB()
{
	
}
public static void ONE_MUL()
{
	
}
public static void ONE_DIV()
{
	
}
public static void ONE_MOD()
{
	
}
public static void ONE_SL()
{
	
}
public static void ONE_SR()
{
	
}
public static void ONE_CPG()
{
	
	int dec_stack = getTwosComplement(Stack[TOS]);
	//int dec_stack = Bin_to_Dec(Stack[TOS]);
	System.out.println("Input Value into decimal:"+dec_stack);
	int dec_EA = Bin_to_Dec(MEMORY.MEM[Effective_Address]);
	if(dec_stack > dec_EA)
	{
		Stack[TOS+1] = "0000000000000001";
	}
	else if(dec_stack < dec_EA)
	{
		Stack[TOS+1] = "0000000000000000";
	}
	else if(dec_stack == dec_EA)
	{
		Stack[TOS+1] = "0000000000000001";
	}
	
	TOS = TOS+1;
	PC = PC+1;
	CPU(PC,Trace_Flag);
}

public static void ONE_CPL()
{
	
	
	int dec_stack = getTwosComplement(Stack[TOS]);
	
	
	//int dec_stack = Bin_to_Dec(Stack[TOS]);
	int dec_EA = Bin_to_Dec(MEMORY.MEM[Effective_Address]);
	if(dec_stack < dec_EA)
	{
		Stack[TOS+1] = "0000000000000001";
	}
	else if(dec_stack > dec_EA)
	{
		Stack[TOS+1] = "0000000000000000";
	}
	else if(dec_stack == dec_EA)
	{
		Stack[TOS+1] = "0000000000000001";
	}
	
	TOS = TOS+1;
	PC = PC+1;
	CPU(PC,Trace_Flag);
}
public static void ONE_CPE()
{
	
}

public static void ONE_BR()
{
	
	System.out.println("Stack Of TOS:"+Stack[TOS]);
	PC = Effective_Address;
	CPU(PC,Trace_Flag);
}
public static void ONE_BRT()
{
	
	if(Stack[TOS].equals("0000000000000001"))
	{
		PC = Effective_Address;
		Stack[TOS] = null;
		TOS = TOS-1;
		CPU(PC,Trace_Flag);
	}
	else if(Stack[TOS].equals("0000000000000000"))
	{
		PC = PC+1;
		Stack[TOS] = null;
		TOS = TOS-1;
		CPU(PC,Trace_Flag);
	}
}
public static void ONE_BRF()
{
	
	if(Stack[TOS].equals("0000000000000000"))
	{
		PC = Effective_Address;
		Stack[TOS] = null;
		TOS = TOS-1;
		CPU(PC,Trace_Flag);
	}
	else if(Stack[TOS].equals("0000000000000001"))
	{
		PC = PC+1;
		Stack[TOS] = null;
		TOS = TOS-1;
		CPU(PC,Trace_Flag);
	}
}
public static void ONE_CALL()
{
	
	PC = PC + 1;
	TOS = TOS +1;
	String pc_bin = Dec_to_Bin_16_bit(PC);
	System.out.println("Pc value in binary:"+pc_bin);
	Stack[TOS] = pc_bin;
	System.out.println("Stack Value:"+Stack[TOS]);
	PC = Effective_Address;
	CPU(PC,Trace_Flag);	
}

public static void ONE_RD()
{
	
	System_Clock = System_Clock +15;
}

public static void ONE_WR()
{
	
	System_Clock = System_Clock +15;
}

public static void ONE_RTN()
{
	
}
public static void ONE_PUSH()
{
	
	TOS = TOS+1;
	Stack[TOS] = MEMORY.MEMORY("READ",Effective_Address,null);
	System.out.println("Top of Stack:"+Stack[TOS]);
	System.out.println("Top of Stack-1:"+Stack[TOS-1]);
	System.out.println("Top of stack value:"+TOS);
	//System.out.println("Top of Stack-1:"+Stack[TOS-2]);
	PC = PC +1;
	CPU(PC,Trace_Flag);
	
}
public static void ONE_POP()
{
	MEMORY.MEM[Effective_Address] = Stack[TOS];
	System.out.println("Memory of Effective Address:"+MEMORY.MEM[Effective_Address]);
	Stack[TOS] = null;
	//System.out.println("S[tos]:"+Stack[TOS]);
	
	TOS = TOS-1;
	System.out.println("S[tos]:"+Stack[TOS]);
	PC = PC+1;
	
	CPU(PC,Trace_Flag);	
	
}
public static void ONE_HLT()
{
	
	System.out.println("Final Clock Value:"+System_Clock);
	System.exit(0);
}


}



