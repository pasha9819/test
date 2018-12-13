package com.pasha.computernetworkstest.gui;

import javafx.scene.control.ButtonBase;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;


public class ButtonFactory {
    public static final int RADIO = 1;
    public static final int CHECK = 0;


    public static ButtonBase getButton(int buttonType){
        if(buttonType == CHECK){
            return new CheckBox();
        }else if (buttonType == RADIO){
            return new RadioButton();
        }else{
            throw new IllegalArgumentException("Arg may be ButtonType.CHECK or ButtonType.RADIO)");
        }
    }

}
