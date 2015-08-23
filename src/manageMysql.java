import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.JOptionPane;

import com.itextpdf.text.DocumentException;



public class manageMysql
{
	private String username="root";
	private String password="";
	private String url = "jdbc:mysql://localhost/salesManagement";//

	private Connection myConnection = null;
	private Statement myStatement = null;
	private ResultSet myResultset = null;
	private Vector<String> v=new Vector();
	private String SQL[] = new String[6];
//	public void ConnectionManager()
//	{
//		SQL[0]  =  "CREATE  salesmanagement;";
//		SQL[1]  =  "USE salesmanagement;";
//		SQL[2]  =  "DROP TABLE IF EXISTS `batch`;";
//		SQL[3]  =  "CREATE TABLE IF NOT EXISTS `updates` ( `product_id` int(10) NOT NULL,  `amount` int(10) NOT NULL, `sales_price` int(10) NOT NULL, `total_sales` int(10) NOT NULL, `t_s_vat` int(5) NOT NULL,  `profit` int(5) NOT NULL,  `date` varchar(20) NOT NULL,  `time` varchar(20) NOT NULL) ;";
//		SQL[4]  =  "CREATE TABLE IF NOT EXISTS `item` (`product_id` int(10) NOT NULL AUTO_INCREMENT,`name` varchar(50) NOT NULL,  `category` varchar(50) NOT NULL, `cost` int(10) NOT NULL, `available` int(10) NOT NULL,  PRIMARY KEY (`product_id`));";
//		SQL[5]  =  "USE salesmanagement";
//	}
	public void createConnection()
	{
		//ConnectionManager();
		//boolean flag = true;
		if(myConnection==null)
		{
				try
				{
				int NumberOfRows,Response;
				myConnection = DriverManager.getConnection(url, username,password);
				myStatement = myConnection.createStatement();	
				myResultset = myStatement.executeQuery("SHOW DATABASES LIKE 'salesmanagement'");
				if(!myResultset.next())
				   for(String sql: SQL)
				 		{
					   myStatement.executeUpdate(sql);
				 		}
				else
				  {
					myStatement.executeUpdate("USE salesmanagement;");
					myResultset = myStatement.executeQuery("SHOW TABLES");
	
				  }
				//myConnection = DriverManager.getConnection(url,username,password);
				//JOptionPane.showMessageDialog(null, "Connected Successfully");
			}
			catch (SQLException e)
			{
				JOptionPane.showMessageDialog(null,"Error Code("+e.getErrorCode()+") : "+ e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
				//flag = false;
				
			}
		}
		//return flag;
	}




	public void closeConnection()
	{
		try
		{
			if(myConnection!=null)
			{
				myConnection.close();
				//JOptionPane.showMessageDialog(null, "Connection closed successfully.");
			}
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null,"Error Code("+e.getErrorCode()+") : "+ e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	
	
	public void insertItem( String name, String category,String cost,String salescost ,String available)
	{
		int i;
		String avail,avail_new,Cost;
		int effectedRow = 0;
		try
		{
			//createConnection();
			Vector vector=items();
			Vector price = null;
			Object[] item;
	        item=vector.toArray();
	    	for(i=0;i<vector.size();i++)
	    	{
	    		
	    		if(name.equalsIgnoreCase((String) item[i]))
	    		{
	    			avail=Avail(name);
		    		int a;
		    		a=Integer.parseInt(available)+Integer.parseInt(avail);
		    		avail_new=String.valueOf(a);
	    		//	myStatement = myConnection.createStatement();
//	    			myResultset = myStatement.executeQuery("SELECT  cost FROM item WHERE name='"+(String) item[i]+"';");
//		    		myResultset.previous();
//		    		while(myResultset.next())
//		    		{
//		    			String p_cost=myResultset.getObject(1).toString();
//		    			price.add(p_cost);
//		    		}
//		    		Object dam[];
//		    		dam=price.toArray();
//		    		for(int i1=0;i1<price.size();i1++)
//		    		{
//		    			if(cost.equals(dam[i1]))
//		    			{
//		    				
//		    			}
//		    		}
		    		
		    		
		    		effectedRow =myStatement.executeUpdate("UPDATE item SET available = '"+avail_new+"', WHERE name='"+name+"';");
		    		
	    			return;
	    		}
	    			
	    	}
	    	if(i==vector.size())
	    	{
	    		myStatement = myConnection.createStatement();
	    		effectedRow = myStatement.executeUpdate("INSERT INTO  item(name,category,cost,sales_price,available) VALUES('"+name+"','"+category+"','"+cost+"','"+salescost+"','"+available+"');");

	    	}

				if(effectedRow>0)
				JOptionPane.showMessageDialog(null,"Data inserted");
		}
		catch (SQLException e)
		{	    		
//System.err.println(e.getMessage());
			JOptionPane.showMessageDialog(null,"Error Code("+e.getErrorCode()+") : "+ e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public String Avail(String name)
	{
		String available = null;
		try {
			
		//createConnection();
		myStatement = myConnection.createStatement();
		myResultset = myStatement.executeQuery("SELECT  available FROM item WHERE name='"+name+"';");
		
			myResultset.previous();
			while(myResultset.next())
		{
			available = myResultset.getObject(1).toString();
		}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return available;
	}
	
	
	public Vector<String> items() throws SQLException
	{
		//createConnection();
		myStatement = myConnection.createStatement();
		myResultset = myStatement.executeQuery("SELECT DISTINCT name FROM item ;");
		String name;
		myResultset.previous();
		
			System.out.println("All available information: ");
			while(myResultset.next())
			{
				name=myResultset.getObject(1).toString();
				v.add(name);
			}
	
		//closeConnection();
		return v;
	}
	
	
	
	public Vector<String> categories() throws SQLException
	{
		createConnection();
		myStatement = myConnection.createStatement();
		myResultset = myStatement.executeQuery("SELECT DISTINCT category FROM item ;");
		String category;
		myResultset.previous();
		
			System.out.println("All available information: ");
			while(myResultset.next())
			{
				category=myResultset.getObject(1).toString();
				v.add(category);
			}
	
		//closeConnection();
		return v;
	}
	
	public String amount(String id)
	
	{
		String  Amount=null;
		createConnection();
		
		try {
			myStatement = myConnection.createStatement();
			myResultset = myStatement.executeQuery("SELECT available FROM item WHERE product_id='"+id+"';");
			myResultset.previous();
			
			while(myResultset.next())
			{
				Amount=myResultset.getObject(1).toString();
				//System.out.println(Amount);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Amount;
			
	}
	
	

	public Vector<String> showCategoryInformation(String category)
	{
		try
		{
			//createConnection();
			myStatement = myConnection.createStatement();
			myResultset = myStatement.executeQuery("SELECT product_id,name,cost,available FROM item WHERE category='"+category+"';");
			String id, Name, cost,available;
			myResultset.previous();
			System.out.println("All available information: ");
			while(myResultset.next())
			{
				id = myResultset.getObject(1).toString();
				v.add(id);
				Name = myResultset.getObject(2).toString();
				v.add(Name);
				cost = myResultset.getObject(3).toString();
				v.add(cost);
				available = myResultset.getObject(4).toString();
				v.add(available);
				//System.out.println(id+"  "+Name+"  "+category+"  "+cost);
			}
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null,"Error Code("+e.getErrorCode()+") : "+ e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
		}
		//closeConnection();
		return v;
	}
	
	
	
	
	public Vector<String> showItemInformation()
	{
		try
		{
			//createConnection();
			myStatement = myConnection.createStatement();
			myResultset = myStatement.executeQuery("SELECT * FROM item;");

			String id, Name, category,cost,salesPrice,available;
			myResultset.previous();
			System.out.println("All available information: ");
			while(myResultset.next())
			{
				id = myResultset.getObject(1).toString();
				v.add(id);
				Name = myResultset.getObject(2).toString();
				v.add(Name);

				category = myResultset.getObject(3).toString();
				v.add(category);

				cost = myResultset.getObject(4).toString();
				v.add(cost);
				
				salesPrice=myResultset.getObject(5).toString();
				v.add(salesPrice);
				
				available =myResultset.getObject(6).toString();
				v.add(available);

				//System.out.println(id+"  "+Name+"  "+category+"  "+cost);
			}
			//closeConnection();
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null,"Error Code("+e.getErrorCode()+") : "+ e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
		}
		return v;
	}
	

	public void updateAvailable(String available,String id)
	{
		try
		{
			//createConnection();
			myStatement = myConnection.createStatement();
			int effectedRow = myStatement.executeUpdate("UPDATE item SET available = '"+available+"'WHERE product_id='"+id+"';");
			if(effectedRow>0)
				JOptionPane.showMessageDialog(null,"Data updated");
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null,"Error Code("+e.getErrorCode()+") : "+ e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
		}
		//closeConnection();
	}


	




	
	
		
	
	public void insertUpdate(String customer_id,String id,String amount) throws SQLException 
	{
		try
		{
			createConnection();
		myStatement = myConnection.createStatement();
		myResultset = myStatement.executeQuery("SELECT sales_price FROM item WHERE product_id='"+id+"';");
		String  sales_price = null;
		myResultset.previous();
		while(myResultset.next())
		{
			sales_price = myResultset.getObject(1).toString();
		}
		String pre_amount;

		float total=(int) (Integer.parseInt(amount)*Float.parseFloat(sales_price));
		String total_sales= String.format("%f",total);
		float total_sales_with_vat=(float) (Float.parseFloat(total_sales)+Float.parseFloat(total_sales)*.15);
		String t_s_vat=String.format("%f",total_sales_with_vat);
		myStatement = myConnection.createStatement();
		myResultset = myStatement.executeQuery("SELECT cost FROM item WHERE product_id='"+id+"';");
		String  cost = null;
		myResultset.previous();
		while(myResultset.next())
		{
			cost = myResultset.getObject(1).toString();
		}
		
		String profit;
		String date;
		String time;
		double total_profit=Double.parseDouble(total_sales)- Integer.parseInt(amount)*Double.parseDouble(cost);
		profit=	String.format("%f",total_profit);
		time = new SimpleDateFormat("HH:mm:ss").format(new Date());
		date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

		pre_amount=amount(id);
		int Amount=Integer.parseInt(pre_amount)-Integer.parseInt(amount);
		String new_amount=String.valueOf(Amount);
		updateAvailable(new_amount, id);
		myStatement = myConnection.createStatement();
			int effectedRow = myStatement.executeUpdate("INSERT INTO  updates (Customer_id,product_id,amount,total_sales,t_s_vat,profit,date,time) VALUES('"+customer_id+"','"+id+"','"+amount+"','"+total_sales+"','"+t_s_vat+"','"+profit+"','"+date+"','"+time+"');");
			if(effectedRow>0)
			JOptionPane.showMessageDialog(null,"Data inserted");
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null,"Error Code("+e.getErrorCode()+") : "+ e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
		}
		//closeConnection();

	}
	
	public String customer() throws SQLException
	{
		
		createConnection();
		myStatement = myConnection.createStatement();
		myResultset = myStatement.executeQuery("SELECT DISTINCT Customer_id FROM updates ;");
		String id = null,c_id;
		myResultset.previous();
		
			System.out.println("All available information: ");
			while(myResultset.next())
			{
				c_id=myResultset.getObject(1).toString();
				id=c_id;
			}
	
		//closeConnection();
		return id;
	}
	
	
	public Vector<String> dailyTransaction(String date)
	{
		try
		{
			//createConnection();
			myStatement = myConnection.createStatement();
			myResultset = myStatement.executeQuery("SELECT * FROM updates WHERE date='"+date+"';");

			String id, amount, sales_price,total_sales,t_s_vat,profit,time;
			myResultset.previous();
			System.out.println("All available information: ");
			while(myResultset.next())
			{
				id = myResultset.getObject(1).toString();
				v.add(id);
				amount = myResultset.getObject(2).toString();
				v.add(amount);
				sales_price = myResultset.getObject(3).toString();
				v.add(sales_price);
				total_sales = myResultset.getObject(4).toString();
				v.add(total_sales);
				t_s_vat=myResultset.getObject(5).toString();
				v.add(t_s_vat);
				profit=myResultset.getObject(6).toString();
				v.add(profit);
				time=myResultset.getObject(8).toString();
				v.add(time);
				System.out.println(id+"   "+amount+"   "+sales_price+"   "+total_sales+"   "+t_s_vat+"   "+profit+"   "+time+"   "+date);
			}
			//closeConnection();
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null,"Error Code("+e.getErrorCode()+") : "+ e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
		}
		return v;
	}
	
	public Vector<String> sales_id() throws SQLException
	{
		createConnection();
		myStatement = myConnection.createStatement();
		myResultset = myStatement.executeQuery("SELECT DISTINCT Customer_id FROM updates ;");
		String id;
		myResultset.previous();
		
			//System.out.println("All available information: ");
			while(myResultset.next())
			{
				id=myResultset.getObject(1).toString();
				v.add(id);
			}
	
		//closeConnection();
		return v;
	}
	
	
	public Vector<String> sales_id_info(String id) 
	{
		Vector <String> vec= new Vector();
		try {
			vec.clear();
			myStatement = myConnection.createStatement();
			myResultset = myStatement.executeQuery("SELECT *  FROM updates WHERE  Customer_id='"+id+"';");
		myResultset.previous();
		while(myResultset.next())
			{
				String p_id=myResultset.getObject(2).toString();
				vec.add(p_id);
				String amount=myResultset.getObject(3).toString();
				vec.add(amount);
				String total=myResultset.getObject(4).toString();
				vec.add(total);
				String total_vat=myResultset.getObject(5).toString();
				vec.add(total_vat);
				String date=myResultset.getObject(7).toString();
				vec.add(date);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vec;
}
        
			//System.out.println("All available information: ");
		//	Pdf.pdf();
			
		

	
	
	public String getItemInfo(String name)  {
		String s = null;
		try {
			myStatement = myConnection.createStatement();
			myResultset = myStatement.executeQuery("SELECT available FROM item WHERE name='"+name+"';");
			myResultset.previous();
			while (myResultset.next()) {
				s=myResultset.getObject(1).toString();
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		return s;
		
	}
	
	public String getprice(String id )
	{
		String s = null;
		String a=null;
		double x=0;
		try {
			createConnection();
			myStatement = myConnection.createStatement();System.out.println("erer");
			myResultset = myStatement.executeQuery("SELECT t_s_vat FROM updates WHERE name='"+id+"';");
			myResultset.previous();
			
			while (myResultset.next()) {
			a=myResultset.getObject(1).toString();
				x=x+Double.parseDouble(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		s=String.format("%f",x);
		return s;
	}
	
	

}

