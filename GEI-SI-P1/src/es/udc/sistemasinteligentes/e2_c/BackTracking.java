package es.udc.sistemasinteligentes.e2_c;

import es.udc.sistemasinteligentes.*;
import java.util.ArrayList;

public class BackTracking implements EstrategiaBusqueda {
    public Estado soluciona_ej(ProblemaBusqueda p) {
        return null;
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

    public Nodo backtracking (Nodo n, ProblemaBusqueda p){
        Nodo nodoMeta = null;
        ProblemaCuadradoMagico.EstadoCuadradoMagico es = (ProblemaCuadradoMagico.EstadoCuadradoMagico)n.getEstado();
        if(p.esMeta(es)) return n;
        if(es.NoTieneSucesores()){
            return null;
        }
        Accion[] accDisponible = p.acciones(n.getEstado()); //Lista de acciones disponibles para el estado del nodo
        for (Accion acc: accDisponible){
            Estado res = p.result(n.getEstado(),acc);
            Nodo newNodo = new Nodo(res,n,acc);
            nodoMeta = backtracking(newNodo,p);
            if (nodoMeta != null)
                break;
        }
        return nodoMeta;

    }

    @Override
    public Nodo[] soluciona(ProblemaBusqueda p) throws Exception {
        Nodo nodoMeta;

        Estado estadoActual = p.getEstadoInicial();
        Nodo nodoActual = new Nodo(estadoActual, null, null);

        if((nodoMeta = backtracking(nodoActual,p)) == null)
            throw new Exception("No se ha hallado solución\n");
        else {
            return reconstruye_sol(nodoMeta);
        }


    }

}
