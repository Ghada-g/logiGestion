package EspaceClient;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.Color;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JSeparator;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class FormulaireClient extends JFrame {
	static Connection connection=null;
	java.sql.PreparedStatement prepared =null;
	
	static Statement st=null;

	private JPanel contentPane;
	private JTextField Nom_A;
	private JTextField Prenom_A;
	private JTextField CIN_A;
	private JPasswordField mdp_A;
	private JTextField ddn_A;

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
					FormulaireClient frame = new FormulaireClient();
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
	public FormulaireClient() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 574, 546);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 558, 508);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Nom");
		lblNewLabel_1.setBounds(31, 63, 46, 14);
		panel.add(lblNewLabel_1);
		
		Nom_A = new JTextField();
		Nom_A.setBounds(119, 60, 145, 20);
		panel.add(Nom_A);
		Nom_A.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Pr\u00E9nom");
		lblNewLabel.setBounds(31, 122, 46, 14);
		panel.add(lblNewLabel);
		
		JLabel lblCin = new JLabel("C.I.N");
		lblCin.setBounds(31, 173, 46, 14);
		panel.add(lblCin);
		
		Prenom_A = new JTextField();
		Prenom_A.setBounds(119, 119, 145, 20);
		panel.add(Prenom_A);
		Prenom_A.setColumns(10);
		
		CIN_A = new JTextField();
		CIN_A.setBounds(119, 170, 145, 20);
		panel.add(CIN_A);
		CIN_A.setColumns(10);
		
		JLabel lblMotDePasse = new JLabel("Mot de passe");
		lblMotDePasse.setBounds(12, 286, 98, 14);
		panel.add(lblMotDePasse);
		
		mdp_A = new JPasswordField();
		mdp_A.setBounds(119, 283, 145, 20);
		panel.add(mdp_A);
		
		JButton btnNewButton = new JButton("valider");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nom_a = Nom_A.getText().toString();
				String prenom_a = Prenom_A.getText().toString();
			    String cin_a = CIN_A.getText().toString();
				
				String date_naissance_a = ddn_A.getText().toString();
				String password_a = mdp_A.getText().toString();
				
				String sql ="Insert into  agent_administratif (nom_Agent,prenom_Agent,cin_Agent,matricule_Agent,ddn_Agent,mdp_Agent) values (?,?,?,?,?,?)";
				try{
					if (!Nom_A.equals("") && !Prenom_A.equals("") && !CIN_A.equals("")  && !ddn_A.equals("") && !mdp_A.equals("")){
				prepared = connection.prepareStatement(sql);
				
				prepared.setString(1, nom_a);
				
				prepared.setString(2, prenom_a);
				
				prepared.setString(3, cin_a);
				
				
				
				
				prepared.setString(5, date_naissance_a);
				
				prepared.setString(6, password_a);
				
				prepared.execute();
				
				Nom_A.setText("");
				Prenom_A.setText("");
				CIN_A.setText("");
				
				ddn_A.setText("");
				mdp_A.setText("");
					
					}
					else{
						JOptionPane.showConfirmDialog(null, "champ vide");
					}
				
				
				
				
				
				
				
				
				}
				catch(SQLException e1){
					e1.printStackTrace();
					
				
		}
				
				
				
				
			}
		});
		
		
		btnNewButton.setBounds(155, 443, 89, 23);
		panel.add(btnNewButton);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAnnuler.setBounds(325, 443, 89, 23);
		panel.add(btnAnnuler);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(119, 402, 295, 2);
		panel.add(separator);
		
		JLabel lblDateDeNaissance = new JLabel("date de naissance");
		lblDateDeNaissance.setBounds(12, 232, 110, 14);
		panel.add(lblDateDeNaissance);
		
		JLabel lblFormulairePourEtudiant = new JLabel("Formulaire Pour les Clients");
		lblFormulairePourEtudiant.setForeground(new Color(123, 104, 238));
		lblFormulairePourEtudiant.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		lblFormulairePourEtudiant.setBounds(102, 0, 339, 36);
		panel.add(lblFormulairePourEtudiant);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\machf\\OneDrive\\Desktop\\projet\\client2.jpg"));
		lblNewLabel_2.setBounds(308, 44, 238, 333);
		panel.add(lblNewLabel_2);
		
		ddn_A = new JTextField();
		ddn_A.setBounds(119, 229, 145, 20);
		panel.add(ddn_A);
		ddn_A.setColumns(10);
	}

}


