package company;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				MainFrame frame = new MainFrame();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public MainFrame() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Employee Management System");
		setIconImage(new ImageIcon(getClass().getResource("jdbcicon.png")).getImage());
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		setResizable(false);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		/* -------- Main Title -------- */

		JLabel lblTitle = new JLabel("Company");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitle.setBounds(175, 10, 120, 20);
		contentPane.add(lblTitle);

		/* -------- Labels -------- */

		JLabel lblInsert = new JLabel("Insert Employee :");
		lblInsert.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblInsert.setBounds(60, 50, 120, 20);
		contentPane.add(lblInsert);

		JLabel lblGet = new JLabel("Get Employee :");
		lblGet.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblGet.setBounds(60, 90, 120, 20);
		contentPane.add(lblGet);

		JLabel lblFetch = new JLabel("Fetch All Employee :");
		lblFetch.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFetch.setBounds(60, 130, 130, 20);
		contentPane.add(lblFetch);

		JLabel lblUpdate = new JLabel("Update Employee :");
		lblUpdate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblUpdate.setBounds(60, 170, 130, 20);
		contentPane.add(lblUpdate);

		JLabel lblDelete = new JLabel("Delete Employee :");
		lblDelete.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDelete.setBounds(60, 210, 130, 20);
		contentPane.add(lblDelete);

		/* -------- Buttons -------- */

		JButton btnInsert = new JButton("Insert");
		btnInsert.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnInsert.setBounds(210, 50, 100, 25);
		contentPane.add(btnInsert);

		btnInsert.addActionListener(e -> {
			InsertEmployeeFrame frame = new InsertEmployeeFrame();
			frame.setVisible(true);
			dispose();
		});

		JButton btnGet = new JButton("Get");
		btnGet.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnGet.setBounds(210, 90, 100, 25);
		contentPane.add(btnGet);

		btnGet.addActionListener(e -> {
			GetEmployeeFrame f = new GetEmployeeFrame();
			f.setVisible(true);
			dispose();
		});

		JButton btnFetch = new JButton("Fetch");
		btnFetch.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnFetch.setBounds(210, 130, 100, 25);
		contentPane.add(btnFetch);

		btnFetch.addActionListener(e -> {
			FetchAllEmployeeFrame f = new FetchAllEmployeeFrame();
			f.setVisible(true);
			dispose();
		});

		JButton btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnUpdate.setBounds(210, 170, 100, 25);
		contentPane.add(btnUpdate);

		btnUpdate.addActionListener(e -> {
			UpdateEmployeeFrame f = new UpdateEmployeeFrame();
			f.setVisible(true);
			dispose();
		});

		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnDelete.setBounds(210, 210, 100, 25);
		contentPane.add(btnDelete);

		btnDelete.addActionListener(e -> {
			DeleteEmployeeFrame f = new DeleteEmployeeFrame();
			f.setVisible(true);
			dispose();
		});

	}
}