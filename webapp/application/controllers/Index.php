<?php
defined('BASEPATH') OR exit('No direct script access allowed');
class Index extends CI_Controller {
    public function __construct() {
        parent::__construct();
        $this->load->helper('url');
        $this->load->model('db');
    }
    public function index(...$convert) {
        $key = isset($convert)?$convert:'';
        // $key = (isset($_POST['key']))?$_POST['key']:'';
        $data['target'] = $this->db->getImageData($key);
        $data['history'] = $this->db->getAll();
        $this->load->view('templates/header', $data);
        $this->load->view('index/index', $data);
        $this->load->view('index/history', $data);
        $this->load->view('templates/footer');
    }

    public function image(...$key) {
        $this->index();
    }

    public function string_art(...$key) {
        // $key = (isset($_POST['key']))?$_POST['key']:'';
        $data['target'] = $this->db->getImageData($key);
        $data['history'] = $this->db->getAll();
        $this->load->view('templates/header', $data);
        $this->load->view('index/image', $data);
        $this->load->view('index/history', $data);
        $this->load->view('templates/footer');
    }
}
?>