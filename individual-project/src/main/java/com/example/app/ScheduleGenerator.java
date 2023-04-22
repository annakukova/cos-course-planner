/**
 * File: ScheduleGenerator.java
 * Author: Anna Kukova
 * Date: 04/22/2023
 */
package com.example.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public abstract class ScheduleGenerator {
	// private static static final int ArrayList = 0;
	// private static List<String> selectedCourses;
	// private static int numSemesters;

	private static ArrayList<ArrayList<Course>> result;
	private static HashSet<Course> addedDependencies;

	// public ScheduleGenerator(List<String> selectedCourses, int numSemesters) {
	// 	this.selectedCourses = selectedCourses;
	// 	this.numSemesters = numSemesters;
	// }

	public static void resetDependencyTable() {
		result = new ArrayList<>();
		addedDependencies = new HashSet<>();
	}

	public static ArrayList<ArrayList<Course>> getResult() {
        return result;
    }

	// new ArrayList<Course>(Arrays.asList(CourseRegistry.getSelectedCourses()));
	public static ArrayList<Course> buildDependencyTable(ArrayList<Course> selectedCourses) {
		if (selectedCourses.size() == 0)
			return new ArrayList<Course>();
		ArrayList<Course> currentSelection = new ArrayList<>();
		while (selectedCourses.size() > 0) {
			Course course = selectedCourses.get(0);
			ArrayList<Course> prerequisites = new ArrayList<Course>(Arrays.asList(course.getPrerequisites()));
			// System.out.println(prerequisites);
			if (prerequisites.size() > 0) {
				ArrayList<Course> coursesToAdd = new ArrayList<Course>(prerequisites.stream().filter(p -> !addedDependencies.contains(p)).collect(Collectors.toList()));
				buildDependencyTable(coursesToAdd);
				// System.out.println(childDependencyTable);
				selectedCourses.removeAll(coursesToAdd);
				for (Course c : coursesToAdd) {
					if (!addedDependencies.contains(c)) {
						addedDependencies.add(c);
						currentSelection.add(c);
					}
				}
			}
			selectedCourses.remove(course);
			if (!addedDependencies.contains(course)) {
				addedDependencies.add(course);
				currentSelection.add(course);
			}
		}
		result.add(currentSelection);
		return currentSelection;
	}

	/**
		Generates a schedule of courses for a certain number of semesters based on a given dependency table.
		The schedule is generated such that each semester has a given max number of courses.
		@param dependencyTable an ArrayList of ArrayLists of Courses representing the dependency table for the courses
		@param semesterCount an integer representing the number of semesters for which to generate a schedule
		@param coursesPerSemester an integer representing the maximum number of courses allowed per semester
		@return an ArrayList of ArrayLists of Courses representing the generated schedule
	*/
	public static ArrayList<ArrayList<Course>> generateSchedule(int semesterCount, int coursesPerSemester) {
		ArrayList<ArrayList<Course>> schedule = new ArrayList<ArrayList<Course>>() {{
			for (int i = 0; i < semesterCount; i++)
				add(new ArrayList<Course>());
		}};
		int currentSemester = 0,
			dependencyTableIndex = 0;
		while (currentSemester < semesterCount - 1 && dependencyTableIndex < result.size()) {
			ArrayList<Course> currentSemesterCourses = schedule.get(currentSemester);
			while (dependencyTableIndex < result.size()) {
				System.out.println(dependencyTableIndex);
				ArrayList<Course> currentSegment = result.get(dependencyTableIndex);
				System.out.println(currentSegment);
				if (!(result.get(dependencyTableIndex).stream().anyMatch(c -> compatibleWith(c, currentSemesterCourses)) &&
				currentSemesterCourses.size() < coursesPerSemester))
					break;

				int freeSlots = coursesPerSemester - currentSemesterCourses.size();
				ArrayList<Course> matches = new ArrayList<Course>(currentSegment.stream().filter(c -> compatibleWith(c, currentSemesterCourses)).collect(Collectors.toList()));
				while (freeSlots > 0 && matches.size() > 0) {
					Course c = matches.get(0);
					matches.remove(c);
					currentSegment.remove(c);
					currentSemesterCourses.add(c);
					freeSlots--;
				}
				if (currentSegment.size() == 0)
					dependencyTableIndex++;
			}
			currentSemester++;
		}
		return schedule;
	}

	/**Checks if a given course is compatible with a list of courses.
	A course is compatible if it has no prerequisites or if all of its prerequisites are already included in the list of courses.
	@param course the course to check for compatibility
	@param courses the list of courses to check against
	@return true if the course is compatible with the list of courses, false otherwise
	*/
	public static boolean compatibleWith(Course course, ArrayList<Course> courses) {
		ArrayList<Course> prerequisites = new ArrayList<Course>(Arrays.asList(course.getPrerequisites()));
		if (course.getPrerequisites().length == 0 || courses.size() == 0)
			return true;
		return !prerequisites.stream().anyMatch(p -> courses.contains(p));
	}

	// public List<String>[] generateSchedules() {
	// 	List<String>[] schedules = new List[numSemesters];
	// 	List<String> remainingCourses = new ArrayList<>(selectedCourses);
	// 	int coursesPerSemester = 3; //something unfinished
	// 	if (remainingCourses.isEmpty()) {
	// 		return schedules;
	// 	}
	// 	for (int i = 0; i < numSemesters; i++) {
	// 		int coursesThisSemester = Math.min(coursesPerSemester, remainingCourses.size());
	// 		List<String> coursesThisSemester = new ArrayList<>();
	// 		for (int j = 0; j < numCoursesThisSemester; j++) {
	// 			String course = selectCourse(remainingCourses, schedules, i);
	// 			coursesThisSemester.add(course);
	// 			remainingCourses.remove(course);
	// 		}
	// 		schedules[i] = coursesThisSemester;
	// 		if (remainingCourses.isEmpty()) {
	// 			break;
	// 		}
	// 	}
	// 	return schedules;
	// }

	// private String selectCourse(List<String> remainingCourses, List<String>[] schedules, int semester) {
	// 	List<String> candidates = new ArrayList<>();
	// 	for (String course : remainingCourses) {
	// 		if (canTakeCourse(course, schedules, semester)) {
	// 			candidates.add(course);
	// 		}
	// 	}
	// 	if (candidates.isEmpty()) {
	// 		throw new RuntimeException("Cannot schedule remaining courses");
	// 	}
	// 	return candidates.get(0);
	// }

	// private boolean canTakeCourse(String course, List<String>[] schedules, int semester) {
	// 	Course[] prerequisites = CourseRegistry.getCourse(course).getPrerequisites();
	// 	if (prerequisites == null) {
	// 		return true;
	// 	}
	// 	for (Course prerequisite : prerequisites) {
	// 		boolean found = false;
	// 		for (int i = 0; i < semester; i++) {
	// 			if (schedules[i] != null && schedules[i].contains(prerequisite.getName())) {
	// 				found = true;
	// 				break;
	// 			}
	// 		}
	// 		if (!found) {
	// 			return false;
	// 		}
	// 	}
	// 	return true;
	// }
}
