package br.com.andrey.projetointegradoapp.ChangeViewsMainList;

import android.view.DragEvent;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import br.com.andrey.projetointegradoapp.ColorPicker.ValueView;
import br.com.andrey.projetointegradoapp.R;

/**
 * Created by andrey on 04/08/2016.
 */
public class ViewHolder {

    public TextView campoIp;
    public TextView campoNome;
    public SeekBar campoBarra;
    public Switch campoSwitch1;
    public Switch campoSwitch2;
    public Switch campoSwitch3;


    public ViewHolder(View view) {
        campoNome =(TextView)view.findViewById(R.id.list_item_nome);
        campoIp =(TextView) view.findViewById(R.id.list_item_IpAdress);

        campoBarra = (SeekBar)view.findViewById(R.id.list_item_seekbar);
        campoSwitch1 = (Switch)view.findViewById(R.id.list_item_switch_1);
        campoSwitch2 = (Switch)view.findViewById(R.id.list_item_switch_2);
        campoSwitch3 = (Switch)view.findViewById(R.id.list_item_switch_3);

    }
}
