public class PostText extends Post {
    private String contenido;

    public PostText(String contenido, User usuario1) {
        super(usuario1);
        this.contenido = contenido;
    }

    @Override
    public String toString() {
        return "Post de Texto: \""+ usuario.getNombre()  + contenido + "\" [" + fecha + "]";
    }
}
