package view;

import java.util.List;


import model.LoanTextVO;







public class LoanTextView {

	public static void display(List<LoanTextVO> ltvlist) {
		//직원의 정보 여러건 출력
		System.out.println("-----??의 정보 여러건 출력------");
		for(LoanTextVO ltv:ltvlist) {
			System.out.println("****"+ltv.getLoan_type_name()+"******");
			System.out.println(ltv);
			
		}
	}
	public static void display(LoanTextVO ltv) {
		System.out.println("-----직원의 정보 1건 출력------");
		System.out.println(ltv);
	}
	public static void display(String message) {
		System.out.println("-----알림------");
		System.out.println(message);
	}

}
