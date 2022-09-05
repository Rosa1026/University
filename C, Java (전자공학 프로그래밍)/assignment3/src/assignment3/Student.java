package assignment3;

public class Student extends Visitor{
	int fee;
	boolean status;
	
	public Student(int age, int fee) {
		this.age = age;
		this.fee = fee;
		this.status = false;
	}
	
	public boolean pay() {
		if(this.fee - STUDENT_FEE < 0) //정해진 입장료보다 갖고 있는 돈이 낮으므로
			return false;              //pay에 false -> 입장 못 함
		else
			return true;               //아니라면 true
	}
	
	public boolean isMember() {
		return false;                  //회원이 아니므로 false
	}
}
