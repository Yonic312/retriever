package order;

import java.util.HashMap;
import java.util.List;

import cart.cartVO;

public class orderServiceImpl implements orderService{
	orderDao dao = new orderDaoImpl();

	@Override
	public void insert(orderVO vo) {
		dao.insert(vo);
	}

	@Override
	public List<HashMap<String, Object>> select(orderVO vo) {
		return dao.select(vo);
	}

	@Override
	public List<HashMap<String, Object>> selectView(orderVO vo) {
		
		return dao.selectView(vo);
	}

	@Override
	public int countOrder(orderVO vo) {
		
		return dao.countOrder(vo);
	}
}
