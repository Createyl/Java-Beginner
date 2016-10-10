import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


class TestFrame extends JFrame implements ActionListener,ItemListener
{
	JLabel labSex = new JLabel ("性别");
	JLabel labBirthday = new JLabel("生日");
	JLabel labYear = new JLabel("年");
	JLabel labMonth = new JLabel("月");
	JLabel labDay = new JLabel("日");
	
	JButton btnOk = new JButton ("确定");
	JTextArea ta = new JTextArea(3,10);
	
	JRadioButton rbtnMale = new JRadioButton("男",true);
	JRadioButton rbtnFemale = new JRadioButton ("女");
	
	JComboBox<Integer> cmbYear = new JComboBox<Integer>();
	
	JComboBox<Integer> cmbMonth = new JComboBox<Integer>();
	JComboBox<Integer> cmbDay = new JComboBox<Integer>();
	
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	JPanel panel3 = new JPanel();
	
	Container cp;// = frame.getContentPane();
	
	TestFrame ()
	{
		cp = this.getContentPane();
		this.setTitle("测试窗体");
		panel1.setLayout(new GridLayout(1,3,10,10));
		panel1.add(labSex);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rbtnMale);
		group.add(rbtnFemale);
		
		panel1.add(rbtnMale);
		panel1.add(rbtnFemale);
		
		for (int i=1990;i<2100;i++)
		cmbYear.addItem(i);
		
		for (int i=1;i<=12;i++)
		cmbMonth.addItem(i);
		
		for (int i=1;i<=31;i++)
		cmbDay.addItem(i);
		
		
		panel2.setLayout(new GridLayout(1,7));
		panel2.add(labBirthday);
		panel2.add(cmbYear);
		panel2.add(labYear);
		panel2.add(cmbMonth);
		panel2.add(labMonth);
		panel2.add(cmbDay);
		panel2.add(labDay);
		
		panel3.setLayout(new GridLayout(1,2));
		panel3.add(btnOk);
		panel3.add(ta);
		
		cp.setLayout(new FlowLayout());
		cp.add(panel1);
		cp.add(panel2);
		cp.add(panel3);
		
		rbtnMale.addActionListener(this);
		rbtnFemale.addActionListener(this);
		btnOk.addActionListener(this);
		
		cmbYear.addItemListener(this);
		cmbMonth.addItemListener(this);
		this.pack();
		this.setLocation(200,300);
		this.setVisible(true);
	}
	
	public void itemStateChanged(ItemEvent e)
	{
		if (e.getSource()==cmbYear||e.getSource()==cmbMonth)
		{
			int month = (int)cmbMonth.getSelectedItem();
			switch (month)
			{
				case 1: case 3:case 5: case 7: case 8: case 10:case 12:
			    cmbDay.removeAllItems();
				for (int i=1;i<=31;i++)
				cmbDay.addItem(i);
				break;
				case 4: case 6: case 9: case 11:
				cmbDay.removeAllItems();
				for (int i=1;i<=30;i++)
				cmbDay.addItem(i);
				break;
				case 2:
				cmbDay.removeAllItems();
				int year = (int)cmbYear.getSelectedItem();
				if (year%4==0&&year%100!=0||year%400==0)
					for (int i=1;i<=29;i++)
						cmbDay.addItem(i);
				else
					for (int i=1;i<=28;i++)
						cmbDay.addItem(i);
				
			}
		}
	}
	
	public void actionPerformed (ActionEvent e)
	{
		if (e.getSource()==btnOk)
		{
			String str = "性别是：";
			String sex ="";
			if (rbtnMale.isSelected())
				sex ="男\r\n";
			if (rbtnFemale.isSelected())
				sex ="女\r\n";
			
			str += sex;
			
			int year = (int)cmbYear.getSelectedItem();
			int month = (int)cmbMonth.getSelectedItem();
			int day = (int)cmbDay.getSelectedItem();
			
			str += "出生日期:"+ year+"年"+month+"月"+day+"日";
				
			ta.setText(str);
		}
	}
}
class Test
{
	public static void main (String args[])
	{
		new TestFrame();
	}
}