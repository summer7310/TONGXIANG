package org.resource.objectclass;

//import java.io.Serializable;

public class PdContainerToCablelineId implements java.io.Serializable {

	private long containerBelongsCableline;
	private long containerToCableline;
	
	public PdContainerToCablelineId() {
		
	}

	public PdContainerToCablelineId(long containerBelongsCableline,
			long containerToCableline) {
		this.containerBelongsCableline = containerBelongsCableline;
		this.containerToCableline = containerToCableline;
	}

	public long getContainerBelongsCableline() {
		return containerBelongsCableline;
	}

	public void setContainerBelongsCableline(long containerBelongsCableline) {
		this.containerBelongsCableline = containerBelongsCableline;
	}

	public long getContainerToCableline() {
		return containerToCableline;
	}

	public void setContainerToCableline(long containerToCableline) {
		this.containerToCableline = containerToCableline;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ (int) (containerBelongsCableline ^ (containerBelongsCableline >>> 32));
		result = prime * result
				+ (int) (containerToCableline ^ (containerToCableline >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof PdContainerToCablelineId))
			return false;
		PdContainerToCablelineId other = (PdContainerToCablelineId) obj;
		return (this.getContainerBelongsCableline() == other
				.getContainerBelongsCableline())
				&& (this.getContainerToCableline() == other
						.getContainerToCableline());
	}
	
	
}
