package view;

import java.util.List;

import model.CustomerVO;





public class CustomerView {

	public static void display(List<CustomerVO> ctvlist) {
		//직원의 정보 여러건 출력
		System.out.println("-----??의 정보 여러건 출력------");
		for(CustomerVO ctv:ctvlist) {
			System.out.println("****"+ctv.getCust_id()+"******");
			System.out.println(ctv);
		}
	}
	public static void display(CustomerVO ctv) {
		System.out.println("-----직원의 정보 1건 출력------");
		System.out.println(ctv);
	}
	public static void display(String message) {
		System.out.println("-----알림------");
		System.out.println(message);
	}

}
