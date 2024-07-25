package review;

import java.util.HashMap;
import java.util.List;

public interface reviewDao {
	void insert(reviewVO vo);
	List<HashMap<String, Object>> select(reviewVO vo);
	List<String> cntReview(List<String> li);
	void delete(reviewVO vo);
	void deleteM(reviewVO vo);
	
	List<String> rateAvg(List<String> li);
	
}
