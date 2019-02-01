package main.views;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import main.interfaces.IUser;

public class ShowProjects implements ActionListener{
	
	private IUser iuser;
	private MainFrame frame;
	private JScrollPane sp;
	private JLabel lbl_Title;
	private JLabel lbl_ProductOwner;
	private JLabel lbl_ScrumMaster;
	private JButton btn_ShowSpecifications;
	
	public ShowProjects(IUser iuser, MainFrame frame) {
		this.iuser = iuser;
		this.frame = frame;
		this.frame.getInternalFrame().setVisible(true);
		this.frame.getInternalFrame().setSize(480, 260);
		// Center JInternalFrame with this:
		Dimension desktopSize = this.frame.getDesktopPane().getSize();
		Dimension jInternalFrameSize = this.frame.getInternalFrame().getSize();
		this.frame.getInternalFrame().setLocation((desktopSize.width - jInternalFrameSize.width)/2,
				(desktopSize.height- jInternalFrameSize.height)/2 - 40);
		this.frame.getInternalFrame().setTitle("Crear Usuario");
		this.frame.getInternalFrame().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		this.lbl_Title = new JLabel("Titulo del Proyecto");
		this.lbl_ProductOwner = new JLabel("Product Owner");
		this.lbl_ScrumMaster = new JLabel("Scrum Master");
		this.btn_ShowSpecifications = new JButton("Mostrar Especificaciones;");
		
		this.btn_ShowSpecifications.addActionListener(this);
		
		this.frame.getInternalFrame().add(this.lbl_Title, "wrap");
		this.frame.getInternalFrame().add(this.btn_ShowSpecifications, "wrap");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton) {
			JButton btn = (JButton) e.getSource();
			if(btn == btn_ShowSpecifications) {
				
			}
		}
	}

}
