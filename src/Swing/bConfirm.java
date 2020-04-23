package Swing;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import JDBC.BDTO;

public class bConfirm extends JFrame {

	private JPanel JlistP;
	private JLabel renumList2, name2, chkin2, chkout2, fewday2, total2, chk;
	private JLabel renumList, name, chkin, chkout, fewday,renum,totalPrice;
	private JTextField chkF;
	private JButton ok;
	private ArrayList<BDTO> bList = null;

	public bConfirm(ArrayList<BDTO> bList) {
		this.bList = bList;
		this.setLayout(null);
		this.setBounds(100, 100, 303, 439);
		labelSet();
		textFildSet();
		buttonSet();
		binfoSet();

		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	private void textFildSet() {
		chkF = new JTextField("");
		chkF.setBounds(55, 150, 180, 25);
		chkF.setVisible(true);
		this.add(chkF);

	}

	private void binfoSet() {
		renumList2 = new JLabel(":");
		renumList2.setBounds(125, 136, 68, 21);
		renumList2.setVisible(false);
		this.add(renumList2);

		name2 = new JLabel("");
		name2.setBounds(125, 167, 90, 21);
		name2.setVisible(false);
		this.add(name2);

		chkin2 = new JLabel("");
		chkin2.setBounds(125, 198, 68, 21);
		chkin2.setVisible(false);
		this.add(chkin2);

		chkout2 = new JLabel("");
		chkout2.setBounds(125, 229, 68, 21);
		chkout2.setVisible(false);
		this.add(chkout2);

		fewday2 = new JLabel("");
		fewday2.setBounds(125, 260, 68, 21);
		fewday2.setVisible(false);
		this.add(fewday2);

		total2 = new JLabel("");
		total2.setBounds(125, 291, 68, 21);
		total2.setVisible(false);
		this.add(total2);

	}

	private void buttonSet() {
		JButton back = new JButton("< 이 전");
		back.setBounds(5, 350, 100, 23);
		back.setFont(new Font("굴림", Font.PLAIN, 11));
		back.setBorderPainted(false); // 버튼 선 제거
		back.setContentAreaFilled(false); // 버튼 색 제거
		back.setFocusPainted(false); // 버튼 선택선 제거
		this.add(back);

		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});

		ok = new JButton("확 인");
		ok.setBounds(100, 200, 70, 30);
		this.add(ok);
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				for (int i = 0; i < bList.size(); i++) {
					String renum = bList.get(i).getRenum();
					if (chkF.getText().equals(renum)) {
						renumList2.setText(bList.get(i).getRenum());
						name2.setText(bList.get(i).getName());
						chkin2.setText(bList.get(i).getCheckin());
						chkout2.setText(bList.get(i).getCheckout());
						fewday2.setText(bList.get(i).getNight() + "");
						total2.setText(bList.get(i).getPrice() + "");

						visibleSet();
					}
				}

			}

			private void visibleSet() {
				// false
				chk.setVisible(false);
				chkF.setVisible(false);
				ok.setVisible(false);

				// true
				renumList.setVisible(true);
				renumList2.setVisible(true);
				renum.setVisible(true);
				name.setVisible(true);
				name2.setVisible(true);
				chkin.setVisible(true);
				chkin2.setVisible(true);
				chkout.setVisible(true);
				chkout2.setVisible(true);
				fewday.setVisible(true);
				fewday2.setVisible(true);
				total2.setVisible(true);
				totalPrice.setVisible(true);

			}
		});

	}

	private void labelSet() {

		chk = new JLabel("예약번호를 입력하세요");
		chk.setFont(new Font("돋움", Font.BOLD, 17));
		chk.setBounds(50, 70, 190, 50);
		chk.setVisible(true);
		this.add(chk);

		renumList = new JLabel("예약현황");
		renumList.setFont(new Font("돋움", Font.BOLD, 20));
		renumList.setBounds(100, 70, 100, 50);
		renumList.setVisible(false);
		this.add(renumList);

		renum = new JLabel("예약번호:");
		renum.setBounds(45, 136, 68, 21);
		renum.setVisible(false);
		this.add(renum);

		name = new JLabel("성      명:");
		name.setBounds(45, 167, 68, 21);
		name.setVisible(false);
		this.add(name);

		chkin = new JLabel("체 크  인:");
		chkin.setBounds(45, 198, 68, 21);
		chkin.setVisible(false);
		this.add(chkin);

		chkout = new JLabel("체크아웃:");
		chkout.setBounds(45, 229, 68, 21);
		chkout.setVisible(false);
		this.add(chkout);

		fewday = new JLabel("숙박기간:");
		fewday.setBounds(45, 260, 68, 21);
		fewday.setVisible(false);
		this.add(fewday);

		totalPrice = new JLabel("합      계:");
		totalPrice.setBounds(45, 291, 68, 21);
		totalPrice.setVisible(false);
		this.add(totalPrice);

	}

//	public static void main(String[] args) {
//		new bConfirm();
//	}
}
