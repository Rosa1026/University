package assignment5;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ProfessorScreen extends JFrame implements ActionListener{

	private Pf_RegisterScreen rsc; //St_RegisterScreen ������ rsc ����
	private JTabbedPane jtp;       //JTabbedPane jtp -> panel
	
	
	public ProfessorScreen() {
		
		jtp = new JTabbedPane();   //Panel ����
		rsc = new Pf_RegisterScreen(); //RegisterScreen ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //ȭ�鿡 close ��ư (x)
		
		JMenuBar jmb = new JMenuBar();		//�޴��� ����
		
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
		
		jtp.addTab("���ǵ��", rsc); //panel�� ���ǵ���̶�� �̸��� rsc �߰�
		getContentPane().add(jtp);
		
		
		setTitle("Professor Panel");//�̸��� Professor Panel
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


class Pf_RegisterScreen extends JPanel implements ActionListener{
	
	professor pf;
	JLabel name = new JLabel("���� �̸� : ");
	JLabel time = new JLabel("���� �ð� : ");
	JLabel info = new JLabel("                             "); //���� ��� ���̺� �߰�
	JTextField lec_name = new JTextField(10); //���� �̸� �Է��ϱ� ���� �ؽ�Ʈ �ʵ�
	JTextField lec_time = new JTextField(10); //���� �ð� �Է��ϱ� ���� �ؽ�Ʈ �ʵ�
	
	Pf_RegisterScreen() {
		pf = (professor) management.now_member; //management�� ����Ǿ��ִ� ���� ����� professor������ �ٲپ� pf�� ����
		// TODO Auto-generated constructor stub
		JButton register = new JButton("���ǵ��"); //���ǵ���̶�� �̸��� ��ư ���� 
		
		add(name);
		add(lec_name);
		add(time);
		add(lec_time);
		add(register);
		add(info); //�ռ� ������ ���̺�� ��ư �߰�
		register.addActionListener(this); //��ư�� Ŭ�� �� �̺�Ʈ �߻��ϹǷ� listner ����
	}
	
	
	public void actionPerformed(ActionEvent ae) {
		String s = ae.getActionCommand();
		lecture temp; //lecture �������� temp ��ü ����
		if(s == "���ǵ��") //�̺�Ʈ�� �߻��� ���� ����� ���ڿ��� ���ǵ���̶��
		{
			pf= (professor) management.now_member;
			temp = new lecture(lec_name.getText(), pf.get_name(), lec_time.getText()); //temp�� ���� �̸��� ������ ����, ���� �ð��� �޼ҵ� �̿��� ������ ��ü ����
			if(pf.register_lecture(temp))  //temp�� �Ű������� �޼ҵ带 ȣ���� 1�� ��ȯ������
				info.setText("���������� ���ǰ� ��ϵǾ����ϴ�."); //���ǵ�� ���� ���� �˸�
			else
				info.setText("�ð��� ��ġ�� ������ �ֽ��ϴ�. �ٽ� Ȯ���� �ּ���."); //���� �� �ٽ� �Է��ϰԲ� ����
				
				
		}
	}

	
}
