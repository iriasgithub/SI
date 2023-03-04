package es.udc.sistemasinteligentes;

public class Nodo {

    private final Estado estado;
    private final Nodo padre;
    private final Accion accion;

    public Estado getEstado() {
        return estado;
    }

    public Nodo getPadre() {
        return padre;
    }

    public Nodo(Estado estado, Nodo padre, Accion accion) {
        this.estado = estado;
        this.padre = padre;
        this.accion = accion;
    }
}
