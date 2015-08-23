import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.sun.xml.internal.ws.org.objectweb.asm.Label;



public class TransactionTable extends javax.swing.JDialog{
		private javax.swing.JButton jButton1;
	    private javax.swing.JScrollPane jScrollPane1;
	    private javax.swing.JTable jTable1;
    	private String d,n;
    	private TransactionTableModel tableModel;
    public TransactionTable(String  cat,String b) {
    	d=cat;
    	n=b;
        initComponents();
        setSize(700,500);
    }

    private void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton("Total Price");       
        jTable1.setFillsViewportHeight(true);
        jScrollPane1.setViewportView(jTable1);
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(null);//new BorderLayout()
        jScrollPane1.setBounds(0, 0, 700, 500);
        add(jScrollPane1);
        JLabel jlabel1 = new JLabel("Total Cost:");
        jlabel1.setFont(new Font("Verdana",1,20));
        jlabel1.setBounds(100,380,200,120);
        //add(jlabel1);
       // manageMysql sqlManageMysql = new manageMysql() ;
      //  String string=sqlManageMysql.getprice(d);
        JLabel  jlabel2=new JLabel(n);
        jlabel2.setBounds(300,380,200,120);
        jlabel2.setFont(new Font("Verdana",1,20));
       // add(jlabel2);
        jTable1.setModel(new TransactionTableModel(d));
//        double a=tableModel.getPrice();
//       // String xString=String.format("%f",a);
        
        jTable1.setVisible(true);
        pack();
    }

   
   
    
   
}
