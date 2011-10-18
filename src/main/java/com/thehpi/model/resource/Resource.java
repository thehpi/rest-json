package com.thehpi.model.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.thehpi.model.representation.MyJaxbBean;
import com.thehpi.model.representation.MyJaxbBeans;

@Path("bean")
public class Resource
{
	@GET
	@Produces({ "application/xml", "application/json" })
	public MyJaxbBeans getMyBean()
	{
		MyJaxbBeans beans = new MyJaxbBeans();
		beans.quantity=2;

		MyJaxbBean b = new MyJaxbBean("Agamemnon", 32);
		beans.getBeans().add(b);

		b = new MyJaxbBean("Haha", 29);
		beans.getBeans().add(b);

		return beans;
	}
}
