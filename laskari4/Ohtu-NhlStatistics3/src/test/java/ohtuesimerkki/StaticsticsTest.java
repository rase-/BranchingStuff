package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class StaticsticsTest {
    PlayerReader mockPlayerReader;
    Statistics stats;
    

    @Before
    public void setUp() {
        mockPlayerReader = mock(PlayerReader.class);
        
    }

    @Test
    public void playerFound() {
//        Player p = stats.search("Lemieux");
//        assertEquals("PIT", p.getTeam());
//        assertEquals(45, p.getGoals());
//        assertEquals(54, p.getAssists());
//        assertEquals(45 + 54, p.getPoints());
        
        ArrayList<Player> test = new ArrayList<Player>();
        test.add(new Player("Lemieux", "PIT", 45, 54));
        when(mockPlayerReader.getPlayers()).thenReturn(test);
        stats = new Statistics(mockPlayerReader);
        Player p = stats.search("Lemieux");
        assertEquals("PIT", p.getTeam());
        assertEquals(45, p.getGoals());
        assertEquals(54, p.getAssists());
        assertEquals(45 + 54, p.getPoints());

    }
    
    @Test
    public void teamMembersFound(){
//        List<Player> players = stats.team("EDM");
//        assertEquals(3, players.size());
//        for (Player player : players) {
//            assertEquals("EDM", player.getTeam());
//        }
        stats = new Statistics(mockPlayerReader);
        ArrayList<Player> test = new ArrayList<Player>();
        test.add(new Player("Lemieux", "PIT", 45, 54));
        test.add(new Player("Lemieuxin kaveri", "PIT", 45, 54));
        test.add(new Player("Ei Lemiexin kaveri", "EDM", 45, 54));
        when(mockPlayerReader.getPlayers()).thenReturn(test);
        stats = new Statistics(mockPlayerReader);
        List<Player> team = stats.team("PIT");
        for (Player p : team) {
            assertEquals("PIT", p.getTeam());
        }
        
    }
    
    @Test
    public void topScorersFound(){
        
        ArrayList<Player> test = new ArrayList<Player>();
        test.add(new Player("Gretzky", "PIT", 45, 54));
        test.add(new Player("Lemieux", "PIT", 45, 54));
        test.add(new Player("Pekka", "PIT", 45, 54));
        when(mockPlayerReader.getPlayers()).thenReturn(test);
        stats = new Statistics(mockPlayerReader);
        List<Player> players = stats.topScorers(2);
        assertEquals(3, players.size());
        assertEquals("Gretzky", players.get(0).getName());
        assertEquals("Lemieux", players.get(1).getName());
    }
}
