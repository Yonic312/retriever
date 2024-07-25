package cart;

import java.util.HashMap;
import java.util.List;

public interface cartDao {
	void insert(cartVO vo);
	void deleteAll(cartVO vo);
	String Dup(cartVO vo);
	void update(String str1, String str2);
	List<HashMap<String, Object>> select(cartVO vo);
	HashMap<String, Object> selectOne(cartVO vo);
}
