package es.udc.sistemasinteligentes.e2_b;

import es.udc.sistemasinteligentes.*;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws Exception {
        int[][] inicial = {{4,9,2},{3,5,0},{0,1,0}};

        ProblemaCuadradoMagico.EstadoCuadradoMagico estadoInicial = new ProblemaCuadradoMagico.EstadoCuadradoMagico(inicial);
        ProblemaBusqueda cuadradoMagico = new ProblemaCuadradoMagico(estadoInicial);
        Heuristica heuristicaCuadradoMagico = new HeuristicaCuadradoMagico();

        EstrategiaBusquedaInformada buscador = new A_Estrella();
        ArrayList<NodoInformado> sol = buscador.soluciona(cuadradoMagico, heuristicaCuadradoMagico);

        System.out.println("Reconstruyendo solución con BÚSQUEDA EN PROFUNDIDAD:\n");
        for (int i = 0; i < sol.size(); i++){
            if (i != sol.size() - 1)
                System.out.print(sol.get(i).getEstado().toString() + "  " + (char)8595 + "\n");
            else
                System.out.print(sol.get(i).getEstado().toString() + " \n");
        }

    }
}