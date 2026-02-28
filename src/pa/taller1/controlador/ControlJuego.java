package pa.taller1.controlador;

import java.util.List;
import java.util.Random;
import pa.taller1.modelo.Equipo;
import pa.taller1.modelo.Jugador;
import pa.taller1.modelo.TipoEmbocada;

public class ControlJuego {

    private static final int COL_PUNTAJE = 0;
    private static final int COL_EXITOS = 1;
    private static final int COL_INTENTOS = 2;

    private ControlGeneral cGeneral;

    private List<Equipo> listaEquipos;

    private int[][] tablaResultados;

    private int tiempoPorEquipo;
    private int tiempoPorJugador;

    private Random random;

    public ControlJuego(ControlGeneral cGeneral, Random random) {
        this.cGeneral = cGeneral;
        this.random = random;
    }

    public void inicializarJuego(List<Equipo> equipos, int tiempoEquipo) {

        this.listaEquipos = equipos;
        this.tiempoPorEquipo = tiempoEquipo;
        this.tiempoPorJugador = tiempoEquipo / 3;

        this.tablaResultados = new int[equipos.size()][3];
    }

    public void iniciarJuego() {

        for (int i = 0; i < listaEquipos.size(); i++) {

            cGeneral.notificarInicioEquipo(listaEquipos.get(i));

            jugarTurnoEquipo(i);

            cGeneral.notificarResultadoEquipo(
                    listaEquipos.get(i),
                    tablaResultados[i][COL_PUNTAJE],
                    tablaResultados[i][COL_EXITOS],
                    tablaResultados[i][COL_INTENTOS]
            );
        }

        int indiceGanador = determinarGanador();

        cGeneral.notificarResultadoFinal(
                listaEquipos.get(indiceGanador),
                tablaResultados[indiceGanador][COL_PUNTAJE]
        );
    }

    private void jugarTurnoEquipo(int indiceEquipo) {

        Equipo equipo = listaEquipos.get(indiceEquipo);
        List<Jugador> jugadores = equipo.getJugadores();

        for (Jugador jugador : jugadores) {
            jugarTurnoJugador(indiceEquipo, jugador);
        }
    }

    private void jugarTurnoJugador(int indiceEquipo, Jugador jugador) {

        long inicio = System.currentTimeMillis();

        while ((System.currentTimeMillis() - inicio)
                < tiempoPorJugador * 1000) {

            TipoEmbocada embocada = generarEmbocadaAleatoria();

            tablaResultados[indiceEquipo][COL_INTENTOS]++;

            if (embocada != TipoEmbocada.FALLA) {

                tablaResultados[indiceEquipo][COL_PUNTAJE] += embocada.getPuntos();
                tablaResultados[indiceEquipo][COL_EXITOS]++;

                cGeneral.notificarEmbocada(
                        jugador,
                        embocada,
                        embocada.getPuntos()
                );
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
        }
    }

    private TipoEmbocada generarEmbocadaAleatoria() {
        TipoEmbocada[] valores = TipoEmbocada.values();
        return valores[random.nextInt(valores.length)];
    }

    private int determinarGanador() {

        int indiceGanador = 0;

        for (int i = 1; i < tablaResultados.length; i++) {

            int puntajeActual = tablaResultados[i][COL_PUNTAJE];
            int puntajeGanador = tablaResultados[indiceGanador][COL_PUNTAJE];

            if (puntajeActual > puntajeGanador) {
                indiceGanador = i;
            }

            else if (puntajeActual == puntajeGanador) {

                int intentosActual = tablaResultados[i][COL_INTENTOS];
                int intentosGanador = tablaResultados[indiceGanador][COL_INTENTOS];

                // Gana el que logró lo mismo con MENOS intentos
                if (intentosActual < intentosGanador) {
                    indiceGanador = i;
                }
            }
        }

        return indiceGanador;
    }
}