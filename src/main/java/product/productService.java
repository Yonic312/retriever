package product;

import java.util.HashMap;
import java.util.List;

public interface productService {
	void insert(productVO vo);
	void update(productVO vo);
	void delete(productVO vo);
	
	HashMap<String, Object> edit(productVO vo);
	List<HashMap<String, Object>> select(productVO vo);
	List<String>member();
}
