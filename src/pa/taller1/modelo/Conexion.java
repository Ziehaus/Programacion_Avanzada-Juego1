package pa.taller1.modelo;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Conexion {

    private RandomAccessFile raf;

    public void conectarRandom(String ruta) throws IOException {
        raf = new RandomAccessFile(ruta, "rw");
    }

    public void escribirLinea(String texto) throws IOException {
        raf.seek(raf.length());
        raf.writeUTF(texto);
    }

    public String leerLinea() throws IOException {
        if (raf.getFilePointer() < raf.length()) {
            return raf.readUTF();
        }
        return null;
    }

    public void cerrar() throws IOException {
        if (raf != null) {
            raf.close();
        }
    }
}
