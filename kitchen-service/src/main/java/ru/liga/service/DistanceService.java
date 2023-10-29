package ru.liga.service;

import org.postgis.PGgeometry;
import org.postgis.Point;
import org.springframework.stereotype.Service;

/**
 * Сервис для вычисления расстояния между координатами.
 */
@Service
public class DistanceService {

    /**
     * Константа для преобразования градусов в километры.
     */
    private static final Double KILOMETERS_TO_DEGREES = 111.134861111;

    /**
     * Вычисляет расстояние в километрах между двумя координатами в формате WKT.
     *
     * @param coordinate1 Координаты первой точки в формате WKT.
     * @param coordinate2 Координаты второй точки в формате WKT.
     * @return Расстояние между координатами в километрах.
     * @throws RuntimeException Если не удалось вычислить расстояние.
     */
    public static Double calculateDistance(String coordinate1, String coordinate2) {
        try {
            PGgeometry geom1 = new PGgeometry(coordinate1);
            PGgeometry geom2 = new PGgeometry(coordinate2);

            Point point1 = (Point) geom1.getGeometry();
            Point point2 = (Point) geom2.getGeometry();

            double distance = point1.distance(point2);
            double distanceInKilometers = distance * KILOMETERS_TO_DEGREES;

            return distanceInKilometers;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Не удалось вычислить расстояние");
        }
    }
}
