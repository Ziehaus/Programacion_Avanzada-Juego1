package controlador;

import Modelo.Equipo;
import Modelo.Jugador;

import java.util.List;

public class EquipoController {

    public int calcularPuntajeEquipo(Equipo equipo) {

        int total = 0;

        for (Jugador jugador : equipo.getJugadores()) {
            total += jugador.getPuntaje();
        }

        return total;
    }

    public int obtenerTotalIntentosExitosos(Equipo equipo) {

        int total = 0;

        for (Jugador jugador : equipo.getJugadores()) {
            total += jugador.getIntentosExitosos();
        }

        return total;
    }

    public Equipo obtenerEquipoGanador(List<Equipo> equipos) {

        Equipo ganador = equipos.get(0);

        for (Equipo equipo : equipos) {

            if (calcularPuntajeEquipo(equipo) >
                calcularPuntajeEquipo(ganador)) {

                ganador = equipo;
            }

            else if (calcularPuntajeEquipo(equipo) ==
                     calcularPuntajeEquipo(ganador)) {

                ganador = resolverEmpate(ganador, equipo);
            }
        }

        return ganador;
    }

    private Equipo resolverEmpate(Equipo equipo1, Equipo equipo2) {

        int exitos1 = obtenerTotalIntentosExitosos(equipo1);
        int exitos2 = obtenerTotalIntentosExitosos(equipo2);

        if (exitos2 > exitos1) {
            return equipo2;
        }

        return equipo1;
    }
}