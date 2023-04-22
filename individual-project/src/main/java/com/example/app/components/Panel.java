/**
 * File: Panel.java
 * Author: Anna Kukova
 * Date: 04/22/2023
 */

package com.example.app.components;

import java.awt.Color;
import java.awt.Container;
import java.awt.LayoutManager;
import javax.swing.JPanel;

public class Panel extends JPanel {
	public Panel() {
        super();
		setStyle(this);
    }

	public Panel(LayoutManager manager) {
		super(manager);
        setStyle(this);
	}

	public static void setStyle(Panel panel) {
		Container parent = panel.getParent();
		if (parent != null)
			panel.setBackground(panel.getParent().getBackground());
		else
			panel.setBackground(Color.WHITE);
	}
}
