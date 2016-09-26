package com.jjt.myutils.domain;

public class Contact {
	private int id;
	private String name;
	private String phone;
	private String email;
	private String QQ;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQQ() {
		return QQ;
	}

	public void setQQ(String qQ) {
		QQ = qQ;
	}

	@Override
	public String toString() {
		return "Contact [name=" + name + ", email=" + email + ", QQ=" + QQ
				+ "]";
	}

}
