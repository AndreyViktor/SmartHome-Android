package br.com.andrey.projetointegradoapp;

import android.content.Context;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

/**
 * Created by andrey on 22/12/2016.
 */
public class EditRgbListItem {
    public View getView(View view, final Modulo modulo, final Context context) {
        ViewHolder holder = (ViewHolder) view.getTag();
        final ModuloLedRGB rgb = (ModuloLedRGB) modulo;
        holder.campoBarra.setTag(rgb.getId());
        holder.campoBarraRed.setTag(rgb.getId());
        holder.campoBarraBlue.setTag(rgb.getId());
        holder.campoBarraGreen.setTag(rgb.getId());

        holder.campoBarra.setProgress((int) rgb.getProgress());
        holder.campoBarraRed.setProgress((int) rgb.getProgressRed());
        holder.campoBarraGreen.setProgress((int) rgb.getProgressGreen());
        holder.campoBarraBlue.setProgress((int) rgb.getProgressBlue());

        holder.campoBarra.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    rgb.setProgress(progress);
                    String message = "W" + (int) (progress * 2.55 + 100);
                    Thread t = new Thread(new UDP(message, rgb.getModuleIpAdress()));
                    t.start();
                    Toast.makeText(context, "sending to IP:" + rgb.getModuleIpAdress() + "msg:" + message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Long id = (Long) seekBar.getTag();
                rgb.setId(id);

                Toast.makeText(context, "White :" + rgb.getProgress() + "%", Toast.LENGTH_SHORT).show();

                ModuloDAO dao = new ModuloDAO(context);
                dao.updateRgb(rgb);
                dao.close();

            }
        });

        holder.campoBarraRed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    rgb.setProgress(progress);
                    String message = "R" + (int) (progress * 2.55 + 100);
                    Thread t = new Thread(new UDP(message, rgb.getModuleIpAdress()));
                    t.start();
                    Toast.makeText(context, "sending to IP:" + rgb.getModuleIpAdress() + "msg:" + message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Long id = (Long) seekBar.getTag();
                rgb.setId(id);

                Toast.makeText(context, "Red :" + rgb.getProgress() + "%", Toast.LENGTH_SHORT).show();

                ModuloDAO dao = new ModuloDAO(context);
                dao.updateRgb(rgb);
                dao.close();

            }
        });

        holder.campoBarraGreen.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    rgb.setProgressGreen(progress);
                    String message = "G" + (int) (progress * 2.55 + 100);
                    Thread t = new Thread(new UDP(message, rgb.getModuleIpAdress()));
                    t.start();
                    Toast.makeText(context, "sending to IP:" + rgb.getModuleIpAdress() + "msg:" + message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Long id = (Long) seekBar.getTag();
                modulo.setId(id);

                Toast.makeText(context, "Green :" + rgb.getProgressGreen() + "%", Toast.LENGTH_SHORT).show();

                ModuloDAO dao = new ModuloDAO(context);
                dao.updateRgb(rgb);
                dao.close();

            }
        });
        holder.campoBarraBlue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    rgb.setProgressBlue(progress);
                    String message = "B" + (int) (progress * 2.55 + 100);
                    Thread t = new Thread(new UDP(message, modulo.getModuleIpAdress()));
                    t.start();
                    Toast.makeText(context, "sending to IP:" + modulo.getModuleIpAdress() + "msg:" + message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Long id = (Long) seekBar.getTag();
                rgb.setId(id);

                Toast.makeText(context, "blue :" + rgb.getProgressBlue() + "%", Toast.LENGTH_SHORT).show();

                ModuloDAO dao = new ModuloDAO(context);
                dao.updateRgb(rgb);
                dao.close();

            }
        });
    return view;
    }
}
