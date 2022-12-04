package Entita;

public abstract class Pawn {
    private String URL;
    public Pawn() {

    }
    public void setPawn( String URL){
        this.URL=URL;
    }
    public void draw(){

    }
    public String getURL(){
        return URL;
    }
    public void setURL(String URL){
        this.URL = URL;
    }
}
