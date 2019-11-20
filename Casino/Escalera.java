public class Escalera extends Punto{
    public Escalera(){
        setNombre(NombreJuego.ESCALERA);
    }
    public int mostrarPunto(int[] dados){
        super.mostrarPunto(dados);
        if(escalera(dados)){
            return 20;
        }
        else{
            return 0;
        }

    }
    public void sacarPunto(int[] dados){
        super.sacarPunto(dados);
        setPunto(mostrarPunto(dados));
    }
    public int[] bubbleSort(int[] dadosOrdenados){
        int n = dadosOrdenados.length;
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
    public boolean escalera(int[] dados){
        dados = this.bubbleSort(dados);

        for(int i = 0;i<(dados.length-1);i++){
            if(dados[i]+1 != dados[i+1]){
                return false;
            }
        }
        return true;
    }
}