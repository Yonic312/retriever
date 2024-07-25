package member;

import java.util.HashMap;
import java.util.List;

public interface memberService {
	void insert (memberVO vo);
	void update (memberVO vo);
	void delete (memberVO vo);
	List<HashMap<String, Object>> select (memberVO vo);
	HashMap<String, Object> edit (memberVO vo);
}
