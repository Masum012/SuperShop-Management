import java.awt.BorderLayout;



public class CategoryTable extends javax.swing.JDialog{
	 private javax.swing.JButton jButton1;
	    private javax.swing.JScrollPane jScrollPane1;
	    private javax.swing.JTable jTable1;
    	private String d;
    public CategoryTable(String  cat) {
    	d=cat;
        initComponents();
        setSize(700,500);
    }

    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();       
        jTable1.setFillsViewportHeight(true);
        jScrollPane1.setViewportView(jTable1);

//        jButton1.setText("jButton1");
//        jButton1.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                jButton1ActionPerformed(evt);
//            }
//        });
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(new BorderLayout());
        add(jScrollPane1);
        jTable1.setModel(new CategoryTableModel(d));

        jTable1.setVisible(true);
          //add(jButton1, BorderLayout.SOUTH);
        pack();
    }

   
   
    
   
}
