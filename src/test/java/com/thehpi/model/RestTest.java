package com.thehpi.model;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.grizzly.http.server.HttpServer;

import com.sun.jersey.api.container.grizzly2.GrizzlyWebContainerFactory;
import com.thehpi.ActivateJersey;
import com.thehpi.model.resource.Resource;

public class RestTest
{
	private static URI getBaseURI()
	{
		return UriBuilder.fromUri("http://localhost/").port(8090).build();
	}

	public static final URI BASE_URI = getBaseURI();

	protected static HttpServer startServer() throws IOException
	{
		final Map<String, String> initParams = new HashMap<String, String>();

		// add the ActivateJersey to make sure the Provider for exception mapping is picked up
		initParams.put("com.sun.jersey.config.property.packages", Resource.class.getPackage().getName() + ";" + ActivateJersey.class.getPackage().getName());

		System.out.println("Starting grizzly...");
		return GrizzlyWebContainerFactory.create(BASE_URI, initParams);
	}

	public static void main(String[] args) throws IOException
	{
		HttpServer httpServer = startServer();
		System.out.println(String.format("Jersey app started with WADL available at " + "%sapplication.wadl\nHit enter to stop it...", BASE_URI, BASE_URI));
		System.in.read();
		httpServer.stop();
	}
}
