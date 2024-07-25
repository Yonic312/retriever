package product;

import java.util.*;

public interface productDao {
	void insert(productVO vo);
	void update(productVO vo);
	void delete(productVO vo);
	
	HashMap<String, Object> edit(productVO vo);
	List<HashMap<String, Object>> select(productVO vo);
	List<String>member();
	
}
