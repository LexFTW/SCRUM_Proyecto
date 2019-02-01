package main.views;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import main.interfaces.IUser;

public class ShowProjects implements ActionListener{
	
	private IUser iuser;
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
	
	public ShowProjects(IUser iuser, MainFrame frame) {
		this.iuser = iuser;
		this.frame = frame;
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
		
		this.lbl_Title = new JLabel("Titulo del Proyecto");
		this.lbl_ProductOwner = new JLabel("Product Owner");
		this.lbl_ScrumMaster = new JLabel("Scrum Master");
		
		this.tf_Title = new JTextField();
		this.tf_ProductOnwer = new JTextField();
		this.tf_ScrumMaster = new JTextField();
		
		this.btn_ShowSpecifications = new JButton("Mostrar Especificaciones");
		
		this.btn_ShowSpecifications.addActionListener(this);
		
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
				
			}
		}
	}

}
