import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.naming.Binding;
import java.util.ArrayList;
import java.util.HashSet;


public class Main extends Application {
    Stage window;
    Generala generala;
    ArrayList<TextField> nombreJugadorGenerala;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.show();
        window.setTitle("CASINO CNC");
        menuPrincial();
    }
    private void closeApp(){
        window.close();
    }
    private void menuPrincial(){

        StackPane root = new StackPane();
        
        root.getChildren().addAll(btnesMenuPrincipal());
        Scene menuPrincipal  = new Scene(root,650,550);
        root.setBackground(background( new Image("file:imagenes/menuPrincipal.jpg")));
        window.setScene(menuPrincipal);
    }

    private void menuGenerala(){

        BorderPane root = new BorderPane();

        root.setLeft(btnBackMenuPrincipal());
        root.setCenter(btnesMenuGenerala());
        root.setTop(txtMenuGenerala());
       
        root.setBackground(background(new Image("file:imagenes/menuGenerala.jpg")));
        Scene menuGenerala = new Scene(root,650,550);

        window.setScene(menuGenerala);
    }
    private void menuBlackjack(){
        StackPane root = new StackPane();
        root.setBackground(background(new Image("file:imagenes/menuBlackjack.jpg")));
        Scene menuBlackjack = new Scene(root,650,550);
        window.setScene(menuBlackjack);
    }
    private void ingresarJugadores(int cantJugadores){
        generala = new Generala();
        generala.setJugadores(cantJugadores);
        BorderPane root = new BorderPane();

        root.setLeft(btnBackMenuGenerala());
        root.setCenter(JugadoresInput(cantJugadores));
        root.setRight(btnJugar());

        root.setBackground(background(new Image("file:imagenes/menuGenerala.jpg")));
        
        Scene ingresarJugadores = new Scene(root,650,550);

        window.setScene(ingresarJugadores);
    }
    
    private void jugarGenerala(){
        BorderPane root = new BorderPane();

        generala.checkTurno();
        root.setCenter(dados());


        root.setBackground(background(new Image("file:imagenes/menuGenerala.jpg")));

        Scene jugarGenerala = new Scene(root,650,550);

        window.setScene(jugarGenerala);

    }
    private void mostrarDados(){
        BorderPane root = new BorderPane();

        root.setBackground(background(new Image("file:imagenes/menuGenerala.jpg")));
        root.setCenter(dados());
        Scene jugarGenerala = new Scene(root,650,550);

        window.setScene(jugarGenerala);
    }
    //metodos
    private void checkNombreJugadores(){
        for(int i = 0;i < generala.getJugadores().size();i++){
            generala.getJugador(i).setNombre(nombreJugadorGenerala.get(i).getText());
        }//relleno los nombres con el input

        boolean hayError = false;
        if(generala.nombreVacio()){//checkeo que no haya un nombre vacio
            hayError = true;
            System.out.println("nombre vacio");
        }
        if(generala.nombreRepetido()){//checkeo que no haya un nombre repetido
            hayError = true;
            System.out.println("nombre repetido");
        }
        if(!hayError){
            jugarGenerala();
        }
    }
    private void tirarDados(HashSet<Integer> indice){
        generala.tirarDados(indice);
        generala.getIndices().clear();
        generala.sumarTurno();
        jugarGenerala();
    }
    //javafx
    private Background background(Image imagen){
        BackgroundImage backgroundImage = new BackgroundImage( imagen, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);
        return background;
    }
    private Button boton(String rutaImagen){
        Button boton = new Button();

        Image imagen = new Image(rutaImagen);
        boton.setBackground(background(imagen));
        boton.setPrefSize(imagen.getWidth(),imagen.getHeight());

        return boton;
    }
    private VBox btnesMenuPrincipal(){
        Button btnGenerala = boton("file:imagenes/Boton-Generala.png");
        btnGenerala.setOnAction(event -> menuGenerala());

        Button btnBlackjack = boton("file:imagenes/Boton-Blackjack.png");
        btnBlackjack.setOnAction(event -> menuBlackjack());

        Button btnExit = boton("file:imagenes/Boton-Exit.png");
        btnExit.setOnAction(event -> closeApp());

        VBox panelV = new VBox(btnGenerala,btnBlackjack,btnExit);
        panelV.setAlignment(Pos.CENTER);
        panelV.setPadding(new Insets(150,0,0,0));
        panelV.setSpacing(20);

        return panelV;
    }
    
    private VBox txtMenuGenerala(){
        VBox panelV = new VBox();  
        panelV.setPadding(new Insets(260,0,0,70));

        Image pregunta = new Image("file:imagenes/Texto-Pregunta.png");
        panelV.getChildren().add(new ImageView(pregunta));

        return panelV;
    }
    private VBox btnesMenuGenerala(){
        VBox panelV = new VBox();
        
        panelV.setSpacing(20);
        panelV.setPadding(new Insets(10,0,0,120));
        panelV.setAlignment(Pos.TOP_LEFT);

        for(int i = 2; i<5;i++){
            Button btn = boton("file:imagenes/Boton-"+Integer.toString(i)+".png");
            final int s = i;//lambda solo acepta parametros final
            btn.setOnAction(event -> ingresarJugadores(s));
            panelV.getChildren().add(btn);
        }

        return panelV;
    }
    private HBox btnBackMenuPrincipal(){
        Button btnBack = boton("file:imagenes/Boton-Back.png");
        btnBack.setOnAction(event -> menuPrincial());

        HBox panelH = new HBox(btnBack);
        panelH.setPadding(new Insets(0,0,10,10));
        panelH.setAlignment(Pos.BOTTOM_LEFT);
        return panelH;
    }
    
    private HBox btnJugar(){
        Button btnJugar = new Button("JUGAR");
        btnJugar.setOnAction(event -> checkNombreJugadores());
        btnJugar.setPrefSize(100,50);

        HBox panelH = new HBox(btnJugar);
        panelH.setPadding(new Insets(0,10,10,0));
        panelH.setAlignment(Pos.BOTTOM_RIGHT);
        return panelH;
    }
    private GridPane JugadoresInput(int cantJugadores){
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(30,0,0,0));
        grid.setVgap(15);
        grid.setHgap(15);
        grid.setAlignment(Pos.TOP_CENTER);
        
        nombreJugadorGenerala = new ArrayList<>();
        for(int i = 0; i < cantJugadores; i++){
            Label jugador = new Label("Jugador "+(i+1)+":");
            GridPane.setConstraints(jugador, 0, i);
            TextField nombre = new TextField();
            nombre.setPromptText("nombre");
            GridPane.setConstraints(nombre, 1, i);
            grid.getChildren().addAll(jugador,nombre);
            nombreJugadorGenerala.add(nombre);
        }
        return grid;

    }
    private HBox btnBackMenuGenerala(){
        Button btnBack = boton("file:imagenes/Boton-Back.png");
        btnBack.setOnAction(event -> menuGenerala());
        

        HBox panelH = new HBox(btnBack);
        panelH.setPadding(new Insets(0,0,10,10));
        panelH.setAlignment(Pos.BOTTOM_LEFT);
        return panelH;
    }
    private HBox dados(){
        HashSet<Integer> indices = new HashSet<>();//dados a tirar
        for(int i = 0;i<5;i++) indices.add(i);
        HBox panelH = new HBox();
        panelH.setPadding(new Insets(0,0,0,0));
        panelH.setSpacing(5);
        panelH.setAlignment(Pos.CENTER);
        for(int i = 0;i<5;i++){
            Button numero = new Button(Integer.toString(generala.getDado(i)));
           /* if(estadoBtnes.get(i)==true){
                numero.setText("seleccionado");
            }
            estadoBtnes.add(false);
            numero.setOnAction(event -> indices.add(j));//*/
            panelH.getChildren().add(numero);
        }
        Button tirar = new Button("Tirar");
        tirar.setOnAction(event -> tirarDados(indices));
        panelH.getChildren().add(tirar);
        
        return panelH;
    }
    ArrayList<Boolean> estadoBtnes = new ArrayList<>();
    private void btnNumero(int i){
        if(estadoBtnes.get(i)==false){
            generala.getIndices().add(i);
            estadoBtnes.set(i,true);
        }
        else {
            estadoBtnes.set(i,false);
            generala.getIndices().remove(generala.getIndices().indexOf(i));
        }
        jugarGenerala();
    }
    

}
