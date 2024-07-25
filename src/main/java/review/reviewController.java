package review;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.websocket.Session;

import java.io.IOException;
import java.util.HashMap;

import cart.*;

/**
 * Servlet implementation class reviewController
 */
public class reviewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public reviewController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String sw = request.getParameter("sw");
		String path = request.getContextPath();
		
		reviewVO vo = new reviewVO();
		
		reviewService rservice = new reviewServiceImpl();
		if(sw.equals("S")) {
			vo.setPid(request.getParameter("pid"));
			System.out.println("pid : "+ request.getParameter("pid"));
			
			// 제품 정보
			cartService cservice = new cartServiceImpl();
			cartVO cvo = new cartVO();
			cvo.setPid(request.getParameter("pid"));
		
			request.setAttribute("pid", request.getParameter("pid"));
			request.setAttribute("Cli", cservice.selectOne(cvo));
			
			request.setAttribute("li", rservice.select(vo));
			RequestDispatcher dispacher = request.getRequestDispatcher("/review/view.jsp");
			dispacher.forward(request, response);
		}else if(sw.equals("I")) {
			
			vo.setMid(request.getParameter("mid"));
			vo.setPid(request.getParameter("pid"));
			vo.setReview(request.getParameter("review"));
			vo.setRate(request.getParameter("rate"));
			rservice.insert(vo);
			response.sendRedirect("reviewController?sw=S&pid="+request.getParameter("pid"));
			
		}else if(sw.equals("D")) {
			vo.setIdx(request.getParameter("pid"));
			vo.setIdx(request.getParameter("idx"));
			vo.setMid(request.getParameter("mid"));
			rservice.delete(vo);
			response.sendRedirect("reviewController?sw=S&pid="+request.getParameter("pid"));
		}else if(sw.equals("MD")) {
			vo.setIdx(request.getParameter("pid"));
			vo.setIdx(request.getParameter("idx"));
			vo.setMid(request.getParameter("mid"));
			rservice.delete(vo);
			response.sendRedirect("reviewController?sw=S&pid="+request.getParameter("pid"));
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
