package order;

import lombok.Data;

@Data
public class orderVO {
	private int idxOrder;
	private String orderG;
	private String cart_id;
	private String mid;
	private String pid;
	private String today;
	
	private int pageSize;
	private int totalOrder;
	private int totalPage;
	private int i1;
	private int i2;
	private int cnt;
	private int minI;
	private int maxI;
}
