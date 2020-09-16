package swinRestaurant;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import swinRestaurant.Waiter.WaiterClient;

public class Waiter extends Thread {
	private JLabel lblNewLabel_2;
	private HashMap<Integer, HashMap<String, HashSet<String>>> hs = new HashMap<Integer, HashMap<String, HashSet<String>>>();
	private HashSet<String> hs1 = new HashSet<String>();
	private JTextField textField;
	private JTextField textField_1;
	private String custName = "";
	private String tableno = "";
	@SuppressWarnings("unused")
	private boolean lunch = false;
	@SuppressWarnings("unused")
	private boolean breakFast = false;
	@SuppressWarnings("unused")
	private boolean dinner = false;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox;
	private JLabel lblNewLabel_3;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox_1;
	private JTable table;
	private JTable table_1;
	private JButton btnPrepare;
	private JButton btnClearDisplay;

	@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
	protected Waiter() throws Exception {
		WaiterClient wc = new WaiterClient();
		wc.start();
		JFrame jf = new JFrame("Restaurant Order Waiter");
		jf.setBounds(100, 100, 972, 702);
		jf.getContentPane().setLayout(null);
		JPanel panel = new JPanel();
		panel.setBounds(0, 11, 955, 83);
		panel.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 1), "Customer Details", TitledBorder.LEADING,
				TitledBorder.TOP, null, Color.BLACK));
		jf.getContentPane().add(panel);
		panel.setLayout(null);

		textField = new JTextField();
		textField.setBounds(115, 26, 191, 20);
		panel.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Customer Name");
		lblNewLabel.setBounds(20, 29, 85, 14);
		panel.add(lblNewLabel);

		textField_1 = new JTextField();
		textField_1.setBounds(412, 26, 174, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Table Number");
		lblNewLabel_1.setBounds(316, 29, 86, 14);
		panel.add(lblNewLabel_1);

		JLabel lblMealType = new JLabel("Meal Type:");
		lblMealType.setBounds(607, 29, 58, 14);
		panel.add(lblMealType);

		JRadioButton rdbtnBreakfast = new JRadioButton("BreakFast\r\n");
		rdbtnBreakfast.addActionListener(e -> {
			breakFast = true;
			dinner = false;
			lunch = false;
			try {
				hs1.clear();
				comboBox.removeAllItems();
				comboBox_1.removeAllItems();
				comboBox.addItem(new JCheckBox("------------------------Select Food------------------------", false));
				comboBox_1.addItem(
						new JCheckBox("------------------------Select Beverage------------------------", false));
				Class.forName("com.mysql.cj.jdbc.Driver");
				// Class.forName("mysql-connector-java-8.0.12");

				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "T@mugugly");
				//jdbc:mysql://localhost/db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC

				// here db is database name, root is username and password
				Statement stmt = con.createStatement();
				String sql;
				sql = "select * from Assignment where MenuDesc ='Food' AND MealType ='BreakFast';";
				ResultSet rs = stmt.executeQuery(sql);

				while (rs.next()) {
					comboBox.addItem(new JCheckBox(rs.getString(3), false));
				}
				Statement stmt2 = con.createStatement();
				sql = "select * from Assignment where MenuDesc ='Beverage' AND MealType ='BreakFast';";
				ResultSet rs2 = stmt2.executeQuery(sql);

				while (rs2.next()) {
					comboBox_1.addItem(new JCheckBox(rs2.getString(3), false));
				}
				con.close();
				stmt.close();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			comboBox.setEnabled(true);
			comboBox_1.setEnabled(true);
		});
		rdbtnBreakfast.setBounds(688, 25, 112, 23);
		panel.add(rdbtnBreakfast);

		JRadioButton rdbtnLunch = new JRadioButton("Lunch\r\n");
		rdbtnLunch.addActionListener(e -> {
			lunch = true;
			dinner = false;
			breakFast = false;
			try {
				hs1.clear();
				comboBox.removeAllItems();
				comboBox_1.removeAllItems();
				comboBox.addItem(new JCheckBox("------------------------Select Food------------------------", false));
				comboBox_1.addItem(
						new JCheckBox("------------------------Select Beverage------------------------", false));
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "T@mugugly");
				// here db is database name, root is username and password
				Statement stmt = con.createStatement();
				String sql;
				sql = "select * from Assignment where MenuDesc ='Food' AND MealType ='Lunch';";
				ResultSet rs = stmt.executeQuery(sql);

				while (rs.next()) {
					comboBox.addItem(new JCheckBox(rs.getString(3), false));
				}
				Statement stmt2 = con.createStatement();
				sql = "select * from Assignment where MenuDesc ='Beverage' AND MealType ='Lunch';";
				ResultSet rs2 = stmt2.executeQuery(sql);

				while (rs2.next()) {
					comboBox_1.addItem(new JCheckBox(rs2.getString(3), false));
				}
				con.close();
				stmt.close();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			comboBox.setEnabled(true);
			comboBox_1.setEnabled(true);
		});
		rdbtnLunch.setBounds(802, 25, 79, 23);
		panel.add(rdbtnLunch);

		JRadioButton rdbtnDinner = new JRadioButton("Dinner");
		rdbtnDinner.addActionListener(e -> {
			dinner = true;
			lunch = false;
			breakFast = false;
			try {
				hs1.clear();
				comboBox.removeAllItems();
				comboBox_1.removeAllItems();
				comboBox.addItem(new JCheckBox("------------------------Select Food------------------------", false));
				comboBox_1.addItem(
						new JCheckBox("------------------------Select Beverage------------------------", false));
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "T@mugugly");
				// here db is database name, root is username and password
				Statement stmt = con.createStatement();
				String sql;
				sql = "select * from Assignment where MenuDesc ='Food' AND MealType ='Dinner';";
				ResultSet rs = stmt.executeQuery(sql);

				while (rs.next()) {
					comboBox.addItem(new JCheckBox(rs.getString(3), false));
				}
				Statement stmt2 = con.createStatement();
				sql = "select * from Assignment where MenuDesc ='Beverage' AND MealType ='Dinner';";
				ResultSet rs2 = stmt2.executeQuery(sql);

				while (rs2.next()) {
					comboBox_1.addItem(new JCheckBox(rs2.getString(3), false));
				}
				con.close();
				stmt.close();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			comboBox.setEnabled(true);
			comboBox_1.setEnabled(true);

		});
		rdbtnDinner.setBounds(883, 25, 66, 23);
		panel.add(rdbtnDinner);

		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnBreakfast);
		bg.add(rdbtnLunch);
		bg.add(rdbtnDinner);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 105, 955, 83);
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Choose Menu Items",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		jf.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblFood = new JLabel("Food");
		lblFood.setBounds(50, 33, 37, 14);
		panel_1.add(lblFood);

		JLabel lblBe = new JLabel("Beverage");
		lblBe.setBounds(588, 33, 56, 14);
		panel_1.add(lblBe);
		Dimension minimumSize = new Dimension();
		minimumSize.setSize(251, 20);
		comboBox = new JComboBox() {
			public void setPopupVisible(boolean v) {
				super.setPopupVisible(true);
			}
		};

		comboBox.addActionListener(e -> {
			if (((JCheckBox) comboBox.getSelectedItem()) != null) {
				if (!hs1.contains(((JCheckBox) comboBox.getSelectedItem()).getText())) {
					hs1.add(((JCheckBox) comboBox.getSelectedItem()).getText());
				} else {
					hs1.remove(((JCheckBox) comboBox.getSelectedItem()).getText());
				}
				((JCheckBox) comboBox.getSelectedItem())
						.setSelected(!((JCheckBox) comboBox.getSelectedItem()).isSelected());
			}
		});

		comboBox.addItem(new JCheckBox("------------------------Select Food------------------------"));
		comboBox.setRenderer(new ComboBoxRender());
		comboBox.setBounds(111, 30, 274, 20);
		panel_1.add(comboBox);

		comboBox_1 = new JComboBox() {
			public void setPopupVisible(boolean v) {
				super.setPopupVisible(true);
			}
		};

		comboBox_1.addActionListener(e -> {

			if (((JCheckBox) comboBox_1.getSelectedItem()) != null) {
				if (!hs1.contains(((JCheckBox) comboBox_1.getSelectedItem()).getText())) {
					hs1.add(((JCheckBox) comboBox_1.getSelectedItem()).getText());
				} else {
					hs1.remove(((JCheckBox) comboBox_1.getSelectedItem()).getText());
				}
				((JCheckBox) comboBox_1.getSelectedItem())
						.setSelected(!((JCheckBox) comboBox_1.getSelectedItem()).isSelected());
			}
		});
		comboBox_1.addItem(new JCheckBox("------------------------Select Beverage------------------------"));

		comboBox_1.setRenderer(new ComboBoxRender());
		comboBox_1.setBounds(659, 30, 274, 20);

		panel_1.add(comboBox_1);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 201, 955, 176);
		panel_2.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Order Status", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		jf.getContentPane().add(panel_2);
		panel_2.setLayout(null);

		table = new JTable(new DefaultTableModel(new Object[] { "" }, 0));
		table.getSelectionModel().addListSelectionListener(e -> {
			btnPrepare.setEnabled(true);
		});
		table.setFillsViewportHeight(true);
		JScrollPane pane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pane.setBounds(10, 48, 438, 117);
		pane.getViewport().setBackground(Color.WHITE);

		lblNewLabel_2 = new JLabel("Order with waiting State (No Orders available to prepare).");
		lblNewLabel_2.setBounds(10, 24, 353, 14);
		panel_2.add(lblNewLabel_2);

		lblNewLabel_3 = new JLabel("Order with served State (No Orders available to serve)\r\n");
		lblNewLabel_3.setBounds(507, 24, 363, 14);
		panel_2.add(lblNewLabel_3);

		table_1 = new JTable(new DefaultTableModel(new Object[] { "" }, 0));
		table_1.setFillsViewportHeight(true);
		JScrollPane pane1 = new JScrollPane(table_1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pane1.setBounds(507, 49, 438, 117);
		pane1.getViewport().setBackground(Color.WHITE);
		panel_2.add(pane1);
		panel_2.add(pane);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setBounds(0, 399, 955, 176);
		jf.getContentPane().add(panel_3);
		panel_3.setLayout(null);

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Command Buttons", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel_4.setBounds(0, 611, 955, 55);
		jf.getContentPane().add(panel_4);
		panel_4.setLayout(null);

		JButton btnNewButton = new JButton("Enter Data");
		btnNewButton.addActionListener(e -> {
			panel_3.removeAll();
			if (textField.getText().length() == 0) {
				JOptionPane.showMessageDialog(jf, "Please provide customer name.", "Validation Error",
						JOptionPane.ERROR_MESSAGE);
			} else if (textField_1.getText().length() == 0) {
				JOptionPane.showMessageDialog(jf, "Please provide table number.", "Validation Error",
						JOptionPane.ERROR_MESSAGE);
			} else {

				custName = textField.getText();
				HashMap hm = new HashMap();
				tableno = textField_1.getText();
				hm.put(custName, hs1);
				hs.put(Integer.parseInt(tableno), hm);
				if (hs.get(Integer.parseInt(tableno)).get(custName)
						.contains("------------------------Select Beverage------------------------")) {
					hs.get(Integer.parseInt(tableno)).get(custName)
							.remove("------------------------Select Beverage------------------------");
				}
				if (hs.get(Integer.parseInt(tableno)).get(custName)
						.contains("------------------------Select Food------------------------")) {
					hs.get(Integer.parseInt(tableno)).get(custName)
							.remove("------------------------Select Food------------------------");
				}
				Iterator it = hs.get(Integer.parseInt(tableno)).get(custName).iterator();
				String s = "";
				while (it.hasNext()) {
					s = s + it.next() + ",";
				}
				s = custName + "| Table : " + tableno + " | " + s;
				Object obj[] = new Object[] { s };
				// CS
				try {
					Waiter.client.send(s);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(jf, "Order placed succesfully.", "Information",
						JOptionPane.INFORMATION_MESSAGE);
				lblNewLabel_2.setText("Orders with wating status.");
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.addRow(obj);

				table.setModel(model);
				jf.revalidate();
			}
		});
		btnNewButton.setBounds(17, 21, 124, 23);
		panel_4.add(btnNewButton);

		JButton btnDisplayChoices = new JButton("Display Choices");
		btnDisplayChoices.addActionListener(e -> {
			panel_3.removeAll();
			if (((JCheckBox) comboBox.getSelectedItem()).getText().length() == 0) {
				JOptionPane.showMessageDialog(jf, "Please choose the food item to view nutritional information.",
						"Validation Error", JOptionPane.ERROR_MESSAGE);
			} else if (((JCheckBox) comboBox_1.getSelectedItem()).getText().length() == 0) {
				JOptionPane.showMessageDialog(jf, "Please choose the beverage item to view nutritional information.",
						"Validation Error", JOptionPane.ERROR_MESSAGE);
			} else {
				panel_3.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)),
						"Menu Choices and Nutrition Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				String column[] = { "Item Name", "Energy", "Protein", "Carbohydrate", "Total Fat", "Fibre", "Price" };
				String data[][] = new String[hs1.size() + 1][7];

				try {
					if (hs1.contains("------------------------Select Beverage------------------------"))
						hs1.remove("------------------------Select Beverage------------------------");
					if (hs1.contains("------------------------Select Food------------------------"))
						hs1.remove("------------------------Select Food------------------------");
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root",
							"T@mugugly");
					Iterator it = hs1.iterator();
					int i = 0;
					String str = "Total Nutrient for each Type";
					double energy = 0;
					double protien = 0;
					double carb = 0;
					double fat = 0;
					double fibre = 0;
					double price = 0;

					while (it.hasNext()) {

						data[i][0] = (String) it.next();

						Statement stmt = con.createStatement();
						String sql;
						sql = "select * from Assignment where ItemName = '" + data[i][0] + "';";
						ResultSet rs = stmt.executeQuery(sql);
						while (rs.next()) {
							data[i][1] = (Double.toString(rs.getDouble(5)));
							energy = energy + rs.getDouble(5);
							data[i][2] = (Double.toString(rs.getDouble(6)));
							protien = protien + rs.getDouble(6);
							data[i][3] = (Double.toString(rs.getDouble(7)));
							carb = carb + rs.getDouble(7);
							data[i][4] = (Double.toString(rs.getDouble(8)));
							fat = fat + rs.getDouble(8);
							data[i][5] = (Double.toString(rs.getDouble(9)));
							fibre = fibre + rs.getDouble(9);
							data[i][6] = (Double.toString(rs.getDouble(4)));
							price = price + rs.getDouble(4);
						}
						i++;

					}

					data[i][0] = str;
					data[i][1] = Double.toString(energy);
					data[i][3] = Double.toString(carb);
					data[i][2] = Double.toString(protien);
					data[i][4] = Double.toString(fat);
					data[i][5] = Double.toString(fibre);
					data[i][6] = "";
					con.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}

				JTable table_3;
				table_3 = new JTable(data, column);
				table_3.setBounds(10, 15, 935, 154);
				JScrollPane jsp = new JScrollPane(table_3, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
						JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				jsp.setBounds(5, 18, 945, 155);
				jsp.getViewport().setBackground(Color.WHITE);
				panel_3.add(jsp);
				table_3.setFillsViewportHeight(true);
				// table_2.setBackground(Color.WHITE);
				panel_3.revalidate();
				jf.revalidate();
			}
		});
		btnDisplayChoices.setBounds(151, 21, 124, 23);
		panel_4.add(btnDisplayChoices);

		JButton btnNewButton_1 = new JButton("Display Order");
		btnNewButton_1.addActionListener(e -> {
			if (textField.getText().length() == 0) {
				JOptionPane.showMessageDialog(jf, "Please enter table number (1 - 9) allowed to view ordered items.",
						"Validation Error", JOptionPane.ERROR_MESSAGE);
			}
			if (custName.length() == 0) {
				JOptionPane.showMessageDialog(null, "Please confirm the order");
			} else {
				panel_3.removeAll();
				String column[] = { "Customer name", "Ordered items" };
				Iterator it = hs.get(Integer.parseInt(tableno)).get(custName).iterator();
				String data[][];
				String str = "";
				while (it.hasNext()) {
					str = str + it.next() + ",";
				}
				data = new String[][] { { str } };
				JTable table_3;
				table_3 = new JTable(new DefaultTableModel(new Object[] { "Customer name", "Ordered items" }, 0));
				DefaultTableModel model = (DefaultTableModel) table_3.getModel();
				model.addRow(new Object[] { custName, str });
				table_3.setBounds(10, 15, 935, 154);
				panel_3.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)),
						"Ordered items at Table " + tableno + " ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				JScrollPane jsp = new JScrollPane(table_3, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
						JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				jsp.setBounds(5, 18, 945, 155);
				jsp.getViewport().setBackground(Color.WHITE);
				panel_3.add(jsp);
				table_3.setFillsViewportHeight(true);
				panel_3.revalidate();
			}
		});
		btnNewButton_1.setBounds(285, 21, 124, 23);
		panel_4.add(btnNewButton_1);

		btnPrepare = new JButton("Prepare");
		btnPrepare.setEnabled(false);
		btnPrepare.setBounds(419, 21, 124, 23);
		panel_4.add(btnPrepare);

		JButton btnNewButton_2 = new JButton("Bill");
		btnNewButton_2.setEnabled(false);
		btnNewButton_2.setBounds(553, 21, 124, 23);
		panel_4.add(btnNewButton_2);

		btnClearDisplay = new JButton("Clear Display");
		btnClearDisplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				hs1.clear();
				hs.clear();
				panel_3.removeAll();
				panel_3.repaint();
				panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				comboBox.removeAllItems();
				comboBox_1.removeAllItems();
				for (int i = 0; i < model.getRowCount(); i++)
					model.removeRow(i);
				DefaultTableModel model1 = (DefaultTableModel) table_1.getModel();
				for (int i = 0; i < model1.getRowCount(); i++)
					model1.removeRow(i);
				textField_1.setText("");
				textField.setText("");
				rdbtnBreakfast.setSelected(false);
				rdbtnLunch.setSelected(false);
				rdbtnDinner.setSelected(false);
				lblNewLabel_3.setText("Order with served State (No Orders available to serve)\r\n");
				lblNewLabel_2.setText("Order with waiting State (No Orders available to prepare).");
				panel_3.revalidate();
				try {
					Waiter.client.send("Clear");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnClearDisplay.setBounds(687, 21, 124, 23);
		panel_4.add(btnClearDisplay);

		JButton btnNewButton_3 = new JButton("Quit");
		btnNewButton_3.addActionListener(e -> {
			System.exit(0);
		});
		btnNewButton_3.setBounds(821, 21, 124, 23);
		panel_4.add(btnNewButton_3);
		comboBox.setEnabled(false);
		comboBox_1.setEnabled(false);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	@SuppressWarnings("rawtypes")
	class ComboBoxRender implements ListCellRenderer {

		@Override
		public Component getListCellRendererComponent(JList arg0, Object arg1, int arg2, boolean arg3, boolean arg4) {

			if (arg1 instanceof Component) {
				Component c = (Component) (arg1);
				if (arg3) {
					c.setBackground(arg0.getSelectionBackground());
					c.setForeground(arg0.getSelectionForeground());
				}

				return c;
			} else {
				return new JLabel("");
			}

		}

	}

	/////
	// Client Server program//

	static class client {

		public static void send(String ss) throws Exception {
			Socket s;
			Socket s1;
			if (ss.equalsIgnoreCase("Clear")) {
				s = new Socket("localhost", 5059);
				s1 = new Socket("localhost", 5060);
			} else {
				s = new Socket("localhost", 5056);
				s1 = new Socket("localhost", 5055);
			}
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			DataOutputStream dos1 = new DataOutputStream(s1.getOutputStream());
			dos.writeUTF(ss);
			dos1.writeUTF(ss);
			dos1.flush();
			dos1.close();
			dos.flush();
			dos.close();
			s.close();
			s1.close();
		}

	}

	@SuppressWarnings("rawtypes")
	public void run() {
		while (true) {
			try {
				ServerSocket ss = new ServerSocket(5054);
				Socket s = ss.accept();
				DataInputStream dis = new DataInputStream(s.getInputStream());
				String str = dis.readUTF();
				if (str.charAt(0) == '1') {
					Vector v;
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					DefaultTableModel model1 = (DefaultTableModel) table_1.getModel();
					v = model.getDataVector();
					int row = Integer.parseInt(str.split(" ")[1]);
					Vector v1 = (Vector) v.get(row);
					Object obj[] = { v1.get(0) };
					model1.addRow(obj);
					table_1.setModel(model1);
					model.removeRow(row);
					if (model.getRowCount() == 0) {
						lblNewLabel_2.setText("Order with waiting State (No Orders available to prepare).");
					}
				} else {
					DefaultTableModel model = (DefaultTableModel) table_1.getModel();
					model.removeRow(Integer.parseInt(str.split(" ")[1]));
					if (model.getRowCount() == 0) {
						lblNewLabel_3.setText("Order with served State (No Orders available to serve)\r\n");
					}
				}
				dis.close();
				s.close();
				ss.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	public class WaiterClient extends Thread {
		public void run() {
			try {
				ServerSocket ss = new ServerSocket(5050);
				Socket s = ss.accept();
				DataInputStream dis = new DataInputStream(s.getInputStream());
				String str = (String) dis.readUTF();
				if (str.charAt(0) == '0') {
					DefaultTableModel model = (DefaultTableModel) table_1.getModel();
					model.removeRow(Integer.parseInt(str.split(" ")[1]));
					if (model.getRowCount() == 0) {
						lblNewLabel_3.setText("Order with served State (No Orders available to serve)\r\n");
						btnClearDisplay.doClick();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
