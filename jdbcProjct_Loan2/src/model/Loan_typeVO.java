package model;

import java.sql.Date;

public class Loan_typeVO {
	private String Loan_type_name;//대출이름
	private int Loan_type_min;//대출 최소금액
	private int	Loan_type_max;//대출 최대금액
	private int Loan_type_rate; //대출 이자율
	private Date Loan_type_day;//대출일
	private int manager_id; //관리자 아이디
	public Loan_typeVO() {
		super();
	}
	public Loan_typeVO(String loan_type_name, int loan_type_min, int loan_type_max, int loan_type_rate,
			Date loan_type_day, int manager_id) {
		super();
		Loan_type_name = loan_type_name;
		Loan_type_min = loan_type_min;
		Loan_type_max = loan_type_max;
		Loan_type_rate = loan_type_rate;
		Loan_type_day = loan_type_day;
		this.manager_id = manager_id;
	}
	public String getLoan_type_name() {
		return Loan_type_name;
	}
	public void setLoan_type_name(String loan_type_name) {
		Loan_type_name = loan_type_name;
	}
	public int getLoan_type_min() {
		return Loan_type_min;
	}
	public void setLoan_type_min(int loan_type_min) {
		Loan_type_min = loan_type_min;
	}
	public int getLoan_type_max() {
		return Loan_type_max;
	}
	public void setLoan_type_max(int loan_type_max) {
		Loan_type_max = loan_type_max;
	}
	public int getLoan_type_rate() {
		return Loan_type_rate;
	}
	public void setLoan_type_rate(int loan_type_rate) {
		Loan_type_rate = loan_type_rate;
	}
	public Date getLoan_type_day() {
		return Loan_type_day;
	}
	public void setLoan_type_day(Date loan_type_day) {
		Loan_type_day = loan_type_day;
	}
	public int getManager_id() {
		return manager_id;
	}
	public void setManager_id(int manager_id) {
		this.manager_id = manager_id;
	}
	@Override
	public String toString() {
		return "Loan_typeVO [Loan_type_name=" + Loan_type_name + ", Loan_type_min=" + Loan_type_min + ", Loan_type_max="
				+ Loan_type_max + ", Loan_type_rate=" + Loan_type_rate + ", Loan_type_day=" + Loan_type_day
				+ ", manager_id=" + manager_id + "]";
	}

	
}
