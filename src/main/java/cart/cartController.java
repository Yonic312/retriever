package cart;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import login.loginVO;
import product.*;
import review.*;

import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class cartController
 */
public class cartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public cartController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession();
		
		String sw = request.getParameter("sw");
		String path = request.getContextPath();
		
		cartService service = new cartServiceImpl();
		loginVO vo2 = new loginVO();
		vo2=(loginVO)session.getAttribute("vo2");
	
		
		if (sw.equals("S")) {
			cartVO vo = new cartVO();
			vo.setMid(vo2.getId());
					
			request.setAttribute("vo", vo2);
			request.setAttribute("li", service.select(vo));
			RequestDispatcher dispacher = request.getRequestDispatcher("/cart/list.jsp");
			dispacher.forward(request, response);
			
		} else if(sw.equals("I")) {
			
			request.setAttribute("Pli", request.getAttribute("Pli"));
			
			cartVO vo = new cartVO();
			vo.setMid(request.getParameter("mid"));
			vo.setPid(request.getParameter("pid"));
			vo.setAmount(request.getParameter("amount"));
			service.insert(vo);
			response.sendRedirect(path+"/cartController?sw=S");
		} else if (sw.equals("F")) {
			productVO vo = new productVO();
			productService pservice = new productServiceImpl();
			
			
			request.setAttribute("Pli", pservice.select(vo)); // 아래 띄우기(리스트)
			
			// 후기 개수
			reviewService rservice = new reviewServiceImpl();
			List<String> list = pservice.member();
			
			request.setAttribute("cntList", rservice.cntReview(list));
			request.setAttribute("rateAvg", rservice.rateAvg(list));
			
			request.setAttribute("pid", request.getParameter("pid"));
			RequestDispatcher dispacher = request.getRequestDispatcher("/cart/cartform.jsp");
			dispacher.forward(request, response);

		
		} else if (sw.equals("U")) {
			
			String[] amount = request.getParameterValues("amount");
			String[] pid = request.getParameterValues("pid");
			
			System.out.println("amount[0] : " + amount[0].toString());
			System.out.println("pid[0] : " + pid[0].toString());
			
			for(int i = 0; i < amount.length; i++) {
				System.out.println("amount[i]" + amount[i]);
				service.update(amount[i], pid[i]);
			}
			response.sendRedirect(path+"/cartController?sw=S");
		} else if (sw.equals("D")) {
			System.out.println("D");
			cartVO vo = new cartVO();
			vo.setMid(request.getParameter("mid"));
			
			service.deleteAll(vo);
			
			response.sendRedirect(path+"/cartController?sw=S");
		
		} else if (sw.equals("Dup")) {
			cartVO vo = new cartVO();
			
			if(request.getParameter("pid")!="") {
				request.setAttribute("pid", request.getParameter("pid"));
			}
			
			vo.setCart_id(request.getParameter("cart_id"));
			request.setAttribute("rt", service.Dup(vo));
			
			RequestDispatcher dispacher 
			= request.getRequestDispatcher("/cart/cartform.jsp");
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
