package controlador;

import Modelo.Equipo;
import Modelo.Jugador;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class EquipoPropertiesDAO implements EquipoDAO {

    private String rutaArchivo;

    public EquipoPropertiesDAO(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    @Override
    public List<Equipo> cargarEquipos() {

        List<Equipo> equipos = new ArrayList<>();
        Properties properties = new Properties();

        try (FileInputStream fis = new FileInputStream(rutaArchivo)) {

            properties.load(fis);

            for (String key : properties.stringPropertyNames()) {

                String linea = properties.getProperty(key);

                String[] datos = linea.split(";");

                String proyecto = datos[0];
                String nombreEquipo = datos[1];

                Equipo equipo = new Equipo(proyecto, nombreEquipo);

                Jugador j1 = new Jugador(datos[2], datos[3]);
                Jugador j2 = new Jugador(datos[4], datos[5]);
                Jugador j3 = new Jugador(datos[6], datos[7]);

                equipo.getJugadores().add(j1);
                equipo.getJugadores().add(j2);
                equipo.getJugadores().add(j3);

                equipos.add(equipo);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return equipos;
    }

    @Override
    public void guardarEquipos(List<Equipo> equipos) {
        // No obligatorio para properties según el requerimiento
    }
}