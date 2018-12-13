package com.pasha.computernetworkstest.gui.utils;


import javafx.scene.control.Label;
import javafx.scene.text.Font;

public final class LabelModel {
    private static final int LAYOUT_X = 10;

    private static final int LAYOUT_Y = 10;

    private static final int PREF_WIDTH = 700;

    private static final int PREF_HEIGHT = 100;

    private static final boolean WRAP_TEXT = true;

    private static final int FONT_SIZE = 16;

    private static final Font FONT = new Font(FONT_SIZE);

    /**
     *
     * @return Label with preset parameters
     *          such as <p><code>LAYOUT_X = {@value LAYOUT_X}</code>,</p>
     *                  <p><code>LAYOUT_Y = {@value LAYOUT_Y}</code>,</p>
     *                  <p><code>PREF_WIDTH = {@value PREF_WIDTH}</code>,</p>
     *                  <p><code>PREF_HEIGHT = {@value  PREF_HEIGHT}</code>,</p>
     *                  <p><code>WRAP_TEXT = {@value WRAP_TEXT}</code>,</p>
     *                  <p><code>Font.size = {@value FONT_SIZE}</code></p>
     */
    public static Label getLabel(){
        Label qLabel = new Label();

        qLabel.setWrapText(WRAP_TEXT);
        qLabel.setFont(FONT);

        qLabel.setPrefSize(PREF_WIDTH, PREF_HEIGHT);
        qLabel.setLayoutX(LAYOUT_X);
        qLabel.setLayoutY(LAYOUT_Y);

        return qLabel;
    }
}
