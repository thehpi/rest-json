package com.thehpi;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.ext.ContextResolver;
import javax.xml.bind.JAXBContext;

import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.api.json.JSONJAXBContext;
import com.thehpi.model.representation.MyJaxbBean;
import com.thehpi.model.representation.MyJaxbBeans;

// @Provider
public class JAXBContextResolver implements ContextResolver<JAXBContext>
{

	private JAXBContext context;

	private Class<?>[] types = { MyJaxbBean.class, MyJaxbBeans.class };

	public JAXBContextResolver() throws Exception {
		Map<String, String> ns2json = new HashMap<String, String>();
		ns2json.put("http://thehpi.com/mybean", "mybean1");
		ns2json.put("http://thehpi.com/mybeans", "mybeans");
		this.context = new JSONJAXBContext(JSONConfiguration.mappedJettison().xml2JsonNs(ns2json).build(), this.types);
     }

	@Override
	public JAXBContext getContext(Class<?> objectType)
	{
		for (Class<?> type : this.types) {
			if (type == objectType) {
				return this.context;
			}
		}
		return null;
	}
}