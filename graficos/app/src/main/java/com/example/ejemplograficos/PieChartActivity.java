package com.example.ejemplograficos;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PieChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);


        //definir instancia del elemento pieChart
        PieChart pieChart=findViewById(R.id.pieChart);


        //arreglo de datos
        /*ArrayList<PieEntry> visitas = new ArrayList<PieEntry>();
        visitas.add(new PieEntry(505,2015));
        visitas.add(new PieEntry(350,2016));
        visitas.add(new PieEntry(750,2017));
        visitas.add(new PieEntry(225,2018));
        visitas.add(new PieEntry(500,2019));
        visitas.add(new PieEntry(300,2020));

        PieDataSet pieDataSet=new PieDataSet(visitas, "Grafico de Pastel");
        pieDataSet.setColors(ColorTemplate.PASTEL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(16f);

        PieData pieData= new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.setCenterText("Ejemplo grafico de pastel");
        pieChart.animate();*/

        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        String label = "type";

        //inicializar los datos
        //Map<> representa una estructura de datos tipo clave/valor
        Map<String,Integer> datos = new HashMap<>();
        datos.put("Bebidas", 200);
        datos.put("Snacks", 450);
        datos.put("Granos Basicos", 120);
        datos.put("Lacteos", 300);
        datos.put("Frutas", 150);

        //incializando colores para los datos
        ArrayList<Integer> colores = new ArrayList<>();
        colores.add(Color.parseColor("#304567"));
        colores.add(Color.parseColor("#309976"));
        colores.add(Color.parseColor("#476567"));
        colores.add(Color.parseColor("#890567"));
        colores.add(Color.parseColor("#355676"));
        colores.add(Color.parseColor("#ff567f"));

        //introducir los datos dentro de la pie chart entry
        for (String type: datos.keySet()){
            pieEntries.add(new PieEntry(datos.get(type).floatValue(), type));
        }

        //recolectando los datos con un nombre de etiqueta
        PieDataSet pieDataSet = new PieDataSet(pieEntries, label);
        pieDataSet.setValueFormatter(new PercentFormatter());
        //definir el tamaño del texto
        pieDataSet.setValueTextSize(12f);
        pieDataSet.setColors(colores);
        //pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        //agrupando los datos
        PieData pieData= new PieData(pieDataSet);
        //mostrando los valores en el elemento pieChart
        pieData.setDrawValues(true);
        pieChart.setData(pieData);
        pieChart.animateY(2000);

    }
}