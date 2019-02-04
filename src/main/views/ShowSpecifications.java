package main.views;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import main.interfaces.IProject;
import main.interfaces.IUser;
import main.models.Project;

public class ShowSpecifications implements ActionListener{

	private IUser iuser;
	private IProject iproject;
	private Project project;
	private MainFrame frame;
	private JScrollPane sp_SpecificationPane;
	private JList<SpecificationPane> specifications;
	private JButton btn_Save;
	private JButton btn_Add;
	private JButton btn_Remove;
	
	public ShowSpecifications(IUser iuser, IProject iproject, MainFrame frame) {
		this.iuser = iuser;
		this.iproject = iproject;
		this.frame = frame;
		this.frame.getInternalFrame().getContentPane().removeAll();
		this.frame.getInternalFrame().setVisible(true);
		this.frame.getInternalFrame().setSize(480, 260);
		// Center JInternalFrame with this:
		Dimension desktopSize = this.frame.getDesktopPane().getSize();
		Dimension jInternalFrameSize = this.frame.getInternalFrame().getSize();
		this.frame.getInternalFrame().setLocation((desktopSize.width - jInternalFrameSize.width)/2,
				(desktopSize.height- jInternalFrameSize.height)/2 - 40);
		this.frame.getInternalFrame().setTitle("Especificaciones");
		this.frame.getInternalFrame().setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		this.btn_Save = new JButton("Guardar");
		this.btn_Add = new JButton("Añadir");
		this.btn_Remove = new JButton("Eliminar");
		
		this.btn_Add.addActionListener(this);
		
		this.specifications = new JList<>();
		this.specifications.add(new SpecificationPane("Texto de prueba"));
		this.specifications.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		this.specifications.setLayoutOrientation(JList.VERTICAL);
		this.specifications.setSize(1, 1);
		this.specifications.setVisible(true);
		
		this.sp_SpecificationPane = new JScrollPane(this.specifications);
		this.sp_SpecificationPane.setPreferredSize(new Dimension(700, 400));
		this.sp_SpecificationPane.setVisible(true);
		
		this.frame.getInternalFrame().add(this.btn_Add, "split 3, align center");
		this.frame.getInternalFrame().add(this.btn_Save);
		this.frame.getInternalFrame().add(this.btn_Remove, "wrap");
		
		for (int i = 0; i < iproject.getCountSpecifications(); i++) {
			this.frame.getInternalFrame().add(new SpecificationPane(iproject.getProjectSelected().getProjectDescription()), "wrap, pushx, growx, span 3");
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton) {
			JButton btn = (JButton) e.getSource();
			if(btn == btn_Add) {
				// # Añadir especificación
			}
		}
	}

}
