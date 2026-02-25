package Modelo;

public class Resultado {

    private int clave;
    private String nombreEquipo;
    private String jugador1;
    private String jugador2;
    private String jugador3;
    private int puntajeTotal;

    public Resultado(int clave, String nombreEquipo,
                     String jugador1, String jugador2, String jugador3,
                     int puntajeTotal) {
        this.clave = clave;
        this.nombreEquipo = nombreEquipo;
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.jugador3 = jugador3;
        this.puntajeTotal = puntajeTotal;
    }

    public int getClave() {
        return clave;
    }

    public void setClave(int clave) {
        this.clave = clave;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public String getJugador1() {
        return jugador1;
    }

    public void setJugador1(String jugador1) {
        this.jugador1 = jugador1;
    }

    public String getJugador2() {
        return jugador2;
    }

    public void setJugador2(String jugador2) {
        this.jugador2 = jugador2;
    }

    public String getJugador3() {
        return jugador3;
    }

    public void setJugador3(String jugador3) {
        this.jugador3 = jugador3;
    }

    public int getPuntajeTotal() {
        return puntajeTotal;
    }

    public void setPuntajeTotal(int puntajeTotal) {
        this.puntajeTotal = puntajeTotal;
    }
}
