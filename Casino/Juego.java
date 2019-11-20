public abstract class Juego extends Punto{
    int numero;
    public Juego(int num){
        super();
        numero = num;
    }
    public int mostrarPunto(int[] dados){
        super.mostrarPunto(dados);
        int result = 0;
        for(int i = 0;i<dados.length;i++){
            if(dados[i]==getNumero()){
                result = result + numero;
            }
        }
        return result;
    }
    public void sacarPunto(int[] dados){
        super.sacarPunto(dados);
        setPunto(mostrarPunto(dados));
    }
    public int getNumero(){
        return numero;
    }
}