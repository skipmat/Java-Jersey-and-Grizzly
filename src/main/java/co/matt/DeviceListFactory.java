package co.matt;

public class DeviceListFactory {
	
	public static DeviceListInterface deviceList = new DeviceList();
	
	public DeviceListInterface getDeviceListInterface(){
		return deviceList;
	}

}
