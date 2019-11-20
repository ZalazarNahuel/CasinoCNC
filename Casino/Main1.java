public class Main1{
    public static void main(String[] args) {
        Poker n = new Poker();
        int[] dados = new int[5];
        dados[0] = 6;
        dados[1] =4;
        dados[2] =5;
        dados[3] =5;
        dados[4] = 1;
        n.sacarPunto(dados);   
        System.out.println(n.getPunto());
    }
}