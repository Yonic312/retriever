package order;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import login.loginVO;

import java.io.IOException;

/**
 * Servlet implementation class orderController
 */
public class orderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public orderController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		String path = request.getContextPath();
		HttpSession session = request.getSession();
		String sw = request.getParameter("sw");
		orderVO ovo = new orderVO();
		
		loginVO vo2 = new loginVO();
		vo2=(loginVO)session.getAttribute("vo2");
		
		System.out.println("Order Controller !!!vo2 id : " + vo2.getId());
		ovo.setMid(vo2.getId());
		
		orderService oservice = new orderServiceImpl();
		if(sw.equals("detail")) {
			System.out.println("View order G =  " + request.getParameter("orderG"));
			ovo.setOrderG(request.getParameter("orderG"));
			ovo.setMid(vo2.getId());
			System.out.println("view : " + oservice.selectView(ovo));
	    	request.getSession().setAttribute("payList", oservice.selectView(ovo));
	    	RequestDispatcher dispacher = request.getRequestDispatcher("/test/successView.jsp");
	    	dispacher.forward(request, response);
	    	
		} else if (sw.equals("S")) {
			// 전체 주문서 목록 개수
			int countOrder = oservice.countOrder(ovo);
			request.setAttribute("countOrder", oservice.countOrder(ovo));
			
			// 사이즈 크기
			double PageSize = 10.0;
			ovo.setPageSize((int)PageSize);
			
			// 총 페이지 크기
			
			int totalPage = (int)Math.ceil(countOrder / (PageSize));
			ovo.setTotalPage(totalPage);
			
			// 페이지를 누르면 번호를 반환 할 cnt 선언
			int cnt;
			
			if (request.getParameter("cnt") != null) {
				cnt = (int)Double.parseDouble(request.getParameter("cnt"));
			}else{
				cnt = 1;
			}
			ovo.setCnt(cnt);
			
			
			// 현재 페이지(i1) 리스트 마지막(i2) 선언
			int i1 = 0;
			i1 = i1 + (int)((cnt-1) * PageSize);
			ovo.setI1(i1);
			
			int i2 = i1 + (int)PageSize;
			ovo.setI2(i2);
			
			// 페이지 고정 1 > 11 > 21
			int minI;
			if(request.getParameter("minI") != null) {
					minI = (int)Double.parseDouble(request.getParameter("minI"));			
				} else {
					minI = 1;
			}
			ovo.setMinI(minI);
			
			int maxI;
			System.out.println(request.getParameter("maxI"));
			if(request.getParameter("maxI") != null) {
					maxI = (int)Double.parseDouble(request.getParameter("maxI"));			
				} else {
					maxI = 10;
			}
			ovo.setMaxI(maxI);
			
			request.setAttribute("orderVO", ovo);
			
			request.setAttribute("orderList", oservice.select(ovo));
			RequestDispatcher dispacher = request.getRequestDispatcher("/test/success.jsp");
			dispacher.forward(request, response);
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
