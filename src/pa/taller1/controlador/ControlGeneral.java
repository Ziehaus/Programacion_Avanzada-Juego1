package pa.taller1.controlador;

import pa.taller1.modelo.Equipo;
import pa.taller1.modelo.Jugador;
import pa.taller1.modelo.TipoEmbocada;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

public class ControlGeneral {

    private ControlVista cVista;
    private ControlJuego cJuego;
    private ControlPersistencia cPersistencia;

    private List<Equipo> listaEquipos;

    public ControlGeneral() {

        this.cPersistencia = new ControlPersistencia();
        this.cJuego = new ControlJuego(this, new Random());
        this.cVista = new ControlVista(this);

        this.listaEquipos = new ArrayList<>();
    }

    // ===================================================
    // CARGA DE ARCHIVO PROPERTIES
    // ===================================================

    public void cargarArchivo(File archivo) {

        try {

            cPersistencia.asociarArchivo(archivo);

            Properties props = cPersistencia.abrirProperties();

            transformarPropertiesAEquipos(props);

            cVista.mostrarMensaje("Archivo cargado correctamente.");

        } catch (Exception e) {
            cVista.mostrarError("Error al cargar archivo.");
        }
        cVista.mostrarEquipos(listaEquipos);
    }

    // ===================================================
    // TRANSFORMACIÓN (SIN LÓGICA EN MODELO)
    // ===================================================

    private void transformarPropertiesAEquipos(Properties props) {

        listaEquipos.clear();

        // Ejemplo básico de estructura:
        // equipo.1.nombre=Equipo A
        // equipo.1.jugador1=Juan
        // equipo.1.jugador2=Pedro
        // equipo.1.jugador3=Luis

        int i = 1;

        while (true) {

            String nombreEquipo =
                    props.getProperty("equipo." + i + ".nombre");

            if (nombreEquipo == null) break;

            Equipo equipo = new Equipo();
            equipo.setNombreEquipo(nombreEquipo);

            List<Jugador> jugadores = new ArrayList<>();

            for (int j = 1; j <= 3; j++) {

                String nombreJugador =
                        props.getProperty(
                                "equipo." + i + ".jugador" + j);

                if (nombreJugador != null) {
                    Jugador jugador = new Jugador();
                    jugador.setNombre(nombreJugador);
                    jugadores.add(jugador);
                }
            }

            equipo.setJugadores((ArrayList<Jugador>) jugadores);

            listaEquipos.add(equipo);

            i++;
        }
    }

    // ===================================================
    // INICIO DEL JUEGO
    // ===================================================

    public void iniciarJuego(int tiempoPorEquipo) {

        if (listaEquipos.isEmpty()) {
            cVista.mostrarError(
                    "Debe cargar equipos primero.");
            return;
        }

        cJuego.inicializarJuego(
                listaEquipos,
                tiempoPorEquipo);

        cJuego.iniciarJuego();
    }

    // ===================================================
    // NOTIFICACIONES DESDE CONTROL JUEGO
    // ===================================================

    public void notificarInicioEquipo(Equipo equipo) {
        cVista.mostrarInicioEquipo(equipo);
    }

    public void notificarEmbocada(
            Jugador jugador,
            TipoEmbocada tipo,
            int puntos) {

        cVista.mostrarEmbocada(
                jugador,
                tipo,
                puntos);
    }

    public void notificarResultadoEquipo(
            Equipo equipo,
            int puntaje,
            int exitos,
            int intentos) {

        cVista.mostrarResultadoEquipo(
                equipo,
                puntaje,
                exitos,
                intentos);
    }

    public void notificarResultadoFinal(
            Equipo equipo,
            int puntaje) {

        cVista.mostrarGanador(
                equipo,
                puntaje);

        // Guardar ganador serializado
        try {
            cPersistencia.guardarObjetoSerializable(
                    equipo,
                    new File("data/ganador.dat"));
        } catch (Exception e) {
        }
    }
}