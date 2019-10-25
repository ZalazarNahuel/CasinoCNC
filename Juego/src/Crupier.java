import java.util.ArrayList;

public class Crupier {

    private ArrayList<Carta> cartasCrupier = new ArrayList<Carta>();

    public Crupier() {

    }

    public ArrayList<Carta> getCartas() {
        return cartasCrupier;
    }

    public void setCartas(ArrayList<Carta> cartasCrupierz) {
        this.cartasCrupier = cartasCrupierz;
    }

    public void setearPrimeraCarta() {

        for (int i = 0; i < 1; i++) {
            this.cartasCrupier.add(new Carta());
            this.cartasCrupier.get(i).ValorRand();
            this.cartasCrupier.get(i).NombreDeValor();
        }
    }


    public void setearValorCartasCrupier(int rango1, int rango2) {
        for (int i = rango1; i < rango2; i++) {
            this.cartasCrupier.add(new Carta());
            this.cartasCrupier.get(i).ValorRand();
            this.cartasCrupier.get(i).NombreDeValor();
        }
    }

    public void PedirCartaCrupier() {
            int contador=0;
            int num = cartasCrupier.size()+1;
            for (int i = cartasCrupier.size(); i < num; i++) {
                if( this.sumatoriaCrupier()<=21 || contador==0){
                    setearValorCartasCrupier(this.cartasCrupier.size(), num);
                    System.out.println( "El crupier levanta un " + cartasCrupier.get(i).getNombre());
                    contador++;
                }
            }
        }

    public int sumatoriaCrupier() {
        int sumatoria = 0;
        ArrayList<Integer> nums = new ArrayList<Integer>();


        for (int i = 0; i < cartasCrupier.size(); i++) {
            if(this.cartasCrupier.get(i).getValor() != 1 && this.cartasCrupier.get(i).getValor() != 11){
                sumatoria = sumatoria + (this.cartasCrupier.get(i).getValor());
            }

            else{
                nums.add(i);
            }
        }
        if(nums.size() != 0) {
            for (int i = 0; i < nums.size(); i++) {
                if (sumatoria + 11 <= 21 ) {
                    this.cartasCrupier.get(nums.get(i)).setValor(11);
                }
                else {
                    this.cartasCrupier.get(nums.get(i)).setValor(1);

                }
                sumatoria = sumatoria+ this.cartasCrupier.get(nums.get(i)).getValor();
            }
        }

        return sumatoria;

    }

    public boolean CrupiersePasa(){
        boolean CrupiersePasa=false;
        if(sumatoriaCrupier()>21){
            CrupiersePasa=true;
        }
        return CrupiersePasa;
    }

    public boolean CrupierNoSePasa(){
        boolean CrupierNosePasa=false;
        if(sumatoriaCrupier()<=21){
            CrupierNosePasa=true;
        }
        return CrupierNosePasa;
    }

    public void mostrarCartasCrupier() {
        System.out.print("Las cartas del crupier son ");
        for (int i = 0; i < this.cartasCrupier.size(); i++) {
            System.out.print(this.cartasCrupier.get(i).getNombre() + " ");
        }
        System.out.println();

    }

    public boolean chequearCrupierBlackjack() {
        boolean seguro = false;
        for (int i = 0; i < this.cartasCrupier.size(); i++) {
            if (this.cartasCrupier.get(i).getNombre() == "A") {
                seguro = true;


            }
        }
        return seguro;
    }




}

