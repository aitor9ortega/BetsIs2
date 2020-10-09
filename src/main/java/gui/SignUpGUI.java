package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.User;
import exceptions.UserExist;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;

public class SignUpGUI extends JFrame {

	private JPanel contentPane;
	private JTextField nameField;
	private JTextField usernameField;
	private JPasswordField passField;
	private JPasswordField confirmpassField;
	private JButton btnSignUp = new JButton(ResourceBundle.getBundle("Etiquetas").getString("SignUp"));
	private JCheckBox chckbxIAccept = new JCheckBox(ResourceBundle.getBundle("Etiquetas").getString("IAccept"));
	private JLabel lblErrors;

	private BLFacade bl;
	private JLabel lblUsername = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Username"));
	private JLabel lblPassword = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Password"));
	private JLabel lblConfirmPassword = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ConfirmPassword"));
	private JLabel lblName = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Name"));

	/**
	 * Create the frame.
	 */
	public SignUpGUI() {

		setTitle(ResourceBundle.getBundle("Etiquetas").getString("SignUp"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblName.setBounds(30, 39, 80, 14);
		contentPane.add(lblName);

		nameField = new JTextField();
		nameField.setBounds(202, 36, 122, 20);
		contentPane.add(nameField);
		nameField.setColumns(10);

		lblUsername.setBounds(30, 78, 122, 14);
		contentPane.add(lblUsername);

		lblPassword.setBounds(30, 119, 107, 14);
		contentPane.add(lblPassword);

		lblConfirmPassword.setBounds(30, 158, 157, 14);
		contentPane.add(lblConfirmPassword);

		chckbxIAccept.setBounds(44, 210, 135, 23);
		contentPane.add(chckbxIAccept);

		btnSignUp.setBounds(187, 210, 116, 23);
		contentPane.add(btnSignUp);
		btnSignUp.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String name = nameField.getText();
				String username = usernameField.getText();
				String pass = passField.getText();
				String passconf = confirmpassField.getText();
				if (!pass.equals(passconf)) {
					lblErrors.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorDifferentPasswords"));
				} else if (!chckbxIAccept.isSelected()) {
					lblErrors.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorAcceptConditions"));
				} else {
					bl = IntroGUI.getBusinessLogic();
					try {
						bl.register(username, pass, name);
						close();
					} catch (UserExist e) {
						lblErrors
								.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorUserAlreadyRegistered"));
					}

				}

			}
		});

		usernameField = new JTextField();
		usernameField.setBounds(202, 75, 122, 20);
		contentPane.add(usernameField);
		usernameField.setColumns(10);

		passField = new JPasswordField();
		passField.setBounds(202, 116, 122, 20);
		contentPane.add(passField);
		passField.setColumns(10);

		confirmpassField = new JPasswordField();
		confirmpassField.setBounds(202, 158, 122, 20);
		contentPane.add(confirmpassField);
		confirmpassField.setColumns(10);

		lblErrors = new JLabel("");
		lblErrors.setBounds(123, 185, 262, 14);
		lblErrors.setForeground(Color.red);
		contentPane.add(lblErrors);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	/**
	 * This method closes the window, but not the application
	 */
	private void close() {
		this.setVisible(false);
		this.dispose();
	}
}