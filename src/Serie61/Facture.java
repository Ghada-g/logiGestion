package Serie61;

import java.awt.EventQueue;

import javax.swing.JFrame;


	import javax.swing.JLabel;
	import javax.swing.JOptionPane;

	import java.awt.Font;
	import java.awt.Color;
	import javax.swing.JTextField;
	import javax.swing.SwingConstants;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
	import java.awt.event.ActionListener;
	import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
	import javax.swing.JScrollPane;
	import javax.swing.JTable;
	import  java.sql.Connection ;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	public class Facture extends JFrame {

		private JFrame frame;
		private JTextField textField;
		private JTextField textField_1;
		private JTextField textField_2;
		private JTextField textField_3;
		private JTextField textField_4;
		private JTextField textField_5;
		
		Connection conn =null;
		PreparedStatement prepared =null;
		ResultSet resultat = null;
		private JTable table;

		/**
		 * Launch the application.
		 */
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Facture window = new Facture();
						window.frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}

		/**
		 * Create the application.
		 */
		public Facture() {
			initialize();
		}

		/**
		 * Initialize the contents of the frame.
		 */
		private void initialize() {
			frame = new JFrame();
			frame.getContentPane().setForeground(Color.BLUE);
			frame.getContentPane().setFont(new Font("Times New Roman", Font.ITALIC, 16));
			frame.setBounds(100, 100,800, 400);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setLayout(null);
			

			
			JLabel lblNumfacture = new JLabel("Num_facture");
			lblNumfacture.setForeground(Color.BLUE);
			lblNumfacture.setFont(new Font("Times New Roman", Font.ITALIC, 16));
			lblNumfacture.setBounds(41, 85, 185, 26);
			frame.getContentPane().add(lblNumfacture);
			
			JLabel lblDatefacture = new JLabel("Date_facture");
			lblDatefacture.setForeground(Color.BLUE);
			lblDatefacture.setFont(new Font("Times New Roman", Font.ITALIC, 16));
			lblDatefacture.setBounds(41, 122, 185, 28);
			frame.getContentPane().add(lblDatefacture);
			
			JLabel lblNewLabel = new JLabel("Num_client");
			lblNewLabel.setForeground(Color.BLUE);
			lblNewLabel.setFont(new Font("Times New Roman", Font.ITALIC, 16));
			lblNewLabel.setBounds(41, 151, 185, 32);
			frame.getContentPane().add(lblNewLabel);
			
			JLabel lblNewLabel_1 = new JLabel("Quantite");
			lblNewLabel_1.setForeground(Color.BLUE);
			lblNewLabel_1.setFont(new Font("Times New Roman", Font.ITALIC, 16));
			lblNewLabel_1.setBounds(41, 214, 185, 26);
			frame.getContentPane().add(lblNewLabel_1);
			
			JLabel lblNewLabel_2 = new JLabel("Prix");
			lblNewLabel_2.setForeground(Color.BLUE);
			lblNewLabel_2.setFont(new Font("Times New Roman", Font.ITALIC, 16));
			lblNewLabel_2.setBounds(41, 257, 185, 14);
			frame.getContentPane().add(lblNewLabel_2);
			
			JLabel lblNewLabel_3 = new JLabel("code_de_commande");
			lblNewLabel_3.setForeground(Color.BLUE);
			lblNewLabel_3.setFont(new Font("Times New Roman", Font.ITALIC, 16));
			lblNewLabel_3.setBounds(41, 181, 185, 26);
			frame.getContentPane().add(lblNewLabel_3);
			
			JLabel lblGestionDeFacturation = new JLabel("Gestion de facturation");
			lblGestionDeFacturation.setForeground(new Color(0, 139, 139));
			lblGestionDeFacturation.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
			lblGestionDeFacturation.setHorizontalAlignment(SwingConstants.CENTER);
			lblGestionDeFacturation.setBounds(270, 11, 244, 44);
			frame.getContentPane().add(lblGestionDeFacturation);
			
			textField = new JTextField();
			textField.setFont(new Font("Tahoma", Font.ITALIC, 12));
			textField.setForeground(new Color(192, 192, 192));
			textField.setBounds(189, 90, 155, 20);
			frame.getContentPane().add(textField);
			textField.setColumns(10);
			
			textField_1 = new JTextField();
			textField_1.setFont(new Font("Tahoma", Font.ITALIC, 12));
			textField_1.setForeground(new Color(192, 192, 192));
			textField_1.setBounds(189, 122, 155, 20);
			frame.getContentPane().add(textField_1);
			textField_1.setColumns(10);
			
			textField_2 = new JTextField();
			textField_2.setFont(new Font("Tahoma", Font.ITALIC, 12));
			textField_2.setForeground(new Color(192, 192, 192));
			textField_2.setBounds(189, 161, 155, 20);
			frame.getContentPane().add(textField_2);
			textField_2.setColumns(10);
			
			textField_3 = new JTextField();
			textField_3.setFont(new Font("Tahoma", Font.ITALIC, 12));
			textField_3.setForeground(new Color(192, 192, 192));
			textField_3.setBounds(189, 187, 155, 20);
			frame.getContentPane().add(textField_3);
			textField_3.setColumns(10);
			
			textField_4 = new JTextField();
			textField_4.setFont(new Font("Tahoma", Font.ITALIC, 12));
			textField_4.setForeground(new Color(192, 192, 192));
			textField_4.setBounds(189, 219, 155, 20);
			frame.getContentPane().add(textField_4);
			textField_4.setColumns(10);
			
			textField_5 = new JTextField();
			textField_5.setFont(new Font("Tahoma", Font.ITALIC, 12));
			textField_5.setForeground(new Color(192, 192, 192));
			textField_5.setBounds(189, 251, 155, 20);
			frame.getContentPane().add(textField_5);
			textField_5.setColumns(10);
			
			JButton btnCreation = new JButton("creation");
			btnCreation.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					String Num_facture= textField.getText();
	                String Date_facture= textField_1.getText();
	                String Num_client = textField_2.getText(); 
	                String code_de_commande = textField_3.getText();
	                String Quantite = textField_4.getText();
	                String Prix = textField_5.getText();
	                     

	                try {
	                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:8080/gestion_de_facturation", "root", "");

	String sql = "INSERT INTO facture values('" + Num_facture + "','" +Date_facture + "','"+ Num_client + "','" + code_de_commande+ "','" + Quantite +"','"+ Prix+"')";

	                    Statement sta = connection.createStatement();
					
					JOptionPane.showMessageDialog(null, "création de facture ");
					 connection.close();
					}catch(SQLException e) {
					
						e.printStackTrace();
					}
				}
			});
			btnCreation.setBounds(88, 308, 89, 23);
			frame.getContentPane().add(btnCreation);
			
			JButton btnModifier = new JButton("Modifier");
			btnModifier.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				
				String sql ="update facture set Prix= ? where Quantite = ?";
				try {
					Statement sta = conn.createStatement();
					prepared.setString(1,textField_5.getText());
					prepared.setString(2,textField_4.getText());
					prepared.execute();
					JOptionPane.showMessageDialog(null, " update de facture ");
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
				}
			});
			btnModifier.setBounds(215, 308, 89, 23);
			frame.getContentPane().add(btnModifier);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(393, 85, 357, 237);
			frame.getContentPane().add(scrollPane);
			
			table = new JTable();
			scrollPane.setViewportView(table);
			
			JButton btnActualise = new JButton("Actualise");
			btnActualise.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					UpdateTable();
				}
			});
			btnActualise.setBounds(619, 56, 89, 23);
			frame.getContentPane().add(btnActualise);
		}
		public void UpdateTable() {
			String sql = "select facture ";
			try {
				prepared =conn.prepareStatement(sql);
				resultat = prepared.executeQuery(); 
				table.setModel(DbUtils.resultSetToTableModel(resultat));		 
						
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}