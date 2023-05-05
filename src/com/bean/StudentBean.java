package com.bean;

public class StudentBean {
	@Override
	public String toString() {
		return "StudentBean [maths=" + maths + ", physics=" + physics + ", chemistry=" + chemistry + ", english="
				+ english + ", name=" + name + "]";
	}
	private int maths,physics,chemistry,english;
	private String name;
	public int getMaths() {
		return maths;
	}
	public void setMaths(int maths) {
		this.maths = maths;
	}
	public int getPhysics() {
		return physics;
	}
	public void setPhysics(int physics) {
		this.physics = physics;
	}
	public int getChemistry() {
		return chemistry;
	}
	public void setChemistry(int chemistry) {
		this.chemistry = chemistry;
	}
	public int getEnglish() {
		return english;
	}
	public void setEnglish(int english) {
		this.english = english;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
