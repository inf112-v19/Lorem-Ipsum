package inf112.skeleton.app.Visuals;

import com.badlogic.gdx.Input;

import java.util.ArrayList;

public class MyTextInputListener implements Input.TextInputListener {

    @Override
    public void input (String text) {
        System.out.println("player name saved");
        System.out.println("input(String text): " + text);
    }

    @Override
    public void canceled () {
        System.out.println("pressed cancel to input");
    }

}