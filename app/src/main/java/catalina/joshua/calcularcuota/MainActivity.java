package catalina.joshua.calcularcuota;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.duration;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        float valor_cuota = 0.0f;

        Button btn_calcular = (Button) findViewById(R.id.btn_calcular);
        final TextView txt_monto = (TextView) findViewById(R.id.input_monto);
        final TextView txt_interes = (TextView) findViewById(R.id.input_cuotas);
        final TextView txt_plazo = (TextView) findViewById(R.id.input_cuotas);

        btn_calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float monto = Float.parseFloat( txt_monto.getText().toString() );
                float interes = Float.parseFloat( txt_interes.getText().toString() );
                int plazo = Integer.parseInt(txt_plazo.getText().toString());

                if (interes == 0.0){
                    showMensaje(MainActivity.this.getApplicationContext(), "El interes no puede ser cero", Toast.LENGTH_LONG);
                }
                if (monto == 0.0){
                    showMensaje(MainActivity.this.getApplicationContext(), "El monto no puede ser cero", Toast.LENGTH_LONG);
                }
                if (plazo == 0){
                    plazo = 1;
                }

                interes = interes / 100;
                float val_prestamo = interes * monto;



            }
        });
    }

    private void showMensaje(Context context, String texto, int duracion){
        Toast toast = Toast.makeText(context, texto, duracion);
        toast.show();
    }
}
