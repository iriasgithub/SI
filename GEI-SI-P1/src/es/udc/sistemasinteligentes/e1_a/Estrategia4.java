package es.udc.sistemasinteligentes.e1_a;

import es.udc.sistemasinteligentes.*;
import es.udc.sistemasinteligentes.e2_b.NodoInformado;

import java.util.ArrayList;

public class Estrategia4 implements EstrategiaBusqueda {

    public Estrategia4() {
    }
    public Estado soluciona_ej(ProblemaBusqueda p){return null;}

    public Nodo[] reconstruye_sol (Nodo nMeta){
        ArrayList<Nodo> solucion = new ArrayList<>(); //Array donde guardaremos la solucion
        Nodo a = nMeta;
        while (a!=null){ //Mientras el padre no sea nulo
            solucion.add(0, a); //Vamos añadiendo el nodo
            a = a.getPadre(); //Y convirtiendo el nodo en su padre
        }
        /*Cuando se llama al método toArray() con un arreglo vacío como argumento, Java utiliza la clase java.lang.reflect.Array
        para crear un nuevo arreglo con la misma cantidad de elementos que la colección. De esta manera, el método toArray()
        puede devolver un arreglo de la longitud necesaria para almacenar todos los elementos de la colección, incluso si
        ese arreglo se inicializó con una longitud de cero.*/
        return solucion.toArray(new Nodo[0]);
    }

    @Override
    public Nodo[] soluciona (ProblemaBusqueda p) throws Exception {
        ArrayList<Estado> explorados = new ArrayList<Estado>();
        Estado estadoActual = p.getEstadoInicial();
        explorados.add(estadoActual);
        Nodo nodoActual = new Nodo(estadoActual, null, null); //Creamos el nodo incial

        int i = 1;

        System.out.println((i++) + " - Empezando búsqueda en " + estadoActual);

        while (!p.esMeta(estadoActual)) {
            System.out.println((i++) + " - " + estadoActual + " no es meta");
            Accion[] accionesDisponibles = p.acciones(estadoActual);
            boolean modificado = false;
            for (Accion acc : accionesDisponibles) {
                Estado sc = p.result(estadoActual, acc);

                System.out.println((i++) + " - RESULT(" + estadoActual + "," + acc + ")=" + sc);
                if (!explorados.contains(sc)) {
                    estadoActual = sc;
                    nodoActual = new Nodo(sc, nodoActual, acc); //Vamos actualizando el nodo en cada paso
                    System.out.println((i++) + " - " + sc + " NO explorado");
                    explorados.add(estadoActual);
                    modificado = true;
                    System.out.println((i++) + " - Estado actual cambiado a " + estadoActual);
                    break;
                } else
                    System.out.println((i++) + " - " + sc + " ya explorado");
            }
            if (!modificado) throw new Exception("No se ha podido encontrar una solución");
        }
        System.out.println((i++) + " - FIN - " + estadoActual);
        return reconstruye_sol(nodoActual);
    }
}