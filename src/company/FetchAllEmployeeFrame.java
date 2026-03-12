package company;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;

public class FetchAllEmployeeFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				FetchAllEmployeeFrame frame = new FetchAllEmployeeFrame();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public FetchAllEmployeeFrame() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("All Employees");
		setIconImage(new ImageIcon(getClass().getResource("jdbcicon.png")).getImage());
		setBounds(100, 100, 500, 350);
		setLocationRelativeTo(null);
		setResizable(false);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		/* -------- Title -------- */

		JLabel lblTitle = new JLabel("Fetch All Employees");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitle.setBounds(160, 15, 200, 20);
		contentPane.add(lblTitle);

		/* -------- Buttons -------- */

		JButton btnFetch = new JButton("Fetch All");
		btnFetch.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnFetch.setBounds(60, 55, 100, 25);
		contentPane.add(btnFetch);

		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnBack.setBounds(360, 270, 100, 25);
		contentPane.add(btnBack);

		/* -------- Table -------- */

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 100, 420, 150);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		/* -------- Table Header Style -------- */

		table.getTableHeader().setBackground(new Color(70,130,180));
		table.getTableHeader().setForeground(Color.WHITE);
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 12));

		/* -------- Alternate Row Colors -------- */

		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

			public Component getTableCellRendererComponent(
					JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {

				Component c = super.getTableCellRendererComponent(
						table, value, isSelected, hasFocus, row, column);

				if(!isSelected) {

					if(row % 2 == 0) {
						c.setBackground(new Color(240,248,255));
					}
					else {
						c.setBackground(Color.WHITE);
					}

				}

				return c;
			}

		});

		/* -------- Fetch Button Logic -------- */

		btnFetch.addActionListener(e -> {

			try {

				Connection con = DBConnection.getConnection();

				String query = "select * from employee";

				PreparedStatement ps = con.prepareStatement(query);

				ResultSet rs = ps.executeQuery();

				DefaultTableModel model = new DefaultTableModel();

				model.addColumn("ID");
				model.addColumn("Name");
				model.addColumn("Age");
				model.addColumn("Salary");

				while(rs.next()) {

					int id = rs.getInt("id");
					String name = rs.getString("name");
					int age = rs.getInt("age");
					double salary = rs.getDouble("salary");

					model.addRow(new Object[]{id,name,age,salary});

				}

				table.setModel(model);

			}
			catch(Exception ex) {
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