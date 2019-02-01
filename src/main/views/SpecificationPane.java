package main.views;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextArea;

import net.miginfocom.swing.MigLayout;

public class SpecificationPane extends JPanel{

	private JLabel lbl_Hours;
	private JButton btn_Save;
	private JTextArea ta_Description;
	private JSpinner s_Hours;
	private JCheckBox cb_Check;
	
	public SpecificationPane(String description) {
		this.setLayout(new MigLayout());
		this.lbl_Hours = new JLabel("Número de horas");
		this.btn_Save = new JButton("Guardar cambios");
		this.ta_Description = new JTextArea(description);
		this.s_Hours = new JSpinner();
		this.cb_Check = new JCheckBox("Marcar");

		this.add(this.lbl_Hours);
		this.add(this.s_Hours);
		this.add(this.cb_Check, "align right, wrap");
		this.add(this.ta_Description, "span 3, wrap, pushx, growx, pushy, growy");
		
	}
	
}
