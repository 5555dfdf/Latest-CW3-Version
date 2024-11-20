import Utils.QuestionUtil;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SubjectChoosePage {
    private static final  String cs_path = "resources/questionsBank/questions_cs.xml";
    private static final  String ee_path = "resources/questionsBank/questions_ee.xml";
    private static final  String english_path = "resources/questionsBank/questions_english.xml";
    private static final  String math_path = "resources/questionsBank/questions_mathematics.xml";

    public static void show(Stage stage){
        VBox menuLayout = new VBox(10);
        menuLayout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Label title = new Label("Please Choose Your subject！");
        Button csQuizButton = new Button("Computer Science");
        Button eeQuizButton = new Button("Electronic Engineering");
        Button englishButton = new Button("English");
        Button mathButton = new Button("Mathematics");
        Label other = new Label("Other functions");
        Button backButton = new Button("return");
        backButton.setOnAction(e -> ChooseFunction.show(stage));

        csQuizButton.setOnAction(e->{
              showQuizScene.show(stage, cs_path);
                });
        eeQuizButton.setOnAction(e->{
            showQuizScene.show(stage, ee_path);
       });
       englishButton.setOnAction(e->{
           showQuizScene.show(stage, english_path);
     });
      mathButton.setOnAction(e->{
          showQuizScene.show(stage, math_path);
    });

//        rankingButton.setOnAction(e -> RankingPage(stage));
        menuLayout.getChildren().addAll(title, csQuizButton,eeQuizButton,englishButton
                , mathButton,other, backButton);

        Scene scene = new Scene(menuLayout, 500, 400);

        stage.setTitle("QuizSystem");
        stage.setScene(scene);
        stage.show();
    }

}
