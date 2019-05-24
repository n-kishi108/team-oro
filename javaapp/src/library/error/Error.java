public class Error {
    public Error(){}
    public void exitMessage(String ... messages) {
        this.setErrorHeader();
        for(String message : messages) {
            System.out.println(message);
        }
        this.setErrorFooter();
        System.exit(0);
    }

    private void setErrorHeader() {
        System.out.println("______Error______");
        System.out.println("");
    }

    private void setErrorFooter() {
        System.out.println("");
        System.out.println("_______End_______");
        System.out.println("");
        System.out.println("プログラムを終了します。");
    }
}