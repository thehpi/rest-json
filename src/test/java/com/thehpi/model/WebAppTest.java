package com.thehpi.model;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.MediaType;

import org.junit.Before;
import org.junit.Test;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.ClientResponse.Status;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.LoggingFilter;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;
import com.thehpi.model.representation.MyJaxbBeans;

public class WebAppTest extends JerseyTest {

	private static String JSON = MediaType.APPLICATION_JSON;

	private static String XML = MediaType.APPLICATION_XML;

	private WebResource webResource;

    public WebAppTest() throws Exception {
		super(new WebAppDescriptor.Builder("com.thehpi").
			initParam("com.sun.jersey.spi.container.ContainerResponseFilters", "com.sun.jersey.server.linking.LinkFilter").
			build());
    }

	@Before
	public void setup()
	{
		this.webResource = this.resource();
		this.webResource.addFilter(new LoggingFilter());
	}

	private <T> T unmarshal(Class<T> clazz, String path)
	{
		return this.unmarshal(clazz, path, MediaType.APPLICATION_XML);
	}

	private <T> T unmarshal(Class<T> clazz, String path, String mediaType)
	{
		JSONConfiguration.natural().build();

		ClientResponse response = this.webResource.path(path).accept(mediaType).get(ClientResponse.class);
		assertEquals(Status.OK, response.getClientResponseStatus());

		T result = response.getEntity(clazz);

		return result;
	}

	@Test
	public void testBean() throws Exception
	{
		String path = "bean";
		MyJaxbBeans bean = this.unmarshal(MyJaxbBeans.class, path);
		assertEquals(2, bean.getBeans().size());

		bean = this.unmarshal(MyJaxbBeans.class, path, MediaType.APPLICATION_JSON);
		assertEquals(2, bean.getBeans().size());
		System.out.println(bean.getBeans().get(0).name);
    }
}
