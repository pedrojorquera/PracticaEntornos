package practicaJUnit;

import java.util.Vector;
import java.util.Date;

/**
 * The type Credito.
 */
public class Credito extends Tarjeta implements InterfaceCredito {
	/**
	 * The Credito.
	 */
	protected double credito;
	private Vector movimientos;

	/**
	 * Instantiates a new Credito.
	 *
	 * @param _numero         the numero
	 * @param _titular        the titular
	 * @param _fechaCaducidad the fecha caducidad
	 * @param _credito        the credito
	 */
	public Credito(String _numero, String _titular, Date _fechaCaducidad, double _credito)
	{
		super(_numero, _titular, _fechaCaducidad);
		credito=_credito;
		setMovimientos(new Vector());
	}
	public void retirar(double x) throws Exception
	{
		Movimiento m=new Movimiento();
		m.setConcepto("Retirada en cajero autom�tico");
		x=(x*0.05<3.0 ? 3 : x*0.05); // A�adimos una comisi�n de un 5%, m�nimo de 3 euros.
		m.setImporte(x);
		getMovimientos().addElement(m);
		if (x>getCreditoDisponible())
			throw new Exception("Cr�dito insuficiente");
	}
	public void ingresar(double x) throws Exception
	{
		Movimiento m=new Movimiento();
		m.setConcepto("Ingreso en cuenta asociada (cajero autom�tico)");
		m.setImporte(x);
		getMovimientos().addElement(m);
		cuentaAsociada.transferir(x);
	}
	public void pagoEnEstablecimiento(String datos, double x) throws Exception
	{
		Movimiento m=new Movimiento();
		m.setConcepto("Compra a cr�dito en: " + datos);
		m.setImporte(x);
		getMovimientos().addElement(m);
	}
	public double getSaldo()
	{
		double r=0.0;
		for (int i = 0; i< this.getMovimientos().size(); i++)
		{
			Movimiento m=(Movimiento) getMovimientos().elementAt(i);
			r+=m.getImporte();
		}
		return r;
	}

	/**
	 * Gets credito disponible.
	 *
	 * @return the credito disponible
	 */
	@Override
	public double getCreditoDisponible()
	{
		return credito-getSaldo();
	}

	/**
	 * Liquidar.
	 *
	 * @param mes the mes
	 * @param ano   the a
	 */
	@Override
	public void liquidar(int mes, int ano)
	{
		Movimiento liq=new Movimiento();
		liq.setConcepto("Liquidaci�n de operaciones tarj. cr�dito, " + (mes+1) + " de " + (ano+1900));
		double r=0.0;
		for (int i = 0; i< this.getMovimientos().size(); i++)
		{
			Movimiento m=(Movimiento) getMovimientos().elementAt(i);
			if (m.getFecha().getMonth()+1==mes && m.getFecha().getYear()+1900==ano)
				r+=m.getImporte();
		}
		liq.setImporte(r);
		if (r!=0)
			cuentaAsociada.addMovimiento(liq);
	}

	/**
	 * The Movimientos.
	 */
	public Vector getMovimientos() {
		return movimientos;
	}

	@Override
	public void setMovimientos(Vector movimientos) {
		this.movimientos = movimientos;
	}
}
