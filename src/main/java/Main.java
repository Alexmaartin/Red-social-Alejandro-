import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        RedSocial redSocial = new RedSocial();
        Scanner scanner = new Scanner(System.in);

        int opcion;
        do {
            System.out.println("\n=== MENÚ DE RedSocialCampusDual ===");
            System.out.println("1. Registrarse");
            System.out.println("2. Iniciar Sesión");
            System.out.println("3. Cerrar Sesión");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1: // Registrarse
                    System.out.println("Ingrese el nombre de usuario:");
                    String nombreRegistro = scanner.nextLine();
                    redSocial.registrarUsuario(nombreRegistro.trim());   // .trim() para que a la hora de poner el nombre no falle si se cuela un espacio
                    break;

                case 2: // Iniciar Sesión
                    if (redSocial.getActiveUser() == null) {
                        System.out.println("Ingrese su nombre de usuario:");
                        String nombreLogin = scanner.nextLine();
                        redSocial.iniciarSesion(nombreLogin.trim());  // .trim() (Jose luis)
                        if (redSocial.getActiveUser() != null) {
                            menuPrincipal(redSocial, scanner);
                        }
                    } else {
                        System.out.println("Ya hay una sesión activa. Cierra sesión primero.");
                    }
                    break;

                case 3: // Cerrar Sesión
                    redSocial.cerrarSesion();
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
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Publicar Post");
            System.out.println("2. Comentar en Post");
            System.out.println("3. Seguir Usuario");
            System.out.println("4. Dejar de Seguir Usuario");
            System.out.println("5. Ver Muro");
            System.out.println("6. Mostrar Usuarios");
            System.out.println("0. Cerrar Sesión y Volver al Menú de Autenticación");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1: // Publicar Post
                    System.out.println("¿Qué tipo de post desea publicar?");
                    System.out.println("1. Texto");
                    System.out.println("2. Imagen");
                    System.out.println("3. Video");
                    int tipoPost = scanner.nextInt();
                    scanner.nextLine();

                    if (tipoPost == 1) {
                        System.out.println("Ingrese el contenido del post:");
                        String contenido = scanner.nextLine();
                        redSocial.getActiveUser().agregarPost(new PostTexto(contenido, redSocial.getActiveUser()));
                    } else if (tipoPost == 2) {
                        System.out.println("Ingrese el título de la imagen:");
                        String titulo = scanner.nextLine();
                        System.out.println("Ingrese el ancho de la imagen:");
                        int ancho = scanner.nextInt();
                        System.out.println("Ingrese el alto de la imagen:");
                        int alto = scanner.nextInt();
                        redSocial.getActiveUser().agregarPost(new PostImagen(titulo, ancho, alto, redSocial.getActiveUser()));
                    } else if (tipoPost == 3) {
                        System.out.println("Ingrese el título del video:");
                        String tituloVideo = scanner.nextLine();
                        System.out.println("Ingrese la calidad del video (Ejemplo: 720p, 1080p, 4K):");
                        String calidad = scanner.nextLine();
                        System.out.println("Ingrese la duración del video (en segundos):");
                        int duracion = scanner.nextInt();
                        redSocial.getActiveUser().agregarPost(new PostVideo(tituloVideo, calidad, duracion, redSocial.getActiveUser()));
                    } else {
                        System.out.println("Tipo de post inválido.");
                    }
                    break;

                case 2: // Comentar en Post
                    System.out.println("Nombre de usuario del autor del post:");
                    String autor = scanner.nextLine();
                    Usuario usuarioAutor = redSocial.buscarUsuario(autor);
                    if (usuarioAutor != null && !usuarioAutor.getPosts().isEmpty()) {
                        System.out.println("Selecciona el número del post:");
                        for (int i = 0; i < usuarioAutor.getPosts().size(); i++) {
                            System.out.println(i + ". " + usuarioAutor.getPosts().get(i));
                        }
                        int postIndex = scanner.nextInt();
                        scanner.nextLine();

                        if (postIndex >= 0 && postIndex < usuarioAutor.getPosts().size()) {
                            Post post = usuarioAutor.getPosts().get(postIndex);
                            System.out.println("Ingrese su comentario:");
                            String textoComentario = scanner.nextLine();
                            post.agregarComentario(new Comentario(textoComentario, redSocial.getActiveUser()));
                            System.out.println("Comentario añadido.");
                        } else {
                            System.out.println("Índice de post no válido.");
                        }
                    } else {
                        System.out.println("Usuario no encontrado o sin posts.");
                    }
                    break;

                case 3: // Seguir Usuario
                    System.out.println("Ingrese el nombre del usuario a seguir:");
                    String nombreSeguir = scanner.nextLine();
                    Usuario usuarioSeguir = redSocial.buscarUsuario(nombreSeguir);
                    if (usuarioSeguir != null) {
                        redSocial.getActiveUser().seguirUsuario(usuarioSeguir);
                    } else {
                        System.out.println("Usuario no encontrado.");
                    }
                    break;

                case 4: // Dejar de Seguir Usuario
                    System.out.println("Ingrese el nombre del usuario a dejar de seguir:");
                    String nombreDejarSeguir = scanner.nextLine();
                    Usuario usuarioDejarSeguir = redSocial.buscarUsuario(nombreDejarSeguir);
                    if (usuarioDejarSeguir != null) {
                        redSocial.getActiveUser().dejarDeSeguir(usuarioDejarSeguir);
                    } else {
                        System.out.println("Usuario no encontrado.");
                    }
                    break;

                case 5: // Ver Muro
                    redSocial.mostrarMuro(redSocial.getActiveUser());
                    break;

                case 6: // Mostrar Usuarios
                    redSocial.mostrarUsuarios();
                    break;

                case 0: // Salir
                    redSocial.cerrarSesion();
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }
}


