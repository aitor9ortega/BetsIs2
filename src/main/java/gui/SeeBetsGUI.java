package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Date;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Bet;
import domain.BetOddContainer;
import domain.MovementBet;
import domain.User;

import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextField;

public class SeeBetsGUI extends JFrame {

	private JPanel contentPane;
	private JLabel lblBet = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
	private JList moneyList;
	private JButton btnRem = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Remove"));
	private User a;
	private DefaultListModel listModel = new DefaultListModel();
	private Collection<BetOddContainer> betList;
	private JScrollPane scrollPane = new JScrollPane();
	private Bet b;
	private SeeMovesGUI seem;
	private BLFacade bl;

	/**
	 * Create the frame.
	 */
	public SeeBetsGUI(final User a, SeeMovesGUI s) {
		bl = IntroGUI.getBusinessLogic();
		this.a = a;
		this.seem = s;
		betList = bl.getBetOddContainers(a.getBets());
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 542, 307);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblBet.setBounds(217, 24, 115, 16);
		contentPane.add(lblBet);

		moneyList = new JList();
		moneyList.setModel(listModel);
		scrollPane.setViewportView(moneyList);
		scrollPane.setBounds(22, 63, 452, 103);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		contentPane.add(scrollPane);

		btnRem.setBounds(234, 196, 128, 25);
		contentPane.add(btnRem);

		btnRem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if (!moneyList.isSelectionEmpty()) {
					b = ((BetOddContainer) moneyList.getSelectedValue()).getBet();
					float dirua = b.getBetMoney();
					listModel.removeElement(moneyList.getSelectedValue());
					User u = bl.removeBet(a, b);
					seem.updateUser(u);
					seem.renewMovements();

				}

			}
		});

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.renewBets();
	}

	public void renewBets() {
		listModel.clear();
		for (BetOddContainer m : betList) {
			if (m.getBet().getSituation().equals("Not finished"))
				listModel.addElement(m);
		}
	}

	public SeeBetsGUI returnthis() {
		return this;
	}

}
