package entities;
// TODO: Auto-generated Javadoc
/**
 * The Class PaymentAbstract.
 */
public class AbstractPayment extends AbstractJsonToString {
	
	/** The type. */
	private Class<? extends AbstractPayment> type;

	/**
	 * Instantiates a new abstract payment.
	 */
	protected AbstractPayment() {}

	/**
	 * Instantiates a new abstract payment.
	 *
	 * @param type the type
	 */
	public AbstractPayment(Class<? extends AbstractPayment> type) 
	{
		this.type = type;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public Class<? extends AbstractPayment> getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(Class<? extends AbstractPayment> type) {
		this.type = type;
	}
}
