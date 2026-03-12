package company;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class InsertEmployeeFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtAge;
	private JTextField txtSalary;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				InsertEmployeeFrame frame = new InsertEmployeeFrame();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public InsertEmployeeFrame() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Insert Employee");
		setIconImage(new ImageIcon(getClass().getResource("jdbcicon.png")).getImage());
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		setResizable(false);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		/* -------- Title -------- */

		JLabel lblTitle = new JLabel("Insert Employee");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitle.setBounds(150, 15, 180, 20);
		contentPane.add(lblTitle);

		/* -------- Labels -------- */

		JLabel lblName = new JLabel("Name :");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblName.setBounds(70, 60, 80, 20);
		contentPane.add(lblName);

		JLabel lblAge = new JLabel("Age :");
		lblAge.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAge.setBounds(70, 100, 80, 20);
		contentPane.add(lblAge);

		JLabel lblSalary = new JLabel("Salary :");
		lblSalary.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSalary.setBounds(70, 140, 80, 20);
		contentPane.add(lblSalary);

		/* -------- Text Fields -------- */

		txtName = new JTextField();
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtName.setBounds(160, 60, 140, 22);
		contentPane.add(txtName);

		txtAge = new JTextField();
		txtAge.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtAge.setBounds(160, 100, 140, 22);
		contentPane.add(txtAge);

		txtSalary = new JTextField();
		txtSalary.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtSalary.setBounds(160, 140, 140, 22);
		contentPane.add(txtSalary);

		/* -------- Buttons -------- */

		JButton btnInsert = new JButton("Insert");
		btnInsert.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnInsert.setBounds(160, 180, 100, 25);
		contentPane.add(btnInsert);

		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnBack.setBounds(320, 220, 100, 25);
		contentPane.add(btnBack);

		/* -------- Insert Logic -------- */

		btnInsert.addActionListener(e -> {

			String name = txtName.getText();
			String ageText = txtAge.getText();
			txtAge.addKeyListener(new java.awt.event.KeyAdapter() {
			    public void keyTyped(java.awt.event.KeyEvent evt) {

			        char c = evt.getKeyChar();

			        if(!Character.isDigit(c)) {
			            evt.consume();
			        }

			    }
			});
			String salaryText = txtSalary.getText();
			txtSalary.addKeyListener(new java.awt.event.KeyAdapter() {
			    public void keyTyped(java.awt.event.KeyEvent evt) {

			        char c = evt.getKeyChar();

			        if(!(Character.isDigit(c) || c == '.')) {
			            evt.consume();
			        }

			    }
			});

			if(ValidationUtil.isEmpty(name)) {
			    JOptionPane.showMessageDialog(null, "Name cannot be empty");
			    return;
			}

			if(!ValidationUtil.isInteger(ageText)) {
			    JOptionPane.showMessageDialog(null, "Age must be a number");
			    return;
			}

			if(!ValidationUtil.isDouble(salaryText)) {
			    JOptionPane.showMessageDialog(null, "Salary must be a number");
			    return;
			}

			int age = Integer.parseInt(ageText);
			double salary = Double.parseDouble(salaryText);

		    try {

		        Connection con = DBConnection.getConnection();

		        String query = "insert into employee(name,age,salary) values(?,?,?)";

		        PreparedStatement ps = con.prepareStatement(query);

		        ps.setString(1, name);
		        ps.setInt(2, age);
		        ps.setDouble(3, salary);

		        int result = ps.executeUpdate();

		        if(result > 0) {

		            JOptionPane.showMessageDialog(null, "Employee Inserted Successfully");

		            txtName.setText("");
		            txtAge.setText("");
		            txtSalary.setText("");
		        }

		    } catch(Exception ex) {
		        ex.printStackTrace();
		    }

		});

		/* -------- Back Button -------- */

		btnBack.addActionListener(e -> {

			MainFrame mf = new MainFrame();
			mf.setVisible(true);
			dispose();

		});

	}
}