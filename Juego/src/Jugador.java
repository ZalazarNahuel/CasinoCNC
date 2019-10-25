import java.util.ArrayList;
import java.util.Scanner;

public class Jugador {

    private ArrayList<Carta> cartasMano2 = new ArrayList<Carta>();
    private String nombreJugador;
    private ArrayList<Carta> cartas = new ArrayList<Carta>();
    protected int dineroTotal;
    private int apuesta;
    private int apuesta2;
    private int apuestaInicial;
    private boolean separacion = false;
    private int apuesSeg;

    public Jugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    public Jugador(String nombreJugador, int dineroTotal) {
        this.nombreJugador = nombreJugador;
        this.dineroTotal = dineroTotal;
    }

    public int getApuesSeg() {
        return apuesSeg;
    }

    public void setApuesSeg(int apuesSeg) {
        this.apuesSeg = apuesSeg;
    }

    public int getApuesta2() {
        return apuesta2;
    }

    public void setApuesta2(int apuesta2) {
        this.apuesta2 = apuesta2;
    }

    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    public int getApuestaInicial() {
        return apuestaInicial;
    }

    public void setApuestaInicial(int apuestaInicial) {
        this.apuestaInicial = apuestaInicial;
    }

    public ArrayList<Carta> getCartas() {
        return cartas;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public void setCartas(ArrayList<Carta> cartas) {
        this.cartas = cartas;
    }

    public int getDinero() {
        return dineroTotal;
    }

    public void setDinero(int dinero) {
        this.dineroTotal = dinero;
    }

    public int getApuesta() {
        return apuesta;
    }

    public void setApuesta(int apuesta) {
        this.apuesta = apuesta;
    }

    public boolean getSeparacion() {
        return separacion;
    }

    public void setSeparacion(boolean separacion) {
        this.separacion = separacion;
    }

    public void setearValorCartas(int rango1, int rango2) {
        for (int i = rango1; i < rango2; i++) {
            this.cartas.add(new Carta());
            this.cartas.get(i).ValorRand();
            this.cartas.get(i).NombreDeValor();
            }
        }



    public void mostrarCartas() {
        System.out.println("Las cartas de " + this.nombreJugador + " son ");
        for (int i = 0; i < this.cartas.size(); i++) {
            System.out.print(this.cartas.get(i).getNombre() + " ");
        }
    }


    public int sumatoria() {
        int sumatoria = 0;
        ArrayList<Integer> nums = new ArrayList<Integer>();


        for (int i = 0; i < cartas.size(); i++) {
            if(this.cartas.get(i).getValor() != 1 && this.cartas.get(i).getValor() != 11){
                sumatoria = sumatoria + (this.cartas.get(i).getValor());
            }

            else{
                nums.add(i);
            }
        }
        if(nums.size() != 0) {
            for (int i = 0; i < nums.size(); i++) {
                if (sumatoria + 11 <= 21) {
                    this.cartas.get(nums.get(i)).setValor(11);
                }
                else {
                    this.cartas.get(nums.get(i)).setValor(1);
                }
                sumatoria = sumatoria+ this.cartas.get(nums.get(i)).getValor();
            }
        }

            return sumatoria;

    }

    public boolean sinDinero(){
        boolean sinPlata=false;
        if(this.dineroTotal == 0){
            sinPlata=true;
        }
        return sinPlata;
    }


        public boolean sePasa(){
        boolean sePasa=false;
        if(sumatoria()>21){
                sePasa=true;
            }
        return sePasa;
        }

    public boolean NosePasa(){
        boolean NosePasa=false;
        if(sumatoria() <= 21){
            NosePasa=true;
        }
        return NosePasa;
    }

    public boolean blackjack(){
        boolean blackjack1=false;
        if(sumatoria() == 21 && this.cartas.size()==2){
            blackjack1=true;
        }
        return blackjack1;
    }



    public void PedirCarta() {

        int contador=0;
        int num = cartas.size()+1;
        for (int i = cartas.size(); i < num; i++) {
        if( this.sumatoria()<=21 || contador==0){
                setearValorCartas(this.cartas.size(), num);
                System.out.println(this.nombreJugador + " levanta un " + cartas.get(i).getNombre());
                contador++;
            }
        }
    }


    public void Plantarse() {

        System.out.println(this.nombreJugador + " se planta");

    }


    public void duplicarApuesta() {
        this.apuesta = this.apuesta * 2;
        System.out.println(this.nombreJugador + " duplica su apuesta a " + apuesta);

    }

}


