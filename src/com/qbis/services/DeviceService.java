package com.qbis.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.qbis.common.ConstantUtil;
import com.qbis.common.QueryManager;
import com.qbis.common.QueryManager.QueryStrings;
import com.qbis.model.Device;

/**
 * This is used for performing all operations related to device of application's
 * user.
 * 
 * @author Ankur Kumar
 *
 */
@Component
public class DeviceService {
	/**
	 * This method would be called on initialization of application in the
	 * user's device.
	 * 
	 * @param device
	 * @return Map<String,Object
	 */
	public Map<String, Object> initialization(Device device) {
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			Integer id = QueryManager.execuateUpdate(
					QueryStrings.INSERT_DEVICE_DETAILS,
					new Object[] { device.getDevice_id(),
							device.getDevice_name(), device.getDevice_os(),
							device.getApp_version(),
							device.getRegister_pn_token(),
							device.getOs_version(), device.getNetwork() });

			if (id > 0) {
				map.put("status", "200");
				map.put("msg", "OK");
				map.put("is_new_app_version_available",
						ConstantUtil.NEW_VERSION_AVAILABLE);
				map.put("terms_condition", ConstantUtil.SERVER_IP
						+ "qbis/pages/terms_condition.jsp");
				map.put("faq", ConstantUtil.SERVER_IP + "qbis/pages/faq.jsp");
			} else {
				map.put("status", 401);
				map.put("msg", "Data not saved");
			}
		} catch (Exception e) {
			map.put("status", 401);
			map.put("msg", "something went wrong");
			e.printStackTrace();
		}

		return map;
	}
}
