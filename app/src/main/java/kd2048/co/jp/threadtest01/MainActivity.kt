package kd2048.co.jp.threadtest01

import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.WindowManager
import android.widget.Button
import kd2048.co.jp.threadtest01.myHandler.Companion.MESSAGE1
import kd2048.co.jp.threadtest01.myHandler.Companion.MESSAGE2
import kd2048.co.jp.threadtest01.myHandler.Companion.MESSAGE3
import kd2048.co.jp.threadtest01.myHandler.Companion.MESSAGE4
import java.lang.Thread.currentThread
import java.lang.Thread.sleep
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    val mthreadAAA = HandlerThread("AAA")
    val mthreadBBB = HandlerThread("BBB")
    val mthreadCCC = HandlerThread("CCC")
    var mHandlerAAA = Handler()
    var mHandlerBBB = Handler()
    var mHandlerCCC = Handler()
    var mHandlerMY = myHandler()

    override fun onCreate(savedInstanceState: Bundle?) {
        // super.xxx()で親クラスで初期化の処理をするので消してはいけない
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        mthreadAAA.start()
        mthreadBBB.start()
        mthreadCCC.start()
        mHandlerAAA = Handler(mthreadAAA.looper)
        mHandlerBBB = Handler(mthreadBBB.looper)
        mHandlerCCC = Handler(mthreadCCC.looper)
        Log.d("kd>", "mthread ${mthreadAAA}")
        Log.d("kd>", "mthread.looper ${mthreadAAA.looper}")
        Log.d("kd>", "mHandler ${mHandlerAAA}")

        val button1 = findViewById<Button>(R.id.button1)
        button1.setOnClickListener {
            val r = Runnable {
                Log.d("kd>", "スレッド開始 ${HandlerThread.currentThread()}")
                sleep(5000)
                Log.d("kd>", "スレッド完了 ${HandlerThread.currentThread()}")
                mHandlerMY.sendEmptyMessage(MESSAGE1)
            }
            Log.d("kd>", "ボタン１クリック")
            mHandlerAAA.post(r)
            mHandlerBBB.post(r)
            mHandlerCCC.post(r)

        }
        val button2 = findViewById<Button>(R.id.button2)
        button2.setOnClickListener {
            Log.d("kd>", "ボタン２クリック")
            mHandlerMY.sendEmptyMessage(MESSAGE2)
        }
        val button3 = findViewById<Button>(R.id.button3)
        button3.setOnClickListener {
            Log.d("kd>", "ボタン３クリック")
            mHandlerMY.sendEmptyMessage(MESSAGE3)
        }
        val button4 = findViewById<Button>(R.id.button4)
        button4.setOnClickListener {
            Log.d("kd>", "ボタン４クリック")
            mHandlerMY.sendEmptyMessage(MESSAGE4)
        }
    }
}

class myHandler : Handler() {
    companion object{
        val MESSAGE1 = 1
        val MESSAGE2 = 2
        val MESSAGE3 = 3
        val MESSAGE4 = 4
    }

    override fun handleMessage(msg: Message?) {
        //super.handleMessage(msg)
        when(msg?.what){

            MESSAGE1 -> Log.d("kd>", "MESSAGE1 ${HandlerThread.currentThread()}")
            MESSAGE2 -> Log.d("kd>", "MESSAGE2 ${HandlerThread.currentThread()}")
            MESSAGE3 -> Log.d("kd>", "MESSAGE3 ${HandlerThread.currentThread()}")
            MESSAGE4 -> Log.d("kd>", "MESSAGE4 ${HandlerThread.currentThread()}")
        }
    }

}