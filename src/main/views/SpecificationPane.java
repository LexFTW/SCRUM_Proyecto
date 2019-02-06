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

	public JScrollPane getSp_TextArea() {
		return sp_TextArea;
	}

	public void setSp_TextArea(JScrollPane sp_TextArea) {
		this.sp_TextArea = sp_TextArea;
	}

	public JLabel getLbl_Hours() {
		return lbl_Hours;
	}

	public void setLbl_Hours(JLabel lbl_Hours) {
		this.lbl_Hours = lbl_Hours;
	}

	public JButton getBtn_Save() {
		return btn_Save;
	}

	public void setBtn_Save(JButton btn_Save) {
		this.btn_Save = btn_Save;
	}

	public JTextArea getTa_Description() {
		return ta_Description;
	}

	public void setTa_Description(JTextArea ta_Description) {
		this.ta_Description = ta_Description;
	}

	public JSpinner getS_Hours() {
		return s_Hours;
	}

	public void setS_Hours(JSpinner s_Hours) {
		this.s_Hours = s_Hours;
	}

	public JCheckBox getCb_Check() {
		return cb_Check;
	}

	public void setCb_Check(JCheckBox cb_Check) {
		this.cb_Check = cb_Check;
	}
}
