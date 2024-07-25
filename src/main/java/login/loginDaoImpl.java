package login;

import java.sql.*;

import pkg.DBConnection;

public class loginDaoImpl implements loginDao {
	
	private DBConnection DBconn;
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs = null;
	String SQL;
	
	@Override
	public String login(loginVO vo) {
		DBconn = DBConnection.getInstance();
		String mgrade = "null";
		
		try {
			SQL=" select * from member where mid = ? and"
					+ " (mpassword1=? OR mpassword2=?) ";
			conn = DBconn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPwd1());
			pstmt.setString(3, vo.getPwd2());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				mgrade = rs.getString("mgrade");
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return mgrade;
	}

	@Override
	public String ckID(loginVO vo) {
		DBconn = DBConnection.getInstance();
		String ck = "";
		
		try {
			SQL=" SELECT * FROM member WHERE mid = ? ";
			conn = DBconn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, vo.getId());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				ck = "T";
				System.out.println("중복입니다.");
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return ck;
	}
}
