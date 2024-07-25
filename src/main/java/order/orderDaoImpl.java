package order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import pkg.DBConnection;

public class orderDaoImpl implements orderDao{
	private DBConnection DBconn;
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs = null;
	String SQL;
	
	public orderDaoImpl() {
		DBconn = DBConnection.getInstance();
	}
	
	@Override
	public void insert(orderVO vo) {
		try {
			SQL = " insert into tbl_order(mid, orderG, pid, cart_id) values (?,?,?,?)";
			conn = DBconn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, vo.getMid());
			pstmt.setString(2, vo.getOrderG());
			pstmt.setString(3, vo.getPid());
			pstmt.setString(4, vo.getCart_id());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public List<HashMap<String, Object>> select(orderVO vo) {
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		try {
			SQL = " SELECT * FROM tbl_order o join member m "
					+ " on o.mid = m.mid where m.mid=? GROUP by orderG";
			
			conn = DBconn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, vo.getMid());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				HashMap<String, Object> m = new HashMap<String, Object>();
				m.put("idxOrder",rs.getString("idxOrder"));
				m.put("orderG",rs.getString("orderG"));
				m.put("today",rs.getString("today"));
				
				list.add(m); // 나중에 작업하기 결제 완료 창에서 사진 찍을거 
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<HashMap<String, Object>> selectView(orderVO vo) {
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		try {
			SQL = " select * from tbl_order o join tbl_product p "
					+ " on o.pid = p.pid join tbl_cart c "
					+ " on o.pid = c.pid join member m "
					+ " on m.mid = c.mid "
					+ " where orderG = ? and m.mid = ? order by idxOrder desc";
			System.out.println("VOVOVOVOO@@@@@@:" + vo);
			conn = DBconn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, vo.getOrderG());
			pstmt.setString(2, vo.getMid());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				HashMap<String, Object> m = new HashMap<String, Object>();
				m.put("idxOrder",rs.getString("idxOrder"));
				m.put("orderG",rs.getString("orderG"));
				m.put("pid",rs.getString("pid"));
				m.put("pname",rs.getString("pname"));
				m.put("today",rs.getString("today"));
				m.put("pprice",rs.getString("pprice"));
				m.put("amount",rs.getString("amount"));
				m.put("pimg",rs.getString("pimg"));
				m.put("mphone",rs.getString("mphone"));
				m.put("maddr2",rs.getString("maddr2"));
				m.put("maddr3",rs.getString("maddr3"));
				
				list.add(m); // 나중에 작업하기 결제 완료 창에서 사진 찍을거 
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int countOrder(orderVO vo) {
		int countOrder = 0;
		try {
			SQL = " SELECT count(*) from tbl_order where mid=?";
			conn = DBconn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, vo.getMid());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				countOrder = rs.getInt("count(*)");
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return countOrder;
	}

}
