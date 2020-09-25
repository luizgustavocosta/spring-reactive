package tech.costa.luiz.ui;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import org.springframework.stereotype.Component;
import tech.costa.luiz.reactive.client.RandomMessage;
import tech.costa.luiz.reactive.client.WebClientMessageClient;

import java.util.function.Consumer;

import static javafx.collections.FXCollections.observableArrayList;

@Component
public class ChartController implements Consumer<RandomMessage> {

    @FXML
    private LineChart<String, Double> chart;
    private final WebClientMessageClient webClientMessageClient;
    private final ObservableList<Data<String, Double>> seriesData = observableArrayList();

    public ChartController(WebClientMessageClient webClientMessageClient) {
        this.webClientMessageClient = webClientMessageClient;
    }

    @FXML
    public void initialize() {
        String name = "LuizGustavo";
        final ObservableList<Series<String, Double>> data = observableArrayList();
        data.add(new Series<>(name, seriesData));
        chart.setData(data);
        webClientMessageClient.randomMessagesFor(name).subscribe(this);
    }

    @Override
    public void accept(RandomMessage randomMessage) {
        Platform.runLater(() ->
            seriesData.add(
                    //new Data<>(String.valueOf(randomMessage.getDateTime().getSecond()),
                    new Data<>(randomMessage.getId(),
                            Double.valueOf("2"))));
    }
}
