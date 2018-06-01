package tarea2.app.tarea2;


import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Pantalla para ingresar los datos correspondientes y enviar a la aplicacion de Whatsapp
 */
public class MainActivity extends AppCompatActivity {

    private View.OnClickListener ListenerWA;
    private View.OnClickListener ListenerWA2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Establecer accciones para el boton de envio
        Button boton_enviar = (Button) findViewById(R.id.boton_enviar);
        Button boton_enviar2 = (Button) findViewById(R.id.boton_enviar2);

        ListenerWA = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EnviarAWhatsapp(view);
            }
        };
        boton_enviar.setOnClickListener(ListenerWA);

        ListenerWA2 = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EnviarAWhatsapp2(view);
            }
        };
        boton_enviar2.setOnClickListener(ListenerWA2);
    }
    private void EnviarAWhatsapp(View view) {
        try {
            TextView input_telefono = (TextView) findViewById(R.id.telefono);
            TextView input_mensaje = (TextView) findViewById(R.id.mensaje);

            String telefono = input_telefono.getText().toString(); // Puede digitar con espacios o el signo + .
            telefono = telefono.replace("+", "").replace(" ", "");

            String mensaje = input_mensaje.getText().toString();

            Intent sendIntent = new Intent("android.intent.action.MAIN");
            sendIntent.putExtra("jid", telefono + "@s.whatsapp.net");
            sendIntent.putExtra(Intent.EXTRA_TEXT, mensaje);
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.setPackage("com.whatsapp");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        }
        catch (Exception e){
            e.printStackTrace();
            Toast.makeText(MainActivity.this,"No se encontro la aplicacion Whatsapp",Toast.LENGTH_LONG).show();
        }
    }

    public void EnviarAWhatsapp2(View view){
        try {
            TextView input_telefono = (TextView) findViewById(R.id.telefono);
            TextView input_mensaje = (TextView) findViewById(R.id.mensaje);

            String telefono = input_telefono.getText().toString(); // Puede digitar con espacios o el signo + .
            telefono = telefono.replace("+", "").replace(" ", "");

            String mensaje = input_mensaje.getText().toString();

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+telefono +"&text="+mensaje));
            intent.setPackage("com.whatsapp");
            startActivity(intent);
        }
        catch (Exception e){
            e.printStackTrace();
            Toast.makeText(MainActivity.this,"No se encontro la aplicacion Whatsapp",Toast.LENGTH_LONG).show();
        }
    }


}

