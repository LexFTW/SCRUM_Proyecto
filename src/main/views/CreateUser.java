package main.views;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;


public class CreateUser extends JInternalFrame{
	// VARIABLES OF THE CREATEUSER CLASS
	private JTextField txtName;
	private JTextField txtLogin;
	private JTextField txtMail;
	private JTextField txtRepeatPassword;
	private JTextField txtPassword;
	
	public CreateUser() {
		// THIS PART BELONGS TO THE VARIABLES FEATURES
		getContentPane().setForeground(Color.WHITE);
		
		JLabel lblName = new JLabel("Nombre:");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblLogin = new JLabel("Login generado:");
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblRepeatPassword = new JLabel("Repite Password:");
		lblRepeatPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblMail = new JLabel("Mail:");
		lblMail.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Seleccione un perfil de usuario", "Master Owner", "Scrum Master", "Developer", "Administrator"}));
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 11));
		setVisible(true);
		txtName = new JTextField();
		txtName.setColumns(10);
		
		txtLogin = new JTextField();
		txtLogin.setColumns(10);
		txtLogin.setEditable(false);
		
		txtMail = new JTextField();
		txtMail.setColumns(10);
		
		txtRepeatPassword = new JTextField();
		txtRepeatPassword.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		
		JButton btnGeneratePassword = new JButton("Generar Password");
		btnGeneratePassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JButton btnSave = new JButton("Guardar");
		
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
}
