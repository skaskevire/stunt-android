package com.stunt.entity;

public enum WheicleEnum {
	TRUCK("Truck On Tracks"), EIGHT_WHEELER("Cross-Country Vehicle");
	private final String name;
	WheicleEnum(String name)
	{
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	public static WheicleEnum getByName(String name)
	{
		for(WheicleEnum we : values())
		{
			if(we.getName().equals(name))
			{
				return we;
			}
		}
		return null;
	}
}
