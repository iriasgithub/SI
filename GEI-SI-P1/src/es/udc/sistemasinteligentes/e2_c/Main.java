package es.udc.sistemasinteligentes.e2_c;

import es.udc.sistemasinteligentes.EstrategiaBusqueda;
import es.udc.sistemasinteligentes.Nodo;
import es.udc.sistemasinteligentes.ProblemaBusqueda;

public class Main {

    public static void main(String[] args) throws Exception {
        int[][] inicial = {{4,0,0},{0,0,0},{0,0,0}};

        ProblemaCuadradoMagico.EstadoCuadradoMagico estadoInicial = new ProblemaCuadradoMagico.EstadoCuadradoMagico(inicial);
        ProblemaBusqueda cuadradoMagico = new ProblemaCuadradoMagico(estadoInicial);

        EstrategiaBusqueda buscador = new BackTracking();
        Nodo[] sol = buscador.soluciona(cuadradoMagico);

        System.out.println("Reconstruyendo solución con BÚSQUEDA CON BACKTRACKING:\n");
        for (int i = 0; i < sol.length; i++){
            if (i != sol.length - 1)
                System.out.print(sol[i].getEstado().toString() + "  " + (char)8595 + "\n");
            else
                System.out.print(sol[i].getEstado().toString() + " \n");

        }

    }
}