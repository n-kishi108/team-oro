package library.image;

import library.error.error; // 自作のエラークラス

import java.io.File; // ファイル操作用
import java.io.IOException; // ファイル操作用
import javax.imageio.ImageIO; // 画像操作用
import java.awt.image.BufferedImage; // 画像切り出し用
import java.awt.image.RasterFormatException; // 切り出し用

public class MyImage {

	private Error error;
	
	private File image;

	private ImageIO imageio;

	private int width;
	private int height;

	public String partOfImage;

	private ArrayList<ArrayList<BufferedImage>> biList;




	/*****************

	init

	*****************/
	
	// コンストラクタ
	public MyImage() {
		this.error = new Error();
		this.biList = new ArrayList<>();
	}

	// 画像を読み込む
	public void setImage(String path) {
		this.image = new File(path);
		try {
			this.imageio = new ImageIO.read(new File(path));
		} catch(Exception e) {
			this.error.exitMessage("画像の切り出しに失敗しました。", "プログラムを終了します。");
		}

		this.getSize();
	}




	/*****************

	サイズ取得用

	*****************/

	// サイズを取得する
	private void getSize() {
		//本来ありえないバグ
		if(this.imageio == null) {
			this.error.exitMessage("指定された画像データが見つかりませんでした。");
		}
		this.getWidth();
		this.getHeight();
	}

	// 幅を取得
	private void getWidth() {
		this.width = Integer.parseInt(imageio.getWidth());
	}

	// 高さを取得
	private void getHeight() {
		this.height = Integer.parseInt(imageio.getHeight());
	}




	/*****************

	画像取得・切り出し・2値化関連

	*****************/

	// 画像を取得する
	public void getImage() {
		return this.image();
	}

	// 画像切り出し
	/*
	row: 一行の分割数
	 */
	public void split(Integer row) {

		ArrayList<ArrayList<String>> planar = new ArrayList<>();
		
		// バリデーションチェック
		if(row < 32 || row > 128) {
			this.error.exitMessage("不正な値が入力されました。");
		}

		BufferedImage bi = null;

		int rownum = Math.ceil(this.width / row); // 分割されたモジュールの横(実質一片)のピクセル数
		Integer col = Math.ceil(this.height / rownum); // たての分割数
		
		// 分割開始
		planar = this.splitRun(bi, row, col);
		for(int i = 0; i < row; i++) {
			ArrayList<String> tmp = new ArrayList<>();
			for(int j = 0; j < col; j++) {
				try {
					bi = this.imageio.getSubimage( col * j, row * i, row, row);
					list.add(tmp);
					this.biList.add(bi);
				} catch(RasterFormatException rfe) {
					this.error.exitMessage("画像を切り出すことができませんでした。");
				}
			}
			planar.add(tmp);
		}
		return planar;
	}

	// 分割処理メイン
	private ArrayList<ArrayList<String>> splitRun(BufferedImage bi, Integer row, Integer col) {
		ArrayList<ArrayList<String>> arraylist = new ArrayList<>();

		for(int i = 0; i < row; i++) {
			arraylist.add(this.splitRow(bi, col, row, i));
		}
		return arraylist;
	}

	// 行を分割
	private ArrayList<String> splitRow(BufferedImage bi, Integer col, Integer row, int i) {
		ArrayList<String> tmp = new ArrayList<>();
		for(int j = 0; j < col; j++) {
			tmp.add(this.splitCol(bi, col, row, i, j));
		}
		return tmp;
	}

	// 列を分割
	private String splitCol(BufferedImage bi, Integer col, Integer row, int i, int j) {
		try {
			bi = this.imageio.getSubimage( col * j, row * i, row, row);
			return bi;
		} catch(RasterFormatException rfe) {
			this.error.exitMessage("画像を切り出すことができませんでした。");
		}
		return "0";
	}

	//2値化
	/*
	index[0] >> 行番号
	index[1] >> 列番号
	 */
	public void binarize(int ... index) {
		int tv; // 閾値
		String filename; // 画像名
		if(index.length < 2) this.error.exitMessage("引数が足りんです。");
		ArrayList<BufferedImage> temp = biList.get(index[0]);
		BufferedImage img = temp.get(index[1]);
		// BufferedImage img = biList.get(index[0]).get(index[1]); // 画像格納クラス

		for(int y = 0; y < this.height; y++) {
			for(int x = 0; x < this.width; x++) {
				// (x,y)の色を取得
				int color = img.getRGB(x, y);
   
				// 色をr,g,bに分解
				int r = (color >> 16) & 0xff;
				int g = (color >> 8) & 0xff;
				int b = color & 0xff;

				// rgbの平均値を計算
				int average = (r + g + b) / 3;
				
				// ２値化
				if (100 <= average) {
					// 閾値tv以上なら白
					r = 255;
					g = 255;
					b = 255;
				} else {
					// 閾値tv未満なら黒
					r = 0;
					g = 0;
					b = 0;
				}

				// r,g,bの色を合成
				int binary = (r << 16) + (g << 8) + b;

				// 合成した色を(x,y)に設定
				img.setRGB(x, y, binary);				
			}
		}

		temp.set(index[1], img);
		biList.set(index[0], temp);

		return;
	}

}
