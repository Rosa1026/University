package assignment3;

public class Member extends Visitor{
	boolean status;           //ȸ���̶�� ����ᰡ 0�̹Ƿ� ���� ���� ���� X
	
	public Member(int age) {
		this.age = age;    
		this.status = false; 
	}
	
	public boolean pay() {
		return true;         //ȸ���� ����Ḧ ���������Ƿ� pay �׻� true ��ȯ
	}
	
	public boolean isMember() {
		return true;         //ȸ���̹Ƿ� true ��ȯ
	}
}
