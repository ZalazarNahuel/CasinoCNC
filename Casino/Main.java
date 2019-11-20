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
import javafx.scene.text.Font;
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
        window.getIcons().add(new Image("file:imagenes/dado5blanco.png"));
        window.setResizable(false);
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
    
    private void crearGenerala(int cantJugadores){
        generala = new Generala();
        generala.llenarIndices();
        generala.setJugadores(cantJugadores);

        ingresarJugadores(generala.getJugadores().size());
    }
    private void ingresarJugadores(int cantJugadores){
        BorderPane root = new BorderPane();

        root.setRight(btnBackYJugar());
        root.setLeft(JugadoresInput(cantJugadores));
        root.setBottom(errores());

        root.setBackground(background(new Image("file:imagenes/menuGenerala.jpg")));
        
        escena.setRoot(root);
    }
    
    private void jugarGenerala(){
        BorderPane root = new BorderPane();

        root.setCenter(dados());
        root.setBottom(opcionesBottom());

        root.setBackground(background(new Image("file:imagenes/pantallaJuegoGenerala.png")));

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
        root.setLeft(btnBackJugar());

        root.setBackground(background(new Image("file:imagenes/menuGenerala.jpg")));

        escena.setRoot(root);
    }
    private void generalaFin(){
        BorderPane root = new BorderPane();
        root.setCenter(ganador());
        root.setLeft(salir());

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
            ingresarJugadores(generala.getJugadores().size());
        }
        if(generala.nombreRepetido()){//checkeo que no haya un nombre repetido
            hayError = true;
            ingresarJugadores(generala.getJugadores().size());
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
    private void updateDados(int j){
        generala.addIndices(j);
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
    private Label textImage(String rutaImagen){
        Label text = new Label();

        Image imagen = new Image(rutaImagen);
        text.setBackground(background(imagen));
        text.setPrefSize(imagen.getWidth(), imagen.getHeight());

        return text;
    }
    private VBox btnesMenuPrincipal(){
        Button btnGenerala = boton("file:imagenes/Boton-Generala.png");
        btnGenerala.setOnAction(event -> menuGenerala());

        Button btnExit = boton("file:imagenes/Boton-Exit.png");
        btnExit.setOnAction(event -> closeApp());

        VBox panelV = new VBox(btnGenerala,btnExit);
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
            btn.setOnAction(event -> crearGenerala(s));
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
    
    private GridPane JugadoresInput(int cantJugadores){
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(215,0,0,10));
        grid.setVgap(15);
        grid.setHgap(25);
        grid.setAlignment(Pos.CENTER_LEFT);
        
        nombreJugadorGenerala = new ArrayList<>();
        for(int i = 0; i < cantJugadores; i++){
            ImageView jugador = new ImageView(new Image("file:imagenes/jugador"+Integer.toString(i+1)+".png"));
            GridPane.setConstraints(jugador, 0, i);
            TextField nombre = new TextField();
            nombre.setPromptText("nombre");
            GridPane.setConstraints(nombre, 1, i);
            grid.getChildren().addAll(jugador,nombre);
            nombreJugadorGenerala.add(nombre);
        }
        return grid;

    }

    private VBox btnBackYJugar(){
        Button btnBack = boton("file:imagenes/Boton-Back.png");
        btnBack.setOnAction(event -> menuGenerala());
        
        Button btnJugar = boton("file:imagenes/Boton-Jugar.png");
        btnJugar.setOnAction(event -> checkNombreJugadores());
        
        VBox panelV = new VBox(btnBack,btnJugar);
        panelV.setPadding(new Insets(215,10,0,0));
        panelV.setSpacing(25);
        panelV.setAlignment(Pos.CENTER_RIGHT);
        return panelV;
    }
    private HBox errores(){
        HBox panelH = new HBox();
        panelH.setSpacing(15);
        panelH.setPadding(new Insets(0,0,5,15));
        if(generala.getErrorVacio()){
            panelH.getChildren().addAll(new ImageView(new Image("file:imagenes/Error-NomVacio.png")));
        }
        if(generala.getErrorRepetido()){
            panelH.getChildren().addAll(new ImageView(new Image("file:imagenes/Error-NomRepetido.png")));
        }
        return panelH;
    }

    private HBox dados(){
        HBox panelH = new HBox();
        panelH.setPadding(new Insets(235,0,0,0));
        panelH.setSpacing(5);
        panelH.setAlignment(Pos.CENTER);
        for(int i = 0;i<5;i++){
            Button numero;
            if(generala.getIntentos()==0){
                numero = boton("file:imagenes/dado"+(i+1)+"inicial.png");
            }
            else if(generala.getIndices().contains(i)){
                numero = boton("file:imagenes/dado"+Integer.toString(generala.getDado(i))+"rojo.png");
            }
            else{
                numero = boton("file:imagenes/dado"+Integer.toString(generala.getDado(i))+"blanco.png");
            }
            int j = i;
            if((generala.getIntentos()!=0) && (generala.getIntentos()!=3)){
                numero.setOnAction(event -> updateDados(j));
            }
            panelH.getChildren().add(numero);
        }
       
        
        return panelH;
    }
    private HBox tirar(){
        HBox panelH = new HBox();
        panelH.setAlignment(Pos.BOTTOM_RIGHT);
        panelH.setSpacing(10);
        panelH.setPadding(new Insets(0,0,10,0));
        Button tirar = boton("file:imagenes/Boton-Tirar.png");
        tirar.setOnAction(event -> tirarDados());
        Button elegir = boton("file:imagenes/Boton-ElegirJugada.png");
        elegir.setOnAction(event -> elegirJugada() );

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
    private VBox datosJugador(){
        VBox panelv = new VBox();
        panelv.setAlignment(Pos.BOTTOM_LEFT);
        panelv.setSpacing(10);
        panelv.setPadding(new Insets(0,0,10,10));
        Label jugador = textImage("file:imagenes/jugador.png");
        jugador.setText(generala.getNombreJugador(generala.getJugadorActual()));
        jugador.setFont(Font.font(35));
        jugador.setPadding(new Insets(0,20,0,0));
        jugador.setAlignment(Pos.CENTER_RIGHT);
        
        Label intentos = textImage("file:imagenes/intentos.png");
        intentos.setText(Integer.toString(generala.getIntentos()+1));
        intentos.setFont(Font.font(35));
        intentos.setPadding(new Insets(0,20,0,0));
        intentos.setAlignment(Pos.CENTER_RIGHT);
        if(generala.getIntentos() > 2){
            intentos.setText("3");
        }

        
        Button salir = boton("file:imagenes/Boton-Salir.png");
        salir.setOnAction(e -> menuGenerala());

        Button jugadas = boton("file:imagenes/Boton-ListaJugadas.png");
        jugadas.setOnAction(e -> mostrarJugadas());

        panelv.getChildren().addAll(jugador,intentos,jugadas,salir);
        return panelv;
    }
    private HBox opcionesBottom(){
        HBox panelh = new HBox();
        panelh.getChildren().addAll(datosJugador(),tirar());

        return panelh;
    }

    private GridPane juegosNoHechos(){
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(240,0,0,0));
        grid.setVgap(10);
        grid.setHgap(30);
        grid.setAlignment(Pos.TOP_CENTER);
        int k = 0;
        int i = 0;
        int j = 0;
        while(generala.juegosNoHechosJugador(generala.getJugadorActual()).size()!=k){
            if(k ==(generala.juegosNoHechosJugador(generala.getJugadorActual()).size() / 2) ) {
                i = 1;
                j = 0;
            }
            Button btn = boton("file:imagenes/cartel-"+generala.juegosNoHechosJugador(generala.getJugadorActual()).get(k).getNombre()+".png");
            btn.setText(Integer.toString(generala.mostrarJuegoJugador(generala.getJugadorActual(),generala.juegosNoHechosJugador(generala.getJugadorActual()).get(k))));
            btn.setAlignment(Pos.CENTER_RIGHT);
            btn.setPadding(new Insets(0,20,0,0));
            btn.setFont(Font.font(35));
            int m = k;
            btn.setOnAction(e -> realizarJuego(generala.juegosNoHechosJugador(generala.getJugadorActual()).get(m)));

            GridPane.setConstraints(btn, i, j);
            grid.getChildren().add(btn);
            k++;
            j++;
        }

        return grid;
    }
    private GridPane juegosHechos(){
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(240,100,0,0));
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(5);
        grid.setHgap(30);
        int k = 0;
        for(int i = 0; i <2;i++){
            for ( int j = 0; j<5;j++){
                Label txt = textImage("file:imagenes/cartel-"+generala.getJugador(generala.getJugadorActual()).getPunto(k).getNombre()+".png");
                if(generala.getJugador(generala.getJugadorActual()).getPunto(k).getPunto()>-1){
                    txt.setText(Integer.toString(generala.getJugador(generala.getJugadorActual()).getPunto(k).getPunto()));
                }
                txt.setFont(Font.font(35));
                txt.setPadding(new Insets(0,20,0,0));
                txt.setAlignment(Pos.CENTER_RIGHT);
                
                GridPane.setConstraints(txt, i, j);
                grid.getChildren().add(txt);
                k++;
 
            }
        }
        
        return grid;
    }
    private VBox btnBackJugar(){
        VBox panelv = new VBox();
        panelv.setAlignment(Pos.BOTTOM_LEFT);
        panelv.setPadding(new Insets(0,0,10,10));
        Button back =  boton("file:imagenes/Boton-Back.png");
        back.setOnAction(e -> jugarGenerala());
        panelv.getChildren().add(back);

        return panelv;
    }
    private VBox ganador(){
        VBox panelv  = new VBox();
        panelv.setAlignment(Pos.CENTER);
        panelv.setPadding(new Insets(50,50,0,0));
        Label txt =textImage("file:imagenes/cartel-ganador.png");
        txt.setText(generala.getGanador().getNombre());
        txt.setAlignment(Pos.CENTER_RIGHT);
        txt.setFont(Font.font(35));
        txt.setPadding(new Insets(0,20,0,0));

        panelv.getChildren().add(txt);
        return panelv;
    }
    private VBox salir(){
        VBox panelv = new VBox();
        panelv.setAlignment(Pos.BOTTOM_LEFT);
        panelv.setPadding(new Insets(0,0,10,10));
        Button salir = boton("file:imagenes/Boton-Salir.png");
        salir.setOnAction(e -> menuGenerala());
        panelv.getChildren().add(salir);

        return panelv;
    }

}
