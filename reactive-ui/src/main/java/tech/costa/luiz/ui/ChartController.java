package tech.costa.luiz.ui;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import org.springframework.stereotype.Component;
import tech.costa.luiz.reactive.client.Bingo;
import tech.costa.luiz.reactive.client.RandomNumberClient;

import java.util.function.Consumer;

import static javafx.collections.FXCollections.observableArrayList;

@Component
public class ChartController {

    @FXML
    private LineChart<String, Integer> chart;
    private final RandomNumberClient randomNumberClient;


    public ChartController(RandomNumberClient randomNumberClient) {
        this.randomNumberClient = randomNumberClient;
    }

    @FXML
    public void initialize() {
        String name = "Luiz";
        final ObservableList<Series<String, Integer>> data = observableArrayList();
        final BingoSubscriber bingoSubscriber = new BingoSubscriber(name);
        randomNumberClient.randomNumbersFor(name).subscribe(bingoSubscriber);

        String otherName = "Gustavo";
        final BingoSubscriber otherBingoSubscriber = new BingoSubscriber(otherName);
        randomNumberClient.randomNumbersFor(name).subscribe(otherBingoSubscriber);

        data.add(bingoSubscriber.series);
        data.add(otherBingoSubscriber.series);
        chart.setData(data);

    }

     private static class BingoSubscriber implements Consumer<Bingo> {

        private final ObservableList<Data<String, Integer>> seriesData = observableArrayList();
        private final Series<String, Integer> series;

        public BingoSubscriber(String name) {
            series = new Series<>(name, seriesData);
        }

        @Override
        public void accept(Bingo bingo) {
            Platform.runLater(() ->
                    seriesData.add(
                            new Data<>(String.valueOf(bingo.getDateTime().getSecond()),
                                    bingo.getNumber())));
        }
     }
}
