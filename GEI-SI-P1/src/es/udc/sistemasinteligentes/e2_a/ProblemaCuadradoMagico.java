package es.udc.sistemasinteligentes.e2_a;

import es.udc.sistemasinteligentes.Accion;
import es.udc.sistemasinteligentes.Estado;
import es.udc.sistemasinteligentes.ProblemaBusqueda;
import es.udc.sistemasinteligentes.ejemplo.ProblemaAspiradora;

public class ProblemaCuadradoMagico extends ProblemaBusqueda {
    public static class EstadoCuadradoMagico extends Estado{

        private int[][] board;
        int size;


        public EstadoCuadradoMagico(int[][] board) {
            this.board = board;
            this.size = board.length;

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
    public static class AccionCuadradoMagico extends Accion{


        public boolean esAplicable(Estado es) {
            /*
             * comprobamos si
             * @param es  cumple las precondiciones para aplicar la acción
             * @return true si en el tablero no está el número que se quiere insertar y que ese número está entre 1 y N^2
             * @return false en caso contrario
             */
        }


    }

}