package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.SortedMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.ArrayListItzuli;
import domain.Bet;
import domain.MovementBet;
import domain.User;

import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextField;

public class BestUserGUI extends JFrame {

	private JPanel contentPane;
	private JLabel lblBet = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Bai"));
	private JList moneyList;
	private DefaultListModel listModel = new DefaultListModel();
	private Collection<Bet> betList;
	private JScrollPane scrollPane = new JScrollPane();
	

	/**
	 * Create the frame.
	 */
	public BestUserGUI() {
		//this.seem = s;
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("Bai"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 542, 307);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		lblBet.setBounds(217, 24, 115, 16);
		contentPane.add(lblBet);
		
		moneyList = new JList ();
		moneyList.setModel(listModel);
		scrollPane.setViewportView(moneyList);
		scrollPane.setBounds(22, 63, 452, 103);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		contentPane.add(scrollPane);
		BLFacade bl = IntroGUI.getBusinessLogic();
		SortedMap<Float,ArrayListItzuli> map = bl.bestUser().getMap();
		for (float e : map.keySet()) {
		    Float key = e;
		    Collection<User> value = map.get(e).getList();
		    for(User u : value) {
		    	String bai = u.getUsername() + " : " + key;
		    	listModel.addElement(bai);
		    }
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	
	 
	public BestUserGUI returnthis() {
		return this;
	}
	
}
