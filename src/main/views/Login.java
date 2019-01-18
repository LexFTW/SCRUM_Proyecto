package main.views;


import java.awt.EventQueue;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Color;

public class Login implements ActionListener{

	// VARIABLES DE LA CLASE LOGIN
	private JFrame frame;
	private JDesktopPane desktop;
	private JInternalFrame if1;
	private JLabel lblLogin;
	private JLabel lblPassword;
	private JTextField txtLogin;
	private JTextField txtPassword;
	private JButton btnSend;
	private JMenuBar menuBar;
	private JMenu mnProjects;
	private JMenu mnUsuarios;
	private JLabel lblUser;
	private JLabel lblNameUser;
	private JButton btnExit;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Gestor de proyectos");
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 750, 715);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		desktop = new JDesktopPane();
		desktop.setBackground(Color.WHITE);
		if1 = new JInternalFrame("Login", true, true, true, true);
		if1.setSize(500, 330);
		if1.setLocation(76, 69);
		desktop.add(if1);

		lblLogin = new JLabel("Login:");
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 14));

		lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));

		txtLogin = new JTextField();
		txtLogin.setColumns(10);
		txtLogin.addActionListener(this);

		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		txtPassword.addActionListener(this);

		btnSend = new JButton("Enviar");
		btnSend.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSend.addActionListener(this);
		
		lblUser = new JLabel("Usuario:");
		lblUser.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUser.setVisible(false);
		
		lblNameUser = new JLabel("No User");
		lblNameUser.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNameUser.setVisible(false);
		
		btnExit = new JButton("SALIR");
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnExit.setVisible(false);
		
		// PARTE LAYOUT(GROUP LAYOUT) DEL INTERNAL FRAME LOGIN
		GroupLayout groupLayout = new GroupLayout(if1.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addGap(87).addComponent(btnSend,
								GroupLayout.PREFERRED_SIZE, 310, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup().addGap(42)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(lblLogin)
										.addComponent(lblPassword))
								.addGap(86)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(txtLogin)
										.addComponent(txtPassword, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE))))
				.addContainerGap(121, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(63)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblLogin).addComponent(
						txtLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(49)
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addComponent(lblPassword).addComponent(
						txtPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
				.addComponent(btnSend, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE).addGap(64)));

		if1.getContentPane().setLayout(groupLayout);
		if1.setVisible(true);
		GroupLayout groupLayout_1 = new GroupLayout(frame.getContentPane());
		groupLayout_1.setHorizontalGroup(
			groupLayout_1.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout_1.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout_1.createSequentialGroup()
							.addComponent(lblUser)
							.addGap(18)
							.addComponent(lblNameUser, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnExit)
							.addGap(8))
						.addComponent(desktop, GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout_1.setVerticalGroup(
			groupLayout_1.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnExit)
						.addGroup(groupLayout_1.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNameUser)
							.addComponent(lblUser)))
					.addGap(26)
					.addComponent(desktop, GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
					.addContainerGap())
		);
		// PARTE LAYOUT(GROUP LAYOUT) DEL INTERNAL FRAME LOGIN

		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 684, 21);
		frame.setJMenuBar(menuBar);

		mnProjects = new JMenu("Proyectos");
		menuBar.add(mnProjects);

		mnUsuarios = new JMenu("Usuarios");
		menuBar.add(mnUsuarios);
		
		menuBar.setVisible(false);
		frame.getContentPane().setLayout(groupLayout_1);
		frame.setVisible(true);
	}
	
	public void enterPressed(ActionEvent e) {
		if1.setVisible(false);
		lblUser.setVisible(true);
		lblNameUser.setVisible(true);
		btnExit.setVisible(true);
		CreateUser cu1 = new CreateUser();
		cu1.setSize(500,500);
		cu1.setLocation(100, 5);
		desktop.add(cu1);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		enterPressed(e);
		
	}
	
	
}