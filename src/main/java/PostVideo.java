public class PostVideo extends Post {
    private String titulo;
    private String calidad;
    private int duracion;

    public PostVideo(String titulo, String calidad, String duracion, User usuario1) {
        super(usuario1);
        this.titulo = titulo;
        this.calidad = calidad;
        this.duracion = Integer.parseInt(duracion);
    }

    @Override
    public String toString() {
        return "Post de Video de " + usuario.getNombre() + ": \"" + titulo + "\" [" + calidad + ", " + duracion + " segundos] [" + getDate() + "]";
    }
}




