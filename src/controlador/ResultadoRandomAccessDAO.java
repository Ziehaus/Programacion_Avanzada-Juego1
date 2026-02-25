package controlador;

import Modelo.Resultado;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class ResultadoRandomAccessDAO implements ResultadoDAO {

    private static final int TAMANIO_STRING = 30;
    private static final int TAMANIO_REGISTRO = 248;

    private String rutaArchivo;

    public ResultadoRandomAccessDAO(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    @Override
    public void guardarResultado(Resultado resultado) {

        try (RandomAccessFile archivo =
                     new RandomAccessFile(rutaArchivo, "rw")) {

            long longitud = archivo.length();

            int clave = (int) (longitud / TAMANIO_REGISTRO) + 1;

            archivo.seek(longitud);

            archivo.writeInt(clave);
            escribirStringFijo(archivo, resultado.getNombreEquipo());
            escribirStringFijo(archivo, resultado.getJugador1());
            escribirStringFijo(archivo, resultado.getJugador2());
            escribirStringFijo(archivo, resultado.getJugador3());
            archivo.writeInt(resultado.getPuntajeTotal());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Resultado> leerResultados() {

        List<Resultado> resultados = new ArrayList<>();

        try (RandomAccessFile archivo =
                     new RandomAccessFile(rutaArchivo, "r")) {

            while (archivo.getFilePointer() < archivo.length()) {

                int clave = archivo.readInt();
                String nombreEquipo = leerStringFijo(archivo);
                String jugador1 = leerStringFijo(archivo);
                String jugador2 = leerStringFijo(archivo);
                String jugador3 = leerStringFijo(archivo);
                int puntaje = archivo.readInt();

                Resultado resultado = new Resultado(
                        clave,
                        nombreEquipo.trim(),
                        jugador1.trim(),
                        jugador2.trim(),
                        jugador3.trim(),
                        puntaje
                );

                resultados.add(resultado);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultados;
    }

    @Override
    public int contarVictorias(String nombreEquipo) {

        int contador = 0;

        List<Resultado> resultados = leerResultados();

        for (Resultado r : resultados) {
            if (r.getNombreEquipo().equals(nombreEquipo)) {
                contador++;
            }
        }

        return contador;
    }

    private void escribirStringFijo(RandomAccessFile archivo, String texto)
            throws IOException {

        StringBuilder sb = new StringBuilder(texto);

        if (sb.length() > TAMANIO_STRING) {
            sb.setLength(TAMANIO_STRING);
        } else {
            while (sb.length() < TAMANIO_STRING) {
                sb.append(" ");
            }
        }

        archivo.writeChars(sb.toString());
    }

    private String leerStringFijo(RandomAccessFile archivo)
            throws IOException {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < TAMANIO_STRING; i++) {
            sb.append(archivo.readChar());
        }

        return sb.toString();
    }
}