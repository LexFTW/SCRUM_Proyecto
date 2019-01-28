package main.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import main.interfaces.IUser;
import main.models.User;

public class CreateProjects implements ActionListener{
	
	private IUser iuser;
	private MainFrame frame;
	
	private JLabel lbl_Title;
	private JLabel lbl_Description;
	private JLabel lbl_ScrumMaster;
	private JLabel lbl_ProductOwner;
	private JLabel lbl_ErrorData;
	private JTextField tf_Title;
	private JTextArea ta_Description;
	private JComboBox cb_ScrumMaster;
	private JComboBox cb_ProductOwner;
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
		this.lbl_ErrorData = new JLabel();
		this.lbl_ErrorData.setForeground(Color.RED);
		
		this.tf_Title = new JTextField();
		this.ta_Description = new JTextArea();
		
		this.cb_ScrumMaster = new JComboBox<>();
		this.cb_ProductOwner = new JComboBox<>();
		
		ArrayList<User> scrums = iuser.getAllUsers();
		ArrayList<String> scrumString = new ArrayList<>();
		ArrayList<String> productString = new ArrayList<>();
		for (User user : scrums) {
			if(user.getPermissionID() == 2) {
				scrumString.add(user.getUserID() + ". " + user.getUserName());
			}else if(user.getPermissionID() == 3) {
				productString.add(user.getUserID() + ". " + user.getUserName());
			}
		}
		
		this.cb_ScrumMaster.setModel(new DefaultComboBoxModel(scrumString.toArray()));
		this.cb_ProductOwner.setModel(new DefaultComboBoxModel(productString.toArray()));
		
		this.btn_ValidateProject = new JButton("Crear Proyecto");
		this.btn_ValidateProject.addActionListener(this);
		
		this.frame.getInternalFrame().add(this.lbl_Title);
		this.frame.getInternalFrame().add(this.tf_Title, "wrap, pushx, growx");
		this.frame.getInternalFrame().add(this.lbl_Description);
		this.frame.getInternalFrame().add(this.ta_Description, "wrap, pushx, pushy, growx, growy");
		this.frame.getInternalFrame().add(this.lbl_ScrumMaster);
		this.frame.getInternalFrame().add(this.cb_ScrumMaster, "wrap, pushx, growx");
		this.frame.getInternalFrame().add(this.lbl_ProductOwner);
		this.frame.getInternalFrame().add(this.cb_ProductOwner, "wrap, pushx, growx");
		this.frame.getInternalFrame().add(this.lbl_ErrorData, "wrap, span 2");
		this.frame.getInternalFrame().add(this.btn_ValidateProject, "wrap, span 2, align right");
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
}
