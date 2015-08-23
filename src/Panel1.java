import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;


class Panel1  extends JPanel implements ActionListener {
	private String s;
    private JPanel panel;
    private JLabel label1,label2;
    private Test test =new Test();
    private JButton item;
    private JComboBox category;
    private manageMysql sql=new manageMysql();
    private String string;
    private Object[] array;
    private Vector<String> vector=new Vector(); 
   	     public Panel1()
	     {
   	    	 sql.createConnection();
	    	 test.setLookAndFeel();
	    	 setLayout(new BorderLayout());
	    	 setBackground(new Color(0,0,0,0));
	    	 setLayout(null);
	    	 label1=new JLabel("Item Info: ");
	         label1.setBounds(200,110,200,120);
	         label1.setFont(new Font("Verdana",1,20));
	         
	         label2=new JLabel("Category Info: ");
	         label2.setBounds(200,160,250,120);
	         label2.setFont(new Font("Verdana",1,20));
	    	 add(label1);
	    	 add(label2);
	    	 item=new JButton("ITEM");
	    	 item.setBounds(460,150,200,50);
	 		 this.add(item);
	    	 this.setBounds(0,200, 900,500);
	 		 item.addActionListener(this);
	 		 try {
				vector=sql.categories();
			} catch (SQLException e) {
				e.printStackTrace();	
			}
	     	array = vector.toArray();
	     	category=new JComboBox(array);
	    	category.setBounds(460,200,200,40);

	     	category.addItemListener(
	     			new ItemListener()
	     			{
						@Override
						public void itemStateChanged(ItemEvent event) {
							if(event.getStateChange()==ItemEvent.SELECTED)
							{	     				

								s=(String) array[category.getSelectedIndex()];
								CategoryTable c=new CategoryTable(s);
								c.setVisible(true);
							}
						}
	     			});
	     	this.add(category);
	     }
   	  public void actionPerformed(ActionEvent event)
   	  {
  		if(event.getSource()==item)
  		{
  			ItemTable d=new ItemTable();
  			d.setVisible(true);
  		}
   	  }
   	 
}
    
    
