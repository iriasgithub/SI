package es.udc.sistemasinteligentes.e2_a;

import es.udc.sistemasinteligentes.EstrategiaBusqueda;
import es.udc.sistemasinteligentes.Nodo;
import es.udc.sistemasinteligentes.ProblemaBusqueda;

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