import java.util.ArrayList;
import java.util.List;

public class User {
    private String nombre;
    private List<User> siguiendo;
    private List<Post> posts;
    private List<User> comentarios;

    public User(String nombre) {
        this.nombre = nombre;
        this.siguiendo = new ArrayList<>();
        this.posts = new ArrayList<>();
    }

    public User() {

    }

    public void addPost(Post post) {
        posts.add(post);
        System.out.println("Post añadido por " + nombre + ": " + post);
    }

    public String getNombre() {
        return nombre;
    }

    public List<User> getFollowing() {
        return siguiendo;
    }
    public List<User> getComments(){
        return comentarios;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void followUser(User usuario) {
        if (!siguiendo.contains(usuario)) {
            siguiendo.add(usuario);
            System.out.println(nombre + " ahora sigue a " + usuario.getNombre());
        } else {
            System.out.println("Ya sigues a " + usuario.getNombre());
        }
    }

    public void unfollowUser(User usuario) {
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
