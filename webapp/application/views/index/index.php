        <div class="convert-wrapper">
            <div class="wrapper">
                <?php if(!empty($target)):?>
                <img src="<?=base_url($target['image_path']);?>" alt="<?=$target['convert_key'];?>">
                <?php else:?>
                <h1>画像が存在しません。変換に失敗した可能性があります。</h1>
                <?php endif;?>
            </div>
        </div>