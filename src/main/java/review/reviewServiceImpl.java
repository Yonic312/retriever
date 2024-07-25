package review;

import java.util.HashMap;
import java.util.List;

public class reviewServiceImpl implements reviewService {
	reviewDao dao = new reviewDaoImpl();
	
	@Override
	public void insert(reviewVO vo) {
		dao.insert(vo);
		
	}

	@Override
	public List<HashMap<String, Object>> select(reviewVO vo) {

		return dao.select(vo);
	}

	@Override
	public List<String> cntReview(List<String> li) {
		
		return dao.cntReview(li);
	}

	@Override
	public List<String> rateAvg(List<String> li) {
	
		return dao.rateAvg(li);
	}

	@Override
	public void delete(reviewVO vo) {
		dao.delete(vo);;
		
	}

	@Override
	public void deleteM(reviewVO vo) {
		dao.deleteM(vo);
		
	}
	
}
