package es.udc.sistemasinteligentes.e2_a;

import es.udc.sistemasinteligentes.*;
import es.udc.sistemasinteligentes.e2_b.NodoInformado;

import java.util.ArrayList;
import java.util.Stack;

public class EstrategiaProfundidad implements EstrategiaBusqueda {
    public ArrayList<NodoInformado> soluciona_A_Estrella(ProblemaBusqueda p){return null;}

    public Estado soluciona_ej(ProblemaBusqueda p) {
        return null;
    }
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
            if (introduce) F.add(Nh);
        }
    }

    public Nodo[] reconstruye_sol (Nodo nMeta){
        ArrayList<Nodo> solucion = new ArrayList<>();
        Nodo a = nMeta;
        while (a!=null){
            solucion.add(0, a);
            a = a.getPadre();
        }
        return solucion.toArray(new Nodo[0]);
    }
    public ArrayList<Nodo> sucesores (Nodo n, ProblemaBusqueda p){
        ArrayList<Nodo> sucesores = new ArrayList<>(); //Array donde guardaremos los sucesores del nodo meta
        Accion[] accDisponible = p.acciones(n.getEstado()); //Lista de acciones disponibles para el estado del nodo
        for (Accion acc: accDisponible){
            Estado res = p.result(n.getEstado(),acc);
            sucesores.add(new Nodo(res,n,acc));
        }
        return sucesores;

    }

    @Override
    public Nodo[] soluciona(ProblemaBusqueda p) throws Exception {
        int nodosExpandidos = 0;
        int nodosCreados = 0;
        ArrayList<Nodo> sucesores;
        ArrayList<Nodo> explorados = new ArrayList<>();
        Stack<Nodo> frontera = new Stack<>();

        Estado estadoActual = p.getEstadoInicial();
        Nodo nodoActual = new Nodo(estadoActual, null, null);
        nodosCreados ++;
        frontera.add(nodoActual);

        while (!frontera.isEmpty()) {
            nodoActual = frontera.pop();
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
                introduce_F(explorados, sucesores, frontera);
            }
        }
        throw new Exception("No se ha hallado solución\n");

    }

}
