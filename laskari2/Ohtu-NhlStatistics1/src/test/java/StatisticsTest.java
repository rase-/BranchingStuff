/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.List;
import ohtuesimerkki.Player;
import ohtuesimerkki.Reader;
import ohtuesimerkki.Statistics;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author tonykovanen
 */
public class StatisticsTest {
    Statistics stats;
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
    
    public StatisticsTest() {
        
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        stats = new Statistics(readerStub);
    }
    
    @After
    public void tearDown() {
    }
    @Test
    public void playerNotFoundReturnsNull() {
        assertNull(stats.search("Tony Kovanen"));
    }
    
    @Test
    public void playerFoundReturnsRightPlayer() {
        assertEquals("Semenko", stats.search("Semenko").getName());
    }
    
    @Test
    public void teamReturnsRightNumberOfPlayers() {
        assertEquals(3, stats.team("EDM").size());
    }
    
    @Test
    public void teamReturnsEmptyListWhenTeamNotFound() {
        assertEquals(0, stats.team("ADS").size());
    }
    @Test
    public void topScoresReturnsRightAmountOfPlayers() {
        assertEquals(3, stats.topScorers(2).size());
    }
    @Test
    public void topScoresReturnsRightPlayerWithOne() {
        assertEquals("Gretzky", stats.topScorers(1).get(0).getName());
    }
    @Test
    public void topScoresReturnsRightOrderWithTwoPlayers() {
        List<Player> scorers = stats.topScorers(2);
        assertTrue(scorers.get(0).getPoints() > scorers.get(1).getPoints());
    }
}
