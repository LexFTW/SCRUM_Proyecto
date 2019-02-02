package main.views;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import main.implementations.ProjectSQLLocal;
import main.implementations.ProjectSQLRemote;
import main.interfaces.IProject;
import main.interfaces.IUser;
import main.models.Project;

public class ShowProjects implements ActionListener{
	
	private IUser iuser;
	private IProject iproject;
	private MainFrame frame;
	private JScrollPane sp;
	private JLabel lbl_Title;
	private JLabel lbl_ProductOwner;
	private JLabel lbl_ScrumMaster;
	private JButton btn_ShowSpecifications;
	private JTextField tf_Title;
	private JTextField tf_ProductOnwer;
	private JTextField tf_ScrumMaster;
	private JTextArea ta_Description;
	private JTable table;
	private DefaultTableModel dtm;
	private Project project;
	
	public ShowProjects(IUser iuser, MainFrame frame) {
		this.iuser = iuser;
		this.frame = frame;

		if(this.iuser.getTitleConnection().contains("Online")) {
			this.iproject = new ProjectSQLRemote();
		}else {
			this.iproject = new ProjectSQLLocal();
		}
		
		this.frame.getInternalFrame().getContentPane().removeAll();
		this.frame.getInternalFrame().setVisible(true);
		this.frame.getInternalFrame().setSize(480, 260);
		// Center JInternalFrame with this:
		Dimension desktopSize = this.frame.getDesktopPane().getSize();
		Dimension jInternalFrameSize = this.frame.getInternalFrame().getSize();
		this.frame.getInternalFrame().setLocation((desktopSize.width - jInternalFrameSize.width)/2,
				(desktopSize.height- jInternalFrameSize.height)/2 - 40);
		this.frame.getInternalFrame().setTitle("Mostrar Proyectos");
		this.frame.getInternalFrame().setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		this.table = new JTable();
		
		String[] cabecera = {"Proyectos"};
		
		this.dtm = new DefaultTableModel(cabecera, 0);
		this.table.setModel(dtm);
		
		this.sp = new JScrollPane();
		this.sp.setViewportView(this.table);
		
		this.addRowsProjects();
		
		this.lbl_Title = new JLabel("Titulo del Proyecto");
		this.lbl_ProductOwner = new JLabel("Product Owner");
		this.lbl_ScrumMaster = new JLabel("Scrum Master");
		
		this.tf_Title = new JTextField();
		this.tf_ProductOnwer = new JTextField();
		this.tf_ScrumMaster = new JTextField();
		
		this.btn_ShowSpecifications = new JButton("Mostrar Especificaciones");
		this.btn_ShowSpecifications.addActionListener(this);
		this.table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(table.getSelectedRow() > -1) {
					project = iproject.getProject(table.getValueAt(table.getSelectedRow(), 0).toString());
					tf_Title.setText(project.getProjectName());
					tf_ProductOnwer.setText(iproject.getProductOwner(project.getProductOwnerID()));
					tf_ScrumMaster.setText(iproject.getScrumMaster(project.getScrumMasterID()));
				}
			}
		});
		
		this.frame.getInternalFrame().add(this.sp, "span 1 5");
		this.frame.getInternalFrame().add(this.lbl_Title);
		this.frame.getInternalFrame().add(this.tf_Title, "wrap, pushx, growx");
		this.frame.getInternalFrame().add(this.lbl_ProductOwner);
		this.frame.getInternalFrame().add(this.tf_ProductOnwer, "wrap, pushx, growx");
		this.frame.getInternalFrame().add(this.lbl_ScrumMaster);
		this.frame.getInternalFrame().add(this.tf_ScrumMaster, "wrap, pushx, growx");
		this.frame.getInternalFrame().add(this.btn_ShowSpecifications, "wrap, span 2, align right");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton) {
			JButton btn = (JButton) e.getSource();
			if(btn == btn_ShowSpecifications) {
				if(this.tf_Title.getText().length() != 0 ||
						this.tf_ScrumMaster.getText().length() != 0 ||
						this.tf_ProductOnwer.getText().length() != 0) {
					
					new ShowSpecifications(iuser, iproject, frame);
				}
			}
		}
	}
	
	private void addRowsProjects() {
		if(iuser.getUserLogged().getPermissionID() == 2) {
			for (Project project : iproject.getAllProjects()) {
				Object[] data = {project.getProjectName()};
				this.dtm.addRow(data);
			}
		}else if(iuser.getUserLogged().getPermissionID() == 3 || iuser.getUserLogged().getPermissionID() == 4) {
			for (Project project : iproject.getAllProjects(iuser.getUserLogged().getUserID())) {
				Object[] data = {project.getProjectName()};
				this.dtm.addRow(data);
			}
		}
	}

}
