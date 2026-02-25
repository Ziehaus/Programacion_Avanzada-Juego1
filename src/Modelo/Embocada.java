package Modelo;

public enum Embocada {

    SIMPLE(2),
    DOBLE(10),
    VERTICAL(3),
    MARIQUITA(4),
    PUÑALADA(5),
    PURTIÑA(6),
    DOMINIO_REVES(8),
    FALLA(0);

    private int puntaje;

    private Embocada(int puntaje) {
        this.puntaje = puntaje;
    }

    public int getPuntaje() {
        return puntaje;
    }
}