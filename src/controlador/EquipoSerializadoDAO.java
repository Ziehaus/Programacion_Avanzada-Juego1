package controlador;

import Modelo.Equipo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EquipoSerializadoDAO implements EquipoDAO {

    private String rutaArchivo;

    public EquipoSerializadoDAO(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    @Override
    public List<Equipo> cargarEquipos() {

        File archivo = new File(rutaArchivo);

        if (!archivo.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(archivo))) {

            return (List<Equipo>) ois.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    @Override
    public void guardarEquipos(List<Equipo> equipos) {

        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(rutaArchivo))) {

            oos.writeObject(equipos);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}