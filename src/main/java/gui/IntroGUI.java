package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

public class IntroGUI extends JFrame {

	private JPanel contentPane;

	private static final long serialVersionUID = 1L;

	private static BLFacade appFacadeInterface;

	private JRadioButton euskeraRadioButton;

	private JRadioButton englishRadioButton;

	private JRadioButton castellanoRadioButton;

	private final ButtonGroup buttonGroup = new ButtonGroup();

	private JLabel lbWelcome;

	public static BLFacade getBusinessLogic() {
		return appFacadeInterface;
	}

	public static void setBussinessLogic(BLFacade afi) {
		appFacadeInterface = afi;
	}

	/**
	 * Create the frame.
	 */
	public IntroGUI() {
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("W"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		final JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(62, 32, 294, 52);
		contentPane.add(btnLogin);
		btnLogin.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JFrame a = new LoginGUI();
				a.setVisible(true);
			}
		});

		final JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.setBounds(62, 95, 294, 52);
		contentPane.add(btnSignUp);
		btnSignUp.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JFrame a = new SignUpGUI();
				a.setVisible(true);
			}
		});

		final JButton btnQueryEvents = new JButton("Query events");
		btnQueryEvents.setBounds(62, 158, 294, 39);
		contentPane.add(btnQueryEvents);
		btnQueryEvents.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JFrame a = new FindQuestionsGUI(null);
				a.setVisible(true);
			}
		});

		lbWelcome = new JLabel("Welcome !");
		lbWelcome.setBounds(182, 13, 152, 14);
		contentPane.add(lbWelcome);

		euskeraRadioButton = new JRadioButton("Euskera");
		euskeraRadioButton.setBounds(33, 219, 127, 25);
		contentPane.add(euskeraRadioButton);

		euskeraRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Locale.setDefault(new Locale("eus"));
				System.out.println("Locale: " + Locale.getDefault());
				btnLogin.setText(ResourceBundle.getBundle("Etiquetas").getString("Login"));
				btnSignUp.setText(ResourceBundle.getBundle("Etiquetas").getString("SignUp"));
				lbWelcome.setText(ResourceBundle.getBundle("Etiquetas").getString("Welcome"));
				btnQueryEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryEvents"));
			}
		});
		buttonGroup.add(euskeraRadioButton);

		englishRadioButton = new JRadioButton("English", true);
		englishRadioButton.setBounds(164, 219, 127, 25);
		contentPane.add(englishRadioButton);

		englishRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Locale.setDefault(new Locale("en"));
				System.out.println("Locale: " + Locale.getDefault());
				btnLogin.setText(ResourceBundle.getBundle("Etiquetas").getString("Login"));
				btnSignUp.setText(ResourceBundle.getBundle("Etiquetas").getString("SignUp"));
				lbWelcome.setText(ResourceBundle.getBundle("Etiquetas").getString("Welcome"));
				btnQueryEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryEvents"));
			}
		});
		buttonGroup.add(englishRadioButton);

		castellanoRadioButton = new JRadioButton("Castellano");
		castellanoRadioButton.setBounds(297, 219, 127, 25);
		contentPane.add(castellanoRadioButton);

		castellanoRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Locale.setDefault(new Locale("es"));
				System.out.println("Locale: " + Locale.getDefault());
				btnLogin.setText(ResourceBundle.getBundle("Etiquetas").getString("Login"));
				btnSignUp.setText(ResourceBundle.getBundle("Etiquetas").getString("SignUp"));
				lbWelcome.setText(ResourceBundle.getBundle("Etiquetas").getString("Welcome"));
				btnQueryEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryEvents"));

			}
		});
		buttonGroup.add(castellanoRadioButton);

	}

}
