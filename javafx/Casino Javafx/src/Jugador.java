import java.util.ArrayList;
import java.util.HashSet;

public class Jugador {

    private String nombre;
    private ArrayList<Punto> puntaje;
    private int intento;

    public Jugador(String nombrex){
        nombre = nombrex;
        puntaje = new ArrayList<>();
        intento = 0;
        setPuntos();

    }

    private void setPuntos(){
        this.getPuntaje().add(new Punto("1",-1));
        this.getPuntaje().add(new Punto("2",-1));
        this.getPuntaje().add(new Punto("3",-1));
        this.getPuntaje().add(new Punto("4",-1));
        this.getPuntaje().add(new Punto("5",-1));
        this.getPuntaje().add(new Punto("escalera",-1));
        this.getPuntaje().add(new Punto("full",-1));
        this.getPuntaje().add(new Punto("poker",-1));
        this.getPuntaje().add(new Punto("generala",-1));
    }
    public void iniciarTurno(){
        intento = 0;
    }
    public String getNombre(){
        return this.nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public ArrayList<Punto> getPuntaje(){
        return this.puntaje;
    }
    public Punto getPunto(int i){
        return this.getPuntaje().get(i);
    }
}