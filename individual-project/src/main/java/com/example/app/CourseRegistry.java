/**
 * File: CourseRegistry.java
 * Author: Anna Kukova
 * Date: 04/22/2023
 */
package com.example.app;

import java.util.HashMap;

public abstract class CourseRegistry {
	private static HashMap<String, Course> courses = new HashMap<>();

	public static void addCourse(Course course) {
		courses.put(course.getName(), course);
	}

	public static Course getCourse(String courseName) {
		return courses.get(courseName);
	}

	public static Course[] getSelectedCourses() {
		return courses.values().stream().filter(Course::isSelected).toArray(Course[]::new);
	}

	public static Course[] getSelectedCourses(String category) {
		return courses.values().stream().filter(course -> course.isSelected() && course.getCategory().equals(category)).toArray(Course[]::new);
	}

	public static Course[] getCourseByCategory(String category) {
		return courses.values().stream().filter(course -> course.getCategory().equals(category)).toArray(Course[]::new);
	}
}

// CourseRegistry.getCourse(String courseName);