import java.util.ArrayList;
import java.util.Scanner;

public class Blackjack {

    ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
    Crupier Nahuel = new Crupier();
    Partida partida = new Partida(Nahuel, jugadores);


    public void Blackjack(){

        int contador=0;
        while(jugadores.size()!=0 || contador==0) {
        JugadoresParticipantes(jugadores);
        System.out.println("AHORA VA A COMENZAR LA RONDA DE APUESTAS");
        System.out.println();
        realizarApuestasIniciales(jugadores);
        System.out.println("EL CRUPIER DA CIERRE A LA RONDA DE APUESTAS, PARA COMENZAR LA REPARTIJA DE CARTAS ");
        System.out.println();
        partida.Repartir1erTurno();
        Nahuel.mostrarCartasCrupier();
        ApuestaSegura(Nahuel, jugadores);
        AccionJugador(jugadores);
        while(Nahuel.sumatoriaCrupier() < 17){
            Nahuel.PedirCartaCrupier();
        }
        Nahuel.mostrarCartasCrupier();
        partida.GanadoresYPerdedores();
        partida.sinDinero();
        contador++;
        partida.resetear();
    }
}

    public static void JugadoresParticipantes(ArrayList<Jugador> jugadores) {
        int elegido = 0;
        int opcion = 0;
        String nombre;

        while (opcion != 2) {
            elegido=0;
            System.out.println("Se agrega algun jugador al juego?   1-SI         2-NO");
            Scanner myObj = new Scanner(System.in);  // Create a Scanner object
            opcion = myObj.nextInt();
            if (opcion == 1) {

                while (elegido != 2) {

                    elegido=0;
                    System.out.println("Cual es el nombre de este jugador?");
                    Scanner myObj2 = new Scanner(System.in);  // Create a Scanner object
                    nombre = myObj2.nextLine();

                    for (int i = 0; i < jugadores.size(); i++) {
                        if (jugadores.get(i).getNombreJugador().equals(nombre)) {
                            elegido++;
                        }
                    }

                    if (elegido == 0) {
                        elegido = 2;
                        jugadores.add(new Jugador(nombre, 100));
                        System.out.println( nombre + " fue agregado con exito al juego y posee 100 fichas");
                    }


                    else if (elegido == 1) {
                        System.out.println("Elija otro nombre, el elegido ya esta en uso");
                    }
                }
            }
        }


        int removidos = 0;
        int opcion2 = 0;

        while(opcion2 != 2){
            System.out.println("Algun jugador desea retirarse del juego?   1-SI         2-NO");
            Scanner myObj = new Scanner(System.in);  // Create a Scanner object
            opcion2 = Integer.parseInt(myObj.nextLine());
            if (opcion2 == 1) {
                String nombre2;
                while (removidos == 0) {
                    System.out.println("Cual es el nombre del jugador que desea retirarse?");
                    Scanner myObj3 = new Scanner(System.in);  // Create a Scanner object
                    nombre2 = myObj3.nextLine();

                    for (int i = 0; i < jugadores.size(); i++) {
                        if (nombre2.equals(jugadores.get(i).getNombreJugador())) {
                            jugadores.remove(i);
                            removidos++;

                        }
                    }

                    if (removidos == 0){
                        System.out.println("No hay ningun jugador con ese nombre");
                    }

                    else {
                        System.out.println(nombre2 + " fue eliminado de la partida");

                    }
                }
            }
        }
        System.out.println("Ningun jugador se retira de la partida");

    }

    public static void realizarApuestasIniciales(ArrayList<Jugador> jugadores) {


        for (int i = 0; i < jugadores.size(); i++) {
            int bucle=0;
            while (bucle == 0) {
                System.out.println(jugadores.get(i).getNombreJugador() + " tiene " + jugadores.get(i).getDinero() + " fichas, que apuesta inicial quiere realizar?");
                Scanner myObj = new Scanner(System.in);  // Create a Scanner object
                jugadores.get(i).setApuestaInicial(Integer.parseInt(myObj.nextLine()));

                if (jugadores.get(i).getApuestaInicial() == 0 || jugadores.get(i).getApuestaInicial() < 0 || jugadores.get(i).getApuestaInicial() > jugadores.get(i).getDinero()) {
                    System.out.println("Esa apuesta inicial no es realizable");
                }

                else {
                    System.out.println( jugadores.get(i).getNombreJugador() + " apuesta la cantidad de " + jugadores.get(i).getApuestaInicial() + " fichas");
                    jugadores.get(i).setApuestaInicial(jugadores.get(i).getApuestaInicial());
                    jugadores.get(i).setApuesta(jugadores.get(i).getApuestaInicial());
                    jugadores.get(i).setDinero(jugadores.get(i).getDinero() - jugadores.get(i).getApuestaInicial());
                    bucle++;
                }
            }
        }
    }




    public static void ApuestaSegura(Crupier Nahuel, ArrayList<Jugador> jugadores) {
        int bucle = 0;

        if (Nahuel.chequearCrupierBlackjack()) {

            for (int i = 0; i < jugadores.size(); i++) {

                bucle=0;
                while (bucle == 0) {
                    System.out.println(jugadores.get(i).getNombreJugador() + ", ¿Cuanto quiere apostar al blackjack del crupier? Lo maximo que puede apostar es la mitad de su apuesta inicial");
                    Scanner myObj = new Scanner(System.in);  // Create a Scanner object
                    jugadores.get(i).setApuesSeg(Integer.parseInt(myObj.nextLine()));
                    if (jugadores.get(i).getApuesSeg() <= jugadores.get(i).getApuestaInicial() / 2 || jugadores.get(i).getApuesSeg() > 0) {
                        jugadores.get(i).setDinero(jugadores.get(i).getDinero() - jugadores.get(i).getApuesSeg());
                        bucle++;
                    }
                    else {
                        System.out.println("Esta apuesta no es realizable");
                    }

                    if (jugadores.get(i).getSeparacion()) {
                        System.out.println(jugadores.get(i).getNombreJugador() + ", ¿Cuanto quiere apostar al blackjack del crupier en su segunda mano? Lo maximo que puede apostar es la mitad de su apuesta inicial");
                        if (jugadores.get(i).getApuesSeg() < jugadores.get(i).getApuestaInicial() / 2 || jugadores.get(i).getApuesSeg() != 0 || jugadores.get(i).getApuesSeg() > 0) {
                            jugadores.get(i).setDinero(jugadores.get(i).getDinero() - jugadores.get(i).getApuesSeg());
                            bucle++;

                        }
                        else{
                            System.out.println("Esta apuesta no es realizable");

                        }
                    }
                }
            }
        }
    }

    public static void AccionJugador(ArrayList<Jugador> jugadores) {

        for (int i = 0; i < jugadores.size(); i++) {

            System.out.println("Es el turno de " + jugadores.get(i).getNombreJugador());
            System.out.println();

            int bucle=0;
            int contador=0;


            while (bucle == 0) {

                jugadores.get(i).mostrarCartas();
                System.out.println();

                System.out.println("1- Plantarse ");
                if (contador==0) {
                    System.out.println("2- Doblar Apuesta ");
                }
                if(contador!=0) {
                    System.out.println("2- Pedir Carta ");
                }
                else{

                    System.out.println("3- Pedir Carta ");
                }

                int accion=0;
                Scanner myObj = new Scanner(System.in);  // Create a Scanner object
                accion = Integer.parseInt(myObj.nextLine());

                if (accion == 1) {
                    jugadores.get(i).Plantarse();
                    bucle++;
                }

                if (accion == 2 && (jugadores.get(i).getApuesta()) * 2 < jugadores.get(i).getDinero() && contador==0) {
                    jugadores.get(i).duplicarApuesta();
                    System.out.println("Ahora mismo su apuesta es de " + jugadores.get(i).getApuesta());
                }

                if (accion == 2 &&  contador!=0) {
                    if(jugadores.get(i).sumatoria()>21){
                        System.out.println("El jugador se excedio de 21");
                        bucle++;
                    }
                    else{
                        jugadores.get(i).PedirCarta();
                        contador++;
                        if(jugadores.get(i).sumatoria()>21) {
                            System.out.println("El jugador se excedio de 21");
                            bucle++;
                        }
                    }
                }


                if (accion == 3) {
                    if (jugadores.get(i).sumatoria() > 21) {
                        System.out.println("El jugador se excedio de 21");
                        bucle++;
                    } else {
                        jugadores.get(i).PedirCarta();
                        contador++;
                        if (jugadores.get(i).sumatoria() > 21) {
                            System.out.println("El jugador se excedio de 21");
                            bucle++;
                        }
                    }
                }

            }
        }
    }

}
