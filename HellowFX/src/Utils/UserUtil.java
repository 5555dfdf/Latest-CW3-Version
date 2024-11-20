package Utils;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class UserUtil {

    // 检查用户名和密码是否匹配
    public static boolean validateUser(String username, String password, String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userInfo = line.split(",");
                if (userInfo.length == 3) { // 确保行格式正确
                    String storedUsername = userInfo[0].trim();
                    String storedPassword = userInfo[2].trim();
                    if (storedUsername.equals(username) && storedPassword.equals(password)) {
                        return true; // 匹配成功
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // 匹配失败
    }
}
