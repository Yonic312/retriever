package member;

import java.util.HashMap;
import java.util.List;

public class memberServiceImpl implements memberService{
	memberDao dao = new memberDaoImpl();
	@Override
	public void insert(memberVO vo) {
		dao.insert(vo);
		
	}

	@Override
	public void delete(memberVO vo) {
		dao.delete(vo);
		
	}

	@Override
	public List<HashMap<String, Object>> select(memberVO vo) {
		return dao.select(vo);
	}

	@Override
	public HashMap<String, Object> edit(memberVO vo) {
		return dao.edit(vo);
	}

	@Override
	public void update(memberVO vo) {
		dao.update(vo);
		
	}
	
}
