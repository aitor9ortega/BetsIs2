package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import businessLogic.BLFacade;
import domain.Bet;
import domain.Odd;
import domain.Person;
import domain.User;
import exceptions.MinAmountNotReached;
import exceptions.NegativeAmount;
import exceptions.NoMoney;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CreateBetGUI extends JFrame {

	private JPanel contentPane;
	private JTextField amountField;
	private JButton btnCreate  = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Create"));
	private JLabel lblAmount  = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Amount"));
	private JLabel lblProfit = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Profit"));
	private JLabel lblProfitNumber;
	private JLabel errorLabel;
	private Collection<Odd> odds;
	private Person person;
	private float fee = 1;
	private FindQuestionsGUI f;

	/**
	 * Create the frame.
	 */
	public CreateBetGUI(Collection<Odd> betOdds,Person p, final FindQuestionsGUI f) {
		this.f=f;
		this.odds=betOdds;
		for(Odd o : betOdds) fee *= o.getFee();
		this.person=p;
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("Bet"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		btnCreate.setBounds(159, 201, 97, 25);
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				BLFacade bl= IntroGUI.getBusinessLogic();
				User u=((User)person);
				float amount=Float.parseFloat(amountField.getText());
				try {
					if(amount>u.getMoney())
						throw new NoMoney();
					User us=bl.addBet(u,odds,amount,fee);
					f.updateUser(us);
					close();
				}catch(NoMoney nm) {
					errorLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("NoEnoughMoney"));
				}catch(MinAmountNotReached e) {
					errorLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorNotMinumunBet"));
				}
			}});
		contentPane.add(btnCreate);
		
		
		lblAmount.setBounds(42, 45, 97, 16);
		contentPane.add(lblAmount);
		
		amountField = new JTextField();
		amountField.setBounds(193, 42, 162, 22);
		amountField.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
			    egin();
			  }
			  public void removeUpdate(DocumentEvent e) {
			    egin();
			  }
			  public void insertUpdate(DocumentEvent e) {
			    egin();
			  }
			public void egin() {
				// TODO Auto-generated method stub
				errorLabel.setText("");
				float i;
				try {
					i=(float)Double.parseDouble(amountField.getText());
					if(i<0)
						throw new NegativeAmount();
					lblProfitNumber.setText(ResourceBundle.getBundle("Etiquetas").getString("BetProfit")+" "+ (fee*i));
					btnCreate.setEnabled(true);
				}catch(NegativeAmount a) {
					errorLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorNegativeAmount"));
					btnCreate.setEnabled(false);
				}
				catch(Exception e) {
					errorLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorIncorrectAmount"));
					btnCreate.setEnabled(false);
				}
			}});
		contentPane.add(amountField);
		amountField.setColumns(10);
		 
		lblProfit.setBounds(42, 126, 69, 16);
		contentPane.add(lblProfit);
		
		lblProfitNumber = new JLabel("");
		lblProfitNumber.setBounds(159, 126, 213, 16);
		contentPane.add(lblProfitNumber);
		
		errorLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreateBetGUI.lblNewLabel.text"));
		errorLabel.setBounds(104, 172, 233, 14);
		errorLabel.setForeground(Color.RED);
		contentPane.add(errorLabel);
		
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
