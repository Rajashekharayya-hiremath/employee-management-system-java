package company;

import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class GetEmployeeFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtName;

	private JLabel lblNameResult;
	private JLabel lblAgeResult;
	private JLabel lblSalaryResult;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				GetEmployeeFrame frame = new GetEmployeeFrame();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public GetEmployeeFrame() {

		setTitle("Get Employee Details");
		setIconImage(new ImageIcon(getClass().getResource("jdbcicon.png")).getImage());
		setBounds(100,100,450,320);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("Search Employee");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitle.setBounds(150,20,200,20);
		contentPane.add(lblTitle);

		/* ---------- ID ---------- */

		JLabel lblId = new JLabel("Enter ID :");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblId.setBounds(60,70,100,20);
		contentPane.add(lblId);

		txtId = new JTextField();
		txtId.setBounds(150,70,140,22);
		contentPane.add(txtId);

		/* ---------- NAME ---------- */

		JLabel lblNameSearch = new JLabel("Enter Name :");
		lblNameSearch.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNameSearch.setBounds(60,100,100,20);
		contentPane.add(lblNameSearch);

		txtName = new JTextField();
		txtName.setBounds(150,100,140,22);
		contentPane.add(txtName);

		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(150,130,100,25);
		contentPane.add(btnSearch);

		JButton btnBack = new JButton("Back");
		btnBack.setBounds(320,240,100,25);
		contentPane.add(btnBack);

		/* ---------- RESULT LABELS ---------- */

		JLabel lblName = new JLabel("Name :");
		lblName.setBounds(60,180,80,20);
		contentPane.add(lblName);

		JLabel lblAge = new JLabel("Age :");
		lblAge.setBounds(60,210,80,20);
		contentPane.add(lblAge);

		JLabel lblSalary = new JLabel("Salary :");
		lblSalary.setBounds(60,240,80,20);
		contentPane.add(lblSalary);

		lblNameResult = new JLabel("----");
		lblNameResult.setBounds(150,180,140,20);
		contentPane.add(lblNameResult);

		lblAgeResult = new JLabel("----");
		lblAgeResult.setBounds(150,210,140,20);
		contentPane.add(lblAgeResult);

		lblSalaryResult = new JLabel("----");
		lblSalaryResult.setBounds(150,240,140,20);
		contentPane.add(lblSalaryResult);

		/* ---------- SEARCH LOGIC ---------- */

		btnSearch.addActionListener(e -> {

			String idText = txtId.getText().trim();
			String nameText = txtName.getText().trim();

			if(idText.isEmpty() && nameText.isEmpty()) {

				JOptionPane.showMessageDialog(null,"Enter ID or Name");
				return;

			}

			try {

				Connection con = DBConnection.getConnection();

				if(!idText.isEmpty()) {

					int id;

					try {
						id = Integer.parseInt(idText);
					}
					catch(NumberFormatException ex) {
						JOptionPane.showMessageDialog(null,"ID must be a number");
						return;
					}

					String query = "select * from employee where id=?";

					PreparedStatement ps = con.prepareStatement(query);
					ps.setInt(1,id);

					ResultSet rs = ps.executeQuery();

					if(rs.next()) {

						lblNameResult.setText(rs.getString("name"));
						lblAgeResult.setText(String.valueOf(rs.getInt("age")));
						lblSalaryResult.setText(String.valueOf(rs.getDouble("salary")));

					}
					else {

						JOptionPane.showMessageDialog(null,"Employee Not Found");

					}

				}

				else {

					String query = "select * from employee where name like ?";

					PreparedStatement ps = con.prepareStatement(query);
					ps.setString(1,"%"+nameText+"%");

					ResultSet rs = ps.executeQuery();

					if(rs.next()) {

						lblNameResult.setText(rs.getString("name"));
						lblAgeResult.setText(String.valueOf(rs.getInt("age")));
						lblSalaryResult.setText(String.valueOf(rs.getDouble("salary")));

					}
					else {

						JOptionPane.showMessageDialog(null,"Employee Not Found");

					}

				}

			}
			catch(Exception ex) {
				ex.printStackTrace();
			}

		});

		/* ---------- BACK ---------- */

		btnBack.addActionListener(e -> {

			MainFrame mf = new MainFrame();
			mf.setVisible(true);
			dispose();

		});

	}
}