package es.udc.sistemasinteligentes.e2_b;

import es.udc.sistemasinteligentes.*;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class A_Estrella implements EstrategiaBusquedaInformada {
    public ArrayList<Nodo> soluciona (ProblemaBusqueda p) {return null;}

    void introduce_F (ArrayList<NodoInformado> E, ArrayList<NodoInformado> H, PriorityQueue<NodoInformado> F, Heuristica h){
        boolean introduce;
        boolean posible_remove;
        NodoInformado NodoEnFrontera = null;

        for (NodoInformado Nh : H) {
            for (NodoInformado Ne : E){
                if (Nh.getEstado().equals(Ne.getEstado())) {
                    break;
                }
            }
            introduce = true;
            posible_remove = false;
            for (NodoInformado Nf : F){
                if (Nh.getEstado().equals(Nf.getEstado())) {
                    introduce = false;
                    posible_remove = true;
                    NodoEnFrontera = Nf;
                    break;
                }

            }
            if (introduce) F.offer(Nh);
            Nh.f(h);
            if (posible_remove && Nh.compareTo(NodoEnFrontera) > 0)
                F.remove(NodoEnFrontera);
        }
    }

    public ArrayList<NodoInformado> reconstruye_sol (NodoInformado nMeta){
        ArrayList<NodoInformado> solucion = new ArrayList<>();
        NodoInformado a = nMeta;
        while (a!=null){
            solucion.add(0, a);
           a = a.getPadre();
        }
        return solucion;
    }
    public ArrayList<NodoInformado> sucesores (NodoInformado n, ProblemaBusqueda p){
        ArrayList<NodoInformado> sucesores = new ArrayList<>(); //Array donde guardaremos los sucesores del nodo meta
        Accion[] accDisponible = p.acciones(n.getEstado()); //Lista de acciones disponibles para el estado del nodo
        for (Accion acc: accDisponible){
            Estado res = p.result(n.getEstado(),acc);
            sucesores.add(new NodoInformado(res,n,acc));
        }
        return sucesores;

    }

    @Override
    public ArrayList<NodoInformado> soluciona(ProblemaBusqueda p, Heuristica h) throws Exception {
        int nodosExpandidos = 0;
        int nodosCreados = 0;
        ArrayList<NodoInformado> sucesores;
        ArrayList<NodoInformado> explorados = new ArrayList<>();
        PriorityQueue<NodoInformado> frontera = new PriorityQueue<>();

        Estado estadoActual = p.getEstadoInicial();
        NodoInformado nodoActual = new NodoInformado(estadoActual, null, null);
        nodosCreados ++;
        frontera.add(nodoActual);

        while (!frontera.isEmpty()) {
            nodoActual = frontera.poll();
            nodosExpandidos ++;
            estadoActual = nodoActual.getEstado();
            if (p.esMeta(estadoActual)) {
                System.out.println("Nodos creados: " + nodosCreados);
                System.out.println("Nodos expandidos: " + nodosExpandidos);
                return reconstruye_sol(nodoActual);
            }
            else {
                explorados.add(nodoActual);
                sucesores = sucesores(nodoActual, p);
                //Mirar de cambiar: es muy ineficiente!
                for (int i = 0; i < sucesores.size(); i++) {
                    nodosCreados++;
                }
            }
            introduce_F(explorados, sucesores, frontera, h);
        }
        throw new Exception("No se ha hallado solución\n");

    }

}
