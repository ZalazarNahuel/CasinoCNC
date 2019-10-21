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

    public Generala(){
        jugadores = new ArrayList<Jugador>();
        ganador = new Jugador(null);
        dados = new int[5];
        indices = new ArrayList<>();
        intentos = 0;
        jugadorActual = 0;
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

    public boolean nombreRepetido(){
        for(int i = 0;i < getJugadores().size();i++){
            for(int j = 0;j <getJugadores().size();j++){
                if(i!=j && !getJugador(j).getNombre().isEmpty()){
                    if(getJugador(i).getNombre().equalsIgnoreCase(getJugador(j).getNombre())){
                        return true;
                        
                    } 
                }
            }
        }
        return false;
    }
    public boolean nombreVacio(){
        for(int i = 0;i<getJugadores().size();i++){
            if(getJugador(i).getNombre().isEmpty()) return true;
        }
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
        getJugador(jugador).setPuntaje(juego,realizarJuego(juego));
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
    //Sacar las jugadas
    public int realizarJuego(NombreJuego nombre){
        int punto = 0;
        switch(nombre){
            case UNO:
                punto = this.sacarPunto(1);
                break;
            case DOS:
                punto = this.sacarPunto(2);
                break;
            case TRES:
                punto = this.sacarPunto(3);
                break;
            case CUATRO:
                punto = this.sacarPunto(4);
                break;
            case CINCO:
                punto = this.sacarPunto(5);
                break;
            case SEIS:
                punto = this.sacarPunto(6);
                break;
            case ESCALERA:
                int[] dadosOrdenados = getDados();
                if(this.escalera(dadosOrdenados,dadosOrdenados.length)) punto = 20;
                break;
            case FULL:
                if(this.full(getDados(),getDados().length)) punto = 30;
                break;
            case POKER:
                if(this.poker(getDados(),getDados().length)) punto = 40;
                break;
            case GENERALA:
                if(this.generala(getDados(),getDados().length)) punto = 50;
                break;
        }
        return punto;
    }  
    public int sacarPunto(int numero){
        int punto = 0;
        for(int i = 0; i < 5;i++){
            if(getDado(i) == numero){
                punto = punto + numero;
            }
        }
        return punto;
    }

    public int[] bubbleSort(int[] dadosOrdenados, int length){
        int n = length;
        int temp = 0;
        for(int i=0; i < n; i++){
            for(int j=1; j < (n-i); j++){
                if(dadosOrdenados[j-1] > dadosOrdenados[j]){
                    temp = dadosOrdenados[j-1];
                    dadosOrdenados[j-1] = dadosOrdenados[j];
                    dadosOrdenados[j] = temp;
                }
            }
        }
        return dadosOrdenados;
    }
    public boolean escalera(int[] dados,int length){
        dados = this.bubbleSort(dados,length);

        for(int i = 0;i<(length-1);i++){
            if(dados[i]+1 != dados[i+1]){
                return false;
            }
        }
        return true;
    }
    public ArrayList<Integer> encontrarJuego(int[] dados,int length){
        HashMap<Integer,Integer> juegos = new HashMap<Integer, Integer>();
        for(int i = 0; i < length ; i++){
            boolean noEncontrado = true;
            for(Map.Entry<Integer,Integer> juego : juegos.entrySet() ){
                if(juego.getKey() == dados[i]){
                    juegos.put(dados[i],juegos.get(dados[i])+1);
                    noEncontrado = false;
                }
            }
            if(noEncontrado) juegos.put(dados[i],1);
        }
        ArrayList<Integer> cantidades = new ArrayList<Integer>();
        for(Map.Entry<Integer,Integer> juego : juegos.entrySet()){
            cantidades.add(juego.getValue());
        }
        return cantidades;
    }
    public boolean full(int[] dados,int length){
        ArrayList<Integer> juegos = encontrarJuego(dados,length);
        if(juegos.size()!=2) return false;
        else {
            if( (juegos.get(0)==3 && juegos.get(1)==2) || (juegos.get(1)==3 && juegos.get(0)==2) ) return true;
            else return false;
        }
    }
    public boolean poker(int[] dados,int length){
        ArrayList<Integer> juegos = encontrarJuego(dados,length);
        if(juegos.size()!=2) return false;
        else {
            if( (juegos.get(0)==4 && juegos.get(1)==1) || (juegos.get(1)==4 && juegos.get(0)==1) ) return true;
            else return false;
        }
    }
    public boolean generala(int[] dados,int length){
        ArrayList<Integer> juegos = encontrarJuego(dados,length);
        if(juegos.size()!=1) return false;
        else {
            if( juegos.get(0)== 5 ) return true;
            else return false;
        }
    } 
}