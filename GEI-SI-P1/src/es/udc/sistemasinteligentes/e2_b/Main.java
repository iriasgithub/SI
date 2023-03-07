package es.udc.sistemasinteligentes.e2_b;

import es.udc.sistemasinteligentes.*;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws Exception {
        int[][] inicial = new int[3][3];
        inicial[0][0] = 4;
        inicial[0][1] = 9;
        inicial[0][2] = 2;

        inicial[1][0] = 3;
        inicial[1][1] = 5;
        inicial[1][2] = 0;

        inicial[2][0] = 0;
        inicial[2][1] = 1;
        inicial[2][2] = 0;


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