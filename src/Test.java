import java.awt.Color;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.seaglasslookandfeel.SeaGlassLookAndFeel;

public class Test{
	private Object[] name,item,category;
	private Vector<String> v;
	private manageMysql sql =new manageMysql(); 
	private static Panel1 p1=new Panel1() ;
	private static Panel2 p2=new Panel2();
	private static Panel3  p3=new Panel3();
	private static Panel4  p4=new Panel4();
	//private manageMysql sql =new manageMysql();
	private static int i;
	private static JPanel pl1;
	//private static Image icon;
	// private static Ads panel;
	private static JFrame f;
	private static JPanel panel;
	private JTextField field;
	private JButton search;
	public Test() {
		
		
		

		
		
		f = new JFrame();
		f.setSize(900, 700);
		setLookAndFeel();
		f.setLayout(null);
		f.setUndecorated(true);
		
		sql.createConnection();
		//f.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
		//f.setTitle("SUPERSHOP MANAGEMENT");
		
		
		f.setContentPane(new JLabel(new ImageIcon(Main.class.getResource("masum.jpg"))));
	    field = new JTextField(20);
	    JButton exit = new JButton();
	    
		exit.setBounds(870, -35, 30, 100);
		exit.setIcon(new ImageIcon(Main.class.getResource("exit-icon.png")));
		f.add(exit);
		exit.addActionListener(new ActionListener() {
			
			@Override
			
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
	    search=new JButton("Search");
	    search.setBounds(800, 10, 70, 30);
	    field.setBounds(600,10, 200, 30);
	    f.add(field);
	    f.add(search);
	     Window frame = null;
		AutoSuggestor autoSuggestor = new AutoSuggestor(field, f, null, Color.WHITE.brighter(), Color.black, Color.white, 0.75f) {
	            @Override
	            boolean wordTyped(String typedWord) {

	                //create list for dictionary this in your case might be done via calling a method which queries db and returns results as arraylist
	                ArrayList<String> words = new ArrayList<>();
	                try {
	                	int i=0;
						   v=sql.items();
						   v=sql.categories();
						   name=v.toArray();
							for(i=0;i<v.size();i++)
							{
								String s=(String)name[i];
								words.add(s);
							}
							
						} catch (SQLException e) {
						e.printStackTrace();
						}
	                
	                setDictionary(words);

	                return super.wordTyped(typedWord);//now call super to check for any matches against newest dictionary
	            }
		};

		panel = new JPanel();
		panel.setLayout(null);
		panel.setSize(900, 700);
		f.setLocationRelativeTo(null);
		JMenuBar m = new JMenuBar();
		JMenu about = new JMenu("About");
		JMenu help = new JMenu("help");
		m.add(about);
		m.add(help);
		///f.setJMenuBar(m);
		f.add(panel);
		pl1 = new JPanel();
		pl1.setBounds(0, 50, 900, 200);
		pl1.setBackground(new Color(0, 0, 0, 0));

		JButton button1 = new JButton("STOCK");
		JButton button2 = new JButton("INSERT IN SHOP");
		JButton button3 = new JButton("TRANSACTIONS");
		JButton button4 = new JButton("SELL");
		pl1.add(button1);
		pl1.add(button2);
		pl1.add(button3);
		pl1.add(button4);
		panel.add(pl1);
		search.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				v.clear();
				try {
					v=sql.items();
					item=v.toArray();
					for(int i=0;i<v.size();i++)
					{
						String p=(String)item[i];
						String s=field.getText().toString();
						s = s.replace(s.substring(s.length()-1), "");
						if(p.equals(s))
						{
							String itemn;
							itemn=sql.getItemInfo(p);
							//System.out.println(itemn);
							JOptionPane.showMessageDialog(null,"You have " +itemn+ " available" );
						}
					}
					v.clear();
					v=sql.categories();
					category=v.toArray();
					for(int i=0;i<v.size();i++)
					{
						String p=(String)category[i];
						String s=field.getText().toString();
						s = s.replace(s.substring(s.length()-1), "");
						
						if(p.equals(s))
						{
							new CategoryTable(s);
						}
					}
					
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		button1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				setI(1);
				refreshMe();
			}
		});
		button2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				setI(2);
				refreshMe();
			}
		});
		button3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setI(3);
				refreshMe();
			}
		});

		button4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setI(4);
				refreshMe();
			}
		});
		//f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.setResizable(false);

	}

	public JFrame getTest() {
		return f;

	}

	public static void setI(int j) {
		i = j;
	}

	public static void refreshMe() {

		checkPanel();

	}

	public static void checkPanel() {

		if (i == 1) {
			
			panel.add(p1);
			panel.remove(p2);
			panel.remove(p3);
			panel.remove(p4);
			panel.revalidate();
			panel.repaint();
		} else if (i == 2) {
			panel.add(p2);
			panel.remove(p1);
			panel.remove(p3);
			panel.remove(p4);
			panel.revalidate();
			panel.repaint();
		} else if (i == 3) {
			panel.add(p3);
			panel.remove(p1);
			panel.remove(p2);
			panel.remove(p4);
			panel.revalidate();
			panel.repaint();
		}else if (i == 4) {
			panel.add(p4);
			panel.remove(p1);
			panel.remove(p2);
			panel.remove(p3);
			panel.revalidate();
			panel.repaint();
		}
		
	}

//	public static void main(String[] args) {
//		new Test();
//	}

	public static void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel(new SeaGlassLookAndFeel());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
