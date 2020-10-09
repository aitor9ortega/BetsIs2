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
import domain.Odd;
import domain.Question;
import exceptions.InvalidRate;
import exceptions.OddExist;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
 
public class CreateOddGUI extends JFrame {
 
    private JPanel contentPane;
    private JTextField feeField;
    private JTextField resultField;
    private Question question;
    private BLFacade bl;
	private JButton btnCreate = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Create")); //$NON-NLS-1$ //$NON-NLS-2$
	private JLabel errorLabel;
	private JLabel lblFee = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Fee"));
	private JLabel lblResult= new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Result"));

 
    /**
     * Create the frame.
     */
    public CreateOddGUI(Question q,final FindQuestionsGUI g) {
    	this.question=q;
        setTitle(ResourceBundle.getBundle("Etiquetas").getString("CreateOdd")); //$NON-NLS-1$ //$NON-NLS-2$
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
       
        //lblFee = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Fee")); //$NON-NLS-1$ //$NON-NLS-2$
        lblFee.setBounds(46, 64, 65, 14);
        contentPane.add(lblFee);
       
        //lblResult= new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Result")); //$NON-NLS-1$ //$NON-NLS-2$
        lblResult.setBounds(46, 121, 65, 14);
        contentPane.add(lblResult);
       
        feeField = new JTextField();
        feeField.setBounds(175, 61, 115, 20);
        contentPane.add(feeField);
        feeField.setColumns(10);
       
        resultField=new JTextField();
        resultField.setBounds(175, 118, 115, 20);
        contentPane.add(resultField);
        resultField.setColumns(10);
       
        
        btnCreate.setBounds(160, 202, 103, 23);
        contentPane.add(btnCreate);
        
        errorLabel = new JLabel("");
        errorLabel.setBounds(87, 162, 254, 14);
        errorLabel.setForeground(Color.red);
        contentPane.add(errorLabel);
        btnCreate.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				errorLabel.setText("");
				try {
					Odd o= new Odd();
					BLFacade bl=IntroGUI.getBusinessLogic();
					Odd odd=bl.createOdd(question,Float.parseFloat(feeField.getText()),resultField.getText());
					g.updateQuestion(odd);
					g.renewOdds();
					close();
				}catch(InvalidRate i) {
					errorLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorIncorrectFee"));
				}catch(OddExist o) {
					errorLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorOddAlreadyExists"));
				}catch(java.lang.NumberFormatException e1) {
					errorLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorFeeNotNumber"));
				}
			}
        }
        );
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