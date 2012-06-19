package de.merten.umgr.backend;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class ConversionUtils {

	private static final BigDecimal DIVISOR_HOUR_KILO = new BigDecimal(60000);
	
	public static BigDecimal fromWminTokWh(long Wmin) {
		
		return new BigDecimal(Wmin).divide(DIVISOR_HOUR_KILO, 2, RoundingMode.HALF_UP);
		
	}
	
	
	private ConversionUtils() {}
	
	
}
