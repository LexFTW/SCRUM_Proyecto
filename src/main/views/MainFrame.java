package main.views;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class MainFrame extends JFrame{

	// Attributes;
	private JDesktopPane desktopPane;
	private JInternalFrame internalFrame;
	private JPanel panel;
	private JMenuBar menuBar;
	private JMenu menu_projects;
	private JMenu menu_users;
	private JLabel lbl_UserLogged;
	private JButton btn_LogOut;
	
	MainFrame(){
		// Initialize components "Menu":
		this.menuBar = new JMenuBar();
		this.menu_projects = new JMenu("Proyectos");
		this.menu_users = new JMenu("Usuarios");
		
		this.menuBar.add(menu_projects);
		this.menuBar.add(menu_users);
		
		this.panel = new JPanel(new MigLayout());
		this.panel.setBackground(Color.WHITE);
		
		this.lbl_UserLogged = new JLabel("Usuario: ");
		this.btn_LogOut = new JButton("Cerrar Sesión");
		
		this.desktopPane = new JDesktopPane();
		this.desktopPane.setSize(720, 480);
		
		this.internalFrame = new JInternalFrame("Login", true, true, true);
		this.internalFrame.setLayout(new MigLayout("insets 15"));
		this.internalFrame.setSize(360, 240);
		this.internalFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.internalFrame.setVisible(true);
		this.desktopPane.add(this.internalFrame);
		
		// Center JInternalFrame with this:
		Dimension desktopSize = desktopPane.getSize();
		Dimension jInternalFrameSize = internalFrame.getSize();
		internalFrame.setLocation((desktopSize.width - jInternalFrameSize.width)/2 - 10,
		    (desktopSize.height- jInternalFrameSize.height)/2 - 50);
		
		this.panel.add(this.lbl_UserLogged, "align right");
		this.panel.add(this.btn_LogOut, "wrap");
		this.panel.add(this.desktopPane, "pushx, growx, pushy, growy, span 2, wrap");
		this.setJMenuBar(menuBar);
		this.setTitle("Gestión de Proyectos SCRUM");
		this.setSize(720, 480);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(this.panel);
		this.setVisible(true);
	}

	public JDesktopPane getDesktopPane() {
		return desktopPane;
	}

	public JInternalFrame getInternalFrame() {
		return internalFrame;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setDesktopPane(JDesktopPane desktopPane) {
		this.desktopPane = desktopPane;
	}

	public void setInternalFrame(JInternalFrame internalFrame) {
		this.internalFrame = internalFrame;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public JMenuBar getMenuBar() {
		return menuBar;
	}
	
	public JMenu getMenu_projects() {
		return menu_projects;
	}

	public JMenu getMenu_users() {
		return menu_users;
	}

	public void setMenu_projects(JMenu menu_projects) {
		this.menu_projects = menu_projects;
	}

	public void setMenu_users(JMenu menu_users) {
		this.menu_users = menu_users;
	}

	public JLabel getLbl_UserLogged() {
		return lbl_UserLogged;
	}

	public JButton getBtn_LogOut() {
		return btn_LogOut;
	}

	public void setMenuBar(JMenuBar menuBar) {
		this.menuBar = menuBar;
	}

	public void setLbl_UserLogged(JLabel lbl_UserLogged) {
		this.lbl_UserLogged = lbl_UserLogged;
	}

	public void setBtn_LogOut(JButton btn_LogOut) {
		this.btn_LogOut = btn_LogOut;
	}
}
