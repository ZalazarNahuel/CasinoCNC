
public class Punto {
    NombreJuego nombre;
    int punto;

    public Punto (NombreJuego nombrex){
        nombre = nombrex;
        punto = -1; 
    }

    public NombreJuego getNombre(){
        return this.nombre;
    }
    public int getPunto(){
        return this.punto;
    }
    public void setPunto(int puntox){
        this.punto = puntox;
    }

    
}