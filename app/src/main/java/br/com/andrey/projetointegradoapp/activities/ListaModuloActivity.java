package br.com.andrey.projetointegradoapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.DragEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

import br.com.andrey.projetointegradoapp.ColorPicker.ObservableColor;
import br.com.andrey.projetointegradoapp.Modules.Modulo;
import br.com.andrey.projetointegradoapp.Modules.ModuloAdapter;
import br.com.andrey.projetointegradoapp.DAO.ModuloDAO;
import br.com.andrey.projetointegradoapp.Modules.ModuloLedRGB;
import br.com.andrey.projetointegradoapp.R;

public class ListaModuloActivity extends AppCompatActivity {

    private ListView listaModulo;
    private Modulo modulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_modulo);

        listaModulo = (ListView) findViewById(R.id.Lista_Modulos);
        listaModulo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ModuloLedRGB rgb =  (ModuloLedRGB) listaModulo.getItemAtPosition(position);
                Log.d("id:",rgb.getId()+"");
                Intent config = new Intent(ListaModuloActivity.this, RgbConfigActivity.class);
                config.putExtra("rgb",rgb);
                startActivity(config);
            }
        });

        Button addModulo = (Button) findViewById(R.id.Lista_Botao_adiciona);
        addModulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent novoModulo = new Intent(ListaModuloActivity.this, FormularioAddModuloActivity.class);
                startActivity(novoModulo);
            }
        });
        registerForContextMenu(listaModulo);
    }

    private void carregaLista() {
        ModuloDAO dao = new ModuloDAO(this);
        List<Modulo> modulos = dao.buscaModulos();
        dao.close();

        ModuloAdapter adapter = new ModuloAdapter(this, modulos);
        listaModulo.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }

    @Override
    public void onCreateContextMenu(final ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {

        MenuItem deletar = menu.add("deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Modulo modulo = (Modulo) listaModulo.getItemAtPosition(info.position);

                Toast.makeText(ListaModuloActivity.this, "item:" + modulo.getId() + " deletado", Toast.LENGTH_SHORT);

                ModuloDAO dao = new ModuloDAO(ListaModuloActivity.this);
                dao.delete(modulo);
                dao.close();
                carregaLista();
                return false;
            }
        });

    }
}


