package model;

public class LoanTextVO {
	private int loanText_number; //대출번호
	private int loanText_money; //대출금
	private int loanText_year; //대출 기간(년단위)
	private int loanText_month; //대출 기간(달단위)
	private int cust_id; //고객 번호
	private String loan_type_name; //대출 이름
	private int manager_id; //매니저 아이디
	public LoanTextVO() {
		super();
	}
	public LoanTextVO(int loanText_number, int loanText_money, int loanText_year, int loanText_month, int cust_id,
			String loan_type_name, int manager_id) {
		super();
		this.loanText_number = loanText_number;
		this.loanText_money = loanText_money;
		this.loanText_year = loanText_year;
		this.loanText_month = loanText_month;
		this.cust_id = cust_id;
		this.loan_type_name = loan_type_name;
		this.manager_id = manager_id;
	}
	public int getLoanText_number() {
		return loanText_number;
	}
	public void setLoanText_number(int loanText_number) {
		this.loanText_number = loanText_number;
	}
	public int getLoanText_money() {
		return loanText_money;
	}
	public void setLoanText_money(int loanText_money) {
		this.loanText_money = loanText_money;
	}
	public int getLoanText_year() {
		return loanText_year;
	}
	public void setLoanText_year(int loanText_year) {
		this.loanText_year = loanText_year;
	}
	public int getLoanText_month() {
		return loanText_month;
	}
	public void setLoanText_month(int loanText_month) {
		this.loanText_month = loanText_month;
	}
	public int getCust_id() {
		return cust_id;
	}
	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
	}
	public String getLoan_type_name() {
		return loan_type_name;
	}
	public void setLoan_type_name(String loan_type_name) {
		this.loan_type_name = loan_type_name;
	}
	public int getManager_id() {
		return manager_id;
	}
	public void setManager_id(int manager_id) {
		this.manager_id = manager_id;
	}
	@Override
	public String toString() {
		return "LoanTextVO [loanText_number=" + loanText_number + ", loanText_money=" + loanText_money
				+ ", loanText_year=" + loanText_year + ", loanText_month=" + loanText_month + ", cust_id=" + cust_id
				+ ", loan_type_name=" + loan_type_name + ", manager_id=" + manager_id + "]";
	}

	
	
}

