package pa.taller1.modelo;

public enum TipoEmbocada {

    SIMPLE(2),
    DOBLE(10),
    VERTICAL(3),
    MARIQUITA(4),
    PUNALADA(5),
    PURTINA(6),
    DOMINIO_REVES(8),
    FALLA(0);

    private final int puntos;

    private TipoEmbocada(int puntos) {
        this.puntos = puntos;
    }

    public int getPuntos() {
        return puntos;
    }
}

