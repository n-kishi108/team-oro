<?php
class Db extends CI_Model {
    public $mysqli;
    public function __construct() {
        parent::__construct();
        $this->mysqli = new mysqli('localhost', 'root', '', 'oro');
    }

    public function getAll() {
        $sql = "SELECT * FROM `history`";
        $result = $this->mysqli->query($sql);
        $list = array();
        while($row = $result->fetch_assoc()) {
            array_push($list, $row);
        }
        return $list;
    }

    public function getImageData(...$key) {
        $sql = "SELECT * FROM `history` ";
        // echo var_dump($key);
        // exit;
        if(!empty($key[0])) $sql .= "WHERE `convert_key`='$key[0]' ";
        else $sql .= "ORDER BY `date` DESC LIMIT 1";
        // $sql = "SELECT * FROM `history` WHERE `convert_key` = '$key' ORDER BY `date` DESC";
        $result = $this->mysqli->query($sql);
        while($row = $result->fetch_assoc()) {
            return $row;
        }
        return null;
    }
}
?>