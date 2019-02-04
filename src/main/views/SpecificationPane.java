package main.views;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;

import net.miginfocom.swing.MigLayout;

public class SpecificationPane extends JPanel{

	private JScrollPane sp_TextArea;
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
		this.ta_Description.setRows(3);
		this.sp_TextArea = new JScrollPane();
		this.sp_TextArea.setViewportView(this.ta_Description);
		this.s_Hours = new JSpinner();
		this.cb_Check = new JCheckBox("Marcar");

		this.add(this.lbl_Hours, "split 2, align left");
		this.add(this.s_Hours);
		this.add(this.cb_Check, "wrap");
		this.add(this.sp_TextArea, "wrap, span 4, pushx, growx");
		this.add(this.btn_Save, "wrap, span 4, align right");
	}
	
}
