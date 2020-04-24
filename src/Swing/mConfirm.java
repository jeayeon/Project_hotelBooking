package Swing;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import JDBC.BDTO;

public class mConfirm extends JFrame {

	private JPanel JlistP;
	private JList<String> jlist;
	private JScrollPane jScrollpane;
	private DefaultListModel<String> dm = new DefaultListModel<>();
	private JLabel renumList2, name2, chkin2, chkout2, fewday2, total2;
	private ArrayList<BDTO> bList = null;
	private Hmain Hm = null;

	public mConfirm(Hmain Hm, ArrayList<BDTO> bList) {
		this.bList = bList;
		this.Hm = Hm;

		this.setLayout(null);
		this.setBounds(100, 100, 303, 439);
		renumListSet();
		labelSet();
		buttonSet();
		jlistSet();
		binfoSet();

		this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	private void renumListSet() {
		System.out.println("여기서도:" + Hm.getId());
		if (bList.get(0).getId() != null) {
			for (int i = 0; i < bList.size(); i++) {
				if (Hm.getId().equals(bList.get(i).getId())) {
					dm.addElement(bList.get(i).getRenum());
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "예약내용이 없습니다.", "Message", JOptionPane.INFORMATION_MESSAGE);
		}

	}

	private void binfoSet() {
		renumList2 = new JLabel("");
		renumList2.setBounds(125, 136, 68, 21);
		this.add(renumList2);

		name2 = new JLabel("");
		name2.setBounds(125, 167, 68, 21);
		this.add(name2);

		chkin2 = new JLabel("");
		chkin2.setBounds(125, 198, 68, 21);
		this.add(chkin2);

		chkout2 = new JLabel("");
		chkout2.setBounds(125, 229, 68, 21);
		this.add(chkout2);

		fewday2 = new JLabel("");
		fewday2.setBounds(125, 260, 68, 21);
		this.add(fewday2);

		total2 = new JLabel("");
		total2.setBounds(125, 291, 68, 21);
		this.add(total2);

	}

	private void jlistSet() {
		jlist = new JList<String>(dm);
		jScrollpane = new JScrollPane(jlist);
		jScrollpane.setBounds(12, 28, 263, 82);
		this.add(jScrollpane);

		jlist.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				String renumber = jlist.getSelectedValue();// 클릭한 행의 값 반환
				for (int i = 0; i < bList.size(); i++) {
					if (renumber.equals(bList.get(i).getRenum())) {
						renumList2.setText(bList.get(i).getRenum());
						name2.setText(bList.get(i).getName());
						chkin2.setText(bList.get(i).getCheckin());
						chkout2.setText(bList.get(i).getCheckout());
						fewday2.setText(bList.get(i).getNight() + "");
						total2.setText(bList.get(i).getPrice() + "");
					}
				}

			}
		});

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
				Hm.setVisibertrue();
				dispose();

			}
		});

	}

	private void labelSet() {
		JLabel renumList = new JLabel("예약리스트");
		renumList.setBounds(110, 0, 80, 15);
		this.add(renumList);

		JLabel renum = new JLabel("예약번호:");
		renum.setBounds(45, 136, 68, 21);
		this.add(renum);

		JLabel name = new JLabel("성      명:");
		name.setBounds(45, 167, 68, 21);
		this.add(name);

		JLabel chkin = new JLabel("체 크  인:");
		chkin.setBounds(45, 198, 68, 21);
		this.add(chkin);

		JLabel chkout = new JLabel("체크아웃:");
		chkout.setBounds(45, 229, 68, 21);
		this.add(chkout);

		JLabel fewday = new JLabel("숙박기간:");
		fewday.setBounds(45, 260, 68, 21);
		this.add(fewday);

		JLabel totalPrice = new JLabel("합      계:");
		totalPrice.setBounds(45, 291, 68, 21);
		this.add(totalPrice);
	}

//	public static void main(String[] args) {
//		new mConfirm();
//	}
}
