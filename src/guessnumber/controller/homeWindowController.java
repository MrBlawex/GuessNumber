package guessnumber.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.net.URL;
import java.nio.file.Path;
import java.util.Random;
import java.util.ResourceBundle;

public class homeWindowController implements Initializable {

    @FXML
    private Slider slider;

    @FXML
    private Label lb_maxValueSlider;

    @FXML
    private Label lb_score;

    @FXML
    private Label lb_number;

    @FXML
    private Label lb_valueSlider2;

    @FXML
    private VBox showScore;

    @FXML
    private Label lb_valueSlider1;

    @FXML
    private Button btn_check;

    @FXML
    private Button btn_startGame;

    @FXML
    private TextField textField;

    @FXML
    private Label lb_minValueSlider;

    @FXML
    private AnchorPane gameZone;

    @FXML
    private AnchorPane gameControls;

    @FXML
    private VBox showMassage;

    @FXML
    private Label lb_massage;

    @FXML
    private TextArea textArea;

    int score = 7;
    int number;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lb_minValueSlider.setText(String.valueOf((int) slider.getMin()));
        lb_maxValueSlider.setText(String.valueOf((int) slider.getMax()));


        lb_valueSlider1.textProperty().bindBidirectional(lb_valueSlider2.textProperty());
        lb_valueSlider1.setText(String.valueOf((int) slider.getValue()));

        slider.valueProperty().addListener((observable, oldValue, newValue) -> lb_valueSlider1.setText(String.valueOf((int) newValue.doubleValue())));

        gameZone.setVisible(false);

        btn_check.disableProperty().bind(textField.textProperty().isEmpty());
    }

    @FXML
    public void checkNumber(MouseEvent event) {
        int currentNumber = Integer.valueOf(textField.getText());
        if (currentNumber < number) {
            textArea.setText(textArea.getText() + "Ваше число меньше: " + currentNumber + "\n");
            score--;
            lb_score.setText(String.valueOf(score));
        }
        if (currentNumber > number) {
            textArea.setText(textArea.getText() + "Ваше число больше: " + currentNumber + "\n");
            score--;
            lb_score.setText(String.valueOf(score));
        }
        if (currentNumber == number) {
            showScore.setVisible(false);
            score = 7;
            gameControls.setDisable(true);
            lb_massage.setText("Вы выиграли!!!");
            lb_massage.setTextFill(Color.GREEN);
            lb_number.setText(String.valueOf(number));
            showMassage.setVisible(true);
        }
        if (score == 0) {
            lb_massage.setText("Вы проиграли!!!");
            lb_massage.setTextFill(Color.RED);
            lb_number.setText(String.valueOf(number));

            score = 7;

            showScore.setVisible(false);
            showMassage.setVisible(true);
            gameControls.setDisable(true);
        }
        textField.setText("");
    }

    @FXML
    public void startGame(MouseEvent event) {
        Random random = new Random();
        number = random.nextInt((int) slider.getValue());

        lb_score.setText(String.valueOf(score));


        textArea.setText("");
        showMassage.setVisible(false);

        lb_score.setText(String.valueOf(score));
        showScore.setVisible(true);
        gameZone.setVisible(true);
        gameControls.setDisable(false);
    }

}
