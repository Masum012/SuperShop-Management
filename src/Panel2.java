import java.awt.Color; 
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


class Panel2 extends JPanel implements ActionListener {
	private manageMysql sql= new manageMysql() ;
	private JButton okbutton ;
	private JTextField text1;
	private JTextField text2;
	private JTextField text3;
	private JTextField text4,text5;
	private JLabel jlabel1,jlabel2,jlabel3,jlabel4,jlabel5;
	
	
    public Panel2() 
    {
    	Test.setLookAndFeel();
    	this.setLayout(null);
    	this.setBackground( new Color(0,0,0,0));
        jlabel1 = new JLabel("Enter Name:");
        jlabel1.setFont(new Font("Verdana",1,20));
        jlabel1.setBounds(200,60,200,120);
        
        jlabel2=new JLabel("Enter Category:");
        jlabel2.setBounds(200,110,200,120);
        jlabel2.setFont(new Font("Verdana",1,20));
        
        jlabel3=new JLabel("Enter purchase cost:");
        jlabel3.setBounds(200,160,250,120);
        jlabel3.setFont(new Font("Verdana",1,20));
        
        jlabel4=new JLabel("Enter sales cost:");
        jlabel4.setFont(new Font("Verdana",1,20));
        jlabel4.setBounds(200,210,200,120);
        
        jlabel5=new JLabel("Enter amount:");
        jlabel5.setFont(new Font("Verdana",1,20));
        jlabel5.setBounds(200,260,200,120);
        
        okbutton=new JButton("OK");
        okbutton.setBounds(385,340,120,40);
        
        text1=new JTextField();
        text1.setFont(new Font("Tahoma",1,20));
        text1.setBounds(460,100,200,40);
       
       text2=new JTextField();
       text2.setFont(new Font("Tahoma",1,20));
       text2.setBounds(460,150,200,40);
       
       text3=new JTextField();
       text3.setFont(new Font("Tahoma",1,20));
       text3.setBounds(460,200,200,40);
       
       text4=new JTextField();
       text4.setFont(new Font("Tahoma",1,20));
       text4.setBounds(460,250,200,40);
       
       text5=new JTextField();
       text5.setFont(new Font("Tahoma",1,20));
       text5.setBounds(460,300,200,40);
        
       this.add(jlabel1); 
       this.add(text1);
       this.add(jlabel2);
       this.add(text2);
       this.add(jlabel3);
       this.add(text3);
       this.add(jlabel4);
       this.add(text4);
       this.add(text5);
       this.add(jlabel5);
       this.add(okbutton);

        
        okbutton.addActionListener(this);
        this.setBounds(0,200,900,500);
        
    }
    
    

	@Override
	public void actionPerformed(ActionEvent event)
	{
		sql.createConnection();               
		if(event.getSource()==okbutton)
		{
			//System.out.print("kkj");
			String name=text1.getText();	
			String category=text2.getText();	
			String cost=text3.getText();	                                              
			String sales_price=text4.getText();
			String quantity=text5.getText();

			sql.createConnection();
			sql.insertItem( name, category,cost,sales_price,quantity);
			text1.setText("");
			text2.setText("");
			text3.setText("");
			text4.setText("");
			text5.setText("");
		}
	}

}