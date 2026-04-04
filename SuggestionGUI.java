
// Please see Main.java to determine which extension options I chose.

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextArea;
//Extra imports for improved interface readability
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class SuggestionGUI extends JFrame {

    // for creating the text field for the user to enter a text pattern to search
    // for
    private JTextField enterField;

    // for creating a text area for each novel
    private JTextArea firstDisplay;
    private JTextArea secondDisplay;
    private JTextArea thirdDisplay;

    // for creating buttons to append novel text area text to an external file
    private JButton firstButton;
    private JButton secondButton;
    private JButton thirdButton;

    // for writing the novel text area text to an external file
    private FileWriter writer;
    private String filePath;

    // set up GUI
    public SuggestionGUI() {
        super("Autocomplete from novels");

        setLayout(null);

        enterField = new JTextField("Enter text here");
        enterField.setFont(new Font("Serif", Font.PLAIN, 32));
        enterField.setBackground(Color.blue);
        enterField.setForeground(Color.yellow);
        enterField.setBounds(10, 10, 800, 60);
        add(enterField);

        try {
            filePath = "output.txt";
            writer = new FileWriter(filePath);
        } catch (IOException e) {
            System.out.println("IO exception: could not create FileWriter");
            e.printStackTrace();
        }

        // create and size the display areas, set them to be uneditable, and add them to
        // the GUI.
        // create first display for Frankenstein
        firstDisplay = new JTextArea();
        firstDisplay.setBounds(10, 80, 800, 200);
        firstDisplay.setEditable(false);
        add(firstDisplay);

        // create button to append first display text to output file
        firstButton = new JButton("Append");
        firstButton.setBounds(10, 280, 80, 20);
        add(firstButton);
        firstButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // call write to file method
                appendToFile(firstDisplay.getText(), enterField.getText(), filePath);
            }
        });

        // create second display for Moby Dick
        secondDisplay = new JTextArea();
        secondDisplay.setBounds(10, 320, 800, 200);
        secondDisplay.setEditable(false);
        add(secondDisplay);

        // create button to append second display text to output file
        secondButton = new JButton("Append");
        secondButton.setBounds(10, 520, 80, 20);
        add(secondButton);
        secondButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // call write to file method
                appendToFile(secondDisplay.getText(), enterField.getText(), filePath);
            }
        });

        // create third display for Great Expectations
        thirdDisplay = new JTextArea();
        thirdDisplay.setBounds(10, 560, 800, 200);
        thirdDisplay.setEditable(false);
        add(thirdDisplay);

        // create button to append third display text to output file
        thirdButton = new JButton("Append");
        thirdButton.setBounds(10, 760, 80, 20);
        add(thirdButton);
        thirdButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // call write to file method
                appendToFile(thirdDisplay.getText(), enterField.getText(), filePath);
            }
        });

        setSize(840, 840); // set size of window
        setVisible(true); // show window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // create three ThreadedAutocomplete objects that use a given file name, the
        // same text enter field, and a given display for each respective novel
        ThreadedAutocomplete frankenstein = new ThreadedAutocomplete("Frankenstein.txt", enterField, firstDisplay);
        ThreadedAutocomplete mobyDick = new ThreadedAutocomplete("MobyDick.txt", enterField, secondDisplay);
        ThreadedAutocomplete greatExpectations = new ThreadedAutocomplete("GreatExpectations.txt", enterField,
                thirdDisplay);

        // start threads
        frankenstein.start();
        mobyDick.start();
        greatExpectations.start();

        // join the threads so that main waits on them before completing
        try {
            frankenstein.join();
            mobyDick.join();
            greatExpectations.join();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted");
            e.printStackTrace();
        }

        // close the gui (happens on entering the text "quit")
        dispose();

        // close the writer to free resources
        try {
            writer.close();
        } catch (IOException e) {
            System.out.println("Error closing file writer");
            e.printStackTrace();
        }
    }

    /**
     * Appends text from a given text area to output.txt
     * 
     * @param displayText The text from the text area to append
     * @param fieldText   The text from the text entry field to append
     * @param filePath    The file path to output to
     */
    private void appendToFile(String displayText, String fieldText, String filePath) {
        try {
            Files.writeString(Path.of(filePath), fieldText + " " + displayText, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Could not write to file " + filePath);
            e.printStackTrace();
        }
    }
}
