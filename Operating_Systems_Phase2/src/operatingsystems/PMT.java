package operatingsystems;
import java.util.ArrayList;


public class PMT extends SYSTEM
{
	public int page_no;
	public int frame_no;
	public int valid_bit;
	public int ref_bit;
	public int dirty_bit;
	
	PMT(int page_no,int frame_no,int valid_bit,int ref_bit,int dirty_bit)
	{
		this.page_no = page_no;
		this.frame_no = frame_no;
		this.valid_bit = valid_bit;
		this.ref_bit = ref_bit;
		this.dirty_bit = dirty_bit;
	}
	
	
}
