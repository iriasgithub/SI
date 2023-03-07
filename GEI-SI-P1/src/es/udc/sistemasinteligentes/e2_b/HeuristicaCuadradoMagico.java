package es.udc.sistemasinteligentes.e2_b;
import es.udc.sistemasinteligentes.*;


public class HeuristicaCuadradoMagico extends Heuristica {

    @Override
    public float evalua(Estado e) {
        ProblemaCuadradoMagico.EstadoCuadradoMagico ec = (ProblemaCuadradoMagico.EstadoCuadradoMagico) e;
            int n = ec.getSize();
            int [][] board = ec.getBoard();
            int sum = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int value = board[i][j];
                    if (value != 0) { // skip the empty cell
                        int goalX = (value - 1) / n;
                        int goalY = (value - 1) % n;
                        sum += Math.abs(i - goalX) + Math.abs(j - goalY);
                    }
                }
            }
            return sum;
    }
}

