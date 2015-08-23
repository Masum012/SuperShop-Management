
import java.io.ObjectInputStream.GetField;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;
import javax.swing.text.AbstractDocument;


public class CategoryTableModel extends AbstractTableModel{
	manageMysql sql=new manageMysql();
	Object[] array;
	Vector<String> v;
     Object[][] data;
     int j=0;
    public CategoryTableModel(String c){
    	sql.createConnection();
    	v= sql.showCategoryInformation(c);
    	array=v.toArray();
    	data = new Object[(v.size())/4][8];
    	 System.out.println(v.size());
         for (int i = 0; i < (v.size())/4; i++) {
             data[i][0]= array[j++]; 
             data[i][1]=  array[j++];
             data[i][2]=  array[j++];
             data[i][3]=  array[j++];
        }
    }
   String[] s={"Product ID","Product name","Purchase cost","Available"};
   

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        
        return 4;
    }
    @Override
    public String getColumnName(int i)
    {
    	return s[i];
    }
    
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }
    
}
