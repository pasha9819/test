package com.pasha.computernetworkstest.gui.utils;

import com.pasha.computernetworkstest.gui.ButtonFactory;
import javafx.scene.control.ButtonBase;
import javafx.scene.text.Font;

public final class ButtonModel {
    private static final int LAYOUT_X = 25;

    private static final int PREF_WIDTH = 680;

    private static final int PREF_HEIGHT = 40;

    private static final boolean WRAP_TEXT = true;

    private static final int FONT_SIZE = 13;

    private static final Font FONT = new Font(FONT_SIZE);

    /**
     *
     * @param type type of Button (<code>ButtonFactory.RADIO</code>
     *              or <code>ButtonFactory.CHECK</code>)
     * @return RadioButton or CheckBox with preset parameters
     *          such as <p><code>LAYOUT_X = {@value LAYOUT_X}</code>,</p>
     *                  <p><code>PREF_WIDTH = {@value PREF_WIDTH}</code>,</p>
     *                  <p><code>PREF_HEIGHT = {@value  PREF_HEIGHT}</code>,</p>
     *                  <p><code>WRAP_TEXT = {@value WRAP_TEXT}</code>,</p>
     *                  <p><code>Font.size = {@value FONT_SIZE}</code></p>
     */
    public static ButtonBase getButton(int type){
        ButtonBase b = ButtonFactory.getButton(type);

        b.setLayoutX(LAYOUT_X);
        b.setPrefWidth(PREF_WIDTH);
        b.setPrefHeight(PREF_HEIGHT);
        b.setWrapText(WRAP_TEXT);
        b.setFont(FONT);

        return b;
    }
}
