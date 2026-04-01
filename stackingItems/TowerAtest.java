import tower.*;
import javax.swing.JOptionPane;

public class TowerAtest {

    public static void main(String[] args) {
        acceptanceFearfulAndCrazy();
        acceptanceOpenerHierarchicalAndGhost();
    }

    private static void acceptanceFearfulAndCrazy() {
        Tower fearfulTower = new Tower(800, 20);
        fearfulTower.makeVisible();

        logState("Aceptación 1A - Torre vacía", fearfulTower);
        pause(1000);

        fearfulTower.pushLid("fearful", 5);
        logState("Fearful lid sin taza compañera: no debe entrar", fearfulTower);
        pause(1200);

        fearfulTower.pushCup(5);
        logState("Se agrega cup normal 5", fearfulTower);
        pause(1200);

        fearfulTower.pushLid("fearful", 5);
        logState("Fearful lid con taza compañera: entra y sella", fearfulTower);
        pause(1200);

        fearfulTower.popLid();
        logState("Fearful lid sellando: no debe salir", fearfulTower);
        pause(1500);

        fearfulTower.makeInvisible();

        Tower crazyTower = new Tower(800, 20);
        crazyTower.makeVisible();

        logState("Aceptación 1B - Nueva torre para crazy lid", crazyTower);
        pause(1000);

        crazyTower.pushCup(5);
        logState("Se agrega cup normal 5", crazyTower);
        pause(1200);

        crazyTower.pushLid("crazy", 5);
        logState("Crazy lid: debe ubicarse en la base y no sellar", crazyTower);
        pause(1500);

        askAcceptance("Aceptación 1",
            "¿Acepta la demostración de FearfulLid y CrazyLid?");
        crazyTower.makeInvisible();
    }

    private static void acceptanceOpenerHierarchicalAndGhost() {
        Tower openerTower = new Tower(800, 20);
        openerTower.makeVisible();

        logState("Aceptación 2A - Torre vacía", openerTower);
        pause(1000);

        openerTower.pushCup(7);
        openerTower.pushLid(7);
        logState("Taza 7 cerrada con su tapa", openerTower);
        pause(1200);

        openerTower.pushCup("opener", 5);
        logState("Opener cup: elimina la tapa que le impedía el paso", openerTower);
        pause(1500);

        openerTower.makeInvisible();

        Tower hierarchicalTower = new Tower(800, 20);
        hierarchicalTower.makeVisible();

        logState("Aceptación 2B - Nueva torre para hierarchical cup", hierarchicalTower);
        pause(1000);

        hierarchicalTower.pushCup(5);
        hierarchicalTower.pushCup(3);
        logState("Torre con cup 5 y cup 3", hierarchicalTower);
        pause(1200);

        hierarchicalTower.pushCup("hierarchical", 7);
        logState("Hierarchical cup: desplaza menores y llega a la base", hierarchicalTower);
        pause(1200);

        hierarchicalTower.removeCup(3);
        logState("Hierarchical cup en la base: no debe dejarse quitar", hierarchicalTower);
        pause(1200);

        hierarchicalTower.makeInvisible();

        Tower ghostTower = new Tower(800, 20);
        ghostTower.makeVisible();

        logState("Aceptación 2C - Nueva torre para ghost cup", ghostTower);
        pause(1000);

        ghostTower.pushCup(7);
        ghostTower.pushLid(7);
        logState("Cup 7 cerrada con su tapa", ghostTower);
        pause(1200);

        ghostTower.pushCup("ghost", 5);
        logState("Ghost cup: entra dentro de una taza cerrada sin quitar la tapa", ghostTower);
        pause(1500);

        askAcceptance("Aceptación 2",
            "¿Acepta la demostración de OpenerCup, HierarchicalCup y GhostCup?");
    }

    private static void logState(String title, Tower tower) {
        System.out.println("====================================");
        System.out.println(title);
        System.out.println("Height: " + tower.height());

        String[][] items = tower.stackingItems();
        for (int i = 0; i < items.length; i++) {
            System.out.println(i + ": " + items[i][0] + " " + items[i][1]);
        }

        int[] lided = tower.lidedCups();
        System.out.print("Lided cups: ");
        for (int i = 0; i < lided.length; i++) {
            System.out.print(lided[i] + " ");
        }
        System.out.println();
        System.out.println("====================================");
    }

    private static void askAcceptance(String title, String message) {
        int result = JOptionPane.showConfirmDialog(
            null,
            message,
            title,
            JOptionPane.YES_NO_OPTION
        );

        if (result == JOptionPane.YES_OPTION) {
            System.out.println(title + ": ACEPTADA");
        } else {
            System.out.println(title + ": NO ACEPTADA");
        }
    }

    private static void pause(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}