package assignment5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;

public class LoginScreen extends JFrame implements ActionListener{
	
	JTextField ID = new JTextField(20); //id �Է� ���� �ؽ�Ʈ�ʵ� ����
	JTextField PW = new JTextField(20); //pw �Է� ���� �ؽ�Ʈ�ʵ� ����
	JLabel icon = new JLabel();
	JLabel text1 = new JLabel();
	JLabel text2 = new JLabel();        //���̺� ����
	JButton button1 = new JButton("Login");
	JButton button2 = new JButton("New"); //��ư 2�� ����
	
	public LoginScreen() {
		Container c1 = getContentPane();  //�����̳� ����
		ImageIcon ajou = new ImageIcon("assignment5/ajou2.png"); //������ ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //ȭ�鿡 x �� ����

		GridLayout b1 = new GridLayout(7,1,10,10); //gridlayout ���
		c1.setLayout(b1);
	   

		icon.setIcon(ajou);
		icon.setText("���ִ��б� ������û ���α׷� �Դϴ�."); //icon�� text�� ������ ��
		
		text1.setText("ID�� �Է��� �ּ���");
		text2.setText("PW�� �Է��� �ּ���"); //�ռ� ������ ���̺� ���� ����
		
		button1.setText("Login");
		button2.setText("ȸ������"); //��ư 2���� ���� ����
		
		c1.add(icon);
		c1.add(text1);
		c1.add(ID);
		c1.add(text2);
		c1.add(PW);
		c1.add(button1);
		c1.add(button2); //�ռ� ������ ��ҵ� �����̳ʿ� �߰�
		
		button1.addActionListener(this);
		button2.addActionListener(this); //��ư ������ �̺�Ʈ �߻��ϹǷ� listner ����

		ID.addActionListener(this);
		PW.addActionListener(this); //id�� pw �Է� �� �̺�Ʈ �߻��ϹǷ� listner ����
		
		setTitle("������û ���α׷�"); //â�� �̸��� ������û ���α׷�
		setSize(500,400);
		setVisible(true);
		
	}
	public void actionPerformed(ActionEvent ae)
	{
		member Member;
		int flag;
		String id = ID.getText();
		String pw = PW.getText(); //�ؽ�Ʈ�ʵ忡 �Էµ� �� ����
		
		if(ae.getActionCommand() == "Login") { //Login ���ȴٸ�
						
			id = ID.getText();
			pw = PW.getText();
			
			Member = login.check_id_pw(id, pw); //�Էµ� id, pw�� ������ �´��� check�ؼ� member�� ����
			
			if(Member == null) //���� null�̶�� �߸� �Է�
			{
				flag = 0; //flag�� 0���� ����
				new New_Window(flag); //new_window �޼ҵ� �Ű����� 0���� ȣ��
				
			}
			else
			{
				dispose();
				management.now_member = Member; //management.now member�� ����
				if(management.now_member instanceof student) //now member�� student�� ���ϸ�
					new StudentScreen(); //studentscreen ���
				else if(management.now_member instanceof professor) //professor�� ���ϸ�
					new ProfessorScreen(); //professorscreen ���
				
			}
			
		}
		else if(ae.getActionCommand() == "ȸ������") { //ȸ�������� ���ȴٸ�
			new MakeNewScreen(); //makenewscreen ����
		}
	}
	
}

class New_Window extends JFrame{
	 New_Window(int flag) {
	        setTitle("Alert"); //Alert���� �̸� ����
	        
	        JPanel NewWindowContainer = new JPanel(); //Panel ����
	        setContentPane(NewWindowContainer);
        	
	        if(flag == 0) //�Ű����� 0�̶�� ���̵� Ʋ�� ���̹Ƿ�
	        {
	        	NewWindowContainer.add(new JLabel("�Է��� ������ ��ġ�ϴ� ���̵�� �������� �ʽ��ϴ�"));
	        	NewWindowContainer.add(new JLabel("�ٽ��ѹ� �Է��� �ּ���"));
	        }   
	        else if(flag == 1) //�Ű����� 1�̶�� ȸ�����Խ� �ߺ��� ���̵� �Է��� ���̹Ƿ�
	        {
	        	NewWindowContainer.add(new JLabel("�ߺ��� id�� �����մϴ�"));
	        	NewWindowContainer.add(new JLabel("�ٸ� ���̵� ����� �ּ���"));
	        }
	        else if(flag == 2) //�Ű����� 2��� ȸ������ ����
	        {
	        	NewWindowContainer.add(new JLabel("ȸ�������� �Ϸ� �Ǿ����ϴ�!"));
	        }
	        setSize(300,100);
	        setResizable(false);
	        setVisible(true);
	    }
}

class MakeNewScreen extends JFrame implements ActionListener{ //ȸ������ Ŭ����
	
	JLabel jl1 = new JLabel("����� �ź���? ");
	ButtonGroup bgp = new ButtonGroup(); //�׷��ư ����
	JRadioButton jrb1 = new JRadioButton("�л�", true);
	JRadioButton jrb2 = new JRadioButton("����"); //radio��ư 2�� ����
	
	JTextField NAME = new JTextField(5); //�̸� �Է� ���� �ؽ�Ʈ�ʵ�
	JTextField ID = new JTextField(20); //���̵� �Է�
	JTextField PW = new JTextField(20); //��й�ȣ �Է�
	JButton BTN = new JButton("ȸ������"); //ȸ������ ��ư ����
	
	public MakeNewScreen() {
		
		Container c1 = getContentPane();
		GridLayout g1 = new GridLayout(9,2,10,10);
		c1.setLayout(g1);
		
        setTitle("ȸ������"); //�̸��� ȸ������
        
        bgp.add(jrb1);
        bgp.add(jrb2); //��ư�׷쿡 �ռ� ������ radio ��ư 2�� �߰�
                
        c1.add(jl1);
        c1.add(new JLabel(" "));
		c1.add(jrb1);
		c1.add(jrb2);
		c1.add(new JLabel("�̸��� �Է��� �ּ���"));
		c1.add(NAME);
		c1.add(new JLabel("ID �� �Է��� �ּ���"));
		c1.add(ID);
		c1.add(new JLabel("PW �� �Է��� �ּ���"));
		c1.add(PW);
		c1.add(new JLabel(" "));
		c1.add(BTN); //������� �ź� ��ư -> �̸� -> ���̵� -> ��й�ȣ -> ȸ������ ��ư �߰�
		
		BTN.setText("ȸ������"); //��ư ���� ȸ���������� ����
		BTN.addActionListener(this); //��ư ������ �̺�Ʈ �߻��ϹǷ� listner ����
		
        setSize(300,400);
        setResizable(false);
        setVisible(true); 
				
	}
	public void actionPerformed(ActionEvent ae) {
		member Member;
		System.out.println(ae.getActionCommand());
		if(ae.getActionCommand() == "ȸ������") { //ȸ������ ���ȴٸ�
			if(!login.check_duplicate_id(ID.getText())) //���̵� ���ٸ�
			{
				new New_Window(1); //ȸ������ ����
				ID.setText(""); //���̵� �Է� â ���
			}
			else {
				new New_Window(2); //ȸ������ ����
				if(jrb1.isSelected() == true)
				{
					Member = new student(NAME.getText(), ID.getText(), PW.getText());
					management.students.add( (student) Member); //management�� ����
				}
				else 
				{
					Member = new professor(NAME.getText(), ID.getText(), PW.getText());
					management.professors.add( (professor) Member); //management�� ����
				}
				
				this.dispose();
				
			}
						
		}
	}
	
}


