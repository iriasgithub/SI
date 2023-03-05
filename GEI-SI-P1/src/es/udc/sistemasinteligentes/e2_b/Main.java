package es.udc.sistemasinteligentes.e2_b;

import es.udc.sistemasinteligentes.EstrategiaBusqueda;
import es.udc.sistemasinteligentes.Nodo;
import es.udc.sistemasinteligentes.ProblemaBusqueda;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws Exception {
        int[][] inicial = new int[4][4];
        inicial[0][0] = 2;
        inicial[0][1] = 8;
        inicial[0][2] = 15;
        inicial[0][3] = 0;
        inicial[1][0] = 14;
        inicial[1][1] = 12;
        inicial[1][2] = 5;
        inicial[1][3] = 3;
        inicial[2][0] = 0;
        inicial[2][1] = 0;
        inicial[2][2] = 0;
        inicial[2][3] = 6;
        inicial[3][0] = 0;
        inicial[3][1] = 1;
        inicial[3][2] = 0;
        inicial[3][3] = 16;

        ProblemaCuadradoMagico.EstadoCuadradoMagico estadoInicial = new ProblemaCuadradoMagico.EstadoCuadradoMagico(inicial);
        ProblemaBusqueda cuadradoMagico = new ProblemaCuadradoMagico(estadoInicial);

        EstrategiaBusqueda buscador = new EstrategiaProfundidad();
        ArrayList<Nodo> sol = buscador.soluciona(cuadradoMagico);

        System.out.println("Reconstruyendo solución con BÚSQUEDA EN PROFUNDIDAD:\n");
        for (int i = 0; i < sol.size(); i++){
            if (i != sol.size() - 1)
                System.out.print(sol.get(i).getEstado().toString() + "  " + (char)8595 + "\n");
            else
                System.out.print(sol.get(i).getEstado().toString() + " \n");

        }

    }
}