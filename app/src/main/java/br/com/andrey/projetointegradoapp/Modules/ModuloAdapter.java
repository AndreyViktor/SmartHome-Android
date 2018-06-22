package br.com.andrey.projetointegradoapp.Modules;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.List;

import br.com.andrey.projetointegradoapp.ChangeViewsMainList.EditDimmerListItem;
import br.com.andrey.projetointegradoapp.ColorPicker.ObservableColor;
import br.com.andrey.projetointegradoapp.ColorPicker.ValueView;
import br.com.andrey.projetointegradoapp.DAO.ModuloDAO;
import br.com.andrey.projetointegradoapp.MQTThelper.MQTThelper;
import br.com.andrey.projetointegradoapp.R;
import br.com.andrey.projetointegradoapp.ChangeViewsMainList.ViewHolder;
import br.com.andrey.projetointegradoapp.network.UDP;

import static android.R.attr.data;

/**
 * Created by andrey on 04/08/2016.
 */
public class ModuloAdapter extends BaseAdapter {
    private final List<Modulo> modulos;
    private final Context context;
    private ViewHolder holder;
    ///
    //////
    MQTThelper mqttHelper;


 //   private Modulo modulo;

    public ModuloAdapter(Context context, List<Modulo> modulos) {
        this.modulos=modulos;
        this.context=context;
    }

    @Override
    public int getCount() {
        return modulos.size();
    }

    @Override
    public Object getItem(int position) {
        return modulos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return modulos.get(position).getId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Modulo modulo = modulos.get(position);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view;
        if(convertView == null) {
            switch (modulo.getModulo()){
                case "RGB":
                    view = inflater.inflate(R.layout.list_item_rgb, parent, false);
                    break;
                case "Switch":
                    view = inflater.inflate(R.layout.list_item_switch, parent, false);
                    break;
                case "Dimmer":
                    view = inflater.inflate(R.layout.list_item_dimmer, parent, false);
                    break;
                default: view = inflater.inflate(R.layout.list_item_dimmer,parent,false);

            }
            holder = new ViewHolder(view);
            view.setTag(holder);
        }else{
            view = convertView;

        }
        holder.campoNome.setTag(modulo.getId());
        holder.campoIp.setTag(modulo.getId());


        holder.campoNome.setText(modulo.getNome());
        holder.campoIp.setText("ip adress:"+modulo.getModuleIpAdress());

        startMqtt();

        switch (modulo.getModulo()){
            case "Switch":
                final ModuloSwitch sw = (ModuloSwitch) modulo;
                holder.campoSwitch1.setChecked(sw.isOn());
                holder.campoSwitch2.setChecked(sw.isOn());
                holder.campoSwitch3.setChecked(sw.isOn());


                holder.campoSwitch1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String message ="";

                        if(sw.isOn()){
                            message = "false";
                            sw.setOn(false);
                        }else{
                            message = "true";
                            sw.setOn(true);
                        }
                        try {
                            mqttHelper.mqttAndroidClient.publish("barplug/relay1/", message.getBytes(),0,false);
                        } catch (MqttException e) {
                            e.printStackTrace();
                        }
                        ModuloDAO dao = new ModuloDAO(context);
                        dao.updateSwitch(sw);
                        dao.close();

                    }
                });
                holder.campoSwitch2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String message ="";

                        if(sw.isOn()){
                            message = "false";
                            sw.setOn(false);
                        }else{
                            message = "true";
                            sw.setOn(true);
                        }
                        try {
                            mqttHelper.mqttAndroidClient.publish("barplug/relay2/", message.getBytes(),0,false);
                        } catch (MqttException e) {
                            e.printStackTrace();

                        }
                        ModuloDAO dao = new ModuloDAO(context);
                        dao.updateSwitch(sw);
                        dao.close();

                    }
                });
                holder.campoSwitch3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String message ="";

                    if(sw.isOn()){
                        message = "false";
                        sw.setOn(false);
                    }else{
                        message = "true";
                        sw.setOn(true);
                    }
                    try {
                        mqttHelper.mqttAndroidClient.publish("barplug/relay3/", message.getBytes(),0,false);
                    } catch (MqttException e) {
                        e.printStackTrace();
                    }
                    ModuloDAO dao = new ModuloDAO(context);
                    dao.updateSwitch(sw);
                    dao.close();

                }
            });
                break;
            case "RGB":
                    view = getViewRGB(view, modulo, context);
                break;
            case "Dimmer":
                    EditDimmerListItem dimmer = new EditDimmerListItem();
                    view = dimmer.getView(view, modulo, context);

                break;

        }

        return view;
    }

    private View getViewRGB(View view, Modulo modulo, final Context context) {

        final ModuloLedRGB rgb = (ModuloLedRGB) modulo;
        final ValueView vv = (ValueView) view.findViewById(R.id.value_rgb_list);
        ObservableColor oc = new ObservableColor(rgb.getColor());
        vv.updateColor(oc);
        vv.observeColor(oc);

        vv.setOnValueChangeListener(new ValueView.OnValueChangeListener() {
            @Override
            public void onValueChanged(ObservableColor oc) {
                rgb.setColor(oc.getColor());

                int white = rgb.getLedWhite() ;
                int red = rgb.getLedRed() ;
                int green = rgb.getLedGreen() ;
                int blue = rgb.getLedBlue();

                //String message ="W"+white+"R"+red+"G"+green+"B"+blue ;
                try {
                    mqttHelper.mqttAndroidClient.publish("lampadargb/white/", (white+"").getBytes(),0,false);
                    mqttHelper.mqttAndroidClient.publish("lampadargb/red/", (red+"").getBytes(),0,false);
                    mqttHelper.mqttAndroidClient.publish("lampadargb/green/", (green+"").getBytes(),0,false);
                    mqttHelper.mqttAndroidClient.publish("lampadargb/blue/", (blue+"").getBytes(),0,false);
                } catch (MqttException e) {
                    e.printStackTrace();
                }

                Log.d("branco:",white+"");
                Log.d("red:",red+"");
                Log.d("green:",green+"");
                Log.d("blue:",blue+"");

                ModuloDAO dao = new ModuloDAO(context);
                dao.updateRgb(rgb);
                dao.close();
            }
        });
        return view;
    }

    private void startMqtt(){
        mqttHelper = new MQTThelper(context);
        mqttHelper.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean b, String s) {

            }

            @Override
            public void connectionLost(Throwable throwable) {

            }

            @Override
            public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
                Log.w("Debug",mqttMessage.toString());

            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

            }
        });
    }
}
