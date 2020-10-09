package gui;

import businessLogic.BLFacade;
import configuration.UtilDate;

import com.toedter.calendar.JCalendar;
import domain.Question;
import domain.User;
import domain.Bet;
import domain.MovementBet;
import domain.Odd;
import domain.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.text.DateFormat;
import java.util.*;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class FindQuestionsGUI extends JFrame {
	private static final long serialVersionUID = 1L;

	private final JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
	private final JLabel jLabelQueries = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Queries"));
	private final JLabel jLabelEvents = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Events"));

	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));

	// Code for JCalendar
	private Person person;
	private JCalendar jCalendar1 = new JCalendar();
	private Calendar calendarMio = null;
	private JScrollPane scrollPaneEvents = new JScrollPane();
	private JScrollPane scrollPaneQuestions = new JScrollPane();
	private JScrollPane scrollPaneOdds = new JScrollPane();
	private JScrollPane scrollPaneBets = new JScrollPane();

	private JTable tableEvents = new JTable();
	private JTable tableQueries = new JTable();
	private JTable tableOdds = new JTable();

	private DefaultTableModel tableModelEvents;
	private DefaultTableModel tableModelQueries;
	private DefaultTableModel tableModelOdds;
	private DefaultTableModel tableModelBets;

	private String[] columnNamesEvents = new String[] { ResourceBundle.getBundle("Etiquetas").getString("EventN"),
			ResourceBundle.getBundle("Etiquetas").getString("Event"),

	};
	private String[] columnNamesQueries = new String[] { ResourceBundle.getBundle("Etiquetas").getString("QueryN"),
			ResourceBundle.getBundle("Etiquetas").getString("Query")

	};
	private String[] columnNamesOdds = new String[] { ResourceBundle.getBundle("Etiquetas").getString("OddN"),
			ResourceBundle.getBundle("Etiquetas").getString("Odd")

	};

	private JButton btnCreateEvent = null;

	private JButton btnCreateQuestion = null;

	private JButton btnCreateOdd = null;

	private JLabel lblOdds;

	private Date currentDate = null;

	private JButton btnAccount = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Account"));
	private JButton btnBet = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Bet"));

	private JButton btnEmaitzakArgitaratu;
	private final JButton btnProfit = new JButton(ResourceBundle.getBundle("Etiquetas").getString("BetProfit"));
	private final JLabel labelError = new JLabel("");

	private JButton btnSelect = new JButton(ResourceBundle.getBundle("Etiquetas").getString("SelectBet"));
	private final JTable tableBets = new JTable();

	private Collection<Odd> betOdds = new ArrayList<Odd>();

	private JButton btnRemoveBet = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Remove"));

	private JButton btnRemoveEvent = new JButton();

	public FindQuestionsGUI(Person p) {
		try {
			person = p;
			jbInit();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method initialize all the elements of FindQuestionGUI
	 */
	private void jbInit() {

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(862, 508));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));

		jLabelEventDate.setBounds(new Rectangle(40, 15, 140, 25));
		jLabelQueries.setBounds(38, 223, 258, 14);
		jLabelEvents.setBounds(274, 19, 122, 16);

		this.getContentPane().add(jLabelEventDate, null);
		this.getContentPane().add(jLabelQueries);
		this.getContentPane().add(jLabelEvents);

		jButtonClose.setBounds(new Rectangle(274, 419, 130, 30));

		jButtonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButton2_actionPerformed(e);
			}
		});

		// private JButton getCreateEvent() {
		// if(btnCreateEvent == null) {
		// btnCreateEvent = new
		// JButton(ResourceBundle.getBundle("Etiquetas").getString("FindQuestionsGUI.btnNewButton.text"));
		// btnCreateEvent.setBounds(295, 219, 89, 23);
		// getContentPane().add(btnCreateEvent);
		// btnCreateEvent.addActionListener(new ActionListener(){
		//
		// @Override
		// public void actionPerformed(ActionEvent arg0) {
		// // TODO Auto-generated method stub
		// JFrame a = new CreateEventGUI();
		// a.setVisible(true);
		// }
		// });
		// }
		// return btnCreateEvent;
		// }

		this.getContentPane().add(jButtonClose, null);

		jCalendar1.setBounds(new Rectangle(40, 50, 225, 150));

		// Code for JCalendar
		this.jCalendar1.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent propertychangeevent) {

				if (propertychangeevent.getPropertyName().equals("locale")) {
					jCalendar1.setLocale((Locale) propertychangeevent.getNewValue());
				} else if (propertychangeevent.getPropertyName().equals("calendar")) {
					calendarMio = (Calendar) propertychangeevent.getNewValue();

					jCalendar1.setCalendar(calendarMio);
					Date firstDay = UtilDate.trim(new Date(jCalendar1.getCalendar().getTime().getTime()));
					currentDate = firstDay;
					renewEvents();
				}
			}
		});

		this.getContentPane().add(jCalendar1, null);

		scrollPaneEvents.setBounds(new Rectangle(292, 50, 346, 150));
		scrollPaneQuestions.setBounds(new Rectangle(33, 250, 371, 116));

		tableEvents.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				renewQuestions();
			}
		});

		scrollPaneEvents.setViewportView(tableEvents);
		tableModelEvents = new DefaultTableModel(null, columnNamesEvents);

		// tableEvents.setEnabled(false);
		tableEvents.setModel(tableModelEvents);
		tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);

		scrollPaneQuestions.setViewportView(tableQueries);
		tableModelQueries = new DefaultTableModel(null, columnNamesQueries);

		tableQueries.setModel(tableModelQueries);
		tableQueries.getColumnModel().getColumn(0).setPreferredWidth(100);
		tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);
		tableQueries.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				renewOdds();
			}
		});

		this.getContentPane().add(scrollPaneEvents, null);
		this.getContentPane().add(scrollPaneQuestions, null);

		btnAccount.setBounds(465, 15, 97, 25);
		getContentPane().add(btnAccount);

		btnAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JFrame a = new SeeMovesGUI((User) person, returnThis());
				a.setVisible(true);
			}
		});

		btnBet.setBounds(710, 354, 97, 25);
		getContentPane().add(btnBet);

		btnBet.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int i = tableOdds.getSelectedRow();
				JFrame a = new CreateBetGUI(betOdds, person, returnThis());
				a.setVisible(true);
			}
		});

		btnBet.setEnabled(false);

		lblOdds = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Odds"));
		lblOdds.setBounds(539, 233, 46, 14);
		getContentPane().add(lblOdds);

		scrollPaneOdds.setBounds(new Rectangle(292, 50, 346, 150));
		scrollPaneOdds.setBounds(475, 250, 163, 116);
		getContentPane().add(scrollPaneOdds);

		// tableOdds = new JTable();
		tableModelOdds = new DefaultTableModel(null, columnNamesOdds);
		tableOdds.setModel(tableModelOdds);
		tableOdds.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnSelect.setEnabled(true);
			}
		});
		scrollPaneOdds.setViewportView(tableOdds);

		btnCreateEvent = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Event+"));
		btnCreateEvent.setBounds(287, 213, 109, 23);
		getContentPane().add(btnCreateEvent);
		btnCreateEvent.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				labelError.setText("");
				JFrame a = new CreateEventGUI(currentDate, returnThis());
				a.setVisible(true);
			}
		});
		btnCreateEvent.setEnabled(false);

		btnCreateQuestion = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Query+"));
		btnCreateQuestion.setBounds(33, 379, 130, 23);
		getContentPane().add(btnCreateQuestion);
		btnCreateQuestion.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				labelError.setText("");
				int i = tableEvents.getSelectedRow();
				domain.Event ev = (domain.Event) tableModelEvents.getValueAt(i, 2); // obtain ev object
				JFrame a = new CreateQuestionGUI(ev, returnThis());
				a.setVisible(true);
			}
		});
		btnCreateQuestion.setEnabled(false);

		btnCreateOdd = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Odd+"));
		btnCreateOdd.setBounds(440, 379, 109, 23);
		getContentPane().add(btnCreateOdd);
		btnCreateOdd.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				labelError.setText("");
				Question q = (Question) tableModelQueries.getValueAt(tableQueries.getSelectedRow(), 2);
				JFrame a = new CreateOddGUI(q, returnThis());
				a.setVisible(true);
			}
		});
		btnCreateOdd.setEnabled(false);

		JLabel lblWelcome = new JLabel("");
		lblWelcome.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setBounds(552, 17, 122, 19);
		getContentPane().add(lblWelcome);

		btnEmaitzakArgitaratu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("EmaitzakArgitaratu"));
		btnEmaitzakArgitaratu.setBounds(400, 16, 148, 23);
		btnEmaitzakArgitaratu.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				labelError.setText("");
				int i = tableEvents.getSelectedRow();
				domain.Event ev = (domain.Event) tableModelEvents.getValueAt(i, 2); // obtain ev object
				if (ev.getQuestions().size() > 0 && ev.getQuestions().get(0).getResult() == null) {
					JFrame a = new PublishResultGUI(ev);
					a.setVisible(true);
				} else {
					labelError.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));
				}
			}
		});
		btnEmaitzakArgitaratu.setEnabled(false);
		getContentPane().add(btnEmaitzakArgitaratu);
		btnProfit.setBounds(549, 212, 89, 23);
		btnProfit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				labelError.setText("");
				int i = tableEvents.getSelectedRow();
				domain.Event ev = (domain.Event) tableModelEvents.getValueAt(i, 2); // obtain ev object

				if (ev.getQuestions().size() > 0 && ev.getQuestions().get(0).getResult() != null) {
					JFrame a = new ProfitGUI(ev);
					a.setVisible(true);

				} else
					labelError.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasNotFinished"));
			}
		});
		btnProfit.setEnabled(false);
		getContentPane().add(btnProfit);
		labelError.setBounds(475, 427, 163, 14);
		labelError.setForeground(Color.RED);
		getContentPane().add(labelError);

		scrollPaneBets.setBounds(682, 66, 140, 265);
		getContentPane().add(scrollPaneBets);

		scrollPaneBets.setViewportView(tableBets);

		tableModelBets = new DefaultTableModel(null, columnNamesOdds);
		tableBets.setModel(tableModelBets);
		tableBets.getColumnModel().getColumn(0).setPreferredWidth(100);
		tableBets.getColumnModel().getColumn(1).setPreferredWidth(268);
		tableBets.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnRemoveBet.setEnabled(true);
			}
		});

		btnSelect.setBounds(561, 378, 114, 25);
		getContentPane().add(btnSelect);
		btnSelect.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				labelError.setText("");
				int i = tableOdds.getSelectedRow();
				Odd o = (Odd) tableModelOdds.getValueAt(i, 2);
				BLFacade bl = IntroGUI.getBusinessLogic();
				Question q = bl.getQuestionOfOdd(o);
				if (q.getResult() != null) {
					labelError.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));
				} else {
					Vector<Object> row = new Vector<Object>();
					row.add(o.getFee());
					row.add(o.getResultBet());
					tableModelBets.setColumnCount(2);
					if (!betOdds.contains(o)) {
						betOdds.add(o);
						tableModelBets.addRow(row);
					}
					tableBets.getColumnModel().getColumn(0).setPreferredWidth(100);
					tableBets.getColumnModel().getColumn(1).setPreferredWidth(268);
					btnBet.setEnabled(true);
					btnRemoveBet.setEnabled(true);
				}

			}

		});

		btnSelect.setEnabled(false);

		JLabel lblBetList = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Betlist"));
		lblBetList.setBounds(723, 31, 66, 15);
		getContentPane().add(lblBetList);

		btnRemoveBet.setBounds(710, 390, 97, 25);
		getContentPane().add(btnRemoveBet);
		btnRemoveBet.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int r = tableBets.getSelectedRow();
				if (r != -1) {
					float rowFee = (Float) tableModelBets.getValueAt(r, 0);
					String rowResult = (String) tableModelBets.getValueAt(r, 1);
					for (Odd o : betOdds) {
						if (o.getFee() == rowFee && o.getResultBet().equals(rowResult)) {
							betOdds.remove(o);
							break;
						}
					}
					if (betOdds.isEmpty())
						btnBet.setEnabled(false);
					renewBets();
				}

			}

		});

		///////////////////////////////////////////////////////////////////////////////

		btnRemoveEvent = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Remove") + " "
				+ ResourceBundle.getBundle("Etiquetas").getString("Event"));
		btnRemoveEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				User u = null;
				float dirua = 0;
				int i = tableEvents.getSelectedRow();
				domain.Event ev = (domain.Event) tableModelEvents.getValueAt(i, 2); // obtain ev object
				BLFacade bl = IntroGUI.getBusinessLogic();
				bl.removeEvent(ev);
				renewEvents();
			}
		});
		btnRemoveEvent.setBounds(407, 212, 130, 25);
		getContentPane().add(btnRemoveEvent);
		btnRemoveEvent.setEnabled(false);

		btnRemoveBet.setEnabled(false);

		JButton btnBestUser = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Bai")); //$NON-NLS-1$ //$NON-NLS-2$
		btnBestUser.setBounds(66, 422, 97, 25);
		getContentPane().add(btnBestUser);
		btnBestUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame a = new BestUserGUI();
				a.setVisible(true);
			}
		});

		if (person == null || person instanceof User) {
			btnCreateEvent.setVisible(false);
			btnCreateOdd.setVisible(false);
			btnCreateQuestion.setVisible(false);
			btnEmaitzakArgitaratu.setVisible(false);
			btnProfit.setVisible(false);
			btnRemoveEvent.setVisible(false);
		} else {
			btnBet.setVisible(false);
			btnAccount.setVisible(false);
			btnSelect.setVisible(false);
			scrollPaneBets.setVisible(false);
			btnRemoveBet.setVisible(false);
			lblBetList.setVisible(false);
			this.setSize(new Dimension(696, 510));

		}
		if (person != null) {
			lblWelcome.setText(ResourceBundle.getBundle("Etiquetas").getString("W") + " " + person.getUsername() + "!");
		} else {
			btnBestUser.setVisible(false);
			lblWelcome.setVisible(false);
			btnBet.setVisible(false);
			btnAccount.setVisible(false);
			btnSelect.setVisible(false);
			scrollPaneBets.setVisible(false);
			btnRemoveBet.setVisible(false);
			lblBetList.setVisible(false);
			this.setSize(new Dimension(696, 510));

		}

	}

	/**
	 * This method renews the list of events of selected date in FindQuestionGUI
	 */

	public void renewEvents() {
		DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar1.getLocale());

		try {
			tableModelEvents.setDataVector(null, columnNamesEvents);
			tableModelEvents.setColumnCount(3); // another column added to allocate ev objects

			BLFacade facade = IntroGUI.getBusinessLogic();
			Vector<domain.Event> events = facade.getEvents(this.currentDate);

			if (events.isEmpty())
				jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("NoEvents") + ": "
						+ dateformat1.format(calendarMio.getTime()));
			else
				jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("Events") + ": "
						+ dateformat1.format(calendarMio.getTime()));

			for (domain.Event ev : events) {
				Vector<Object> row = new Vector<Object>();

				System.out.println("Events " + ev);

				row.add(ev.getEventNumber());
				row.add(ev.getDescription());
				row.add(ev); // ev object added in order to obtain it with tableModelEvents.getValueAt(i,2)
				tableModelEvents.addRow(row);
			}
			tableEvents.getColumnModel().getColumn(0).setPreferredWidth(100);
			tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);
			tableEvents.getColumnModel().removeColumn(tableEvents.getColumnModel().getColumn(2)); // not shown in JTable
		} catch (Exception e1) {

			jLabelQueries.setText(e1.getMessage());
		}
		CreateQuestionGUI.paintDaysWithEvents(jCalendar1);
		tableModelOdds.setRowCount(0);
		tableModelQueries.setRowCount(0);
		btnCreateEvent.setEnabled(true);
		btnCreateQuestion.setEnabled(false);
		btnCreateOdd.setEnabled(false);
		// btnBet.setEnabled(false);
		btnEmaitzakArgitaratu.setEnabled(false);
		btnProfit.setEnabled(false);
		btnSelect.setEnabled(false);
		btnRemoveEvent.setEnabled(false);

	}

	/**
	 * This method renews the list of questions of selected date in FindQuestionGUI
	 */

	public void renewQuestions() {
		int i = tableEvents.getSelectedRow();
		domain.Event ev = (domain.Event) tableModelEvents.getValueAt(i, 2); // obtain ev object
		Vector<Question> queries = ev.getQuestions();
		tableModelQueries.setDataVector(null, columnNamesQueries);
		tableModelQueries.setColumnCount(3);

		if (queries.isEmpty())
			jLabelQueries
					.setText(ResourceBundle.getBundle("Etiquetas").getString("NoQueries") + ": " + ev.getDescription());
		else
			jLabelQueries.setText(
					ResourceBundle.getBundle("Etiquetas").getString("SelectedEvent") + " " + ev.getDescription());
		tableModelQueries.setRowCount(0);
		for (domain.Question q : queries) {
			Vector<Object> row = new Vector<Object>();

			row.add(q.getQuestionNumber());
			row.add(q.getQuestion());
			row.add(q);
			tableModelQueries.addRow(row);
		}
		tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);
		tableQueries.getColumnModel().removeColumn(tableQueries.getColumnModel().getColumn(2));
		tableModelOdds.setRowCount(0);
		btnCreateEvent.setEnabled(true);
		btnCreateQuestion.setEnabled(true);
		btnRemoveEvent.setEnabled(true);
		btnCreateOdd.setEnabled(false);
		// btnBet.setEnabled(false);
		btnSelect.setEnabled(false);
		btnEmaitzakArgitaratu.setEnabled(true);
		btnProfit.setEnabled(true);
	}

	/**
	 * This method renews the list of odds of selected date in FindQuestionGUI
	 */

	public void renewOdds() {
		int i = tableQueries.getSelectedRow();
		Question q = (Question) tableModelQueries.getValueAt(i, 2); // obtain qu object
		Collection<Odd> odds = q.getOdds();
		tableModelOdds.setDataVector(null, columnNamesOdds);
		tableModelOdds.setColumnCount(3);
		tableModelOdds.setRowCount(0);
		for (Odd o : odds) {
			Vector<Object> row = new Vector<Object>();

			row.add(o.getFee());
			row.add(o.getResultBet());
			row.add(o);
			tableModelOdds.addRow(row);
		}
		tableOdds.getColumnModel().getColumn(0).setPreferredWidth(100);
		tableOdds.getColumnModel().getColumn(1).setPreferredWidth(268);
		tableOdds.getColumnModel().removeColumn(tableOdds.getColumnModel().getColumn(2));
		btnCreateEvent.setEnabled(true);
		btnCreateQuestion.setEnabled(true);
		btnCreateOdd.setEnabled(true);
		// btnBet.setEnabled(false);
		btnSelect.setEnabled(false);

	}

	public void renewBets() {
		tableModelBets.setDataVector(null, columnNamesOdds);
		tableModelBets.setColumnCount(2);
		tableModelBets.setRowCount(0);
		for (Odd o : betOdds) {
			Vector<Object> row = new Vector<Object>();
			row.add(o.getFee());
			row.add(o.getResultBet());
			row.add(o);
			tableModelBets.addRow(row);
		}
		tableBets.getColumnModel().getColumn(0).setPreferredWidth(100);
		tableBets.getColumnModel().getColumn(1).setPreferredWidth(268);
		btnRemoveBet.setEnabled(false);

	}

	public void updateUser(User u) {
		this.person = u;
	}

	public void updateEvent(Question q) {
		int i = tableEvents.getSelectedRow();
		domain.Event ev = (domain.Event) tableModelEvents.getValueAt(i, 2); // obtain ev object
		ev.addQuestion(q);
	}

	public void updateQuestion(Odd o) {
		int i = tableQueries.getSelectedRow();
		Question q = (Question) tableModelQueries.getValueAt(i, 2);
		q.getOdds().add(o);
	}

	private FindQuestionsGUI returnThis() {
		return this;
	}

	private void jButton2_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}