package review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import pkg.DBConnection;

public class reviewDaoImpl implements reviewDao {
	private DBConnection DBconn;
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs = null;
	String SQL;
	
	public reviewDaoImpl() {
		DBconn = DBConnection.getInstance();
	}
	
	@Override
	public void insert(reviewVO vo) {
		try {
			SQL = " insert into tbl_review (mid, pid, review, rate) "
					+ " values(?, ?, ?, ?)";
			conn = DBconn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, vo.getMid());
			pstmt.setString(2, vo.getPid());
			pstmt.setString(3, vo.getReview());
			pstmt.setString(4, vo.getRate());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
			
		
	}

	@Override
	public List<HashMap<String, Object>> select(reviewVO vo) {
		List<HashMap<String, Object>> m = new ArrayList<HashMap<String, Object>>();
		try {
			SQL = " select * from tbl_review where pid=? order by writeTime desc";
			conn = DBconn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, vo.getPid());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				HashMap<String, Object> m2 = new HashMap<String, Object>();
				m2.put("pid", vo.getPid());
				m2.put("mid", rs.getString("mid"));
				m2.put("review", rs.getString("review"));
				m2.put("writeTime", rs.getString("writeTime"));
				m2.put("rate", rs.getString("rate"));
				m2.put("idx", rs.getString("idx"));
				m.add(m2);
			}
			

		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return m;
	}

	@Override
	public List<String> cntReview(List<String> li) {
		List<String> list = new ArrayList<String>();
		 
		try {
			for(String str : li) {
				SQL = " SELECT COUNT(*) as cnt FROM `tbl_review` WHERE pid=?";
				conn = DBconn.getConnection();
				pstmt = conn.prepareStatement(SQL);
				pstmt.setString(1, str);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					list.add(rs.getString("cnt"));
				}
			}

		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<String> rateAvg(List<String> li) {
		List<String> list = new ArrayList<String>();
		 
		try {
			for(String str : li) {
				SQL = " SELECT AVG(rate) as avg FROM `tbl_review` WHERE pid=?";
				conn = DBconn.getConnection();
				pstmt = conn.prepareStatement(SQL);
				pstmt.setString(1, str);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					if(rs.getString("avg")==null) {
						list.add("0");
					}else {
						list.add(rs.getString("avg"));
					}
					
				}
			}

		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void delete(reviewVO vo) {
		try {
			SQL = " delete from tbl_review where idx=? and mid=?";
			conn = DBconn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, vo.getIdx());
			pstmt.setString(2, vo.getMid());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public void deleteM(reviewVO vo) {
		try {
			SQL = " delete from tbl_review where idx=?";
			conn = DBconn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, vo.getIdx());
			pstmt.setString(2, vo.getMid());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

}
