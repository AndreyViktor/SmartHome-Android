package br.com.andrey.projetointegradoapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import br.com.andrey.projetointegradoapp.Modules.Modulo;
import br.com.andrey.projetointegradoapp.DAO.ModuloDAO;
import br.com.andrey.projetointegradoapp.R;

public class FormularioAddModuloActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_add_modulo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_formulario,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_ok:
                Modulo modulo = new Modulo();

                EditText campoNome= (EditText) findViewById(R.id.formulario_nome);
                EditText campoIP = (EditText) findViewById(R.id.formulario_IP);
                RadioGroup group =(RadioGroup) findViewById(R.id.formulario_group);
                RadioButton rb = (RadioButton) findViewById(group.getCheckedRadioButtonId());

                modulo.setNome(campoNome.getText().toString());
                modulo.setModuleIpAdress(campoIP.getText().toString());
                modulo.setModulo(rb.getText().toString());

                ModuloDAO dao = new ModuloDAO(this);
                dao.insere(modulo);
                dao.close();

                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
