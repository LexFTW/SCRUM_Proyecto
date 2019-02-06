package main.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import main.interfaces.IUser;
import main.models.User;
import main.models.UserPermission;

public class CreateUser implements ActionListener {

	private IUser iuser;
	private MainFrame frame;

	private JLabel lbl_UserName;
	private JLabel lbl_UserNickname;
	private JLabel lbl_UserPassword;
	private JLabel lbl_UserPasswordValidate;
	private JLabel lbl_UserEmail;
	private JLabel lbl_Permission;
	private JLabel lbl_ErrorData;

	private JTextField tf_UserName;
	private JTextField tf_UserNickname;
	private JTextField tf_UserEmail;

	private JPasswordField pf_UserPassword;
	private JPasswordField pf_UserPasswordValidate;

	private JButton btn_GeneratePassword;
	private JButton btn_InsertUser;

	private JComboBox comboBox;

	public CreateUser(IUser iuser, MainFrame frame) {
		this.iuser = iuser;
		this.frame = frame;
		this.frame.getInternalFrame().getContentPane().removeAll();
		this.frame.getInternalFrame().setVisible(true);
		this.frame.getInternalFrame().setSize(480, 260);
		// Center JInternalFrame with this:
		Dimension desktopSize = this.frame.getDesktopPane().getSize();
		Dimension jInternalFrameSize = this.frame.getInternalFrame().getSize();
		this.frame.getInternalFrame().setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
				(desktopSize.height - jInternalFrameSize.height) / 2 - 40);
		this.frame.getInternalFrame().setTitle("Crear Usuario");
		this.frame.getInternalFrame().setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		this.lbl_UserName = new JLabel("Nombre Completo");
		this.lbl_UserNickname = new JLabel("Nombre de Usuario");
		this.lbl_UserPassword = new JLabel("Contraseña");
		this.lbl_UserPasswordValidate = new JLabel("Repite la Contraseña");
		this.lbl_UserEmail = new JLabel("Correo Electrónico");
		this.lbl_Permission = new JLabel("Asignar Permisos");
		this.lbl_ErrorData = new JLabel();
		this.lbl_ErrorData.setForeground(Color.RED);
		this.lbl_ErrorData.setVisible(false);

		this.tf_UserName = new JTextField();
		this.tf_UserNickname = new JTextField();
		this.tf_UserNickname.setEditable(false);
		this.pf_UserPassword = new JPasswordField();
		this.pf_UserPasswordValidate = new JPasswordField();
		this.tf_UserEmail = new JTextField();

		this.comboBox = new JComboBox<>();
		ArrayList<UserPermission> permissions = iuser.getAllPermissions();
		ArrayList<String> permissionName = new ArrayList<>();

		for (UserPermission permission : permissions) {
			permissionName.add(permission.getPermissionID() + ". " + permission.getPermissionName());
		}

		this.comboBox.setModel(new DefaultComboBoxModel(permissionName.toArray()));

		this.btn_GeneratePassword = new JButton("Generar Contraseña");
		this.btn_GeneratePassword.addActionListener(this);

		this.btn_InsertUser = new JButton("Crear Usuario");
		this.btn_InsertUser.addActionListener(this);

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
		this.frame.getInternalFrame().add(this.lbl_Permission);
		this.frame.getInternalFrame().add(this.comboBox, "wrap, pushx, growx, span 3");
		this.frame.getInternalFrame().add(this.lbl_ErrorData, "wrap, span 5");
		this.frame.getInternalFrame().add(this.btn_InsertUser, "span 5, align right, wrap");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) {
			JButton btn = (JButton) e.getSource();
			if (btn == btn_GeneratePassword) {
				String pwd = iuser.generatePassword();
				this.pf_UserPassword.setText(pwd);
				this.pf_UserPasswordValidate.setText(pwd);
			} else if (btn == btn_InsertUser) {
				if (this.tf_UserName.getText().length() == 0 || this.tf_UserEmail.getText().length() == 0
						|| this.pf_UserPassword.getPassword().length == 0
						|| this.pf_UserPasswordValidate.getPassword().length == 0) {
					// It can't create the user because in some fields haven't data
					this.lbl_ErrorData.setText("No se puede crear el usuario porque faltan datos.");
					this.lbl_ErrorData.setVisible(true);
				} else {
					this.lbl_ErrorData.setVisible(false);
					if (generateUsername()) {
						if (validatePassword()) {
							if (validateEmail()) {
								createUser();
							}
						}
					}
				}
			}
		}
	}

	private void createUser() {
		JOptionPane.showMessageDialog(null, "Nombre de Usuario: " + this.tf_UserNickname.getText() + "\nContraseña: "
				+ String.valueOf(this.pf_UserPassword.getPassword()), null, JOptionPane.PLAIN_MESSAGE);

		User user = new User();
		user.setUserName(this.tf_UserName.getText().split(" ")[0]);
		String lastname = "";
		System.out.println(Arrays.toString(this.tf_UserName.getText().split(" ")));
		System.out.println(this.tf_UserName.getText().split(" ").length);
		for (int i = 1; i < this.tf_UserName.getText().split(" ").length; i++) {
			lastname = lastname + " " + this.tf_UserName.getText().split(" ")[i];
		}
		user.setUserLastname(lastname);
		user.setUserNickname(this.tf_UserNickname.getText());
		user.setUserPassword(iuser.getHashingPassword(String.valueOf(this.pf_UserPassword.getPassword())));
		user.setUserEmail(this.tf_UserEmail.getText());
		user.setPermissionID(Integer.parseInt(this.comboBox.getSelectedItem().toString().substring(0, 1)));
		iuser.insertUser(user, true);

		this.tf_UserEmail.setText("");
		this.tf_UserName.setText("");
		this.tf_UserNickname.setText("");
		this.pf_UserPassword.setText("");
		this.pf_UserPasswordValidate.setText("");
	}

	private boolean validateEmail() {
		String email = tf_UserEmail.getText();
		Pattern pattern = Pattern.compile("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b");
		Matcher matcher = pattern.matcher(email);

		if (!matcher.find()) {
			this.lbl_ErrorData.setText("El formato del correo es incorrecto.");
			this.lbl_ErrorData.setVisible(true);
			return false;
		} else {
			return true;
		}
	}

	private boolean validatePassword() {
		if (Arrays.equals(this.pf_UserPassword.getPassword(), this.pf_UserPasswordValidate.getPassword())) {
			this.lbl_ErrorData.setVisible(false);
			if (this.pf_UserPassword.getPassword().length < 6) {
				this.lbl_ErrorData.setText("La contraseña introducida no cumple con el minimo de caracteres.");
				this.lbl_ErrorData.setVisible(true);
				return false;
			} else {
				this.lbl_ErrorData.setVisible(false);
				return true;
			}
		} else {
			this.lbl_ErrorData.setText("Las contraseñas introducidas no son iguales.");
			this.lbl_ErrorData.setVisible(true);
			return false;
		}
	}

	private boolean generateUsername() {
		if (this.tf_UserName.getText().split(" ").length < 2) {
			System.out.println("El nombre añadido es demasiado corto para poder generar el usuario.");
			this.lbl_ErrorData.setText("El nombre añadido es demasiado corto para poder generar el usuario.");
			this.lbl_ErrorData.setVisible(true);
			return false;
		} else {
			this.lbl_ErrorData.setVisible(false);
			String nickname = this.tf_UserName.getText().split(" ")[0].toUpperCase().charAt(0)
					+ this.tf_UserName.getText().split(" ")[1].substring(0, 1).toUpperCase()
					+ this.tf_UserName.getText().split(" ")[1].substring(1).toLowerCase();

			int count = 0;

			for (User user : iuser.getAllUsers()) {
				if (user.getUserNickname().contains(nickname)) {
					count++;
				}
			}

			if (count != 0) {
				nickname = nickname + count;
			}

			this.tf_UserNickname.setText(nickname);
			return true;
		}
	}

}
