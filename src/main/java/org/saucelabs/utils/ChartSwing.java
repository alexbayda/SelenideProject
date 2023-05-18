package org.saucelabs.utils;

import io.qameta.allure.Attachment;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.stream.Collectors;

public class ChartSwing {

    @Attachment
    public static byte[] toLineChartPict(String title, String seriesName, Map<Integer, Long> data) throws InterruptedException, ExecutionException {
        var upperBound = (data.values().stream().max(Long::compare).orElseThrow().intValue() / 1000 + 1) * 1000;
        var bas = new ByteArrayOutputStream();

        new JFXPanel();

        FutureTask<Optional<byte[]>> futureTask = new FutureTask<>(() -> {
            final var xAxis = new NumberAxis(1, data.size(), 1);
            final var yAxis = new NumberAxis("Milliseconds", 0, upperBound, 500);
            yAxis.setTickMarkVisible(true);
            final var lineChart = new AreaChart<>(xAxis, yAxis);

            lineChart.setTitle(title);
            lineChart.setAnimated(false);

            var series = new XYChart.Series<Number, Number>();
            series.setName(seriesName);

            List<Integer> keys = data.keySet().stream().sorted().collect(Collectors.toList());
            for (Integer key : keys)
                series.getData().add(new XYChart.Data<>(key + 1, data.get(key)));

            lineChart.getData().add(series);
            Scene scene = new Scene(lineChart, 1000, 600);

            WritableImage image = scene.snapshot(null);

            try {
                ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", bas);
                return Optional.of(bas.toByteArray());
            } catch (Exception e) {
                return Optional.empty();
            }
        });

        Platform.runLater(futureTask);

        Optional<byte[]> result = futureTask.get();
        return result.orElse(null);
    }
}
