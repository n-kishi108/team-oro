package library.planar;

public class Planar {
    private ArrayList<ArrayList<T>> array;

    // コンストラクタ
    public Planar() {
        this.array = new ArrayList<T>();
    }

    // 行を格納
    public void addRow(ArrayList<T> object) {
        this.array.add(object);
    }

    // 値を更新
    /*
    row: 何段目(何行目)
    col: 何列目
    value: 格納する値
    */
    public void set(Integer row, Integer col, T value) {
        ArrayList<T> tmp = this.array.get(row);
        tmp.set(col, value);
        this.array.set(row, tmp);
    }

    // 値を取得
    /*
    row: 何段目(何行目)
    col: 何列目
    */
    public T get(Integer row, Integer col) {
        ArrayList<T> tmp = this.array.get(row);
        return tmp.get(col);
    }
}