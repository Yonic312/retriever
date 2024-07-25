package test;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * Servlet implementation class testController
 */
public class testController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public testController() {
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
		
		testService service = new testServiceImpl();
		
		if (sw.equals("F")) {
			response.sendRedirect(path+"/test/form.jsp");
			
		}else if (sw.equals("kakao")) {
			response.sendRedirect(path+"/test/pay.jsp");
			
		}else if (sw.equals("I")) {
			String[] name = request.getParameterValues("name");
			String[] amount = request.getParameterValues("amount");
			
			HashMap<String, String> li = new HashMap<>();
			for (int i = 0; i < name.length; i++) {
				testVO vo = new testVO();
				vo.setName(name[i]);
				vo.setAmount(amount[i]);
				// service.print(vo);
				li.put(name[i], amount[i]);
			}			
			 request.setAttribute("li", li); 
			 RequestDispatcher dispacher = request.getRequestDispatcher("/test/list.jsp");
			 dispacher.forward(request,response);
		}else if (sw.equals("S")) {
			
			
			RequestDispatcher dispacher = request.getRequestDispatcher("/test/list.jsp");
			 dispacher.forward(request,response);
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
