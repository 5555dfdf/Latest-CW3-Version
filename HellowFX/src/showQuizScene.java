import Utils.QuestionUtil;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import xjtlu.cpt111.assignment.quiz.model.Option;
import xjtlu.cpt111.assignment.quiz.model.Question;
import java.util.*;



public class showQuizScene {

    public static void show(Stage stage, String path) {
        List<Question> questionList = setupQuiz(path);
        if (questionList.isEmpty()) {
            // 如果没有问题，显示提示信息
            Label noQuestionsLabel = new Label("No questions available.");
            VBox root = new VBox(10, noQuestionsLabel);
            root.setStyle("-fx-alignment: center; -fx-padding: 20px;");
            Scene scene = new Scene(root, 800, 600);
            stage.setTitle("QuizSystem");
            stage.setScene(scene);
            stage.show();
            return;
        }

        // 当前题目索引
        final int[] currentIndex = {0};
        // 累积分数
        final int[] totalScore = {0};

        // 问题显示部分
        Label questionStatement = new Label();
        Label questionSection = new Label();

        // 答题框
        TextField answerInput = new TextField();
        answerInput.setPromptText("Enter your answer here");

        // 提交/下一题按钮
        Button actionButton = new Button("Next");

        // 答案结果显示
        Label resultLabel = new Label();

        actionButton.setOnAction(event -> {
            if (currentIndex[0] < questionList.size()) {
                // 获取当前问题
                Question currentQuestion = questionList.get(currentIndex[0]);
                String userAnswer = answerInput.getText().trim();

                try {
                    int userAnswerIndex = Integer.parseInt(userAnswer); // 将用户输入的答案转为整数
                    if (userAnswerIndex == correctOptionIndex) {
                        // 根据难度增加分数
                        int questionScore = getScoreByDifficulty(String.valueOf(currentQuestion.getDifficulty()));
                        totalScore[0] += questionScore;
                    }
                } catch (NumberFormatException e) {
                    // 忽略无效答案
                }

                // 清空输入框
                answerInput.clear();

                // 前进到下一题或结束
                currentIndex[0]++;
                if (currentIndex[0] < questionList.size()) {
                    updateQuestion(questionList, currentIndex[0], questionStatement, questionSection, resultLabel);
                    if (currentIndex[0] == questionList.size() - 1) {
                        // 最后一题切换按钮文本
                        actionButton.setText("Submit");
                    }
                } else {
                    // 显示总成绩
                    questionStatement.setText("Quiz Finished!");
                    questionSection.setText("");
                    resultLabel.setText("Your total score is: " + totalScore[0]);
                    resultLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: blue;");
                    actionButton.setDisable(true);
                    answerInput.setDisable(true);
                }
            }
        });

        // 返回按钮
        Button backButton = new Button("Return");
        backButton.setOnAction(event -> stage.close());

        // 初始显示第一题
        updateQuestion(questionList, currentIndex[0], questionStatement, questionSection, resultLabel);

        // 布局
        VBox root = new VBox(10, questionStatement, questionSection, answerInput, actionButton, resultLabel, backButton);
        root.setStyle("-fx-alignment: center; -fx-padding: 20px;");
        Scene scene = new Scene(root, 800, 600);

        // 设置舞台
        stage.setTitle("QuizSystem");
        stage.setScene(scene);
        stage.show();
    }

    // 方法：根据难度获取分值
    private static int getScoreByDifficulty(String difficulty) {
        switch (difficulty.toLowerCase()) {
            case "easy":
                return 5;
            case "medium":
                return 10;
            case "hard":
                return 15;
            case "very_hard":
                return 20;
            default:
                return 0; // 未知难度默认 0 分
        }
    }

    // 在类中添加一个字段来存储当前问题正确答案的新索引
    private static int correctOptionIndex = -1;

    private static void updateQuestion(List<Question> questionList, int index, Label questionStatement, Label questionSection, Label resultLabel) {
        Question question = questionList.get(index);
        questionStatement.setText("Question " + (index + 1) + ": " + question.getQuestionStatement());

        // 获取选项并转换为 List
        Option[] options = question.getOptions();
        List<Option> optionList = new ArrayList<>();
        Collections.addAll(optionList, options);

        // 随机打乱选项
        Collections.shuffle(optionList, new Random());

        // 查找正确答案的新索引
        Option correctOption = Arrays.stream(options)
                .filter(Option::isCorrectAnswer) //  Option 类有一个 isCorrectAnswer() 方法
                .findFirst()
                .orElse(null);
        if (correctOption != null) {
            correctOptionIndex = optionList.indexOf(correctOption) + 1; // 转换为 1-based 索引
        }

        // 构造带序号的选项文本
        StringBuilder optionsWithNumbers = new StringBuilder("Options:\n");
        for (int i = 0; i < optionList.size(); i++) {
            String optionText = optionList.get(i).toString();
            optionText = optionText.replace("[correctAnswer]", "").trim();
            optionsWithNumbers.append(i + 1).append(". ").append(optionText).append("\n");
        }

        questionSection.setText(optionsWithNumbers.toString());
        resultLabel.setText(""); // 清空上一个问题的结果
    }

    public static List<Question> setupQuiz(String path) {
        QuestionUtil questionUtil = new QuestionUtil();
        questionUtil.loadQuestions(path);
        List<Question> questions = questionUtil.getQuestionsList();
        // 打印问题和选项
        return questions;
    }
}