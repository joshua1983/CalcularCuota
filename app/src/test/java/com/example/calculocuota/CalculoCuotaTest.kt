package com.example.calculocuota

import com.example.calculocuota.dto.Cuota
import org.junit.Assert
import org.junit.Test

internal class CalculoCuotaTest {
    @Test
    fun calculoCuotaTest() {
        val _monto = "1000000"
        val _plazo = "12"
        val _interes = "1.95"
        var retorno = ""


        val monto = _monto.toFloat()
        val plazo = Integer.parseInt(_plazo)

        if (_plazo == "1"){
            retorno = (monto/plazo).toString()
        }
        val planPagos: MutableList<Cuota> = ArrayList<Cuota>()
        val interesProcentaje = _interes.toFloat()
        var sumatoria = 0.0F
        var montoPlazo = monto

        for (i in 1 .. plazo){
            var interes = montoPlazo * (interesProcentaje/100)
            println(i.toString()+" interes: "+interes)
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
            println(i.toString()+" cuota: "+cuota)


            var cuotaObj = Cuota()
            cuotaObj.interes = interes
            cuotaObj.monto = cuota
            cuotaObj.deudaTotal = montoPlazo
            planPagos.add(cuotaObj)
            sumatoria += cuota

            println(i.toString()+" saldo: "+montoPlazo)
        }

        var promedio = sumatoria / plazo

        Assert.assertTrue(promedio>0)

        println(promedio)

    }
}