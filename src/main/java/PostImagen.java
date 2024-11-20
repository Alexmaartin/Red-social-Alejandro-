public class PostImagen extends Post {
    private String titulo;
    private int ancho;
    private int alto;

    public PostImagen(String titulo, int ancho, int alto, Usuario usuario1) {
        super(usuario1);
        this.titulo = titulo;
        this.ancho = ancho;
        this.alto = alto;
    }

    @Override
    public String toString() {
        return "Post de Imagen de " + usuario.getNombre() + ": \"" + titulo + "\" con dimensiones " + ancho + "x" + alto + " [" + getFecha() + "]";
    }
}



