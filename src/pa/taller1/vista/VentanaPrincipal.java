
package pa.taller1.vista;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

public class VentanaPrincipal extends JFrame {

    private JTextArea areaTexto;
    private JButton btnCargar;
    private JButton btnIniciar;
    private JButton btnLimpiar;
    private JButton btnSalir;
    private JFileChooser fileChooser;

    public VentanaPrincipal() {
        configurarVentana();
        configurarFileChooser();
        inicializarComponentes();
        setVisible(true);
    }

    private void configurarVentana() {
        setTitle("TORNEO DE BALERO");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
    }

    private void configurarFileChooser() {
        fileChooser = new JFileChooser();
        FileNameExtensionFilter filter =
                new FileNameExtensionFilter(
                        "Archivos Properties (*.properties)",
                        "properties");
        fileChooser.setFileFilter(filter);
        fileChooser.setCurrentDirectory(
                new File(System.getProperty("user.dir")));
    }

    private void inicializarComponentes() {

        // ===== TÍTULO =====
        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(new Color(25, 25, 112));
        panelTitulo.setPreferredSize(new Dimension(900, 70));

        JLabel lblTitulo = new JLabel("TORNEO DE BALERO",
                SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial",
                Font.BOLD, 28));
        lblTitulo.setForeground(Color.WHITE);

        panelTitulo.add(lblTitulo);
        add(panelTitulo, BorderLayout.NORTH);

        // ===== ÁREA DE TEXTO =====
        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        areaTexto.setFont(
                new Font("Monospaced",
                        Font.PLAIN, 13));
        areaTexto.setLineWrap(true);
        areaTexto.setWrapStyleWord(true);

        JScrollPane scrollPane =
                new JScrollPane(areaTexto);

        scrollPane.setBorder(
                BorderFactory.createTitledBorder(
                        "Registro del Juego"));

        add(scrollPane, BorderLayout.CENTER);

        // ===== BOTONES =====
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(
                new FlowLayout(
                        FlowLayout.CENTER, 20, 15));

        btnCargar = crearBoton("Cargar Properties");
        btnIniciar = crearBoton("Iniciar Juego");
        btnLimpiar = crearBoton("Limpiar");
        btnSalir = crearBoton("Salir");

        panelBotones.add(btnCargar);
        panelBotones.add(btnIniciar);
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnSalir);

        add(panelBotones, BorderLayout.SOUTH);
    }

    private JButton crearBoton(String texto) {
        JButton boton = new JButton(texto);
        boton.setPreferredSize(
                new Dimension(180, 40));
        boton.setFocusPainted(false);
        return boton;
    }

    // =========================
    // GETTERS DE BOTONES
    // =========================

    public JButton getBtnCargar() {
        return btnCargar;
    }

    public JButton getBtnIniciar() {
        return btnIniciar;
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public JButton getBtnSalir() {
        return btnSalir;
    }

    // =========================
    // MÉTODOS VISUALES
    // =========================

    public File solicitarArchivoPropiedades() {
        int resultado =
                fileChooser.showOpenDialog(this);

        if (resultado ==
                JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        }

        return null;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(
                this,
                mensaje,
                "Información",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(
                this,
                mensaje,
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    public void agregarTexto(String texto) {
        areaTexto.append(texto);
        areaTexto.setCaretPosition(
                areaTexto.getDocument()
                        .getLength());
    }

    public void limpiarTexto() {
        areaTexto.setText("");
    }
}