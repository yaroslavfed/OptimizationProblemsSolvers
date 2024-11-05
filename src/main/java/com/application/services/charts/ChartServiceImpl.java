package com.application.services.charts;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartUtils;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class ChartServiceImpl implements ChartService {

    public byte[] generateChart() throws IOException {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(1, "Series1", "Category1");
        dataset.addValue(4, "Series1", "Category2");
        dataset.addValue(3, "Series1", "Category3");

        // Создание графика
        JFreeChart chart = ChartFactory.createBarChart(
                "Chart Title",
                "Category Axis",
                "Value Axis",
                dataset
        );

        // Запись графика в байтовый массив
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ChartUtils.writeChartAsPNG(baos, chart, 600, 400);
        return baos.toByteArray();
    }
}
