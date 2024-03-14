package eduCourse_prj.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class AdminStudMgtDesign extends JDialog {
	AdminHomeDesign awd;


	public AdminStudMgtDesign(AdminHomeDesign awd, String title) {
		super(awd, title, true);
		this.awd = awd;
		setLayout(null);

		// Set the size of the dialog
		setSize(1000, 650);



		setLocationRelativeTo(null);
	}

}