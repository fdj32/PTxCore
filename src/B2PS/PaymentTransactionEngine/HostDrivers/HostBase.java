package B2PS.PaymentTransactionEngine.HostDrivers;

public class HostBase implements AutoCloseable {

	@Override
	public void close() throws Exception {

	}

	protected enum AcquirerSslType {
        Optional,
        Required,
        None
	}
	
	
	

}
