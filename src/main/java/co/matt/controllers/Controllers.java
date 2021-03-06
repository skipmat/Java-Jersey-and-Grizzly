package co.matt.controllers;

import java.io.File;
import java.net.URL;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;

import co.matt.DeviceListFactory;
import co.matt.DeviceListInterface;
import co.matt.dao.Device;

@Path("/device")
public class Controllers {
	
	private DeviceListInterface functions;
	
	public Controllers(){
		this.functions = new DeviceListFactory().getDeviceListInterface();
	}
	
	@Path("/home")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response hellowWorld() {
		URL url = getClass().getResource("../views/homepage.html");
		File file = new File(url.getPath());
		return Response.ok(file).build();
    }
	
	@Path("/getAllDevices")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDevices() {
		return Response.ok(functions.getAllDevices()).build();       
    }
	
	@Path("/getDevice/{brand}/{model}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDevice(@PathParam("brand") String brand, @PathParam("model") String model) {
		return Response.ok(functions.getDevice(brand, model)).build();       
    }
	
	@Path("/addDevice/{brand}/{model}/{formfactor}")
	@PUT
    public Response getDevice(@PathParam("brand") String brand, @PathParam("model") String model, @PathParam("formfactor") String formfactor) {
		if(brand.equals("undefined") || model.equals("undefined") || formfactor.equals("undefined")){
			return Response.serverError().build();
		}
		functions.addDevice(brand, model, formfactor);  
		return Response.ok().build();
    }
	
	@Path("/addDevices")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
	public Response getDevices(List<Device> devices){
		functions.addDevices(devices);
		return Response.ok().build();
    }
}
