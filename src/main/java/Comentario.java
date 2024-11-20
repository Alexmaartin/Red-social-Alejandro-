import java.util.Date;

public class Comentario {
    private String texto;
    private Date fecha;
    private Usuario usuario;

    public Comentario(String texto, Usuario usuario) {
        this.texto = texto;
        this.fecha = new Date();
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return usuario.getNombre() + ": " + texto + " [" + fecha + "]";
    }
}


