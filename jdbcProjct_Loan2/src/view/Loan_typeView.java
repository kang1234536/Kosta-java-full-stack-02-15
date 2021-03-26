package view;

import java.util.List;


import model.CustomerVO;
import model.Loan_typeVO;





public class Loan_typeView {

	public static void display(List<Loan_typeVO> ltvlist) {
		//직원의 정보 여러건 출력
		System.out.println("-----??의 정보 여러건 출력------");
		for(Loan_typeVO ltv:ltvlist) {
			System.out.println("****"+ltv.getLoan_type_name()+"******");
			System.out.println(ltv);
			
		}
	}
	public static void display(Loan_typeVO ltv) {
		System.out.println("-----직원의 정보 1건 출력------");
		System.out.println(ltv);
	}
	public static void display(String message) {
		System.out.println("-----알림------");
		System.out.println(message);
	}

}
