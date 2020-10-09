package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Bet;
import domain.Event;
import domain.Odd;
import domain.Question;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class ProfitGUI extends JFrame {

	private JPanel contentPane;
	private JLabel lblProfit;
	private JLabel lblProfitNumber;
	private JButton btnCalculate;
	private JLabel lblMoneyPlayed;
	private JLabel lblPlayedNumber;
	private JLabel lblBets;
	private JLabel lblBetsNumber;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public ProfitGUI(final Event ev) {
		setTitle(ResourceBundle.getBundle("Etiquetas").getString("Profit"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblProfit = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Profit"));
		lblProfit.setBounds(107, 152, 73, 30);
		contentPane.add(lblProfit);

		lblProfitNumber = new JLabel("");
		lblProfitNumber.setHorizontalAlignment(SwingConstants.LEFT);
		lblProfitNumber.setBounds(194, 152, 67, 30);
		contentPane.add(lblProfitNumber);

		btnCalculate = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Calculate"));
		btnCalculate.setBounds(164, 215, 97, 25);
		contentPane.add(btnCalculate);

		lblMoneyPlayed = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("MoneyPlayed"));
		lblMoneyPlayed.setBounds(66, 109, 96, 16);
		contentPane.add(lblMoneyPlayed);

		lblPlayedNumber = new JLabel("");
		lblPlayedNumber.setBounds(194, 109, 56, 16);
		contentPane.add(lblPlayedNumber);

		lblBets = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
		lblBets.setBounds(106, 56, 56, 16);
		contentPane.add(lblBets);

		lblBetsNumber = new JLabel("");
		lblBetsNumber.setBounds(194, 56, 56, 16);
		contentPane.add(lblBetsNumber);
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				float dirua = 0;
				float emandakoDirua = 0;
				float jokatutakoDirua = 0;
				int kont = 0;
				BLFacade bl = IntroGUI.getBusinessLogic();
				Vector<Question> queries = ev.getQuestions();
				for (domain.Question q : queries) {
					Collection<Odd> odds = q.getOdds();
					for (domain.Odd o : odds) {
						Collection<Bet> bets = bl.getBets(o);
						for (domain.Bet b : bets) {
							kont++;
							if (b.getSituation().equals("Irabazi")) {
								emandakoDirua = emandakoDirua + b.getBetMoney() * b.getFee();
								jokatutakoDirua += b.getBetMoney();
							}
							if (b.getSituation().equals("Galdu")) {
								jokatutakoDirua = jokatutakoDirua + b.getBetMoney();
							}
						}
					}
				}
				dirua = jokatutakoDirua - emandakoDirua;
				lblBetsNumber.setText(" " + kont);
				lblPlayedNumber.setText("" + jokatutakoDirua);
				lblProfitNumber.setText("" + dirua);
			}
		});

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
