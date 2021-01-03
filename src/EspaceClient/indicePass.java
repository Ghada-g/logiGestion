package EspaceClient;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



public class indicePass extends JFrame {
	static Connection connection=null;
	java.sql.PreparedStatement prepared =null;
	private ResultSet rs;
	private String sql;
	
	static Statement st=null;

	private JPanel contentPane;
	private JTextField UserName;
	private JTextField indicationField;
	private JTextField txtRevenirLa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		System.out.println("Bonjour");
		try {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e) {
		// TODO Auto-generated catch
		 e.printStackTrace();
		} catch (IllegalAccessException e) {
		// TODO Auto-generated catch
		 e.printStackTrace();
		} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		String url="jdbc:mysql://localhost:3306/gestionabsence";
	
		String login="root";
		String mdp="0000";
		try {
		connection=DriverManager.getConnection(url,login,mdp);
		System.out.println("Connection réussie...");
		} catch (SQLException e) {
		System.out.println("Connexion echoée....");
		e.printStackTrace();
		}
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					indicePass frame = new indicePass();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public indicePass() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 446, 475);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		UserName = new JTextField();
		UserName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				
				//***********
				String username=UserName.getText().toString();
				String sql= "select mdp_Agent from agent_administratif where cin_Agent=?";
				try {
					prepared= connection.prepareStatement(sql);
					
					prepared.setString(1, username);
					rs=prepared.executeQuery();
					if (rs.next()){
					
					String pass =rs.getString("mdp_Agent");
					String pass1 =pass.substring(0, 3);
					indicationField.setText("les 3 premiers lettres sont  " +pass1);}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
				
			
		});
		UserName.setBounds(162, 209, 168, 20);
		contentPane.add(UserName);
		UserName.setColumns(10);
		
		indicationField = new JTextField();
		indicationField.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		indicationField.setEditable(false);
		
		indicationField.setBounds(93, 277, 283, 33);
		contentPane.add(indicationField);
		indicationField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nom utilisateur");
		lblNewLabel.setBounds(23, 212, 104, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblProblmesDeConnexion = new JLabel("Probl\u00E8mes de connexion ?");
		lblProblmesDeConnexion.setFont(new Font("Tempus Sans ITC", Font.BOLD | Font.ITALIC, 25));
		lblProblmesDeConnexion.setForeground(SystemColor.textHighlight);
		lblProblmesDeConnexion.setBounds(72, 102, 323, 47);
		contentPane.add(lblProblmesDeConnexion);
		
		JLabel lblEntrezVotreNom = new JLabel("Entrez votre nom d\u2019utilisateur ");
		lblEntrezVotreNom.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		lblEntrezVotreNom.setForeground(SystemColor.activeCaption);
		lblEntrezVotreNom.setBounds(114, 149, 262, 14);
		contentPane.add(lblEntrezVotreNom);
		
		txtRevenirLa = new JTextField();
		txtRevenirLa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				LoginClient obj =new LoginClient();
			    obj.setVisible(true);		
			    obj.setLocationRelativeTo(null);
			}
		});
		txtRevenirLa.setForeground(SystemColor.textHighlight);
		txtRevenirLa.setFont(new Font("Tempus Sans ITC", Font.BOLD | Font.ITALIC, 25));
		txtRevenirLa.setText("     Revenir \u00E0 l'\u00E9cran de connexion");
		txtRevenirLa.setBounds(0, 393, 434, 33);
		contentPane.add(txtRevenirLa);
		txtRevenirLa.setColumns(10);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\packard bell\\Desktop\\projet fdhila\\loginmoq.PNG"));
		label.setBounds(146, -2, 168, 108);
		contentPane.add(label);
	}
}
