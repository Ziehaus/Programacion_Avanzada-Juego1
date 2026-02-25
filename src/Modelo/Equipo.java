package Modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Equipo implements Serializable {

    private String nombreProyectoCurricular;
    private String nombreEquipo;
    private List<Jugador> jugadores;

    public Equipo(String nombreProyectoCurricular, String nombreEquipo) {
        this.nombreProyectoCurricular = nombreProyectoCurricular;
        this.nombreEquipo = nombreEquipo;
        this.jugadores = new ArrayList<>();
    }

    public String getNombreProyectoCurricular() {
        return nombreProyectoCurricular;
    }

    public void setNombreProyectoCurricular(String nombreProyectoCurricular) {
        this.nombreProyectoCurricular = nombreProyectoCurricular;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }
}
