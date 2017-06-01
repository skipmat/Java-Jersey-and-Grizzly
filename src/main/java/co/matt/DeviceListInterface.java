package co.matt;

import java.util.List;

import co.matt.domain.Device;

public interface DeviceListInterface {
	
	public void addDevices(List<Device> device);
	
	public void addDevice(String brand, String model, String formfactor);
	
	public List<Device> getAllDevices();
	
	public Device getDevice(String brand, String model);
}
