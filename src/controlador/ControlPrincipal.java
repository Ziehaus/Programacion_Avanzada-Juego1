package controlador;

import Modelo.Equipo;

import java.io.File;
import java.util.List;

public class ControlPrincipal {

    private static final String RUTA_PROPERTIES = "equipos.properties";
    private static final String RUTA_SERIALIZADO = "equipos.dat";
    private static final String RUTA_RESULTADOS = "resultados.dat";

    public void ejecutar() {

        // 1️⃣ Decidir qué DAO usar para equipos
        EquipoDAO equipoDAO;

        File archivoSerializado = new File(RUTA_SERIALIZADO);

        if (archivoSerializado.exists()) {
            equipoDAO = new EquipoSerializadoDAO(RUTA_SERIALIZADO);
        } else {
            equipoDAO = new EquipoPropertiesDAO(RUTA_PROPERTIES);
        }

        // 2️⃣ DAO para resultados históricos
        ResultadoDAO resultadoDAO =
                new ResultadoRandomAccessDAO(RUTA_RESULTADOS);

        // 3️⃣ Crear controladores de lógica
        EquipoController equipoController = new EquipoController();
        JugadorController jugadorController = new JugadorController();

        // 4️⃣ Inyección de dependencias
        JuegoController juegoController = new JuegoController(
                equipoController,
                jugadorController,
                equipoDAO,
                resultadoDAO
        );

        // 5️⃣ Cargar equipos
        juegoController.cargarEquipos();

        // Si cargó desde properties por primera vez,
        // guardar serializado para próximas ejecuciones
        if (!archivoSerializado.exists()) {
            List<Equipo> equipos = equipoDAO.cargarEquipos();
            new EquipoSerializadoDAO(RUTA_SERIALIZADO)
                    .guardarEquipos(equipos);
        }

        // 6️⃣ Configurar juego
        juegoController.setTiempoPorEquipo(10);

        // 7️⃣ Ejecutar
        Equipo ganador = juegoController.iniciarJuego();

        // 8️⃣ Mostrar información básica (provisional)
        System.out.println("Equipo ganador: " + ganador.getNombreEquipo());
    }
}
