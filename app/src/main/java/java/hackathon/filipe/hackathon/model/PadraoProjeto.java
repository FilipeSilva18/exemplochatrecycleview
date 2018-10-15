package java.hackathon.filipe.hackathon.model;

public class PadraoProjeto {
    private String nome;
    private int foto;

    public PadraoProjeto(String nome, int foto) {
        this.nome = nome;
        this.foto = foto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return "PadraoProjeto{" +
                "nome='" + nome + '\'' +
                ", foto=" + foto +
                '}';
    }
}
