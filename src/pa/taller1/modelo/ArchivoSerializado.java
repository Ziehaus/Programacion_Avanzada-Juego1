package pa.taller1.modelo;

import java.io.*;

public class ArchivoSerializado {

    private File archivo;

    public ArchivoSerializado(File archivo) {
        this.archivo = archivo;
    }

    public ObjectOutputStream abrirSalida() throws IOException {
        return new ObjectOutputStream(
                new FileOutputStream(archivo));
    }

    public ObjectInputStream abrirEntrada() throws IOException {
        return new ObjectInputStream(
                new FileInputStream(archivo));
    }
}
