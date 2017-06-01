package co.matt;

import java.io.IOException;
import java.net.URI;

import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class Main extends ResourceConfig{
	
	private void start() {
		this.packages("co.matt.controllers");
		GrizzlyHttpServerFactory.createHttpServer(URI.create("http://localhost:9000"),this, true);
		DeviceListLoader loader = new DeviceListLoader();
		loader.readInFile();	
	}
	
	public static void main(String[] args) throws IOException {
        new Main().start();    
	}
}
