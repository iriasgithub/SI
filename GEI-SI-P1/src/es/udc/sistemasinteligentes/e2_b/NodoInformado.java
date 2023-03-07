package es.udc.sistemasinteligentes.e2_b;

import es.udc.sistemasinteligentes.Accion;
import es.udc.sistemasinteligentes.Estado;
import es.udc.sistemasinteligentes.Heuristica;
import es.udc.sistemasinteligentes.Nodo;

public class NodoInformado implements Comparable<NodoInformado>{

    private final Estado estado;
    private final NodoInformado padre;
    private final Accion accion;
    private float coste;

    public Estado getEstado() {
        return estado;
    }

    public NodoInformado getPadre() {
        return padre;
    }

    public float getCoste() {
        return coste;
    }

    public NodoInformado(Estado estado, NodoInformado padre, Accion accion) {
        this.estado = estado;
        this.padre = padre;
        this.accion = accion;
        if (padre == null){
            this.coste = 0;
        }
        else this.coste = this.accion.getCoste() + this.getPadre().getCoste();

    }

    public void f (Heuristica h){
         this.coste = this.getCoste() + h.evalua(this.getEstado());
    }

    @Override
    public int compareTo(NodoInformado n) {
        return Float.compare(n.getCoste(), this.getCoste());
        /*Devuelve 1 si el nodo que llama al método es menor -> mayor prioridad
            Devuelve -1 si el nodo como parámetro es menor -> mayor prioridad
         */
    }
}
