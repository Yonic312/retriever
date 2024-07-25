package login;

public class loginServiceImpl implements loginService{
	loginDao dao = new loginDaoImpl();
	@Override
	public String login(loginVO vo) {
		
		return dao.login(vo);
	}
	@Override
	public String ckID(loginVO vo) {
		return dao.ckID(vo);
	}

}
