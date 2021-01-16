package EspaceMagasin;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Commande{

	private JFrame frame;
	private JTextField code_article;
	private JTextField num_client;
	private JTextField quantité;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Commande window = new Commande();
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
	public Commande() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 352);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Commande");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setBounds(121, 26, 143, 52);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("CodeArticle");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_1.setBounds(64, 89, 103, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("NumClient");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_2.setBounds(64, 131, 90, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblQunatit = new JLabel("Qunatité");
		lblQunatit.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblQunatit.setBounds(64, 181, 59, 14);
		frame.getContentPane().add(lblQunatit);
		
		code_article = new JTextField();
		code_article.setBounds(177, 87, 86, 20);
		frame.getContentPane().add(code_article);
		code_article.setColumns(10);
		
		num_client = new JTextField();
		num_client.setBounds(178, 129, 86, 20);
		frame.getContentPane().add(num_client);
		num_client.setColumns(10);
		
		quantité = new JTextField();
		quantité.setBounds(177, 179, 86, 20);
		frame.getContentPane().add(quantité);
		quantité.setColumns(10);
		
		JButton btnZjouter = new JButton("Ajouter");
		btnZjouter.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnZjouter.setBounds(177, 250, 89, 37);
		frame.getContentPane().add(btnZjouter);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAnnuler.setBounds(297, 250, 89, 37);
		frame.getContentPane().add(btnAnnuler);
	}
}
