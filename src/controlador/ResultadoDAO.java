package controlador;

import Modelo.Resultado;
import java.util.List;

public interface ResultadoDAO {

    void guardarResultado(Resultado resultado);

    List<Resultado> leerResultados();

    int contarVictorias(String nombreEquipo);
}