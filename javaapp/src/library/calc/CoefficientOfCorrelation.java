package library.calc;

import java.util.List;
import java.util.ArrayList;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferInt;

/*+
* 切り出した画像と文字列の画像から相関係数を求め画像を返す
*/
public class CoefficientOfCorrelation {
	/**
	* 切り出した画像を束縛する
	*/
	private BufferedImage aMainImage = null;

	/**
	* 文字列から変換した画像
	*/
	private BufferedImage aStringImage = null;

	/**
	* 似つかわしい文字列が入ったリスト
	*/
	private List<String> aSimilarStringList = new ArrayList<String>();

	/**
	* @param aMainImage 分割した画像
	* @return 分割した画像と対応した文字列の画像
	*/
	public BufferedImage CoefficientOfCorrelation(BufferedImage aMainImage) {
		MyString aMyString = new MyString();
		//画像の束縛
		this.aMainImage = aMainImage;

		//一番似つかわしい文字が入った配列を取得する。(getMostSimilarStringListの処理はMyStringでするべき？計算はこっちの用いて)
		//this.aSimilarStringList = this.getMostSimilarStringList();
		this.aSimilarStringList = aMyString.recommend(this.aMainImage);

		//画像と文字列の相関係数を求める(指定した闘値(0.8~1.0くらい??)になったら終了)
		for(String aString: this.aSimilarStringList) {
			this.aStringImage = aMyString.getStringImage(aString, this.aMainImage.getWidth(), this.aMainImage.getHeight());
			Double r = this.getCoefficient(this.aMainImage, this.aStringImage);
			if(r > 0.8) {
				break;
			}
		}

		return this.aStringImage;
	}

	/**
	* 相関係数を求める
	* @param aMainImage　分割した画像
	* @param aStringImage 文字列から変換された画像
	* @return 相関係数(-1.0~1.0の範囲)の値を返す
	*/
	public Double getCoefficient(BufferedImage aMainImage, BufferedImage aStringImage) {
		//データの設定
		ArrayList<Double> aMainImagePixel = this.convertINTtoDOUBLE(getPixel(aMainImage));
		ArrayList<Double> aStringImagePixel = this.convertINTtoDOUBLE(getPixel(aStringImage));

		Integer n = aMainImagePixel.size();

		//和を求めて平均を計算する
		Double sum1 = 0.0, sum2 = 0.0;

		for (Integer i = 0; i < n; i++) {
			sum1 += aMainImagePixel.get(i);
			sum2 += aStringImagePixel.get(i);
		}

		Double mean1 = sum1 / n;
		Double mean2 = sum2 / n;

		//２乗和、積和を求めて分散、共分散を計算する

		Double sum11 = 0.0;
		Double sum12 = 0.0;
		Double sum22 = 0.0;

		for (Integer i = 0; i < n; i++) {
			sum11 += Math.pow(aMainImagePixel.get(i) - mean1, 2);

			sum22 += Math.pow(aStringImagePixel.get(i) - mean2, 2);

			sum12 += (aMainImagePixel.get(i) - mean1) * (aStringImagePixel.get(i) - mean2);
		}

		Double variance1 = sum11 / n;
		Double variance2 = sum22 / n;

		Double cov = sum12 / n;

		//相関係数の計算

		Double sd1 = Math.sqrt(variance1);
		Double sd2 = Math.sqrt(variance2);

		Double r = cov / (sd1 * sd2);

		return r;
	}

	/**
	* ピクセル情報の入ったint[]をDouble型に変換しリストを返す
	* @param pixelOfint ピクセル情報が入ったリスト
	* @return double型のピクセル情報が入ったリスト(最大値:1)
	*/
	public ArrayList<Double> convertINTtoDOUBLE(int[] pixelOfint) {
		ArrayList<Double> pixelOfdouble = new ArrayList<Double>();
		for(int num: pixelOfint) {
			if(num == 16777215) num = 1;
			pixelOfdouble.add(Double.valueOf(num));
		}
		return pixelOfdouble;
	}
	/**
	* @param img 画像データ
	* @return ピクセル情報が入ったint[]の配列
	*/
	private int[] getPixel(BufferedImage img) {
 		DataBuffer buf = img.getRaster().getDataBuffer();
		int[] pixel = ((DataBufferInt)buf).getData();

		return pixel;
	}
}