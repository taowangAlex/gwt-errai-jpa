package com.cnpc.video.client.shared;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.jboss.errai.common.client.api.annotations.Portable;

@NamedQueries({@NamedQuery(name="allPerson",query="SELECT person FROM Person person ORDER BY person.name")})
@Portable
@Entity
public class Person implements Comparable<Person> {

	@Id @GeneratedValue
	private Long id;

	private String name;
	private String age;
	private String phoneNum;
	private String address;

	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAge() {
		return age;
	}


	public void setAge(String age) {
		this.age = age;
	}


	public String getPhoneNum() {
		return phoneNum;
	}


	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	@Override
	public int compareTo(Person o) {
		if (name == o.name)
			return 0;
		if (name == null && o.name != null)
			return -1;
		if (name != null && o.name == null)
			return 1;
		return name.compareTo(o.getName());
	}

}
