package main.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.interfaces.IProject;
import main.interfaces.IUser;

public class ShowSpecifications implements ActionListener{

	private IUser iuser;
	private IProject iproject;
	private MainFrame frame;
	
	public ShowSpecifications(IUser iuser, IProject iproject, MainFrame frame) {
		this.iuser = iuser;
		this.iproject = iproject;
		this.frame = frame;
		this.frame.getInternalFrame().getContentPane().removeAll();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}

}
