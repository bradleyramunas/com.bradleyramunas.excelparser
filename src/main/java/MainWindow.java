import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.SyncFailedException;

public class MainWindow extends JFrame{
    private JTextField textField1;
    private JButton browseButton;
    private JTextField textField2;
    private JButton browseButton1;
    private JButton createStudentDatButton;
    private JButton createStudentTxtButton;
    private JPanel mainFrame;


    public MainWindow(){
        super("Create Database Files");


        browseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String fileDirectory = getExcelFileDirectory();
                textField1.setText(fileDirectory);
            }
        });

        browseButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String fileDirectory = getFileDirectory();
                textField2.setText(fileDirectory);
            }
        });

        createStudentTxtButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                try {
                    createStudentTXT();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        mainFrame.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        setContentPane(mainFrame);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    public void createStudentTXT() throws IOException{
        GenerateStudentText.createStudentTXT(textField1.getText(), textField2.getText());
    }

    public String getFileDirectory(){
        String desktopDirectory = System.getProperty("user.home");
        JFileChooser jFileChooser = new JFileChooser(desktopDirectory + "\\Desktop");
        jFileChooser.setDialogTitle("Choose a file directory");
        jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jFileChooser.showOpenDialog(mainFrame);
        String fileDirectory = jFileChooser.getSelectedFile().getAbsolutePath();
        if(fileDirectory != null) return fileDirectory;
        else return "";
    }

    public String getExcelFileDirectory(){
        String desktopDirectory = System.getProperty("user.home");
        JFileChooser jFileChooser = new JFileChooser(desktopDirectory + "/Desktop");
        jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jFileChooser.setFileFilter(new FileNameExtensionFilter("excel", "xlsx"));
        jFileChooser.showOpenDialog(mainFrame);
        String fileDirectory = jFileChooser.getSelectedFile().getAbsolutePath();
        if(fileDirectory != null) return fileDirectory;
        else return "";
    }
}
