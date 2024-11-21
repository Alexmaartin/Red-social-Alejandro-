import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Post {
    protected Date fecha;
    protected List<Comentario> comentarios;
    protected User usuario;

    public Post() {
        this.usuario = new User();
        this.fecha = new Date();
        this.comentarios = new ArrayList<>();
    }

    public Post(User usuario1){
        this.usuario = usuario1;
        this.fecha = new Date();
        this.comentarios = new ArrayList<>();
    }

    public Date getDate() {
        return fecha;
    }

    public List<Comentario> getComment() {
        return comentarios;
    }

    public void addComment(Comentario comentario) {
        comentarios.add(comentario);
    }

}


