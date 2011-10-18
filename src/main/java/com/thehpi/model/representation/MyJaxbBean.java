package com.thehpi.model.representation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.NONE)
public class MyJaxbBean
{
	@XmlElement
	public String name;

	@XmlAttribute
	public int age;

	public MyJaxbBean()
	{
	} // JAXB needs this

	public MyJaxbBean(String name, int age)
	{
		this.name = name;
		this.age = age;
	}
}
