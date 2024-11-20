import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class RegisterPage {

    public static void show(Stage stage) {
        // 创建控件
        Label usernameLabel = new Label("New Username:");
        TextField usernameField = new TextField();

        Label useridLabel = new Label("New UserID:");
        TextField useridField = new TextField();

        Label passwordLabel = new Label("New Password:");
        PasswordField passwordField = new PasswordField();
        Label confirmLabel = new Label("Confirm your Password:");
        PasswordField confirmField = new PasswordField();

        Button registerButton = new Button("Sign-In");
        Button backButton = new Button("Return");

        registerButton.setOnAction(e -> {
            String newUsername = usernameField.getText();
            String newUserid = useridField.getText();
            String newPassword = passwordField.getText();
            String confirmPass = confirmField.getText();
            String filePath = "resources/u.csv";

            // 检查各项是否为空
            if (newUsername.isEmpty()||newUserid.isEmpty()||newPassword.isEmpty() || confirmPass.isEmpty()) {
                showErrorDialog("No empty field!");
                return;  //
            }

            // 检查密码是否匹配
            if (!newPassword.equals(confirmPass)) {
                showErrorDialog("Password mismatch, please re-enter password!");
                passwordField.clear();  // 清空密码字段
                confirmField.clear();   // 清空确认密码字段
                return;  // 不结束注册过程，允许继续输入
            }

            // 尝试写入文件
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
                // 将新用户数据写入CSV文件
                writer.write(newUsername + "," + newUserid + "," + newPassword);
                writer.newLine();
                writer.close();// 确保新记录位于新的一行
                ChooseFunction.show(stage);  // 注册成功后显示下一个界面
            } catch (IOException e1) {
                // 处理与文件相关的错误
                showErrorDialog("An error occurred while saving registration information, please try again.");
                e1.printStackTrace();  // 输出错误日志，便于调试
            }
        });



        // 设置返回按钮点击事件
        backButton.setOnAction(e -> new Main().start(stage));

        // 布局
        VBox root = new VBox(10, usernameLabel, usernameField,useridLabel,useridField, passwordLabel, passwordField,confirmLabel,confirmField, registerButton, backButton);
        root.setStyle("-fx-alignment: center; -fx-padding: 20px;");

        // 场景
        Scene scene = new Scene(root, 500, 400);

        // 设置舞台
        stage.setTitle("QuizSystem");
        stage.setScene(scene);
        stage.show();

    }
    // 显示错误对话框的方法
    private static void showErrorDialog(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Registration Error");
        alert.setContentText(message);
        alert.showAndWait();  // 显示对话框并等待用户关闭
    }
}
