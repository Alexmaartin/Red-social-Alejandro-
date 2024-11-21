public class PostVideo extends Post {
    private String titulo;
    private String calidad;
    private int duracion;

    public PostVideo(String titulo, String calidad, int duracion, User usuario1) {
        super(usuario1);
        this.titulo = titulo;
        this.calidad = calidad;
        this.duracion = duracion;
    }

    @Override
    public String toString() {
        return "Post de Video de " + usuario.getNombre() + ": \"" + titulo + "\" [" + calidad + ", " + duracion + " segundos] [" + getDate() + "]";
    }
}




