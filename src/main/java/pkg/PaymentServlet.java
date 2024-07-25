package pkg;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import login.loginVO;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import cart.*;
import order.*;


/**
 * Servlet implementation class PaymentServlet
 */
public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private static final String KAKAO_API_KEY = "489749ae6a50b478d5ca4e6d9531958c";  //  발급받은 Admin 키
    private static final String CID = "TC0ONETIME"; // 테스트 CID
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentServlet() {
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
		
		// String path = request.getContextPath();
        String path = "http://localhost:8090/retriever";   // 필히 문자열로 넘겨주어야 한다.  (중요)
        HttpSession session = request.getSession();
        
        Calendar now = Calendar.getInstance();
        int hms = now.get(Calendar.HOUR)+now.get(Calendar.MINUTE) +now.get(Calendar.SECOND); 
  
        loginVO vo2 = new loginVO();
		vo2=(loginVO)session.getAttribute("vo2");
		
		System.out.println("Order Controller !!!vo2 id : " + vo2.getId());
        
        
		String partner_order_id = Integer.toString(hms); 
		String item_name = "리트리버상품"; 
		String quantity = "1004";
		
		// cart 서비스
		cartService cservice = new cartServiceImpl();
		cartVO cvo = new cartVO();
		cvo.setMid(request.getParameter("partner_user_id"));
		System.out.println(cservice.select(cvo));
		
		List<String> midList = new ArrayList<String>();
		List<String> cidList = new ArrayList<String>();
		List<String> pidList = new ArrayList<String>();
		for(HashMap<String, Object> m : cservice.select(cvo)) {
			midList.add((String)m.get("amount"));
			cidList.add((String)m.get("cart_id"));
			pidList.add((String)m.get("pid"));
		}
		
		
		String mid = request.getParameter("partner_user_id"); //partner_user_id
		System.out.println("midmid : " + mid);
		String orderG = mid + hms;
		
		// order 준비
		orderService oservice = new orderServiceImpl();
		orderVO ovo = new orderVO();
		
		ovo.setMid(mid);
		ovo.setOrderG(orderG);
		ovo.setMid(vo2.getId());
		for(int i=0; i<midList.size();i++) {
			ovo.setPid(pidList.get(i));
			ovo.setCart_id(cidList.get(i));
			System.out.println("ovo"+ovo);
			oservice.insert(ovo);
		}
		
		System.out.println("!!!!!!session.getId() : " +  session.getId());
		
		String total_amount = request.getParameter("total_amount");
        
        String apiUrl = "https://kapi.kakao.com/v1/payment/ready"; // 카카오 사용 준비 URL
        URL url = new URL(apiUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "KakaoAK " + KAKAO_API_KEY);
        conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        conn.setDoOutput(true);
        
        System.out.println("conn:" + conn);
        
        String params = "cid=" + CID +
                        "&partner_order_id=" +partner_order_id+      //  order_id (상품 id)
                        "&partner_user_id=" + mid+   //  user_id  (사용자 id)
                        "&item_name=" + item_name +           //  item_name ( 상품명 ) 
                        "&quantity=1" + quantity +               //  quantity ( 수량 )
                        "&total_amount=" + total_amount +             //  total_amount ( 가격 )
                        "&vat_amount=1" +                //  vat_amount ( 부가가치세 : 세금 )
                        "&tax_free_amount=0" +           //  tax_free_amount ( 면세 )
                        "&approval_url=" + path + "/ApproveServlet" + // 성공
                        "&cancel_url=" + path + "/cancel" + // 취소
                        "&fail_url="+ path +"/fail"; // 실패

        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = params.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int code = conn.getResponseCode();
        Scanner sc;
        if (code == 200) {
            sc = new Scanner(conn.getInputStream());
            System.out.println("conn1:" + sc);
        } else {
            sc = new Scanner(conn.getErrorStream());
            System.out.println("conn2:" + sc);
        }
        
        StringBuilder result = new StringBuilder();
        while (sc.hasNext()) {
            result.append(sc.nextLine());
        }
        System.out.println("result:" + result);
        sc.close();
        
        // JSON 파싱 후, next_redirect_pc_url 값으로 리다이렉트
        
        // tid 는 결제 준비 단계에서 카카오페이로 부터 받은 거래 고유 번호 이다. 
        // 이 tid 는 결제 승인 단계에서 필수로 사용되며 세션 또는 다늘 방법으로 관리해야 한다. 
        // 준비 단계에서 받은 tid를 결제 승인 요청 시 함께 전송하여 결제를 완료 해 야 한다.\
        
        String tid = result.toString().split("\"tid\":\"")[1].split("\"")[0];
        
        // 방법1.
        request.getSession().setAttribute("tid", tid);
        request.getSession().setAttribute("partner_order_id", partner_order_id); //
        
        request.getSession().setAttribute("mid", mid); //
        request.getSession().setAttribute("item_name", item_name); //
        request.getSession().setAttribute("quantity", quantity); //
        request.getSession().setAttribute("amount", midList); //
        request.getSession().setAttribute("total_amount", total_amount); //
        
        request.getSession().setAttribute("orderList", oservice.select(ovo));
        
       
        String redirectUrl = result.toString().split("\"next_redirect_pc_url\":\"")[1].split("\"")[0];
        System.out.println("===>redirectUrl 확인:" + redirectUrl);
		response.sendRedirect(redirectUrl);	
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
