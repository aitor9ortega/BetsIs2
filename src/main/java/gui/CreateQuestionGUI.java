package gui;

import java.text.DateFormat;
import java.util.*;

import javax.swing.*;

import com.toedter.calendar.JCalendar;

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import businessLogic.BLFacade;
import configuration.UtilDate;
import domain.Event;
import domain.Question;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

public class CreateQuestionGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JLabel jLabelQuery = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Query"));
	private JLabel jLabelMinBet = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("MinimumBetPrice"));

	private JTextField jTextFieldQuery = new JTextField();
	private JTextField jTextFieldPrice = new JTextField();


	private JButton jButtonCreate = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CreateQuery"));
	private JLabel jLabelError = new JLabel();
	
	private Event event;
	
	/*
	 * Hemen parametro moduan aukeratutako gartaera pasatu behar zaio parametro moduan eta galdera aukeratutako gerataera horrei gehitu beharko zaio. 
	 */

	public CreateQuestionGUI(Event e,FindQuestionsGUI g) {
		try {
			jbInit(e,g);
		} catch (Exception u) {
			u.printStackTrace();
		}
	}

	private void jbInit(Event e, final FindQuestionsGUI g) throws Exception {
		this.event=e;
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(569, 325));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("CreateQuery"));
		jLabelQuery.setBounds(new Rectangle(25, 81, 75, 20));
		jTextFieldQuery.setBounds(new Rectangle(100, 81, 429, 20));
		jLabelMinBet.setBounds(new Rectangle(25, 119, 75, 20));
		jTextFieldPrice.setBounds(new Rectangle(100, 119, 60, 20));

		jButtonCreate.setBounds(new Rectangle(205, 198, 130, 30));


		jButtonCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonCreate_actionPerformed(e,g);
			}
		});
		

		jLabelError.setBounds(new Rectangle(113, 236, 305, 20));
		jLabelError.setForeground(Color.red);

		this.getContentPane().add(jLabelError, null);

		this.getContentPane().add(jButtonCreate, null);
		this.getContentPane().add(jTextFieldQuery, null);
		this.getContentPane().add(jLabelQuery, null);
		this.getContentPane().add(jTextFieldPrice, null);

		this.getContentPane().add(jLabelMinBet, null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

	
	
	
	private void jButtonCreate_actionPerformed(ActionEvent e, FindQuestionsGUI g) {;

		try {
			jLabelError.setText("");

			// Displays an exception if the query field is empty
			String inputQuery = jTextFieldQuery.getText();

			if (inputQuery.length() > 0) {

				// It could be to trigger an exception if the introduced string is not a number
				float inputPrice = Float.parseFloat(jTextFieldPrice.getText());

				if (inputPrice <= 0)
					jLabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorNumber"));
				else {

					// Obtain the business logic from a StartWindow class (local or remote)
					BLFacade facade = IntroGUI.getBusinessLogic();

					Question q=facade.createQuestion(event, inputQuery, inputPrice);
					g.updateEvent(q);
					g.renewQuestions();
					
					close();
				}
			} else
				jLabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorQuery"));
		} catch (EventFinished e1) {
			jLabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished") + ": "
					+ event.getDescription());
		} catch (QuestionAlreadyExist e1) {
			jLabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));
		} catch (java.lang.NumberFormatException e1) {
			jLabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorNumber"));
		} catch (Exception e1) {

			e1.printStackTrace();

		}
		
	}
	public static void paintDaysWithEvents(JCalendar jCalendar) {
		// For each day in current month, it is checked if there are events, and in that
		// case, the background color for that day is changed.

		BLFacade facade = IntroGUI.getBusinessLogic();

		Vector<Date> dates=facade.getEventsMonth(jCalendar.getDate());
			
		Calendar calendar = jCalendar.getCalendar();
		
		int month = calendar.get(Calendar.MONTH);
		//int today=calendar.get(Calendar.DAY_OF_MONTH);

		calendar.set(Calendar.DAY_OF_MONTH, 1);
		int offset = calendar.get(Calendar.DAY_OF_WEEK);

		if (Locale.getDefault().equals(new Locale("es")))
			offset += 4;
		else
			offset += 5;
		
		
	 	for (Date d:dates){

	 		calendar.setTime(d);
	 		System.out.println(d);
	 		

			
			// Obtain the component of the day in the panel of the DayChooser of the
			// JCalendar.
			// The component is located after the decorator buttons of "Sun", "Mon",... or
			// "Lun", "Mar"...,
			// the empty days before day 1 of month, and all the days previous to each day.
			// That number of components is calculated with "offset" and is different in
			// English and Spanish
//			    		  Component o=(Component) jCalendar.getDayChooser().getDayPanel().getComponent(i+offset);; 
			Component o = (Component) jCalendar.getDayChooser().getDayPanel()
					.getComponent(calendar.get(Calendar.DAY_OF_MONTH) + offset);
			o.setBackground(Color.CYAN);
	 	}
	 	
	 		calendar.set(Calendar.DAY_OF_MONTH, 1);
	 		calendar.set(Calendar.MONTH, month);
	 	
	}
	/**
	 * This method closes the window, but not the application
	 */
	public void close() {
		this.setVisible(false);
		this.dispose();
	}

}