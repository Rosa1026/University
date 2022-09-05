package assignment4;

public class Waiting implements Runnable{
	
	private Visitor[] visitors;
	private int count; // �� �ο�
	public int remainingVisitors = 0; // �����ִ� �ο� 
	private int time = 0;
	private String name;
	int i=0;
	int j=0;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		// common�� vip�� ��� �ٸ��� ����
		if(this.name=="VIP") { //���� �ο��� �̸��� VIP�� �����Ǿ��ִٸ�
			AddVisitor(Managing.vipVisitors[time]); //vipVisitors[]�� ����� ���� visitors[]�� ����
		}
		else if(this.name=="Common") { //���� ����� �̸��� Common���� �����Ǿ��ִٸ�
			AddVisitor(Managing.commonVisitors[time]); //commonVisitors[]�� ����� ���� visitors[]�� ����
		}
		time+=1; //Waiting�� �ð� �� 1 ����
	}
	
	public void AddVisitor(Visitor visitor) {   //��⿭�� �Ű������� ���� visitor�� �����ϴ� �޼ҵ�
		 this.visitors[this.count]=visitor;     //�Ű����� visitor�� visitors[count]�� ����. count�� 0���� 1�� ����
		 if(visitor!=null) {  //visitors�� ����� ���� null�� �ƴҶ����� -> ��, null�� ������ �� �������� ����ִ� ���̹Ƿ�
            this.count+=1;                           //count 1�� ����
			this.remainingVisitors+=1;          //remainingVisitors 1�� ���� -> ��⿭�� �ִ� ���
		 }
		 else {                                 //visitor�� null�� ������ ������
			 System.out.println("No new "+name+" came in."); //���� ���� �����Ƿ� ���� ���
		 }
	}
	
	public Visitor DealVisitor() {             //�մ��� �������� �����ϱ� ���� �޼ҵ�
		if(CheckWaiting()==true) {             //��⿭�� ����� ������ true�� ��ȯ�ϹǷ� true���
			if(this.name=="VIP") {             //���� �̸��� VIP���
				Managing.vip.visitors=this.visitors;   //Addvisitor���� �Ű������� �����س��� visitors�� managing.vip.visitors�� ����
				j+=1;   //������ �������� ���� ������ integer ����
				this.remainingVisitors-=1;     //��⿭�� ����� �� �� �������� �����Ͽ����Ƿ� ���� �ο� �� 1 ����
				return Managing.vip.visitors[j-1];  //DealVisitor �޼ҵ��� ���ϰ����� Managing.vip.visitors[j-1]�� ����
			}
			
			else if(this.name=="Common") {     //�̸��� common�̶��
				Managing.common.visitors=this.visitors; //Addvisitor���� �Ű������� �����س��� visitors�� managing.common.visitors�� ����
				i+=1;                          //������ �������� ���� ������ integer ����
				this.remainingVisitors-=1;     //��⿭�� ����� �� �� �������� �����Ͽ����Ƿ� ���� �ο� �� 1 ����
				return Managing.common.visitors[i-1];  //DealVisitor �޼ҵ��� ���ϰ����� Managing.common.visitors[i-1]�� ����
			}

		}
		return null; //������� �մ��� ���� ���
	}
			
	public boolean CheckWaiting() { //��ٸ��� �ִ� ����� �ִ��� üũ�ϴ� �޼ҵ�
		if(remainingVisitors == 0)  //�����ִ� Visitors�� ���ٸ�
			return false;           //��ٸ��� �����Ƿ� false
		else
			return true;            //��ٸ��� �����Ƿ� true
	}
	
	Waiting(String name){
		this.visitors = new Visitor[10];
		this.remainingVisitors = 0;
		this.count = 0;
		this.name = name;
	}                              //Waiting ��ü ���� ���� �޼ҵ�
	
	public void PrintInfo()        //���� �����ִ� �ο��� �������� ��Ÿ���� �޼ҵ�
	{
		System.out.println(name + " Waiting�� ���� �����ִ� �ο��� " + remainingVisitors + "�� �Դϴ�.");
	}

}