/**
 * File: COSCourseScheduler.java
 * Author: Anna Kukova
 * Date: 04/22/2023
 */

package com.example.app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.BoxView;
import org.yaml.snakeyaml.Yaml;
import com.example.app.components.ActionPanel;
import com.example.app.components.CheckBox;
import com.example.app.components.ComboBox;
import com.example.app.components.Panel;
import com.example.app.components.PrimaryButton;

public class COSCourseScheduler extends JFrame {
	private final String generateScheduleLabel = "Generate Schedule";
	private final String savePastCoursesLabelText = "Save Past Courses Selection";
	private final String switchToPastCoursesLabelText = "Switch to Past Courses Selection";
	private final String[] categories = new String[] {
		"required",
		"elective group 1",
		"elective group 2",
		"elective group 3",
	};
	private final String[] categoryLabels = new String[] {
		"Required Courses",
		"Elective Group 1",
		"Elective Group 2",
		"Elective Group 3",
	};

	private boolean selectingPreviousCourses = true;
	private HashMap<String, ArrayList<String>> selectedCourses = new HashMap<>();

	private HashMap<String, CheckBox[]> categoryCheckboxes = new HashMap<>();
	private HashMap<String, Integer> categoryLimits = new HashMap<>();
	private HashMap<String, String[]> categoryButtonLabels = new HashMap<>();
	private HashMap<String, ActionListener[]> categoryButtonActions = new HashMap<>();

	private ComboBox semesterSelection;
	private JTabbedPane tabbedPane;
	private JList<String> semesterList;
	private PrimaryButton bottomButton;
	private boolean[] bottomButtonVisibility = new boolean[] { false, true, true, true, true, true };

	private ArrayList<ArrayList<Course>> schedule;

/**
Constructs a new COSCourseScheduler object.
@throws FileNotFoundException if the config file with courses is not found.
*/
	public COSCourseScheduler() throws FileNotFoundException {
		// read config
		Yaml yaml = new Yaml();
		InputStream configStream = new FileInputStream("./data/config.yml");
		Map<String, Object> config = yaml.load(configStream);

		for (Map<String, Object> cpc : (List<Map<String, Object>>) config.get("maxCoursesPerCategory")) {
			String name = (String) cpc.get("name");
			int max = (int) cpc.get("amount");
			categoryLimits.put(name, max);
		}

		InputStream coursesStream = new FileInputStream("./data/courses.yml");
		List<Map<String, Object>> courses = yaml.load(coursesStream);

		HashMap<String, ArrayList<String>> coursesByCategory = new HashMap<>();

		for (Map<String, Object> c : courses) {
			String name = (String) c.get("name");
			String category = (String) c.get("category");
			ArrayList<String> prerequisites = (ArrayList<String>) c.get("prerequisites");
			if (prerequisites == null)
				prerequisites = new ArrayList<String>();
			Course course = new Course(name, category, prerequisites.toArray(String[]::new));
			if (!coursesByCategory.containsKey(category))
				coursesByCategory.put(category, new ArrayList<String>());
			coursesByCategory.get(category).add(name);
			CourseRegistry.addCourse(course);
		}

		setBackground(Color.WHITE);
		setTitle((String) config.get("title"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize((int) config.get("width"), (int) config.get("height"));
		setLocationRelativeTo(null);

/**creates UI components
*/
		tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Welcome", createWelcomePanel());
		tabbedPane.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				bottomButton.setVisible(bottomButtonVisibility[tabbedPane.getSelectedIndex()]);
				if (isLastTab())
					bottomButton.setText(generateScheduleLabel);
				else
					bottomButton.setText(
							selectingPreviousCourses ? savePastCoursesLabelText : switchToPastCoursesLabelText);
			}
		});

/**Sets up action for each tab
*/
		ActionListener previousButtonAction = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				tabbedPane.setSelectedIndex(tabbedPane.getSelectedIndex() - 1);
			}
		},
				nextButtonAction = new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						tabbedPane.setSelectedIndex(tabbedPane.getSelectedIndex() + 1);
					}
				};
		ActionListener[] genericCategoryButtons = new ActionListener[] { previousButtonAction, nextButtonAction };

		categoryButtonLabels.put("required", new String[] { "< Back", "Elective Group 2 >" });
		categoryButtonActions.put("required", genericCategoryButtons);

		categoryButtonLabels.put("elective group 1", new String[] { "< Required Courses", "Elective Group 2 >" });
		categoryButtonActions.put("elective group 1", genericCategoryButtons);

		categoryButtonLabels.put("elective group 2", new String[] { "< Elective Group 1", "Elective Group 3 >" });
		categoryButtonActions.put("elective group 2", genericCategoryButtons);

		categoryButtonLabels.put("elective group 3", new String[] { "< Elective Group 2", "Next >" });
		categoryButtonActions.put("elective group 3", genericCategoryButtons);

/**creates tabs
*/
		for (int i = 0; i < categories.length; i++) {
			String category = categories[i],
					label = categoryLabels[i];
			String[] categoryCourses = coursesByCategory.get(category).toArray(String[]::new);
			String[] buttonLabels = categoryButtonLabels.get(category);
			ActionListener[] buttonActions = categoryButtonActions.get(category);
			Panel tabPanel = new Panel(),
					checkBoxesPabel = createCheckBoxPanel(label, categoryCourses, category);
			;
			tabPanel.setLayout(new BoxLayout(tabPanel, BoxView.Y_AXIS));
			tabPanel.add(checkBoxesPabel);
			tabPanel.add(new ActionPanel(buttonLabels, buttonActions));
			tabbedPane.addTab(label, tabPanel);
		}
		Panel semesterPanel = createSemesterPanel((int) config.get("semesters"));
		tabbedPane.addTab("Schedule", semesterPanel);
		add(tabbedPane, BorderLayout.CENTER);

		bottomButton = new PrimaryButton(savePastCoursesLabelText);
		bottomButton.setVisible(false);
		bottomButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (isLastTab()) {
					ScheduleGenerator.resetDependencyTable();
					ScheduleGenerator.buildDependencyTable(new ArrayList<Course>(Arrays.asList(CourseRegistry.getSelectedCourses())));
					schedule = ScheduleGenerator.generateSchedule(
						(int) config.get("semesters"),
						(int) config.get("coursesPerSemester")
					);
					updateSemesterList();
					return;
				}
				if (selectingPreviousCourses)
					switchToCurrentCourses();
				else
					switchToPreviousCourses();
			}
		});

		add(bottomButton, BorderLayout.SOUTH);
	}
/**creates Welcome panel
*/
	private Panel createWelcomePanel() {
		Panel panel = new Panel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(BorderFactory.createTitledBorder("Welcome to COS Course Scheduler"));
		panel.add(new JLabel("Please select a course to generate a schedule for."));
		panel.add(new JLabel("You can select multiple courses at once."));
		return panel;
	}

/**creates panels for courses
*/
	private Panel createCheckBoxPanel(String title, String[] courseNames, String category) {
		Panel panel = new Panel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setAlignmentX(CENTER_ALIGNMENT);
		CheckBox[] checkboxes = new CheckBox[courseNames.length];
		for (int i = 0; i < courseNames.length; i++) {
			checkboxes[i] = new CheckBox(courseNames[i]);
			checkboxes[i].addActionListener(checkBoxPanelListener(category));
			checkboxes[i].setBackground(getBackground());
			panel.add(checkboxes[i]);
		}
		categoryCheckboxes.put(category, checkboxes);
		return panel;
	}

	private Panel createSemesterPanel(int semesterCount) {
		Panel panel = new Panel();
		// panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		JLabel label = new JLabel("Remaining semesters:");
		panel.add(label);
		String[] semesters = new String[semesterCount];
		for (int i = 0; i < semesters.length; i++)
			semesters[i] = "Semester " + (i + 1);
		semesterSelection = new ComboBox(semesters);
		semesterSelection.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				updateSemesterList();
			}
		});
		panel.add(semesterSelection);

		semesterList = new JList<String>();
		panel.add(semesterList, BorderLayout.CENTER);
		return panel;
	}

	private ActionListener checkBoxPanelListener(String category) {
		int max = categoryLimits.get(category);

		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int alreadySelected = CourseRegistry.getSelectedCourses(category).length;
				CheckBox checkbox = (CheckBox) e.getSource();
				if (max > -1)
					if (alreadySelected >= max && checkbox.isSelected()) {
						checkbox.setSelected(false);
						return;
					}
				Course course = CourseRegistry.getCourse(checkbox.getText());
				course.setSelected(checkbox.isSelected());
			}
		};
	}
/**Switches to the courses that are yet to be taken
*/
	private void switchToCurrentCourses() {
		for (String category : categories) {
			CheckBox[] checkboxes = Arrays.asList(categoryCheckboxes.get(category))
					.stream()
					.filter(CheckBox::isSelected)
					.toArray(CheckBox[]::new);
			for (CheckBox cb : checkboxes) {
				cb.setVisible(false);
				cb.setSelected(false);
			}
			String[] courses = Arrays.asList(CourseRegistry.getSelectedCourses(category))
					.stream()
					.map(course -> course.getName())
					.toArray(String[]::new);
			selectedCourses.put(category, new ArrayList<String>(Arrays.asList(courses)));
		}
		selectingPreviousCourses = false;
		bottomButton.setText(switchToPastCoursesLabelText);
	}
/**
Switches to the previously selected courses.
*/
	private void switchToPreviousCourses() {
		for (String category : categories) {
			CheckBox[] currentlySelected = Arrays.asList(categoryCheckboxes.get(category))
					.stream()
					.filter(cb -> cb.isVisible() && cb.isSelected())
					.toArray(CheckBox[]::new);
			CheckBox[] previouslySelected = Arrays.asList(categoryCheckboxes.get(category))
					.stream()
					.filter(cb -> !cb.isVisible())
					.toArray(CheckBox[]::new);
			for (CheckBox cb : currentlySelected) {
				CourseRegistry.getCourse(cb.getText()).setSelected(false);
				cb.setSelected(false);
			}
			for (CheckBox cb : previouslySelected) {
				CourseRegistry.getCourse(cb.getText()).setSelected(true);
				cb.setSelected(true);
				cb.setVisible(true);
			}
		}
		selectingPreviousCourses = true;
		bottomButton.setText(savePastCoursesLabelText);
	}

	private void updateSemesterList() {
		if (schedule != null)
			semesterList.setListData(schedule.get(semesterSelection.getSelectedIndex()).stream().map(Course::toString).toArray(String[]::new));
	}

	private boolean isLastTab() {
		return tabbedPane.getSelectedIndex() == tabbedPane.getTabCount() - 1;
	}
}
