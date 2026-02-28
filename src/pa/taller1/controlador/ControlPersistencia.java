package pa.taller1.controlador;

import java.io.*;
import java.util.Properties;

public class ControlPersistencia {

    private File archivoActual;

    public ControlPersistencia() {
    }

    public void asociarArchivo(File archivo) {
        this.archivoActual = archivo;
    }

    // =========================
    // PROPERTIES
    // =========================

    public Properties abrirProperties() throws IOException {

        if (archivoActual == null) {
            throw new IllegalStateException("No hay archivo asociado.");
        }

        if (!archivoActual.exists()) {
            throw new FileNotFoundException("Archivo no encontrado.");
        }

        Properties props = new Properties();

        try (FileInputStream fis = new FileInputStream(archivoActual)) {
            props.load(fis);
        }

        return props;
    }

    // =========================
    // SERIALIZACIÓN
    // =========================

    public void guardarObjetoSerializable(Object obj, File destino)
            throws IOException {

        try (ObjectOutputStream oos =
                     new ObjectOutputStream(
                             new FileOutputStream(destino))) {

            oos.writeObject(obj);
        }
    }

    public Object leerObjetoSerializable(File origen)
            throws IOException, ClassNotFoundException {

        try (ObjectInputStream ois =
                     new ObjectInputStream(
                             new FileInputStream(origen))) {

            return ois.readObject();
        }
    }

    // =========================
    // RANDOM ACCESS FILE
    // =========================

    public void escribirRandom(String texto, File archivo)
            throws IOException {

        try (RandomAccessFile raf =
                     new RandomAccessFile(archivo, "rw")) {

            raf.seek(raf.length());
            raf.writeUTF(texto);
        }
    }

    public void leerRandom(File archivo)
            throws IOException {

        try (RandomAccessFile raf =
                     new RandomAccessFile(archivo, "r")) {

            while (raf.getFilePointer() < raf.length()) {
                System.out.println(raf.readUTF());
            }
        }
    }
}