package porop.calculator;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class CalculatorFrame extends JFrame implements ActionListener {

	CalculatorAgent calcAgent;
	JTextField operand1 = new JTextField(4);
	JTextField operand2 = new JTextField(4);
	JTextField result = new JTextField(6);
	JTextField operator = new JTextField(6);
	JButton equal = new JButton("=");
	JButton clear = new JButton("Clear");
	
	
	
	
	public CalculatorFrame() throws HeadlessException {
		try {
			calcAgent = new CalculatorAgent("localhost", 8888);
		} catch (Exception err) {
			JOptionPane.showMessageDialog(
					null, err.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		this.setTitle("Lesson01-Exam02");
		
		Container contentPane = this.getContentPane();
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		contentPane.add(Box.createVerticalGlue());
		contentPane.add(this.createInputForm());
		contentPane.add(this.createToolBar());
		contentPane.add(Box.createVerticalGlue());
		
		/*
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		*/
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				calcAgent.close();
				System.exit(0);
			}
		});
		this.pack();
		this.setLocationRelativeTo(null);		
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		if(event.getSource() == equal) {
			compute();
		} else {
			clearForm();
		}

	}

	private Box createToolBar() {
		// TODO Auto-generated method stub
		Box box = Box.createHorizontalBox();
		box.add(clear);
		clear.addActionListener(this);
		return box;
	}

	private Box createInputForm() {
		// TODO Auto-generated method stub
		Box box = Box.createHorizontalBox();
		box.setMaximumSize(new Dimension(300, 30));
		box.setAlignmentY(Box.CENTER_ALIGNMENT);
		box.add(operand1);
		box.add(operator);
		box.add(operand2);
		box.add(equal);
		equal.addActionListener(this);
		box.add(result);
		return box;
	}

	private void clearForm() {
		// TODO Auto-generated method stub
		this.operand1.setText("");
		this.operand2.setText("");
		this.result.setText("");
	}

	private void compute() {
		// TODO Auto-generated method stub
		double a = Double.parseDouble(operand1.getText());
		double b = Double.parseDouble(operand2.getText());
		double r = 0;
		
		try {
			r = calcAgent.compute(operator.getText(), a, b);
			result.setText(Double.toString(r));
		} catch (Exception err) {
			JOptionPane.showMessageDialog(null, err.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CalculatorFrame app = new CalculatorFrame();
		app.setVisible(true);
	}

}
