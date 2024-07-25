package cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import pkg.DBConnection;

public class cartDaoImpl implements cartDao {
	
	private DBConnection DBconn;
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs = null;
	String SQL;
	
	public cartDaoImpl() {
		DBconn = DBConnection.getInstance();
	}
	
	@Override
	public void insert(cartVO vo) {

		try {
			SQL = " insert into tbl_cart(mid,pid,amount) values (?,?,?)";
			conn = DBconn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, vo.getMid());
			pstmt.setString(2, vo.getPid());
			pstmt.setString(3, vo.getAmount());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<HashMap<String, Object>> select(cartVO vo) {
		List<HashMap<String, Object>> li = new ArrayList<HashMap<String, Object>>();
		try {
			
			SQL = " select * from tbl_cart c join member m "
					+ " ON c.mid = m.mid "
					+ " join tbl_product p "
					+ " ON p.pid = c.pid where m.mid = ? ";
			conn = DBconn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, vo.getMid());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				HashMap<String, Object> m = new HashMap<String, Object>();
				m.put("cart_id", rs.getString("cart_id"));
				m.put("mid", rs.getString("mid"));
				m.put("pid", rs.getString("pid"));
				m.put("amount", rs.getString("amount"));
				m.put("pprice", rs.getString("pprice"));
				m.put("pname", rs.getString("pname"));
				m.put("pimg", rs.getString("pimg"));
				li.add(m);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return li;
	}

	@Override
	public void update(String str1, String str2) {
		try {
			
			SQL = " update tbl_cart set amount=? where pid=? ";
			conn = DBconn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, str1); // amount
			pstmt.setString(2, str2); // pid
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteAll(cartVO vo) {
		try {
			
			SQL = " delete from tbl_cart where mid = ? ";
			conn = DBconn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, vo.getMid());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public String Dup(cartVO vo) {
		String rt = null;
		try {
			SQL = " select * from tbl_cart where cart_id = ? ";
			conn = DBconn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, vo.getCart_id());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				System.out.println("이미 존재합니다.");
				rt="0";
			} else {
				System.out.println("존재하지 않습니다.");
				rt=vo.getCart_id();
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return rt;
	}

	@Override
	public HashMap<String, Object> selectOne(cartVO vo) {
		HashMap<String, Object> m = new HashMap<String, Object>();
		try {	
			SQL = " select * from tbl_product  where pid = ? ";
			conn = DBconn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, vo.getPid());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				m.put("pprice", rs.getString("pprice"));
				m.put("pname", rs.getString("pname"));
				m.put("pimg", rs.getString("pimg"));
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return m;
	}

}
