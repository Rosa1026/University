package assignment3;

public class Main {
	public static void main(String[] args) {
		Museum m = new Museum(); //�ڹ��� ����
		Adult v1 = new Adult(25, 5000); //25�쿡 5õ���� ����Ḧ ���� � ����
		Member v2 = new Member(30);     //30�쿡 ȸ�� ����
		Student v3 = new Student(14, 1000);  //14�쿡 1õ���� ����Ḧ ���� �л� ����
		Child v4 = new Child(6);             //6�� ��� ����
		Student v5 = new Student(18, 5000);  //18�쿡 5õ���� ����Ḧ ���� �л� ����
		Student v6 = new Student(15, 2000);  //15�쿡 2õ���� ����Ḧ ���� �л� ����
		Child v7 = new Child(8);             //8�� ��� ����
		
		m.enter(v1); //� ����
		m.enter(v2); //ȸ�� ����
		m.enter(v3); //�л� ����� ���� Ȯ��
		m.enter(v4); //��� ����
		m.enter(v5); //�л� ���� ���� Ȯ��
		
		m.exit(v1); //� ����
		m.exit(v3); //���� ���� ��� ���� Ȯ��
		m.enter(v6); //�л� ����
		m.enter(v7); //��� ���� ���� Ȯ��
	}

}