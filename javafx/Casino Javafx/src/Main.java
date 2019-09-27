import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class Main extends Application {
    Stage window;
    Scene menuP,menuG,menuB;
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
        root.getChildren().addAll(tituloMenuPrincipal(),btnesMenuPrincipal());
        Scene menuPrincipal  = new Scene(root,650,550);

        window.setScene(menuPrincipal);
    }

    private void menuGenerala(){

        BorderPane root = new BorderPane();

        root.setTop(textMenuGenerala());
        root.setLeft(btnBackMenuGenerala());
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
    private void jugarGenerala(int cantJugadores){
        StackPane root = new StackPane();
        root.getChildren().addAll(tituloMenuGenerala());
        Scene jugarGenerala = new Scene(root,650,550);

        window.setScene(jugarGenerala);
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
    private VBox btnesMenuPrincipal(){
        Button btnGenerala = new Button("GENERALA");
        btnGenerala.setOnAction(event -> menuGenerala());
        btnGenerala.setMinSize(150,50);
        Button btnBlackjack = new Button("BLACKJACK");
        btnBlackjack.setOnAction(event -> menuBlackjack());
        btnBlackjack.setMinSize(150,50);
        Button btnExit = new Button("EXIT");
        btnExit.setOnAction(event -> closeApp());
        btnExit.setMinSize(150,50);

        VBox panelV = new VBox(btnGenerala,btnBlackjack,btnExit);
        panelV.setAlignment(Pos.CENTER);
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
        btnDos.setOnAction(event -> jugarGenerala(2));
        btnDos.setPrefSize(150,50);
        Button btnTres = new Button("3");
        btnTres.setOnAction(event -> jugarGenerala(3));
        btnTres.setPrefSize(150,50);
        Button btnCuatro = new Button("4");
        btnCuatro.setOnAction(event -> jugarGenerala(4));
        btnCuatro.setPrefSize(150,50);

        VBox panelV = new VBox(btnDos,btnTres,btnCuatro);
        panelV.setSpacing(20);
        panelV.setPadding(new Insets(25,0,0,0));
        panelV.setAlignment(Pos.TOP_CENTER);

        return panelV;
    }
    private HBox btnBackMenuGenerala(){
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

}
