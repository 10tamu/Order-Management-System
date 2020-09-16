package swinRestaurant;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

public class Test {
	public Test() {
		JFrame jf = new JFrame();
		jf.setLayout(new FlowLayout());
		Vector<JCheckBox> v = new Vector<JCheckBox>();
		// v.add(new JCheckBox("hdi",false));
		// v.add(new JCheckBox("hlo",false));
		v.add(new JCheckBox("hiii", false));
		v.add(new JCheckBox("hilo", false));
		JComboBox cb = new JComboBox(v) {
			public void setPopupVisible(boolean v) {
				super.setPopupVisible(true);
			}
		};
		// ((JCheckBox)cb.getSelectedItem()).setSelected(!((JCheckBox)cb.getSelectedItem()).isSelected());
		cb.addActionListener(e -> {
			((JCheckBox) cb.getSelectedItem()).setSelected(!((JCheckBox) cb.getSelectedItem()).isSelected());
		});

		cb.setRenderer(new ComboBoxRender());
		// cb.addItem(new JCheckBox("kkk",false));
		cb.add(new JCheckBox("lll", true));
		jf.getContentPane().add(cb);
		jf.setVisible(true);
		jf.setSize(500, 500);
	}

	class ComboBoxRender implements ListCellRenderer {

		@Override
		public Component getListCellRendererComponent(JList arg0, Object arg1, int arg2, boolean arg3, boolean arg4) {
			if (arg1 instanceof Component) {
				Component c = (Component) (arg1);
				if (arg3) {
					c.setBackground(arg0.getSelectionBackground());
					c.setForeground(arg0.getSelectionForeground());
				} else {
					c.setBackground(arg0.getBackground());
					c.setForeground(arg0.getBackground());
				}
				return c;
			} else {
				return new JLabel(arg1.toString());
			}

		}

	}
}
/*
 * private JPanel getContent() { // We need to keep track of the selections
 * SelectionManager manager = new SelectionManager(); // and make the selection
 * state available to the renderer. MultiRenderer renderer = new
 * MultiRenderer(manager); Object[] items = { "George", "Greta", "Jenny",
 * "Anna", "Pieter", "Antonio", "Susan", "Tom" };
 * manager.setNonSelectables("Greta", "Pieter"); DefaultComboBoxModel model =
 * new DefaultComboBoxModel(items); JComboBox combo = new JComboBox(model);
 * combo.addActionListener(manager); combo.setRenderer(renderer); JPanel panel =
 * new JPanel(); panel.add(combo); return panel; }
 * 
 * public static void main(String[] args) { JFrame f = new JFrame();
 * f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); f.getContentPane().add(new
 * Test().getContent()); f.setSize(300,145); f.setLocation(200,200);
 * f.setVisible(true); } }
 * 
 * class SelectionManager implements ActionListener { JComboBox combo = null;
 * ArrayList selectedItems = new ArrayList(); // j2se 1.5+ // List selectedItems
 * = new ArrayList(); // j2se 1.4- ArrayList<Object> nonSelectables = new
 * ArrayList<Object>();
 * 
 * public void actionPerformed(ActionEvent e) { if(combo == null) { combo =
 * (JComboBox)e.getSource(); } Object item = combo.getSelectedItem(); // Toggle
 * the selection state for item. if(selectedItems.contains(item)) {
 * selectedItems.remove(item); } else if(!nonSelectables.contains(item)) {
 * selectedItems.add(item); } }
 * 
 * /** The varargs feature (Object... args) is new in j2se 1.5 You can replace
 * the argument with an array.
 */
/*
 * public void setNonSelectables(Object... args) { for(int j = 0; j <
 * args.length; j++) { nonSelectables.add(args[j]); } }
 * 
 * public boolean isSelected(Object item) { return selectedItems.contains(item);
 * } }
 * 
 * /** Implementation copied from source code.
 *//*
	 * class MultiRenderer extends BasicComboBoxRenderer { SelectionManager
	 * selectionManager;
	 * 
	 * public MultiRenderer(SelectionManager sm) { selectionManager = sm; }
	 * 
	 * public Component getListCellRendererComponent(JList list, Object value, int
	 * index, boolean isSelected, boolean cellHasFocus) { if
	 * (selectionManager.isSelected(value)) {
	 * setBackground(list.getSelectionBackground());
	 * setForeground(list.getSelectionForeground()); } else {
	 * setBackground(list.getBackground()); setForeground(list.getForeground()); }
	 * 
	 * setFont(list.getFont());
	 * 
	 * if (value instanceof Icon) { setIcon((Icon)value); } else { setText((value ==
	 * null) ? "" : value.toString()); } return this; } } /* JButton jb1, jb2;
	 * JPopupMenu popupMenu; JMenuItem jmi1, jmi2; JList list; int i = 1; public
	 * Test() { JFrame jf = new JFrame (); jf.getContentPane().setLayout(null);
	 * jf.setSize(500, 500); JPopupMenu popupMenu_1 = new JPopupMenu("Hello");
	 * popupMenu_1.setVisible(true); popupMenu_1.setSize(100, 100); JMenuItem jm1 =
	 * new JMenuItem();
	 * 
	 * popupMenu_1.setBounds(0, 0, 166, 16); addPopup(jf.getContentPane(),
	 * popupMenu_1); jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 * jf.setVisible(true); } private static void addPopup(Component component,
	 * final JPopupMenu popup) { }
	 * 
	 * /*public Test() { Vector data; setLayout(new BorderLayout()); list = new
	 * JList(); list.setModel(new DefaultListModel()); add(new
	 * JScrollPane(list),"Center"); add(jb1 = new JButton("Add"), "East"); add(jb2 =
	 * new JButton("Clear"), "West"); jb1.addActionListener(this);
	 * jb2.addActionListener(this);
	 * 
	 * popupMenu = new JPopupMenu(); popupMenu.add(jmi1= new JMenuItem("Add"));
	 * popupMenu.add(new JPopupMenu.Separator()); popupMenu.add(jmi2 = new
	 * JMenuItem("Clear"));
	 * 
	 * 
	 * list.addMouseListener(new MouseAdapter() { public void
	 * mouseClicked(MouseEvent me) { // if right mouse button clicked (or
	 * me.isPopupTrigger()) if (SwingUtilities.isRightMouseButton(me) &&
	 * !list.isSelectionEmpty() && list.locationToIndex(me.getPoint()) ==
	 * list.getSelectedIndex()) { popupMenu.show(list, me.getX(), me.getY()); } } }
	 * );
	 * 
	 * jmi1.addActionListener(this); jmi2.addActionListener(this);
	 * 
	 * 
	 * } public Dimension getPreferredSize(){ return new Dimension(50, 50); }
	 * 
	 * public void actionPerformed(ActionEvent ae) { if (ae.getSource() == jb1 ||
	 * ae.getSource() == jmi1) { // add DefaultListModel dlm = (DefaultListModel)
	 * list.getModel(); dlm.addElement ((Object) Integer.toString(i++)); } else { //
	 * clear list.setModel(new DefaultListModel()); } } public static void
	 * main(String s[]) { JFrame frame = new JFrame("PopUp JList"); Test panel = new
	 * Test(); frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	 * frame.getContentPane().add(panel,"Center");
	 * 
	 * frame.setSize(200,200); frame.setVisible(true); /* }
	 *//*
		 * public static void main(String args[]) { Test t= new Test(); } } /*try{
		 * Class.forName("com.mysql.cj.jdbc.Driver"); Connection
		 * con=DriverManager.getConnection(
		 * "jdbc:mysql://localhost:3306/db","root","b@subh@sk@r"); //here sonoo is
		 * database name, root is username and password Statement
		 * stmt=con.createStatement(); ResultSet
		 * rs=stmt.executeQuery("select * from Assignment"); while(rs.next())
		 * System.out.println(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3))
		 * ; con.close(); }catch(Exception e){ System.out.println(e);}
		 * 
		 * 
		 * }
		 */
