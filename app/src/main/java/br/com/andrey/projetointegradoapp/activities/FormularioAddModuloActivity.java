package br.com.andrey.projetointegradoapp.activities;

import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import br.com.andrey.projetointegradoapp.Modules.Modulo;
import br.com.andrey.projetointegradoapp.DAO.ModuloDAO;
import br.com.andrey.projetointegradoapp.R;

import br.com.andrey.projetointegradoapp.ColorPicker.HueSatView;
import br.com.andrey.projetointegradoapp.Modules.Modulo;
import br.com.andrey.projetointegradoapp.DAO.ModuloDAO;
import br.com.andrey.projetointegradoapp.R;
import br.com.andrey.projetointegradoapp.network.TcpClient;

public class FormularioAddModuloActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_add_modulo);
        WifiManager manager = (WifiManager) this.getApplicationContext().getSystemService(this.WIFI_SERVICE);
        WifiInfo info = manager.getConnectionInfo();
        String ssid = info.getSSID();

        /*
        TcpClient tcp = new TcpClient(new TcpClient.OnMessageReceived() {
            @Override
            public void messageReceived(String message) {
                Modulo m = new Modulo();
                m.setModuleIpAdress(message);
                Log.d("msg:",message);
            }
        });
        tcp.run();
        tcp.sendMessage("SSID"+"Password");*/
        Log.d("ssid",ssid);
        Toast.makeText(this,ssid,Toast.LENGTH_LONG).show();

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
                RadioGroup group =(RadioGroup) findViewById(R.id.formulario_group);
                EditText campoNome= (EditText) findViewById(R.id.formulario_nome);
                EditText campoIP = (EditText) findViewById(R.id.formulario_IP);
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
