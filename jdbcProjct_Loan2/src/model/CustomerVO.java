package model;

public class CustomerVO {
	private int cust_id; //고객번호  
	private String cust_name; //고객이름
	private String cust_job; //고객직업
	private String cust_phone; //고객 전화번호
	private String cust_account;//고객 계좌번호
	private int cust_account_balance; //고객 계좌 잔액
	private int cust_pass; //고객 비밀번호
	private int manager_id; //관리자 아이디
	public CustomerVO() {
		super();
	}
	public CustomerVO(int cust_id, String cust_name, String cust_job, String cust_phone, String cust_account,
			int cust_account_balance, int cust_pass, int manager_id) {
		super();
		this.cust_id = cust_id;
		this.cust_name = cust_name;
		this.cust_job = cust_job;
		this.cust_phone = cust_phone;
		this.cust_account = cust_account;
		this.cust_account_balance = cust_account_balance;
		this.cust_pass = cust_pass;
		this.manager_id = manager_id;
	}
	public int getCust_id() {
		return cust_id;
	}
	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public String getCust_job() {
		return cust_job;
	}
	public void setCust_job(String cust_job) {
		this.cust_job = cust_job;
	}
	public String getCust_phone() {
		return cust_phone;
	}
	public void setCust_phone(String cust_phone) {
		this.cust_phone = cust_phone;
	}
	public String getCust_account() {
		return cust_account;
	}
	public void setCust_account(String cust_account) {
		this.cust_account = cust_account;
	}
	public int getCust_account_balance() {
		return cust_account_balance;
	}
	public void setCust_account_balance(int cust_account_balance) {
		this.cust_account_balance = cust_account_balance;
	}
	public int getCust_pass() {
		return cust_pass;
	}
	public void setCust_pass(int cust_pass) {
		this.cust_pass = cust_pass;
	}
	public int getManager_id() {
		return manager_id;
	}
	public void setManager_id(int manager_id) {
		this.manager_id = manager_id;
	}
	@Override
	public String toString() {
		return "CustomerVO [cust_id=" + cust_id + ", cust_name=" + cust_name + ", cust_job=" + cust_job
				+ ", cust_phone=" + cust_phone + ", cust_account=" + cust_account + ", cust_account_balance="
				+ cust_account_balance + ", cust_pass=" + cust_pass + ", Manager_id=" + manager_id + "]";
	}
	
	
	

	
	
}
