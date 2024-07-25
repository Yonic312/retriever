package member;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pkg.BCrypt;

import java.io.IOException;

/**
 * Servlet implementation class retrieverController
 */
public class memberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public memberController() {
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
		
		memberService service = new memberServiceImpl();
		
		System.out.println("sw : " + sw);
		
		if (sw.equals("S")) {
			request.setAttribute("li", service.select(null));
			RequestDispatcher dispacher = request.getRequestDispatcher("/member/list.jsp");
			dispacher.forward(request, response);
			
		}else if (sw.equals("F")) {
			response.sendRedirect(path+"/member/memberform.jsp");
		}else if (sw.equals("FM")) {
			response.sendRedirect(path+"/member/memberformM.jsp");
		}else if (sw.equals("I")) {
			memberVO vo = new memberVO();
			
			String pwd = request.getParameter("pwd");
			String BCpw = BCrypt.hashpw(pwd, BCrypt.gensalt());
			
			vo.setMpassword2(BCpw);
			vo.setMid(request.getParameter("mid"));
			vo.setMpassword1(request.getParameter("pwd"));
			vo.setMphone(request.getParameter("mphone"));
			vo.setMaddr1(request.getParameter("maddr1"));
			vo.setMaddr2(request.getParameter("maddr2"));
			vo.setMaddr3(request.getParameter("maddr3"));
			vo.setMname(request.getParameter("mname"));
			vo.setMage(Integer.parseInt(request.getParameter("mage")));
			vo.setMgender(request.getParameter("mgender"));
			vo.setMgrade(request.getParameter("mgrade"));
			
			service.insert(vo);
			response.sendRedirect(path+"/memberController?sw=S");
			
		}else if (sw.equals("D")) {
			System.out.println("D");
			memberVO vo = new memberVO();
			vo.setMid(request.getParameter("mid"));
			service.delete(vo);
			response.sendRedirect(path+"/memberController?sw=S");
			
		}else if (sw.equals("E")) {
			memberVO vo = new memberVO();
			vo.setMid(request.getParameter("mid"));
			request.setAttribute("li", service.edit(vo));
			RequestDispatcher dispacher = request.getRequestDispatcher("/member/edit.jsp");
			dispacher.forward(request, response);
			
		}else if (sw.equals("U")) {
			memberVO vo = new memberVO();
			
			String pwd = request.getParameter("pwd");
			String BCpw = BCrypt.hashpw(pwd, BCrypt.gensalt());
			System.out.println(BCpw);
			
			vo.setMpassword2(BCpw);
			vo.setMid(request.getParameter("mid"));
			vo.setMpassword1(request.getParameter("pwd"));
			vo.setMphone(request.getParameter("mphone"));
			vo.setMaddr1(request.getParameter("maddr1"));
			vo.setMaddr2(request.getParameter("maddr2"));
			vo.setMaddr3(request.getParameter("maddr3"));
			vo.setMname(request.getParameter("mname"));
			vo.setMage(Integer.parseInt(request.getParameter("mage")));
			vo.setMgender(request.getParameter("mgender"));
			vo.setMgrade(request.getParameter("mgrade"));
			
			System.out.println(vo);
			service.update(vo);
			
			response.sendRedirect(path+"/memberController?sw=S");
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
