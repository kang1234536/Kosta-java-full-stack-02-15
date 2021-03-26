package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;

public class LoanTextDAO {
	static List<LoanTextVO> ltvlist = new ArrayList<LoanTextVO>();
	//관리자에서 대출정보 얻기1
	public List<LoanTextVO> selectById(int user) {
		
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select l.cust_id,l.loantext_money,l.loantext_year,l.loan_type_name,l.loantext_number,ll.loan_type_rate from LoanText l,loan_type ll  where  l.loan_type_name=ll.loan_type_name and l.manager_id=?"; // 직원의 아이디 가변 ?
		try {
			st = conn.prepareStatement(sql);
			st.setInt(1, user);
	
			rs = st.executeQuery();
			while (rs.next()) {
				ltvlist.add(makeLtvM1(rs));// 이문장은 길기에 함수로 뺴놓자! ->makeEmp

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DBUtil.dbClose(rs, st, conn);
		return ltvlist;
	}

//관리자에서 대출정보 얻기 2
private LoanTextVO makeLtvM1(ResultSet rs) throws SQLException  {
	LoanTextVO ltv = new LoanTextVO(); 
	
	ltv.setLoanText_number(rs.getInt("LoanText_number"));
	ltv.setLoanText_money(rs.getInt("LoanText_money"));
	ltv.setLoanText_year(rs.getInt("LoanText_year"));	
	ltv.setLoan_type_name(rs.getString("Loan_type_name"));
	ltv.setCust_id(rs.getInt("Cust_id"));
	ltv.setLoanText_month(rs.getInt("loan_type_rate")); //이자율 임의로 넣기

	return ltv;
}




//대출조회하기 대출정보들 전부 구하기2
	private LoanTextVO makeLtv(ResultSet rs) throws SQLException  {
		LoanTextVO ltv = new LoanTextVO(); 
		
		ltv.setLoanText_number(rs.getInt("LoanText_number"));
		ltv.setLoanText_money(rs.getInt("LoanText_money"));
		ltv.setLoanText_year(rs.getInt("LoanText_year"));
		ltv.setLoanText_month(rs.getInt("LoanText_month"));
		ltv.setCust_id(rs.getInt("Cust_id"));
		ltv.setLoan_type_name(rs.getString("Loan_type_name"));
		ltv.setManager_id(rs.getInt("Manager_id"));
	
		return ltv;
	}	
	//대출정보 넣기 (고객)1
	public int insertLD(LoanTextVO lv) {
		String sql ="insert into LoanText values(LoanText_seq.nextval,?,?,?,?,?,1)";
		Connection conn;
		PreparedStatement st=null;
		int result=0;
		
		conn = DBUtil.getConnection();
		try {
			conn.setAutoCommit(false);  //한번에 여러 sql문을 실행하고자 하는경우 사용
			st=conn.prepareStatement(sql);
			
			st.setInt(1, lv.getLoanText_money());
			st.setInt(2, lv.getLoanText_year());				
			st.setInt(3, lv.getLoanText_month());
			st.setInt(4, lv.getCust_id());
			st.setString(5, lv.getLoan_type_name());
			
		
			
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
	//대출조회하기 대출금과 대출달 구하기1
	public List<LoanTextVO> selectByName(String name) {
		List<LoanTextVO> ltvlist = new ArrayList<LoanTextVO>();
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select loantext_money, loantext_month "
				+ " from loantext "
				+ " where cust_id=(select cust_id from customer where cust_name =?)"; // 
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, name);
			rs = st.executeQuery();
			while (rs.next()) {
				ltvlist.add(findLtv(rs));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DBUtil.dbClose(rs, st, conn);
		return ltvlist;
	}
	//대출조회하기 대출정보들 전부 구하기1
	public List<LoanTextVO> selectByName1(String name) {
		List<LoanTextVO> ltvlist = new ArrayList<LoanTextVO>();
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "select * "
				+ " from loantext "
				+ " where cust_id=(select cust_id from customer where cust_name =?)"; // 
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
	//대출조회하기 대출금과 대출달 구하기2
	private LoanTextVO findLtv(ResultSet rs) throws SQLException {
		LoanTextVO ltv = new LoanTextVO(); 
		
		
		ltv.setLoanText_money(rs.getInt("LoanText_money"));
		ltv.setLoanText_month(rs.getInt("LoanText_month"));
		
		
	
		return ltv;
	}




}
