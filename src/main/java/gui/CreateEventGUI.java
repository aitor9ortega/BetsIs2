package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import com.toedter.calendar.JCalendar;

import businessLogic.BLFacade;
import domain.Event;
import exceptions.EventExist;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.ResourceBundle;

import javax.swing.JButton;

public class CreateEventGUI extends JFrame {

	private JPanel contentPane;
	private JTextField eventNumField;
	private JTextField descriptionField;
	private JLabel errorLabel;


	
	/*
	 * CreateEvent eraikitzailean parametro moduan aukeratutako eguna pasatu beharko diogu. 
	 */

	/**
	 * Create the frame.
	 */
	

	public CreateEventGUI(final Date d,final FindQuestionsGUI g) {
		setTitle(ResourceBundle.getBundle("Etiquetas").getString("CreateEvent"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 465, 249);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
//		JLabel lblGertaeraZenbakia = new JLabel("Event number: ");
//		lblGertaeraZenbakia.setBounds(22, 33, 92, 14);
//		contentPane.add(lblGertaeraZenbakia);
//		
//		eventNumField = new JTextField();
//		eventNumField.setBounds(106, 30, 51, 20);
//		contentPane.add(eventNumField);
//		eventNumField.setColumns(10);
		
		JLabel lblDescription = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Description"));
		lblDescription.setBounds(22, 82, 73, 14);
		contentPane.add(lblDescription);
		
		descriptionField = new JTextField();
		descriptionField.setBounds(106, 79, 281, 20);
		contentPane.add(descriptionField);
		descriptionField.setColumns(10);
		
		JButton btnCreateEvent = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CreateEvent")); //$NON-NLS-1$ //$NON-NLS-2$
		btnCreateEvent.setBounds(149, 147, 129, 38);
		contentPane.add(btnCreateEvent);
		
		errorLabel = new JLabel("");
		errorLabel.setBounds(77, 122, 281, 14);
		contentPane.add(errorLabel);
		errorLabel.setForeground(Color.red);
		errorLabel.setVisible(false);
		btnCreateEvent.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				errorLabel.setText("");
	//			Integer num = Integer.parseInt(eventNumField.getText());
				String des = descriptionField.getText();
				BLFacade bl= IntroGUI.getBusinessLogic();
				try {
					bl.createEvent(des,d);
					g.renewEvents();
					close();
				}catch(EventExist e) {
					errorLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventAlreadyExist"));
				}
			}});
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
