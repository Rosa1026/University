package assignment3;

public class Adult extends Visitor{
	int fee;
	boolean status; //���� ����
	
	public Adult(int age, int fee) {
		this.age = age;                //age�� �ԷµǴ� �� age�� ����
		this.fee = fee;                 //fee�� �ԷµǴ� �� fee�� ����
		this.status = false;            //���� ���� ���� false�� �ʱ�ȭ
	}
	
	public boolean pay() {
		if(this.fee - ADULT_FEE < 0) //����ᰡ ������ ADULT_FEE ������ ���ٸ� pay
			return false;         //�� false -> ����� �� ��
		else
			return true;         //�ƴ϶�� true
	}
	
	public boolean isMember() {
		return false;                //ȸ���� �ƴϹǷ� false
	}
}
