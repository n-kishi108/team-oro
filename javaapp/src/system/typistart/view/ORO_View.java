package system.typistart.view;

import system.typistart.controller.ORO_Controller;
import javax.swing.JFrame; /* ウィンドウを起動するため */
import java.awt.Font; /* フォントを指定するため */
import java.util.HashMap; /* フォントを指定するため */
import java.awt.Color; /* 文字色を指定するため */

public class ORO_View {

	public ORO_Controller ctl; // コントローラ
	public HashMap<String, String> fontInfo; // フォント

	// コンストラクタ
	public ORO_View(ORO_Controller controller) {
		this.ctl = controller;
		this.setTheDefaultFontStyle();
	}

	// 使用するフォントのデフォルトスタイルをフィールドとして定義する
	public void setTheDefaultFontStyle() {
		this.fontInfo = new HashMap<>();
		this.fontInfo.put("family", "Font.MONOSPACED"); // 等幅フォント
		this.fontInfo.put("style", "0"); // 特にスタイルは指定しない
		this.fontInfo.put("style", "14"); // 特にスタイルは指定しない
	}

}
