package product;

import java.sql.*;
import java.util.*;

import pkg.DBConnection;

public class productDaoImpl implements productDao{
	
	private DBConnection DBconn;
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs = null;
	String SQL;
	
	public productDaoImpl() {
		DBconn = DBConnection.getInstance();
	}
	
	@Override
	public List<HashMap<String, Object>> select(productVO vo) {
		List<HashMap<String, Object>> li = new ArrayList<HashMap<String, Object>>();
		try {
			SQL = " select * from tbl_product order by pid";
			conn = DBconn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				HashMap<String, Object> m = new HashMap<String, Object>();
				m.put("pid", rs.getString("pid"));
				m.put("pname", rs.getString("pname"));
				m.put("pprice", rs.getString("pprice"));
				m.put("pbaesongbi", rs.getString("pbaesongbi"));
				m.put("pdesc", rs.getString("pdesc"));
				m.put("pimg", rs.getString("pimg"));
				
				li.add(m);
			}
			

		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return li;
	}
	
	@Override
	public void insert(productVO vo) {
		
		try {
			SQL = " insert into tbl_product values (?,?,?,?,?,?)";
			conn = DBconn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, vo.getPid());
			pstmt.setString(2, vo.getPname());
			pstmt.setInt(3, vo.getPprice());
			pstmt.setInt(4, vo.getPbaesongbi());
			pstmt.setString(5, vo.getPdesc());
			pstmt.setString(6, vo.getPimg());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		
	}

	@Override
	public void update(productVO vo) {
		try {
			SQL = " update tbl_product set "
					+ " pname=?, pprice=?, pbaesongbi=?, pdesc=?, pimg=?"
					+ " where pid=? ";
			conn = DBconn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, vo.getPname());
			pstmt.setInt(2, vo.getPprice());
			pstmt.setInt(3, vo.getPbaesongbi());
			pstmt.setString(4, vo.getPdesc());
			pstmt.setString(5, vo.getPimg());
			pstmt.setString(6, vo.getPid());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(productVO vo) {
		try {
			SQL = " delete from tbl_product where pid=?";
			conn = DBconn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, vo.getPid());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public HashMap<String, Object> edit(productVO vo) {
		HashMap<String, Object> m = new HashMap<String, Object>();
		try {
			SQL = " select * from tbl_product where pid = ?";
			conn = DBconn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, vo.getPid());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				m.put("pid", rs.getString("pid"));
				m.put("pname", rs.getString("pname"));
				m.put("pprice", rs.getString("pprice"));
				m.put("pbaesongbi", rs.getString("pbaesongbi"));
				m.put("pdesc", rs.getString("pdesc"));
				m.put("pimg", rs.getString("pimg"));
				
				
				
			}
			System.out.println("m"+m);
			

		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return m;
	}

	@Override
	public List<String> member() {
		List<String> list = new ArrayList<String>();
		try {
			SQL = " SELECT pid FROM tbl_product";
			conn = DBconn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				list.add(rs.getString("pid"));
				
			}
			

		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}

	

}
