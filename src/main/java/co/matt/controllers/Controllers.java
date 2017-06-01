package co.matt.controllers;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import co.matt.DeviceListFactory;
import co.matt.DeviceListInterface;

@Path("/device")
public class Controllers {
	
	private DeviceListInterface functions;
	
	public Controllers(){
		this.functions = new DeviceListFactory().getDeviceListInterface();
	}
	
	@Path("/helloworld")
    @GET
    public Response hellowWorld() {
		return Response.ok("Hello World").build();    
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
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDevice(@PathParam("brand") String brand, @PathParam("model") String model, @PathParam("formfactor") String formfactor) {
		functions.addDevice(brand, model, formfactor);  
		return Response.ok().build();
    }
}
