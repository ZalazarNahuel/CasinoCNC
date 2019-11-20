import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class Pares extends Punto{
    private int cantidad1;
    private int cantidad2;
    private int puntoSacar;
    public Pares(int cant1,int cant2,int punto){
        super();
        cantidad1 = cant1;
        cantidad2 = cant2;
        puntoSacar = punto;
    }
    public int getCantidad1(){
        return cantidad1;
    }
    public int getCantidad2(){
        return cantidad2;
    }
    public int getPuntoSacar(){
        return puntoSacar;
    }
    public int mostrarPunto(int[] dados){
        super.mostrarPunto(dados);
        ArrayList<Integer> cantidades = calcularCantidadPares(dados);
        if(cantidades.size()==2){
            if((cantidades.get(0)==getCantidad1() && cantidades.get(1)==getCantidad2()) || (cantidades.get(1)==getCantidad1() && cantidades.get(0)==getCantidad2())){
                System.out.println(cantidades.get(0)+" "+cantidades.get(1));
                return this.getPuntoSacar();
            }
            else{
                return 0;
            }
        }
        else if(cantidades.size()==1){
            if( cantidades.get(0)==getCantidad1()){
                return this.getPuntoSacar();
            }
            else{
                return 0;
            }
        }
        else{
            return 0;
        }
        
    }
    public void sacarPunto(int[] dados){
        super.sacarPunto(dados);
        this.setPunto(mostrarPunto(dados));
        
    }
    public ArrayList<Integer> calcularCantidadPares(int[] dados){
        HashMap<Integer,Integer> juegos = new HashMap<Integer, Integer>();
        for(int i = 0; i < dados.length ; i++){
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
}