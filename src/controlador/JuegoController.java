package controlador;

import Modelo.Equipo;
import Modelo.Jugador;
import Modelo.Resultado;
import Modelo.Embocada;

import java.util.List;
import java.util.Random;

public class JuegoController {

    private EquipoController equipoController;
    private JugadorController jugadorController;
    private EquipoDAO equipoDAO;
    private ResultadoDAO resultadoDAO;

    private List<Equipo> equipos;
    private int tiempoPorEquipo;

    private Random random;

    public JuegoController(EquipoController equipoController,
                           JugadorController jugadorController,
                           EquipoDAO equipoDAO,
                           ResultadoDAO resultadoDAO) {

        this.equipoController = equipoController;
        this.jugadorController = jugadorController;
        this.equipoDAO = equipoDAO;
        this.resultadoDAO = resultadoDAO;
        this.random = new Random();
    }

    public void cargarEquipos() {
        this.equipos = equipoDAO.cargarEquipos();
    }

    public void setTiempoPorEquipo(int tiempoPorEquipo) {
        this.tiempoPorEquipo = tiempoPorEquipo;
    }

    public Equipo iniciarJuego() {

        reiniciarEstadisticas();

        for (Equipo equipo : equipos) {
            ejecutarTurnoEquipo(equipo);
        }

        Equipo ganador = equipoController.obtenerEquipoGanador(equipos);

        int puntajeTotal = equipoController.calcularPuntajeEquipo(ganador);

        Resultado resultado = new Resultado(
                0,
                ganador.getNombreEquipo(),
                ganador.getJugadores().get(0).getNombre(),
                ganador.getJugadores().get(1).getNombre(),
                ganador.getJugadores().get(2).getNombre(),
                puntajeTotal
        );

        resultadoDAO.guardarResultado(resultado);

        return ganador;
    }

    private void ejecutarTurnoEquipo(Equipo equipo) {

        for (Jugador jugador : equipo.getJugadores()) {
            ejecutarTurnoJugador(jugador, tiempoPorEquipo);
        }
    }

    private void ejecutarTurnoJugador(Jugador jugador, int tiempo) {

        for (int i = 0; i < tiempo; i++) {

            jugadorController.incrementarIntento(jugador);

            Embocada embocada = generarEmbocadaAleatoria();

            if (embocada != Embocada.FALLA) {
                jugadorController.incrementarIntentoExitoso(jugador);
            }

            jugadorController.sumarPuntaje(jugador, embocada.getPuntaje());
        }
    }

    private Embocada generarEmbocadaAleatoria() {
        Embocada[] valores = Embocada.values();
        int indice = random.nextInt(valores.length);
        return valores[indice];
    }

    private void reiniciarEstadisticas() {

        for (Equipo equipo : equipos) {
            for (Jugador jugador : equipo.getJugadores()) {
                jugadorController.reiniciarEstadisticas(jugador);
            }
        }
    }
}