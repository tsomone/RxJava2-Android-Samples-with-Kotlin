package android.tutorial.reactiveprogrammingexample.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.tutorial.reactiveprogrammingexample.R
import android.tutorial.reactiveprogrammingexample.utils.CommonConstants
import android.util.Log
import android.view.View
import io.reactivex.MaybeObserver
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_example.*

/**
 * Created by tsomone on 21/06/18.
 */
class ReduceExampleActivity : AppCompatActivity() {
    private val TAG = ReduceExampleActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example)
    }

    /*
     * simple example using reduce to add all the number
     */
    fun doSomework(view: View) {
        Observable.just(1, 2, 3, 4)
                .reduce { t1, t2 -> t1 + t2 }
                .subscribe(getObserver())
    }

    private fun getObserver(): MaybeObserver<Int> {
        return object : MaybeObserver<Int> {
            override fun onSuccess(value: Int) {
                textView.append(" onSuccess : value : $value")
                textView.append(CommonConstants.NEW_LINE)
                Log.d(TAG, " onSuccess : value : $value")
            }

            override fun onComplete() {
                textView.append(" onComplete")
                textView.append(CommonConstants.NEW_LINE)
                Log.d(TAG, " onComplete")
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
