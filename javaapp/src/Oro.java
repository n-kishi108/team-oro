// import system.typistart.controller.ORO_Controller;

public class Oro {

	protected Controller controller;

	// public void setView(View aView) {
	// 	//
	// }

	public static void main(String[]args) {
		Oro app = new Oro();
		app.run();

		return;
	}

	// ここにメインの処理を書く
	private void run() {
		this.controller = new Controller(); // 初期処理として、ウィンドウを起動する
		this.init();

		return;
	}

	private void init() {
		JFrame frame = new JFrame("タイピストアート");
		JMenuBar menuBar = JMenuBar.menuBar();
		frame.setJMenuBar(aMenuBar);
        frame.getContentPane().add(this);
        frame.addNotify(); 
        frame.pack(); 
		Integer topOffset = frame.getInsets().top + menuBar.getHeight();
		frame.setSize(hoge.WIDTH, hoge.HEIGHT + topOffset);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(100, 100);
        frame.setVisible(true);

		return;
	}

}
