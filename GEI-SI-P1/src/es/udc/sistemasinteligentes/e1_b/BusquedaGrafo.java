package es.udc.sistemasinteligentes.e1_b;

import es.udc.sistemasinteligentes.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BusquedaGrafo implements EstrategiaBusqueda {

    public BusquedaGrafo() {
    }
    public Estado soluciona_ej(ProblemaBusqueda p){return null;}

    void introduce_F (ArrayList<Nodo> E, ArrayList<Nodo> H, Stack<Nodo> F){
        boolean introduce;
        for (Nodo Nh : H) {
            introduce = true;
            for (Nodo Nf : F){
                if (Nh.getEstado().equals(Nf.getEstado())) {
                    introduce = false;
                    break;
                }
            }
            for (Nodo Ne : E){
                if (Nh.getEstado().equals(Ne.getEstado())) {
                    introduce = false;
                    break;
                }
            }
            if (introduce) F.push(Nh);
        }
    }

    public Nodo[] reconstruye_sol (Nodo nMeta){
        ArrayList<Nodo> solucion = new ArrayList<>(); //Array donde guardaremos la solucion
        Nodo a = nMeta;
        while (a!=null){
            solucion.add(0, a);
            a = a.getPadre();
        }
        return solucion.toArray(new Nodo[0]);
    }

    public ArrayList<Nodo> sucesores (Nodo nMeta, ProblemaBusqueda p){
        ArrayList<Nodo> sucesores = new ArrayList<>(); //Array donde guardaremos los sucesores del nodo meta
        Accion[] accDisponible = p.acciones(nMeta.getEstado()); //Lista de acciones disponibles para el estado del nodo
        for (Accion acc: accDisponible){
            Estado res = p.result(nMeta.getEstado(),acc);
            sucesores.add(new Nodo(res,nMeta,acc));
        }
        return sucesores;

    }

    @Override
    public Nodo[] soluciona(ProblemaBusqueda p) throws Exception {
        ArrayList<Nodo> sucesores;
        ArrayList<Nodo> explorados = new ArrayList<>();
        Stack<Nodo> frontera = new Stack<>();

        Estado estadoActual = p.getEstadoInicial();
        Nodo nodoActual = new Nodo(estadoActual, null, null);
        frontera.add(nodoActual);

        while (!frontera.isEmpty()) {
            nodoActual = frontera.pop();
            estadoActual = nodoActual.getEstado();
            if (p.esMeta(estadoActual)) {
                return reconstruye_sol(nodoActual);
            }
            else {
                explorados.add(nodoActual);
                sucesores = sucesores(nodoActual, p);
                introduce_F(explorados, sucesores, frontera);
            }
        }
        throw new Exception("No se ha hallado solución\n");

    }
}
