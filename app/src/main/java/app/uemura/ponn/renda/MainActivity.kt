package app.uemura.ponn.renda

import android.os.Bundle
import android.os.CountDownTimer
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import app.uemura.ponn.renda.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //バインディングクラスの変数
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(this.root) }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    //タップを数える変数を準備する
        var tapCount: Int = 0

    //残り時間を10秒にセットする
        var second = 10

    //タイマーを設定する
        val timer:CountDownTimer = object : CountDownTimer(10000, 1000) {
            //タイマーが終了するときに呼び出す
            override fun onFinish() {
                //STARTボタンを見える状態にする
                binding.startButton.isVisible = true
                //TAPボタンを灰色に変更する
                binding.tapButton.backgroundTintList = ContextCompat.getColorStateList(this@MainActivity, R.color.gray)
                //残り秒数を10秒に戻す
                second = 10
                //タップを数える実数を0に戻す
                tapCount = 0
            }

            //1秒ごとに呼ばれる
            override fun onTick(millisUnFinished: Long) {
                //TAPボタンをピンク色にセット
                binding.tapButton.backgroundTintList = ContextCompat.getColorStateList(this@MainActivity, R.color.pink)
                //残り秒数を1ずつ減らす
                second = second -1
                //残り秒数をTextViewに反映する
                binding.secondText.text = second.toString()
            }

        }

    //STARTボタンがタップされたときの処理
        binding.startButton.setOnClickListener{
            //タップされた数をTextViewに反映する
            binding.countText.text = tapCount.toString()
            //STARTボタンを見えない状態にする
            binding.startButton.isVisible = false
            //タイマーを開始する
            timer.start()
        }

    //ボタンがタップされたときの処理
        binding.tapButton.setOnClickListener{
            if (second < 10){
            //タップを数える変数を1加算する
                tapCount = tapCount + 1

            //タップされた数をテキストビューに反映する
                binding.countText.text = tapCount.toString()
            }


        }

    }

}