package product;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import member.memberService;
import member.memberServiceImpl;
import member.memberVO;
import product.productVO;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

//톰캣은 기본 설정이 maxFileSize : 5M 로 설정 되어 있다.
@MultipartConfig(
location="/", 
fileSizeThreshold=1024*1024,
maxFileSize=1024*1024*100, 
maxRequestSize=1024*1024*5*25
)

/**
 * Servlet implementation class productController
 */
public class productController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public productController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());

		String contentType = request.getContentType();
		productVO vo = new productVO();
		
		
		HttpSession session = request.getSession();
		productService service = new productServiceImpl();
		if (contentType != null && contentType.toLowerCase().startsWith("multipart/")) {
			// 한글 처리하기
	 		request.setCharacterEncoding("UTF-8");
	 		response.setCharacterEncoding("UTF-8");
	 		response.setContentType("text/html;charset=UTF-8");
	 		
			String sw = request.getParameter("sw");
			String pid = request.getParameter("pid");
			String pname = request.getParameter("pname");
			
			String path = request.getContextPath();
			String realFolder = getServletContext().getRealPath("/product/files/");
	 		String nameStr = request.getPart("pimg").getSubmittedFileName(); // 원본이름(경로)를 가져온다
	 		
	 		// 사진 이름에 난수 넣기
	 		Date now = new Date();
	 		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	 		String k = sdf.format(now);
	 		int ran=(int)(Math.random()*100)+101 ;
	 		
	 		Part part = request.getPart("pimg"); // part에 이미지를 저장
	 		// 파일첨부 유무 확인 후 확장자 만들기
	 		String extension = "";
	 		String pimg = "";
	 		if (nameStr.lastIndexOf('.')>0) { // 파일이 없으면 파일 이름에 space.png 넣기
	 			int lastIndex = nameStr.lastIndexOf('.'); // 이름의 마지막 '.' 뒤로 가져오기
	 			extension = nameStr.substring(lastIndex); // 확장자
	 			
	 			pimg = k + ran + extension;
	 			part.write(realFolder + pimg); // C:\Users\4545\eclipse-workspace\.metada ~ 경로와 파일이름으로 넣는다
	 		} else {
	 			pimg = "space.png";
	 		}
	 		
	 		vo.setPimg(pimg);
			
			if (sw.equals("I")) {
				
				vo.setPid(request.getParameter("pid"));
				vo.setPname(request.getParameter("pname"));
				vo.setPprice(Integer.parseInt(request.getParameter("pprice")));
				vo.setPbaesongbi(Integer.parseInt(request.getParameter("pbaesongbi")));
				vo.setPdesc(request.getParameter("pdesc"));
				vo.setPimg(pimg);
				/* vo.setPimg(request.getPart("pimg").getSubmittedFileName()); */
				
				service.insert(vo);
				
				response.sendRedirect(path+"/productController?sw=S");
			}else if (sw.equals("U")) {
				vo.setPid(request.getParameter("pid"));
				vo.setPname(pname);
				vo.setPprice(Integer.parseInt(request.getParameter("pprice")));
				vo.setPbaesongbi(Integer.parseInt(request.getParameter("pbaesongbi")));
				vo.setPdesc(request.getParameter("pdesc"));
				vo.setPimg(pimg);
				System.out.println("U vo : " + vo);
				
				service.update(vo);
				response.sendRedirect(path+"/productController?sw=S");
				
			}
		}else {
			String sw = request.getParameter("sw");
			String realFolder = getServletContext().getRealPath("/product/files/");
			String path = request.getContextPath();
			
			if (sw.equals("S")) {
				
				session.setAttribute("Pli", service.select(null));
				
				request.setAttribute("li", service.select(null));
				RequestDispatcher dispacher = request.getRequestDispatcher("/product/list.jsp");
				dispacher.forward(request, response);
				
			}else if (sw.equals("F")) {
				response.sendRedirect(path+"/product/productform.jsp");
			
			} else if (sw.equals("E")) {
				System.out.println("service.edit(vo)"+service.edit(vo));
				vo.setPid(request.getParameter("pid"));
				request.setAttribute("li", service.edit(vo));
				RequestDispatcher dispacher = request.getRequestDispatcher("/product/edit.jsp");
				dispacher.forward(request, response);
			
			} else if (sw.equals("D")) {
				vo.setPid(request.getParameter("pid"));
				service.delete(vo);
				response.sendRedirect(path+"/productController?sw=S");
			}
			
			
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
