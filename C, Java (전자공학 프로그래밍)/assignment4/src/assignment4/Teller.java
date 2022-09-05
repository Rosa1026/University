package assignment4;

public class Teller implements Runnable{
	private String name;      // 이름
	private boolean working;  // 현재 상태
	private boolean workType; // true 면 입금, false면 대출
	private int progressTime; // 담당업무에 걸리는 시간
	private Visitor visitor;  // 담당중인 손님
	protected boolean Case;   // 대출 성공 실패를 나타내기 위해 선언한 Case
	
	public Teller(String name)
	{
		this.name = name;        //Teller의 이름
		this.working = false;    //Teller의 현재 상태
		this.workType = false;   //Teller의 업무
		this.progressTime = 0;   //Teller가 맡은 담당업무에 걸리는 시간
		
	}
	
	public void run() {		
		// TODO Auto-generated method stub
		if (this.working == false) //일하는 상태가 아니라면
		{
			DealCustomer();        //손님 배정
		}
		
		else                       //현재 일하는 상태라면
		{
			this.workType=this.visitor.CheckType(); //방문자의 업무를 점원의 업무에 저장
			if(this.progressTime>0) {
				try{
					Thread.sleep(100);
				} catch(InterruptedException e) {
					e.printStackTrace(); 
				}                                 //try-catch 구문을 사용해 Thread.sleep()을 줌. 1000ms 동안 멈춰있음.
				finally {
					this.progressTime-=1;         //try-catch 구문이 진행되면 업무시간을 1감소. 즉, 남은 업무 시간을 감소시키는 작업
				}
			}
			
			if(this.progressTime==0) {       //주어진 업무시간이 끝나면
				if(this.workType == false) {      //workType이 false라면 대출 업무 담당
					if(Case) {                    //Case에는 대출 가능 여부가 담겨있음
						Loan(this.visitor.GetMoney()); //Loan 메소드 통해 문구 출력
						this.working=false;       //작업이 끝났으므로 false로 변경
					}
				}
				
				else {
					Deposit(this.visitor.GetMoney()); //Deposit 메소드 동작
					this.working=false;               //작업이 끝났으므로 false
					this.workType=false;              //false가 기본값이므로 false로 변경
				}
			}
		}
	}
	
	public synchronized void DealCustomer()
	{
		// 해당 함수 수정 가능
		// 대출금 확인의 경우에는 대출을 시작할 때 금액 확인
		if (Managing.vip.CheckWaiting() == true){ //vip가 기다리고 있을 때
			this.visitor=Managing.vip.DealVisitor(); //DealVisitor 메소드의 반환값을 visitor(Teller가 담당하는 고객)에 저장
			this.working=true; //일하는 중으로 변경
		}
		
		else if((Managing.common.CheckWaiting() == true)&&(Managing.vip.CheckWaiting() == false)){ //vip가 안기다리면서 common이 기다리고 있을 때
			this.visitor=Managing.common.DealVisitor(); //담당 중인 손님에 Managing.common.DealVisitor() 대입
			this.working=true; //일하는 중으로 변경
		}
		
		else //아무도 기다리고 있지 않을 때
		{
			this.working = false; //일하지 않으므로 false
			return;
		}
		
		if(this.visitor.CheckType() == false) { //대출을 원할 때
			if(Managing.CheckLoan(this.visitor.GetMoney()) == true) { //대출을 성공했을 경우
				Managing.money-=this.visitor.GetMoney(); //대출의 경우 요청된 순간에 돈을 빼므로 Managing.money에서 담당받는 손님의 money를 뺌
				this.progressTime=5;                     //5초가 걸려야하므로 업무에 걸리는 시간을 5로 지정
				Case=true; //새로 선언한 Case 변수에 true를 저장
			}
			else { //대출을 원했으나 대출이 실패한 경우
				System.out.println(this.name+" 점원은 돈이 부족해 "+this.visitor.GetName()+"의 " + this.visitor.GetMoney() + "원을 대출 해주지 못했습니다."); //대출 실패시 대출 불가 문구 출력
				this.working=false;       //작업이 끝났으므로 false로 변경
				Case=false; //새로 선언한 Case 변수에 false를 저장
			}
		}
		else { //입금을 원하는 경우
			this.progressTime=3; //입금 업무에 걸리는 시간은 3초이므로 3을 저장
		}
	}
	
	private synchronized void Deposit(int money) { // 입금의 경우에는 입금이 처리될 경우 진행
		System.out.println(name + " 점원은 " + visitor.GetName() +"고객님의 " + money + "원을 입금 처리 했습니다."); //입금 처리 했다는 문구 출력
		Managing.money += money; //입금되었으므로 은행의 머니에 입금된 만큼 추가
	}
	
	private synchronized void Loan(int money){ //대출이 요청될 경우 진행
		System.out.println(name + " 점원은 " + visitor.GetName() +"고객님의 " + money + "원을 대출 처리 했습니다."); //대출 처리 했다는 문구 출력

	}
	
}