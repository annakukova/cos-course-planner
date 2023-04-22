package com.example.app.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;

public class PrimaryButton extends JButton {

	public PrimaryButton() {
		super();
		setStyle(this);
	}

	public PrimaryButton(String text) {
		super(text);
		setStyle(this);
	}

	public static void setStyle(JButton button) {
		Color buttonBackgroundColor = Color.decode("#8f52d8"); // color for button background
		button.setBackground(buttonBackgroundColor);
		button.setOpaque(true);
		button.setBorderPainted(false);
		button.setFocusPainted(false);
		button.setPreferredSize(new Dimension(200, 40));
		button.setRolloverEnabled(true);
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Arial", Font.BOLD, 14));
	}
}
