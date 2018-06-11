

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JuegoAdvancedTest {

    @Test
    public void whenBoardIsFullGameIsOver() {
        Juego game = new Juego(crearTablero(), new Player[0]);
        assertTrue(game.isOver());
    }

    @Test
    public void whoWonWhenXHasColumnReturnsX() {
        Player[] jugadores = crearJugadores();
        Juego game = new Juego(createColumnWinnerXBoard(), jugadores);
        assertEquals(game.whoWon(), jugadores[0]);
    }

    @Test
    public void whoWonWhenXHasLineReturnsX() {
        Player[] jugadores = crearJugadores();
        Juego game = new Juego(createLineWinnerXBoard(), jugadores);
        assertEquals(game.whoWon(), jugadores[0]);
    }

    @Test
    public void whoWonWhenXHasDiagonalReturnsX() {
        Player[] jugadores = crearJugadores();
        Juego game = new Juego(createDiagonalWinnerXBoard(), jugadores);
        assertEquals(game.whoWon(), jugadores[0]);
    }

    @Test
    public void whoWonWhenXHasInvertedDiagonalReturnsX() {
        Player[] jugadores = crearJugadores();
        Juego game = new Juego(createInvertedDiagonalWinnerXBoard(), jugadores);
        assertEquals(game.whoWon(), jugadores[0]);
    }

    @Test
    public void whoWonWhenNobodyWonReturnsNull() {
        Player[] jugadores = crearJugadores();
        Juego game = new Juego(crearTablero(), jugadores);
        assertNull(game.whoWon());
        
        
            
     //Metodos con mockito
    private Tablero crearTablero() {
        Tablero board = mock(Tablero.class);
        Casilla occupiedSquare = mock(Casilla.class);
        when(occupiedSquare.isOccupied()).thenReturn(true);
    }

    private Player[] crearJugadores() {
        Player[] players = new Player[2];
        Player playerX = mock(Player.class);
        when(playerX.getChip()).thenReturn('x');
        Player o = mock(Player.class);
        when(o.getChip()).thenReturn('o');
        players[0] = playerX;
        players[1] = o;
        return players;
    }

    private Casilla crearCasillaNeutral() {
        final Casilla cn = mock(Casilla.class);
        when(cn.getChip()).thenReturn(' ');
        when(cn.isOccupied()).thenReturn(false);
        return cn;
    }

    private Casilla crearXcasilla() {
        final Casilla cx = mock(Casilla.class);
        when(cx.getChip()).thenReturn('x');
        when(cx.isOccupied()).thenReturn(true);
        return cx;
    }

    private Tablero crearColumnasxGanadoras() {
        final Tablero board = mock(Tablero.class);

        final Casilla cn = crearCasillaNeutral();
        final Casilla cx = crearXcasilla();

        when(board.getCasilla(any(Posicion.class))).thenAnswer(new Answer<Casilla>() {
            public Casilla answer(InvocationOnMock invocationOnMock) {
                Posicion posicion = (Posicion) invocationOnMock.getArguments()[0];
                if (posicion.getX() == 0 && posicion.getY() == 0) {
                    return cx;
                }

                if (posicion.getX() == 1 && posicion.getY() == 0) {
                    return cx;
                }

                if (posicion.getX() == 2 && posicion.getY() == 0) {
                    return cx;
                }

                return cn;
            }
        });
        return board;
    }

    private Tablero CrearLineaXGanadora() {
        final Tablero board = mock(Tablero.class);

        final Casilla cn = crearCasillaNeutral();
        final Casilla cx = crearXcasilla();

        when(board.getCasilla(any(Posicion.class))).thenAnswer(new Answer<Casilla>() {
            public Casilla answer(InvocationOnMock invocationOnMock) {
                Posicion posicion = (Posicion) invocationOnMock.getArguments()[0];

                if (posicion.getX() == 0 && posicion.getY() == 0) {
                    return cx;
                }

                if (posicion.getX() == 0 && posicion.getY() == 1) {
                    return cx;
                }

                if (posicion.getX() == 0 && posicion.getY() == 2) {
                    return cx;
                }

                return cn;
            }
        });
        return board;
    }

    private Tablero CrearDigagonalGanadora() {
        final Tablero board = mock(Tablero.class);

        final Casilla cn = crearCasillaNeutral();
        final Casilla cx = crearXcasilla();

        when(board.getCasilla(any(Posicion.class))).thenAnswer(new Answer<Casilla>() {
            public Casilla answer(InvocationOnMock invocationOnMock) {
                Posicion posicion = (Posicion) invocationOnMock.getArguments()[0];

                if (posicion.getX() == 0 && posicion.getY() == 0) {
                    return cx;
                }

                if (posicion.getX() == 1 && posicion.getY() == 1) {
                    return cx;
                }

                if (posicion.getX() == 2 && posicion.getY() == 2) {
                    return cx;
                }

                return cn;
            }
        });
        return board;
    }

    private Tablero CrearDiagonalIndirectaGanadora() {
        final Tablero board = mock(Tablero.class);

        final Casilla cn = crearCasillaNeutral();
        final Casilla cx = crearXcasilla();

        when(board.getCasilla(any(Posicion.class))).thenAnswer(new Answer<Casilla>() {
            public Casilla answer(InvocationOnMock invocationOnMock) {
                Posicion posicion = (Posicion) invocationOnMock.getArguments()[0];

                if (posicion.getX() == 0 && posicion.getY() == 2) {
                    return cx;
                }

                if (posicion.getX() == 1 && posicion.getY() == 1) {
                    return cx;
                }

                if (posicion.getX() == 2 && posicion.getY() == 0) {
                    return cx;
                }

                return cn;
            }
        });
        return board;

    }
}
