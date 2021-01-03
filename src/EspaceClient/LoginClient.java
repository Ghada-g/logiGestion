package EspaceClient;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


	import java.awt.BorderLayout;

	import java.awt.EventQueue;

	import javax.swing.JFrame;
	import javax.swing.JPanel;
	import javax.swing.border.EmptyBorder;

	import java.awt.Color;

	import javax.swing.JLabel;
	import javax.swing.ImageIcon;
	import javax.swing.JOptionPane;
	import javax.swing.JTextField;

	import java.awt.Button;

	import javax.swing.JButton;
	import javax.swing.JPasswordField;

	import java.awt.event.ActionListener;
	import java.awt.event.ActionEvent;

	import javax.swing.JSeparator;

	import java.awt.Font;
	import java.awt.SystemColor;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.awt.event.MouseAdapter;
	import java.awt.event.MouseEvent;

	public class LoginClient extends JFrame {
		static Connection connection=null;
		java.sql.PreparedStatement prepared =null;
		
		static Statement st=null;

		private JPanel contentPane;
		private JTextField cin;
		private JPasswordField mdp;

		
		private ResultSet rs;
		private String sql;

		/**
		 * Launch the application.
		 */
		public static void main(String[] args) {
			
			//*********************************************************************************************************************************//
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

			
		
		//****************************************************************************************************************************************//
			
			
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						LoginClient frame = new LoginClient();
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
		public LoginClient(){
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 629, 512);
			contentPane = new JPanel();
			contentPane.setBackground(Color.WHITE);
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JPanel panel = new JPanel();
			panel.setBackground(Color.WHITE);
			panel.setBounds(53, 11, 528, 97);
			contentPane.add(panel);
			panel.setLayout(null);
			
			JLabel lblGestionDeFacturation = new JLabel("Gestion De Facturation");
			lblGestionDeFacturation.setBounds(107, 5, 364, 39);
			lblGestionDeFacturation.setFont(new Font("Times New Roman", Font.BOLD, 35));
			lblGestionDeFacturation.setForeground(SystemColor.textHighlight);
			panel.add(lblGestionDeFacturation);
			
			JLabel lblBienvenueDansLespace = new JLabel("Bienvenue dans l'espace Client ");
			lblBienvenueDansLespace.setForeground(SystemColor.activeCaption);
			lblBienvenueDansLespace.setFont(new Font("Tempus Sans ITC", Font.ITALIC, 25));
			lblBienvenueDansLespace.setBounds(107, 57, 480, 31);
			panel.add(lblBienvenueDansLespace);
			
			JLabel label = new JLabel("");
			label.setBackground(Color.WHITE);
			label.setBounds(0, 11, 81, 51);
			contentPane.add(label);
			label.setIcon(new ImageIcon("C:\\Users\\packard bell\\Desktop\\projet fdhila\\isima50.jpg"));
			
			cin = new JTextField();
			cin.setBounds(249, 181, 149, 20);
			contentPane.add(cin);
			cin.setColumns(10);
			
			JButton btnNewButton = new JButton("se connecter");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					///*****************************************

				    String cin1=cin.getText().toString();
			        String mdp1=mdp.getText().toString();

			
			    
			    String sql="Select cin_Agent,mdp_Agent from agent_administratif";
			    try{
			

			prepared = connection.prepareStatement(sql);
			    rs=prepared.executeQuery();
			    int i=0;
			 if(cin1.equals("") || mdp1.equals("")){
				  
		            
		        	JOptionPane.showConfirmDialog(null, "Remplissez les champs vides!!!");
		       
			 }else {
			    while(rs.next()) {
			
			        String cin=rs.getString("cin_Agent");
			        String mdp=rs.getString("mdp_Agent");
			       if(cin.equals(cin1) && mdp.equals(mdp1))
			       {   i=1;
			        	JOptionPane.showMessageDialog(null, "connetion reussite");
			        	//menuAgent f = new menuAgent();
						//f.setVisible(true);	
						}
	                            
			        
			        }
			       
			       
			      if(i==0)
			    	
			            
			        	JOptionPane.showConfirmDialog(null, "Vérifier vos paramétre");;
			       
			
			    }}
		
			catch(SQLException e2){
				e2.printStackTrace();
				
			}
			
			
			
			
		}

			
	});
			btnNewButton.setBounds(235, 359, 114, 23);
			contentPane.add(btnNewButton);
			
			mdp = new JPasswordField();
			mdp.setBounds(249, 246, 149, 20);
			contentPane.add(mdp);
			
			JLabel lblNewLabel = new JLabel("Nom utilisateur");
			lblNewLabel.setBounds(92, 184, 89, 14);
			contentPane.add(lblNewLabel);
			
			JLabel lblNewLabel_1 = new JLabel("Mot de passe");
			lblNewLabel_1.setBounds(92, 249, 89, 14);
			contentPane.add(lblNewLabel_1);
			
			JSeparator separator = new JSeparator();
			separator.setBounds(126, 323, 326, 2);
			contentPane.add(separator);
			
			JLabel lblInscriezvous = new JLabel("Inscriez-vous?");
			lblInscriezvous.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
	FormulaireClient obj =new FormulaireClient();
					
				//	obj.setVisible(true);
				//	obj.setLocationRelativeTo(null);
				}
			});
			
			lblInscriezvous.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
			lblInscriezvous.setForeground(new Color(0, 0, 255));
			lblInscriezvous.setBounds(259, 393, 100, 14);
			contentPane.add(lblInscriezvous);
			
			JLabel lblMotDePasse = new JLabel("Mot de passe oubli\u00E9");
			lblMotDePasse.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					
				//**************	
					indicePass obj =new indicePass();
				    obj.setVisible(true);		
				    obj.setLocationRelativeTo(null);}
			});
			lblMotDePasse.setFont(new Font("Tahoma", Font.ITALIC, 9));
			lblMotDePasse.setBounds(311, 268, 114, 14);
			contentPane.add(lblMotDePasse);
		}
	}
