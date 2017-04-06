package br.com.andrey.projetointegradoapp.activities;

import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;

import br.com.andrey.projetointegradoapp.ColorPicker.AlphaView;
import br.com.andrey.projetointegradoapp.ColorPicker.HueSatView;
import br.com.andrey.projetointegradoapp.ColorPicker.ObservableColor;
import br.com.andrey.projetointegradoapp.R;
import br.com.andrey.projetointegradoapp.ColorPicker.SwatchView;

import static java.security.AccessController.getContext;

public class RgbConfigActivity extends AppCompatActivity{
    private final ObservableColor observableColor = new ObservableColor(0);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rgb_config);
        SwatchView swatch = (SwatchView) findViewById(R.id.swatch_view);
        swatch.updateColor(observableColor);

        AlphaView alpha = (AlphaView) findViewById(R.id.alpha_view);
        alpha.observeColor(observableColor);

        HueSatView hue = (HueSatView) findViewById(R.id.hue_sat);
        hue.observeColor(observableColor);
        hue.updateColor(observableColor);


    }
}
