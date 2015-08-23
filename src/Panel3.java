import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

class Panel3 extends JPanel implements ActionListener{
	private JComboBox id;
	private Object[] array,a;
	private Vector<String> v=new Vector(); 
	private Vector<String> vector=new Vector(); 
	private manageMysql sql=new manageMysql();
	private JButton buttonOK;
	private JButton ok ;
	
	private UtilDateModel model;
	private JDatePanelImpl datePanel;
	
	private String date,s_id;
	private SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
	//private manageMysql connectionObject = new manageMysql();
	//private JDatePanelImpl datePanel;
	private JDatePickerImpl datePicker;
	//private JTextArea area=new JTextlArea();
	private JPanel panel;
    public Panel3() {
    	sql.createConnection();
    	Test.setLookAndFeel();
        this.setBackground(new Color(0,0,0,0));
        
        
       // setLayout(new FlowLayout());
        
		JLabel label=new JLabel("Transaction Date: ");
		label.setPreferredSize(new Dimension(150,30));
		label.setFont(new Font("Tahoma",1,15));
		
		add(label);
		
		model = new UtilDateModel();
		model.setDate(2014, 9, 16);
		model.setSelected(true);

		datePanel = new JDatePanelImpl(model);

		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

		add(datePicker);
		 buttonOK = new JButton("OK");
		buttonOK.addActionListener(this);

		add(buttonOK);
		
		
		panel=new JPanel();
		panel.setPreferredSize(new Dimension(900,400));
		panel.setLayout(null);
		panel.setBackground(new Color(0,0,0,0));
		//panel.add(new JScrollPane(area),BorderLayout.CENTER );
		add(panel);
 		//panel.add(new JScrollPane(area),BorderLayout.CENTER );
        this.setBounds(0,200,900,500);
	
        
        try {
			v=sql.sales_id();
		} catch (SQLException e) {
			e.printStackTrace();	
		}
        
        JLabel label1=new JLabel("Select customer: ");
		label1.setBounds(250, 100, 150, 40);
		label1.setFont(new Font("Tahoma",1,15));
		
		
    	array = v.toArray();
     	id=new JComboBox(array);
    	id.setBounds(500, 100, 100, 40);

     	id.addItemListener(
     			new ItemListener()
     			{
					@Override
					public void itemStateChanged(ItemEvent event) {
						if(event.getStateChange()==ItemEvent.SELECTED)
						{	     				

							String Strings = (String) array[id.getSelectedIndex()];
							  manageMysql sqlManageMysql = new manageMysql() ;
						      String string=sqlManageMysql.getprice(Strings);
							TransactionTable c=new TransactionTable(Strings,string);
							c.setVisible(true);
						}
					}
     			});
     	panel.add(label1);
     	panel.add(id);
		//System.out.println(id);
		
     	
    }
    public void actionPerformed(ActionEvent event) {
    	sql.createConnection();
    	if(event.getSource()==buttonOK)
    	{
	    	Date today = (Date) datePicker.getModel().getValue();
			date =  DATE_FORMAT.format(today);
			DailySalesTable d=new DailySalesTable(date);
  			d.setVisible(true);
    	}			
	}
    
}
