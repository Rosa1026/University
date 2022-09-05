package assignment4;

public class Waiting implements Runnable{
	
	private Visitor[] visitors;
	private int count; // 총 인원
	public int remainingVisitors = 0; // 남아있는 인원 
	private int time = 0;
	private String name;
	int i=0;
	int j=0;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		// common과 vip일 경우 다르게 진행
		if(this.name=="VIP") { //들어온 인원의 이름이 VIP로 설정되어있다면
			AddVisitor(Managing.vipVisitors[time]); //vipVisitors[]에 저장된 값을 visitors[]에 저장
		}
		else if(this.name=="Common") { //들어온 사람의 이름이 Common으로 설정되어있다면
			AddVisitor(Managing.commonVisitors[time]); //commonVisitors[]에 저장된 값을 visitors[]에 저장
		}
		time+=1; //Waiting의 시간 값 1 증가
	}
	
	public void AddVisitor(Visitor visitor) {   //대기열에 매개변수로 들어온 visitor를 저장하는 메소드
		 this.visitors[this.count]=visitor;     //매개변수 visitor를 visitors[count]에 저장. count는 0부터 1씩 증가
		 if(visitor!=null) {  //visitors에 저장된 값이 null이 아닐때까지 -> 즉, null을 만나면 그 순간부터 비어있는 것이므로
            this.count+=1;                           //count 1씩 증가
			this.remainingVisitors+=1;          //remainingVisitors 1씩 증가 -> 대기열에 있는 사람
		 }
		 else {                                 //visitor가 null을 만났을 때부터
			 System.out.println("No new "+name+" came in."); //들어온 고객이 없으므로 문구 출력
		 }
	}
	
	public Visitor DealVisitor() {             //손님을 점원에게 배정하기 위한 메소드
		if(CheckWaiting()==true) {             //대기열에 사람이 있으면 true를 반환하므로 true라면
			if(this.name=="VIP") {             //만약 이름이 VIP라면
				Managing.vip.visitors=this.visitors;   //Addvisitor에서 매개변수를 저장해놓은 visitors를 managing.vip.visitors에 저장
				j+=1;   //리턴을 내기위한 새로 선언한 integer 변수
				this.remainingVisitors-=1;     //대기열의 사람을 한 명 점원에게 배정하였으므로 남은 인원 수 1 감소
				return Managing.vip.visitors[j-1];  //DealVisitor 메소드의 리턴값으로 Managing.vip.visitors[j-1]을 리턴
			}
			
			else if(this.name=="Common") {     //이름이 common이라면
				Managing.common.visitors=this.visitors; //Addvisitor에서 매개변수를 저장해놓은 visitors를 managing.common.visitors에 저장
				i+=1;                          //리턴을 내기위한 새로 선언한 integer 변수
				this.remainingVisitors-=1;     //대기열의 사람을 한 명 점원에게 배정하였으므로 남은 인원 수 1 감소
				return Managing.common.visitors[i-1];  //DealVisitor 메소드의 리턴값으로 Managing.common.visitors[i-1]을 리턴
			}

		}
		return null; //대기중인 손님이 없는 경우
	}
			
	public boolean CheckWaiting() { //기다리고 있는 사람이 있는지 체크하는 메소드
		if(remainingVisitors == 0)  //남아있는 Visitors가 없다면
			return false;           //기다리지 않으므로 false
		else
			return true;            //기다리고 있으므로 true
	}
	
	Waiting(String name){
		this.visitors = new Visitor[10];
		this.remainingVisitors = 0;
		this.count = 0;
		this.name = name;
	}                              //Waiting 객체 생성 위한 메소드
	
	public void PrintInfo()        //현재 남아있는 인원이 몇인지를 나타내는 메소드
	{
		System.out.println(name + " Waiting에 현재 남아있는 인원은 " + remainingVisitors + "명 입니다.");
	}

}