
public class Punto {
    private int punto;
     NombreJuego nombre;

    public Punto (){
        punto = -1; 
    }

    public NombreJuego getNombre(){
        return nombre;
    }

    public int getPunto(){
        return this.punto;
    }
    public void setPunto(int puntox){
        this.punto = puntox;
    }
    public void sacarPunto(int[] dados){

    }
    public int mostrarPunto(int[] dados){
        return 0;
    }
    public void setNombre(NombreJuego nombrex){
        nombre = nombrex;
    }

    
}