package login;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * Servlet implementation class loginController
 */
public class loginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginController() {
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
		System.out.println("sw : " + sw);
		
		String path = request.getContextPath();
		HttpSession session = request.getSession();
		loginService service = new loginServiceImpl();
		
		
		if(sw.equals("F")) {
			response.sendRedirect(path+"/login/login.jsp");
		} else if (sw.equals("login")) {
			loginVO vo = new loginVO();
			vo.setId(request.getParameter("id"));
			vo.setPwd1(request.getParameter("pwd"));
			vo.setPwd2(request.getParameter("pwd"));
			
			vo.setMgrade(service.login(vo));
			
			if(!service.login(vo).equals("null")) {
				vo.setOK("true");
				
				session.setAttribute("vo2", vo);
				response.sendRedirect(path+"/index.jsp");
			} else { 
				vo.setOK("false");
				response.sendRedirect(path+"/login/login.jsp");
			}
		} else if (sw.equals("logout")) {
			session.invalidate();
			response.sendRedirect(path + "/index.jsp");
			
		} else if (sw.equals("ckID")) { // 중복확인
			loginVO vo = new loginVO();
			vo.setId(request.getParameter("mid"));
			System.out.println("ckID mid : "+request.getParameter("mid"));
			String ck = service.ckID(vo);
			PrintWriter out = response.getWriter();
			out.print(ck);
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
