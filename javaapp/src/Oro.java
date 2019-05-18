// import system.typistart.controller.ORO_Controller;

public class Oro {

	protected Controller controller;

	// public void setView(View aView) {
	// 	//
	// }

	public static void main(String[]args) {
		Oro app = new Oro();
		app.run();
	}

	// ここにメインの処理を書く
	private void run() {
		this.controller = new Controller(); // 初期処理として、ウィンドウを起動する
		this.init();
	}

	private void init() {
		// メニュー画面を表示する
		MenuView menu = new MenuView(this.controller);
		this.controller.setView(menu);
		this.controller.setLayout("menu");
	}

}
