package co.matt.validation;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import co.matt.DeviceListFactory;
import co.matt.DeviceListInterface;
import co.matt.DeviceListLoader;
import co.matt.domain.Attributes;
import co.matt.domain.Device;

public class DeviceValidationTest {
	
	public DeviceValidation unit;
	public DeviceListInterface deviceList;
	
	@Before
	public void setup(){
		deviceList = new DeviceListFactory().getDeviceListInterface();
		DeviceListLoader loader = new DeviceListLoader();
		unit = new DeviceValidation();
		loader.readInFile();
	}
	
	@Test
	public void testDeviceValidatedIsSuccessful() throws Exception {
		unit.validation(deviceList.getAllDevices());
		
		assertThat(deviceList.getAllDevices().size(), is(3));
	}
	
	@Test
	public void testDeviceIsRemovedForDuplicateDeviceFullNames() throws Exception {
		List<Attributes> list = new ArrayList<Attributes>();
		Attributes attr = new Attributes("Bluetooth", "0.1");
		list.add(attr);
	
		deviceList.addDevices(Arrays.asList(new Device("sony", "K750", "CANDYBAR", list), new Device("sony", "K750", "CANDYBAR", list)));
		unit.validation(deviceList.getAllDevices());
		
		assertThat(deviceList.getAllDevices().size(), is(4));
	}
	
	@Test
	public void testDeviceIsRemovedForMissingBrandAndModel() throws Exception {
		List<Attributes> list = new ArrayList<Attributes>();
		Attributes attr = new Attributes("Bluetooth", "0.1");
		list.add(attr);
	
		deviceList.addDevices(Arrays.asList(new Device("", "K750", "CANDYBAR", list), new Device("sony", "", "CANDYBAR", list)));
		unit.validation(deviceList.getAllDevices());
		
		assertThat(deviceList.getAllDevices().size(), is(3));
	}
	
	@Test
	public void testDeviceIsRemovedForInvalidFormFactor() throws Exception {
		List<Attributes> list = new ArrayList<Attributes>();
		Attributes attr = new Attributes("Bluetooth", "0.1");
		list.add(attr);
			
		deviceList.addDevices(Arrays.asList(new Device("kony", "K750", "SOMETHING", list), new Device("sony", "KK750", "ANOTHERTHING", list)));
		unit.validation(deviceList.getAllDevices());
		
		assertThat(deviceList.getAllDevices().size(), is(3));
	}
	
	@Test
	public void testDeviceIsRemovedForExceedingAttributeNameCharacterLength() throws Exception {
		List<Attributes> list = new ArrayList<Attributes>();
		Attributes attr = new Attributes("BluetoothBluetoothBluetoothBluetoothBluetoothBluetoothBluetooth", "0.1");
		list.add(attr);
	
		deviceList.addDevices(Arrays.asList(new Device("sony", "K750", "CANDYBAR", list)));
		unit.validation(deviceList.getAllDevices());
		
		assertThat(deviceList.getAllDevices().size(), is(3));
	}
	
	@Test
	public void testDeviceIsRemovedForExceedingAttributeValueCharacterLength() throws Exception {
		List<Attributes> list = new ArrayList<Attributes>();
		Attributes attr = new Attributes("Bluetooth", "BluetoothBluetoothBluetoothBluetoothBluetoothBluetoothBluetooth");
		list.add(attr);
	
		deviceList.addDevices(Arrays.asList(new Device("sony", "K750", "CANDYBAR", list)));
		unit.validation(deviceList.getAllDevices());
		
		assertThat(deviceList.getAllDevices().size(), is(3));
	}

}
