package assignment4;

public class Teller implements Runnable{
	private String name;      // �̸�
	private boolean working;  // ���� ����
	private boolean workType; // true �� �Ա�, false�� ����
	private int progressTime; // �������� �ɸ��� �ð�
	private Visitor visitor;  // ������� �մ�
	protected boolean Case;   // ���� ���� ���и� ��Ÿ���� ���� ������ Case
	
	public Teller(String name)
	{
		this.name = name;        //Teller�� �̸�
		this.working = false;    //Teller�� ���� ����
		this.workType = false;   //Teller�� ����
		this.progressTime = 0;   //Teller�� ���� �������� �ɸ��� �ð�
		
	}
	
	public void run() {		
		// TODO Auto-generated method stub
		if (this.working == false) //���ϴ� ���°� �ƴ϶��
		{
			DealCustomer();        //�մ� ����
		}
		
		else                       //���� ���ϴ� ���¶��
		{
			this.workType=this.visitor.CheckType(); //�湮���� ������ ������ ������ ����
			if(this.progressTime>0) {
				try{
					Thread.sleep(100);
				} catch(InterruptedException e) {
					e.printStackTrace(); 
				}                                 //try-catch ������ ����� Thread.sleep()�� ��. 1000ms ���� ��������.
				finally {
					this.progressTime-=1;         //try-catch ������ ����Ǹ� �����ð��� 1����. ��, ���� ���� �ð��� ���ҽ�Ű�� �۾�
				}
			}
			
			if(this.progressTime==0) {       //�־��� �����ð��� ������
				if(this.workType == false) {      //workType�� false��� ���� ���� ���
					if(Case) {                    //Case���� ���� ���� ���ΰ� �������
						Loan(this.visitor.GetMoney()); //Loan �޼ҵ� ���� ���� ���
						this.working=false;       //�۾��� �������Ƿ� false�� ����
					}
				}
				
				else {
					Deposit(this.visitor.GetMoney()); //Deposit �޼ҵ� ����
					this.working=false;               //�۾��� �������Ƿ� false
					this.workType=false;              //false�� �⺻���̹Ƿ� false�� ����
				}
			}
		}
	}
	
	public synchronized void DealCustomer()
	{
		// �ش� �Լ� ���� ����
		// ����� Ȯ���� ��쿡�� ������ ������ �� �ݾ� Ȯ��
		if (Managing.vip.CheckWaiting() == true){ //vip�� ��ٸ��� ���� ��
			this.visitor=Managing.vip.DealVisitor(); //DealVisitor �޼ҵ��� ��ȯ���� visitor(Teller�� ����ϴ� ��)�� ����
			this.working=true; //���ϴ� ������ ����
		}
		
		else if((Managing.common.CheckWaiting() == true)&&(Managing.vip.CheckWaiting() == false)){ //vip�� �ȱ�ٸ��鼭 common�� ��ٸ��� ���� ��
			this.visitor=Managing.common.DealVisitor(); //��� ���� �մԿ� Managing.common.DealVisitor() ����
			this.working=true; //���ϴ� ������ ����
		}
		
		else //�ƹ��� ��ٸ��� ���� ���� ��
		{
			this.working = false; //������ �����Ƿ� false
			return;
		}
		
		if(this.visitor.CheckType() == false) { //������ ���� ��
			if(Managing.CheckLoan(this.visitor.GetMoney()) == true) { //������ �������� ���
				Managing.money-=this.visitor.GetMoney(); //������ ��� ��û�� ������ ���� ���Ƿ� Managing.money���� ���޴� �մ��� money�� ��
				this.progressTime=5;                     //5�ʰ� �ɷ����ϹǷ� ������ �ɸ��� �ð��� 5�� ����
				Case=true; //���� ������ Case ������ true�� ����
			}
			else { //������ �������� ������ ������ ���
				System.out.println(this.name+" ������ ���� ������ "+this.visitor.GetName()+"�� " + this.visitor.GetMoney() + "���� ���� ������ ���߽��ϴ�."); //���� ���н� ���� �Ұ� ���� ���
				this.working=false;       //�۾��� �������Ƿ� false�� ����
				Case=false; //���� ������ Case ������ false�� ����
			}
		}
		else { //�Ա��� ���ϴ� ���
			this.progressTime=3; //�Ա� ������ �ɸ��� �ð��� 3���̹Ƿ� 3�� ����
		}
	}
	
	private synchronized void Deposit(int money) { // �Ա��� ��쿡�� �Ա��� ó���� ��� ����
		System.out.println(name + " ������ " + visitor.GetName() +"������ " + money + "���� �Ա� ó�� �߽��ϴ�."); //�Ա� ó�� �ߴٴ� ���� ���
		Managing.money += money; //�ԱݵǾ����Ƿ� ������ �ӴϿ� �Աݵ� ��ŭ �߰�
	}
	
	private synchronized void Loan(int money){ //������ ��û�� ��� ����
		System.out.println(name + " ������ " + visitor.GetName() +"������ " + money + "���� ���� ó�� �߽��ϴ�."); //���� ó�� �ߴٴ� ���� ���

	}
	
}