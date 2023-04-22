/**
 * File: Course.java
 * Author: Anna Kukova
 * Date: 04/22/2023
 */
package com.example.app;

import java.util.HashSet;

public class Course {
	private String name;
	private String category;
	private boolean isSelected;
	private final HashSet<Course> prerequisites = new HashSet<Course>();

	public Course(String name, String category, String[] prerequisites) {
        this.name = name;
        this.category = category;
		for (String p : prerequisites)
			this.prerequisites.add(CourseRegistry.getCourse(p));
    }

	public String getName() {
		return name;
	}

	public String getCategory() {
        return category;
    }

	public boolean isSelected() {
        return isSelected;
    }

	public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

	public Course[] getPrerequisites() {
		return prerequisites.toArray(new Course[prerequisites.size()]);
	}

	@Override
	public String toString() {
        return name;
    }
}
