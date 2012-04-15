/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import ohtu.verkkokauppa.*;
import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
/**
 *
 * @author tonykovanen
 */
public class KauppaTest {
        
    public KauppaTest() {
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
    public void aloitetaanAsiointiLisataanTuoteKoriinJaVarmistetaanOstostaSuoritettasessaAsiakkasTilinumeroJaSumma() {
        Pankki mockPankki = mock(Pankki.class);
        Viitegeneraattori mockViite = mock(Viitegeneraattori.class);
        Varasto mockVarasto = mock(Varasto.class);

        Kauppa kauppa = new Kauppa(mockVarasto, mockPankki, mockViite);
        kauppa.aloitaAsiointi();
        when(mockVarasto.saldo(0)).thenReturn(1);
        when(mockVarasto.haeTuote(0)).thenReturn(new Tuote(0, "Liberty Ale", 4));
        kauppa.lisaaKoriin(0);
        kauppa.tilimaksu("Matti Luukkainen", "12345-6789");
        verify(mockPankki).tilisiirto(eq("Matti Luukkainen"), anyInt(), eq("12345-6789"), anyString(), eq(4));
        
    }
    
    @Test
    public void aloitetaanAsiointiLisataanKaksiEriTuotettaKoriinJaVarmistetaanTilisiirrostaAsiakasTilinumeroJaSumma() {
        Pankki mockPankki = mock(Pankki.class);
        Viitegeneraattori mockViite = mock(Viitegeneraattori.class);
        Varasto mockVarasto = mock(Varasto.class);

        Kauppa kauppa = new Kauppa(mockVarasto, mockPankki, mockViite);
        kauppa.aloitaAsiointi();
        when(mockVarasto.saldo(0)).thenReturn(1);
        when(mockVarasto.saldo(1)).thenReturn(1);
        when(mockVarasto.haeTuote(1)).thenReturn(new Tuote(1, "Koff Porter", 3));
        when(mockVarasto.haeTuote(0)).thenReturn(new Tuote(0, "Liberty Ale", 4));
        kauppa.lisaaKoriin(0);
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("Matti Luukkainen", "12345-6789");
        verify(mockPankki).tilisiirto(eq("Matti Luukkainen"), anyInt(), eq("12345-6789"), anyString(), eq(7));
        
    }
    
    @Test
    public void aloitetaanAsiointiKoriinLisataanKaksiTuotettaJaSuoritetaanOstosVarmistetaanTilisiirrostaAsiakasTilinueroJaSumma() {
        Pankki mockPankki = mock(Pankki.class);
        Viitegeneraattori mockViite = mock(Viitegeneraattori.class);
        Varasto mockVarasto = mock(Varasto.class);

        Kauppa kauppa = new Kauppa(mockVarasto, mockPankki, mockViite);
        kauppa.aloitaAsiointi();
        when(mockVarasto.saldo(0)).thenReturn(2);
        when(mockVarasto.haeTuote(0)).thenReturn(new Tuote(0, "Liberty Ale", 4));
        kauppa.lisaaKoriin(0);
        kauppa.lisaaKoriin(0);
        kauppa.tilimaksu("Matti Luukkainen", "12345-6789");
        verify(mockPankki).tilisiirto(eq("Matti Luukkainen"), anyInt(), eq("12345-6789"), anyString(), eq(8));
    }
    @Test
    public void  aloitetaanAsiointiJaKoriinLisataanTuoteJokaOnLoppuJaTuoteJotaOnTarpeeksiJaVarmistetaanTilsiirrostaAsiakasTilinumeroJaSumma() {
        Pankki mockPankki = mock(Pankki.class);
        Viitegeneraattori mockViite = mock(Viitegeneraattori.class);
        Varasto mockVarasto = mock(Varasto.class);

        Kauppa kauppa = new Kauppa(mockVarasto, mockPankki, mockViite);
        kauppa.aloitaAsiointi();
        when(mockVarasto.saldo(0)).thenReturn(2);
        when(mockVarasto.saldo(1)).thenReturn(0);
        when(mockVarasto.haeTuote(0)).thenReturn(new Tuote(0, "Liberty Ale", 4));
        when(mockVarasto.haeTuote(1)).thenReturn(new Tuote(1, "Weihenstephaner Hefe Weissbier", 5));
        kauppa.lisaaKoriin(0);
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("Matti Luukkainen", "12345-6789");
        verify(mockPankki).tilisiirto(eq("Matti Luukkainen"), anyInt(), eq("12345-6789"), anyString(), eq(4));
    }
    
    @Test
    public void asioinninAloittaminenTyhjentaaOstoskorin() {
        Pankki mockPankki = mock(Pankki.class);
        Viitegeneraattori mockViite = mock(Viitegeneraattori.class);
        Varasto mockVarasto = mock(Varasto.class);

        Kauppa kauppa = new Kauppa(mockVarasto, mockPankki, mockViite);
        kauppa.aloitaAsiointi();
        when(mockVarasto.saldo(0)).thenReturn(2);
        when(mockVarasto.saldo(1)).thenReturn(0);
        when(mockVarasto.haeTuote(0)).thenReturn(new Tuote(0, "Liberty Ale", 4));
        when(mockVarasto.haeTuote(1)).thenReturn(new Tuote(1, "Weihenstephaner Hefe Weissbier", 5));
        kauppa.lisaaKoriin(0);
        kauppa.lisaaKoriin(1);
        
        kauppa.aloitaAsiointi();
        kauppa.tilimaksu("Matti Luukkainen", "12345-6789");
        verify(mockPankki).tilisiirto(eq("Matti Luukkainen"), anyInt(), anyString(), anyString(), eq(0));
        
    }
    
    @Test
    public void kauppaPyytaaJokaiselleTransaktiolleOmanViitenumeron() {
        Pankki mockPankki = mock(Pankki.class);
        Viitegeneraattori mockViite = mock(Viitegeneraattori.class);
        Varasto mockVarasto = mock(Varasto.class);

        Kauppa kauppa = new Kauppa(mockVarasto, mockPankki, mockViite);
        kauppa.aloitaAsiointi();
        when(mockVarasto.saldo(0)).thenReturn(2);
        when(mockVarasto.saldo(1)).thenReturn(0);
        when(mockVarasto.haeTuote(0)).thenReturn(new Tuote(0, "Liberty Ale", 4));
        when(mockVarasto.haeTuote(1)).thenReturn(new Tuote(1, "Weihenstephaner Hefe Weissbier", 5));
        int viite = 0;
        when(mockViite.uusi()).thenReturn(viite);
        kauppa.lisaaKoriin(0);
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("Matti Luukkainen", "12345-6789");
        verify(mockPankki).tilisiirto(eq("Matti Luukkainen"), eq(0), anyString(), anyString(), eq(4));
        viite++;
        when(mockViite.uusi()).thenReturn(viite);
        kauppa.tilimaksu("Matti Luukkainen", "12345-6789");
        verify(mockPankki).tilisiirto(eq("Matti Luukkainen"), eq(1), anyString(), anyString(), eq(4));
        
    }
    @Test
    public void postaminenKutsuuPalautustaOikeallaTuotteella() {
        Pankki mockPankki = mock(Pankki.class);
        Viitegeneraattori mockViite = mock(Viitegeneraattori.class);
        Varasto mockVarasto = mock(Varasto.class);

        Kauppa kauppa = new Kauppa(mockVarasto, mockPankki, mockViite);
        kauppa.aloitaAsiointi();
        when(mockVarasto.saldo(0)).thenReturn(2);
        when(mockVarasto.saldo(1)).thenReturn(0);
        when(mockVarasto.haeTuote(0)).thenReturn(new Tuote(0, "Liberty Ale", 4));
        when(mockVarasto.haeTuote(1)).thenReturn(new Tuote(1, "Weihenstephaner Hefe Weissbier", 5));
        int viite = 0;
        when(mockViite.uusi()).thenReturn(viite);
        kauppa.lisaaKoriin(0);
        kauppa.lisaaKoriin(1);
        kauppa.poistaKorista(0);
        verify(mockVarasto).palautaVarastoon(new Tuote(0, "Liberty Ale", 4));
    }
}
