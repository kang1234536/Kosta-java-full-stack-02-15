package controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.CustomerDAO;
import model.CustomerVO;
import model.LoanTextDAO;
import model.LoanTextVO;
import model.Loan_typeDAO;
import model.Loan_typeVO;
import model.ManagerDAO;
import model.ManagerVO;
import view.CustomerView;
import view.LoanTextView;
import view.Loan_typeView;


public class MainController {
	static CustomerDAO ctd = new CustomerDAO();
	static CustomerVO ctv = new CustomerVO();
	static List<CustomerVO> ctvlist = new ArrayList<CustomerVO>();
	static	LoanTextDAO ltd = new LoanTextDAO();
	static LoanTextVO ltvo = new LoanTextVO();
	static List<LoanTextVO> ltvlist = new ArrayList<LoanTextVO>();
	static	Loan_typeDAO ltdao = new Loan_typeDAO();
	static	Loan_typeVO ltdvo = new Loan_typeVO();
	static List<Loan_typeVO> ltvolist=new ArrayList<Loan_typeVO>();
	static ManagerDAO mdao= new ManagerDAO();
	static ManagerVO mvo= new ManagerVO();
	static List<ManagerVO> mlist = new ArrayList<ManagerVO>();
	public static void main(String[] args) {
		// 메인!!
	
		
		first_screen();

	}
	//메인화면
	private static void first_screen() {
		// 첫번쨰 화면
		int user = 0; // id
		int pass = 0;// password
		int result = 0;
		Scanner scan = new Scanner(System.in);
		while (true) {
			System.out.println(
					"========================================================================================================================");
			System.out.println("                                                      0.관리자||1.회원가입||2.로그인||3.종료");

			System.out.println(
					"========================================================================================================================");
			int work = scan.nextInt();
			switch (work) {
			case 0:
				System.out.println("예제 프로그램 관리자 ||  id/pass 는 1/1234로 고정!!!");
				System.out.println("관리자로 접속 하시 겠습니까?(Y)|(N)");
				String value =scan.next();
				switch (value) {
				case "y", "Y":
					System.out.print("id(숫자만 입력해주세요): ");
					int id1 = scan.nextInt();
					mvo.setManager_id(id1);
					System.out.print("pass(숫자만 입력해주세요) :");
					int pass1 = scan.nextInt();
					mvo.setManager_pass(pass1);
					 mlist = mdao.selectByIP(id1,pass1);
					if(mlist.size()==0) {
						System.out.println("로그인에 실패하였습니다.");
					}					
					for (ManagerVO mv : mlist) {
						
						
						if (mv.getManager_id()==id1 && mv.getManager_pass()==pass1) {
							System.out.println("로그인에 성공하였습니다.");
							manager_screen(mlist);
						}
					
					
					 }
					break;
				case "n", "N":
					System.out.println("안녕히가세요");
					System.exit(0);
					break;
				default:
					System.out.println("다시 입력해주세요");
					break;

				}
				break;
			case 1:
				System.out.println("회원가입을 하시겠습니까? (Y)|(N)");
				String key = scan.next();
				switch (key) {
				case "y", "Y":
					cc: System.out.println("정보를 적어주세요!!");
					System.out.print("id(숫자만 입력해주세요): ");
					int id1 = scan.nextInt();
					List<CustomerVO> ctvlist = ctd.selectbyID(id1);
					for (CustomerVO ct : ctvlist) {
						if (ct.getCust_id() == id1) {
							System.out.println("아이디가 다른사용자와 겹칩니다 다른 아이디를 입력해주세요");
							System.out.println("프로그램을 종료");
							System.exit(0);
						}
					}
					ctv.setCust_id(id1);
					System.out.print("password: ");
					ctv.setCust_pass(scan.nextInt());
					System.out.print("name: ");
					ctv.setCust_name(scan.next());
					System.out.print("job(무직,대학생,직장인,주부) 나머지는 허용하지않습니다!!!: ");
					ctv.setCust_job(scan.next());
					System.out.print("phone: ");
					ctv.setCust_phone(scan.next());
					System.out.print("accountNo: ");
					ctv.setCust_account(scan.next());
					System.out.print("accountNo_balance: ");
					ctv.setCust_account_balance(scan.nextInt());
					ctv.setManager_id(1); //관리자 아이디는 1로 고정!!
					
					result = ctd.insertCTM(ctv);
					CustomerView.display(result > 0 ? "입력성공" : "입력실패");
					break;
				case "n", "N":
					System.out.println("안녕히가세요");
					System.exit(0);
					break;
				default:
					System.out.println("다시 입력해주세요");
					break;

				}
				break;
			case 2:
				System.out.print("아이디 :");
				user = scan.nextInt();
				System.out.print("비밀번호 :");
				pass = scan.nextInt();
				select(user, pass);

				break;
			case 3:
				System.out.println("프로그램이 종료되었습니다.");
				System.exit(0);
			default:
				break;
			}

		}

	}
	// 관리자 화면
	private static void manager_screen(List<ManagerVO> mlist2) {
		// 관리자 화면
		Scanner scan = new Scanner(System.in);
		int mid =mvo.getManager_id();
		int result =0; // 다중사용!!
		int c_id=0;//cust_id
		int c_pass=0;//cust_pass
		String c_phone =null;//cust_phone
		String c_name =null;//cust_name
		String c_job =null; //cust_job
		String c_accountNo=null; //cust_account
		
//		loantext_money,loantext_year,loan_type_name,loantext_number
		int l_number =0; //loantext_number
		String l_name =null; //loan_type_name
		int l_year =0; //loantext_year
		int l_money =0; //loantext_money
		int l_cust_id=0; //cust_id
		int l_rate =0; //이자!!
		int l_total=0; //총대출금액
		int l_month_money=0; //매달갚아야할금액
//		loan_type_rate
		int rate =0; //loan_type_rate
		int lt_money=0; //loan_type 에서 갚아야할최종금액
		int lt_money_month =0; //loan_type에서 매달 갚아야할금액
		while(true) {
			System.out.println("1.고객정보|대출정보 확인하기||2.대출종류 조회하기||3.대출종류 삽입하기||4.대출종류 수정하기||5.대출종류 삭제하기||나머지키는 종료!!");
			
			int m_work = scan.nextInt();
			switch(m_work) {
			case 1:
				
				ctvlist=ctd.selectbyID1(mid);
				
				for(CustomerVO cc:ctvlist) {
				
					c_id=cc.getCust_id();
					c_pass=cc.getCust_pass();
					c_phone =cc.getCust_phone();
					c_name=cc.getCust_name();
					c_job=cc.getCust_job();
					c_accountNo=cc.getCust_account();
					System.out.println("======================================================================");
					System.out.println(c_id+"번 고객님의 개인정보");
					System.out.println("고객아이디:"+c_id+"|고객비밀번호:"+c_pass+"|고객 전화번호:"+c_phone+"|고객이름:"+c_name+"|고객직업:"+c_job+"|고객의 계좌번호:"+c_accountNo+"|");
					
				}
				ltvlist=ltd.selectById(mid);
				for(LoanTextVO ll: ltvlist) {
					l_number=ll.getLoanText_number();
					l_name=ll.getLoan_type_name();
					l_year=ll.getLoanText_year();
					l_money=ll.getLoanText_money();
					l_cust_id=ll.getCust_id();
					l_rate=ll.getLoanText_month(); //이자율 임의로 넣기
					l_total=(int)Math.round((l_money*10000)*((double)(l_rate+100)/100)) ;
					l_month_money=(int)Math.round((l_money*10000)*((double)(l_rate+100)/100)/(l_year*12));
					System.out.println("======================================================================");
					System.out.println(l_cust_id+"번 고객님의 대출정보");
					System.out.println("대출조회번호:"+l_number+"|대출이름:"+l_name+"|대출기간(년단위):"+l_year+"년|대출희망금액:"+l_money+"만원|"+"이자율"+l_rate+"%|총 대출 금액은:"+l_total+"원|매달 납부 금액은:"+l_month_money+"원|||");
				}

				System.out.println("======================================================================");

				break;
			case 2:
				ltvolist=ltdao.selectAll();
				for(Loan_typeVO ltvolist2:ltvolist) {
					System.out.println(ltvolist2);
				}
			
				break;
			case 3:
				System.out.println("대출종류를 넣으시겠습니까? (Y)|(N)");
				String key = scan.next();
				switch (key) {
				case "y", "Y":
					 System.out.println("정보를 적어주세요!!");
					System.out.print("대출이름을 적어주세요: ");
					String loan_name=scan.next();
					ltvolist = ltdao.selectByNameloan(loan_name);
					for (Loan_typeVO loanlist : ltvolist) {
						if (loanlist.getLoan_type_name().equals(loan_name)) {
							System.out.println("대출이름이 겹칩니다!!");
							System.out.println("프로그램을 종료");
							System.exit(0);
							break;
						}
						else {
							System.out.println(loan_name+"대출을 생성하겠습니다.");
							break;
						}
					}
					ltdvo.setLoan_type_name(loan_name);
					
					System.out.print("대출최소금액을 적어주세요: ");
					ltdvo.setLoan_type_min(scan.nextInt());
					System.out.print("대출최대금액을 적어주세요: ");
					ltdvo.setLoan_type_max(scan.nextInt());
					System.out.print("대출 이자율을 적어주세요: ");
					ltdvo.setLoan_type_rate(scan.nextInt());
					//date타입은 나중에 넣기 시간많이 뺏김
					ltdvo.setLoan_type_day(new Date(new java.util.Date().getTime()));
					ltdvo.setManager_id(1); //관리자 아이디는 1로 고정!!
					
					result = ltdao.insert(ltdvo);
					Loan_typeView.display(result > 0 ? "입력성공" : "입력실패");
					break;
				case "n", "N":
					System.out.println("안녕히가세요");
					System.exit(0);
					break;
				default:
					System.out.println("다시 입력해주세요");
					break;

				}
				break;
			case 4:
				ltvolist=ltdao.selectAll();
				
				for(Loan_typeVO ltvolist2:ltvolist) {
					System.out.println(ltvolist2);
					
				}
				System.out.print("어떤 대출을 수정하시겠습니까(대출이름(Loan_type_name)을 적으세요)>>");
				String loan_key = scan.next();

				for (Loan_typeVO loanlist : ltvolist) {
					
					if (loan_key.equals(loanlist.getLoan_type_name())) {
						System.out.println(loan_key+"대출 수정하겠습니다!!");

						break;
					}
					else {
						System.out.println("잘못된 대출이름을 입력하셨습니다.");
						System.exit(0);
						break;
					}
				}
				

				
				ltdvo.setLoan_type_name(loan_key);
				
				System.out.print("대출최소금액을 적어주세요: ");
				ltdvo.setLoan_type_min(scan.nextInt());
				System.out.print("대출최대금액을 적어주세요: ");
				ltdvo.setLoan_type_max(scan.nextInt());
				System.out.print("대출 이자율을 적어주세요: ");
				ltdvo.setLoan_type_rate(scan.nextInt());
				//date타입은 나중에 넣기 시간많이 뺏김
				ltdvo.setLoan_type_day(new Date(new java.util.Date().getTime()));
				ltdvo.setManager_id(1); //관리자 아이디는 1로 고정!!
				
				result = ltdao.update(ltdvo);
				Loan_typeView.display(result > 0 ? "수정성공" : "수정실패");
				break;
				
			case 5:
				ltvolist=ltdao.selectAll();
				for(Loan_typeVO ltvolist2:ltvolist) {
					System.out.println(ltvolist2);
				}
				System.out.print("삭제하실 대출이름을 작성하시오>>");
				String loan_name=scan.next();
//				boolean ok =false; //향상된 for문에서 비교를 하기위해!!
//				ok=true;
//				if(!ok) {
//				break;
//			}
				for(Loan_typeVO ltvolist2:ltvolist) {
					if(loan_name.equals(ltvolist2.getLoan_type_name())) {
						System.out.println(loan_name+"을 삭제하겠습니다.");
						break;
					}
					else {
						System.out.println("잘못된 대출이름을 입력하셨습니다.");
						System.exit(0);
						break;
					}
				}
				
				
				result =ltdao.delete(loan_name);
				Loan_typeView.display(result > 0 ? "삭제성공" : "삭제실패");
				break;
				
				
			default:
				System.err.println("프로그램이 종료되었습니다!");
				System.exit(0);
				break;
			}
		}
		
		
	}
	//로그인
	private static void select(int user, int pass) {
			
			ctv = ctd.selectByIP(user, pass);
			LoanTextDAO ltd = new LoanTextDAO();
		
			ltvlist = ltd.selectById(user);
			if (ctv == null) {
				System.out.println("아이디 및 비밀번호가 잘못되었습니다.");
			} else {
				System.out.println("로그인에 성공하였습니다.");

				Two_Screen(ctv, ltvlist);
			}
			
		}
	//두번쨰화면
	private static void Two_Screen(CustomerVO ctv,List<LoanTextVO> ltvlistsize) {
			// TODO Auto-generated method stub
			
			Scanner scan = new Scanner(System.in);
			String name = null;

			while (true) {
				
				System.out.println("1.대출가능여부 확인하기||2.대출금 납부하기||3.대출내역조회");

				int loan_work = scan.nextInt();

				switch (loan_work) {
				case 1:
					aa: if (ctv.getCust_job().equals("무직")&& ltvlistsize.size()>=0 &&ltvlistsize.size()<1) {
						System.out.println("대출이 가능합니다");
						System.out.println("1.대출하기2.이전화면 돌아가기 >>");
						int loan_work1 = scan.nextInt();
						switch (loan_work1) {
						case 1:
							Loan_func(ctv.getCust_id());
							break;
						case 2:
							break aa;
						default:
							break;
						}
					}
					else if(ctv.getCust_job().equals("대학생")&& ltvlistsize.size()>=0 &&ltvlistsize.size()<1) {
						System.out.println("대출이 가능합니다");
						System.out.println("1.대출하기2.이전화면 돌아가기 >>");
						int loan_work1 = scan.nextInt();
						switch (loan_work1) {
						case 1:
							Loan_func(ctv.getCust_id());
							break;
						case 2:
							break aa;
						default:
							break;
					}
					}
					else if(ctv.getCust_job().equals("직장인")&& ltvlistsize.size()>=0 &&ltvlistsize.size()<1) {
						System.out.println("대출이 가능합니다");
						System.out.println("1.대출하기2.이전화면 돌아가기 >>");
						int loan_work1 = scan.nextInt();
						switch (loan_work1) {
						case 1:
							Loan_func(ctv.getCust_id());
							break;
						case 2:
							break aa;
						default:
							break;
					}
					}
					else if(ctv.getCust_job().equals("주부")&& ltvlistsize.size()>=0 &&ltvlistsize.size()<1) {
						System.out.println("대출이 가능합니다");
						System.out.println("1.대출하기2.이전화면 돌아가기 >>");
						int loan_work1 = scan.nextInt();
						switch (loan_work1) {
						case 1:
							Loan_func(ctv.getCust_id());
							break;
						case 2:
							break aa;
						default:
							break;
					}
					}
					else {
						System.out.println("대출이 불가능하십니다");
						System.out.println("안녕히 가세요");
						System.exit(0);
					}

					break;
				case 2:				
					System.out.println(ctv.getCust_id());
					name =ctv.getCust_name();
					ltvlist = ltd.selectByName(name);
					
					ltvolist=ltdao.selectByName(name);
					int a=0; //대출금액
					int b=0; //달
					int c=0; //이자율
					
					for(LoanTextVO list:ltvlist) {
					a=list.getLoanText_money()*10000;
					b=list.getLoanText_month();
					}
					for(Loan_typeVO list1:ltvolist) {
						c=list1.getLoan_type_rate();
					}
					int d=(int)(a*((double)(c+100)/100)/b); //달마다 납부할 금액
					
					System.out.println(name+"고객님이 이번달에 납부하실금액은:"+d+"원 입니다.");
					System.out.print("납부하시겠습니까?(Y,N)");
					String text = scan.next();
					if(text.equals("y")||text.equals("Y")) {
						ctd.updatecust1(ctv.getCust_id());
						System.out.println("납부하셨습니다.");
					
					}
					else if(text.equals("n")||text.equals("N"))
					{
						System.out.println("다음에 이용해주세요");
					}
					else {
						System.out.println("프로그램종료");
						System.exit(0);
					}
					break;
				case 3:
					
					name = ctv.getCust_name();
					ltvlist = ltd.selectByName(name);
					
					ltvolist=ltdao.selectByName(name);
					int a2=0; //대출금액
					int b2=0; //달
					int c2=0; //이자율
					int e=0;//대출조회번호
					String f=null; //대출이름
					int g=0;//대출 년단위
					int h=0; //고객번호
					
					for(LoanTextVO list:ltvlist) {
					a2=list.getLoanText_money()*10000;
					b2=list.getLoanText_month();
					}
					for(Loan_typeVO list1:ltvolist) {
						c2=list1.getLoan_type_rate();
					}
					int d2=(int)(a2*((double)(c2+100)/100)/b2); //달마다 납부할 금액
					int i=(int)(a2*((double)(c2+100)/100)); //총대출금
					ltvlist  = ltd.selectByName1(name);
					for(LoanTextVO ltvlist1:ltvlist) {
						System.out.println(ltvlist1);
					e=ltvlist1.getLoanText_number();
					f=ltvlist1.getLoan_type_name();
					g=ltvlist1.getLoanText_year();
					h=ltvlist1.getCust_id();
					}
					System.out.println("대출조회번호:"+e+"번|고객이름:"+name+"|대출이름:"+f+"|대출원금:"+a2/10000+"만원|이자율:"+c2+"%|대출금:"+i+"원|대출기간(1년단위):"+g+"년|고객번호:"+h+"번|달마다 갚아야 하는 금액:"+d2+"원");
					break;
				default:
				System.out.println("프로그램이 종료되었습니다");
				System.exit(0);
				break;
				}
			}
		}
	//대출db관련
	private static void Loan_func(int id) {
			// 대출db 관련 !!
			ltvo.setCust_id(id);
			int result = 0;
			Scanner scan = new Scanner(System.in);
			
			ltvolist = ltdao.selectAll();
			Loan_typeView.display(ltvolist);
			System.out.print("원하시는 대출을 고르시오 >>");
			String name = scan.next();

			ltvo.setLoan_type_name(name);
			for(Loan_typeVO ltvoo:ltvolist) {
				if(ltvoo.getLoan_type_name().equals(name)) {
					System.out.println(name+" 특징:||"+"연이자" + ltvoo.getLoan_type_rate() + "%||최소금액" + ltvoo.getLoan_type_min()
							+ "만원||최대금액" + ltvoo.getLoan_type_max() + "만원|| 대출기간은1년단위|");
					System.out.print("대출 하실 금액을 입력해주세요>>");
					int money=scan.nextInt();  //대출할금액
					
					
					if(money<ltvoo.getLoan_type_min()||money>ltvoo.getLoan_type_max()) {
						System.out.println("금액을 올바르게 입력해주세요(단위는" + ltvoo.getLoan_type_min() + "만원부터"
								+ ltvoo.getLoan_type_max()+ "만원까지입니다.)");
					}
					else {
						ltvo.setLoanText_money(money);
						System.out.println(money + "만원을 대출희망하셨습니다.");
						System.out.print("대출기간을 입력해주세요(대출기간은 1년단위입니다.)>>");
						int year=scan.nextInt();//대출기간
						if (year > 10 || year < 0) {
							System.out.println("대출기간은 10년을 넘길순 없습니다.");
						} else {
							ltvo.setLoanText_year(year);
							ltvo.setLoanText_month(year*12);
							System.out.println("고객님이 신청하신 대출기간은 : " + year + "년입니다.");
						}
						int paymoney = (int)Math.round(money*10000*(double)(ltvoo.getLoan_type_rate()+100)/100);
						System.out.println("총 갚으셔야 하는 금액은 :"+paymoney+"원 입니다");
						int paymonthmoney =(int)Math.round(money*10000*(double)(ltvoo.getLoan_type_rate()+100)/100)/ltvo.getLoanText_month();
					
						System.out.println("매달 갚아야 하는 금액은 :" +paymonthmoney+"원 입니다.");
						result =ltd.insertLD(ltvo);
						LoanTextView.display(result > 0 ? loan_success(id) : loan_fail());
					}
				}
			}
			
			} 
	//대출실패
	private static String loan_fail() {
			// TODO Auto-generated method stub
			return "실패";
		}
	//대출성공
	private static String loan_success(int id) {
			//
			

			int result = ctd.updatecust(id);

			return "대출 성공하셨습니다. 대출금이 고객님의 계좌로 전달되었습니다.";
		}
	}
	

