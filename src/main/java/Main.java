import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        RedSocial redSocial = new RedSocial();
        Scanner scanner = new Scanner(System.in);

        int opcion;
        do {                                                                                                            //Meterle do while en vez de while
            System.out.println("\n=== MENÚ DE RedSocialCampusDual ===");                                                //Crear menu principal de registrarse, iniciar sesión y cerrar sesión
            System.out.println("1. Registrarse");
            System.out.println("2. Iniciar Sesión");
            System.out.println("3. Cerrar Sesión");
            System.out.println("0. Salir");
            opcion = Utils.integer("Selecciona una opción: ");

            switch (opcion) {
                case 1: // Registrarse
                    String nombreRegistro = Utils.string("Ingrese el nombre de usuario: ");
                    redSocial.userRegistration(nombreRegistro.trim());   // .trim() para que a la hora de poner el nombre no falle si se cuela un espacio
                    break;

                case 2: // Iniciar Sesión
                    if (redSocial.getActiveUser() == null) {
                        String nombreLogin = Utils.string("Ingrese su nombre de usuario: ");
                        redSocial.logIn(nombreLogin.trim());  // .trim() (Jose luis)
                        if (redSocial.getActiveUser() != null) {
                            menuPrincipal(redSocial, scanner);
                        }
                    } else {
                        System.out.println("Ya hay una sesión activa. Cierra sesión primero.");
                    }
                    break;

                case 3: // Cerrar Sesión
                    redSocial.logOut();
                    break;

                case 0: // Salir
                    System.out.println("Gracias por usar la red social. ¡Chao! Chao!");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);

        scanner.close();
    }

    private static void menuPrincipal(RedSocial redSocial, Scanner scanner) {
        int opcion;
        do {
            System.out.println("\n=== MENÚ de USUARIO ===");
            System.out.println("1. Publicar Post");
            System.out.println("2. Comentar en Post");
            System.out.println("3. Seguir Usuario");
            System.out.println("4. Dejar de Seguir Usuario");
            System.out.println("5. Ver Muro");
            System.out.println("6. Mostrar Usuarios");
            System.out.println("0. Cerrar Sesión y Volver al Menú de Autenticación");
            opcion = Utils.integer("Selecciona una opción: ");

            switch (opcion) {
                case 1: // Publicar Post
                    System.out.println("¿Qué tipo de post desea publicar?");
                    System.out.println("1. Texto");
                    System.out.println("2. Imagen");
                    System.out.println("3. Video");
                    int tipoPost = Utils.integer("Selecciona una opción: ");

                    if (tipoPost == 1) {
                        String contenido = Utils.string("Ingrese el contenido del post: ");
                        redSocial.getActiveUser().addPost(new PostText(contenido, redSocial.getActiveUser()));
                    } else if (tipoPost == 2) {
                        String titulo = Utils.string("Ingrese el título de la imagen:");
                        int ancho = Utils.integer("Ingrese el ancho de la imagen:");
                        int alto = Utils.integer("Ingrese el alto de la imagen:");
                        redSocial.getActiveUser().addPost(new PostImage(titulo, ancho, alto, redSocial.getActiveUser()));
                    } else if (tipoPost == 3) {
                        String tituloVideo = Utils.string("Ingrese el título del video: ");
                        String calidad = Utils.string("Ingrese la calidad del video (Ejemplo: 720p, 1080p, 4K): ");
                        String duracion = Utils.string("Ingrese la duración del video (en segundos): ");
                        redSocial.getActiveUser().addPost(new PostVideo(tituloVideo, calidad, duracion, redSocial.getActiveUser()));
                    } else {
                        System.out.println("Tipo de post inválido.");
                    }
                    break;

                case 2: // Comentar en Post
                    String autor = Utils.string("Nombre de usuario del autor del post: ");
                    User usuarioAutor = redSocial.searchUser(autor.trim());
                    if (usuarioAutor != null && !usuarioAutor.getPosts().isEmpty()) {
                        System.out.println("Selecciona el número del post:");
                        for (int i = 0; i < usuarioAutor.getPosts().size(); i++) {
                            System.out.println(i + ". " + usuarioAutor.getPosts().get(i));
                        }
                        int postIndex = Utils.integer();

                        if (postIndex >= 0 && postIndex < usuarioAutor.getPosts().size()) {
                            Post post = usuarioAutor.getPosts().get(postIndex);
                            System.out.println("Ingrese su comentario:");
                            String textoComentario = scanner.nextLine();
                            post.addComment(new Comentario(textoComentario, redSocial.getActiveUser()));
                            System.out.println("Comentario añadido.");
                        } else {
                            System.out.println("Índice de post no válido.");
                        }
                    } else {
                        System.out.println("Usuario no encontrado o sin posts.");
                    }
                    break;

                case 3: // Seguir Usuario
                    String nombreSeguir = Utils.string("Ingrese el nombre del usuario a seguir: ");
                    User usuarioSeguir = redSocial.searchUser(nombreSeguir.trim());
                    if (usuarioSeguir != null) {
                        redSocial.getActiveUser().followUser(usuarioSeguir);
                    } else {
                        System.out.println("Usuario no encontrado.");
                    }
                    break;

                case 4: // Dejar de Seguir Usuario
                    String nombreDejarSeguir = Utils.string("Ingrese el nombre del usuario a dejar de seguir: ");
                    User usuarioDejarSeguir = redSocial.searchUser(nombreDejarSeguir.trim());
                    if (usuarioDejarSeguir != null) {
                        redSocial.getActiveUser().unfollowUser(usuarioDejarSeguir);
                    } else {
                        System.out.println("Usuario no encontrado.");
                    }
                    break;

                case 5: // Ver Muro
                    redSocial.showWall(redSocial.getActiveUser());
                    break;

                case 6: // Mostrar Usuarios
                    redSocial.listUsers();
                    break;

                case 0: // Salir
                    redSocial.logOut();
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }
}


