/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import ohtu.*;
import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author tonykovanen
 */
public class StudentInformationTest {
    
    public StudentInformationTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    @Test
    public void studentInformationIsReadFromSentStudentNr() {
        GsonReader mockGson = mock(GsonReader.class);
        String studentNr = "13865669";
        Palautukset palautukset = new Palautukset();
        ArrayList<Palautus> palautusLista = new ArrayList<Palautus>();
        Palautus p = new Palautus();
        p.setEtunimi("Tony");
        p.setSukunimi("Kovanen");
        p.setGithubtunnus("rase-");
        p.setOpiskelijanumero("13865669");
        p.setTehtavat("1,2,3,4,5");
        p.setTehtavia(5);
        p.setTunteja(2);
        p.setViikko(1);
        palautusLista.add(p);
        palautukset.setPalautukset(palautusLista);
        when(mockGson.read(studentNr)).thenReturn(palautukset);
        StudentInformation info = new StudentInformation(studentNr, mockGson);
        verify(mockGson).read(eq("13865669"));
    }
    
    @Test
    public void studentInformationIsPrintedOutCorrectly() {
        GsonReader mockGson = mock(GsonReader.class);
        String studentNr = "13865669";
        Palautukset palautukset = new Palautukset();
        ArrayList<Palautus> palautusLista = new ArrayList<Palautus>();
        Palautus p = new Palautus();
        p.setEtunimi("Tony");
        p.setSukunimi("Kovanen");
        p.setGithubtunnus("rase-");
        p.setOpiskelijanumero("13865669");
        p.setTehtavat("1,2,3,4,5");
        p.setTehtavia(5);
        p.setTunteja(2);
        p.setViikko(1);
        palautusLista.add(p);
        Palautus b = new Palautus();
        b.setEtunimi("Bottleneck");
        b.setSukunimi("");
        b.setGithubtunnus("");
        b.setOpiskelijanumero("13865669");
        b.setTehtavat("Bottleneck");
        b.setTehtavia(0);
        b.setTunteja(0);
        b.setViikko(0);
        palautusLista.add(b);
        palautukset.setPalautukset(palautusLista);
        when(mockGson.read(studentNr)).thenReturn(palautukset);
        StudentInformation info = new StudentInformation(studentNr, mockGson);
        System.out.println(info);
        Assert.assertEquals("Tony Kovanen 13865669\n\nminiprojekti: Bottleneck\n\nviikko 1: 5 tehtävää 1,2,3,4,5 aikaa kului 2 tuntia.\n\nyhteensä: 5 tehtävää 2 tuntia", info.toString());
    }
    
    @Test
    public void returningNullvaluedStudentReturnsASpecialToString() {
        GsonReader mockGson = mock(GsonReader.class);
        String studentNr = "13865669";
        when(mockGson.read(studentNr)).thenReturn(new Palautukset());
        StudentInformation info = new StudentInformation(studentNr, mockGson);
        Assert.assertEquals("Käyttäjää ei löytynyt", info.toString());
    }
}
