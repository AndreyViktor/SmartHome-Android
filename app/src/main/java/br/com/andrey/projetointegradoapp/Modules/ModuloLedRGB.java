package br.com.andrey.projetointegradoapp.Modules;

import android.graphics.Color;

import java.io.Serializable;

/**
 * Created by andrey on 16/12/2016.
 */

public class ModuloLedRGB extends Modulo {
    private int color;

    private float getValue(){
        float hsv[]={0,0,0};
        Color.colorToHSV(color,hsv);
        return hsv[2];
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getLedWhite() {
        return (int)((255-(Color.alpha(color)))*getValue());
    }

    public int getLedRed() {
        return (Color.red(color));
    }

    public int getLedGreen() {
        return (Color.green(color));
    }

    public int getLedBlue() {
        return (Color.blue(color));
    }

    public double getAlpha() {
        return Color.alpha(color);
    }
}