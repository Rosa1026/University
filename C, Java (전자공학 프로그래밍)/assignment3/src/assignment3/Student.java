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
		if(this.fee - STUDENT_FEE < 0) //������ ����Ẹ�� ���� �ִ� ���� �����Ƿ�
			return false;              //pay�� false -> ���� �� ��
		else
			return true;               //�ƴ϶�� true
	}
	
	public boolean isMember() {
		return false;                  //ȸ���� �ƴϹǷ� false
	}
}
