package es.udc.sistemasinteligentes.e1_b;

import es.udc.sistemasinteligentes.EstrategiaBusqueda;
import es.udc.sistemasinteligentes.Nodo;
import es.udc.sistemasinteligentes.ProblemaBusqueda;

public class Main {

    public static void main(String[] args) throws Exception {
        ProblemaAspiradora.EstadoAspiradora estadoInicial = new ProblemaAspiradora.EstadoAspiradora(ProblemaAspiradora.EstadoAspiradora.PosicionRobot.IZQ,
                                                                                                    ProblemaAspiradora.EstadoAspiradora.PosicionBasura.IZQ);
        ProblemaBusqueda aspiradora = new ProblemaAspiradora(estadoInicial);

        EstrategiaBusqueda buscador = new BusquedaGrafo();
        Nodo[] sol = buscador.soluciona(aspiradora);

        System.out.println("Reconstruyendo solución con BÚSQUEDA GRAFO:");
        for (Nodo estado : sol){
            System.out.print(estado.getEstado().toString() + " ");
        }

    }
}