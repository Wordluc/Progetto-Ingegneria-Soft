package oggetti;

public abstract class Pawn {
    public String nome;
    private int id;
    private String URL;

    public Pawn(String nome, int id, String URL){
        this.nome = nome;
        this.id = id;
        this.URL=URL;
    }

    public int getId() {
        return id;
    }
    public String getURL(){
        return URL;
    }
    public void setURL(String URL){
        this.URL = URL;
    }
}
