package assignment3;

public class Child extends Visitor{
	boolean status;               //����ᰡ 0�̹Ƿ� �����ߴ����� ������ ������ ����
	
	public Child(int age) {       //�ٸ� �͵�� �ٸ��� ���̸��� �̿��� ȣ��
		this.age = age;           //�Էµ� age�� ����
		this.status = false;      //���� ���� ���� false�� �ʱ�ȭ
	}
	
	public boolean pay() {
		return true;             //����ᰡ 0�̹Ƿ� pay�� �׻� true ��ȯ
	}
	
	public boolean isMember() {  //ȸ������ �ҷ����� �����Ƿ� ȸ���� false
		return false;
	}
}
