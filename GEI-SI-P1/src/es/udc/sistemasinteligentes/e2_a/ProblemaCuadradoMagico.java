package es.udc.sistemasinteligentes.e2_a;

import es.udc.sistemasinteligentes.Accion;
import es.udc.sistemasinteligentes.Estado;
import es.udc.sistemasinteligentes.ProblemaBusqueda;
import es.udc.sistemasinteligentes.ejemplo.ProblemaAspiradora;

import java.util.ArrayList;

import static java.lang.Math.pow;

public class ProblemaCuadradoMagico extends ProblemaBusqueda {
    public static class EstadoCuadradoMagico extends Estado{

        private int[][] board;
        int size;


        public EstadoCuadradoMagico(int[][] board) {
            this.board = board;
            this.size = board.length;

        }

        public EstadoCuadradoMagico InsertarNumero (int numero, int fila, int columna){
            board[fila][columna] = numero;
            return new EstadoCuadradoMagico(board);
        }

        @Override
        public String toString(){
            StringBuilder out = new StringBuilder();
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    out.append(board[i][j]).append(" ");
                }
                out.append("\n");
            }
            return out.toString();
        }
        @Override
        public boolean equals(Object o){
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ProblemaCuadradoMagico.EstadoCuadradoMagico that = (ProblemaCuadradoMagico.EstadoCuadradoMagico) o;
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if(this.board[i][j] != that.board[i][j])
                        return false;
                }
            }
            return true;
        }
        @Override
        public int hashCode() {
            int result = 23;
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    result = 31 * result + board[i][j];
                }
            }
            return result;
        }
    }
    public static class AccionCuadradoMagico extends Accion {
        int valor;
        int fila;
        int columna;

        public AccionCuadradoMagico(int valor, int fila, int columna) {
            this.valor = valor;
            this.fila = fila;
            this.columna = columna;
        }

        @Override
        public String toString() {
            return valor + " -> (i=" + fila + ",j=" + columna + ")";
        }

        public boolean esAplicable(Estado es) {
            EstadoCuadradoMagico s = (EstadoCuadradoMagico)es;
            int size = s.size;
            //if (valor < 1 || valor < size) return false; NO hace falta esta precondición, se controla en el met acciones
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (s.board[i][j] == valor) //Si el número ya existe en el tablero
                        return false;
                }
            }
            return true;
        }
        @Override
        public Estado aplicaA(Estado es){
            EstadoCuadradoMagico nuevoEstado;
            EstadoCuadradoMagico s = (EstadoCuadradoMagico)es;
            nuevoEstado = s.InsertarNumero(valor, fila, columna);
            return nuevoEstado;
        }
    }

    public ProblemaCuadradoMagico(Estado estadoInicial) {
        super(estadoInicial);
    }
    @Override
    public boolean esMeta(Estado es){
        EstadoCuadradoMagico s = (EstadoCuadradoMagico)es;
        int size = s.size;
        int sum_col = 0;
        int sum_row = 0;
        int sum_d1 = 0;
        int sum_d2 = 0;
        int sumaMagica = size * ((int)pow(size,2) + 1) / 2;

        for (int i = 0; i < size; i++) {
            sum_d1 += s.board[i][i];
            sum_d2 += s.board[i][size-i-1];
            for (int j = 0; j < size; j++) {
               sum_col += s.board[i][j];
               sum_row += s.board[j][i];
            }
            if (sum_col != sumaMagica || sum_row != sumaMagica
                || sum_d1 != sumaMagica || sum_d2 != sumaMagica)
                return false;
        }
        return true;
    }

    @Override
    public Accion[] acciones(Estado es) {
        ArrayList<Accion> accAL = new ArrayList<>();
        EstadoCuadradoMagico s = (EstadoCuadradoMagico)es;
        int size = s.size;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(s.board[i][j] == 0){
                    for (int n = 1; n < size; n++){
                        Accion accion = new AccionCuadradoMagico(n, i, j);
                        if (accion.esAplicable(es))
                            accAL.add(accion);

                    }
                }
            }
        }
        //Convertimos el ArrayList a Array
        Accion[] accA = new Accion[accAL.size()];
        accA = accAL.toArray(accA);
        return accA;
    }
}