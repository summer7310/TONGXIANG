package org.resource.objectclass;

// Generated Sep 26, 2013 2:25:34 PM by Hibernate Tools 4.0.0

/**
 * PdWirecablToPipeSegmentId generated by hbm2java
 */
public class PdWirecablToPipeSegmentId implements java.io.Serializable {

	private long segmentHaveWirecable;
	private long wirecableBelongsSegment;

	public PdWirecablToPipeSegmentId() {
	}

	public PdWirecablToPipeSegmentId(long segmentHaveWirecable,
			long wirecableBelongsSegment) {
		this.segmentHaveWirecable = segmentHaveWirecable;
		this.wirecableBelongsSegment = wirecableBelongsSegment;
	}

	public long getSegmentHaveWirecable() {
		return this.segmentHaveWirecable;
	}

	public void setSegmentHaveWirecable(long segmentHaveWirecable) {
		this.segmentHaveWirecable = segmentHaveWirecable;
	}

	public long getWirecableBelongsSegment() {
		return this.wirecableBelongsSegment;
	}

	public void setWirecableBelongsSegment(long wirecableBelongsSegment) {
		this.wirecableBelongsSegment = wirecableBelongsSegment;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PdWirecablToPipeSegmentId))
			return false;
		PdWirecablToPipeSegmentId castOther = (PdWirecablToPipeSegmentId) other;

		return (this.getSegmentHaveWirecable() == castOther
				.getSegmentHaveWirecable())
				&& (this.getWirecableBelongsSegment() == castOther
						.getWirecableBelongsSegment());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int)this.getSegmentHaveWirecable();
		result = 37 * result + (int)this.getWirecableBelongsSegment();
		return result;
	}

}
