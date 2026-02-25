package controlador;

import Modelo.Jugador;

public class JugadorController {

    public void incrementarIntento(Jugador jugador) {
        jugador.setIntentos(jugador.getIntentos() + 1);
    }

    public void incrementarIntentoExitoso(Jugador jugador) {
        jugador.setIntentosExitosos(jugador.getIntentosExitosos() + 1);
    }

    public void sumarPuntaje(Jugador jugador, int puntos) {
        jugador.setPuntaje(jugador.getPuntaje() + puntos);
    }

    public void reiniciarEstadisticas(Jugador jugador) {
        jugador.setPuntaje(0);
        jugador.setIntentos(0);
        jugador.setIntentosExitosos(0);
    }
}
