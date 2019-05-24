import java.util.ConcurrentModificationException;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import utility.ImageUtility;

public class MenuView extends ORO_View {



	/**
	 * 内容物の表示位置(原点)を保持するフィールド。
	 */
	private Point2D.Double originPoint;

	/**
	 * 内容物の拡大縮小率を保持するフィールド。
	 */
	private Point2D.Double scaleFactor;

	/**
	 * 指定されたモデルとコントローラと自分（ビュー）とでMVCを構築するコンストラクタ。
	 * @param aModel このビューのモデル
	 * @param aController このビューのコントローラ
	 */
	public ResultView(ResultModel aModel, PaneController aController) {
		super(aModel, aController);
		this.intialize();
	}

	/**
	 * 自分のモデルを応答する。
	 * @return このビューのモデル
	 */
	public PaneModel getModel() {
		return (PaneModel)(this.model);
	}

  /**
	 * メニューバーを作成して応答する。
	 * @return メニューバーのインスタンス
	 */
	public JMenuBar menuBar() {
		// メニューバーを作成する。
		JMenuBar aMenuBar = new JMenuBar();

		// メニューバーに加えるプルダウンメニューとリスナーを束縛する一時変数である。
		JMenu aMenu = null;
		ActionListener aListener = null;

		// "ファイル"プルダウンメニューを作成してメニューバーに加える。
		aMenu = new JMenu("ファイル");
		aListener = new ActionListener() {
			public void actionPerformed(ActionEvent anEvent) {
				try {
			 		String aCommand = anEvent.getActionCommand();
			 		if (aCommand.equals("選択")) {
                        hoge.aFile = this.paintIOfMenu();

			 		}
			// 		if (aCommand.equals("終了"))
			// 		{
			// 			System.exit(0);
			// 		}
			 	} catch (ConcurrentModificationException anException) {}
			 	return;
			}
		};
		aMenu.add("選択").addActionListener(aListener);
		aMenuBar.add(aMenu);

		return aMenuBar;
		}
		
	public void paintIOfMenu() {
		JFileChooser filechooser = new JFileChooser();
        filechooser.setFileFilter(new FileNameExtensionFilter("画像ファイル", "png", "jpg",
				"Jpeg", "GIF", "bmp"));
        filechooser.showOpenDialog(null);
        File aFile = filechooser.getSelectedFile();
        ImageIcon icon = new ImageIcon(aFile.getPath());
        label.setIcon(icon);

        JLabel label2 = new JLabel();
        //label2.setPreferredSize(new Dimension(900,100));
        label2.setHorizontalAlignment(JLabel.CENTER);
        label2.setText(aFile.getName());

        panel.add(label2, BorderLayout.CENTER);
        aFrame.getContentPane().add(panel, BorderLayout.SOUTH);

	}

}
