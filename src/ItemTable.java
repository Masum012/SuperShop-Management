import java.awt.BorderLayout;


	public class ItemTable extends javax.swing.JDialog {
		private javax.swing.JButton jButton1;
	    private javax.swing.JScrollPane jScrollPane1;
	    private javax.swing.JTable jTable1;
	    
	    
	    public ItemTable() {
	        initComponents();
	        setSize(700,500);

	    }

	    private void initComponents() {
	    	jScrollPane1 = new javax.swing.JScrollPane();
	        jTable1 = new javax.swing.JTable();
	        jTable1.setFillsViewportHeight(true);
	        jScrollPane1.setViewportView(jTable1);  
	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
	        getContentPane().setLayout(new BorderLayout());
	        add(jScrollPane1);
	        jTable1.setModel(new ItemTableModel());
	        jTable1.setVisible(true);
	    }

	    
}



