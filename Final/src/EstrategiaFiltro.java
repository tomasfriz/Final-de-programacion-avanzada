@FunctionalInterface
public interface EstrategiaFiltro<T> {
    boolean filtrar(T elemento);
}