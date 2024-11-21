import java.util.ArrayList;
import java.util.List;

public class RedSocial {
    private List<User> usuarios;
    private User activeUser; // Active user que dijo pablo.

    public RedSocial() {
        this.usuarios = new ArrayList<>();
        this.activeUser = null;
    }

    public void userRegistration(String nombre) {
        if (searchUser(nombre) == null) {                                                                               //si el nombre de usuario que meto no estaba registrado.
            User nuevoUsuario = new User(nombre);                                                                 //lo registro en usuario.
            usuarios.add(nuevoUsuario);                                                                                 //lo añado a la lista de usuarios.
            System.out.println("Usuario " + nombre + " registrado con éxito.");
        } else {                                                                                                        //en caso de que el nombre de usuario coincida con uno ya existente.
            System.out.println("El nombre de usuario ya está en uso.");
        }
    }

    public User logIn(String nombre) {
        User usuario = searchUser(nombre);                                                                           //si encuentro el usuario dentro de la lista de usuarios registrados
        if (usuario != null) {
            activeUser = usuario;
            System.out.println("Inicio de sesión exitoso. Bienvenido, " + usuario.getNombre());                         //inicio sesión
        } else {                                                                                                        // en caso de no encontrar ese nombre en la lista, pedir que se registre
            System.out.println("Usuario no encontrado. Por favor, regístrese primero.");
        }
        return activeUser;
    }

    public void logOut() {
        if (activeUser != null) {                                                                                       //Si el usuario esta activo, cerrar sesión
            System.out.println("Cierre de sesión exitoso. Hasta luego, " + activeUser.getNombre());
            activeUser = null;
        } else {
            System.out.println("No hay un usuario activo para cerrar sesión.");
        }
    }

    public User getActiveUser() {
        return activeUser;
    }

    public User searchUser(String nombre) {
        for (User usuario : usuarios) {
            if (usuario.getNombre().equalsIgnoreCase(nombre)) {                                                         //Para que reconozca como igual "Alejandro" que "alejandro"
                return usuario;
            }
        }
        return null;
    }

    public void listUsers() {
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
        } else {
            System.out.println("Usuarios registrados:");
            for (User usuario : usuarios) {
                System.out.println(usuario.getNombre());
            }
        }
    }

    public void showWall(User usuario) {
        if (usuario.getFollowing().isEmpty()) {                                                                         //Si no sigues a nadie u muro está vacio
            System.out.println("El usuario no sigue a nadie. Su muro está vacío.");
            return;
        }

        List<Post> muro = new ArrayList<>();

        for (User seguido : usuario.getFollowing()) {                                                                //Aadir todos los post de los usuarios que sigues
            muro.addAll(seguido.getPosts());
        }

        muro.sort((a, b) -> b.getDate().compareTo(a.getDate()));                                                      //ordenados de mas reciente a mas antiguo

        System.out.println("Muro de " + usuario.getNombre() + ":");
        if (muro.isEmpty()) {
            System.out.println("No hay posts en el muro.");
        } else {
            for (int i = 0; i < Math.min(10, muro.size()); i++) {                                                       // Condición para que i no exceda el menor entre diez y el
                Post post = muro.get(i);                                                                                // tamaño del muro, así si el muro es más pequeño que 10 igualmente
                System.out.println((i + 1) + ". " + post);                                                              // lo recorre entero

                List<Comentario> comentarios = post.getComment();
                if (!comentarios.isEmpty()) {
                    System.out.println("  Comentarios:");
                    for (Comentario comentario : comentarios) {
                        System.out.println("    - " + comentario);
                    }
                } else {
                    System.out.println("  No hay comentarios en este post.");
                }
            }
        }
    }
}






