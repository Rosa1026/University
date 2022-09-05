package assignment3;

public class Museum {
	static int currentNum;   //���� �ڹ��� ������ ��
	int todayNum;            //���� �ڹ��� ������ ��
	int sales;               //�����ϴ� ����� class�� ���� ����ᰪ
	int totalSales;          //���� ����
	String Class;            //class�� ����ϰ� �̸� �̿��� ������ ���� ����
	
	Museum(){
		currentNum = todayNum = sales = totalSales = 0; //�տ��� ������ ������ 0���� �ʱ�ȭ
	}
	
	int getTodayNum() {
		return todayNum;  //���� ������ �� ���� ��ȯ�ϴ� �Լ�
	}
	
	int getSales() {
		return sales;     //����� ����� �� ��ȯ
	}
	
	public boolean enter(Visitor v) { //enter �Լ�
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
		} //������ ����� class�� Class�� ����
		if(v instanceof Adult || v instanceof Student) { //��̰ų� �л��� ��
			if(v.pay() == false) { //���� ���� �ʾҴٸ� - ����ᰡ ���ڶ�ٸ�
				sales = (v instanceof Adult ? Visitor.ADULT_FEE : Visitor.STUDENT_FEE); //�����ϴ� ����Ḧ ���� ��̶�� ��� �����, �ƴ϶�� �л��� �����
				System.out.println("-> ���� �ź� : "+Class); //���� �ȳ� ����̹Ƿ� ���� �ź� �ϸ� ����� Class ���
				System.out.println("-> �ʿ��� ����� : "+sales); //�����Ϸ��� class�� �ʿ��� ������� sales ���
				sales=0; //����Ḧ ����������Ƿ� sales�� �ʱ�ȭ
				return false; //false�� ��ȯ�ϸ� �Լ� ����
			}
			sales = (v instanceof Adult ? Visitor.ADULT_FEE : Visitor.STUDENT_FEE); //���� �´ٸ� class�� ���� sales�� ����� ����
		}
		if(v instanceof Child) { //����� ��
			if(v.age>8) { //���̰� 8�캸�� ���ٸ� ���� ���ѿ� �ɸ��Ƿ�
				System.out.println("-> ���� �ź� : "+Class);
				System.out.println("-> ���� : "+v.age);
				sales=0; //sales�� 0���� �ʱ�ȭ
				return false; //���� �Ұ�
			}
		}
		else if(v instanceof Student) { //�л��� ��
			if(v.age>15||v.age<8) { //���̰� 8���� ���ų� 15�캸�� ���ٸ� ���� ���ѿ� �ɸ��Ƿ�
				System.out.println("-> ���� �ź� : "+Class);
				System.out.println("-> ���� : "+v.age);
				sales=0;
				return false; //���� �Ұ�
			}
		}
		else if(v instanceof Adult) { //��� ��
			if(v.age<16) { //���̰� 16�캸�� �۴ٸ� ���� ���ѿ� �ɸ��Ƿ�
				System.out.println("-> ���� �ź� : "+Class);
				System.out.println("-> ���� : "+v.age);
				sales=0;
				return false; //���� �Ұ�
			}
		}
		System.out.println("������ ���� : "+Class);
		v.status = true; //���������Ƿ� ���� ���¸� ��Ÿ���� status�� true
		todayNum++; //������ ������ ������ ��� �� 1�� ����
		currentNum++; //���� ������ �� 1�� ����
		totalSales+=sales; //��ü ���Ϳ� ������ ����� ����� ���� �� �ٽ� ��ü ���Ϳ� ����
		System.out.println("���� ������ �� : "+currentNum);
		System.out.println("���� ������ �� : "+todayNum);
		System.out.println("���� ���� : "+totalSales);
		sales=0; //sales�� �ٽ� 0���� �ʱ�ȭ
		return true; //true ��ȯ
	}
	
	public void exit(Visitor v) { //exit �Լ�
		if(v.status) { //���� ������ ����̶�� status�� true�̹Ƿ�
			System.out.println("������ ����");
			currentNum--; //���� ������ �� 1 ����
			System.out.println("���� ������ �� : "+currentNum);
		}
		else { //status�� false��� �̹� �����߰ų� �������� �� �� ����̹Ƿ�
			System.out.println("���� ������ ����"); //������ ���ٴ� �޼��� ���
			System.out.println("���� ������ �� : "+currentNum);
		}
	}
}
