package br.com.andrey.projetointegradoapp.Modules;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import br.com.andrey.projetointegradoapp.ChangeViewsMainList.EditDimmerListItem;
import br.com.andrey.projetointegradoapp.ColorPicker.ObservableColor;
import br.com.andrey.projetointegradoapp.ColorPicker.ValueView;
import br.com.andrey.projetointegradoapp.DAO.ModuloDAO;
import br.com.andrey.projetointegradoapp.R;
import br.com.andrey.projetointegradoapp.ChangeViewsMainList.ViewHolder;
import br.com.andrey.projetointegradoapp.network.UDP;

/**
 * Created by andrey on 04/08/2016.
 */
public class ModuloAdapter extends BaseAdapter {
    private final List<Modulo> modulos;
    private final Context context;
    private ViewHolder holder;
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

        switch (modulo.getModulo()){
            case "Switch":
                ModuloSwitch sw = (ModuloSwitch) modulo;
//                holder.campoSwitch.setChecked(sw.isOn());
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
                //Toast.makeText(RgbConfigActivity.this,"id:"+rgb.getId()+"color:"+rgb.getColor(),Toast.LENGTH_SHORT).show();


                int white = rgb.getLedWhite();
                int red = rgb.getLedRed();
                int green = rgb.getLedGreen();
                int blue = rgb.getLedBlue();

                String message ="W"+white+"R"+red+"G"+green+"B"+blue ;
                Thread t = new Thread(new UDP(message, rgb.getModuleIpAdress()));
                t.start();

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


}
