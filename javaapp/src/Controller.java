import system.typistart.controller.ORO_Controller; // コントローラのコアクラス
import system.typistart.view.ORO_View; // ビューのコアクラス

public class Controller {

    public MenuView menu;
    public ResultView result;
    public ORO_Controller parent;

    // コンストラクタ
    public Controller() {
        // System.out.println("Hello Controller");
        this.parent = new ORO_Controller();
        this.parent.init();
        this.parent.setVisible(true);
        System.out.println(this.parent.getContentPane().getSize().height);
    }

    // メニュー画面のビューをセット
    public void setView(MenuView view) {
        this.menu = view;
    }

    // 結果画面のビューをセット
    public void setView(ResultView view) {
        this.result = view;
    }

    // レイアウトセット待ち
    public void setLayout(String page) {
        switch(page) {
            case "menu":
                this.setMenuLayout();
                break;

            case "result":
                this.setResultLayout();
                break;

            default:
                this.setErrorLayout();
        }
    }

    // メニューページ
    private void setMenuLayout(){
        this.menu.init(this.parent);
    }

    // 結果ページ
    private void setResultLayout(){
        this.result.init();
    }

    // エラーページ
    private void setErrorLayout(){
    }
}