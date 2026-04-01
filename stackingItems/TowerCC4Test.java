import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import tower.Tower;

public class TowerCC4Test {

    private void assertStackEquals(Tower tower, String[][] expected) {
        String[][] actual = tower.stackingItems();

        assertEquals(expected.length, actual.length, "La cantidad de elementos apilados no coincide.");
        for (int i = 0; i < expected.length; i++) {
            assertArrayEquals(expected[i], actual[i], "Diferencia en la posicion " + i + ".");
        }
    }

    @Test
    public void shouldPlaceCrazyLidAtBaseInsteadOfSealingCup() {
        Tower tower = new Tower(800, 20);

        tower.pushCup(5);
        tower.pushLid("crazy", 5);

        assertStackEquals(tower, new String[][]{
            {"lid", "5"},
            {"cup", "5"}
        });
        assertArrayEquals(new int[]{}, tower.lidedCups());
        assertEquals(6, tower.height());
        assertTrue(tower.ok());
    }

    @Test
    public void shouldAllowRemovingCrazyLidBecauseItDoesNotSealItsCup() {
        Tower tower = new Tower(800, 20);

        tower.pushCup(5);
        tower.pushLid("crazy", 5);

        tower.removeLid(2);

        assertStackEquals(tower, new String[][]{
            {"cup", "5"}
        });
        assertArrayEquals(new int[]{}, tower.lidedCups());
        assertTrue(tower.ok());
    }

    @Test
    public void shouldRemoveBlockingLidWhenOpenerCupNeedsToPass() {
        Tower tower = new Tower(800, 20);

        tower.pushCup(7);
        tower.pushLid(7);
        tower.pushCup("opener", 5);

        assertStackEquals(tower, new String[][]{
            {"cup", "7"},
            {"cup", "5"}
        });
        assertArrayEquals(new int[]{}, tower.lidedCups());
        assertEquals(7, tower.height());
        assertTrue(tower.ok());
    }

    @Test
    public void shouldRemoveAllBlockingLidsWhenOpenerCupPassesThroughSeveralClosedCups() {
        Tower tower = new Tower(800, 20);

        tower.pushCup(9);
        tower.pushLid(9);
        tower.pushCup(7);
        tower.pushLid(7);
        tower.pushCup("opener", 5);

        assertStackEquals(tower, new String[][]{
            {"cup", "9"},
            {"cup", "7"},
            {"cup", "5"}
        });
        assertArrayEquals(new int[]{}, tower.lidedCups());
        assertEquals(9, tower.height());
        assertTrue(tower.ok());
    }

    @Test
    public void shouldDisplaceSmallerCupsWhenHierarchicalCupEnters() {
        Tower tower = new Tower(800, 20);

        tower.pushCup(5);
        tower.pushCup(3);
        tower.pushCup("hierarchical", 7);

        assertStackEquals(tower, new String[][]{
            {"cup", "7"},
            {"cup", "5"},
            {"cup", "3"}
        });
        assertEquals(7, tower.height());
        assertTrue(tower.ok());
    }

    @Test
    public void shouldNotRemoveHierarchicalCupWhenItReachedBottom() {
        Tower tower = new Tower(800, 20);

        tower.pushCup(5);
        tower.pushCup(3);
        tower.pushCup("hierarchical", 7);

        tower.removeCup(3);

        assertStackEquals(tower, new String[][]{
            {"cup", "7"},
            {"cup", "5"},
            {"cup", "3"}
        });
        assertEquals(7, tower.height());
        assertFalse(tower.ok());
    }
}