package tower;

import java.util.Iterator;
import java.util.List;

/**
 * Taza abridora.
 * Elimina las tapas que le impiden el paso antes de entrar a la torre.
 */
public class OpenerCup extends Cup {

    /**
     * Construye una taza abridora.
     * @param id identificador único.
     * @param number número lógico de la taza.
     * @param color color de la taza.
     * @param x posición horizontal.
     * @param y posición vertical.
     * @param size tamaño de la taza.
     */
    public OpenerCup(int id, int number, String color, int x, int y, int size) {
        super(id, number, color, x, y, size);
    }

    /**
     * Elimina las tapas de mayor tamaño que bloquearían el paso
     * de la taza abridora hacia el interior de tazas más grandes.
     * @param items elementos actuales de la torre.
     */
    @Override
    public void prepareInsertion(List<StackItem> items) {
        Iterator<StackItem> iterator = items.iterator();

        while (iterator.hasNext()) {
            StackItem item = iterator.next();

            if (item.isLid() && item.getSize() > getSize()) {
                item.makeInvisible();
                iterator.remove();
            }
        }
    }

    /**
     * Devuelve el subtipo de la taza.
     * @return subtipo opener.
     */
    @Override
    public String getSubtypeName() {
        return "opener";
    }
}