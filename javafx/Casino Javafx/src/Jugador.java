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
        this.getPuntaje().add(new Punto(NombreJuego.UNO));
        this.getPuntaje().add(new Punto(NombreJuego.DOS));
        this.getPuntaje().add(new Punto(NombreJuego.TRES));
        this.getPuntaje().add(new Punto(NombreJuego.CUATRO));
        this.getPuntaje().add(new Punto(NombreJuego.CINCO));
        this.getPuntaje().add(new Punto(NombreJuego.SEIS));
        this.getPuntaje().add(new Punto(NombreJuego.ESCALERA));
        this.getPuntaje().add(new Punto(NombreJuego.FULL));
        this.getPuntaje().add(new Punto(NombreJuego.POKER));
        this.getPuntaje().add(new Punto(NombreJuego.GENERALA));
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
    public void setPuntaje(NombreJuego juego, int punto){
        for(int i = 0; i <getPuntaje().size();i++){
            if(getPuntaje().get(i).getNombre() == juego){
                getPuntaje().get(i).setPunto(punto);
            }
        }
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