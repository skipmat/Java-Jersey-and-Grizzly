package co.matt;

import java.util.ArrayList;
import java.util.List;

import co.matt.dao.Attributes;
import co.matt.dao.Device;

public class DeviceList implements DeviceListInterface{
	
	public List<Device> devices = new ArrayList<Device>();
	
	public void addDevices(List<Device> device){
		for (Device d : device){
			d.setFullName();
			devices.add(d);
		}	
	}
	
	public void addDevice(String brand, String model, String formfactor){
		devices.add(new Device(brand, model, formfactor, new ArrayList<Attributes>()));
	}
	
	public List<Device> getAllDevices(){
		devices.forEach(device -> sysStuffOut(device));
		return devices;
	}
	
	public Device getDevice(String brand, String model){
		String fullName = brand + " " + model;
		for(Device device : devices){
			if (device.getFullName().equals(fullName)){
				return device;
			}
		}
		return null;	
	}

	public void sysStuffOut(Device device){
		System.out.println("retrieving Device: " + device.getFullName());
	}
}
