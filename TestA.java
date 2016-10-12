import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

class TestFrameA extends JFrame implements ActionListener, ItemListener {
	JLabel labSex = new JLabel("性别");
	JLabel labBirthday = new JLabel("生日");
	JLabel labYear = new JLabel("年");
	JLabel labMonth = new JLabel("月");
	JLabel labDay = new JLabel("日");

	JButton btnOk = new JButton("确定");
	JTextArea ta = new JTextArea(3, 10);

	JRadioButton rbtnMale = new JRadioButton("男", true);
	JRadioButton rbtnFemale = new JRadioButton("女");

	JComboBox<Integer> cmbYear = new JComboBox<Integer>();

	JComboBox<Integer> cmbMonth = new JComboBox<Integer>();
	JComboBox<Integer> cmbDay = new JComboBox<Integer>();

	JComboBox province;
	JComboBox city;
	JComboBox county;

	String[] provinceString = { "河北", "河南" };

	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	JPanel panel3 = new JPanel();

	int index=0;
	String provinceName="河北";
	Container cp;// = frame.getContentPane();

	TestFrameA() {
		cp = this.getContentPane();
		this.setTitle("测试窗体");
		panel1.setLayout(new GridLayout(1, 3, 10, 10));
		panel1.add(labSex);

		ButtonGroup group = new ButtonGroup();
		group.add(rbtnMale);
		group.add(rbtnFemale);

		panel1.add(rbtnMale);
		panel1.add(rbtnFemale);

		for (int i = 1990; i < 2100; i++)
			cmbYear.addItem(i);

		for (int i = 1; i <= 12; i++)
			cmbMonth.addItem(i);

		for (int i = 1; i <= 31; i++)
			cmbDay.addItem(i);

		panel2.setLayout(new GridLayout(1, 7));
		panel2.add(labBirthday);
		panel2.add(cmbYear);
		panel2.add(labYear);
		panel2.add(cmbMonth);
		panel2.add(labMonth);
		panel2.add(cmbDay);
		panel2.add(labDay);

		panel3.setLayout(new GridLayout(1, 2));
		panel3.add(btnOk);
		panel3.add(ta);

		CreateJG();

		cp.setLayout(new FlowLayout());
		cp.add(panel1);
		cp.add(panel2);
		cp.add(panel3);
		cp.add(province);
		cp.add(city);
		cp.add(county);

		rbtnMale.addActionListener(this);
		rbtnFemale.addActionListener(this);
		btnOk.addActionListener(this);

		city.addItemListener(this);
		cmbYear.addItemListener(this);
		cmbMonth.addItemListener(this);
		this.pack();
		this.setLocation(200, 300);
		this.setVisible(true);
	}

	public void CreateJG() {
		province = new JComboBox(provinceString);
		String[] cityString = { "保定市", "石家庄市", "承德市" };
		city = new JComboBox(cityString);
		String[] countyString = { "北市区", "新市区", "竞秀区" };
		county = new JComboBox(countyString);

		province.addItemListener(this);
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == cmbYear || e.getSource() == cmbMonth) {
			int month = (int) cmbMonth.getSelectedItem();
			switch (month) {
				case 1:
				case 3:
				case 5:
				case 7:
				case 8:
				case 10:
				case 12:
					cmbDay.removeAllItems();
					for (int i = 1; i <= 31; i++)
						cmbDay.addItem(i);
					break;
				case 4:
				case 6:
				case 9:
				case 11:
					cmbDay.removeAllItems();
					for (int i = 1; i <= 30; i++)
						cmbDay.addItem(i);
					break;
				case 2:
					cmbDay.removeAllItems();
					int year = (int) cmbYear.getSelectedItem();
					if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
						for (int i = 1; i <= 29; i++)
							cmbDay.addItem(i);
					else
						for (int i = 1; i <= 28; i++)
							cmbDay.addItem(i);

			}
		}
		//
		if (e.getSource() == province) {
			index = province.getSelectedIndex();
			provinceName = (String) province.getItemAt(index);
			if (index == 0) {
				String[] cityString = { "保定市", "石家庄市", "承德市" };
				city.removeAllItems();
				county.removeAllItems();
				for (int i = 0; i < cityString.length; i++)
					city.addItem(cityString[i]);
			}
			else if(index==1){
				String[] cityString = { "D", "E", "F" };
				city.removeAllItems();
				for (int i = 0; i < cityString.length; i++)
					city.addItem(cityString[i]);
				String[] countyString = { "G", "H", "I" };
				county.removeAllItems();
				for (int i = 0; i < countyString.length; i++)
					county.addItem(countyString[i]);
			}

		}
		if(e.getSource()==city){
			if (provinceName.equals("河北")&&e.getStateChange()==e.SELECTED) {
				int index2 = city.getSelectedIndex();
				if (index2 == 0) {
					String[] countyString = { "北市区", "新市区", "竞秀区" };
					county.removeAllItems();
					for (int i = 0; i < countyString.length; i++)
						county.addItem(countyString[i]);
				} else if (index2 == 1) {
					String[] countyString = { "长安区", "桥东区", "桥西区" };
					county.removeAllItems();
					for (int i = 0; i < countyString.length; i++)
						county.addItem(countyString[i]);
				} else if (index2 == 2) {
					String[] countyString = { "A区", "B区", "C区" };
					county.removeAllItems();
					for (int i = 0; i < countyString.length; i++)
						county.addItem(countyString[i]);
				}
			}
			else if (provinceName.equals("河南")&&e.getStateChange()==e.SELECTED) {
				int index2 = city.getSelectedIndex();
				if (index2 == 0) {
					String[] countyString = { "G", "H", "I" };
					county.removeAllItems();
					for (int i = 0; i < countyString.length; i++)
						county.addItem(countyString[i]);
				} else if (index2 == 1) {
					String[] countyString = { "J", "K", "L" };
					county.removeAllItems();
					for (int i = 0; i < countyString.length; i++)
						county.addItem(countyString[i]);
				} else if (index2 == 2) {
					String[] countyString = { "M", "N", "O" };
					county.removeAllItems();
					for (int i = 0; i < countyString.length; i++)
						county.addItem(countyString[i]);
				}
			}
		}

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnOk) {
			String str = "性别是：";
			String sex = "";
			if (rbtnMale.isSelected())
				sex = "男\r\n";
			if (rbtnFemale.isSelected())
				sex = "女\r\n";

			str += sex;

			int year = (int) cmbYear.getSelectedItem();
			int month = (int) cmbMonth.getSelectedItem();
			int day = (int) cmbDay.getSelectedItem();

			str += "出生日期:" + year + "年" + month + "月" + day + "日";

			ta.setText(str);
		}
	}
}

public class TestA {
	public static void main(String args[]) {
		new TestFrameA();
	}
}