package fr.damienchesneau.icsformat;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Damien Chesneau - contact@damienchesneau.fr
 */
public class CalendarUpdater {

    public static void main(String[] args) {
        if (args.length == 2) {
            IcsModifier modifier = new IcsModifier(args[0], args[1]);
            try {
                modifier.update();
            } catch (IOException ex) {
                System.err.println("Oooops error :(");
                Logger.getLogger(CalendarUpdater.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            System.out.println("Please use this app with tow parameters. First is input file the second is the output file.");
        }
    }

}
