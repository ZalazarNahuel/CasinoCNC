import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Generala {
    private ArrayList<Jugador> jugadores;
    private Jugador ganador;
    private int[] dados;
    private ArrayList<Integer> indices;
    private int intentos;
    private int jugadorActual;
    private boolean errorVacio;
    private boolean errorRepetido;

    public Generala(){
        jugadores = new ArrayList<Jugador>();
        ganador = new Jugador(null);
        dados = new int[5];
        indices = new ArrayList<>();
        intentos = 0;
        jugadorActual = 0;
        errorVacio = false;
        errorRepetido = false;
    }

    public void addJugador(Jugador j){
        jugadores.add(j);
    }

    public void setJugadores(int cantJugadores){
        for(int i = 0; i<cantJugadores; i++){
            jugadores.add(new Jugador(null));
        }
    }
    public int getIntentos(){
        return intentos;
    }
    public void setIntentos(int intentosx){
        intentos = intentosx;
    }
    
    public void sumarTurno(){
        setIntentos(getIntentos()+1);
    }
    public int getJugadorActual(){
        return jugadorActual;
    }
    public void setJugadorActual(int i){
        jugadorActual = i;
    }
    public void cambiarJugadorActual(){
        if(getJugadorActual() + 1 == getJugadores().size()){
            setJugadorActual(0);
        }
        else{
            setJugadorActual(getJugadorActual()+1);
        }
        llenarIndices();
        setIntentos(0);
        llenarIndices();
    }
    public ArrayList<Integer> getIndices(){
        return indices;
    }
    public void addIndices(int j){
        if(getIndices().contains(j)){
            getIndices().remove(getIndices().indexOf(j));
        }
        else{
            getIndices().add(j);
        }
    }
    public void llenarIndices() {
        for(int i = 0; i <5 ;i++) indices.add(i);
    }
    public void vaciarIndices(){
        indices.clear();
    }
    public int[] getDados() {
        return dados;
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }
    public String getNombreJugador(int i){
        return this.getJugador(i).getNombre();
    }
    public Jugador getGanador(){
        return ganador;
    }
    public void setDado(int i,int valor){
        this.dados[i] = valor;
    }
    public int getDado(int i){
        return this.dados[i];
    }
    public Jugador getJugador(int i){
        return jugadores.get(i);
    }
    public boolean getErrorRepetido(){
        return errorRepetido;
    }
    public boolean getErrorVacio(){
        return errorVacio;
    }
    public void setErrorVacio(boolean x){
        errorVacio = x;
    }
    public void setErrorRepetido(boolean x){
        errorRepetido = x;
    }

    public boolean nombreRepetido(){
        for(int i = 0;i < getJugadores().size();i++){
            for(int j = 0;j <getJugadores().size();j++){
                if(i!=j && !getJugador(j).getNombre().isEmpty()){
                    if(getJugador(i).getNombre().equalsIgnoreCase(getJugador(j).getNombre())){
                        setErrorRepetido(true);
                        return true;
                        
                    } 
                }
            }
        }
        setErrorRepetido(false);
        return false;
    }
    public boolean nombreVacio(){
        for(int i = 0;i<getJugadores().size();i++){
            if(getJugador(i).getNombre().isEmpty()){
                setErrorVacio(true);
                return true;
            }
        }
        setErrorVacio(false);
        return false;
    }
    public void tirarDados(){
        for (Integer id : this.getIndices() ) {
            this.setDado(id, this.rand());
        }
    }

    public int rand(){
        double num = (Math.random()*6+1);
        return (int)num;
    }

    public  ArrayList<NombreJuego> juegosNoHechosJugador(int jugador){
        ArrayList<NombreJuego> juegos = getJugador(jugador).juegosNoHechos();
        return juegos;
    }
    public void realizarJuegoJugador(int jugador,NombreJuego juego){
        getJugador(jugador).setPuntaje(juego,dados);
    }
    public int mostrarJuegoJugador(int jugador,NombreJuego juego){
        return getJugador(jugador).mostrarJuego(juego,dados);
    }
    public void sacarPuntajeTotalJugadores(){
        for(int i = 0;i<getJugadores().size();i++){
            getJugador(i).sacarPuntajeTotal();
        }
    }
    public void sacarGanador(){
        sacarPuntajeTotalJugadores();
        int punto = 0;
        for(int i = 0;i < getJugadores().size(); i++ ){
            if(punto < getJugador(i).getPuntajeTotal()){
                punto = getJugador(i).getPuntajeTotal();
                ganador = getJugador(i);
            }
        }
    }
    public boolean checkFin(){
        if(juegosNoHechosJugador(getJugadores().size()-1).size() == 0){ // si el ultimo jugador ya no tiene mas juegos para hacer, ya termino la partida
            sacarGanador();
            return true;
        }
        return false;
    }

}