package fr.damienchesneau.icsformat;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 *
 * @author Damien Chesneau - contact@damienchesneau.fr
 */
public class CalendarUpdater {

    public static void main(String[] args) {
        String inputFile = "", output = "";
        if (args.length == 2) {
            inputFile = args[0];
            output = args[1];
        } else {
            //System.out.println("Please use this app with tow parameters. First is input file the second is the output file.");
            JFileChooser fileToChoose = new JFileChooser();
            fileToChoose.showDialog(null, null);
            Path file = fileToChoose.getSelectedFile().toPath();
            inputFile = file.toAbsolutePath().toString();
            int index = inputFile.indexOf(".ics");
            output = inputFile;
            output = (String) output.subSequence(0, index);
            output = output +"New.ics";
        }
        IcsModifier modifier = new IcsModifier(inputFile, output);
        try {
            modifier.update();
        } catch (IOException ex) {
            System.err.println("Oooops error :(");
            Logger.getLogger(CalendarUpdater.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
