package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import domain.Movement;
import domain.User;

import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextField;

public class SeeMovesGUI extends JFrame {

	private JPanel contentPane;
	private JLabel lblMoves = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Moves"));
	private JLabel lblMoney = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Money"));
	private JLabel lblAmount = new JLabel(""); //$NON-NLS-1$ //$NON-NLS-2$
	JButton btnErreplikatu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Replicate"));
	private JList moneyList;
	private JButton btnInsert = new JButton(ResourceBundle.getBundle("Etiquetas").getString("InsertWithdraw"));
	private User a;
	private DefaultListModel listModel = new DefaultListModel();
	private List<Movement> moveList;
	private JScrollPane scrollPane = new JScrollPane();
	private JButton btnSeeBets = new JButton(ResourceBundle.getBundle("Etiquetas").getString("SeeBets"));
	private FindQuestionsGUI f;

	/**
	 * Create the frame.
	 */
	public SeeMovesGUI(User us, final FindQuestionsGUI f) {
		this.f = f;
		this.a = us;
		moveList = new ArrayList<Movement>(a.getMovements());
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("Money"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 542, 307);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblMoves.setBounds(217, 24, 115, 16);
		contentPane.add(lblMoves);

		lblMoney.setBounds(64, 200, 73, 16);
		contentPane.add(lblMoney);

		lblAmount.setBounds(149, 201, 66, 15);
		contentPane.add(lblAmount);
		lblAmount.setText(Float.toString(a.getMoney()));

		moneyList = new JList();
		moneyList.setModel(listModel);
		scrollPane.setViewportView(moneyList);
		scrollPane.setBounds(22, 63, 452, 103);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		contentPane.add(scrollPane);

		btnInsert.setBounds(200, 196, 199, 25);
		contentPane.add(btnInsert);

		btnInsert.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				JFrame b = new InsertMoneyGUI(a, returnthis(), f);
				b.setVisible(true);
			}
		});

		btnSeeBets.setVisible(true);
		btnSeeBets.setBounds(338, 20, 114, 25);
		contentPane.add(btnSeeBets);

		btnSeeBets.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFrame seebets = new SeeBetsGUI(a, returnthis());
				seebets.setVisible(true);

			}

		});

		btnErreplikatu.setBounds(409, 197, 89, 23);
		btnErreplikatu.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFrame erreplikatu = new ReplicateGUI(a);
				erreplikatu.setVisible(true);

			}

		});
		contentPane.add(btnErreplikatu);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.renewMovements();
	}

	public void renewMovements() {
		listModel.clear();
		moveList.clear();
		moveList.addAll(a.getMovements());
		Collections.sort(moveList);
		Collections.reverse(moveList);
		for (Movement m : moveList) {
			listModel.addElement(m.toString());
		}
		lblAmount.setText(Float.toString(a.getMoney()));
	}

	public void updateUser(User u) {
		this.a = u;
		f.updateUser(u);
	}

	public SeeMovesGUI returnthis() {
		return this;
	}
}
