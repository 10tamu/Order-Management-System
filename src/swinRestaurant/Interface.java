package swinRestaurant;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class Interface {
	private static JTextField textField;
	private static JTextField textField_1;
	private static JTextField textField_2;
	private static JTextField textField_3;

	static JFrame UI() {
		JFrame jf = new JFrame("Restaurant Order Reception");
		jf.setBounds(100, 100, 827, 705);
		jf.getContentPane().setLayout(null);
		JPanel panel = new JPanel();
		panel.setBounds(0, 11, 811, 83);
		panel.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 1), "Customer Details", TitledBorder.LEADING,
				TitledBorder.TOP, null, Color.BLACK));
		jf.getContentPane().add(panel);
		panel.setLayout(null);

		textField = new JTextField();
		textField.setBounds(115, 26, 86, 20);
		panel.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Customer Name");
		lblNewLabel.setBounds(20, 29, 85, 14);
		panel.add(lblNewLabel);

		textField_1 = new JTextField();
		textField_1.setBounds(347, 26, 86, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Table Number");
		lblNewLabel_1.setBounds(264, 29, 86, 14);
		panel.add(lblNewLabel_1);

		JLabel lblMealType = new JLabel("Meal Type :");
		lblMealType.setBounds(494, 29, 58, 14);
		panel.add(lblMealType);

		JRadioButton rdbtnBreakfast = new JRadioButton("BreakFast\r\n");
		rdbtnBreakfast.setBounds(565, 25, 73, 23);
		panel.add(rdbtnBreakfast);

		JRadioButton rdbtnLunch = new JRadioButton("Lunch\r\n");
		rdbtnLunch.setBounds(640, 25, 53, 23);
		panel.add(rdbtnLunch);

		JRadioButton rdbtnDinner = new JRadioButton("Dinner");
		rdbtnDinner.setBounds(699, 25, 66, 23);
		panel.add(rdbtnDinner);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 105, 811, 83);
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Choose Menu Items",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		jf.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblFood = new JLabel("Food");
		lblFood.setBounds(33, 33, 29, 14);
		panel_1.add(lblFood);

		JLabel lblBe = new JLabel("Beverage");
		lblBe.setBounds(421, 33, 56, 14);
		panel_1.add(lblBe);

		JComboBox comboBox = new JComboBox();
		comboBox.setEnabled(false);
		comboBox.setBounds(323, 30, 29, 20);
		panel_1.add(comboBox);

		textField_2 = new JTextField();
		textField_2.setEnabled(false);
		textField_2.setBounds(72, 30, 251, 20);
		panel_1.add(textField_2);
		textField_2.setColumns(10);

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setEnabled(false);
		comboBox_1.setBounds(752, 30, 28, 20);
		panel_1.add(comboBox_1);

		textField_3 = new JTextField();
		textField_3.setEnabled(false);
		textField_3.setBounds(487, 30, 265, 20);
		panel_1.add(textField_3);
		textField_3.setColumns(10);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 201, 811, 176);
		panel_2.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Order Status", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		jf.getContentPane().add(panel_2);
		panel_2.setLayout(null);

		JTable table = new JTable();
		table.setFillsViewportHeight(true);
		JScrollPane pane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pane.setBounds(10, 48, 344, 117);
		pane.getViewport().setBackground(Color.WHITE);

		JLabel lblNewLabel_2 = new JLabel("Order with waiting State (No Orders available to prepare).");
		lblNewLabel_2.setBounds(10, 24, 353, 14);
		panel_2.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Order with served State (No Orders available to serve)\r\n");
		lblNewLabel_3.setBounds(419, 24, 363, 14);
		panel_2.add(lblNewLabel_3);

		JTable table_1 = new JTable();
		table_1.setFillsViewportHeight(true);
		JScrollPane pane1 = new JScrollPane(table_1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pane1.setBounds(419, 49, 363, 117);
		pane1.getViewport().setBackground(Color.WHITE);
		panel_2.add(pane1);
		panel_2.add(pane);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setBounds(0, 399, 811, 176);
		jf.getContentPane().add(panel_3);

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Command Buttons", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel_4.setBounds(0, 611, 811, 55);
		jf.getContentPane().add(panel_4);
		panel_4.setLayout(null);

		JButton btnNewButton = new JButton("Enter Data");
		btnNewButton.setBounds(10, 21, 116, 23);
		panel_4.add(btnNewButton);

		JButton btnDisplayChoices = new JButton("Display Choices");
		btnDisplayChoices.setBounds(136, 21, 124, 23);
		panel_4.add(btnDisplayChoices);

		JButton btnNewButton_1 = new JButton("Display Order");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton_1.setBounds(270, 21, 124, 23);
		panel_4.add(btnNewButton_1);

		JButton btnPrepare = new JButton("Prepare");
		btnPrepare.setEnabled(false);
		btnPrepare.setBounds(404, 21, 124, 23);
		panel_4.add(btnPrepare);

		JButton btnNewButton_2 = new JButton("Bill");
		btnNewButton_2.setEnabled(false);
		btnNewButton_2.setBounds(538, 21, 124, 23);
		panel_4.add(btnNewButton_2);

		JButton btnClearDisplay = new JButton("Clear Display");
		btnClearDisplay.setBounds(672, 21, 124, 23);
		panel_4.add(btnClearDisplay);
		return jf;
	}
}
