import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * Write a program that simulates a traffic light.
 * The program lets the user select one of three lights: red, yellow or green.
 * Radio buttons will be used to select either red, yellow, or green.
 * A checkbox sets the light to flashing green. Use BorderPane for the layout.
 */
public class Main extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    private BorderPane root;
    private Circle redCircle;
    private Circle yellowCircle;
    private Circle greenCircle;
    final int WIDTH = 500;
    final int HEIGHT = 500;
    final int WIDTHOFFSET = 60;
    final int HEIGHTOFFSET = 180;

    //Rectangle USES
    final int xStartRectangle = WIDTH / 2 - WIDTHOFFSET;
    final int yStartRectangle = HEIGHT / 2 - HEIGHTOFFSET;

    final int rectangleWidth = WIDTHOFFSET;
    final int rectangleHeight = HEIGHTOFFSET;

    //Circle uses
    final int CIRCLERADIUS = 5 * 2;

    RadioButton redButton;
    RadioButton yellowButton;
    RadioButton greenButton;

    //flag
    private boolean flag;

    @Override
    public void start(Stage primaryStage) throws Exception {
        root = new BorderPane();

        CreateRectangle();
        CreateCircles();
        CreateRadioButtons();
        CreateCheckBox();


        Scene scene = new Scene(root, WIDTH, HEIGHT);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    private void CreateCheckBox() {
        CheckBox cb = new CheckBox("Policia");
        root.setTop(cb);
        FlashingGreen fg = new FlashingGreen();
        cb.setOnMouseClicked(fg);
    }

    class ButtonPressHandles implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent e) {
            SetAllTransparent();
            if (e.getSource().equals(redButton)) {
                redCircle.setFill(Color.RED);
            }
            if (e.getSource().equals(yellowButton)) {
                yellowCircle.setFill(Color.YELLOW);
            }
            if (e.getSource().equals(greenButton)) {
                greenCircle.setFill(Color.LIGHTGREEN);
            }
        }
    }

    class FlashingGreen implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
            if (!flag) {
                flag = true;
                root.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
            } else {
                root.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                flag = false;
            }
        }

    }

    private void SetAllTransparent() {
        redCircle.setFill(Color.TRANSPARENT);
        yellowCircle.setFill(Color.TRANSPARENT);
        greenCircle.setFill(Color.TRANSPARENT);
    }

    private void CreateRectangle() {
        Rectangle lightBox = new Rectangle(rectangleWidth, rectangleHeight);
        lightBox.setX(xStartRectangle);
        lightBox.setY(yStartRectangle);

        lightBox.setStrokeWidth(2);
        lightBox.setFill(Color.TRANSPARENT);
        lightBox.setStroke(Color.BLACK);
        root.getChildren().add(lightBox);
    }

    private void CreateCircles() {
        int circleXStart = (xStartRectangle + rectangleWidth / 2);
        int redYStart = (yStartRectangle + rectangleHeight / 3);
        redCircle = new Circle(circleXStart, redYStart, CIRCLERADIUS);

        redCircle.setStrokeWidth(1);
        redCircle.setStroke(Color.BLACK);
        redCircle.setFill(Color.TRANSPARENT);
        root.getChildren().add(redCircle);

        int yellowYStart = (yStartRectangle + rectangleHeight / 2);
        yellowCircle = new Circle(circleXStart, yellowYStart, CIRCLERADIUS);

        yellowCircle.setStrokeWidth(1);
        yellowCircle.setStroke(Color.BLACK);
        yellowCircle.setFill(Color.TRANSPARENT);
        root.getChildren().add(yellowCircle);

        int greenYStart = (yStartRectangle + rectangleHeight * 2 / 3);
        greenCircle = new Circle(circleXStart, greenYStart, CIRCLERADIUS);

        greenCircle.setStrokeWidth(1);
        greenCircle.setStroke(Color.BLACK);
        greenCircle.setFill(Color.TRANSPARENT);
        root.getChildren().add(greenCircle);
    }

    private void CreateRadioButtons() {

        redButton = new RadioButton("Red");
        yellowButton = new RadioButton("Yellow");
        greenButton = new RadioButton("Green");

        ToggleGroup toggleGroup = new ToggleGroup();
        redButton.setToggleGroup(toggleGroup);
        yellowButton.setToggleGroup(toggleGroup);
        greenButton.setToggleGroup(toggleGroup);

        ButtonPressHandles event = new ButtonPressHandles();
        redButton.setOnMousePressed(event);
        yellowButton.setOnMousePressed(event);
        greenButton.setOnMousePressed(event);

        HBox buttonBox = new HBox(5, redButton, yellowButton, greenButton);

        buttonBox.setAlignment(Pos.CENTER);
        root.setBottom(buttonBox);
    }
}
