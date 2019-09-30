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

import java.util.HashSet;


public class Main extends Application {
    Stage window;
    Generala generala;

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
        

        root.getChildren().addAll(backgroundMenuPrincipal(),btnesMenuPrincipal());
        Scene menuPrincipal  = new Scene(root,650,550);

        window.setScene(menuPrincipal);
    }

    private void menuGenerala(){

        BorderPane root = new BorderPane();

        root.setTop(textMenuGenerala());
        root.setLeft(btnBackMenuPrincipal());
        root.setCenter(btnesMenuGenerala());
        root.setRight(espacioVacio());
        
        Scene menuGenerala = new Scene(root,650,550);

        window.setScene(menuGenerala);
    }
    private void menuBlackjack(){
        //TITULO
        Label titulo = new Label();
        titulo.setAlignment(Pos.TOP_LEFT);
        titulo.setText("BLACKJACK");
        titulo.setTextFill(Color.web("#E60026"));
        titulo.setFont(new Font("arial",30));

        VBox panelT = new VBox(titulo);
        panelT.setAlignment(Pos.TOP_CENTER);

        StackPane root = new StackPane();
        root.getChildren().addAll(panelT);
        Scene menuBlackjack = new Scene(root,650,550);
        window.setScene(menuBlackjack);
    }
    private void ingresarJugadores(int cantJugadores){
        generala = new Generala();
        generala.setJugadores(cantJugadores);
        BorderPane root = new BorderPane();

        root.setTop(textIngresarJugadores());
        root.setLeft(btnBackMenuGenerala());
        root.setCenter(JugadoresInput(cantJugadores));
        root.setRight(btnJugar());
        
        Scene ingresarJugadores = new Scene(root,650,550);

        window.setScene(ingresarJugadores);
    }
    private void checkNombreJugadores(){

    }
    private void jugarGenerala(){

    }
    private ImageView backgroundMenuPrincipal(){
        Image imagenMenuPrincipal = new Image("file:imagenes/menuPrincipal.jpg");
        ImageView bg = new ImageView(imagenMenuPrincipal);

        return bg;
    }
    private VBox tituloMenuPrincipal(){
        Label titulo = new Label();
        titulo.setText("CASINO CNC");
        titulo.setTextFill(Color.web("#E60026"));
        titulo.setFont(new Font("arial",30));

        VBox panelV = new VBox(titulo);
        panelV.setAlignment(Pos.TOP_CENTER);
        return panelV;
    }
    private Background background(Image imagen){
        BackgroundImage backgroundImage = new BackgroundImage( imagen, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);
        return background;
    }
    private VBox btnesMenuPrincipal(){
        Button btnGenerala = new Button();
        Image imagenGenerala = new Image("file:imagenes/Boton-Generala.png");
        btnGenerala.setBackground(background(imagenGenerala));
        btnGenerala.setOnAction(event -> menuGenerala());
        btnGenerala.setPrefSize(imagenGenerala.getWidth(),imagenGenerala.getHeight());

        Button btnBlackjack = new Button();
        Image imagenBlackjack = new Image("file:imagenes/Boton-Blackjack.png");
        btnBlackjack.setBackground(background(imagenBlackjack));
        btnBlackjack.setOnAction(event -> menuBlackjack());
        btnBlackjack.setPrefSize(imagenBlackjack.getWidth(),imagenBlackjack.getHeight());

        Button btnExit = new Button("");
        Image imagenExit = new Image("file:imagenes/Boton-Exit.png");
        btnExit.setBackground(background(imagenExit));
        btnExit.setOnAction(event -> closeApp());
        btnExit.setPrefSize(imagenExit.getWidth(),imagenExit.getHeight());

        VBox panelV = new VBox(btnGenerala,btnBlackjack,btnExit);
        panelV.setAlignment(Pos.CENTER);
        panelV.setPadding(new Insets(150,0,0,0));
        panelV.setSpacing(20);

        return panelV;
    }
    private VBox tituloMenuGenerala(){

        Label titulo = new Label("GENERALA");
        titulo.setTextFill(Color.web("#E60026"));
        titulo.setFont(new Font("arial",30));

        VBox panelV = new VBox(titulo);
        panelV.setAlignment(Pos.TOP_CENTER);

        return panelV;
    }
    private VBox textMenuGenerala(){

        Label texto = new Label("¿Cuantas personas van a jugar?");
        texto.setFont(new Font("arial",14));

        VBox panelV = new VBox(tituloMenuGenerala(),texto);
        panelV.setSpacing(100);
        panelV.setAlignment(Pos.TOP_CENTER);
        
        return panelV;
    }
    private VBox btnesMenuGenerala(){
        Button btnDos = new Button("2");
        btnDos.setOnAction(event -> ingresarJugadores(2));
        btnDos.setPrefSize(150,50);
        Button btnTres = new Button("3");
        btnTres.setOnAction(event -> ingresarJugadores(3));
        btnTres.setPrefSize(150,50);
        Button btnCuatro = new Button("4");
        btnCuatro.setOnAction(event -> ingresarJugadores(4));
        btnCuatro.setPrefSize(150,50);

        VBox panelV = new VBox(btnDos,btnTres,btnCuatro);
        panelV.setSpacing(20);
        panelV.setPadding(new Insets(25,0,0,0));
        panelV.setAlignment(Pos.TOP_CENTER);

        return panelV;
    }
    private HBox btnBackMenuPrincipal(){
        Button btnBack = new Button("<- BACK");
        btnBack.setOnAction(event -> menuPrincial());
        btnBack.setPrefSize(100,50);

        HBox panelH = new HBox(btnBack);
        panelH.setPadding(new Insets(0,0,10,10));
        panelH.setAlignment(Pos.BOTTOM_LEFT);
        return panelH;
    }
    private Label espacioVacio(){
        Label texto = new Label();
        texto.setPrefSize(100,50);

        return texto;
    }
    private VBox textIngresarJugadores(){

        Label texto = new Label("Ingrese los nombres de los jugadores");
        texto.setFont(new Font("arial",14));

        VBox panelV = new VBox(tituloMenuGenerala(),texto);
        panelV.setSpacing(100);
        panelV.setAlignment(Pos.TOP_CENTER);
        
        return panelV;
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
        
        for(int i = 0; i < cantJugadores; i++){
            Label jugador = new Label("Jugador "+(i+1)+":");
            GridPane.setConstraints(jugador, 0, i);
            TextField nombre = new TextField();
            nombre.setPromptText("nombre");
            GridPane.setConstraints(nombre, 1, i);
            grid.getChildren().addAll(jugador,nombre);
            generala.getJugador(i).setNombre(nombre.getText());
        }
        return grid;

    }
    private HBox btnBackMenuGenerala(){
        Button btnBack = new Button("<- BACK");
        btnBack.setOnAction(event -> menuGenerala());
        btnBack.setPrefSize(100,50);

        HBox panelH = new HBox(btnBack);
        panelH.setPadding(new Insets(0,0,10,10));
        panelH.setAlignment(Pos.BOTTOM_LEFT);
        return panelH;
    }

}
