package B2PS.PaymentTransactionEngine.PINPadDrivers.IngenicoCPX;

import B2PS.PaymentTransactionEngine.PINPadDrivers.IPINPad;
import B2PS.PaymentTransactionEngine.PINPadDrivers.PINPadBase;

public class IngenicoCPXPINPad extends PINPadBase implements IPINPad {

	public enum InitMode {
        /// <summary>The Initialization Data is rebuild and loaded.</summary>
        RebuildAndLoad,
        /// <summary>The Initialization Data is loaded from the local file called IngenicoCPX.dat.</summary>
        LoadFromLocalFile,
        /// <summary>The Initialid Data is loaded from the PINpad's internal file (previous initialization).</summary>
        LoadFromPINPadFile
	}
}
