package assignment5;

public class init {
	public static void Init_Program() {
		management.students.add(new student("������", "dongs0125", "1234"));
		management.students.add(new student("������", "sas1200", "1234")); //�л� ����
		
		management.professors.add(new professor("�̹̿�", "mylee", "1234"));
		management.professors.add(new professor("������", "jungwony", "1234"));
		management.professors.add(new professor("�̼���", "saedol", "1234")); //������ ����
		
		management.professors.get(0).register_lecture("����", "��B��B");
		management.professors.get(0).register_lecture("����", "��B��B");
		
		management.professors.get(1).register_lecture("�ڷᱸ��", "ȭA��A");
		management.professors.get(1).register_lecture("�ڷᱸ��", "ȭC��C");
		
		management.professors.get(2).register_lecture("C++", "��B��B");
		management.professors.get(2).register_lecture("Python", "ȭC��C"); //�������� ���� ��� ����
		
		
	} 
}
