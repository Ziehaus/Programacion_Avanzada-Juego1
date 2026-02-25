
package Modelo;

import java.io.Serializable;

public class Jugador implements Serializable {

    private String codigo;
    private String nombre;
    private int puntaje;
    private int intentos;
    private int intentosExitosos;

    public Jugador(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.puntaje = 0;
        this.intentos = 0;
        this.intentosExitosos = 0;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public int getIntentos() {
        return intentos;
    }

    public void setIntentos(int intentos) {
        this.intentos = intentos;
    }

    public int getIntentosExitosos() {
        return intentosExitosos;
    }

    public void setIntentosExitosos(int intentosExitosos) {
        this.intentosExitosos = intentosExitosos;
    }
}