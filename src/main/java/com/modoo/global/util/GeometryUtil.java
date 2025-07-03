package com.modoo.global.util;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;

public class GeometryUtil {

    private static GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);

    /**
     * 위도, 경도로 Point 생성
     *
     * @param latitude
     * @param longitude
     * @return
     */
    public static Point getPoint(double latitude, double longitude) {
        return geometryFactory.createPoint(new Coordinate(latitude, longitude));
    }
}
