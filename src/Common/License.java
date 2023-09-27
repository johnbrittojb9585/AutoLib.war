package Common;

import org.joda.time.DateTime;
import org.joda.time.Days;

public class License {

	// Licence Expiry Date
	public static final DateTime TrialExpiryDate = new DateTime(2100, 02, 17, 0,
			0, 0, 0);

	public static boolean valid() {
		int i = Days.daysBetween(new DateTime(), TrialExpiryDate).getDays();
		System.out.println("Valid Days :::: Trial Date Expired" + i);
		return i > 0 ? true : false;
	}

}