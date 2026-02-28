package pa.taller1.controlador;

import pa.taller1.modelo.Equipo;
import pa.taller1.modelo.Jugador;
import pa.taller1.modelo.TipoEmbocada;
import pa.taller1.vista.VentanaPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

public class ControlVista implements ActionListener {

    private VentanaPrincipal vista;
    private ControlGeneral cGeneral;

    public ControlVista(ControlGeneral cGeneral) {
        this.cGeneral = cGeneral;
        this.vista = new VentanaPrincipal();

        this.vista.getBtnCargar().addActionListener(this);
        this.vista.getBtnIniciar().addActionListener(this);
        this.vista.getBtnLimpiar().addActionListener(this);
        this.vista.getBtnSalir().addActionListener(this);
    }

    // =========================
    // EVENTOS DE BOTONES
    // =========================

    @Override
    public void actionPerformed(ActionEvent e) {

        Object origen = e.getSource();

        if (origen == vista.getBtnCargar()) {
            solicitarCargaArchivo();
        }

        else if (origen == vista.getBtnIniciar()) {
            solicitarInicioJuego();
        }

        else if (origen == vista.getBtnLimpiar()) {
            vista.limpiarTexto();
        }

        else if (origen == vista.getBtnSalir()) {
            System.exit(0);
        }
    }

    // =========================
    // DELEGACIONES AL CONTROL GENERAL
    // =========================

    private void solicitarCargaArchivo() {

        File archivo = vista.solicitarArchivoPropiedades();

        if (archivo == null) {
            vista.mostrarMensaje("Selección cancelada.");
            return;
        }

        if (!archivo.getName().toLowerCase().endsWith(".properties")) {
            vista.mostrarError("El archivo no es válido.");
            return;
        }

        cGeneral.cargarArchivo(archivo);
    }

    private void solicitarInicioJuego() {

        String tiempoStr = 
                javax.swing.JOptionPane.showInputDialog(
                        vista,
                        "Ingrese tiempo por equipo (segundos):");

        if (tiempoStr == null) return;

        try {
            int tiempo = Integer.parseInt(tiempoStr);
            cGeneral.iniciarJuego(tiempo);
        } catch (NumberFormatException ex) {
            vista.mostrarError("Tiempo inválido.");
        }
    }

    // =========================
    // MÉTODOS DE NOTIFICACIÓN
    // =========================

    public void mostrarInicioEquipo(Equipo equipo) {

        vista.agregarTexto("\n==============================\n");
        vista.agregarTexto("Inicia equipo: "
                + equipo.getNombreEquipo() + "\n");
    }

    public void mostrarEmbocada(
            Jugador jugador,
            TipoEmbocada tipo,
            int puntos) {

        vista.agregarTexto(
                jugador.getNombre()
                        + " logró "
                        + tipo.name()
                        + " (+" + puntos + " pts)\n");
    }

    public void mostrarResultadoEquipo(
            Equipo equipo,
            int puntaje,
            int exitos,
            int intentos) {

        vista.agregarTexto("\nResultado equipo "
                + equipo.getNombreEquipo() + "\n");

        vista.agregarTexto("Puntaje: " + puntaje + "\n");
        vista.agregarTexto("Embocadas exitosas: " + exitos + "\n");
        vista.agregarTexto("Intentos totales: " + intentos + "\n");
    }

    public void mostrarGanador(
            Equipo equipo,
            int puntaje) {

        vista.agregarTexto("\n==============================\n");
        vista.agregarTexto("🏆 GANADOR DEL TORNEO 🏆\n");
        vista.agregarTexto("Equipo: "
                + equipo.getNombreEquipo() + "\n");
        vista.agregarTexto("Puntaje final: "
                + puntaje + "\n");

        vista.mostrarMensaje("Ganador: "
                + equipo.getNombreEquipo());
    }
    public void mostrarEquipos(List<Equipo> equipos) {

        vista.limpiarTexto();

        vista.agregarTexto("=== EQUIPOS CARGADOS ===\n\n");

        for (Equipo e : equipos) {

            vista.agregarTexto("Equipo: "
                    + e.getNombreEquipo() + "\n");

            for (Jugador j : e.getJugadores()) {
                vista.agregarTexto("   - "
                        + j.getNombre() + "\n");
            }

            vista.agregarTexto("\n");
        }
    }

    public void mostrarMensaje(String mensaje) {
        vista.mostrarMensaje(mensaje);
    }

    public void mostrarError(String mensaje) {
        vista.mostrarError(mensaje);
    }
}