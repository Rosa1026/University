package assignment5;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StudentScreen extends JFrame implements ActionListener {
	private St_RegisterScreen rsc; //St_RegisterScreen ������ rsc ����
	private JTabbedPane jtp;       //JTabbedPane jtp -> panel
	
	
	public StudentScreen() {
		
		jtp = new JTabbedPane();   //Panel ����
		rsc = new St_RegisterScreen(); //RegisterScreen ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //ȭ�鿡 close ��ư (x)
		JMenuBar jmb = new JMenuBar();	//�޴��� ����
		
		JMenu menu1 = new JMenu("���"); //�޴�1 - ��� - �α׾ƿ�, ����
		JMenu menu2 = new JMenu("����"); //�޴�2 - ���� - ���α׷� ����
		
		jmb.add(menu1);
		jmb.add(menu2);  //menu1,2�� �޴��ٿ� �߰�
		
		
		JMenuItem jmi1 = new JMenuItem("�α׾ƿ�");
		JMenuItem jmi2 = new JMenuItem("����"); //�޴�1�� �� ������ ����
		
		menu1.add(jmi1);
		menu1.add(jmi2); //�޴�1�� ������ 2�� �߰�
		
		JMenuItem jmi3 = new JMenuItem("���α׷� ����"); //�޴�2�� �� ������ ����
		
		menu2.add(jmi3); //�޴�2�� ������ �߰�
		
		jmi1.addActionListener(this);
		jmi2.addActionListener(this);
		jmi3.addActionListener(this); //��� 3�� ��� Ŭ���̶�� �̺�Ʈ�� �����ؾ��ϹǷ� listener ����
		
		setJMenuBar(jmb); //�޴��� set
		
		jtp.addTab("������û", rsc); //panel�� ������û�̶�� �̸��� rsc �߰�
		getContentPane().add(jtp);
				
		setTitle("Student Panel");//�̸��� Student Panel
		setSize(400,500);
		setVisible(true);

	}
	public void actionPerformed(ActionEvent ae) //�̺�Ʈ�� ����
	{
		String s = ae.getActionCommand();
		if (s == "�α׾ƿ�") { //�̺�Ʈ�� �߻��� ���� ����� ���ڿ��� �α׾ƿ��̶��
			LoginScreen login = new LoginScreen(); //���ο� �α��� â ����
			login.setVisible(true); //���ο� �α��� â ���̰� ����
			setVisible(false);      //���� �ִ� â�� �Ⱥ��̰� ����
		} 
		else if (s == "����") {      //������
			new New_Window2(2);    //New_Window2�� �Ű����� 2�� �ְ� �޼ҵ� ����
		}
		else if (s == "���α׷� ����") { //���α׷� �������
			Program_info info = new Program_info(); //Program_info() ����
			info.setVisible(true);                  //���α׷� ���� ���̴� â ���̰� ����
		}
	}
	
}

class St_RegisterScreen extends JPanel implements ActionListener{ //rsc
	
	int num_of_lectures = 6;
	ButtonGroup gb = new ButtonGroup(); //��ư �׷� ����
	JCheckBox[] jc = new JCheckBox[num_of_lectures + 1]; //üũ�ڽ� ����
	private student st; //student ���� ��ü ����
	
	St_RegisterScreen() {
		st = (student) management.now_member; //management�� ����Ǿ��ִ� ���� ����� student������ �ٲپ� st�� ����
		num_of_lectures = management.lectures.size(); //������ ����� num of lectures�� ����
		GridLayout b1 = new GridLayout(num_of_lectures + 2,4,10,10); //�� : ���� ��� +2, �� : 4, �ٰ��� : 10,10 ���� �ϴ� grid layout ����
		setLayout(b1); //�ռ� ������ ���̾ƿ����� rsc ����
		
		
		String[] content = {"����","�����","�ð�","������"}; //4���� �� ��� �̸�
		
		
		JButton btn = new JButton("������û"); //������û �����ִ� ��ư ����
		
		for(int i = 0; i <4; i++)
			add(new JLabel(content[i])); //��� �̸��� ���ʴ�� ���̺� �����Ͽ� �ǳڿ� ���� ù��° ��
		
		for(int i = 0; i < num_of_lectures ; i++)
		{
			jc[i] = new JCheckBox(""); //i�� üũ�ڽ� ���� -> ������� üũ�ڽ� (����ĭ)
			add(jc[i]); //������ üũ�ڽ� ����
			add(new JLabel(management.lectures.get(i).get_name())); //���� �̸�(�����)
			add(new JLabel(management.lectures.get(i).get_time())); //�ð�(�ð�)
			add(new JLabel(management.lectures.get(i).get_prof())); //������ ����(������)
			gb.add(jc[i]); //�ռ� ������ üũ�ڽ��� ��ư �׷����� ����
			jc[i].addActionListener(this); //üũ�ڽ� Ŭ���ϸ鼭 �̺�Ʈ�� �߻��ϹǷ� listner ����
						
		}
		add(new JLabel(""));
		add(new JLabel(""));
		add(new JLabel("")); //num of lectures ����� �� ä�����Ƿ� ������ ���� �� ���� ���� ���� 3��
		add(btn);            //��ư �߰� -> �̷� ���� �ռ� grid layout�� ���� num of lectures+2�� ����
		
		btn.addActionListener(this); //��ư ���� �����鼭 �̺�Ʈ�� �߻��ϹǷ� listner ����
		
		// TODO Auto-generated constructor stub
	}
	public void actionPerformed(ActionEvent ae) {
		String s = ae.getActionCommand();
		lecture temp;
		if(s == "������û") //�̺�Ʈ�� �߻��� ���� ����� ���ڿ��� ������û�̶��
		{
			st = (student) management.now_member; //st�� now_member ����
			for(int i =0; i < num_of_lectures; i++) //���� ����ŭ �ݺ�
			{
				if(jc[i].isSelected())              //���õ� üũ�ڽ� Ȯ��
				{
					temp = management.lectures.get(i); //���õ� üũ�ڽ� ��ȣ i�� �´� ���� temp�� ����
					
					if(st.register_lecture(temp)) //temp�� student.register_lecture �޼ҵ��� �Ű������� ����� ȣ���ؼ� 1�̶�� -> ���� ��û��
					{
						new New_Window2(0);       //new_window2 �޼ҵ� �Ű�����0���� ȣ��
						
					}
					else                          //������û�� ���� �ʾҴٸ�
					{
						new New_Window2(1);       //new_window2 �޼ҵ� �Ű�����1���� ȣ��
					}
					
				}
			}
		}
		
	}

	
}

class New_Window2 extends JFrame implements ActionListener{ //new_window2 �޼ҵ�
	 New_Window2(int flag) {
	        setTitle("Alert"); //������ �˾� â�� �̸��� Alert - �˸�
	        
	        JPanel NewWindowContainer = new JPanel(); //Panel ����
	        setContentPane(NewWindowContainer);
	        
	        FlowLayout fl = new FlowLayout(FlowLayout.CENTER, 5, 5); //flowlayout���� ����
	        
	        JButton btn1 = new JButton("����"); //���� ��ư
	        JButton btn2 = new JButton("���"); //��� ��ư -> �Ǽ��� ���Ḧ ������ �� �ٷ� ������� �ʰԲ� �߰�����
	        
	        if(flag == 0) //new_window2 �޼ҵ带 ȣ���ϴ� �Ű������� 0�̶��
	        {
	        	NewWindowContainer.add(new JLabel("�����Ͻ� ���ǰ� ���������� ��û�Ǿ����ϴ�.")); //��û ���� �޼���
		        
		        setSize(300,100);
		        setResizable(false);
		        setVisible(true);
	        }   
	        else if(flag == 1) //�Ű������� 1�̶��
	        {
	        	NewWindowContainer.add(new JLabel("�����Ͻ� ���Ǹ� ��û�� �� �����ϴ�."));
	        	NewWindowContainer.add(new JLabel("�ð� �� ���Ǹ� �ٽ� Ȯ���� �ּ���.")); //��û ���� �޼���
		        
		        setSize(300,100);
		        setResizable(false);
		        setVisible(true);
	        }
	        else if(flag == 2) //�Ű������� 2���
	        {
		        NewWindowContainer.setLayout(fl); //flowlayout ����
		        
	        	NewWindowContainer.add(new JLabel("���α׷��� �����Ͻðڽ��ϱ�?")); //�ѹ� �� ����� ������ �Բ�
	        	NewWindowContainer.add(btn1);
	        	NewWindowContainer.add(btn2); //�ռ� ������ ��ư �߰�
		        
		        setSize(200,100);
		        setResizable(false);
		        setVisible(true);
	        }
	        
			btn1.addActionListener(this);
			btn2.addActionListener(this); //�� ��ư ��� �̺�Ʈ listner�� ����
	  }
	 public void actionPerformed(ActionEvent ae) {
			String s = ae.getActionCommand();
			if(s == "����") //������ ������
				System.exit(0); //�ý��� ����
			else if(s == "���") //������ ��Ҷ��
				setVisible(false); //Alert â ����
	 }
				
}
