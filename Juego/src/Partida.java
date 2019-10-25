import java.util.ArrayList;

public class Partida {


    private Crupier crupier;
    private ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
    private int turno = 1;

    public void setCrupier(Crupier crupier) {
        this.crupier = crupier;
    }

    public ArrayList<Jugador> getJugadores() {
        return this.jugadores;
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = this.jugadores;
    }

    public int getTurno() {
        return this.turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public Crupier getCrupier() {
        return this.crupier;
    }

    public void Repartir1erTurno() {

        for (int i = 0; i < this.jugadores.size(); i++) {
            this.jugadores.get(i).setearValorCartas(0, 2);
        }
        this.crupier.setearValorCartasCrupier(0, 1);
    }

    public void resetear() {
        for (int i = 0; i < this.jugadores.size(); i++) {
            this.jugadores.get(i).getCartas().clear();
            this.jugadores.get(i).setApuesta(0);
            this.jugadores.get(i).setApuesta2(0);
            this.jugadores.get(i).setApuestaInicial(0);
            this.jugadores.get(i).setSeparacion(false);
            this.jugadores.get(i).setApuesSeg(0);
        }
        this.crupier.getCartas().clear();
    }


    public Partida(Crupier crupier, ArrayList<Jugador> jugadores) {
        this.crupier = crupier;
        this.jugadores = jugadores;
    }

    public void sinDinero() {
        boolean sinMon = false;

        for (int i = 0; i < this.jugadores.size(); i++) {
            if (sinMon) {
                i--;
            }
            sinMon = false;
            if (this.jugadores.get(i).sinDinero()) {
                System.out.println(" " + this.jugadores.get(i).getNombreJugador() + " no tiene mas dinero, por lo tanto se retira de la partida ");
                this.jugadores.remove(i);
                sinMon = true;

            }
        }
    }


    public void GanadoresYPerdedores() {


        for (int i = 0; i < this.jugadores.size(); i++) {

            int dineroPagadoxCrupier = this.jugadores.get(i).getApuesta();

            if (this.jugadores.get(i).sumatoria() > this.crupier.sumatoriaCrupier() && this.jugadores.get(i).NosePasa() && !this.jugadores.get(i).blackjack()) {
                this.jugadores.get(i).dineroTotal = this.jugadores.get(i).getDinero() + dineroPagadoxCrupier + this.jugadores.get(i).getApuesta();
                System.out.println("El crupier pierde y le paga " + this.jugadores.get(i).getApuesta() + " a " + this.jugadores.get(i).getNombreJugador());
            }
            if (this.jugadores.get(i).sumatoria() > this.crupier.sumatoriaCrupier() && this.jugadores.get(i).blackjack()) {
                this.jugadores.get(i).setDinero(this.jugadores.get(i).getDinero() + (dineroPagadoxCrupier * 2) + this.jugadores.get(i).getApuesta());
                System.out.println("El jugador logra un BLACKJACK! El crupier pierde y le paga  " + this.jugadores.get(i).getApuesta() * 2 + " a " + this.jugadores.get(i).getNombreJugador());

            }
            if (this.jugadores.get(i).sumatoria() < this.crupier.sumatoriaCrupier() && this.crupier.CrupierNoSePasa()) {

                System.out.println("El crupier gana y se lleva  " + this.jugadores.get(i).getApuesta() + " de parte de " + this.jugadores.get(i).getNombreJugador());
            }

            if (this.crupier.CrupiersePasa() && this.jugadores.get(i).NosePasa()) {
                this.jugadores.get(i).dineroTotal = this.jugadores.get(i).getDinero() + dineroPagadoxCrupier + this.jugadores.get(i).getApuesta();
                System.out.println("El crupier pierde y le paga " + this.jugadores.get(i).getApuesta() + " a " + this.jugadores.get(i).getNombreJugador());
            }

            if (this.jugadores.get(i).sumatoria() == this.crupier.sumatoriaCrupier() && this.jugadores.get(i).NosePasa()) {
                this.jugadores.get(i).dineroTotal = this.jugadores.get(i).getDinero() + dineroPagadoxCrupier;
                System.out.println("El jugador " + this.jugadores.get(i).getNombreJugador() + " recupera la suma de " + this.jugadores.get(i).getApuesta());
            }


            if (this.jugadores.get(i).sePasa()) {
                System.out.println("El crupier gana y se lleva  " + this.jugadores.get(i).getApuesta() + " de parte de " + this.jugadores.get(i).getNombreJugador());
            }
        }
    }
}






