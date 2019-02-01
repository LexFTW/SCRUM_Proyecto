package main.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import main.implementations.ProjectSQLLocal;
import main.implementations.ProjectSQLRemote;
import main.interfaces.IProject;
import main.interfaces.IUser;
import main.models.Project;
import main.models.User;

public class CreateProjects implements ActionListener {

	private IUser iuser;
	private IProject iproject;
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

		if(this.iuser.getTitleConnection().contains("Online")) {
			this.iproject = new ProjectSQLRemote();
		}else {
			this.iproject = new ProjectSQLLocal();
		}
		
		this.frame.getInternalFrame().getContentPane().removeAll();
		this.frame.getInternalFrame().setTitle("Crear Proyecto");
		this.frame.getInternalFrame().setSize(480, 260);
		this.frame.getInternalFrame().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// Center JInternalFrame with this:
		Dimension desktopSize = this.frame.getDesktopPane().getSize();
		Dimension jInternalFrameSize = this.frame.getInternalFrame().getSize();
		this.frame.getInternalFrame().setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
				(desktopSize.height - jInternalFrameSize.height) / 2 - 40);
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

		ArrayList<User> scrums = iuser.getAllScrumMaster();
		ArrayList<User> owners = iuser.getAllProductOwner();
		ArrayList<String> scrumString = new ArrayList<>();
		ArrayList<String> productString = new ArrayList<>();
		
		for (User user : scrums) {
			scrumString.add(user.getUserID() + ". " + user.getUserName());
		}
		
		for (User user : owners) {
			productString.add(user.getUserID() + ". " + user.getUserName());
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
		Project project;
		boolean found = false;
		if (e.getSource() instanceof JButton) {
			if (tf_Title.getText().length() == 0 || ta_Description.getText().length() == 0) {
				lbl_ErrorData.setText("Hay algun campo vacio");
			} else {
				lbl_ErrorData.setVisible(false);

				for (Project proyecto : iproject.getAllProjects()) {
					System.out.println(proyecto.getProjectName());
					if (proyecto.getProjectName().equalsIgnoreCase(tf_Title.getText())) {
						lbl_ErrorData.setText("El titulo esta repetido");
						lbl_ErrorData.setVisible(true);
						found = true;
					}
				}

				if (!found) {
					project = new Project();
					project.setProjectName(tf_Title.getText());
					project.setProjectDescription(ta_Description.getText());
					project.setScrumMasterID(Integer.parseInt(this.cb_ScrumMaster.getSelectedItem().toString().substring(0, 1)));
					project.setProductOwnerID(Integer.parseInt(this.cb_ProductOwner.getSelectedItem().toString().substring(0, 1)));
					iproject.insertProject(project);
				}
			}
		}
	}
}
