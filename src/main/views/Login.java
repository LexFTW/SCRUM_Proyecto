package main.views;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import main.interfaces.IUser;
import main.models.User;

public class Login implements ActionListener{

	// Atributtes;
	private JLabel lbl_UserNickname;
	private JLabel lbl_UserPassword;
	private JLabel lbl_ErrorLogin;
	private JPasswordField pf_UserPassword;
	private JTextField tf_UserNickName;
	private JButton btn_ValidateLogin;
	
	private IUser iuser;
	private MainFrame frame;
	public Login(IUser iuser){
		this.frame = new MainFrame();
		frame.getJMenuBar().setVisible(false);
		frame.getLbl_UserLogged().setVisible(false);
		frame.getBtn_LogOut().setVisible(false);
		frame.setTitle(frame.getTitle() + iuser.getTitleConnection());
		this.iuser = iuser;
		this.lbl_UserNickname = new JLabel("Nombre de Usuario");
		this.lbl_UserPassword = new JLabel("Contraseña");
		this.lbl_ErrorLogin = new JLabel("Usuario y/o contraseña son incorrectos.");
		this.lbl_ErrorLogin.setForeground(Color.RED);
		this.lbl_ErrorLogin.setVisible(false);
		this.tf_UserNickName = new JTextField();
		this.pf_UserPassword = new JPasswordField();
		this.btn_ValidateLogin = new JButton("Iniciar Sesión");
		
		this.pf_UserPassword.addActionListener(this);
		this.tf_UserNickName.addActionListener(this);
		this.btn_ValidateLogin.addActionListener(this);
		
		frame.getInternalFrame().add(this.lbl_UserNickname, "wrap");
		frame.getInternalFrame().add(this.tf_UserNickName, "pushx, growx, wrap");
		frame.getInternalFrame().add(this.lbl_UserPassword, "wrap");
		frame.getInternalFrame().add(this.pf_UserPassword, "pushx, growx, wrap");
		frame.getInternalFrame().add(this.lbl_ErrorLogin, "wrap");
		frame.getInternalFrame().add(this.btn_ValidateLogin, "wrap, align right");
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(this.tf_UserNickName.getText().equals("") || this.pf_UserPassword.getPassword().length == 0) {
			this.lbl_ErrorLogin.setVisible(true);
		}else {
			this.lbl_ErrorLogin.setVisible(false);
			User user = iuser.getUserLogin(this.tf_UserNickName.getText(), String.valueOf(this.pf_UserPassword.getPassword()));
			
			if(user == null) {
				this.lbl_ErrorLogin.setVisible(true);
			}else {
				this.lbl_ErrorLogin.setVisible(false);
				frame.getInternalFrame().getContentPane().removeAll();
				frame.getInternalFrame().repaint();
				
				if(user.getPermissionID() == 1) {
					frame.getJMenuBar().remove(frame.getMenu_projects());
					new CreateUser(iuser, frame);
				}else {
					System.out.println("[INFO] - Aún estamos trabajando en las otras ventanas:");
				}
				
			}
		}
	}
	
}
