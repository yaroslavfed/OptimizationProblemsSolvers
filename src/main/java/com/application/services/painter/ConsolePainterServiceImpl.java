package com.application.services.painter;

import com.application.utilities.Spot;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Qualifier("consolePainter")
public class ConsolePainterServiceImpl implements PainterService {
    private final List<Double> pointsList = new ArrayList<>();

    @Override
    public void paint(int axisLength, @NotNull List<Double> points) throws InterruptedException {
        // Докидываем новую точку в список
        pointsList.addAll(points);
        // Начало координат (0)
        int origin = axisLength / 2;
        // Масштаб графика
        double scale = 1.0;
        // Составляем график из точек
        var graph = drawAxisWithPoints(axisLength, origin, pointsList, scale);
        System.out.print("\rСчитаем:\t" + graph);
    }

    /**
     * Генерируем график с точками
     *
     * @param axisLength длина графика
     * @param origin     точка центра графика
     * @param points     список точек
     * @param scale      коэффициент масштабирования графика
     * @return консольный вывод графика
     */
    @NotNull
    private String drawAxisWithPoints(int axisLength, int origin, @NotNull List<Double> points, double scale) {
        StringBuilder graph = new StringBuilder();

        // Создаём массив для оси, чтобы отметить позиции точек
        // Заполняем ось горизонтальной линией
        List<Spot<Double, String>> axis = Stream
                .generate(() -> new Spot<Double, String>(null, 0, "-"))
                .limit(Math.abs(axisLength))
                .collect(Collectors.toCollection(ArrayList::new));

        // Отмечаем точки
        for (Double point : points) {
            // Рассчитываем позицию с учётом масштаба и округляем до ближайшего целого
            // Округление до 3 знаков
            BigDecimal bd = new BigDecimal(scale * point).setScale(3, RoundingMode.HALF_UP);
            double roundedScaledPoint = bd.doubleValue();
            int position = (int) roundedScaledPoint + origin;

            // Проверяем, чтобы позиция была в пределах оси
            if (position >= 0 && position < axisLength) {
                // Отмечаем точку символом '*'
                if (axis.get(position).getValue() != null) {
                    axis.get(position).setTemperature(axis.get(position).getTemperature() + 1);
                } else {
                    axis.get(position).setValue(point);
                    axis.get(position).setTemperature(1);
                    axis.get(position).setMark("*");
                }
            }
        }

        // Устанавливаем начало координат
        axis.get(origin).setValue(0.);
        axis.get(origin).setMark("|0");

        // Рисуем ось
        for (Spot<Double, String> c : axis)
            graph.append(c);

        return graph.toString();
    }
}
