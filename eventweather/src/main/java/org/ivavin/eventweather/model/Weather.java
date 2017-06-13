package org.ivavin.eventweather.model;

import java.io.Serializable;

public class Weather implements Serializable {

	private static final long serialVersionUID = -8736723502262608114L;

	private String main;
	private String description;
	private String icon;

	public String getMain() {
		return main;
	}

	public void setMain(final String main) {
		this.main = main;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(final String icon) {
		this.icon = icon;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((icon == null) ? 0 : icon.hashCode());
		result = prime * result + ((main == null) ? 0 : main.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Weather other = (Weather) obj;
		if (description == null) {
			if (other.description != null) {
				return false;
			}
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (icon == null) {
			if (other.icon != null) {
				return false;
			}
		} else if (!icon.equals(other.icon)) {
			return false;
		}
		if (main == null) {
			if (other.main != null) {
				return false;
			}
		} else if (!main.equals(other.main)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Weather [main=" + main + ", description=" + description + ", icon=" + icon + "]";
	}

}
