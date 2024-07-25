package member;

import java.sql.*;
import java.util.*;

import com.mysql.*;

import pkg.DBConnection;

public class memberDaoImpl implements memberDao{
	
	private DBConnection DBconn;
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs = null;
	String SQL;
	public memberDaoImpl(){
		DBconn = DBConnection.getInstance();
	}
	
	@Override
	public void insert(memberVO vo) {
		try {
			SQL = "insert into member "
					+ " (mid, mpassword1,mpassword2, mphone, maddr1, maddr2, maddr3, mname, mage, mgender, mgrade) "
					+ " values(?,?,?,?,?,?,?,?,?,?,?)";
			conn = DBconn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, vo.getMid()); 
			pstmt.setString(2, vo.getMpassword1());
			pstmt.setString(3, vo.getMpassword2()); 
			pstmt.setString(4, vo.getMphone()); 
			pstmt.setString(5, vo.getMaddr1()); 
			pstmt.setString(6, vo.getMaddr2()); 
			pstmt.setString(7, vo.getMaddr3()); 
			pstmt.setString(8, vo.getMname()); 
			pstmt.setInt(9, vo.getMage()); 
			pstmt.setString(10, vo.getMgender());
			pstmt.setString(11, vo.getMgrade());
			System.out.println("vo2" + vo);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBconn.close(pstmt, conn);
		}
		
		
			
	}

	@Override
	public void delete(memberVO vo) {
		try {
			SQL = "delete from member where mid=?";
			conn = DBconn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, vo.getMid()); 
			pstmt.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBconn.close(pstmt, conn);
		}
	}

	@Override
	public List<HashMap<String, Object>> select(memberVO vo) {
		List<HashMap<String, Object>> li = new ArrayList<HashMap<String, Object>>();
		try {
			SQL = " select * from member";
			conn = DBconn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				HashMap<String, Object> m = new HashMap<String, Object>();
				m.put("mid",rs.getString("mid"));
				m.put("mpassword1",rs.getString("mpassword1"));
				m.put("mpassword2",rs.getString("mpassword2"));
				m.put("mphone",rs.getString("mphone"));
				m.put("maddr1",rs.getString("maddr1"));
				m.put("maddr2",rs.getString("maddr2"));
				m.put("maddr3",rs.getString("maddr3"));
				m.put("mname",rs.getString("mname"));
				m.put("mage",rs.getString("mage"));
				m.put("mgender",rs.getString("mgender"));
				m.put("mgrade",rs.getString("mgrade"));
				li.add(m);
				System.out.println(m.get("mid"));
				System.out.println("li : "  +li.get(0));
			}
			
			System.out.println("vo2" + vo);
			pstmt.executeQuery();
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBconn.close(pstmt, conn);
		}
		return li;
	}

	@Override
	public HashMap<String, Object> edit(memberVO vo) {
		HashMap<String, Object> m = new HashMap<String, Object>();
		try {
			SQL = " select * from member where mid=?";
			conn = DBconn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, vo.getMid());
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				m.put("mid",rs.getString("mid"));
				m.put("mpassword1",rs.getString("mpassword1"));
				m.put("mpassword2",rs.getString("mpassword2"));
				m.put("mphone",rs.getString("mphone"));
				m.put("maddr1",rs.getString("maddr1"));
				m.put("maddr2",rs.getString("maddr2"));
				m.put("maddr3",rs.getString("maddr3"));
				m.put("mname",rs.getString("mname"));
				m.put("mage",rs.getString("mage"));
				m.put("mgender",rs.getString("mgender"));
				m.put("mgrade",rs.getString("mgrade"));
			}
			
			System.out.println("vo2" + vo);
			pstmt.executeQuery();
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBconn.close(pstmt, conn);
		}
		return m;
	}

	@Override
	public void update(memberVO vo) {
		try {
			SQL = "update member set mpassword1=?,mpassword2=?, mphone=?,"
					+ " maddr1=?, maddr2=?, maddr3=?, mname=?, mage=?,"
					+ " mgender=?, mgrade=? where mid=?";
			conn = DBconn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			
			pstmt.setString(1, vo.getMpassword1());
			pstmt.setString(2, vo.getMpassword2()); 
			pstmt.setString(3, vo.getMphone()); 
			pstmt.setString(4, vo.getMaddr1()); 
			pstmt.setString(5, vo.getMaddr2()); 
			pstmt.setString(6, vo.getMaddr3()); 
			pstmt.setString(7, vo.getMname()); 
			pstmt.setInt(8, vo.getMage()); 
			pstmt.setString(9, vo.getMgender());
			pstmt.setString(10, vo.getMgrade());
			pstmt.setString(11, vo.getMid()); 
			System.out.println("vo2" + vo);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBconn.close(pstmt, conn);
		}
	}

}
