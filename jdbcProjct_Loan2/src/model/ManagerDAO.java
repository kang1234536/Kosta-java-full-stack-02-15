package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;

public class ManagerDAO {
	//관리자 id와pass로 로그인하기1 
	public List<ManagerVO> selectByIP(int user, int pass) {
		// TODO Auto-generated method stub
		List<ManagerVO> mlist = new ArrayList<ManagerVO>();
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select * from manager where manager_id=? and manager_pass=?"; // 직원의 아이디 가변 ?
		try {
			st = conn.prepareStatement(sql);
			st.setInt(1, user);
			st.setInt(2, pass);
			rs = st.executeQuery();
			while (rs.next()) {
				mlist.add(makemg(rs));
		//		mvo = makemg(rs);// 이문장은 길기에 함수로 뺴놓자! ->makeEmp

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DBUtil.dbClose(rs, st, conn);
		return mlist;
	}
	//관리자 id와pass로 로그인하기2 
	private ManagerVO makemg(ResultSet rs) throws SQLException {
		ManagerVO mgv = new ManagerVO(); 		
		mgv.setManager_id(rs.getInt("Manager_id"));
		mgv.setManager_pass(rs.getInt("Manager_pass"));
		mgv.setManager_name(rs.getString("Manager_name"));
	
		return mgv;
	}
}
