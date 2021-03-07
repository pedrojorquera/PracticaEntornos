package practicaJUnit;

import java.util.Vector;

public interface InterfaceCredito {
    double getCreditoDisponible();

    void liquidar(int mes, int ano);

    void setMovimientos(Vector movimientos);
}
