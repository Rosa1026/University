package assignment5;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ProfessorScreen extends JFrame implements ActionListener{

	private Pf_RegisterScreen rsc; //St_RegisterScreen 형식의 rsc 선언
	private JTabbedPane jtp;       //JTabbedPane jtp -> panel
	
	
	public ProfessorScreen() {
		
		jtp = new JTabbedPane();   //Panel 선언
		rsc = new Pf_RegisterScreen(); //RegisterScreen 선언
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //화면에 close 버튼 (x)
		
		JMenuBar jmb = new JMenuBar();		//메뉴바 생성
		
		JMenu menu1 = new JMenu("기능"); //메뉴1 - 기능 - 로그아웃, 종료
		JMenu menu2 = new JMenu("정보"); //메뉴2 - 정보 - 프로그램 정보
		
		jmb.add(menu1);
		jmb.add(menu2);  //menu1,2를 메뉴바에 추가
		
		
		JMenuItem jmi1 = new JMenuItem("로그아웃");
		JMenuItem jmi2 = new JMenuItem("종료"); //메뉴1에 들어갈 아이템 선언
		
		menu1.add(jmi1);
		menu1.add(jmi2); //메뉴1에 아이템 2개 추가
		
		JMenuItem jmi3 = new JMenuItem("프로그램 정보"); //메뉴2에 들어갈 아이템 선언
		
		menu2.add(jmi3); //메뉴2에 아이템 추가
		
		jmi1.addActionListener(this);
		jmi2.addActionListener(this);
		jmi3.addActionListener(this); //목록 3개 모두 클릭이라는 이벤트에 반응해야하므로 listener 선언
		
		setJMenuBar(jmb); //메뉴바 set
		
		jtp.addTab("강의등록", rsc); //panel에 강의등록이라는 이름의 rsc 추가
		getContentPane().add(jtp);
		
		
		setTitle("Professor Panel");//이름은 Professor Panel
		setSize(400,500);
		setVisible(true);
		
	}
	public void actionPerformed(ActionEvent ae) //이벤트에 반응
	{
		String s = ae.getActionCommand();
		if (s == "로그아웃") { //이벤트가 발생한 곳에 저장된 문자열이 로그아웃이라면
			LoginScreen login = new LoginScreen(); //새로운 로그인 창 선언
			login.setVisible(true); //새로운 로그인 창 보이게 설정
			setVisible(false);      //본래 있던 창은 안보이게 설정
		} 
		else if (s == "종료") {      //종료라면
			new New_Window2(2);    //New_Window2에 매개변수 2를 넣고 메소드 실행
		}
		else if (s == "프로그램 정보") { //프로그램 정보라면
			Program_info info = new Program_info(); //Program_info() 선언
			info.setVisible(true);                  //프로그램 정보 보이는 창 보이게 설정
		}
	}
	
}


class Pf_RegisterScreen extends JPanel implements ActionListener{
	
	professor pf;
	JLabel name = new JLabel("강의 이름 : ");
	JLabel time = new JLabel("강의 시간 : ");
	JLabel info = new JLabel("                             "); //강의 등록 레이블 추가
	JTextField lec_name = new JTextField(10); //강의 이름 입력하기 위한 텍스트 필드
	JTextField lec_time = new JTextField(10); //강의 시간 입력하기 위한 텍스트 필드
	
	Pf_RegisterScreen() {
		pf = (professor) management.now_member; //management에 저장되어있는 현재 멤버를 professor형으로 바꾸어 pf에 저장
		// TODO Auto-generated constructor stub
		JButton register = new JButton("강의등록"); //강의등록이라는 이름의 버튼 생성 
		
		add(name);
		add(lec_name);
		add(time);
		add(lec_time);
		add(register);
		add(info); //앞서 선언한 레이블과 버튼 추가
		register.addActionListener(this); //버튼은 클릭 시 이벤트 발생하므로 listner 선언
	}
	
	
	public void actionPerformed(ActionEvent ae) {
		String s = ae.getActionCommand();
		lecture temp; //lecture 형식으로 temp 객체 선언
		if(s == "강의등록") //이벤트가 발생한 곳에 저장된 문자열이 강의등록이라면
		{
			pf= (professor) management.now_member;
			temp = new lecture(lec_name.getText(), pf.get_name(), lec_time.getText()); //temp에 강의 이름과 교수님 성함, 강의 시간을 메소드 이용해 가져와 객체 생성
			if(pf.register_lecture(temp))  //temp를 매개변수로 메소드를 호출해 1이 반환됐으면
				info.setText("정상적으로 강의가 등록되었습니다."); //강의등록 성공 문구 알림
			else
				info.setText("시간이 겹치는 과목이 있습니다. 다시 확인해 주세요."); //실패 시 다시 입력하게끔 설정
				
				
		}
	}

	
}
