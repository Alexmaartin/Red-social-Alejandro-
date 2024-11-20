public class PostTexto extends Post {
    private String contenido;

    public PostTexto(String contenido, Usuario usuario1) {
        super(usuario1);
        this.contenido = contenido;
    }

    @Override
    public String toString() {
        return "Post de Texto: \""+ usuario.getNombre()  + contenido + "\" [" + fecha + "]";
    }
}
