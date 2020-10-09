package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Event;
import domain.Question;
import domain.User;
import domain.Odd;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.ScrollPaneConstants;

public class PublishResultGUI extends JFrame {

	private JPanel contentPane;
	private JTextField resultField;
	private JButton btnPublish = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Publish"));
	private Event event;
	private DefaultListModel questionListModel = new DefaultListModel();
	private Collection<Question> moveList;
	private JScrollPane scrollPaneQuestions = new JScrollPane();
	private JList questionList;
	private JPanel comboBoxentzat;
	private JScrollPane scrollPaneOdds = new JScrollPane();
	private final JLabel lblOdds = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Odds"));
	private ArrayList<JComboBox> comboBoxak;
	private ArrayList<DefaultComboBoxModel> listModelak;
	private BoxLayout boxLayout;

	/**
	 * Create the frame.
	 */
	public PublishResultGUI(Event e) {
		comboBoxentzat = new JPanel();
		boxLayout = new BoxLayout(comboBoxentzat, BoxLayout.Y_AXIS);
		comboBoxentzat.setLayout(boxLayout);
		comboBoxentzat.setSize(new Dimension(JComboBox.WIDTH, JComboBox.HEIGHT * e.getQuestions().size()));
		;
		comboBoxentzat.setAlignmentX(Component.LEFT_ALIGNMENT);
		this.event = e;
		this.comboBoxak = new ArrayList<JComboBox>();
		this.listModelak = new ArrayList<DefaultComboBoxModel>();
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("PublicResult"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnPublish.setBounds(161, 208, 97, 25);
		btnPublish.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				BLFacade bl = IntroGUI.getBusinessLogic();
				for (int i = 0; i < event.getQuestions().size(); i++) {
					Question q = bl.updateResult((Question) questionListModel.get(i),
							(Odd) comboBoxak.get(i).getSelectedItem());
					((Question) questionListModel.get(i)).setResult(q.getResult());
				}
				close();
			}
		});
		contentPane.add(btnPublish);

		questionList = new JList();
		questionList.setModel(questionListModel);
		questionList.setEnabled(false);
		;
		scrollPaneQuestions.setViewportView(questionList);
		scrollPaneQuestions.setBounds(22, 29, 179, 137);
		scrollPaneQuestions.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(scrollPaneQuestions);

		scrollPaneOdds.setBounds(222, 29, 179, 137);
		scrollPaneOdds.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneOdds.setViewportView(comboBoxentzat);
		contentPane.add(scrollPaneOdds);

		JLabel lblQuestions = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Queries"));
		lblQuestions.setBounds(62, 4, 127, 14);
		contentPane.add(lblQuestions);
		lblOdds.setBounds(279, 4, 46, 14);

		contentPane.add(lblOdds);

		for (Question q : this.event.getQuestions()) {
			questionListModel.addElement(q);
			this.comboBoxak.add(new JComboBox());

			this.listModelak.add(new DefaultComboBoxModel());
			this.comboBoxak.get(this.comboBoxak.size() - 1).setModel(this.listModelak.get(this.listModelak.size() - 1));
			comboBoxentzat.add(this.comboBoxak.get(this.comboBoxak.size() - 1));
			this.comboBoxak.get(this.comboBoxak.size() - 1).setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));
			for (Odd o : q.getOdds()) {
				this.listModelak.get(this.listModelak.size() - 1).addElement(o);
			}

		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	/**
	 * This method closes the window, but not the application
	 */
	public void close() {
		this.setVisible(false);
		this.dispose();
	}
}
