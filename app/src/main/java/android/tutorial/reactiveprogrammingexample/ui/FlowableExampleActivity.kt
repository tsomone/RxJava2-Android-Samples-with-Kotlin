package android.tutorial.reactiveprogrammingexample.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.tutorial.reactiveprogrammingexample.R
import android.tutorial.reactiveprogrammingexample.utils.CommonConstants
import android.util.Log
import android.view.View
import io.reactivex.Flowable
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_example.*

/**
 * Created by tsomone on 11/06/18.
 */
class FlowableExampleActivity : AppCompatActivity() {
    private val TAG = FlowableExampleActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example)
    }

    /*
     * simple example using Flowable
     */
    fun doSomework(view: View) {
        Flowable.just(1, 2, 3, 4)
                .reduce(50, { a, b -> a + b })
                .subscribeWith(getObserver())
    }

    private fun getObserver(): SingleObserver<Int> {
        return object : SingleObserver<Int> {
            override fun onSuccess(value: Int) {
                textView.append(" onSuccess : value : $value")
                textView.append(CommonConstants.NEW_LINE)
                Log.d(TAG, " onSuccess : value : $value")
            }

            override fun onSubscribe(d: Disposable) {
                Log.d(TAG, " onSubscribe : ${d.isDisposed}")
            }

            override fun onError(e: Throwable) {
                textView.append(" onError : ${e.message}")
                textView.append(CommonConstants.NEW_LINE)
                Log.d(TAG, " onError : ${e.message}")
            }
        }
    }
}
