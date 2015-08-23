import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.mysql.jdbc.authentication.MysqlClearPasswordPlugin;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

class Panel4 extends JPanel implements ActionListener{
	
	
	
	private String customer,id,amount,sales_price,  data_amount;
	private int data_b,data_n;
	private manageMysql sql=new manageMysql();
	private static int customer_id;
	private JButton change_customer_id;
	private JButton ok ;
	private JTextField text1,text2;
	private JTextField text3;
	private JTextField text4;
	private JLabel jlabel1,jlabel2,jlabel3,jlabel4;
	private manageMysql connectionObject = new manageMysql();
	private JDatePickerImpl datePicker;
	private JPanel panel;
    public Panel4() {
	    	Test.setLookAndFeel();
	        this.setBackground(new Color(0,0,0,0));
	        
	        
	        setLayout(new FlowLayout());
			
			try {
				String s=sql.customer();
				System.out.println(s);
				customer_id=Integer.parseInt(s);
			} catch (NumberFormatException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			panel=new JPanel();
			panel.setPreferredSize(new Dimension(900,400));
			panel.setLayout(null);
			jlabel1 = new JLabel("Enter Buyer ID:");
	        jlabel1.setFont(new Font("Verdana",1,20));
	        jlabel1.setBounds(200,60,200,120);
			
		    jlabel2=new JLabel("Enter Product ID:");
		    jlabel2.setBounds(200,110,200,120);
		    jlabel2.setFont(new Font("Verdana",1,20));
	        
	        jlabel3=new JLabel("Enter price:");
	        jlabel3.setBounds(200,160,250,120);
	        jlabel3.setFont(new Font("Verdana",1,20));
	        
	        jlabel4=new JLabel("Enter amount:");
	        jlabel4.setFont(new Font("Verdana",1,20));
	        jlabel4.setBounds(200,210,200,120);
	        
	        ok=new JButton("OK");
	        ok.setBounds(385,340,120,40);
			ok.addActionListener(this);

			text1=new JTextField();
		    text1.setFont(new Font("Tahoma",1,20));
		    text1.setBounds(460,100,200,40);
			
		    change_customer_id=new JButton("Change ID");
		    change_customer_id.setBounds(680,100,120,40);
		    change_customer_id.addActionListener(this);
			
	        text2=new JTextField();
	        text2.setFont(new Font("Tahoma",1,20));
	        text2.setBounds(460,150,200,40);
	        
	        text3=new JTextField();
	        text3.setFont(new Font("Tahoma",1,20));
	        text3.setBounds(460,200,200,40);
	        
	        text4=new JTextField();
	        text4.setFont(new Font("Tahoma",1,20));
	        text4.setBounds(460,250,200,40);
	         
	        panel.add(change_customer_id);
	        panel.add(jlabel1);
	        panel.add(text1);
	        panel.add(jlabel2);
	        panel.add(text2);
	        panel.add(jlabel3);
	        panel.add(text3);
	        panel.add(jlabel4);
	        panel.add(text4);
	        panel.add(ok);
	        
		//panel.add(new JScrollPane(area),BorderLayout.CENTER );
		add(panel);
 		//panel.add(new JScrollPane(area),BorderLayout.CENTER );
        this.setBounds(0,200,900,500);
        
        
        

		
		//System.out.println(id);
		
    }
    
    public void ChangeBuyerId()
    {
    	int x = customer_id;
    	String string=String.format("%d",customer_id);
			text1.setText("");
    		text1.setText(string);
    	
    }
    
    public void actionPerformed(ActionEvent event) {
    	
    	
     if(event.getSource()==ok)
    	{
    		    customer=text1.getText();
    			id=text2.getText();	
    			//sales_price=text3.getText();	
    			amount=text4.getText();
    			data_amount=sql.amount(id);
    			
    			data_b=Integer.parseInt(data_amount);
    			data_n=Integer.parseInt(amount);
    			try {
    				//System.out.println(data_b);    				System.out.println(data_n);

					if(data_b>=data_n)
						sql.insertUpdate(customer,id,amount);
					else 
					{
						JOptionPane.showMessageDialog(null, "U have not sufficient amount of product of ID :"+id+"");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
    			text2.setText("");
    			text3.setText("");
    			text4.setText("");
				
    	}
     if(event.getSource()==change_customer_id)
     {
    	 customer_id++;
    	 ChangeBuyerId();
    	 
     }
    			
	}
    
}
