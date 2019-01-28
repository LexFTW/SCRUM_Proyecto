package main.views;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import main.interfaces.IUser;

public class CreateProjects implements ActionListener{
	
	private IUser iuser;
	private MainFrame frame;
	
	private JLabel lbl_Title;
	private JLabel lbl_Description;
	private JLabel lbl_ScrumMaster;
	private JLabel lbl_ProductOwner;
	
	private JTextField tf_Title;
	private JTextArea ta_Description;
	
	private JButton btn_ValidateProject;
	

	public CreateProjects(IUser iuser, MainFrame frame) {
		this.iuser = iuser;
		this.frame = frame;
		this.frame.getInternalFrame().setTitle("Crear Proyecto");
		this.frame.getInternalFrame().setSize(480, 260);
		this.frame.getInternalFrame().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// Center JInternalFrame with this:
		Dimension desktopSize = this.frame.getDesktopPane().getSize();
		Dimension jInternalFrameSize = this.frame.getInternalFrame().getSize();
		this.frame.getInternalFrame().setLocation((desktopSize.width - jInternalFrameSize.width)/2,
				(desktopSize.height- jInternalFrameSize.height)/2 - 40);
		this.frame.getInternalFrame().setVisible(true);
		
		this.lbl_Title = new JLabel("Nombre del Proyecto");
		this.lbl_Description = new JLabel("Descripción");
		this.lbl_ScrumMaster = new JLabel("Scrum Master");
		this.lbl_ProductOwner = new JLabel("Product Owner");
		
		this.tf_Title = new JTextField();
		this.ta_Description = new JTextArea();
		
		this.btn_ValidateProject = new JButton("Crear Proyecto");
		
		this.frame.getInternalFrame().add(this.lbl_Title);
		this.frame.getInternalFrame().add(this.tf_Title, "wrap, pushx, growx");
		this.frame.getInternalFrame().add(this.lbl_Description);
		this.frame.getInternalFrame().add(this.ta_Description, "wrap, pushx, pushy, growx, growy");
		this.frame.getInternalFrame().add(this.lbl_ScrumMaster, "wrap");
		this.frame.getInternalFrame().add(this.btn_ValidateProject, "wrap, span 2, align right");
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
}
