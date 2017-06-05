package co.matt.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import co.matt.dao.Attributes;
import co.matt.dao.Device;

public class DevicePojoTest {
	
	Device device;
	
	@Before
	public void setup(){
		List<Attributes> list = new ArrayList<Attributes>();
		Attributes attr = new Attributes("Bluetooth", "0.1");
		list.add(attr);
		
		device = new Device("sony", "K750", "CURLYWURLY", list);
	}
	
	@Test
	public void testDevicePojo() throws Exception {
		assertThat(device.getBrand(), is("sony"));
		assertThat(device.getFullName(), is("sony K750"));
		assertThat(device.getModel(), is("K750"));
		assertThat(device.getFormFactor(), is("CURLYWURLY"));
		assertThat(device.getAttributes().get(0).getName(), is("Bluetooth"));
		assertThat(device.getAttributes().get(0).getValue(), is("0.1"));	
	}	
}
