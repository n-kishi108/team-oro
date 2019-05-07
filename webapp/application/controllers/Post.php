<?php
class Post extends CI_Controller {
    public function __construct() {
        parent::__construct();
        $this->load->helper('url');
        $this->load->model('db');
    }
    public function insert() {
        $data = array(
            'product' => $_POST['product'],
            'price' => $_POST['price'],
            'quantity' => $_POST['quantity']
        );
        $result = $this->db->insert($data);
        if($result) {
            header('Location: '.base_url());
        }else{
            exit('追加できませんでした。');
        }
    }

    public function delete() {
        $result = $this->db->delete($data = $_POST['product']);
        if($result) {
            header('Location: '.base_url());
        }else{
            exit('削除できませんでした。');
        }
    }
}
?>