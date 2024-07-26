import java.util.ArrayList;
import java.util.List;

public class Utilidades {
    public static <T> List<T> filtrarLista(List<T> lista, Filtro<T> filtro) {
        List<T> resultado = new ArrayList<>();
        for (T elemento : lista) {
            if (filtro.cumple(elemento)) {
                resultado.add(elemento);
            }
        }
        return resultado;
    }
}

@FunctionalInterface
interface Filtro<T> {
    boolean cumple(T elemento);
}