package br.com.andrey.projetointegradoapp.ChangeViewsMainList;

import android.content.Context;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import br.com.andrey.projetointegradoapp.Modules.Modulo;
import br.com.andrey.projetointegradoapp.DAO.ModuloDAO;
import br.com.andrey.projetointegradoapp.Modules.ModuloDimmer;
import br.com.andrey.projetointegradoapp.network.UDP;

/**
 * Created by andrey on 22/12/2016.
 */
public class EditDimmerListItem {
    public View getView(View view, final Modulo modulo, final Context context) {
        ViewHolder holder = (ViewHolder) view.getTag();

        final ModuloDimmer dimmer = (ModuloDimmer) modulo;

        holder.campoBarra.setTag(dimmer.getId());

        holder.campoBarra.setProgress((int) dimmer.getProgress());

        holder.campoBarra.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser) {
                    dimmer.setProgress(progress);
                    String message = "L" + (int)(progress*2.55 + 100);
                    Thread t = new Thread(new UDP(message, dimmer.getModuleIpAdress()));
                    t.start();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Long id = (Long) seekBar.getTag();
                //dimmer.setId(id);

                Toast.makeText(context,"Light :"+dimmer.getProgress()+"%",Toast.LENGTH_SHORT).show();


                ModuloDAO dao = new ModuloDAO(context);
                dao.updateDimmer(dimmer);
                dao.close();

            }
        });
    return view;
    }
}
