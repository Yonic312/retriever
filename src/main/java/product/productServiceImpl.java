package product;

import java.util.HashMap;
import java.util.List;

public class productServiceImpl implements productService{
	productDao dao = new productDaoImpl();
	@Override
	public void insert(productVO vo) {
		dao.insert(vo);
	}

	@Override
	public void update(productVO vo) {
		dao.update(vo);
		
	}

	@Override
	public void delete(productVO vo) {
		dao.delete(vo);
		
	}

	@Override
	public HashMap<String, Object> edit(productVO vo) {
		return dao.edit(vo);
	}

	@Override
	public List<HashMap<String, Object>> select(productVO vo) {
		return dao.select(vo);
	}

	@Override
	public List<String> member() {
		return dao.member();
	}

}
