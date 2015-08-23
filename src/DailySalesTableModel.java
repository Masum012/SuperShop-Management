
import java.io.ObjectInputStream.GetField;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;
import javax.swing.text.AbstractDocument;


public class DailySalesTableModel extends AbstractTableModel{
	manageMysql sql=new manageMysql();
	
	Object[] array;
	Vector<String> v;
    //String[] name = {"Aziz","masum","tanim","opu","golap"};
    int roll = 1207001;
    Object[][] data;
     int j=0;
    public DailySalesTableModel(String date){
    	sql.createConnection();
    	v= sql.dailyTransaction(date);
    	array=v.toArray();
    	data = new Object[(v.size())/7][8];
    	 System.out.println(v.size());
         for (int i = 0; i < (v.size())/7; i++) {
             data[i][0]=  array[j++]; 
             data[i][1]=  array[j++];
             data[i][2]=  array[j++];
             data[i][3]=  array[j++];
             data[i][4]=  array[j++];
             data[i][5]=  array[j++];
             data[i][6]=  array[j++];
             data[i][7]=  date;
        }
    }
   String[] s={"Customer ID","Product ID","Amount","Total sales","T_S_Vat","Profit","Time","Date"};
   

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        
        return 8;
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
