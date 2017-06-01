package co.matt;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import co.matt.domain.Device;
import co.matt.validation.DeviceValidation;

public class DeviceListLoader {
	
	public void readInFile(DeviceListInterface deviceList) {
		DeviceValidation validator = new DeviceValidation();
		List<Device> devices = new ArrayList<Device>();
		Gson gson = new Gson();
		
		try {
			InputStreamReader reader = new InputStreamReader(DeviceList.class.getResourceAsStream("/devices.json"));
			devices = gson.fromJson(reader, new TypeToken<List<Device>>(){}.getType());					
			setFullNameOnDeviceList(devices);
			validator.validation(devices);	
			deviceList.addDevices(devices);				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void readInFile(){
		readInFile(new DeviceListFactory().getDeviceListInterface());
	}
	
	public void setFullNameOnDeviceList(List<Device> devices){
		for(Device device : devices){
			device.setFullName();
		}
	}
}
