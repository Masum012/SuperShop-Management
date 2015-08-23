import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;


public class TransactionTableModel extends AbstractTableModel{
	private double a;
	manageMysql sql=new manageMysql();
	Object[] array;
	Vector<String> v;
    Object[][] data;
     int j=0;
    public TransactionTableModel(String s){
    	
    	sql.createConnection();
    	v= sql.sales_id_info(s);
    	array=v.toArray();
    	data = new Object[(v.size())/5][8];
    	 System.out.println(v.size());
         for (int i = 0; i < (v.size())/5; i++) {
             data[i][0]= array[j++]; 
             data[i][1]=  array[j++];
             data[i][2]=  array[j++];
             data[i][3]=  array[j++];
             double k=Double.parseDouble((String) data[i][3]);
             a=a+k;
             data[i][4]=  array[j++];
        }JOptionPane.showMessageDialog(null,"Total price: "+a);
    }
   public double getPrice()
   {
	   return a;
   }
    String[] s={"ID","Amount","price","price with vat","Date"};

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }
    @Override
    public String getColumnName(int i)
    {
    	return s[i];
    }
    
}
