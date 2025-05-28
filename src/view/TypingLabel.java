package view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class TypingLabel {
    private final Label label = new Label();
    private final String fullText;

    public TypingLabel(String text) {
        this.fullText = text;
        this.label.setStyle("-fx-text-fill: #ffffff;-fx-font-size: 18px;-fx-font-weight: bold;-fx-font-family: 'Serif';-fx-effect: dropshadow(gaussian, #fefefe, 2, 0.2, 0, 1.1);");
    }

    public void playTypingAnimation() {
        Timeline timeline = new Timeline();
        for (int i = 0; i < fullText.length(); i++) {
            final int index = i;
            KeyFrame frame = new KeyFrame(Duration.millis(100 * (i + 1)), event -> {
                label.setText(fullText.substring(0, index + 1));
            });
            timeline.getKeyFrames().add(frame);
        }
        timeline.play();
    }

    public Label getLabel() {
        return label;
    }
}
