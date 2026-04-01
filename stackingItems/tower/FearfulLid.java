    package tower;

import java.util.List;

/**
 * Tapa temerosa.
 * No entra a la torre si su taza compañera no está presente,
 * y no puede salir si está sellando a su taza.
 */
public class FearfulLid extends Lid {

    /**
     * Construye una tapa temerosa.
     * @param id identificador único.
     * @param number número lógico de la tapa.
     * @param color color de la tapa.
     * @param x posición horizontal.
     * @param y posición vertical.
     * @param size tamaño de la tapa.
     */
    public FearfulLid(int id, int number, String color, int x, int y, int size) {
        super(id, number, color, x, y, size);
    }

    /**
     * Indica si la tapa puede entrar a la torre.
     * Solo entra si su taza compañera ya está presente.
     * @param items elementos actuales de la torre.
     * @return true si la taza compañera existe.
     */
    @Override
    public boolean canEnterTower(List<StackItem> items) {
        for (StackItem item : items) {
            if (item.isCup() && item.getNumber() == getNumber()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Indica si la tapa puede ser removida de la torre.
     * No puede salir si actualmente está sellando a su taza.
     * @param items elementos actuales de la torre.
     * @return true si no está sellando a una taza.
     */
    @Override
    public boolean canBeRemoved(List<StackItem> items) {
        return !hasPartner();
    }

    /**
     * Devuelve el subtipo de la tapa.
     * @return subtipo fearful.
     */
    @Override
    public String getSubtypeName() {
        return "fearful";
    }
}