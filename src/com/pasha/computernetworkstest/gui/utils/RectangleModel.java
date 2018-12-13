package com.pasha.computernetworkstest.gui.utils;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public final class RectangleModel {

    private static final int LAYOUT_X = 20;

    private static final int WIDTH = 685;

    private static final int HEIGHT = 40;

    private static final double OPACITY = 0.2;

    private static final int STROKE_WIDTH = 0;

    /**
     *
     * @return Rectangle with preset parameters
     *          such as <p><code>layoutX = {@value LAYOUT_X}</code>,</p>
     *                  <p><code>width = {@value WIDTH}</code>,</p>
     *                  <p><code>height = {@value  HEIGHT}</code>,</p>
     *                  <p><code>fill = <code style = "color: gray">{@code Color.GRAY}</code></code>,</p>
     *                  <p><code>opacity = {@value OPACITY}</code>,</p>
     *                  <p><code>strokeWidth = {@value STROKE_WIDTH}</code></p>
     */
    public static Rectangle getRectangle(){
        Rectangle rec = new Rectangle();

        rec.setLayoutX(LAYOUT_X);

        rec.setWidth(WIDTH);
        rec.setHeight(HEIGHT);

        rec.setFill(Color.GRAY);
        rec.setOpacity(OPACITY);
        rec.setStrokeWidth(STROKE_WIDTH);

        return rec;
    }
}
