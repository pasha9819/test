package com.pasha.computernetworkstest.gui;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public abstract class PreparedEmptyPane {
    public static final int SETTINGS = 0;
    public static final int TEST = 1;
    public static Pane createAnchorPane(int type){
        Pane pane = new AnchorPane();
        pane.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        if (type == SETTINGS){
            pane.setPrefSize(240, 480);
        }else {
            pane.setPrefSize(720, 480);
        }
        return pane;
    }
}
