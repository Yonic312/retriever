package manager;

public class managerServiceImpl implements managerService{
	managerDao dao = new managerDaoImpl();
	
	@Override
	public void insert(managerVO vo) {
		dao.insert(vo);
	}
	
}
