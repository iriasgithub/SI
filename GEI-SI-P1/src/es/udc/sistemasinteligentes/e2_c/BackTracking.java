package es.udc.sistemasinteligentes.e2_c;

import es.udc.sistemasinteligentes.*;
import es.udc.sistemasinteligentes.e2_b.NodoInformado;

import java.util.ArrayList;
import java.util.Stack;

public class BackTracking implements EstrategiaBusqueda {
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
    /*public ArrayList<Nodo> sucesores (Nodo n, ProblemaBusqueda p){
        ArrayList<Nodo> sucesores = new ArrayList<>(); //Array donde guardaremos los sucesores del nodo meta
        Accion[] accDisponible = p.acciones(n.getEstado()); //Lista de acciones disponibles para el estado del nodo
        for (Accion acc: accDisponible){
            Estado res = p.result(n.getEstado(),acc);
            sucesores.add(new Nodo(res,n,acc));
        }
        return sucesores;

    }*/

    public Nodo sucesores (ArrayList<Nodo> E, Nodo n, ProblemaBusqueda p){
        Nodo nodoMeta = null;
        ProblemaCuadradoMagico.EstadoCuadradoMagico es = (ProblemaCuadradoMagico.EstadoCuadradoMagico)n.getEstado();
        if(p.esMeta(es)) nodoMeta = n;
        for (Nodo Ne : E){
            if (Ne.getEstado().equals(n.getEstado())) {
                return null;
            }
        }
        if(es.NoTieneSucesores()){
            nodoMeta = null;
        }
        Accion[] accDisponible = p.acciones(n.getEstado()); //Lista de acciones disponibles para el estado del nodo
        E.add(n);
        for (Accion acc: accDisponible){
            Estado res = p.result(n.getEstado(),acc);
            Nodo newNodo = new Nodo(res,n,acc);
            nodoMeta = sucesores(E,newNodo,p);
            if (nodoMeta != null)
                break;
        }
        return nodoMeta;

    }

    @Override
    public Nodo[] soluciona(ProblemaBusqueda p) throws Exception {
        int nodosExpandidos = 0;
        int nodosCreados = 0;
        ArrayList<Nodo> explorados = new ArrayList<>();
        Nodo nodoMeta = null;

        Estado estadoActual = p.getEstadoInicial();
        Nodo nodoActual = new Nodo(estadoActual, null, null);
        nodosCreados ++;

        if((nodoMeta = sucesores(explorados,nodoActual,p)) == null)
            throw new Exception("No se ha hallado solución\n");
        else return reconstruye_sol(nodoMeta);

    }

}
