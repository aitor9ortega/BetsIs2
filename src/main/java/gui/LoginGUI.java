package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Admin;
import domain.User;
import domain.Person;
import exceptions.BadPassword;
import exceptions.UsernameNoExist;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class LoginGUI extends JFrame {

	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private BLFacade bl;
	private JLabel errorLabel;

	private JLabel lblPasahitzaAhaztuDut = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Forgot"));
	private JButton btnLogeatu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Login"));
	private JLabel lblErabiltzaileizena = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Username"));
	private JLabel lblPasahitza = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Password"));

	/**
	 * Create the frame.
	 */
	public LoginGUI() {
		setTitle(ResourceBundle.getBundle("Etiquetas").getString("Login"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		usernameField = new JTextField();
		usernameField.setBounds(174, 71, 170, 20);
		contentPane.add(usernameField);
		usernameField.setColumns(10);

		lblErabiltzaileizena.setBounds(55, 74, 109, 14);
		contentPane.add(lblErabiltzaileizena);

		lblPasahitza.setBounds(55, 102, 71, 14);
		contentPane.add(lblPasahitza);

		passwordField = new JPasswordField();
		passwordField.setBounds(174, 102, 170, 20);
		contentPane.add(passwordField);

		lblPasahitzaAhaztuDut.setBounds(174, 138, 192, 14);
		contentPane.add(lblPasahitzaAhaztuDut);

		btnLogeatu.setBounds(141, 182, 131, 23);
		contentPane.add(btnLogeatu);

		errorLabel = new JLabel("");
		errorLabel.setBounds(87, 157, 257, 14);
		errorLabel.setForeground(Color.RED);
		contentPane.add(errorLabel);
		btnLogeatu.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				errorLabel.setText(" ");
				bl = IntroGUI.getBusinessLogic();
				Person u = null;

				try {
					u = bl.login(usernameField.getText(), passwordField.getText());
					Frame a = new FindQuestionsGUI(u);
					a.setVisible(true);
				} catch (UsernameNoExist e) {
					errorLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorIncorrectUsername"));
				} catch (BadPassword e) {
					errorLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorIncorrectPassword"));
				}
				System.out.println(u + " hemen dago");
			}
		});

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
