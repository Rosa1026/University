package assignment3;

public class Museum {
	static int currentNum;   //현재 박물관 관람객 수
	int todayNum;            //일일 박물관 관람객 수
	int sales;               //입장하는 사람의 class에 따라 입장료값
	int totalSales;          //일일 수익
	String Class;            //class를 출력하고 이를 이용해 나누기 위해 선언
	
	Museum(){
		currentNum = todayNum = sales = totalSales = 0; //앞에서 선언한 변수들 0으로 초기화
	}
	
	int getTodayNum() {
		return todayNum;  //일일 관람객 수 값을 반환하는 함수
	}
	
	int getSales() {
		return sales;     //사람의 입장료 값 반환
	}
	
	public boolean enter(Visitor v) { //enter 함수
		if(v instanceof Adult) {
			Class="adult";
		}
		else if (v instanceof Student) {
			Class="Student";
		}
		else if (v instanceof Child) {
			Class="Child";
		}
		else if (v instanceof Member) {
			Class="Member";
		} //입장한 사람의 class를 Class에 저장
		if(v instanceof Adult || v instanceof Student) { //어른이거나 학생일 때
			if(v.pay() == false) { //돈을 내지 않았다면 - 입장료가 모자라다면
				sales = (v instanceof Adult ? Visitor.ADULT_FEE : Visitor.STUDENT_FEE); //내야하는 입장료를 저장 어른이라면 어른의 입장료, 아니라면 학생의 입장료
				System.out.println("-> 입장 거부 : "+Class); //돈을 안낸 사람이므로 입장 거부 하며 저장된 Class 출력
				System.out.println("-> 필요한 입장료 : "+sales); //입장하려는 class의 필요한 입장료인 sales 출력
				sales=0; //입장료를 출력해줬으므로 sales값 초기화
				return false; //false를 반환하며 함수 종료
			}
			sales = (v instanceof Adult ? Visitor.ADULT_FEE : Visitor.STUDENT_FEE); //돈을 냈다면 class에 따라 sales에 입장료 저장
		}
		if(v instanceof Child) { //어린이일 때
			if(v.age>8) { //나이가 8살보다 많다면 나이 제한에 걸리므로
				System.out.println("-> 입장 거부 : "+Class);
				System.out.println("-> 나이 : "+v.age);
				sales=0; //sales값 0으로 초기화
				return false; //입장 불가
			}
		}
		else if(v instanceof Student) { //학생일 때
			if(v.age>15||v.age<8) { //나이가 8보다 적거나 15살보다 많다면 나이 제한에 걸리므로
				System.out.println("-> 입장 거부 : "+Class);
				System.out.println("-> 나이 : "+v.age);
				sales=0;
				return false; //입장 불가
			}
		}
		else if(v instanceof Adult) { //어른일 때
			if(v.age<16) { //나이가 16살보다 작다면 나이 제한에 걸리므로
				System.out.println("-> 입장 거부 : "+Class);
				System.out.println("-> 나이 : "+v.age);
				sales=0;
				return false; //입장 불가
			}
		}
		System.out.println("관람객 입장 : "+Class);
		v.status = true; //입장했으므로 입장 상태를 나타내는 status값 true
		todayNum++; //입장할 때마다 입장한 사람 수 1씩 증가
		currentNum++; //현재 관람객 수 1씩 증가
		totalSales+=sales; //전체 수익에 입장한 사람의 입장료 더한 후 다시 전체 수익에 저장
		System.out.println("현재 관람객 수 : "+currentNum);
		System.out.println("일일 관람객 수 : "+todayNum);
		System.out.println("일일 수익 : "+totalSales);
		sales=0; //sales를 다시 0으로 초기화
		return true; //true 반환
	}
	
	public void exit(Visitor v) { //exit 함수
		if(v.status) { //현재 입장한 사람이라면 status가 true이므로
			System.out.println("관람객 퇴장");
			currentNum--; //현재 관람객 수 1 감소
			System.out.println("현재 관람객 수 : "+currentNum);
		}
		else { //status가 false라면 이미 퇴장했거나 입장하지 못 한 사람이므로
			System.out.println("현재 관람객 없음"); //관람객 없다는 메세지 출력
			System.out.println("현재 관람객 수 : "+currentNum);
		}
	}
}
