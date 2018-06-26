package br.com.andrey.projetointegradoapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.eclipse.paho.client.mqttv3.MqttException;

import br.com.andrey.projetointegradoapp.ColorPicker.AlphaView;
import br.com.andrey.projetointegradoapp.ColorPicker.HueSatView;
import br.com.andrey.projetointegradoapp.ColorPicker.ObservableColor;
import br.com.andrey.projetointegradoapp.ColorPicker.ValueView;
import br.com.andrey.projetointegradoapp.DAO.ModuloDAO;
import br.com.andrey.projetointegradoapp.MQTThelper.MQTThelper;
import br.com.andrey.projetointegradoapp.Modules.ModuloLedRGB;
import br.com.andrey.projetointegradoapp.R;

public class RgbConfigActivity extends AppCompatActivity{

    private ObservableColor observableColor;
    private ModuloLedRGB rgb;
    MQTThelper mqttHelper = MQTThelper.getInstance(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rgb_config);

        HueSatView hue = (HueSatView) findViewById(R.id.hue_sat);
        AlphaView alpha = (AlphaView) findViewById(R.id.alpha_view);
        ValueView value = (ValueView) findViewById(R.id.value_view);

        Intent recebeModuloInfo = getIntent();
        rgb = (ModuloLedRGB) recebeModuloInfo.getSerializableExtra("rgb");

        observableColor = new ObservableColor(rgb.getColor());

        hue.updateColor(observableColor);
        hue.observeColor(observableColor);

        alpha.observeColor(observableColor);
        alpha.updateColor(observableColor);

        value.observeColor(observableColor);
        value.updateColor(observableColor);


        value.setOnValueChangeListener(new ValueView.OnValueChangeListener() {
            @Override
            public void onValueChanged(ObservableColor observableColor) {
                rgb.setColor(observableColor.getColor());
                sendMessageToMqtt(rgb);
            }
        });

        hue.setOnColorChangeListener(new HueSatView.OnColorChangeListener() {
            @Override
            public void onNewColorPicked(ObservableColor observableColor) {
                rgb.setColor(observableColor.getColor());
                sendMessageToMqtt(rgb);
            }
        });

        alpha.setOnAlphaChangeListener(new AlphaView.OnAlphaChangeListener() {
            @Override
            public void onAlphaChanged(ObservableColor observableColor) {
                rgb.setColor(observableColor.getColor());
                sendMessageToMqtt(rgb);
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        ModuloDAO dao = new ModuloDAO(this);
        int test =dao.updateRgbStatus(rgb);
        Log.d("test:",""+test);
        dao.close();
    }
    private void sendMessageToMqtt(ModuloLedRGB rgb){

        int white = rgb.getLedWhite();
        int red = rgb.getLedRed();
        int green = rgb.getLedGreen();
        int blue = rgb.getLedBlue();

        try {
            mqttHelper.mqttAndroidClient.publish("lampadargb/white/", String.valueOf(white).getBytes(),0,false);
            mqttHelper.mqttAndroidClient.publish("lampadargb/red/", String.valueOf(red).getBytes(),0,false);
            mqttHelper.mqttAndroidClient.publish("lampadargb/green/", String.valueOf(green).getBytes(),0,false);
            mqttHelper.mqttAndroidClient.publish("lampadargb/blue/", String.valueOf(blue).getBytes(),0,false);
        } catch (MqttException e) {
            e.printStackTrace();
        }

        /*
        String message ="W"+white+"R"+red+"G"+green+"B"+blue ;
        Thread t = new Thread(new UDP(message, rgb.getModuleIpAdress()));
        t.start();*/

        Log.d("branco:",white+"");
        Log.d("red:",red+"");
        Log.d("green:",green+"");
        Log.d("blue:",blue+"");

    }
}
