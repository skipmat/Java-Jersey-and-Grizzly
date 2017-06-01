package co.matt.validation;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;

import co.matt.domain.Attributes;
import co.matt.domain.Device;
import co.matt.domain.FormFactor;

public class DeviceValidation {
	
	private static String removal = "Removing device record beacuse invalid %s: ";
	
	public void validation(List<Device> devices){
		isFullNameValid(devices);
		isBrandAndModelValid(devices);
		isFormFactorValid(devices);
		isAttributeCharacterLengthCorrect(devices);
	}
	
	private void isFullNameValid(List<Device> devices){
		Set<String> set = new HashSet<String>();
		for (Iterator<Device> iterator = devices.iterator(); iterator.hasNext();) {
			Device device = iterator.next();
			if (set.contains(device.getFullName())){
				System.out.println(String.format(removal, "duplicate FullName") + device.getFullName());
				iterator.remove();
			}
			set.add(device.getFullName());	
		}
	}
	
	private void isBrandAndModelValid(List<Device> devices){
		for (Iterator<Device> iterator = devices.iterator(); iterator.hasNext();) {
			Device device = iterator.next();
			if (StringUtils.isBlank(device.getBrand()) || StringUtils.isBlank(device.getModel())){
				System.out.println(String.format(removal, "BrandOrModel") + device.getFullName());
				iterator.remove();
			}
		}
	}
	
	private void isFormFactorValid(List<Device> devices){
		for (Iterator<Device> iterator = devices.iterator(); iterator.hasNext();) {
			Device device = iterator.next();
			if (!EnumUtils.isValidEnum(FormFactor.class, device.getFormFactor())){
				System.out.println(String.format(removal, "FormFactor") + device.getFullName());
				iterator.remove();
			}
		}
	}
	
	private void isAttributeCharacterLengthCorrect(List<Device> devices){
		for (Iterator<Device> iterator = devices.iterator(); iterator.hasNext();) {
			Device device = iterator.next();
			for (Iterator<Attributes> iterator2 = device.getAttributes().iterator(); iterator2.hasNext();) {
				Attributes attribute = iterator2.next();
				if (attribute.getName().length() > 20 || attribute.getValue().length() > 20){
					System.out.println(String.format(removal, "attribute length") + device.getFullName());
					iterator.remove();
				}
			}
		}
	}

}
