package swinRestaurant;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
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

/**
 * Reception: Reception view. Takes order from the customer and adds data to cook to prepare when the food is 
 * in served state later after when the food is served it provides bill to the customer.
 * @author Palak Tank, Isha Shukla
 * @version (19/10/2018)
 */

public class Reception extends Thread {
	private JTextField textField;
	private JTextField textField_1;
	private String custName;
	private String tableno;
	private boolean lunch = false;
	private boolean breakFast = false;
	private boolean dinner = false;
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private JTable table_1;
	private JButton btnNewButton_2;
	private JTable table;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;

	/**
	 * Constructor: It implements user interface for reception and handles food order and billing activity
	 * */
	protected Reception() throws Exception {
		ReceptionClient wc = new ReceptionClient();
		wc.start();
		JFrame jf = new JFrame("Restaurant Order Reception");
		jf.setBounds(100, 100, 972, 705);
		jf.getContentPane().setLayout(null);
		JPanel panel = new JPanel();
		panel.setBounds(0, 11, 955, 83);
		panel.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 1), "Customer Details", TitledBorder.LEADING,
				TitledBorder.TOP, null, Color.BLACK));
		jf.getContentPane().add(panel);
		panel.setLayout(null);

		textField = new JTextField();
		textField.setEnabled(false);
		textField.setBounds(115, 26, 191, 20);
		panel.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Customer Name");
		lblNewLabel.setBounds(20, 29, 85, 14);
		panel.add(lblNewLabel);

		textField_1 = new JTextField();
		textField_1.setEnabled(false);
		textField_1.setBounds(412, 26, 174, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Table Number");
		lblNewLabel_1.setBounds(316, 29, 86, 14);
		panel.add(lblNewLabel_1);

		JLabel lblMealType = new JLabel("Meal Type :");
		lblMealType.setBounds(607, 29, 58, 14);
		panel.add(lblMealType);

		JRadioButton rdbtnBreakfast = new JRadioButton("BreakFast\r\n");
		rdbtnBreakfast.setEnabled(false);
		rdbtnBreakfast.addActionListener(e -> {
			breakFast = true;
			dinner = false;
			lunch = false;
			comboBox.setEnabled(true);
			comboBox_1.setEditable(true);
		});
		rdbtnBreakfast.setBounds(688, 25, 112, 23);
		panel.add(rdbtnBreakfast);

		JRadioButton rdbtnLunch = new JRadioButton("Lunch\r\n");
		rdbtnLunch.setEnabled(false);
		rdbtnLunch.addActionListener(e -> {
			lunch = true;
			dinner = false;
			breakFast = false;
			comboBox.setSelectedItem("------------------------Select Food------------------------");
			comboBox.addItem("Niayyya");
			// ResultSet rs = getDataLunchFood();
			comboBox.setEnabled(true);
			comboBox_1.setEditable(true);
		});
		rdbtnLunch.setBounds(802, 25, 79, 23);
		panel.add(rdbtnLunch);

		JRadioButton rdbtnDinner = new JRadioButton("Dinner");
		rdbtnDinner.setEnabled(false);
		rdbtnDinner.addActionListener(e -> {
			dinner = true;
			lunch = false;
			breakFast = false;
			comboBox.setEnabled(true);
			comboBox_1.setEditable(true);
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
		// Dimension minimumSize = new Dimension();
		// minimumSize.setSize(251, 20);

		comboBox = new JComboBox() {
			public void setPopupVisible(boolean v) {
				super.setPopupVisible(true);
			}
		};

		comboBox.setEnabled(false);
		comboBox.addActionListener(e -> {
			((JCheckBox) comboBox.getSelectedItem())
					.setSelected(!((JCheckBox) comboBox.getSelectedItem()).isSelected());
		});

		comboBox.setRenderer(new ComboBoxRender());
		comboBox.setBounds(111, 30, 274, 20);
		panel_1.add(comboBox);

		comboBox_1 = new JComboBox() {
			public void setPopupVisible(boolean v) {
				super.setPopupVisible(true);
			}
		};
		comboBox_1.setEnabled(false);
		comboBox_1.addActionListener(e -> {
			((JCheckBox) comboBox_1.getSelectedItem())
					.setSelected(!((JCheckBox) comboBox_1.getSelectedItem()).isSelected());
		});

		comboBox_1.setRenderer(new ComboBoxRender());
		comboBox_1.setBounds(659, 30, 274, 20);

		comboBox.addItem(new JCheckBox("------------------------Select Food------------------------", true));
		comboBox_1.addItem(new JCheckBox("------------------------Select Beverage------------------------", true));

		panel_1.add(comboBox_1);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 201, 955, 176);
		panel_2.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Order Status", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		jf.getContentPane().add(panel_2);
		panel_2.setLayout(null);

		table = new JTable(new DefaultTableModel(new Object[] { "" }, 0));
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
		table_1.getSelectionModel().addListSelectionListener(e -> {
			btnNewButton_2.setEnabled(true);
		});

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

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Command Buttons", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel_4.setBounds(0, 611, 955, 55);
		jf.getContentPane().add(panel_4);
		panel_4.setLayout(null);

		JButton btnNewButton = new JButton("Enter Data");
		btnNewButton.setEnabled(false);
		btnNewButton.addActionListener(e -> {
			if (textField.getText().length() == 0) {
				JOptionPane.showMessageDialog(jf, "Please provide customer name.", "Validation Error",
						JOptionPane.ERROR_MESSAGE);
			} else if (textField_1.getText().length() == 0) {
				JOptionPane.showMessageDialog(jf, "Please provide table number.", "Validation Error",
						JOptionPane.ERROR_MESSAGE);
			} else {
				custName = textField.getText();
				tableno = textField_1.getText();
				
			}
		});
		btnNewButton.setBounds(17, 21, 124, 23);
		panel_4.add(btnNewButton);

		JButton btnDisplayChoices = new JButton("Display Choices");
		btnDisplayChoices.setEnabled(false);
		btnDisplayChoices.addActionListener(e -> {
			if (comboBox.getSelectedItem().toString().length() == 0) {
				JOptionPane.showMessageDialog(jf, "Please choose the food item to view nutritional information.",
						"Validation Error", JOptionPane.ERROR_MESSAGE);
			} else if (comboBox_1.getSelectedItem().toString().length() == 0) {
				JOptionPane.showMessageDialog(jf, "Please choose the beverage item to view nutritional information.",
						"Validation Error", JOptionPane.ERROR_MESSAGE);
			} else {

			}
		});
		btnDisplayChoices.setBounds(151, 21, 124, 23);
		panel_4.add(btnDisplayChoices);

		JButton btnNewButton_1 = new JButton("Display Order");
		btnNewButton_1.setEnabled(false);
		btnNewButton_1.addActionListener(e -> {
			if (textField.getText().length() == 0) {
				JOptionPane.showMessageDialog(jf, "Please enter table number (1 - 9) allowed to view ordered items.",
						"Validation Error", JOptionPane.ERROR_MESSAGE);
			} else {

			}
		});
		btnNewButton_1.setBounds(285, 21, 124, 23);
		panel_4.add(btnNewButton_1);

		JButton btnPrepare = new JButton("Prepare");
		btnPrepare.setEnabled(false);
		btnPrepare.setBounds(419, 21, 124, 23);
		panel_4.add(btnPrepare);

		btnNewButton_2 = new JButton("Bill");
		btnNewButton_2.addActionListener(e -> {
			int reply = JOptionPane.showConfirmDialog(jf, "Do you want to proceed to billing. ", "Confirmation Dailog.",
					JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION) {
				JOptionPane.showMessageDialog(jf, "Selected option  billed.", "Information Message",
						JOptionPane.INFORMATION_MESSAGE);
				DefaultTableModel model = (DefaultTableModel) table_1.getModel();
				int row = table_1.getSelectedRow();
				model.removeRow(row);
				try {
					Reception.client.send(Integer.toString(row));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (model.getRowCount() == 0) {
					lblNewLabel_3.setText("Order with served State (No Orders available to serve)\r\n");
				}
			}

		});
		btnNewButton_2.setEnabled(false);
		btnNewButton_2.setBounds(553, 21, 124, 23);
		panel_4.add(btnNewButton_2);

		JButton btnClearDisplay = new JButton("Clear Display");
		btnClearDisplay.setEnabled(false);
		btnClearDisplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	/**
	 * ComboBoxRender: Creates dynamic dropdownlist
	 * */
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
				return new JLabel(arg1.toString());
			}

		}

	}

	/**
	 * Client: Sends request and receives response from server(cook)
	 * */
	static class client {

		public static void send(String st) throws Exception {
			Socket s = new Socket("localhost", 5059);
			Socket s1 = new Socket("localhost", 5050);
			DataOutputStream dos1 = new DataOutputStream(s1.getOutputStream());
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			dos.writeUTF("0 " + st);
			dos1.writeUTF("0 " + st);
			dos.flush();
			dos1.flush();
			dos1.close();
			dos.close();
			s.close();
			s1.close();
		}

	}

	@SuppressWarnings("rawtypes")
	public void run() {
		while (true) {
			try {
				ServerSocket ss = new ServerSocket(5055);
				Socket s = ss.accept();
				DataInputStream dis = new DataInputStream(s.getInputStream());
				String str = (String) dis.readUTF();

				if (str.equalsIgnoreCase("Clear")) {
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					for (int i = 0; i < model.getRowCount(); i++)
						model.removeRow(i);
					DefaultTableModel model1 = (DefaultTableModel) table.getModel();
					for (int i = 0; i < model1.getRowCount(); i++)
						model1.removeRow(i);
					textField_1.setText("");
					textField.setText("");
					lblNewLabel_3.setText("Order with served State (No Orders available to serve)\r\n");
					lblNewLabel_2.setText("Order with waiting State (No Orders available to prepare).");
				} else if (str.charAt(0) != '1') {
					Object obj[] = new Object[] { str };
					lblNewLabel_2.setText("Orders with wating status.");
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.addRow(obj);
					table.setModel(model);
				} else {
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
						lblNewLabel_3.setText("Order with waiting State (No Orders available to prepare).");
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

	class ReceptionClient extends Thread {
		public void run() {
			try {
				ServerSocket ss = new ServerSocket(5060);
				Socket s = ss.accept();
				DataInputStream dis = new DataInputStream(s.getInputStream());
				String str = (String) dis.readUTF();
				if (str.equalsIgnoreCase("Clear")) {
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					for (int i = 0; i < model.getRowCount(); i++)
						model.removeRow(i);
					DefaultTableModel model1 = (DefaultTableModel) table.getModel();
					for (int i = 0; i < model1.getRowCount(); i++)
						model1.removeRow(i);
					
					textField_1.setText("");
					textField.setText("");
					lblNewLabel_3.setText("Order with served State (No Orders available to serve)\r\n");
					lblNewLabel_2.setText("Order with waiting State (No Orders available to prepare).");

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
