package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;



public class CustomerDAO {
	//고객 정보 입력하기
	public int insertCTM(CustomerVO ctm) {
		String sql ="insert into Customer values(?,?,?,?,?,?,?,?)";
		Connection conn;
		PreparedStatement st=null;
		int result=0;
		
		conn = DBUtil.getConnection();
		try {
			conn.setAutoCommit(false);  //한번에 여러 sql문을 실행하고자 하는경우 사용
			st=conn.prepareStatement(sql);
			st.setInt(1, ctm.getCust_id());
			st.setString(2, ctm.getCust_name());
			st.setString(3, ctm.getCust_job());
			st.setString(4, ctm.getCust_phone());
			st.setString(5, ctm.getCust_account());
			st.setInt(6, ctm.getCust_account_balance());
			st.setInt(7, ctm.getCust_pass());
			st.setInt(8, ctm.getManager_id());
			
			
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
	// 고객 id로 정보 찾기
	public List<CustomerVO> selectbyID(int id1) {
		List<CustomerVO> ctvolist = new ArrayList<CustomerVO>();
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select * from Customer where cust_id=?"; // 직원의 아이디 가변 ?
		try {
			st = conn.prepareStatement(sql);
			st.setInt(1, id1);
	
			rs = st.executeQuery();
			while (rs.next()) {
				ctvolist.add(makeCtv(rs));// 이문장은 길기에 함수로 뺴놓자! ->makeEmp

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DBUtil.dbClose(rs, st, conn);
		return ctvolist;
	}
	//관리자에서 고객정보 조회
	public List<CustomerVO> selectbyID1(int id1) {
		List<CustomerVO> ctvolist = new ArrayList<CustomerVO>();
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select cust_id,cust_name,cust_job,cust_phone,cust_account,cust_pass,manager_id from Customer where manager_id=?"; // 직원의 아이디 가변 ?
		try {
			st = conn.prepareStatement(sql);
			st.setInt(1, id1);
	
			rs = st.executeQuery();
			while (rs.next()) {
				ctvolist.add(makeCtvM(rs));// 이문장은 길기에 함수로 뺴놓자! ->makeEmp

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DBUtil.dbClose(rs, st, conn);
		return ctvolist;
	}
	//관리자에서 고객정보 얻기
	private CustomerVO makeCtvM(ResultSet rs) throws SQLException  {
		CustomerVO ctv = new CustomerVO(); 	
		ctv.setCust_id(rs.getInt("Cust_id"));
		ctv.setCust_name(rs.getString("Cust_name"));
		ctv.setCust_job(rs.getString("Cust_job"));
		ctv.setCust_phone(rs.getString("Cust_phone"));
		ctv.setCust_account(rs.getString("Cust_account"));
		ctv.setCust_pass(rs.getInt("Cust_pass"));
		ctv.setManager_id(rs.getInt("Manager_id"));
		return ctv;
	}
	//고객에서 고객정보 얻기
	private CustomerVO makeCtv(ResultSet rs) throws SQLException  {
		CustomerVO ctv = new CustomerVO(); 	
		ctv.setCust_id(rs.getInt("Cust_id"));
		ctv.setCust_name(rs.getString("Cust_name"));
		ctv.setCust_job(rs.getString("Cust_job"));
		ctv.setCust_phone(rs.getString("Cust_phone"));
		ctv.setCust_account(rs.getString("Cust_account"));
		ctv.setCust_account_balance(rs.getInt("Cust_account_balance"));
		ctv.setCust_pass(rs.getInt("Cust_pass"));
		ctv.setManager_id(rs.getInt("Manager_id"));
		return ctv;
	}
	// id와 패스워드로 로그인 식별 고객전용
	public CustomerVO selectByIP(int user, int pass) {
		// TODO Auto-generated method stub
		CustomerVO ctv = null;
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select * from customer where cust_id=? and cust_pass=?"; // 직원의 아이디 가변 ?
		try {
			st = conn.prepareStatement(sql);
			st.setInt(1, user);
			st.setInt(2, pass);
			rs = st.executeQuery();
			while (rs.next()) {
				ctv = makeCtv(rs);// 이문장은 길기에 함수로 뺴놓자! ->makeEmp

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DBUtil.dbClose(rs, st, conn);
		return ctv;
	}
	//대출성공후 대출금 고객 잔액에 넣기(고객)
	public int updatecust(int id) {
		Connection conn;
		PreparedStatement st = null;
		int result = 0;
		String sql = "update customer set "
					+" cust_account_balance =cust_account_balance+(select loantext_money*10000 from loantext  where cust_id= ? ) "
					+" where cust_id =?";
		conn = DBUtil.getConnection();
		try {
			st = conn.prepareStatement(sql);
			
			st.setInt(1, id);
			st.setInt(2, id);
			result = st.executeUpdate();
			conn.commit();// insert/update/delete는 executeUpdate()를 써야한다.
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(null, st, conn);
		}
		return result;
	}	
	
	
	//대출금 납부하면서 잔액변경(고객)
	public int updatecust1(int id) {
		Connection conn;
		PreparedStatement st = null;
		int result = 0;
		String sql = "update customer set "
					+" cust_account_balance =cust_account_balance-(select round(l.loantext_money*10000*((lt.loan_type_rate+100)/100)/l.loantext_month)\r\n"
					+ " from loantext l join loan_type lt using(loan_type_name)\r\n"
					+ " where l.cust_id=? ) "
					+ " where cust_id=?";
		conn = DBUtil.getConnection();
		try {
			st = conn.prepareStatement(sql);
			
			st.setInt(1, id);
			st.setInt(2, id);
			result = st.executeUpdate();
			conn.commit();// insert/update/delete는 executeUpdate()를 써야한다.
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.dbClose(null, st, conn);
		}
		return result;
	}
	public List<CustomerVO> selectALL() {
		// TODO Auto-generated method stub
		return null;
	}	
	
	
}
