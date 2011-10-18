package com.thehpi.model.representation;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace = "http://thehpi.com/mybeans")
@XmlAccessorType(XmlAccessType.NONE)
public class MyJaxbBeans
{
	@XmlAttribute
	public int quantity;

	public List<MyJaxbBean> beans;

	public MyJaxbBeans()
	{
	} // JAXB needs this

	public int getQuantity()
	{
		return this.quantity;
	}

	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}

	@XmlElement(name = "bean", namespace = "http://thehpi.com/mybean")
	public List<MyJaxbBean> getBeans()
	{
		if (this.beans == null) {
			this.beans = new ArrayList<MyJaxbBean>();
		}
		return this.beans;
	}

	public void setBeans(List<MyJaxbBean> beans)
	{
		this.beans = beans;
	}
}
