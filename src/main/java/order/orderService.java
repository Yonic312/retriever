package order;

import java.util.HashMap;
import java.util.List;

import cart.cartVO;

public interface orderService {
	void insert(orderVO vo);
	List<HashMap<String, Object>> select(orderVO vo);
	List<HashMap<String, Object>> selectView(orderVO vo);
	
	int countOrder(orderVO vo);
}
