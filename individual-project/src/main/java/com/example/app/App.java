/**
 * File: App.java
 * Author: Anna Kukova
 * Date: 04/22/2023
 */

package com.example.app;

import java.io.FileNotFoundException;

public class App {
	public static void main(String[] args) throws FileNotFoundException {
		COSCourseScheduler scheduler = new COSCourseScheduler();
		scheduler.setVisible(true);
	}
}
