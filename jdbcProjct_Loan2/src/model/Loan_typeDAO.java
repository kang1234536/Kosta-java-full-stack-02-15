package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;

//관리자가 관리

public class Loan_typeDAO {
	//관리자에서 대출정보얻기 이름으로 식별1
	public List<Loan_typeVO> selectByNameloan(String name) {
		List<Loan_typeVO> ltvlist = new ArrayList<Loan_typeVO>();
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select * from loan_type where loan_type_name=? "; // 
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, name);
			rs = st.executeQuery();
			while (rs.next()) {
				ltvlist.add(makeLtv(rs));// 이문장은 길기에 함수로 뺴놓자! ->makeEmp

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DBUtil.dbClose(rs, st, conn);
		return ltvlist;
	}
	
	
	
	
	//관리자에서 대출정보 얻기1
	public List<Loan_typeVO> selectAll() {
		List<Loan_typeVO> ltlist = new ArrayList<Loan_typeVO>();
		Connection conn = DBUtil.getConnection();
		Statement st = null;
		ResultSet rs = null;
		String sql = "select * from Loan_type";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				ltlist.add(makeLtv(rs)); // 이문장은 길기에 함수로 뺴놓자! ->makeEmp
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DBUtil.dbClose(rs, st, conn);
		return ltlist;
	}
	//관리자에서 대출정보 얻기2
	private Loan_typeVO makeLtv(ResultSet rs) throws SQLException  {
		Loan_typeVO ltv = new Loan_typeVO(); 
		ltv.setLoan_type_name(rs.getString("Loan_type_name"));
		ltv.setLoan_type_min(rs.getInt("Loan_type_min"));
		ltv.setLoan_type_max(rs.getInt("Loan_type_max"));
		ltv.setLoan_type_rate(rs.getInt("Loan_type_rate"));
		ltv.setLoan_type_day(rs.getDate("Loan_type_day"));
		ltv.setManager_id(rs.getInt("Manager_id"));
		return ltv;
	}
	
	
	//대출금 납부하기에서 이자율얻기1
	public List<Loan_typeVO> selectByName(String name) {
		List<Loan_typeVO> ltvlist = new ArrayList<Loan_typeVO>();
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select lt.loan_type_rate "
				+ " from customer c join loantext l using(cust_id) join loan_type lt using(loan_type_name) "
				+ " where c.cust_name=?"; // 
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, name);
			rs = st.executeQuery();
			while (rs.next()) {
				ltvlist.add(findLtv1(rs));// 이문장은 길기에 함수로 뺴놓자! ->makeEmp

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DBUtil.dbClose(rs, st, conn);
		return ltvlist;
	}
	
	//대출금 납부하기에서 이자율얻기2
	private Loan_typeVO findLtv(ResultSet rs) throws SQLException {
		Loan_typeVO ltv = new Loan_typeVO(); 
	
		ltv.setLoan_type_rate(rs.getInt("Loan_type_rate"));
		ltv.setLoan_type_min(rs.getInt("round(l.loantext_money*10000*((ll.loan_type_rate+100)/100))"));
		ltv.setLoan_type_max(rs.getInt("round(l.loantext_money*10000*((ll.loan_type_rate+100)/100)/l.loantext_month)"));
	
		return ltv;
	}
	//대출금 납부하기에서 이자율얻기3
	private Loan_typeVO findLtv1(ResultSet rs) throws SQLException {
		Loan_typeVO ltv = new Loan_typeVO(); 
	
		ltv.setLoan_type_rate(rs.getInt("Loan_type_rate"));

	
		return ltv;
	}



	//관리자에서 대출정보 삽입하기1
	public int insert(Loan_typeVO ltdvo) {
		String sql ="insert into loan_type values(?,?,?,?,?,?)";
		Connection conn;
		PreparedStatement st=null;
		int result=0;
		
		conn = DBUtil.getConnection();
		try {
			conn.setAutoCommit(false);  //한번에 여러 sql문을 실행하고자 하는경우 사용
			st=conn.prepareStatement(sql);
			st.setString(1, ltdvo.getLoan_type_name());
			st.setInt(2, ltdvo.getLoan_type_min());
			st.setInt(3, ltdvo.getLoan_type_max());
			st.setInt(4, ltdvo.getLoan_type_rate());
			st.setDate(5, ltdvo.getLoan_type_day());
			st.setInt(6, ltdvo.getManager_id());
			
			
			result =st.executeUpdate(); //insert/update/delete는 executeUpdate()를 써야한다.
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(null, st, conn);
		}
		
		return result;
	}




	//관리자에서 수정하기1
	public int update(Loan_typeVO ltdvo) {
		Connection conn;
		PreparedStatement st = null;
		int result = 0;
		String sql = "update loan_type set "
				+    " loan_type_min=?, "
				+    " loan_type_max=?, "
				+    " loan_type_rate=?, "
				+    " loan_type_day=?, "
				+    " MANAGER_ID=? "
				+    " where loan_type_name=? ";
		conn = DBUtil.getConnection();
		try {
			st = conn.prepareStatement(sql);
			
			st.setString(6, ltdvo.getLoan_type_name());
			st.setInt(1, ltdvo.getLoan_type_min());
			st.setInt(2, ltdvo.getLoan_type_max());
			st.setInt(3, ltdvo.getLoan_type_rate());
			st.setDate(4, ltdvo.getLoan_type_day());
			st.setInt(5, ltdvo.getManager_id());
			
			result = st.executeUpdate(); // insert/update/delete는 executeUpdate()를 써야한다.
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(null, st, conn);
		}
		return result;
	}




	//관리자에서 삭제하기1
	public int delete(String loan_name) {
		Connection conn;
		PreparedStatement st = null;
		int result = 0;
		String sql ="delete from loan_type where loan_type_name=? ";
		conn = DBUtil.getConnection();
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, loan_name);
			result = st.executeUpdate(); // insert/update/delete는 executeUpdate()를 써야한다.
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(null, st, conn);
		}
		
		
		return result;
	}






	
		
	
	
	
}
