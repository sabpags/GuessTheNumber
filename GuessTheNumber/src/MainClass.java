import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

/*
 * Number guessing game
*/
public class MainClass{

	static JFrame frame;
	static JSlider slider;
	static JLabel rangeLabel;
	static JButton again;
	static JTextField tf;
	static JLabel guessLabel;
	static int numberToGuess;

	public static void main(String[] args) {

		//Creating the JFrame
		frame = new JFrame("Number Guessing Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500,300);
		frame.setResizable(false);

		JLabel title = new JLabel("Welcome to the Number Guessing Game", SwingConstants.CENTER);
		frame.getContentPane().add(BorderLayout.PAGE_START, title);

		//Creating the panel for guessing functions
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Enter your guess: ");
		tf = new JTextField(10); // accepts up to 10 characters
		JButton send = new JButton("Guess");
		send.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				checkNumberGuess();
			} 
		});
		JButton reset = new JButton("Give Up");
		reset.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				guessLabel.setText("The number was: " + numberToGuess);
				again.setVisible(true);
			} 
		});
		panel.add(label);
		panel.add(tf);
		panel.add(send);
		panel.add(reset);
		frame.getContentPane().add(panel);

		// Create Panel for texts and slider
		JPanel main = new JPanel();
		slider = new JSlider();
		// Paint the ticks and tracks
		slider.setPaintTrack(true);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);

		// Set spacing
		slider.setMajorTickSpacing(50);
		slider.setMinorTickSpacing(5);

		// setChangeListener
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				rangeLabel.setText("The number is between 0 and " + slider.getValue());
				resetNumber();
			}
		});
		rangeLabel = new JLabel("The number is between 0 and " + slider.getValue(),SwingConstants.CENTER);
		guessLabel = new JLabel("", SwingConstants.CENTER);
		again = new JButton("Play again?");
		again.setVisible(false);
		again.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				tf.setText("");
				guessLabel.setText("");
				again.setVisible(false);
				resetNumber();
			} 
		});

		main.add(guessLabel);
		main.add(again);
		main.add(rangeLabel);
		main.add(slider);
		main.setLayout(new GridLayout(4,1));
		frame.getContentPane().add(BorderLayout.SOUTH,main);

		numberToGuess = (int) (Math.random() * slider.getValue()); 
		System.out.println(numberToGuess);
		frame.setVisible(true);
	}

	/* 
	 * If value entered is an Integer, system checks if user correctly guessed.
	 * Provides hint if user if greater or less than the number.
	 * If user guesses correctly, congratulation message appears and prompts user to play again if desired.
	*/
	public static void checkNumberGuess() {
		try {
			if (Integer.parseInt(tf.getText()) < numberToGuess) {
				guessLabel.setText("Your guess was less than the number"); 
				if(Integer.parseInt(tf.getText()) < 0)
					guessLabel.setText("Your guess is out of bounds.");
			}
			else if (Integer.parseInt(tf.getText()) > numberToGuess) {
				guessLabel.setText("Your guess was greater than the number"); 
				if(Integer.parseInt(tf.getText()) > slider.getValue())
					guessLabel.setText("Your guess is out of bounds."); 
			}
			else {
				guessLabel.setText("Congratulations! You have guessed the correct number."); 
				again.setVisible(true);
			}
		}
		catch (NumberFormatException e) {
			guessLabel.setText("You have not entered a valid number, please try again.");
		}
	}

	public static void resetNumber() {
		numberToGuess = (int) (Math.random() * slider.getValue());
	//	System.out.println(numberToGuess);
	}
}