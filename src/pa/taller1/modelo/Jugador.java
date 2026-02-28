
package pa.taller1.modelo;
    
import java.io.Serializable;

    public class Jugador implements Serializable {

        private String codigo;
        private String nombre;

        public Jugador() {}

        public Jugador(String codigo, String nombre) {
            this.codigo = codigo;
            this.nombre = nombre;
        }

        public String getCodigo() { return codigo; }

        public void setCodigo(String codigo) { this.codigo = codigo; }

        public String getNombre() { return nombre; }

        public void setNombre(String nombre) { this.nombre = nombre; }
    }
