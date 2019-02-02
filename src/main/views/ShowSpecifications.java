package main.views;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import main.interfaces.IProject;
import main.interfaces.IUser;
import main.models.Project;

public class ShowSpecifications implements ActionListener{

	private IUser iuser;
	private IProject iproject;
	private Project project;
	private MainFrame frame;
	
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
		
		
		this.frame.getInternalFrame().add(new SpecificationPane("Texto de prueba"), "wrap, pushx, growx");
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
