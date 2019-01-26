package main.views;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import main.interfaces.IUser;

public class CreateUser implements ActionListener{

	private IUser iuser;
	private MainFrame frame;
	
	private JLabel lbl_UserName;
	private JLabel lbl_UserNickname;
	private JLabel lbl_UserPassword;
	private JLabel lbl_UserPasswordValidate;
	private JLabel lbl_UserEmail;
	
	private JTextField tf_UserName;
	private JTextField tf_UserNickname;
	private JTextField tf_UserEmail;

	private JPasswordField pf_UserPassword;
	private JPasswordField pf_UserPasswordValidate;
	
	private JButton btn_GeneratePassword;
	
	public CreateUser(IUser iuser, MainFrame frame) {
		this.iuser = iuser;
		this.frame = frame;
		this.frame.getInternalFrame().setTitle("Crear Usuario");
		this.frame.getJMenuBar().setVisible(true);
		this.frame.getLbl_UserLogged().setVisible(true);
		this.frame.getLbl_UserLogged().setText(this.frame.getLbl_UserLogged().getText() + iuser.getUserLogged().getUserNickname() + " (" + iuser.getUserLoggedPermission() + ")");
		this.frame.getBtn_LogOut().setVisible(true);
		
		this.lbl_UserName = new JLabel("Nombre Completo");
		this.lbl_UserNickname = new JLabel("Nombre de Usuario");
		this.lbl_UserPassword = new JLabel("Contraseña");
		this.lbl_UserPasswordValidate = new JLabel("Repite la Contraseña");
		this.lbl_UserEmail = new JLabel("Correo Electrónico");
		
		this.tf_UserName = new JTextField();
		this.tf_UserNickname = new JTextField();
		this.tf_UserNickname.setEditable(false);
		this.pf_UserPassword = new JPasswordField();
		this.pf_UserPasswordValidate = new JPasswordField();
		this.tf_UserEmail = new JTextField();
		
		this.btn_GeneratePassword = new JButton("Generar Contraseña");
		this.btn_GeneratePassword.addActionListener(this);
		
		this.frame.getInternalFrame().add(this.lbl_UserName);
		this.frame.getInternalFrame().add(this.tf_UserName, "span 3, wrap, pushx, growx");
		this.frame.getInternalFrame().add(this.lbl_UserNickname);
		this.frame.getInternalFrame().add(this.tf_UserNickname, "span 3, wrap, pushx, growx");
		this.frame.getInternalFrame().add(this.lbl_UserPassword);
		this.frame.getInternalFrame().add(this.pf_UserPassword, "pushx, growx, span 2");
		this.frame.getInternalFrame().add(this.btn_GeneratePassword, "wrap");
		this.frame.getInternalFrame().add(this.lbl_UserPasswordValidate);
		this.frame.getInternalFrame().add(this.pf_UserPasswordValidate, "span 3, wrap, pushx, growx");
		this.frame.getInternalFrame().add(this.lbl_UserEmail);
		this.frame.getInternalFrame().add(this.tf_UserEmail, "span 3, wrap, pushx, growx");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton) {
			JButton btn = (JButton) e.getSource();
			if(btn == btn_GeneratePassword) {
				String pwd = iuser.generatePassword();
				this.pf_UserPassword.setText(pwd);
				this.pf_UserPasswordValidate.setText(pwd);
			}
		}
		
	}
	
}
