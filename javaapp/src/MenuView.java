import system.typistart.controller.ORO_Controller;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Dimension;

import java.awt.Font;
import java.awt.BorderLayout;
import java.util.HashMap;
import java.awt.Container;

public class MenuView {

	private ORO_Controller controller;
	private Controller ctl;

	public MenuView(ORO_Controller controller) {
		this.controller = controller;
	}

	public MenuView(Controller controller) {
		this.ctl = controller;
	}

	public void paintIOfMenu() {
	}

	public void init(ORO_Controller controller) {
		this.controller = controller;
		JPanel navigation = new JPanel();
		navigation.setLayout(null);
		this.controller.add(navigation, BorderLayout.SOUTH);

		JPanel header = new JPanel();
		header.setLayout(null);
		header.setBackground(new Color(255, 111, 104));
		header.setPreferredSize(new Dimension(this.controller.getFrameWidth(), 50));
		this.controller.add(header, BorderLayout.SOUTH);
	}

}
