package main.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;

import main.interfaces.IUser;

public class ControlPanel implements ActionListener{

	private IUser iuser;
	private MainFrame frame;
	
	public ControlPanel(IUser iuser, MainFrame frame) {
		this.iuser = iuser;
		this.frame = frame;
		this.frame.getInternalFrame().setVisible(false);
		this.frame.getLbl_UserLogged().setText(this.frame.getLbl_UserLogged().getText() + iuser.getUserLogged().getUserNickname()
				+ " (" + iuser.getUserLoggedPermission() + ")");
		this.frame.getLbl_UserLogged().setVisible(true);
		this.frame.getBtn_LogOut().setVisible(true);
		
		if(iuser.getUserLogged().getPermissionID() == 1) {
			this.frame.getJMenuBar().remove(this.frame.getMenu_projects());
		}else if(iuser.getUserLogged().getPermissionID() == 2) {
			this.frame.getJMenuBar().remove(this.frame.getMenu_users());
		}else if(iuser.getUserLogged().getPermissionID() == 3) {
			
		}else if(iuser.getUserLogged().getPermissionID() == 4) {
			
		}

		this.frame.getJMenuBar().setVisible(true);
		this.frame.getMenu_users_create().addActionListener(this);
		this.frame.getBtn_LogOut().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JMenuItem) {
			JMenuItem mi = (JMenuItem) e.getSource();
			if(mi == this.frame.getMenu_users_create()) {
				new CreateUser(iuser, frame);
			}
		}else if(e.getSource() instanceof JButton) {
			JButton btn = (JButton) e.getSource();
			if(btn == this.frame.getBtn_LogOut()) {
				this.frame.dispose();
				new Login(iuser);
			}
		}
	}
	
}
