package controlador;

import Modelo.Equipo;
import java.util.List;

public interface EquipoDAO {

    List<Equipo> cargarEquipos();

    void guardarEquipos(List<Equipo> equipos);
}