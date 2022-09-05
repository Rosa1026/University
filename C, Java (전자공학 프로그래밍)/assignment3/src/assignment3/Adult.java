package assignment3;

public class Adult extends Visitor{
	int fee;
	boolean status; //변수 선언
	
	public Adult(int age, int fee) {
		this.age = age;                //age로 입력되는 값 age에 저장
		this.fee = fee;                 //fee로 입력되는 값 fee에 저장
		this.status = false;            //입장 관련 변수 false로 초기화
	}
	
	public boolean pay() {
		if(this.fee - ADULT_FEE < 0) //입장료가 정해진 ADULT_FEE 값보다 낮다면 pay
			return false;         //에 false -> 입장료 못 냄
		else
			return true;         //아니라면 true
	}
	
	public boolean isMember() {
		return false;                //회원이 아니므로 false
	}
}
