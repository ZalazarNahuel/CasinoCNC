import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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

        window.setScene(menuPrincial());
    }
    private Scene menuPrincial(){
        // BOTONES
        Button btnGenerala = new Button("GENERALA");
        btnGenerala.setOnAction(event -> menuGenerala());
        btnGenerala.setMinSize(150,50);
        Button btnBlackjack = new Button("BLACKJACK");
        btnBlackjack.setMinSize(150,50);
        Button btnExit = new Button("EXIT");
        btnExit.setOnAction(event -> closeApp());
        btnExit.setMinSize(150,50);

        //TITULO
        Label titulo = new Label();
        titulo.setAlignment(Pos.TOP_LEFT);
        titulo.setText("CASINO CNC");
        titulo.setTextFill(Color.web("#E60026"));
        titulo.setFont(new Font("arial",30));


        VBox panelT = new VBox(titulo);
        panelT.setAlignment(Pos.TOP_CENTER);

        VBox panelB = new VBox(btnGenerala,btnBlackjack,btnExit);
        panelB.setAlignment(Pos.CENTER);
        panelB.setSpacing(20);

        StackPane root = new StackPane();
        root.getChildren().addAll(panelT,panelB);
        Scene scene  = new Scene(root,650,550);
        return scene;
    }
    private void closeApp(){
        window.close();
    }
    private void menuGenerala(){

    }
}
