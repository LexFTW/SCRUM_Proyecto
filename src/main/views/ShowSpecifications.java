package main.views;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import main.interfaces.IProject;
import main.interfaces.IUser;
import main.models.Project;
import main.models.Specification;
import net.miginfocom.swing.MigLayout;

public class ShowSpecifications implements ActionListener{

	private IUser iuser;
	private IProject iproject;
	private Project project;
	private MainFrame frame;
	private JPanel panelScrollable;
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
		this.specifications.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		this.specifications.setLayoutOrientation(JList.VERTICAL);
		this.specifications.setSize(1, 1);
		this.specifications.setVisible(true);

		this.panelScrollable = new JPanel(new MigLayout());
		for (int i = 0; i < 2; i++) { // Cambiar el 2 por un método que de un int contando todas las especificaciones que hay.
			this.panelScrollable.add(new SpecificationPane("D"), "wrap, split 3, pushx, growx");
		}
		
		this.sp_SpecificationPane = new JScrollPane(this.panelScrollable);
		this.sp_SpecificationPane.setVisible(true);
		
		this.frame.getInternalFrame().setAutoscrolls(true);
		this.frame.getInternalFrame().add(this.btn_Add, "split 3, align center");
		this.frame.getInternalFrame().add(this.btn_Save);
		this.frame.getInternalFrame().add(this.btn_Remove, "wrap");
		this.frame.getInternalFrame().add(this.sp_SpecificationPane, "wrap, pushx, growx");
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
