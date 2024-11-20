import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChooseFunction {


    public static void show(Stage stage) {
        Label messageLabel = new Label();
        messageLabel.setText("Log-in Success！");
        // 创建控件
        Button testButton = new Button("Attend Quiz");
        Button rankingButton = new Button("Show Ranklist");
        Button backButton = new Button("return");
        backButton.setOnAction(e -> new Main().start(stage));
        // 设置按钮点击事件
        testButton.setOnAction(e -> SubjectChoosePage.show(stage)); // 进入测试界面
//      rankingButton.setOnAction(e -> RankingPage.show(stage)); // 进入排名展示界面

        // 布局
        VBox root = new VBox(20, messageLabel, testButton, rankingButton,backButton);
        root.setStyle("-fx-alignment: center; -fx-padding: 20px;");

        // 场景
        Scene scene = new Scene(root, 500, 400);

        // 设置舞台
        stage.setTitle("QuizSystem");
        stage.setScene(scene);
        stage.show();
    }
}
