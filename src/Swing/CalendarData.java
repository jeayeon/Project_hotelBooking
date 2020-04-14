package Swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CalendarData extends JFrame implements ActionListener {

	private Calendar today = Calendar.getInstance(); // 현재 날짜 및 시간을 받아옴
	private Calendar cal = new GregorianCalendar(); // 그래고리안 캘린더
	private int year = today.get(Calendar.YEAR); // 현재 년도
	private int month = today.get(Calendar.MONTH) + 1;// 현재월 캘린더라는 클래스안에서 1월은 0으로 정의되어있어서 보기편하게 +1
	private int date = today.get(Calendar.DATE);// 현재 날짜

	private int thisyear = today.get(Calendar.YEAR);
	private int thismonth = today.get(Calendar.MONTH) + 1;

	private JPanel North, Center, South;
	private JLabel Northday;
	private JButton NextMonth, PreviousMonth, Choice;

	private JLabel[] weekLabel = new JLabel[7];
	private String[] daysLabel = { "일", "월", "화", "수", "목", "금", "토" };
	private JButton[] calBtn = new JButton[42];
	private int dayOfweek = 0;
	
	private Reservation rv = null;

	CalendarData(Reservation reservation) {
		rv = reservation;
		this.setBounds(500, 300, 340, 350);

		Center = new JPanel(new GridLayout(7, 7));
		this.add(Center, "Center");

		NorthSet();
		CenterSet();
		CalenderSet();
		SouthSet();

		this.NextMonth.addActionListener(this);
		this.PreviousMonth.addActionListener(this);
		this.Choice.addActionListener(this);
		this.setVisible(true);
		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
	}

	private void CalenderSet() {
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DATE, 1);
		dayOfweek = cal.get(cal.DAY_OF_WEEK); // 날짜가 그 주의무슨요일이냐에따라 숫자가나온다 일:1,월:2,화:3 이런순

//		System.out.println(cal.get(cal.DAY_OF_WEEK));
		cal.getFirstDayOfWeek();// 주의 시작요일 일요일부터 시작하기에 무조건 1이 나온다

		for (int i = cal.getFirstDayOfWeek(); i < dayOfweek; i++) {// 1일 시작전에 달력날짜를 비우기위한 for문
			calBtn[i - 1].setText("");
		}

//		System.out.println(today.get(Calendar.YEAR));
//		System.out.println(today.get(Calendar.MONTH)); //자바에선 1~12월이 0~11로표기
//		System.out.println(today.get(Calendar.DATE));
//		System.out.println(cal.getActualMaximum(Calendar.DATE));
//		System.out.println(cal.getMaximum(Calendar.MONTH));
//		System.out.println(cal.getActualMinimum(Calendar.DAY_OF_MONTH));
//		System.out.println(cal.getMinimum(Calendar.DATE));

		// 현재달 1일부터 마지막일까지 숫자 삽입
							//그달의 맥시멈 숫자
		for (int i = 0; i < cal.getActualMaximum(Calendar.DATE); i++) {
			calBtn[i + dayOfweek - 1].setText(i + 1 + "");
			calBtn[i + dayOfweek - 1].setFont(new Font("돋움", Font.PLAIN, 12));
			calBtn[i + dayOfweek - 1].addActionListener(this);
		}

		// 이미 지난날자 버튼 클릭안되게 설정
		if (thismonth == month && thisyear == year) {
			for (int i = 0; i < date - 1; i++) {
				calBtn[i + dayOfweek - 1].setEnabled(false);
			}
		}
		if (thismonth > month && thisyear == year || thisyear > year) {
			for (int i = 0; i < calBtn.length; i++) { // 비어있는 버튼 막기
				calBtn[i].setEnabled(false);
			}
		}

		for (int i = 0; i < calBtn.length; i++) { // 비어있는 버튼 막기
			if (calBtn[i].getText().equals("")) {
				calBtn[i].setEnabled(false);
			}
		}

	}

	private void SouthSet() {
		South = new JPanel();
		South.add(Choice = new JButton("선택"));
		this.add(South, "South");

	}

	private void CenterSet() {

		for (int i = 0; i < weekLabel.length; i++) {
			Center.add(weekLabel[i] = new JLabel(daysLabel[i]));
			weekLabel[i].setHorizontalAlignment(JLabel.CENTER);
		}

		for (int i = 0; i < calBtn.length; i++) {
			Center.add(calBtn[i] = new JButton(""));
			calBtn[i].setFont(new Font("돋움", Font.PLAIN, 13));
//			calBtn[i].setBorderPainted(false);
//			calBtn[i].setContentAreaFilled(false);
		}

	}

	private void NorthSet() { // 상단 년월 및 달선택
		North = new JPanel(new BorderLayout());
		Northday = new JLabel(year + "년 " + month + "월");
		Northday.setFont(new Font("돋움", Font.BOLD, 20));
		Northday.setHorizontalAlignment(Northday.CENTER);
		North.add(Northday, "Center");

		PreviousMonth = new JButton("<");
		PreviousMonth.setFont(new Font("돋움", Font.BOLD, 12));
		PreviousMonth.setBorderPainted(false);
		PreviousMonth.setContentAreaFilled(false);
		PreviousMonth.setFocusPainted(false);
		North.add(PreviousMonth, "West");
		PreviousMonth.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {
				PreviousMonth.setForeground(Color.BLACK);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				PreviousMonth.setForeground(Color.gray);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		NextMonth = new JButton(">");
//		System.out.println(cal.getActualMaximum(Calendar.DATE));
		NextMonth.setFont(new Font("돋움", Font.BOLD, 12));
		NextMonth.setBorderPainted(false);
		NextMonth.setContentAreaFilled(false);
		NextMonth.setFocusPainted(false);
		North.add(NextMonth, "East");
		NextMonth.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {
				NextMonth.setForeground(Color.BLACK);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				NextMonth.setForeground(Color.gray);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		this.add(North, "North");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object click = e.getSource();
		if (click == NextMonth) { // 다음버튼 누를시
			InputCalendar(1);
			Center.removeAll();
			CenterSet();
			CalenderSet();
			Northday.setText(year + "년 " + month + "월");

		} else if (click == PreviousMonth) {//이전버튼누를시
			InputCalendar(-1);
			Center.removeAll();
			CenterSet();
			CalenderSet();
			Northday.setText(year + "년 " + month + "월");
		}else if (click == Choice) {
			dispose();
		}
		//System.out.println(dayOfweek);
		for(int i=0;i<cal.getActualMaximum(Calendar.DATE);i++) {
			if(click.equals(calBtn[i+dayOfweek-1])) { // 
				//System.out.println(calBtn[i+dayOfweek-1].getText());
				String day = calBtn[i+dayOfweek-1].getText();
				//System.out.println(day);
				rvoutput(day);
			}
		}
		
		

	}
	

	private void rvoutput(String day) { //rv로 보내주기위한
		
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		int d = Integer.parseInt(day);
		Calendar c = new GregorianCalendar(year, month-1, d);
		String a=date.format(c.getTime()); 
		rv.daychk(a,year,month-1,d);
		//System.out.println(a);
	}

	private void InputCalendar(int a) {
		month = month + a;
		if (month <= 0) { // 0보다 내려갈시
			month = 12;// 이전해로 넘어감
			year = year - 1;
		} else if (month >= 13) {
			month = 1; // 다음 해로 넘어감
			year = year + 1;
		}
	}

//	public static void main(String[] args) {
//		new CalendarData();
//	}

}
