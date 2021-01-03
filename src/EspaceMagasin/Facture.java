package EspaceMagasin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.SystemColor;

public class Facture {

	private JFrame frame;
	private JTextField txtFacture;
	private JTextField txtNumFacture;
	private JTextField txtNumClient;
	private JTextField txtDateFacture;
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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtFacture = new JTextField();
		txtFacture.setForeground(Color.GRAY);
		txtFacture.setFont(new Font("Sitka Subheading", Font.BOLD | Font.ITALIC, 19));
		txtFacture.setHorizontalAlignment(SwingConstants.CENTER);
		txtFacture.setText("Facture");
		txtFacture.setBounds(131, 11, 156, 29);
		frame.getContentPane().add(txtFacture);
		txtFacture.setColumns(10);
		
		txtNumFacture = new JTextField();
		txtNumFacture.setForeground(Color.PINK);
		txtNumFacture.setFont(new Font("Sitka Small", Font.ITALIC, 13));
		txtNumFacture.setHorizontalAlignment(SwingConstants.CENTER);
		txtNumFacture.setText("Num Facture");
		txtNumFacture.setBounds(24, 62, 102, 20);
		frame.getContentPane().add(txtNumFacture);
		txtNumFacture.setColumns(10);
		
		txtNumClient = new JTextField();
		txtNumClient.setForeground(Color.PINK);
		txtNumClient.setFont(new Font("Sitka Small", Font.ITALIC, 13));
		txtNumClient.setHorizontalAlignment(SwingConstants.CENTER);
		txtNumClient.setText("Num Client");
		txtNumClient.setBounds(150, 62, 86, 20);
		frame.getContentPane().add(txtNumClient);
		txtNumClient.setColumns(10);
		
		txtDateFacture = new JTextField();
		txtDateFacture.setForeground(Color.PINK);
		txtDateFacture.setFont(new Font("Sitka Small", Font.ITALIC, 13));
		txtDateFacture.setText("Date Facture");
		txtDateFacture.setHorizontalAlignment(SwingConstants.CENTER);
		txtDateFacture.setBounds(258, 62, 122, 20);
		frame.getContentPane().add(txtDateFacture);
		txtDateFacture.setColumns(10);
		
		JButton btnImprimer = new JButton("Imprimer");
		btnImprimer.setFont(new Font("Sitka Small", Font.ITALIC, 13));
		btnImprimer.setBounds(291, 202, 102, 23);
		frame.getContentPane().add(btnImprimer);
		
		table = new JTable();
		table.setBounds(34, 93, 359, 98);
		frame.getContentPane().add(table);
	}
}
