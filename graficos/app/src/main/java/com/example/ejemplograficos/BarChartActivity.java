package com.example.ejemplograficos;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class BarChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart_activiy);

        //instancia de de la clase BarChart
        BarChart barChart=findViewById(R.id.barChart);

        //crear un arreglo donde se definan los datos a mostrar en la grafica
        ArrayList<BarEntry> datos = new ArrayList<BarEntry>();
        datos.add(new BarEntry(2015, 350));
        datos.add(new BarEntry(2016, 500));
        datos.add(new BarEntry(2017, 450));
        datos.add(new BarEntry(2018, 250));
        datos.add(new BarEntry(2019, 600));
        datos.add(new BarEntry(2020, 560));

        //definir DataSet para el grafico
        BarDataSet barDataSet = new BarDataSet(datos, "Visitas");

        //definir las propiedades
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        //asignar el dataset a la grafica
        BarData barData=new BarData(barDataSet);
        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("Ejemplo grafico de barra");
        barChart.animateY(2000);
    }
}