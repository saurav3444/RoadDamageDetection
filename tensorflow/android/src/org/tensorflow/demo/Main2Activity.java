package org.tensorflow.demo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.github.mikephil.charting.utils.ColorTemplate.COLORFUL_COLORS;


public class Main2Activity extends Activity {

    HashMap<String, Integer> map = new HashMap<String, Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        try {
            map  = readDataLineByLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setupiechart();
    }

    private void setupiechart() {

        List<PieEntry> pieEntries = new ArrayList<>();

        for (String i : map.keySet()) {

            pieEntries.add(new PieEntry(map.get(i), i));
        }
        PieDataSet dataSet = new PieDataSet(pieEntries, "Road damage statistics");
        dataSet.setColors(COLORFUL_COLORS);
        PieData data = new PieData(dataSet);
        data.setValueTextSize(15f);

        PieChart chart = (PieChart) findViewById(R.id.chart);
        chart.setData(data);

        chart.animateY(1000);
        chart.invalidate();

    }

    public static HashMap<String, Integer> readDataLineByLine() throws IOException {

        File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        FileReader in = new FileReader(new File(dir, "mydata.csv"));
        //CSVReader reader = new CSVReader(in);


        BufferedReader br = new BufferedReader(in);
        String line;
        int damage[] = new int[8];
        HashMap<String, Integer> map = new HashMap<String, Integer>();

        map.put("D00",1);
        map.put("D01",1);
        map.put("D10",1);
        map.put("D11",1);
        map.put("D20",1);
        map.put("D21",1);
        map.put("D43",1);
        map.put("D44",1);

        while ((line = br.readLine()) != null)   //returns a Boolean value
        {
            String splitBy = ",";
            String[] row = line.split(splitBy);    // use comma as separator

            Integer count = map.containsKey(row[1]) ? map.get(row[1]) : 0;
            map.put(row[1], count + 1);

            Log.v("Loog", "obj1"+ row[1] + map.get(row[1])+1 );
        }
        return map;
    }
}