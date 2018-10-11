package java.hackathon.filipe.hackathon.chatbot.model;

public class Conteudo {

    private String titulo;
    private String subtitulo;
    private int imagemTema;

    public Conteudo(String titulo, String subtitulo, int imagemTema) {
        this.titulo = titulo;
        this.subtitulo = subtitulo;
        this.imagemTema = imagemTema;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public int getImagemTema() {
        return imagemTema;
    }

    public void setImagemTema(int imagemTema) {
        this.imagemTema = imagemTema;
    }

    @Override
    public String toString() {
        return "Conteudo{" +
                "titulo='" + titulo + '\'' +
                ", subtitulo='" + subtitulo + '\'' +
                ", imagemTema=" + imagemTema +
                '}';
    }
}
