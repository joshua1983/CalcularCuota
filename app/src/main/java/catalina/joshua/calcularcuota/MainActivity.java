package catalina.joshua.calcularcuota;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.NumberFormat;
import java.util.ArrayList;

import static android.R.attr.duration;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        float valor_cuota = 0.0f;

        Button btn_calcular = (Button) findViewById(R.id.btn_calcular);
        final TextView txt_monto = (TextView) findViewById(R.id.input_monto);
        final TextView txt_interes = (TextView) findViewById(R.id.input_interes);
        final TextView txt_plazo = (TextView) findViewById(R.id.input_cuotas);
        final TextView txt_valor_cuota = (TextView) findViewById(R.id.valor_cuota);

        btn_calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float monto = Float.parseFloat( txt_monto.getText().toString() );
                float interes = Float.parseFloat( txt_interes.getText().toString() );
                double _R = 0;
                int plazo = Integer.parseInt(txt_plazo.getText().toString());
                ArrayList<Cuota> plan_pagos = new ArrayList<Cuota>();
                interes = interes / 100;
                _R = monto / (( 1 -  Math.pow((1 + interes), (-plazo))) / interes );

                if (interes == 0.0){
                    showMensaje(MainActivity.this.getApplicationContext(), "El interes no puede ser cero", Toast.LENGTH_LONG);
                }
                if (monto == 0.0){
                    showMensaje(MainActivity.this.getApplicationContext(), "El monto no puede ser cero", Toast.LENGTH_LONG);
                }
                if (plazo == 0){
                    plazo = 1;
                }

                for (int i=0; i< plazo; i++){
                    Cuota c = new Cuota();
                    if (i > 0){
                        c.setPago(_R);
                        if (interes == 1){
                            c.setCapital(monto);
                            c.setInteres(0);
                            c.setPago(monto);
                            c.setBalance(0);
                        }else{
                            c.setInteres(plan_pagos.get(i-1).getBalance() * interes);
                            c.setCapital(c.getPago() - c.getInteres());
                            c.setBalance(c.getBalance() - c.getCapital());
                        }
                    }else{
                        c.setBalance(monto);
                    }

                    plan_pagos.add(c);

                }
                Cuota respuesta = plan_pagos.get(1);
                NumberFormat format = NumberFormat.getCurrencyInstance();
                txt_valor_cuota.setText( format.format( respuesta.getPago() ) );

            }
        });
    }

    private void showMensaje(Context context, String texto, int duracion){
        Toast toast = Toast.makeText(context, texto, duracion);
        toast.show();
    }
}
