import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by beepbeat/holladiewal on 17.04.2016.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);

        System.out.println("Stundenplan erstellen");
        System.out.println("Format: Stunde (als Zahl), Fach (Kürzel), Lehrer (Kürzel)");
        System.out.println("Bei Freistunde bitte \"null\" angeben");
        List<String> lString = new ArrayList<>();
        for (int d = 0; d < 5; d++) {
            switch(d){
                case 0:{ lString.add("Montag BEGIN");System.out.println("Montag");break;}
                case 1:{ lString.add("Dienstag BEGIN");System.out.println("Dienstag"); break;}
                case 2:{ lString.add("Mittwoch BEGIN");System.out.println("Mittwoch"); break;}
                case 3:{ lString.add("Donnerstag BEGIN");System.out.println("Donnerstag"); break;}
                case 4:{ lString.add("Freitag BEGIN");System.out.println("Freitag"); break;}
            }
            for (int S = 0; S < 10; S++) {
                //noinspection StatementWithEmptyBody
                while (!in.hasNextLine());
                String s = in.nextLine();
                if (!s.contains(",") && !s.equalsIgnoreCase("null")) {
                    System.out.println("Du hast das Format nicht beachtet, Abbruch!");
                    Thread.sleep(5000);
                    System.exit(-1);
                }
                //String[] sA = s.split(",");
                if (!s.equalsIgnoreCase("null"))
                lString.add(s);



            }
            lString.add("END");
        }
        lString.add("Class BEGIN");
        System.out.println("Bitte Jahrgang eingeben");
        while (!in.hasNextLine());
        lString.add(in.nextLine());
        System.out.println("Bitte Klassenzusatz angeben");
        while (!in.hasNextLine());
        lString.add(in.nextLine());
        lString.add("END");
        File persistence = new File("persistence");
        FileUtils.writeLines(persistence, lString, false);
        long crc = FileUtils.checksumCRC32(persistence);
        lString.clear();
        lString.add("CRC BEGIN");
        lString.add(String.valueOf(crc));
        lString.add("END");
        FileUtils.writeLines(persistence, lString, true);
    }
}
