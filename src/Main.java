import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import com.seaglasslookandfeel.SeaGlassLookAndFeel;


public class Main extends JFrame {
	private static JFrame frame;
	private static Image icon;
	private static JButton exit;
	//public static Paint imagePanel;
	public static void main(String[] args) {
		//Paint imagePanel ;
		 frame = new JFrame("Demo application");
		frame.setSize(900, 700);
		setLookAndFeel();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.getContentPane().add(comp)
		frame.setLayout(null);
		frame.setUndecorated(true);
		//frame.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
		frame.setContentPane(new JLabel(new ImageIcon(Main.class.getResource("masum.jpg"))));
		JPanel panel=new JPanel();
		exit=new JButton();
		exit.setBounds(870, -35, 30, 100);
		exit.setIcon(new ImageIcon(Main.class.getResource("exit-icon.png")));
		frame.add(exit);
		exit.addActionListener(new ActionListener() {
			
			@Override
			
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		panel.setBounds(0, 0, 900, 700);
		frame.add(panel);
		placeComponents(panel);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
		

}

	private static void placeComponents(JPanel panel) {
		

		final JTextField userText = new JTextField(20);
		 JLabel passwordLabel ;
		 final JPasswordField passwordText = new JPasswordField(20);
		panel.setLayout(null);
		
		JLabel userLabel = new JLabel("User :");
		userLabel.setFont(new Font("Verdana",1,20));
		userLabel.setBounds(200,260,200,120);
		panel.add(userLabel);
		
		userText.setFont(new Font("Tahoma",1,16));
		userText.setBounds(460,300,200,40);
		panel.add(userText);
		
        
       
		passwordLabel=new JLabel("Password :");
		passwordLabel.setBounds(200,310,200,120);
		passwordLabel.setFont(new Font("Verdana",1,20));
		panel.add(passwordLabel);
		
		passwordText.setFont(new Font("Tahoma",1,16));
		passwordText.setBounds(460,350,200,40);
		panel.add(passwordText);

		JButton loginButton = new JButton("login");
		loginButton.setBounds(400, 420, 80, 50);
		panel.add(loginButton);
		
		
		loginButton.addActionListener(new ActionListener() {
			
			@Override
			
			public void actionPerformed(ActionEvent arg0) {
				
				
				if(passwordText.getText().equals("") && userText.getText().equals(""))
				{
					new Test();
					frame.setVisible(false);
				}
				
				else 
				{
					JOptionPane.showMessageDialog(null,"Wrong Username or Password ", null, JOptionPane.ERROR_MESSAGE);
				}
			}
		});

}
	
	public static void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel(new SeaGlassLookAndFeel());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
