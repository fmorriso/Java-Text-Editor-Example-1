import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
//
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CoupPad extends JFrame implements ActionListener {

    private JTextArea textDisplayArea = new JTextArea();
    private JButton readFileButton = new JButton("Open File");
    private JButton writeFileButton = new JButton("Save File");
    private JTextField nameField = new JTextField(20);
    private JLabel filenameLabel = new JLabel("File Name");
    private JPanel innerWindow = new JPanel();

    public CoupPad() {
        super("CoupPad - The Super Fantastic Text Editor");
        innerWindow.setLayout(new GridLayout(2, 2, 1, 1));

        innerWindow.add(readFileButton);
        innerWindow.add(writeFileButton);
        innerWindow.add(nameField);
        innerWindow.add(filenameLabel);

        this.getContentPane().setLayout(new BorderLayout());

        this.getContentPane().add("North", innerWindow);
        this.getContentPane().add(new JScrollPane(textDisplayArea));
        this.getContentPane().add("Center", textDisplayArea);

        innerWindow.setBackground(Color.red);
        textDisplayArea.setBackground(Color.green);

        textDisplayArea.setFont(new Font("Serif", Font.ITALIC, 20));
        readFileButton.addActionListener(this);
        writeFileButton.addActionListener(this);
    }

    public static void main(String[] args) {
        CoupPad window = new CoupPad();
        window.setSize(800, 400);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setVisible(true);

    }//end main

    /**
     * reads from a text file.  IntelliJ will look for it at the Project Folder
     *
     * @param textArea - where the contents of the text file will be displayed
     * @param fileName - full path to the text file to read.
     */
    private void readTextFile(JTextArea textArea, String fileName) {

        try {

            BufferedReader inStream // Create and

                    = new BufferedReader(new FileReader(fileName));

            String line = inStream.readLine();            // Read one line

            while (line != null) {                        // While more text

                textArea.append(line + "\n");              // textArea a line

                line = inStream.readLine();               // Read next line

            }

            inStream.close();                             // Close the stream

        } catch (FileNotFoundException e) {

            textArea.setText("IOERROR: File NOT Found: " + fileName + "\n");

            e.printStackTrace();

        } catch (IOException e) {

            textArea.setText("IOERROR: " + e.getMessage() + "\n");

            e.printStackTrace();

        }

    } // end readTextFile

    //writes to a text file.  IntelliJ will look for it at the Project Folder

    private void writeTextFile(JTextArea textArea, String fileName) {

        try {

            FileWriter outStream = new FileWriter(fileName);

            outStream.write(textArea.getText());

            outStream.close();

        } catch (IOException e) {

            textArea.setText("IOERROR: " + e.getMessage() + "\n");

            e.printStackTrace();

        }

    } // end writeTextFile()

    //watches the button and waits until it is clicked

    public void actionPerformed(ActionEvent evt) {

        String fileName = nameField.getText();
        if (evt.getSource() == readFileButton) {
            textDisplayArea.setText("");
            readTextFile(textDisplayArea, fileName);
        } else {
            writeTextFile(textDisplayArea, fileName);
        }

    }//end actionPerformed()


    /** get the java version that is running the current program
     * @return string containing the java version running the current program
     */
    private static String getJavaVersion()
    {
        Runtime.Version rtv = Runtime.version();
        return String.format("%s.%s.%s.%s", rtv.feature(), rtv.interim(), rtv.update(), rtv.patch());
    }
}//end class
