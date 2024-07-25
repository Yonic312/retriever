package pkg;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * Servlet implementation class ApproveServlet
 */
public class ApproveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private static final String ADMIN_KEY = "489749ae6a50b478d5ca4e6d9531958c"; // Admin 키
    private static final String CID = "TC0ONETIME"; // 테스트 CID
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApproveServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		String path = request.getContextPath();
		System.out.println("===> ApproveServlet 넘어옴 !!" );
		
        String pgToken = request.getParameter("pg_token");
        
        String tid = (String) request.getSession().getAttribute("tid");
        String partner_order_id = (String) request.getSession().getAttribute("partner_order_id");
        String partner_user_id = (String) request.getSession().getAttribute("partner_user_id");
        String item_name = (String) request.getSession().getAttribute("item_name");
        String quantity = (String) request.getSession().getAttribute("quantity");
        //String amount = (String) request.getSession().getAttribute("amount");
        String total_amount = (String) request.getSession().getAttribute("total_amount");
        
        
        System.out.println("===> ApproveServlet pgToken 확인" +  pgToken);
        System.out.println("===> ApproveServlet tid 확인:" + tid );
        
        
        String apiUrl = "https://kapi.kakao.com/v1/payment/approve";
        URL url = new URL(apiUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "KakaoAK " + ADMIN_KEY);
        conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        conn.setDoOutput(true);

        System.out.println("===>ApproveServlet conn 확인: " + conn );
        
        String params = "cid=" + CID + // 상점ID ( 웹으로 받아옴 )
                        "&tid=" + tid + // 토큰ID
                        "&partner_order_id=" + partner_order_id+ // 주문번호
                        "&partner_user_id=" + partner_user_id+ // 사용자ID
                        "&pg_token=" + pgToken;

        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = params.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int code = conn.getResponseCode();
        Scanner sc;
        if (code == 200) {
            sc = new Scanner(conn.getInputStream());
            System.out.println("===>ApproveServlet sc 성공 확인: " + sc );
        } else {
            sc = new Scanner(conn.getErrorStream());
            System.out.println("===>ApproveServlet sc 실패 확인: " + sc );
        }
        
        StringBuilder result = new StringBuilder();
        while (sc.hasNext()) {
            result.append(sc.nextLine());
        }
        
        System.out.println("===>ApproveServlet result 확인: " + result );
        System.out.println("===>partner_order_id 확인: " + partner_order_id ); //
        System.out.println("===>partner_user_id 확인: " + partner_user_id ); //
        System.out.println("===>item_name 확인: " + item_name ); //
        System.out.println("===>quantity 확인: " + quantity ); //
        //System.out.println("===>amount 확인: " + amount ); //
        System.out.println("===>total_amount 확인: " + total_amount ); //
        
        sc.close();
        
        request.setAttribute("tid", tid);
        request.setAttribute("partner_order_id", partner_order_id);
        request.setAttribute("partner_user_id", partner_user_id);
        request.setAttribute("item_name", item_name);
        request.setAttribute("quantity", quantity);
        //request.setAttribute("amount", amount);
        request.setAttribute("total_amount", total_amount);
        
        
        response.sendRedirect(path+"/orderController?sw=S");
        
		/*
		 * RequestDispatcher dispacher =
		 * request.getRequestDispatcher("/test/success.jsp"); dispacher.forward(request,
		 * response);
		 */
        
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(result.toString());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
