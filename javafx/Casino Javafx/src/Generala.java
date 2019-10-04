import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Generala {
    private ArrayList<Jugador> jugadores;
    private Jugador ganador;
    private int[] dados;

    public Generala(){
        jugadores = new ArrayList<Jugador>();
        ganador = new Jugador(null);
        dados = new int[5];
    }

    public void addJugador(Jugador j){
        jugadores.add(j);
    }
    public void setJugadores(int cantJugadores){
        for(int i = 0; i<cantJugadores; i++){
            jugadores.add(new Jugador(null));
        }
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
                if(i!=j){
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
    public void tirarDados(HashSet<Integer> idDados){
        for (Integer id : idDados ) {
            this.setDado(id, this.rand());
        }
    }

    public int rand(){
        double num = (Math.random()*4+1);
        return (int)num;
    }

    public int sacarPunto(int numero){
        int punto = 0;
        for(int i = 0; i < 5;i++){
            if(this.getDado(i)==numero){
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

    public int realizarJuegoJugador(int jugador, int juego){
        int punto = 0;
        switch(this.getJugador(jugador).getPunto(juego).getNombre()){
            case "1":
                punto = this.sacarPunto(1);
                break;
            case "2":
                punto = this.sacarPunto(2);
                break;
            case "3":
                punto = this.sacarPunto(3);
                break;
            case "4":
                punto = this.sacarPunto(4);
                break;
            case "5":
                punto = this.sacarPunto(5);
                break;
            case "escalera":
                int[] dadosOrdenados = this.getDados();
                if(this.escalera(dadosOrdenados,dadosOrdenados.length)) punto = 20;
                break;
            case "full":
                if(this.full(this.getDados(),this.getDados().length)) punto = 30;
                break;
            case "poker":
                if(this.poker(this.getDados(),this.getDados().length)) punto = 40;
                break;
            case "generala":
                if(this.generala(this.getDados(),this.getDados().length)) punto = 50;
                break;
        }
        //jugadores.get(jugador).anotarJuego(juego,punto);
        return punto;
    }
}