/**
 * File: CheckBox.java
 * Author: Anna Kukova
 * Date: 04/22/2023
 */
package com.example.app.components;

import javax.swing.JCheckBox;

public class CheckBox extends JCheckBox {
	public CheckBox() {
		super();
		setStyle(this);
	}

	public CheckBox(String text) {
        super(text);
        setStyle(this);
    }

	public static void setStyle(JCheckBox checkBox) {
		checkBox.setOpaque(true);
	}
}
