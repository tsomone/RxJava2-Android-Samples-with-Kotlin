package android.tutorial.reactiveprogrammingexample.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.tutorial.reactiveprogrammingexample.R
import android.tutorial.reactiveprogrammingexample.utils.CommonConstants
import android.util.Log
import android.view.View
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_example.*

/**
 * Created by tsomone on 22/06/18.
 */
class ScanExampleActivity : AppCompatActivity() {
    private val TAG = ScanExampleActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example)
    }

    /*
     * Using scan operator, it sends also the previous result
     */
    fun doSomework(view: View) {
        Observable.just(1, 2, 3, 4, 5)
                // Run on a background thread
                .subscribeOn(Schedulers.io())
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .scan { t1, t2 -> t1 + t2 }
                .subscribe(getObserver())
    }

    private fun getObserver(): Observer<Int> {
        return object : Observer<Int> {
            override fun onNext(value: Int) {
                textView.append(" onNext : value : $value")
                textView.append(CommonConstants.NEW_LINE)
                Log.d(TAG, " onNext : value : $value")
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
