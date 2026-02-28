package pa.taller1.modelo;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class ArchivoRandom {

    private File archivo;

    public ArchivoRandom(File archivo) {
        this.archivo = archivo;
    }

    public RandomAccessFile abrir(String modo) throws IOException {
        return new RandomAccessFile(archivo, modo);
    }
}
