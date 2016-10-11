import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


class TestFrame extends JFrame implements ActionListener,ItemListener
{
	JLabel labSex = new JLabel ("性别");//label for identify the item 
	JLabel labBirthday = new JLabel("生日");
	JLabel labYear = new JLabel("年");
	JLabel labMonth = new JLabel("月");
	JLabel labDay = new JLabel("日");
	JLabel labNative = new JLabel("籍贯");
	
	
	JButton btnOk = new JButton ("确定");//create a button
	JTextArea ta = new JTextArea(3,30);//also set the area of "btnOk"button
	
	JRadioButton rbtnMale = new JRadioButton("男",true);//RadioButton,you can only chose one option at the same time
	JRadioButton rbtnFemale = new JRadioButton ("女");//while the value is "true",it would be the default option
	
	JComboBox cmbYear = new JComboBox();//组合框-combobox,create a drop-down menu
	JComboBox cmbMonth = new JComboBox();
	JComboBox cmbDay = new JComboBox();
	JComboBox cmbNative = new JComboBox();
	
	JPanel panel1 = new JPanel();//combine differnt elements such as JLabel and JButton
	JPanel panel2 = new JPanel();
	JPanel panel3 = new JPanel();
	//JPanel panel4 = new JPanel();
	
	Container cp;// = frame.getContentPane();
	
	
	TestFrame ()//overloaded function
	{
		cp = this.getContentPane();
		this.setTitle("测试窗体");
		
		//panel1
		panel1.setLayout(new GridLayout(1,3,10,10));//set the layout of panel 1
		panel1.add(labSex);// add the label-labsex(性别) into the panel
		
		
		ButtonGroup group = new ButtonGroup();//This class is used to create a multiple-exclusion scope for a set of buttons. 
											  //Creating a set of buttons with the same ButtonGroup object means that turning 
											  //"on" one of those buttons turns off all other buttons in the group. 
		group.add(rbtnMale);//add button-rbtnMale"男" into the group so that it can be use as radio-button
		group.add(rbtnFemale);
		
		panel1.add(rbtnMale);
		panel1.add(rbtnFemale);
		
		
		//panel2
		
		for (int i=1990;i<2100;i++)//add the options into the comboBox
		cmbYear.addItem(i);
		
		for (int i=1;i<=12;i++)
		cmbMonth.addItem(i);
		
		for (int i=1;i<=31;i++)
		cmbDay.addItem(i);
		
//		String[] NativeList = new String[5];//create a space to store 5 elements
//		NativeList[0] = 广东;//diretly assign the first element of the array
		
		String[] NativeList = {"广东","北京","上海"};
		for (int i=0;i<=2;i++)
		cmbNative.addItem(NativeList[i]);
		
		panel2.setLayout(new GridLayout(1,7));
		panel2.add(labBirthday);
		panel2.add(cmbYear);
		panel2.add(labYear);
		panel2.add(cmbMonth);
		panel2.add(labMonth);
		panel2.add(cmbDay);
		panel2.add(labDay);
		panel2.add(labNative);
		panel2.add(cmbNative); 
		
		
		//panel3
		panel3.setLayout(new GridLayout(1,2));
		panel3.add(btnOk);
		panel3.add(ta);
		
		cp.setLayout(new FlowLayout());
		cp.add(panel1);//loaded the components in order
		cp.add(panel2);
		cp.add(panel3);
		
		rbtnMale.addActionListener(this);//monitor of the action
		rbtnFemale.addActionListener(this);
		btnOk.addActionListener(this);
		
		cmbYear.addItemListener(this);//monitor of the action
		cmbMonth.addItemListener(this);
		cmbDay.addItemListener(this);
		this.pack();
		this.setLocation(200,300);
		this.setVisible(true);
	}
	
	public void itemStateChanged(ItemEvent e)//to change the
	{
		if (e.getSource()==cmbYear||e.getSource()==cmbMonth)
		{
			int month = cmbMonth.getSelectedIndex();
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
				int year = cmbYear.getSelectedIndex();
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
			
			int year = cmbYear.getSelectedIndex()+1990;
			int month = cmbMonth.getSelectedIndex()+1;
			int day = cmbDay.getSelectedIndex()+1;
			
			str += "出生日期:"+year+"年"+month+"月"+day+"日";
				
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
