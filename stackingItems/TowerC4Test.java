import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import tower.Tower;

public class TowerC4Test {

    private void assertStackEquals(Tower tower, String[][] expected) {
        String[][] actual = tower.stackingItems();

        assertEquals(expected.length, actual.length, "La cantidad de elementos apilados no coincide.");
        for (int i = 0; i < expected.length; i++) {
            assertArrayEquals(expected[i], actual[i], "Diferencia en la posicion " + i + ".");
        }
    }

    @Test
    public void shouldNotAddFearfulLidWhenCompanionCupIsNotInTower() {
        Tower tower = new Tower(800, 20);

        tower.pushLid("fearful", 5);

        assertStackEquals(tower, new String[][]{});
        assertFalse(tower.ok());
    }

    @Test
    public void shouldAddFearfulLidWhenCompanionCupIsPresent() {
        Tower tower = new Tower(800, 20);

        tower.pushCup(5);
        tower.pushLid("fearful", 5);

        assertStackEquals(tower, new String[][]{
            {"cup", "5"},
            {"lid", "5"}
        });
        assertArrayEquals(new int[]{5}, tower.lidedCups());
        assertTrue(tower.ok());
    }

    @Test
    public void shouldNotPopFearfulLidWhenItIsSealingItsCup() {
        Tower tower = new Tower(800, 20);

        tower.pushCup(5);
        tower.pushLid("fearful", 5);

        tower.popLid();

        assertStackEquals(tower, new String[][]{
            {"cup", "5"},
            {"lid", "5"}
        });
        assertArrayEquals(new int[]{5}, tower.lidedCups());
        assertFalse(tower.ok());
    }

    @Test
    public void shouldNotRemoveFearfulLidWhenItIsSealingItsCup() {
        Tower tower = new Tower(800, 20);

        tower.pushCup(5);
        tower.pushLid("fearful", 5);

        tower.removeLid(2);

        assertStackEquals(tower, new String[][]{
            {"cup", "5"},
            {"lid", "5"}
        });
        assertArrayEquals(new int[]{5}, tower.lidedCups());
        assertFalse(tower.ok());
    }

    @Test
    public void shouldStillAllowCreatingNormalCupWithLegacyPushCup() {
        Tower tower = new Tower(800, 20);

        tower.pushCup(5);

        assertStackEquals(tower, new String[][]{
            {"cup", "5"}
        });
        assertTrue(tower.ok());
    }

    @Test
    public void shouldStillAllowCreatingNormalLidWithLegacyPushLid() {
        Tower tower = new Tower(800, 20);

        tower.pushCup(5);
        tower.pushLid(5);

        assertStackEquals(tower, new String[][]{
            {"cup", "5"},
            {"lid", "5"}
        });
        assertArrayEquals(new int[]{5}, tower.lidedCups());
        assertTrue(tower.ok());
    }
    
    @Test
    public void shouldAllowGhostCupInsideClosedCup() {
        Tower tower = new Tower(800, 20);
    
        tower.pushCup(7);
        tower.pushLid(7);
        tower.pushCup("ghost", 5);
    
        assertStackEquals(tower, new String[][]{
            {"cup", "7"},
            {"lid", "7"},
            {"cup", "5"}
        });
        assertArrayEquals(new int[]{7}, tower.lidedCups());
        assertEquals(8, tower.height());
        assertTrue(tower.ok());
    }
    
    @Test
    public void shouldNotRemoveBlockingLidWhenGhostCupEnters() {
        Tower tower = new Tower(800, 20);
    
        tower.pushCup(7);
        tower.pushLid(7);
        tower.pushCup("ghost", 5);
    
        assertArrayEquals(new int[]{7}, tower.lidedCups());
        assertTrue(tower.ok());
    }

}