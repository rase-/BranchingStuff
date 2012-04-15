/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu;

import java.util.List;

/**
 *
 * @author tonykovanen
 */
public class StudentInformation {
    private String studentNumber;
    private String firstname;
    private String lastname;
    private String projectGroup;
    private List<Palautus> returned;
    private Reader informationReader;
    
    public StudentInformation(String studentNumber, Reader reader) {
        this.studentNumber = studentNumber;
        this.informationReader = reader;
        loadInformation();
        if (returned != null) {
            returned.remove(returned.size() - 1);
        }
    }
    private void loadInformation() {
        Palautukset palautukset = informationReader.read(studentNumber);
        if (palautukset.getPalautukset().isEmpty()) {
            this.firstname = null;
            this.lastname = null;
            this.projectGroup = null;
            this.returned = null;
            return;
        }
        this.firstname = palautukset.getPalautukset().get(0).getEtunimi();
        this.lastname = palautukset.getPalautukset().get(0).getSukunimi();
        this.projectGroup = palautukset.getPalautukset().get(palautukset.getPalautukset().size() -1).getTehtavat();
        returned = palautukset.getPalautukset();
            
    }
    
    @Override
    public String toString() {
        if (firstname == null) {
            return "Käyttäjää ei löytynyt";
        }
        String ret = "";
        ret += firstname + " " + lastname + " " + studentNumber + "\n\n";
        ret += "miniprojekti: " + projectGroup + "\n\n";
        int tehtavia = 0;
        int tunteja = 0;
        for (Palautus palautus : returned) {
            tunteja += palautus.getTunteja();
            tehtavia += palautus.getTehtavia();
            ret += palautus + "\n";
        }
        ret += "\nyhteensä: " + tehtavia + " tehtävää " + tunteja +  " tuntia";
        return ret;
    }
}
