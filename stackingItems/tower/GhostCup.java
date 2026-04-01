package tower;

import java.util.List;
import shapes.Rectangle;

/**
 * Taza fantasma.
 * Puede entrar dentro de una taza aunque esta ya esté cerrada con tapa.
 * No elimina tapas ni desplaza objetos: simplemente ignora el bloqueo
 * de las tapas al calcular su posición.
 */
public class GhostCup extends Cup {

    /**
     * Construye una taza fantasma.
     * @param id identificador único.
     * @param number número lógico de la taza.
     * @param color color de la taza.
     * @param x posición horizontal.
     * @param y posición vertical.
     * @param size tamaño de la taza.
     */
    public GhostCup(int id, int number, String color, int x, int y, int size) {
        super(id, number, color, x, y, size);

        Rectangle mark = new Rectangle();
        mark.changeSize(UNIT / 2, UNIT);
        mark.changeColor("white");
        mark.moveHorizontal(x - (UNIT / 2));
        mark.moveVertical(y - (((size + 1) * UNIT) / 2));
        shapes.add(mark);
    }

    /**
     * Calcula la posición vertical de la taza fantasma.
     * A diferencia de una taza normal, puede entrar dentro de una taza
     * aunque esa taza ya esté cerrada con tapa.
     * @param items elementos actuales de la torre.
     * @param index posición del item dentro de la lista.
     * @param towerTop cima actual de la torre.
     * @param floorY coordenada del piso.
     * @return coordenada Y en la que debe ubicarse la taza fantasma.
     */
    @Override
    public int calculateY(List<StackItem> items, int index, int towerTop, int floorY) {
        if (index == 0) {
            return floorY;
        }

        for (int i = index - 1; i >= 0; i--) {
            StackItem candidate = items.get(i);

            if (candidate.canContain(this)) {
                int innerTop = candidate.getInnerFloorY();

                for (int j = i + 1; j < index; j++) {
                    StackItem innerItem = items.get(j);

                    if (!innerItem.isLid() && innerItem.getTop() < innerTop) {
                        innerTop = innerItem.getTop();
                    }
                }

                return innerTop;
            }
        }

        return towerTop;
    }

    /**
     * Devuelve el subtipo de la taza.
     * @return subtipo ghost.
     */
    @Override
    public String getSubtypeName() {
        return "ghost";
    }
}