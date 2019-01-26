package main.views;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JComboBox;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;

import main.interfaces.IUser;
import main.models.User;

import javax.swing.JButton;
import java.awt.Color;


public class CreateUser_ extends JInternalFrame implements ActionListener{
	// VARIABLES OF THE CREATEUSER CLASS
	private JTextField txtName;
	private JTextField txtLogin;
	private JTextField txtMail;
	private JPasswordField txtRepeatPassword;
	private JPasswordField txtPassword;
	private JButton btnGeneratePassword;
	private JButton btnSave;
	private IUser iuser;
	private JLabel lblName;
	private JLabel lblLogin;
	private JLabel lblPassword;
	private JLabel lblRepeatPassword;
	private JLabel lblMail;
	private JComboBox comboBox;
	private JLabel lblWarning;
	
	public CreateUser_(IUser iuser) {
		this.iuser = iuser;
		this.setResizable(true);
		this.setMaximizable(true);
		this.setClosable(true);
		this.setIconifiable(true);
		this.setTitle("Create User");
		// THIS PART BELONGS TO THE VARIABLES FEATURES
		getContentPane().setForeground(Color.WHITE);
		
		lblName = new JLabel("Nombre:");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		lblLogin = new JLabel("Login generado:");
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		lblRepeatPassword = new JLabel("Repite Password:");
		lblRepeatPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		lblMail = new JLabel("Mail:");
		lblMail.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Seleccione un perfil de usuario", "Master Owner", "Scrum Master", "Developer", "Administrator"}));
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 11));
		setVisible(true);
		txtName = new JTextField(30);
		txtName.setColumns(10);
		
		txtLogin = new JTextField();
		txtLogin.setColumns(10);
		txtLogin.setEditable(false);
		
		txtMail = new JTextField();
		txtMail.setColumns(10);
		
		txtRepeatPassword = new JPasswordField();
		txtRepeatPassword.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setColumns(10);
		
		btnGeneratePassword = new JButton("Generar Password");
		btnGeneratePassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnGeneratePassword.addActionListener(this);
		
		btnSave = new JButton("Guardar");
		btnSave.addActionListener(this);
		
		lblWarning = new JLabel();
		
		// THIS PART BELONGS TO LAYOUT(GROUP LAYOUT)
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 14));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(35)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblRepeatPassword)
								.addComponent(lblPassword)
								.addComponent(lblLogin)
								.addComponent(lblName)
								.addComponent(lblMail))
							.addGap(34)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(txtMail, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
								.addComponent(txtLogin)
								.addComponent(txtName, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
								.addComponent(txtPassword)
								.addComponent(txtRepeatPassword))))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnSave, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnGeneratePassword, GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE))
					.addGap(36))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(32)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName)
						.addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(37)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLogin)
						.addComponent(txtLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(32)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword)
						.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnGeneratePassword))
					.addGap(32)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRepeatPassword)
						.addComponent(txtRepeatPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(31)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMail)
						.addComponent(txtMail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(36)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSave))
					.addContainerGap(154, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
		// THIS PART BELONGS TO LAYOUT(GROUP LAYOUT)
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JButton) {
			JButton btn = (JButton) e.getSource();
			if(btn == btnGeneratePassword) {
				String pwdGenerate = iuser.generatePassword();
				txtPassword.setText(pwdGenerate);
				txtRepeatPassword.setText(pwdGenerate);
			}else if(btn == btnSave) {
				generateLogin();
				validatePassword();
				validateMail();
				insertUser(generateLogin(), validatePassword(), validateMail());
			}
		}
	}
	
	private void insertUser(boolean generateLogin, boolean validatePassword, boolean validateMail) {
		if(validateMail) {
			System.out.println("Mailo gueno");
        		System.out.println("Contraseña guena");
    			User user = new User();
    			user.setUserName(txtName.getText().split(" ")[0]);
    			user.setUserLastname(txtName.getText().split(" ")[1] + txtName.getText().split(" ")[2]);
    			user.setUserNickname(txtLogin.getText());
    			user.setUserPassword(iuser.getHashingPassword(txtPassword.getPassword().toString()));
    			user.setUserEmail(txtMail.getText());
    			user.setPermissionID(1);
    			
    			System.out.println(user.toString());
    			
    			iuser.insertUser(user);
		}else {
			JOptionPane.showMessageDialog(null, "Email incorrecto", "Email incorrecto", JOptionPane.ERROR_MESSAGE);
        	txtMail.setText("");
		}
	}

	private boolean validateMail() {
		String email = txtMail.getText();
		Pattern pattern = Pattern.compile("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b");
        Matcher matcher = pattern.matcher(email);
        
        if(!matcher.find()) {
        	return false;
        }else {
        	return true;
        }
	}

	public boolean generateLogin() {
		String[] array = txtName.getText().split(" ");
		String login = array[0].charAt(0) + array[1];
		int aux = 0;
		
		for (User user : iuser.getAllUsers()) {
			if(user.getUserNickname().contains(login)) {
				aux++;
			}
		}
		
		if(aux == 0) {
			txtLogin.setText(login);
		} else {
			txtLogin.setText(login + aux);
		}
		
		return true;
	}
	
	public boolean validatePassword() {
		if(txtRepeatPassword !=  txtPassword) {
			return false;
		} else {
			lblWarning.setVisible(false);
			return true;
		}
	}
}
