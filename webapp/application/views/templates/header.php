<html>
    <head>
        <title>team-oro</title>
        <link rel="shortcut icon" href="<?=base_url('assets/images/favicon.ico')?>" />
        <link rel="stylesheet" href="<?=base_url('assets/css/reset.css')?>" />
        <link rel="stylesheet" href="<?=base_url('assets/css/header.css')?>" />
        <link rel="stylesheet" href="<?=base_url('assets/css/index.css')?>" />
        <link rel="stylesheet" href="<?=base_url('assets/css/footer.css')?>" />
        <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
        <script>
            $(window).on('load', function() {
                $('header nav ul li').addClass('transition02s');
                let td_len = $('.convert-wrapper tr:first-child').children().length;
                let unit_width = Math.floor($('.convert-wrapper table').width() / td_len);
                // $('.convert-wrapper td').css({
                //     'width': unit_width,
                //     'height': unit_width
                // });
            });
        </script>
    </head>
    <body>
    <header>
        <nav>
            <h1 onclick="location.href='<?=base_url()?>';">team-oro</h1>
            <ul>
                <li onclick="location.href='<?=base_url('index/image/'.$target['convert_key'])?>';">
                    <span class="moji">変換元の画像</span>
                    <span class="line transition02s"></span>
                </li>
                <li onclick="location.href='<?=base_url('index/string_art/'.$target['convert_key'])?>';">
                    <span class="moji">変換後の文字</span>
                    <span class="line transition02s"></span>
                </li>
            </ul>
        </nav>
    </header>
