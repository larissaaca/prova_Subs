package larissa.project;

import java.util.HashMap;
import java.util.Map;

public class CityCoordinates {

    private static final Map<String, double[]> CITY_COORDINATES_MAP = new HashMap<>();

    static {
        CITY_COORDINATES_MAP.put("Aracaju", new double[]{-10.9167, -37.05});
        CITY_COORDINATES_MAP.put("Belem", new double[]{-1.4558, -48.5039});
        CITY_COORDINATES_MAP.put("Belo Horizonte", new double[]{-19.9167, -43.9333});
        CITY_COORDINATES_MAP.put("Boa Vista", new double[]{2.81972, -60.67333});
        CITY_COORDINATES_MAP.put("Brasilia", new double[]{-15.793889, -47.882778});
        CITY_COORDINATES_MAP.put("Campo Grande", new double[]{-20.44278, -54.64639});
        CITY_COORDINATES_MAP.put("Cuiaba", new double[]{-15.5989, -56.0949});
        CITY_COORDINATES_MAP.put("Curitiba", new double[]{-25.4297, -49.2711});
        CITY_COORDINATES_MAP.put("Florianopolis", new double[]{-27.5935, -48.55854});
        CITY_COORDINATES_MAP.put("Fortaleza", new double[]{-3.7275, -38.5275});
        CITY_COORDINATES_MAP.put("Teresina", new double[]{-5.089167, -42.801944});
        CITY_COORDINATES_MAP.put("Goiania", new double[]{-16.67861, -49.25389});
        CITY_COORDINATES_MAP.put("Joao Pessoa", new double[]{-7.12, -34.88});
        CITY_COORDINATES_MAP.put("Macapa", new double[]{0.033, -51.05});
        CITY_COORDINATES_MAP.put("Maceio", new double[]{-9.66583, -35.73528});
        CITY_COORDINATES_MAP.put("Manaus", new double[]{-3.1189, -60.0217});
        CITY_COORDINATES_MAP.put("Natal", new double[]{-5.7833, -35.2});
        CITY_COORDINATES_MAP.put("Palmas", new double[]{-10.16745, -48.32766});
        CITY_COORDINATES_MAP.put("Porto Alegre", new double[]{-30.0331, -51.23});
        CITY_COORDINATES_MAP.put("Porto Velho", new double[]{-8.76194, -63.90389});
        CITY_COORDINATES_MAP.put("Recife", new double[]{-8.05, -34.9});
        CITY_COORDINATES_MAP.put("Rio Branco", new double[]{-9.97472, -67.81});
        CITY_COORDINATES_MAP.put("Rio de Janeiro", new double[]{-22.9111, -43.2056});
        CITY_COORDINATES_MAP.put("Salvador", new double[]{-12.9747, -38.4767});
        CITY_COORDINATES_MAP.put("Sao Luis", new double[]{-2.5283, -44.3044});
        CITY_COORDINATES_MAP.put("Sao Paulo", new double[]{-23.55, -46.6333});
        CITY_COORDINATES_MAP.put("Vitoria", new double[]{-20.2889, -40.3083});
    }

    public static double[] getCoordinates(String city) {
        return CITY_COORDINATES_MAP.getOrDefault(city, null);
    }
}
