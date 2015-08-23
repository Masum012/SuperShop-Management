import java.util.Vector;

import javax.swing.table.AbstractTableModel;


public class ItemTableModel extends AbstractTableModel{
	manageMysql sql=new manageMysql();
	Object[] array;
	Vector<String> v;
    Object[][] data;
     int j=0;
    public ItemTableModel(){
    	sql.createConnection();
    	v= sql.showItemInformation();
    	array=v.toArray();
    	data = new Object[(v.size())/6][8];
    	 System.out.println(v.size());
         for (int i = 0; i < (v.size())/6; i++) {
             data[i][0]= array[j++]; 
             data[i][1]=  array[j++];
             data[i][2]=  array[j++];
             data[i][3]=  array[j++];
             data[i][4]=  array[j++];
             data[i][5]=  array[j++];
            
        }
    }
   
    String[] s={"ID","Name","Category","Purchase Cost","Sales Price","Available"};

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        
        return 6;
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
