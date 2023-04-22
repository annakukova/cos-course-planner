/**
 * File: ComboBox.java
 * Author: Anna Kukova
 * Date: 04/22/2023
 */

package com.example.app.components;

import javax.swing.JComboBox;

public class ComboBox extends JComboBox<String> {
	public ComboBox() {
		super();
		setStyle(this);
	}

	public ComboBox(String[] items) {
		super();
		for (String i : items)
			addItem(i);
	}

	public static void setStyle(ComboBox comboBox) {
		
	}
}
