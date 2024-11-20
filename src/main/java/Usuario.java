import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nombre;
    private List<Usuario> siguiendo;
    private List<Post> posts;
    private List<Usuario> comentarios;

    public Usuario(String nombre) {
        this.nombre = nombre;
        this.siguiendo = new ArrayList<>();
        this.posts = new ArrayList<>();
    }

    public Usuario() {

    }

    public void agregarPost(Post post) {
        posts.add(post);
        System.out.println("Post añadido por " + nombre + ": " + post);
    }

    public String getNombre() {
        return nombre;
    }

    public List<Usuario> getSiguiendo() {
        return siguiendo;
    }
    public List<Usuario> getComentarios(){
        return comentarios;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void seguirUsuario(Usuario usuario) {
        if (!siguiendo.contains(usuario)) {
            siguiendo.add(usuario);
            System.out.println(nombre + " ahora sigue a " + usuario.getNombre());
        } else {
            System.out.println("Ya sigues a " + usuario.getNombre());
        }
    }

    public void dejarDeSeguir(Usuario usuario) {
        if (siguiendo.remove(usuario)) {
            System.out.println(nombre + " ha dejado de seguir a " + usuario.getNombre());
        } else {
            System.out.println("No estás siguiendo a " + usuario.getNombre());
        }
    }

    @Override
    public String toString() {
        return nombre;
    }

    public Comparable<Object> getFecha() {
        return getFecha();
    }
}
