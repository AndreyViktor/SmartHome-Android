package br.com.andrey.projetointegradoapp.Modules;

/**
 * Created by andrey on 16/12/2016.
 */

public class ModuloLedRGB extends Modulo {
    private double progress;
    private double progressRed;
    private double progressGreen;
    private double progressBlue;

    public double getProgressRed() {
        return progressRed;
    }

    public void setProgressRed(double progressRed) {
        this.progressRed = progressRed;
    }


    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }

    public double getProgressGreen() {
        return progressGreen;
    }

    public void setProgressGreen(double progressGreen) {
        this.progressGreen = progressGreen;
    }

    public double getProgressBlue() {
        return progressBlue;
    }

    public void setProgressBlue(double progressBlue) {
        this.progressBlue = progressBlue;
    }
}
