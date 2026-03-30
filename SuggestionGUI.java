/* INSTRUCTIONS
Your assignment is to complete the four TODOs
in this document, starting with the top TODO
and working your way down in order.

Then you must make AT LEAST ONE additional
modification of your choice from the following
list:

OPTIONS:

	---STOP ON EXCLAMATION
	Currently the getTextToPeriod method in 
	ThreadedAutocomplete searches for periods 
	or question marks and gathers all the 
	text from the current Scanner position up 
	to that punctuation and returns it. Modify 
	the method to also stop at exclamation marks.
	You can test it with the word "tiresome" and
	if you see: "tiresome unlucky ghost story!"
	with blanks in the other textareas, then you
	did it correctly.
	Also I find my code in the top half of the getTextToPeriod method to be gross so if you 
	manage to make it more elegant, I'll throw in 
	a couple extra credit points.

	---QUIT TO EXIT
	Currently typing the word "quit" into the
	JTextField causes the threads to exit. Make
	it so that "quit" also closes the GUI.

	---BREAK BETWEEN WORDS
	The wrappedToFit method in 
	ThreadedAutocomplete splits the text every 60
	characters regardless of whether or not the
	split occurs in the middle of a word. Modify
	this method so that words are not split up.

	---MULTIWORD MATCH
	Currently only the last word entered in the 
	text field is used for matching. Modify 
	ThreadedAutocomplete to attempt to match on 
	the entire user input, then if no matches are
	found, attempt to match on the whole input
	minus the first word, then if no matches are
	found, try the whole input minus the first
	two words. And so on until a match is found
	or the user input is exhausted.

	---THREE BUTTON OUTPUT
	Add three buttons to the GUI (one for each
	JTextArea) and resize the GUI to display them.
	When the user clicks a button, the current
	JTextField contents, followed by the 
	autocompleted text in the corresponding 
	JTextArea should be written out to file.
	Further clicks continue to APPEND to the file
	rather than overwriting it so that the user
	can create a mashup story from the three
	novels.

	---SOMETHING NEW
	Propose your own extension and complete it.
	Your extension must be of comparable 
	difficulty to the other options and must be
	described in detail in a comment at the top of
	Main.java that also tells me, your instructor,
	where to look in the code to see this
	modification.

Note clearly in a comment at the top of
Main.java which modifications you made.

Small amounts of extra credit are available for
additional modifications beyond the required one
(up to 5 points each).
*/
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextArea;
//Extra imports for improved interface readability
import java.awt.Font;
import java.awt.Color;

public class SuggestionGUI extends JFrame 
{
	private JTextField enterField;
	
	/* TODO: Declare three private JTextArea
	instance variables.
	Test that the code still runs and compiles
	after this. You should notice no changes
	at this point. */
	
	
	// set up GUI
	public SuggestionGUI()
	{
		super("Autocomplete from novels");
		
		setLayout(null);
		
		enterField = new JTextField("Enter text here");
		enterField.setFont(new Font("Serif", Font.PLAIN, 32));
		enterField.setBackground(Color.blue);
		enterField.setForeground(Color.yellow);
		enterField.setBounds(10,10,800,60);
		add(enterField);
		
		/* TODO: Create 3 JTextArea objects and
		add them to this JFrame. Each the
		JTextArea will display suggested 
		autocomplete text for one of the three
		novels. I recommend using the following
		setBounds commands for spacing and sizing
		the JTextAreas on the GUI:
		For the first display:
			setBounds(10,80,800,200);
		For the second:
			setBounds(10,300,800,200);
		For the third:
			setBounds(10,520,800,200);
		Run and test your code. You should notice
		three new text areas on the GUI. */
		
		setSize(840, 840); // set size of window
		setVisible(true);  // show window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/* TODO Instantiate three new
		ThreadedAutocomplete objects. All three
		will take the enterField as their second
		input to the constructor. Each thread
		will take a different JTextArea as their
		third input and a different novel as
		their first input from among:
			"Frankenstein.txt"
			"MobyDick.txt"
			"GreatExpectations.txt"
		Run and test your code. You should notice
		no new features at this point. */
		
		
		/* TODO: Write code to start all three
		threads running concurrently.
		Run and test your code. You should notice
		text from the three novels appearing in
		the three text areas (unless there is no
		match in the novel with the most recent
		word). */
		
	}
}
