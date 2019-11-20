import java.util.ArrayList;

public class Jugador {

    private String nombre;
    private ArrayList<Punto> puntaje;
    private int puntajeTotal;
    

    public Jugador(String nombrex){
        nombre = nombrex;
        puntaje = new ArrayList<>();
        setPuntos();
        puntajeTotal = 0;

    }

    private void setPuntos(){
        this.getPuntaje().add(new Uno());
        this.getPuntaje().add(new Dos());
        this.getPuntaje().add(new Tres());
        this.getPuntaje().add(new Cuatro());
        this.getPuntaje().add(new Cinco());
        this.getPuntaje().add(new Seis());
        this.getPuntaje().add(new Escalera());
        this.getPuntaje().add(new Full());
        this.getPuntaje().add(new Poker());
        this.getPuntaje().add(new GeneralaPunto());
    }
    public void sacarPuntajeTotal(){
        for(int i = 0; i < getPuntaje().size();i++){
            puntajeTotal = puntajeTotal + getPuntaje().get(i).getPunto();
        }
    }
    public int getPuntajeTotal(){
        return puntajeTotal;
    }
    public String getNombre(){
        return this.nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void setPuntaje(NombreJuego juego,int[] dados){
        for(int i = 0; i <getPuntaje().size();i++){
            if(getPuntaje().get(i).getNombre() == juego){
                getPuntaje().get(i).sacarPunto(dados);
            }
        }
    }
    public int mostrarJuego(NombreJuego juego,int[] dados){
        for(int i = 0; i <getPuntaje().size();i++){
            if(getPuntaje().get(i).getNombre() == juego){
                return getPuntaje().get(i).mostrarPunto(dados);
            }
        }
        return 0;
    }

    public ArrayList<Punto> getPuntaje(){
        return this.puntaje;
    }
    public Punto getPunto(int i){
        return this.getPuntaje().get(i);
    }
    public ArrayList<NombreJuego> juegosNoHechos(){
        ArrayList<NombreJuego> juegos = new ArrayList<>();
        for(int i =0 ;i <getPuntaje().size();i++){
            if(getPuntaje().get(i).getPunto() == -1){
                juegos.add(getPuntaje().get(i).getNombre());
            }
        }
        return juegos;
    }
}