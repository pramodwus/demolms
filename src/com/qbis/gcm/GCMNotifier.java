package com.qbis.gcm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

/**
 * This class is to used to send all notifications in qbis application.
 * 
 * @author kuldeep.
 *
 */
public class GCMNotifier {
	/**
	 * Instance of {@link Logger}
	 */
	private static final Logger logger = Logger.getLogger(GCMNotifier.class);
	private String apiKey = null;
	private final int DEFAULT_TIME_TO_LIVE = 3;
	private final boolean DEFAULT_DELAY_WHILE_IDLE = false;

	public GCMNotifier(String apiKey) {
		this.apiKey = apiKey;
	}

	/**
	 * Method for send all notifications from qbis application.
	 * 
	 * @return
	 */
	public void sendNotification(Map<Integer, String> devices,
			String notifText, String quoteId) {
		try {
			Sender sender = new Sender(apiKey);
			Message message = new Message.Builder()
					.timeToLive(DEFAULT_TIME_TO_LIVE)
					.delayWhileIdle(DEFAULT_DELAY_WHILE_IDLE)
					.addData("message", notifText).addData("quoteId", quoteId)
					.build();
			List<String> regIds = new ArrayList<String>();
			for (Map.Entry<Integer, String> entry : devices.entrySet()) {
				regIds.add(entry.getValue());
			}
			MulticastResult result = sender.send(message, regIds, 5);
			List<Result> results = result.getResults();
			for (Result item : results) {
				logger.log(Level.DEBUG,
						item.getErrorCodeName() + "---" + item.getMessageId()
								+ "---" + item.getCanonicalRegistrationId());
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	/*
	 * public static void main(String[] args) throws Exception{ GCMNotifier
	 * notif= new GCMNotifier(ConstantUtil.GCMKEY); String red=
	 * "APA91bEWfLdUQhDD4IMcmbgUg9Moy7xqqYFD5qVRi__7dKG2-EP5D8_MPMam0wi1azM3wGKUz6ruBXvoTQiYQ-cGQcT_pTst5p_wXFNgU_5vSDAij9TXyHMhS9iZfr39CkTzmEzNGHeffSdn7f4JxOwknvg37m-Zaw"
	 * ; List<String>device=new ArrayList<String>(); device.add(red);
	 * Map<Integer,String> devices=new HashMap<Integer,String>(); devices.put(1,
	 * red); notif.sendNotification(devices,"TESTsssssssssss","1"); }
	 */

}