package za.co.hqtech.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "task")
public class Task implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank
	@NotNull
	@Size(min = 10, max = 160)
	private String description;

	@NotNull
	@Temporal(TemporalType.DATE)
	private Date dueDate;
	@NotNull
	private Boolean overdue;

	public Task() {
	}

	public Task(Integer id, String description, Date dueDate, Boolean overdue) {
		this.id = id;
		this.description = description;
		this.dueDate = dueDate;
		this.overdue = overdue;
	}

	public Integer getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public Boolean getOverdue() {
		return overdue;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public void setOverdue(Boolean overdue) {
		this.overdue = overdue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((dueDate == null) ? 0 : dueDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((overdue == null) ? 0 : overdue.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (dueDate == null) {
			if (other.dueDate != null)
				return false;
		} else if (!dueDate.equals(other.dueDate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (overdue == null) {
			if (other.overdue != null)
				return false;
		} else if (!overdue.equals(other.overdue))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", description=" + description + ", dueDate=" + dueDate + ", overdue=" + overdue
				+ "]";
	}

}