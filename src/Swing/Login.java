package Swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Client.Cconnect;

public class Login extends JFrame {
	
	private JTextField textField;
	private JTextField textField_1;
	private JLabel chk;
	private Hmain Hm = null;
	private Cconnect Ccon = null;

	Login(Cconnect c) {
		super("로그인");
		Hm = Hmain.getInstance(c);
		this.Ccon = c;

		this.setLayout(null);
		this.setBounds(500, 200, 250, 260);
		
		setLabel();
		setTextField();
		setButton();

		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		this.setVisible(true); //
		
	}

	
	private void setButton() {
		JButton back = new JButton("< 이 전");
		back.setBounds(5, 174, 100, 23);
		back.setFont(new Font("굴림", Font.PLAIN, 11));
		back.setBorderPainted(false); // 버튼 선 제거
		back.setContentAreaFilled(false); //버튼 색 제거
		back.setFocusPainted(false); // 버튼 선택선 제거
		this.add(back);		
		
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Hm.setVisibertrue();
				dispose();
				
			}
		});
		
		JButton login = new JButton("로그인");
		login.setBounds(70, 145, 90, 23);
		this.add(login);
		
		login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String info = textField.getText()+" "+textField_1.getText();
				Ccon.send("same:"+info);
			}
		});
	}

	private void setTextField() {
		textField = new JTextField(); //id텍스트필드
		textField.setBounds(91, 50, 91, 21);
		this.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField(); //pwd텍스트필드
		textField_1.setBounds(91, 90, 91, 21);
		this.add(textField_1);
		textField_1.setColumns(10);
		
	}

	private void setLabel() {
		JLabel id = new JLabel("아 이 디");
		id.setFont(new Font("굴림", Font.PLAIN, 13));
		id.setBounds(28, 50, 65, 15);
		this.add(id);

		JLabel pwd = new JLabel("비빌번호");
		pwd.setFont(new Font("굴림", Font.PLAIN, 13));
		pwd.setBounds(28, 90, 57, 15);
		this.add(pwd);
		
		chk = new JLabel("");
		chk.setFont(new Font("굴림", Font.PLAIN, 12));
		chk.setBounds(10, 120, 300, 15);
		this.add(chk);
	}


	public void send(String msg) {
		System.out.println(msg);
		if(msg.equals("!no")) {//로그인이 완료되면
			JOptionPane.showMessageDialog(null, "로그인이 완료되었습니다.","Message",JOptionPane.INFORMATION_MESSAGE);
			dispose();
			new Choice(Ccon);
		}else if(msg.equals("!yes")) {
			chk.setText("아이디 혹은 비밀번호가 틀렸습니다");
			chk.setForeground(Color.red);
		}
		
	}
}
