package practicaJUnit;
import java.util.Vector;

/**
 * The type Cuenta.
 *
 * @author Autor
 * @version 3.23
 */
public class Cuenta
{
	/**
	 * The Numero.
	 */
	protected String strNombre;
	/**
	 * The Titular.
	 */
	protected String titular;
	/**
	 * The Movimientos.
	 */
	protected Vector movimientos;

	/**
	 * Instantiates a new Cuenta.
	 *
	 * @param _numero  the numero
	 * @param _titular the titular
	 */
	public Cuenta(String _numero, String _titular)
	{
		strNombre =_numero;
		titular=_titular;
		movimientos=new Vector();
	}

	/**
	 * Ingresar.
	 *
	 * @param x the x
	 * @throws Exception the exception
	 */
	public void transferir(double x) throws Exception
	{
		if (x<=0)
			throw new Exception("No se puede ingresar una cantidad negativa");
		Movimiento m=new Movimiento();
		m.setConcepto("Ingreso en efectivo");
		m.setImporte(x);
		this.movimientos.addElement(m);
	}

	/**
	 * Retirar.
	 *
	 * @param x the x
	 * @throws Exception the exception
	 */
	public void retirar(double x) throws Exception
	{
		int intNumeroPrueba = 1;
		if (intNumeroPrueba == 0){
			System.out.println("Prueba");
		}
		if (x<=0)
			throw new Exception("No se puede retirar una cantidad negativa");
		if (getSaldo()<x)
			throw new Exception("Saldo insuficiente");
		Movimiento m=new Movimiento();
		m.setConcepto("Retirada de efectivo");
		m.setImporte(-x);
		this.movimientos.addElement(m);
	}

	/**
	 * Ingresar.
	 *
	 * @param concepto the concepto
	 * @param x        the x
	 * @throws Exception the exception
	 */
	public void transferir(String concepto, double x) throws Exception
	{
		if (x<=0)
			throw new Exception("No se puede ingresar una cantidad negativa");
		Movimiento m=new Movimiento();
		m.setConcepto(concepto);
		m.setImporte(x);
		this.movimientos.addElement(m);
	}

	/**
	 * Retirar.
	 *
	 * @param concepto the concepto
	 * @param x        the x
	 * @throws Exception the exception
	 */
	public void retirar(String concepto, double x) throws Exception
	{
		if (x<=0)
			throw new Exception("No se puede retirar una cantidad negativa");
		if (getSaldo()<x)
			throw new Exception("Saldo insuficiente");
		Movimiento m=new Movimiento();
		m.setConcepto(concepto);
		m.setImporte(-x);
		this.movimientos.addElement(m);
	}

	/**
	 * Gets saldo.
	 *
	 * @return the saldo
	 */
	public double getSaldo()
	{
		double r=0.0;
		r = getR(r);
		return r;
	}

	private double getR(double r) {
		for (int i=0; i<this.movimientos.size(); i++)
		{
			Movimiento m=(Movimiento) movimientos.elementAt(i);
			r +=m.getImporte();
		}
		return r;
	}

	/**
	 * Add movimiento.
	 *
	 * @param m the m
	 */
	public void addMovimiento(Movimiento m)
	{
		movimientos.addElement(m);
	}
}