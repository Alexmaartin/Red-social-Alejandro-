import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Post {
    protected Date fecha;
    protected List<Comentario> comentarios;
    protected Usuario usuario;

    public Post() {
        this.usuario = new Usuario();
        this.fecha = new Date();
        this.comentarios = new ArrayList<>();
    }

    public Post(Usuario usuario1){
        this.usuario = usuario1;
        this.fecha = new Date();
        this.comentarios = new ArrayList<>();
    }

    public Date getFecha() {
        return fecha;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void agregarComentario(Comentario comentario) {
        comentarios.add(comentario);
    }

}


