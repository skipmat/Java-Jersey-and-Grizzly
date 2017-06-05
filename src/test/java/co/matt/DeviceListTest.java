package co.matt;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import co.matt.dao.Attributes;
import co.matt.dao.Device;

public class DeviceListTest {
	
	public DeviceList unit;
	
	@Before
	public void setup(){
		DeviceListLoader loader = new DeviceListLoader();
		
		unit = new DeviceList();
		loader.readInFile(unit);
	}
	
	@Test
	public void testCanRetrieveAllDevices() throws Exception {	
		List<Device> list = unit.getAllDevices();
		
		for(Device device : list){
			assertThat(device.getBrand(), is(not(nullValue())));
			assertThat(device.getModel(), is(not(nullValue())));
			assertThat(device.getFormFactor(), is(not(nullValue())));
			assertThat(device.getFullName(), is(not(nullValue())));
			for (Attributes attribute : device.getAttributes()){
				assertThat(attribute.getName(), is(not(nullValue())));
				assertThat(attribute.getValue(), is(not(nullValue())));
			}
		}		
		assertThat(list.size(), is(3));
	}
	
	@Test
	public void testCanRetrieveADevice() throws Exception {
		Device device = unit.getDevice("Phony", "X11");
		
		assertThat(device.getBrand(), is("Phony"));
		assertThat(device.getModel(), is("X11"));
		assertThat(device.getFormFactor(), is("SMARTPHONE"));
	}
	
	@Test
	public void testCanAddAnIndividualDevice() throws Exception {
		unit.addDevice("Brand", "Model", "Formfactor");
		
		assertThat(unit.getAllDevices().size(), is(4));
	}
}
