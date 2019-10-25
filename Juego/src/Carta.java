import javax.lang.model.element.VariableElement;
import java.util.HashMap;

public class Carta {

   private String nombre;
   private int valor;

    public Carta(int valor) {
        this.valor = valor;
    }

    public Carta() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getValor() {
        return valor;
    }

    public int ValorRand() {
        double rand = Math.random() * 100;
        if(rand<32){
            this.valor = 10;
        }
        else {
            this.valor = (int) Math.floor(Math.random()*(9-1+1)+1);
        }
         return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String NombreDeValor(){
        double rand = Math.random() * 100;
        if(this.valor ==1 || this.valor== 11){
            this.nombre = "A";
        }
        if(this.valor==2 || this.valor==3 || this.valor==4 || this.valor==5 || this.valor==6 || this.valor==7 || this.valor==8 || this.valor==9  ){
            this.nombre = Integer.toString(valor);

                     }


        if(this.valor==10){
            if(rand<33.333){
                this.nombre = "J";
            }
            else if(rand>33.333 && rand<66.666){
                this.nombre= "Q";
            }
            else if(rand>66.666){
                this.nombre="K";
            }
        }

        return this.nombre;
    }
}
