import Utils.UserUtil;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class LoginPage {

    public static void show(Stage stage) {
        // 创建控件
        Label usernameLabel = new Label("Usernamee:");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("log in");
        Button backButton = new Button("return");

        // 提示信息
        Label messageLabel = new Label();

        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String filePath = "resources/u.csv"; // CSV 文件路径

            if (UserUtil.validateUser(username, password, filePath)) {
                ChooseFunction.show(stage);

            } else {
                messageLabel.setText("Wrong Username or Password！");
            }
        });

        // 设置返回按钮点击事件
        backButton.setOnAction(e -> new Main().start(stage));

        // 布局
        VBox root = new VBox(10, usernameLabel, usernameField, passwordLabel, passwordField, loginButton, messageLabel, backButton);
        root.setStyle("-fx-alignment: center; -fx-padding: 20px;");

        // 场景
        Scene scene = new Scene(root, 500, 400);

        // 设置舞台
        stage.setTitle("QuizSystem");
        stage.setScene(scene);
        stage.show();
    }
}
