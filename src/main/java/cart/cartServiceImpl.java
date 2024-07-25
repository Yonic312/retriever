package cart;

import java.util.HashMap;
import java.util.List;

public class cartServiceImpl implements cartService{
	cartDao dao = new cartDaoImpl();
	@Override
	public void insert(cartVO vo) {
		dao.insert(vo);
	}
	@Override
	public List<HashMap<String, Object>> select(cartVO vo) {
		return dao.select(vo);
	}
	@Override
	public void update(String str1, String str2) {
		dao.update(str1, str2);
		
	}
	@Override
	public void deleteAll(cartVO vo) {
		dao.deleteAll(vo);
		
	}
	@Override
	public String Dup(cartVO vo) {
		return dao.Dup(vo);
	}
	@Override
	public HashMap<String, Object> selectOne(cartVO vo) {
		return dao.selectOne(vo);
	}

}
