public class PostImage extends Post {
    private String titulo;
    private int ancho;
    private int alto;

    public PostImage(String titulo, int ancho, int alto, User usuario1) {
        super(usuario1);
        this.titulo = titulo;
        this.ancho = ancho;
        this.alto = alto;
    }

    @Override
    public String toString() {
        return "Post de Imagen de " + usuario.getNombre() + ": \"" + titulo + "\" con dimensiones " + ancho + "x" + alto + " [" + getDate() + "]";
    }
}



