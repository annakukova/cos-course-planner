/**
 * File: ActionPanel.java
 * Author: Anna Kukova
 * Date: 04/22/2023
 */

package com.example.app.components;

import java.awt.event.ActionListener;
import java.util.Map.Entry;
import java.util.HashMap;

public class ActionPanel extends Panel {
	public ActionPanel() {
		super();
		setStyle(this);
	}

	public ActionPanel(String[] buttonTexts, ActionListener[] listeners) {
		super();
		setStyle(this);
		for (int i = 0; i < buttonTexts.length; i++)
			this.addButton(buttonTexts[i], listeners[i]);
	}

	public ActionPanel(String text, ActionListener listener) {
		super();
		setStyle(this);
		PrimaryButton button = new PrimaryButton(text);
		this.addButton(button, listener);
	}

	public ActionPanel(HashMap<String, ActionListener> buttons) {
		super();
		setStyle(this);
		for (Entry<String, ActionListener> entry : buttons.entrySet())
			this.addButton(entry.getKey(), entry.getValue());
	}

	public void addButton(PrimaryButton button, ActionListener listener) {
		button.addActionListener(listener);
		this.add(button);
	}

	public void addButton(String text, ActionListener listener) {
		PrimaryButton button = new PrimaryButton(text);
		this.addButton(button, listener);
	}

	public static void setStyle(Panel panel) {
		panel.setAlignmentY(BOTTOM_ALIGNMENT);
	}
}
