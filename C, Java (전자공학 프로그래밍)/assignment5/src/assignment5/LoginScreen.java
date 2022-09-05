package assignment5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;

public class LoginScreen extends JFrame implements ActionListener{
	
	JTextField ID = new JTextField(20); //id 입력 위한 텍스트필드 생성
	JTextField PW = new JTextField(20); //pw 입력 위한 텍스트필드 생성
	JLabel icon = new JLabel();
	JLabel text1 = new JLabel();
	JLabel text2 = new JLabel();        //레이블 생성
	JButton button1 = new JButton("Login");
	JButton button2 = new JButton("New"); //버튼 2개 생성
	
	public LoginScreen() {
		Container c1 = getContentPane();  //컨테이너 생성
		ImageIcon ajou = new ImageIcon("assignment5/ajou2.png"); //아이콘 생성
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //화면에 x 자 생성

		GridLayout b1 = new GridLayout(7,1,10,10); //gridlayout 사용
		c1.setLayout(b1);
	   

		icon.setIcon(ajou);
		icon.setText("아주대학교 수강신청 프로그램 입니다."); //icon에 text를 가져다 씀
		
		text1.setText("ID를 입력해 주세요");
		text2.setText("PW를 입력해 주세요"); //앞서 선언한 레이블에 문구 저장
		
		button1.setText("Login");
		button2.setText("회원가입"); //버튼 2개에 문구 저장
		
		c1.add(icon);
		c1.add(text1);
		c1.add(ID);
		c1.add(text2);
		c1.add(PW);
		c1.add(button1);
		c1.add(button2); //앞서 선언한 요소들 컨테이너에 추가
		
		button1.addActionListener(this);
		button2.addActionListener(this); //버튼 누르면 이벤트 발생하므로 listner 선언

		ID.addActionListener(this);
		PW.addActionListener(this); //id와 pw 입력 시 이벤트 발생하므로 listner 선언
		
		setTitle("수강신청 프로그램"); //창의 이름은 수강신청 프로그램
		setSize(500,400);
		setVisible(true);
		
	}
	public void actionPerformed(ActionEvent ae)
	{
		member Member;
		int flag;
		String id = ID.getText();
		String pw = PW.getText(); //텍스트필드에 입력된 값 저장
		
		if(ae.getActionCommand() == "Login") { //Login 눌렸다면
						
			id = ID.getText();
			pw = PW.getText();
			
			Member = login.check_id_pw(id, pw); //입력된 id, pw를 가지고 맞는지 check해서 member에 저장
			
			if(Member == null) //만약 null이라면 잘못 입력
			{
				flag = 0; //flag를 0으로 설정
				new New_Window(flag); //new_window 메소드 매개변수 0으로 호출
				
			}
			else
			{
				dispose();
				management.now_member = Member; //management.now member에 저장
				if(management.now_member instanceof student) //now member가 student에 속하면
					new StudentScreen(); //studentscreen 띄움
				else if(management.now_member instanceof professor) //professor에 속하면
					new ProfessorScreen(); //professorscreen 띄움
				
			}
			
		}
		else if(ae.getActionCommand() == "회원가입") { //회원가입이 눌렸다면
			new MakeNewScreen(); //makenewscreen 생성
		}
	}
	
}

class New_Window extends JFrame{
	 New_Window(int flag) {
	        setTitle("Alert"); //Alert으로 이름 지정
	        
	        JPanel NewWindowContainer = new JPanel(); //Panel 생성
	        setContentPane(NewWindowContainer);
        	
	        if(flag == 0) //매개변수 0이라면 아이디 틀린 것이므로
	        {
	        	NewWindowContainer.add(new JLabel("입력한 정보와 일치하는 아이디는 존재하지 않습니다"));
	        	NewWindowContainer.add(new JLabel("다시한번 입력해 주세요"));
	        }   
	        else if(flag == 1) //매개변수 1이라면 회원가입시 중복된 아이디를 입력한 것이므로
	        {
	        	NewWindowContainer.add(new JLabel("중복된 id가 존재합니다"));
	        	NewWindowContainer.add(new JLabel("다른 아이디를 사용해 주세요"));
	        }
	        else if(flag == 2) //매개변수 2라면 회원가입 성공
	        {
	        	NewWindowContainer.add(new JLabel("회원가입이 완료 되었습니다!"));
	        }
	        setSize(300,100);
	        setResizable(false);
	        setVisible(true);
	    }
}

class MakeNewScreen extends JFrame implements ActionListener{ //회원가입 클래스
	
	JLabel jl1 = new JLabel("당신의 신분은? ");
	ButtonGroup bgp = new ButtonGroup(); //그룹버튼 생성
	JRadioButton jrb1 = new JRadioButton("학생", true);
	JRadioButton jrb2 = new JRadioButton("교수"); //radio버튼 2개 생성
	
	JTextField NAME = new JTextField(5); //이름 입력 위한 텍스트필드
	JTextField ID = new JTextField(20); //아이디 입력
	JTextField PW = new JTextField(20); //비밀번호 입력
	JButton BTN = new JButton("회원가입"); //회원가입 버튼 생성
	
	public MakeNewScreen() {
		
		Container c1 = getContentPane();
		GridLayout g1 = new GridLayout(9,2,10,10);
		c1.setLayout(g1);
		
        setTitle("회원가입"); //이름은 회원가입
        
        bgp.add(jrb1);
        bgp.add(jrb2); //버튼그룹에 앞서 생성한 radio 버튼 2개 추가
                
        c1.add(jl1);
        c1.add(new JLabel(" "));
		c1.add(jrb1);
		c1.add(jrb2);
		c1.add(new JLabel("이름을 입력해 주세요"));
		c1.add(NAME);
		c1.add(new JLabel("ID 를 입력해 주세요"));
		c1.add(ID);
		c1.add(new JLabel("PW 를 입력해 주세요"));
		c1.add(PW);
		c1.add(new JLabel(" "));
		c1.add(BTN); //순서대로 신분 버튼 -> 이름 -> 아이디 -> 비밀번호 -> 회원가입 버튼 추가
		
		BTN.setText("회원가입"); //버튼 문구 회원가입으로 설정
		BTN.addActionListener(this); //버튼 눌리면 이벤트 발생하므로 listner 선언
		
        setSize(300,400);
        setResizable(false);
        setVisible(true); 
				
	}
	public void actionPerformed(ActionEvent ae) {
		member Member;
		System.out.println(ae.getActionCommand());
		if(ae.getActionCommand() == "회원가입") { //회원가입 눌렸다면
			if(!login.check_duplicate_id(ID.getText())) //아이디가 같다면
			{
				new New_Window(1); //회원가입 실패
				ID.setText(""); //아이디 입력 창 비움
			}
			else {
				new New_Window(2); //회원가입 성공
				if(jrb1.isSelected() == true)
				{
					Member = new student(NAME.getText(), ID.getText(), PW.getText());
					management.students.add( (student) Member); //management에 저장
				}
				else 
				{
					Member = new professor(NAME.getText(), ID.getText(), PW.getText());
					management.professors.add( (professor) Member); //management에 저장
				}
				
				this.dispose();
				
			}
						
		}
	}
	
}


