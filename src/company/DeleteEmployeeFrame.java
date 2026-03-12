package company;

import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class DeleteEmployeeFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtId;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				DeleteEmployeeFrame frame = new DeleteEmployeeFrame();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public DeleteEmployeeFrame() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Delete Employee");
		setIconImage(new ImageIcon(getClass().getResource("jdbcicon.png")).getImage());
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		setResizable(false);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("Delete Employee");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitle.setBounds(150, 20, 180, 20);
		contentPane.add(lblTitle);

		JLabel lblId = new JLabel("Employee ID :");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblId.setBounds(70, 90, 100, 20);
		contentPane.add(lblId);

		txtId = new JTextField();
		txtId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtId.setBounds(170, 90, 140, 22);
		contentPane.add(txtId);

		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnDelete.setBounds(170, 130, 100, 25);
		contentPane.add(btnDelete);

		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnBack.setBounds(330, 220, 100, 25);
		contentPane.add(btnBack);

		/* -------- Delete Logic with Validation -------- */

		btnDelete.addActionListener(e -> {

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

			int confirm = JOptionPane.showConfirmDialog(
					null,
					"Are you sure you want to delete this employee?",
					"Confirm Delete",
					JOptionPane.YES_NO_OPTION);

			if(confirm == JOptionPane.YES_OPTION) {

				try {

					Connection con = DBConnection.getConnection();

					String query = "delete from employee where id=?";

					PreparedStatement ps = con.prepareStatement(query);

					ps.setInt(1, id);

					int result = ps.executeUpdate();

					if(result > 0) {
						JOptionPane.showMessageDialog(null, "Employee Deleted Successfully");
						txtId.setText("");
					}
					else {
						JOptionPane.showMessageDialog(null, "Employee Not Found");
					}

				}
				catch(Exception ex) {
					ex.printStackTrace();
				}

			}

		});

		btnBack.addActionListener(e -> {

			MainFrame mf = new MainFrame();
			mf.setVisible(true);
			dispose();

		});

	}
}