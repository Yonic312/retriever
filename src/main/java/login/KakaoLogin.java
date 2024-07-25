package login;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;

/**
 * Servlet implementation class KakaoController
 */
public class KakaoLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KakaoLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String path = request.getContextPath();
		
		String code = request.getParameter("code");
		System.out.println("==> code : " + code);
		
		//카카오 실제 서비스를 실행하는 곳
		KakaoLoginService kakao = new KakaoLoginService(); 
		/* KakaoService service = new KakaoServiceImpl(); */
		
		HttpSession session = request.getSession();
		
		String access_Token = kakao.getKakaoAccessToken(code);
		HashMap<String, Object> userInfo = kakao.getUserInfo(access_Token);
			System.out.println("===>controller access_token :" + access_Token);
			System.out.println("===>controller userInfo :" + userInfo);
			
			KakaoVO vo = new KakaoVO();
			if (userInfo.get("email") != null) {
				session.setAttribute("email", userInfo.get("email"));
				session.setAttribute("nickname", userInfo.get("nickname"));
				session.setAttribute("image", userInfo.get("image"));
				session.setAttribute("access_Token", access_Token);
				
				vo.setNickname((String)userInfo.get("nickname"));
				vo.setEmail((String)userInfo.get("email"));
				vo.setImage((String)userInfo.get("image"));
				System.out.println("vo" + vo);
				/* service.insert(vo); */
				
				System.out.println("image : " + userInfo.get("image"));
			}
			loginVO vo2 = new loginVO();
			vo2.setOK("true");
			System.out.println(vo2.getOK().equals("true"));
			
			session.setAttribute("vo2", vo2);
			response.sendRedirect(path+"/index.jsp");
		//response.sendRedirect(path+"/login/kakaoLoginResult.jsp");
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
