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
import exceptions.NoFounds;
import exceptions.UsernameNoExist;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class ReplicateGUI extends JFrame {

	private JPanel contentPane;
	private JTextField usernameField;
	private BLFacade bl;
	private User u;
	private JLabel errorLabel;
	private JButton btnReplikatu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Replicate"));
	private JLabel lblErabiltzaileizena = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Username"));
	private JLabel lblAmount = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Amount"));
	private JTextField amountField;

	/**
	 * Create the frame.
	 */
	public ReplicateGUI(final User u) {
		this.u = u;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("Replicate"));
		usernameField = new JTextField();
		usernameField.setBounds(174, 71, 170, 20);
		contentPane.add(usernameField);
		usernameField.setColumns(10);

		lblErabiltzaileizena.setBounds(55, 74, 109, 14);
		contentPane.add(lblErabiltzaileizena);

		lblAmount.setBounds(55, 102, 71, 14);
		contentPane.add(lblAmount);

		btnReplikatu.setBounds(141, 182, 131, 23);
		contentPane.add(btnReplikatu);

		errorLabel = new JLabel("");
		errorLabel.setBounds(174, 158, 151, 14);
		errorLabel.setForeground(Color.RED);
		contentPane.add(errorLabel);

		amountField = new JTextField();
		amountField.setBounds(174, 102, 170, 20);
		contentPane.add(amountField);
		amountField.setColumns(10);
		btnReplikatu.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				errorLabel.setText("");
				bl = IntroGUI.getBusinessLogic();
				try {
					bl.replicate(u, usernameField.getText(), Double.parseDouble(amountField.getText()));
					close();
				} catch (NoFounds e) {
					errorLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("NoEnoughMoney"));
				} catch (UsernameNoExist e) {
					errorLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorIncorrectUsername"));

				}
			}
		});

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	private void close() {
		this.setVisible(false);
		this.dispose();
	}
}
