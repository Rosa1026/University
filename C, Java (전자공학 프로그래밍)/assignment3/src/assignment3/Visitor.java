package assignment3;

interface Common {
	int getAge();
	boolean getStatus();
} //Visitor �Լ����� ����ϱ� ���� common interface ����

public abstract class Visitor implements Common{
	int age;        //���� ���� ����
	boolean status; //���� ���� ����
	final static int STUDENT_FEE = 2000; //�л��� ����� 2õ������ ����
	final static int ADULT_FEE = 5000; //��� ����� 5õ������ ����
	
	public abstract boolean pay(); //���� �´��� �� �´���
	public abstract boolean isMember(); //ȸ������ �ƴ���
	
	public int getAge() {
		return age; //age ��ȯ
	}
	public boolean getStatus() {
		return this.status; //status ��ȯ
	}

}
