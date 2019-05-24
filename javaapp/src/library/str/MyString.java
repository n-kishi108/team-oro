package library.str;

import java.util.ArrayList;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import org.yaml.snakeyaml.Yaml;

@SuppressWarnings("unchecked")
public class MyString {

	/**
	* yamlファイルから読み込んだ文字列を格納するリスト
	*/
	private ArrayList<ArrayList<String>> aStringList = new ArrayList<ArrayList<String>>();

	public MyString() {
		this.loadJIS();
	}

	/**
	* yamlファイルから文字列を読み込みリストに格納する
	*/
	public void loadJIS() {
		Yaml aYaml = new Yaml();

		this.aStringList = automaticCast(aYaml.load(ClassLoader.getSystemResourceAsStream("JISsort.yml")));

		return;
	}

	/**
	* 文字列が入ったリストの先頭の文字と切り出した画像の相関係数からグループを決める
	* @param aMainImage 分割した画像
	* @return 似つかわしい文字が入った文字列
	*/
	public ArrayList<String> recommend(BufferedImage aMainImage) {
		CoefficientOfCorrelation aCoc = new CoefficientOfCorrelation();
		Double max = 0.0;
		Integer count = 0;
		for(Integer i = 0; i < this.aStringList.size(); i++) {
			//文字列から画像の取得
			BufferedImage aStringImage = this.getStringImage(this.aStringList.get(i).get(0), aMainImage.getWidth(), aMainImage.getHeight());
			Double r = aCoc.getCoefficient(aMainImage, aStringImage);
			if(r > max) {
				max = r;
				count = i;
			}
		}

		return this.aStringList.get(count);
	}

	/**
	* 文字列を画像に変換する(このメソッドとここで使用しているメソッドはMyImageですべき？)
	* @param aString 対象の文字
	* @param width 分割した画像の幅
	* @param height 分割した画像の高さ
	* @return 文字列から変換した画像
	*/
	public BufferedImage getStringImage(String aString, int width, int height) {
		//画像のオブジェクトの作成
		BufferedImage aStringImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

		//文字を描画する準備
		Graphics aGraphics= aStringImage.createGraphics();
		aGraphics.setColor(Color.WHITE);
		aGraphics.fillRect(0,0,width,height);
		aGraphics.setColor(Color.BLACK);
		aGraphics.setFont(new Font(Font.MONOSPACED, Font.PLAIN, width));

		//文字を真ん中に寄せる。
		this.drawStringCenter(aGraphics, aString, width/2, height/2);

		//画像の2値化
		aStringImage = this.binarization(aStringImage);

		return aStringImage;
	}

	/**
	* 画像の2値化をする処理
	* @param g グラフィックスコンテキスト
	* @param text 文字データ
	* @param x 画像の横の中央座標
	* @param y 画像の縦の中央座標
	*/
	public void drawStringCenter(Graphics g,String text,int x,int y) {
		FontMetrics fm = g.getFontMetrics();
		Rectangle rectText = fm.getStringBounds(text, g).getBounds();
		x = x-rectText.width/2;
		y = y-rectText.height/2+fm.getMaxAscent();
		
		g.drawString(text, x, y);

		return;
	}

	/**
	* 画像の2値化をする処理
	* @param img 画像データ
	* @return 2値化した画像データ
	*/
	public BufferedImage binarization(BufferedImage img) {
   		// 画像サイズの取得
   		int width = img.getWidth();
   		int height= img.getHeight();
   
   		for (int y = 0; y < height; ++ y ) {
   			for (int x = 0; x < width; ++ x ) {
   				// (x,y)の色を取得
   				int color = img.getRGB( x, y );
   
   				// 色をr,g,bに分解
   				int r = ( color >> 16 ) & 0xff;
   				int g = ( color >> 8 ) & 0xff;
   				int b = color & 0xff;
   
   				// rgbの平均値を計算
   				int p = ( r + g + b ) / 3;
   				
   				// ２値化
   				if ( 100 <= p ) {
   					// 閾値tv以上なら白
   					r = 255;
   					g = 255;
   					b = 255;
   				}else{
   					// 閾値tv未満なら黒
   					r = 0;
   					g = 0;
   					b = 0;
   				}
   
   				// r,g,bの色を合成
   				int newcolor = ( r << 16 ) + ( g << 8 ) + b;
   
   				// 合成した色を(x,y)に設定
   				img.setRGB( x, y, newcolor );
   			}
   		}

   		return img;
	}

	/**
	* 総称(ジェネリクス)を用いてオブジェクトを適切な型に合わせてキャストする
	* @param src オブジェクト
	* @return キャストされたもの
	*/
	public static <T> T automaticCast(Object src) {
		T castedObject = (T) src;
		return castedObject;
	}
}