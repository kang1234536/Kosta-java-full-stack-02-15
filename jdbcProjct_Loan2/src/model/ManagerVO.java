package model;

public class ManagerVO {
	private int manager_id; //매니저 아이디
	private int manager_pass; //매니저 비밀번호
	private String manager_name; //매니저이름

	public ManagerVO() {
		super();
	}

	public ManagerVO(int manager_id, int manager_pass, String manager_name) {
		super();
		this.manager_id = manager_id;
		this.manager_pass = manager_pass;
		this.manager_name = manager_name;
	}

	public int getManager_id() {
		return manager_id;
	}

	public void setManager_id(int manager_id) {
		this.manager_id = manager_id;
	}

	public int getManager_pass() {
		return manager_pass;
	}

	public void setManager_pass(int manager_pass) {
		this.manager_pass = manager_pass;
	}

	public String getManager_name() {
		return manager_name;
	}

	public void setManager_name(String manager_name) {
		this.manager_name = manager_name;
	}

	@Override
	public String toString() {
		return "ManagerVO [manager_id=" + manager_id + ", manager_pass=" + manager_pass + ", manager_name="
				+ manager_name + "]";
	}
	

	
	
}
