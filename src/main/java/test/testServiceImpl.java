package test;

public class testServiceImpl implements testService{

	@Override
	public void print(testVO vo) {
		System.out.println("vo : " + vo);
	}
}