package br.com.andrey.projetointegradoapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrey on 04/08/2016.
 */
public class ModuloDAO extends SQLiteOpenHelper {
    public ModuloDAO(Context context) {
        super(context, "modulos", null, 1);
    }

    @Override
            public void onCreate(SQLiteDatabase db) {
                String sql = "create table modulos(id integer primary key," +
                                                    "nome text not null," +
                                                    "ipAdress text," +
                                                    "modulo text," +
                                                    "progress double," +
                                                    "progressRed double," +
                                                    "progressGreen double," +
                                                    "progressBlue double," +
                                                    "switch bit)";
                db.execSQL(sql);
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                switch (oldVersion) {
                    case 1:
                }
            }

            public List<Modulo> buscaModulos() {
                SQLiteDatabase db = getReadableDatabase();
                Cursor c = db.rawQuery("select * from modulos",null);
                List<Modulo> modulos = new ArrayList<Modulo>();
                while(c.moveToNext()){
                    switch (c.getString(c.getColumnIndex("modulo"))){
                        case "RGB":
                            ModuloLedRGB rgb = new ModuloLedRGB();
                            rgb.setNome(c.getString(c.getColumnIndex("nome")));
                            rgb.setId(c.getLong(c.getColumnIndex("id")));
                            rgb.setModuleIpAdress(c.getString(c.getColumnIndex("ipAdress")));
                            rgb.setModulo(c.getString(c.getColumnIndex("modulo")));

                            rgb.setProgress(c.getDouble(c.getColumnIndex("progress")));
                            rgb.setProgressRed(c.getDouble(c.getColumnIndex("progressRed")));
                            rgb.setProgressGreen(c.getDouble(c.getColumnIndex("progressGreen")));
                            rgb.setProgressBlue(c.getDouble(c.getColumnIndex("progressBlue")));

                            modulos.add(rgb);
                            break;
                        case "Dimmer":
                            ModuloDimmer dimmer = new ModuloDimmer();
                            dimmer.setNome(c.getString(c.getColumnIndex("nome")));
                            dimmer.setId(c.getLong(c.getColumnIndex("id")));
                            dimmer.setModuleIpAdress(c.getString(c.getColumnIndex("ipAdress")));
                            dimmer.setModulo(c.getString(c.getColumnIndex("modulo")));

                            dimmer.setProgress(c.getDouble(c.getColumnIndex("progress")));
                            modulos.add(dimmer);
                            break;
                        case"Switch":
                            ModuloSwitch sw = new ModuloSwitch();
                            sw.setNome(c.getString(c.getColumnIndex("nome")));
                            sw.setId(c.getLong(c.getColumnIndex("id")));
                            sw.setModuleIpAdress(c.getString(c.getColumnIndex("ipAdress")));
                            sw.setModulo(c.getString(c.getColumnIndex("modulo")));

                            modulos.add(sw);
                            break;
                    }

                }
            c.close();
            return modulos;
    }

    public void insere(Modulo modulo) {
        ContentValues values = new ContentValues();
        values.put("nome",modulo.getNome());
        values.put("ipAdress",modulo.getModuleIpAdress());
        values.put("modulo", modulo.getModulo());
        //ContentValues values = getFullModuloValues(modulo);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("modulos",null,values);
        values.clear();
    }

    private ContentValues getBasicModuloValues(Modulo modulo) {
        ContentValues values = new ContentValues();
        values.put("id",modulo.getId());
        values.put("nome",modulo.getNome());
        values.put("ipAdress",modulo.getModuleIpAdress());
        
        return values;
    }
    public void delete(Modulo modulo){
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {String.valueOf(modulo.getId())};
        db.delete("modulos","id=?",params);
    }

 /*   public void update(Modulo modulo) {
        SQLiteDatabase db = getWritableDatabase();
       // ContentValues values = new ContentValues();
        //values.put("id",modulo.getId());
        //values.put("progress",modulo.getProgress());
        ContentValues values = getBasicModuloValues(modulo);
        String[] args = {String.valueOf(modulo.getId())};
        db.update("modulos",values,"id=?", args);
        values.clear();
    }*/
    public void updateRgb(ModuloLedRGB rgb){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = getBasicModuloValues(rgb);
        values.put("progress",rgb.getProgress());
        values.put("progressRed",rgb.getProgressRed());
        values.put("progressGreen",rgb.getProgressGreen());
        values.put("progressBlue",rgb.getProgressBlue());
        String[] args = {String.valueOf(rgb.getId())};
        db.update("modulos", values,"id=?",args);
        values.clear();
    }
    public void updateDimmer(ModuloDimmer dimmer){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = getBasicModuloValues(dimmer);
        values.put("progress",dimmer.getProgress());
        String[] args = {String.valueOf(dimmer.getId())};
        db.update("modulos", values,"id=?",args);
        values.clear();
    }

    public void updateSwitch(ModuloSwitch sw){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = getBasicModuloValues(sw);
        String[] args = {String.valueOf(sw.getId())};
        db.update("modulos", values,"id=?",args);
        values.clear();
    }
}
