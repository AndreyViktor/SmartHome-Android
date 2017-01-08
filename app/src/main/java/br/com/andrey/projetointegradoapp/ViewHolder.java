package br.com.andrey.projetointegradoapp;

import android.view.DragEvent;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

/**
 * Created by andrey on 04/08/2016.
 */
public class ViewHolder {

    public TextView campoIp;
    public TextView campoNome;
    public SeekBar campoBarra;
    public SeekBar campoBarraRed;
    public SeekBar campoBarraGreen;
    public SeekBar campoBarraBlue;
    public Switch campoSwitch;

    public ViewHolder(View view) {
        campoNome =(TextView)view.findViewById(R.id.list_item_nome);
        campoIp =(TextView) view.findViewById(R.id.list_item_IpAdress);

        campoBarra = (SeekBar)view.findViewById(R.id.list_item_seekbar);
        campoBarraRed = (SeekBar)view.findViewById(R.id.list_item_seekbar_red);
        campoBarraGreen = (SeekBar)view.findViewById(R.id.list_item_seekbar_green);
        campoBarraBlue = (SeekBar)view.findViewById(R.id.list_item_seekbar_blue);

        campoSwitch = (Switch)view.findViewById(R.id.list_item_switch);

    }
}
