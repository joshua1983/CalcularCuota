package com.example.calculocuota

import android.graphics.Color
import android.os.Bundle
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.setPadding
import androidx.core.widget.doAfterTextChanged
import com.example.calculocuota.dto.Cuota
import java.text.DecimalFormat


class MainActivity : AppCompatActivity() {
    var planPagos: MutableList<Cuota> = ArrayList<Cuota>()
    val dec = DecimalFormat("###,###.##")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val monto = findViewById<EditText>(R.id.txtMonto)
        val plazo = findViewById<EditText>(R.id.txtPlazo)
        val cuota = findViewById<TextView>(R.id.resultado)
        val interes = findViewById<EditText>(R.id.txtInteres)
        val boton = findViewById<Button>(R.id.btnCalcular)
        val tablaCuotas = findViewById<TableLayout>(R.id.tablaCuotas)

        boton.setOnClickListener{
            this.planPagos.clear()
            val valorMonto = monto.text.toString().replace(",","")
            val plazoValor = plazo.text.toString()
            val interesValor = interes.text.toString()
            cuota.text = dec.format( calcularCuota(valorMonto,plazoValor, interesValor) )
            pintarTabla(tablaCuotas)
        }
    }

    fun pintarTabla(tabla:TableLayout){
        tabla.removeAllViews()
        var id = 1
        this.planPagos.forEach{ cuota ->

            var _tr = TableRow(this)
            _tr.id = id
            _tr.setBackgroundColor(Color.LTGRAY)
            _tr.layoutParams = ViewGroup.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.WRAP_CONTENT)
            var _cuotaNumero = TextView(this)
            _cuotaNumero.id = id + 100
            _cuotaNumero.text = id.toString()
            _cuotaNumero.setPadding(5,5,5,5)
            var _cuota = TextView(this)
            _cuota.id = id + 200
            _cuota.text = dec.format( cuota.monto)
            _cuota.setPadding(5,5,5,5)
            _tr.addView(_cuotaNumero)
            _tr.addView(_cuota)
            tabla.addView(_tr)
            id++
        }
    }

    fun calcularCuota(_monto:String, _plazo:String, _interes:String): Float {
        val monto = _monto.toFloat()
        val plazo = Integer.parseInt(_plazo)

        if (_plazo == "1"){
            return (monto/plazo)
        }

        val interesProcentaje = _interes.toFloat()
        var sumatoria = 0.0F
        var montoPlazo = monto

        for (i in 1 .. plazo){
            var interes = montoPlazo * (interesProcentaje/100)
            var cuota = (monto / plazo) + interes
            if (i == 1){
                cuota = montoPlazo / plazo
                montoPlazo = montoPlazo - cuota
            }
            if (i == 2){
                cuota = cuota + planPagos.get(0).interes
                montoPlazo = montoPlazo - (cuota -  planPagos.get(0).interes - interes)
            }
            if (i > 2) {
                montoPlazo = montoPlazo - (cuota - interes)
            }
            var cuotaObj = Cuota()
            cuotaObj.interes = interes
            cuotaObj.monto = cuota
            cuotaObj.deudaTotal = montoPlazo
            planPagos.add(cuotaObj)
            sumatoria += cuota
        }

        var promedio = sumatoria / plazo
        return promedio
    }

}