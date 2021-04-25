package com.example.calculocuota.dto

import android.content.Context
import android.view.Gravity
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView

class TablaCuotas{
    lateinit var context:Context
    var listaCuotas:List<Cuota> = ArrayList()

    fun TablaCuotas(_cuotas:List<Cuota>, layout:TableLayout, _context:Context){
        this.context =_context
        this.listaCuotas =_cuotas
    }

    fun addData(){
        this.listaCuotas.forEach {
            cuota: Cuota -> {
            var tableRow:TableRow = TableRow(this.context)
            var celda:TextView = TextView(this.context)
            celda.gravity = Gravity.CENTER
            celda.setTextSize(12F)

        }
        }
    }
}