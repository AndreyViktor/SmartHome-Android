package br.com.andrey.projetointegradoapp.Modules;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import br.com.andrey.projetointegradoapp.ChangeViewsMainList.EditDimmerListItem;
import br.com.andrey.projetointegradoapp.ChangeViewsMainList.EditRgbListItem;
import br.com.andrey.projetointegradoapp.R;
import br.com.andrey.projetointegradoapp.ChangeViewsMainList.ViewHolder;

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
                    EditRgbListItem rgb = new EditRgbListItem();
                    view = rgb.getView(view, modulo, context);
                break;
            case "Dimmer":
                    EditDimmerListItem dimmer = new EditDimmerListItem();
                    view = dimmer.getView(view, modulo, context);

                break;

        }

        return view;
    }


}
