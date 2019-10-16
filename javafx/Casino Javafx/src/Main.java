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
import javafx.stage.Stage;
import java.util.ArrayList;

public class Main extends Application {
    Stage window;
    Scene escena;
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
        escena  = new Scene(root,650,550);
        root.setBackground(background( new Image("file:imagenes/menuPrincipal.jpg")));
        window.setScene(escena);
    }

    private void menuGenerala(){

        BorderPane root = new BorderPane();

        root.setLeft(btnBackMenuPrincipal());
        root.setCenter(btnesMenuGenerala());
        root.setTop(txtMenuGenerala());
       
        root.setBackground(background(new Image("file:imagenes/menuGenerala.jpg")));
        escena.setRoot(root);
    }
    private void menuBlackjack(){
        StackPane root = new StackPane();
        root.setBackground(background(new Image("file:imagenes/menuBlackjack.jpg")));
        escena.setRoot(root);
    }
    private void ingresarJugadores(int cantJugadores){
        generala = new Generala();
        generala.llenarIndices();
        generala.setJugadores(cantJugadores);
        BorderPane root = new BorderPane();

        root.setLeft(btnBackMenuGenerala());
        root.setCenter(JugadoresInput(cantJugadores));
        root.setRight(btnJugar());

        root.setBackground(background(new Image("file:imagenes/menuGenerala.jpg")));
        
        escena.setRoot(root);
    }
    
    private void jugarGenerala(){
        BorderPane root = new BorderPane();

        root.setCenter(dados());
        root.setBottom(opcionesBottom());

        root.setBackground(background(new Image("file:imagenes/menuGenerala.jpg")));

        escena.setRoot(root);

    }
    private void elegirJugada(){
        BorderPane root = new BorderPane();
        root.setCenter(juegosNoHechos());

        root.setBackground(background(new Image("file:imagenes/menuGenerala.jpg")));

        escena.setRoot(root);
    }
    private void mostrarJugadas(){
        BorderPane root = new BorderPane();
        root.setCenter(juegosHechos());

        root.setBackground(background(new Image("file:imagenes/menuGenerala.jpg")));

        escena.setRoot(root);
    }
    private void generalaFin(){
        BorderPane root = new BorderPane();
        root.setCenter(ganador());

        root.setBackground(background(new Image("file:imagenes/menuGenerala.jpg")));

        escena.setRoot(root);
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
    private void tirarDados(){
        generala.tirarDados();
        generala.vaciarIndices();
        generala.sumarTurno();
        jugarGenerala();
    }
    private void realizarJuego(NombreJuego juego){
        generala.realizarJuegoJugador(generala.getJugadorActual(), juego);
        generala.cambiarJugadorActual();
        if(generala.checkFin()){
            generalaFin();
        }
        else{
            jugarGenerala();
        }
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
        HBox panelH = new HBox();
        panelH.setPadding(new Insets(0,0,0,0));
        panelH.setSpacing(5);
        panelH.setAlignment(Pos.CENTER);
        for(int i = 0;i<5;i++){
            Button numero = new Button(Integer.toString(generala.getDado(i)));
            int j = i;
            if(generala.getIntentos()!=0){
                numero.setOnAction(event -> generala.addIndices(j));
            }
            /*if(generala.getIndices().contains(j){
                //poner imagen
            }
            else{
                //poner imagen 
            }*/
            panelH.getChildren().add(numero);
        }
       
        
        return panelH;
    }
    
    private HBox tirar(){
        HBox panelH = new HBox();
        panelH.setAlignment(Pos.BOTTOM_RIGHT);
        panelH.setSpacing(10);
        panelH.setPadding(new Insets(0,0,10,0));
        Button tirar = new Button("Tirar");
        tirar.setOnAction(event -> tirarDados());
        Button elegir = new Button("elegir jugada");
        elegir.setOnAction(event -> elegirJugada() );
        Button jugadas = new Button("Jugadas");
        jugadas.setOnAction(e -> mostrarJugadas());
        Button salir = new Button("Salir");
        salir.setOnAction(e -> menuGenerala());

        panelH.getChildren().addAll(jugadas,salir);
        if(generala.getIntentos()==0){
            panelH.getChildren().addAll(tirar);
        }
        if(generala.getIntentos()<3 && generala.getIntentos()>0){
            panelH.getChildren().addAll(elegir,tirar);
        }
        if(generala.getIntentos()==3){
            panelH.getChildren().addAll(elegir);
        }
        return panelH;
    }
    private VBox nombreJugador(){
        VBox panelv = new VBox();
        panelv.setAlignment(Pos.BOTTOM_LEFT);
        panelv.setSpacing(10);
        panelv.setPadding(new Insets(0,0,10,10));
        Label txt = new Label("Jugador: "+generala.getNombreJugador(generala.getJugadorActual()));
        Label txt2 = new Label("Intento: "+ (generala.getIntentos()+1));
        if(generala.getIntentos() > 2){
            txt2.setText("Intento: 3");
        }
        panelv.getChildren().addAll(txt,txt2);
        return panelv;
    }
    private HBox opcionesBottom(){
        HBox panelh = new HBox();
        panelh.getChildren().addAll(nombreJugador(),tirar());

        return panelh;
    }

    private GridPane juegosNoHechos(){
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(30,0,0,0));
        grid.setVgap(15);
        grid.setHgap(15);
        grid.setAlignment(Pos.TOP_CENTER);
        
        for(int i = 0; i < generala.juegosNoHechosJugador(generala.getJugadorActual()).size();i++){
            Button btn = new Button();
            Label txt = new Label(Integer.toString(generala.realizarJuego(generala.juegosNoHechosJugador(generala.getJugadorActual()).get(i))));
            btn = new Button(generala.juegosNoHechosJugador(generala.getJugadorActual()).get(i).getNombre());
            int j = i;
            btn.setOnAction(e -> realizarJuego(generala.juegosNoHechosJugador(generala.getJugadorActual()).get(j)));

            GridPane.setConstraints(btn, 0, i);
            GridPane.setConstraints(txt, 1, i);
            grid.getChildren().addAll(btn,txt);
        }

        return grid;
    }
    private VBox juegosHechos(){
        VBox panelv = new VBox();
        panelv.setAlignment(Pos.CENTER);
        for(int i = 0;i < generala.getJugador(generala.getJugadorActual()).getPuntaje().size();i++){
            Label txt = new Label();
            if(generala.getJugador(generala.getJugadorActual()).getPunto(i).getPunto()>-1){
                txt.setText(generala.getJugador(generala.getJugadorActual()).getPunto(i).getNombre() + ": "+generala.getJugador(generala.getJugadorActual()).getPunto(i).getPunto());
            }
            else{
                txt.setText(generala.getJugador(generala.getJugadorActual()).getPunto(i).getNombre() + ": ");
            }

            panelv.getChildren().add(txt);
        }
        Button back = new Button("Back");
        back.setOnAction(e -> jugarGenerala());
        panelv.getChildren().add(back);
        return panelv;
    }
    private VBox ganador(){
        VBox panelv  = new VBox();
        panelv.setAlignment(Pos.CENTER);
        Label txt = new Label("El ganador es: "+ generala.getGanador().getNombre());
        panelv.getChildren().add(txt);
        return panelv;
    }

}
