package com.example.ejemplograficos;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;

public class RadarChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radar_chart);


        RadarChart radarChart=findViewById(R.id.radarChart);

        //definir el arraylist
        ArrayList<RadarEntry> visitantes = new ArrayList<>();
        visitantes.add(new RadarEntry(200));
        visitantes.add(new RadarEntry(500));
        visitantes.add(new RadarEntry(350));
        visitantes.add(new RadarEntry(600));

        //dataset
        RadarDataSet radarDataSet = new RadarDataSet(visitantes, "Visitantes 1");
        radarDataSet.setColor(Color.RED);
        radarDataSet.setLineWidth(2f);
        radarDataSet.setValueTextColor(Color.RED);
        radarDataSet.setValueTextSize(14f);


        //segundo ArrayList
        ArrayList<RadarEntry> visitantes2 = new ArrayList<>();
        visitantes2.add(new RadarEntry(400));
        visitantes2.add(new RadarEntry(300));
        visitantes2.add(new RadarEntry(450));
        visitantes2.add(new RadarEntry(200));

        //dataset
        RadarDataSet radarDataSet2 = new RadarDataSet(visitantes2, "Visitantes 2");
        radarDataSet2.setColor(Color.BLUE);
        radarDataSet2.setLineWidth(2f);
        radarDataSet2.setValueTextColor(Color.BLUE);
        radarDataSet2.setValueTextSize(14f);

        //definir la data para RadarChart
        RadarData radarData= new RadarData();
        radarData.addDataSet(radarDataSet);
        radarData.addDataSet(radarDataSet2);

        String[] labels ={"2015","2016","2017","2018"};
        XAxis xAxis=radarChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
        radarChart.getDescription().setText("Ejemplo Radar Chart");
        radarChart.setData(radarData);
        radarChart.animateX(2000);
    }
}