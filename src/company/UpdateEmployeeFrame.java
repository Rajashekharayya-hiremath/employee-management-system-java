package company;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class UpdateEmployeeFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtName;
	private JTextField txtAge;
	private JTextField txtSalary;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				UpdateEmployeeFrame frame = new UpdateEmployeeFrame();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public UpdateEmployeeFrame() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Update Employee");
		setIconImage(new ImageIcon(getClass().getResource("jdbcicon.png")).getImage());
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		setResizable(false);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("Update Employee");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitle.setBounds(150, 15, 200, 20);
		contentPane.add(lblTitle);

		JLabel lblId = new JLabel("Employee ID :");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblId.setBounds(60, 55, 100, 20);
		contentPane.add(lblId);

		txtId = new JTextField();
		txtId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtId.setBounds(170, 55, 140, 22);
		contentPane.add(txtId);

		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSearch.setBounds(170, 85, 100, 25);
		contentPane.add(btnSearch);

		JLabel lblName = new JLabel("Name :");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblName.setBounds(60, 130, 80, 20);
		contentPane.add(lblName);

		JLabel lblAge = new JLabel("Age :");
		lblAge.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAge.setBounds(60, 160, 80, 20);
		contentPane.add(lblAge);

		JLabel lblSalary = new JLabel("Salary :");
		lblSalary.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSalary.setBounds(60, 190, 80, 20);
		contentPane.add(lblSalary);

		txtName = new JTextField();
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtName.setBounds(170, 130, 140, 22);
		contentPane.add(txtName);

		txtAge = new JTextField();
		txtAge.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtAge.setBounds(170, 160, 140, 22);
		contentPane.add(txtAge);

		txtSalary = new JTextField();
		txtSalary.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtSalary.setBounds(170, 190, 140, 22);
		contentPane.add(txtSalary);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnUpdate.setBounds(170, 220, 100, 25);
		contentPane.add(btnUpdate);

		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnBack.setBounds(330, 220, 100, 25);
		contentPane.add(btnBack);

		/* -------- SEARCH WITH VALIDATION -------- */

		btnSearch.addActionListener(e -> {

			String idText = txtId.getText().trim();

			if(idText.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please enter Employee ID");
				return;
			}

			int id;

			try {
				id = Integer.parseInt(idText);
			}
			catch(NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Employee ID must be a number");
				return;
			}

			try {

				java.sql.Connection con = DBConnection.getConnection();

				String query = "select * from employee where id=?";

				java.sql.PreparedStatement ps = con.prepareStatement(query);

				ps.setInt(1, id);

				java.sql.ResultSet rs = ps.executeQuery();

				if(rs.next()) {

					txtName.setText(rs.getString("name"));
					txtAge.setText(String.valueOf(rs.getInt("age")));
					txtSalary.setText(String.valueOf(rs.getDouble("salary")));

					txtId.setEditable(false);

				}
				else {

					JOptionPane.showMessageDialog(null, "Employee Not Found");

					txtName.setText("");
					txtAge.setText("");
					txtSalary.setText("");

				}

			}
			catch(Exception ex) {
				ex.printStackTrace();
			}

		});

		/* -------- UPDATE WITH VALIDATION -------- */

		btnUpdate.addActionListener(e -> {

			String idText = txtId.getText().trim();
			String name = txtName.getText().trim();
			String ageText = txtAge.getText().trim();
			String salaryText = txtSalary.getText().trim();

			if(name.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Name cannot be empty");
				return;
			}

			int id;
			int age;
			double salary;

			try {
				id = Integer.parseInt(idText);
			}
			catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "Invalid Employee ID");
				return;
			}

			try {
				age = Integer.parseInt(ageText);
			}
			catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "Age must be a number");
				return;
			}

			try {
				salary = Double.parseDouble(salaryText);
			}
			catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "Salary must be a number");
				return;
			}

			try {

				java.sql.Connection con = DBConnection.getConnection();

				String query = "update employee set name=?, age=?, salary=? where id=?";

				java.sql.PreparedStatement ps = con.prepareStatement(query);

				ps.setString(1, name);
				ps.setInt(2, age);
				ps.setDouble(3, salary);
				ps.setInt(4, id);

				int result = ps.executeUpdate();

				if(result > 0) {
					JOptionPane.showMessageDialog(null, "Employee Updated Successfully");
				}
				else {
					JOptionPane.showMessageDialog(null, "Update Failed");
				}

			}
			catch(Exception ex) {
				ex.printStackTrace();
			}

		});

		btnBack.addActionListener(e -> {

			MainFrame mf = new MainFrame();
			mf.setVisible(true);
			dispose();

		});

	}
}