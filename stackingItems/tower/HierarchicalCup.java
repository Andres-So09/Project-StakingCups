package tower;

import java.util.List;

/**
 * Taza jerárquica.
 * Al entrar a la torre, desplaza los objetos de menor tamaño.
 * Si logra quedar en la base, no se deja quitar.
 */
public class HierarchicalCup extends Cup {

    /**
     * Construye una taza jerárquica.
     * @param id identificador único.
     * @param number número lógico de la taza.
     * @param color color de la taza.
     * @param x posición horizontal.
     * @param y posición vertical.
     * @param size tamaño de la taza.
     */
    public HierarchicalCup(int id, int number, String color, int x, int y, int size) {
        super(id, number, color, x, y, size);
    }

    /**
     * Devuelve la posición de inserción de la taza jerárquica.
     * Se ubica antes del primer objeto de menor tamaño, desplazándolo.
     * Si todos los objetos existentes son mayores o iguales, entra al final.
     * @param items elementos actuales de la torre.
     * @return índice de inserción.
     */
    @Override
    public int getInsertionIndex(List<StackItem> items) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getSize() < getSize()) {
                return i;
            }
        }
        return items.size();
    }

    /**
     * Indica si la taza jerárquica puede ser removida.
     * Si quedó en la base de la torre, no se deja quitar.
     * @param items elementos actuales de la torre.
     * @return true si puede salir; false si está en la base.
     */
    @Override
    public boolean canBeRemoved(List<StackItem> items) {
        return items.isEmpty() || items.get(0) != this;
    }

    /**
     * Devuelve el subtipo de la taza.
     * @return subtipo hierarchical.
     */
    @Override
    public String getSubtypeName() {
        return "hierarchical";
    }
}