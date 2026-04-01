package tower;

import java.util.List;

/**
 * Tapa loca.
 * En lugar de tapar a su taza, se ubica en la base de la torre.
 */
public class CrazyLid extends Lid {

    /**
     * Construye una tapa loca.
     * @param id identificador único.
     * @param number número lógico de la tapa.
     * @param color color de la tapa.
     * @param x posición horizontal.
     * @param y posición vertical.
     * @param size tamaño de la tapa.
     */
    public CrazyLid(int id, int number, String color, int x, int y, int size) {
        super(id, number, color, x, y, size);
    }

    /**
     * Indica si esta tapa puede sellar a otro objeto.
     * Una tapa loca no sella tazas.
     * @param other elemento candidato a sellar.
     * @return false siempre.
     */
    @Override
    public boolean canSeal(StackItem other) {
        return false;
    }

    /**
     * Indica la posición de inserción de la tapa loca.
     * Siempre entra en la base de la torre.
     * @param items elementos actuales de la torre.
     * @return índice 0.
     */
    @Override
    public int getInsertionIndex(List<StackItem> items) {
        return 0;
    }

    /**
     * Devuelve el subtipo de la tapa.
     * @return subtipo crazy.
     */
    @Override
    public String getSubtypeName() {
        return "crazy";
    }
}