package assignment5;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Program_info extends JFrame{
	 Program_info() {
		 setTitle("Program Info"); //�˾�â�� �̸��� Program Info�� ����
		 
	     JPanel WindowContainer = new JPanel(); //panel ����
	     setContentPane(WindowContainer);       //panel�� content�� �ֱ����� �۾�
	     
	     JLabel j1 = new JLabel("<html><body style='text-align:center;'>������ : �ȹα�<br />�й� : 201620837<br />������Ʈ ��¥ : 2021-06-15</body></html>"); //���̺� ����
	     
	     WindowContainer.add(j1); //������ ���̺��� panel�� �߰�
	     
	     setSize(300,100);        //������� 300,100
	     setResizable(false);     //������ ���� �Ұ�
	     setVisible(true);        //â ���̰� ����
	    }
}