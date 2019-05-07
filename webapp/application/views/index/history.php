        <aside class="history">
            <h1>変換履歴</h1>
            <main>

                <?php if(!empty($history)): ?>
                <div class="grid-wrapper">
                    <?php foreach($history as $el): ?>
                    <section onclick="hoge(<?=$el['convert_key']?>);" style="background-image: url('<?= base_url($el['image_path'])?>')"></section>
                    <?php endforeach;?>
                </div>
                <?php else:?>
                <span class="msg">変換履歴がありません。</span>
                <?php endif;?>

            </main>

        </aside>
