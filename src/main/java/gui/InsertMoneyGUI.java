package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.User;
import exceptions.InvalidCard;
import exceptions.NegativeNumber;
import exceptions.NoFounds;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class InsertMoneyGUI extends JFrame {

	private JPanel contentPane;
	private JTextField tCC;
	private BLFacade bl;
	private JTextField tMoney;
	private JButton btnWithdraw = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Withdraw"));
	private JButton btnInsert = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Insert"));
	private User erab;
	private JLabel errorLabel;
	private SeeMovesGUI seem;
	private FindQuestionsGUI f;

	/**
	 * Create the frame.
	 * @param seeMovesGUI 
	 */
	public InsertMoneyGUI(User a, SeeMovesGUI seeMovesGUI, final FindQuestionsGUI f) {
		this.f=f;
		this.seem = seeMovesGUI;
		erab=a;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblActualMoney = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ActualMoney"));
		lblActualMoney.setBounds(58, 46, 130, 16);
		contentPane.add(lblActualMoney);
		
		JLabel lblCreditCard = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreditCard"));
		lblCreditCard.setBounds(58, 102, 102, 16);
		contentPane.add(lblCreditCard);
		
		JLabel lblInsert = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("IWMoney"));
		lblInsert.setBounds(58, 163, 146, 16);
		contentPane.add(lblInsert);
		
		tCC = new JTextField();
		tCC.setBounds(232, 99, 146, 22);
		contentPane.add(tCC);
		tCC.setColumns(10);
		
		tMoney = new JTextField();
		tMoney.setBounds(232, 160, 146, 22);
		contentPane.add(tMoney);
		tMoney.setColumns(10);
		
		JLabel lablMoneyNumber = new JLabel("");
		lablMoneyNumber.setText(Float.toString(erab.getMoney()));
		lablMoneyNumber.setBounds(232, 46, 146, 22);
		contentPane.add(lablMoneyNumber);
		
		btnInsert.setBounds(58, 215, 130, 25);
		contentPane.add(btnInsert);
		btnInsert.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				try {
					if(Integer.parseInt(tMoney.getText())<=0) {
						throw new NegativeNumber();
					}
					if(Integer.parseInt(tCC.getText())<=0) {
						throw new InvalidCard();
					}
					bl=IntroGUI.getBusinessLogic();
					User u=bl.changeMoney(erab,Integer.parseInt(tMoney.getText()));
					f.updateUser(u);
					seem.updateUser(u);
					seem.renewMovements();
					close();
				}catch(Exception e){
					errorLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorNumber"));
				}
				
			}
		});
		
		btnWithdraw.setBounds(236, 215, 124, 25);
		contentPane.add(btnWithdraw);
		btnWithdraw.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				try {
					if(Integer.parseInt(tMoney.getText())<=0) {
						throw new NegativeNumber();
					}
					if(Integer.parseInt(tCC.getText())<=0) {
						throw new InvalidCard();
					}
					if(Integer.parseInt(tMoney.getText())>erab.getMoney()) {
						throw new NoFounds();
					}
					bl=IntroGUI.getBusinessLogic();
					User u=bl.changeMoney(erab,Integer.parseInt(tMoney.getText())*-1 );
					f.updateUser(u);
					seem.updateUser(u);
					seem.renewMovements();
					close();
				}catch(Exception e){
					errorLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorNumber"));
				}
				
			}
		});
		
		errorLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("InsertMoneyGUI.lblNewLabel.text"));
		errorLabel.setBounds(91, 192, 269, 16);
		errorLabel.setForeground(Color.RED);
		contentPane.add(errorLabel);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	public void close() {
		this.setVisible(false);
		this.dispose();
	}
}
