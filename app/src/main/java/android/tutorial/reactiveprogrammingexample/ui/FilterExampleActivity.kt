package android.tutorial.reactiveprogrammingexample.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.tutorial.reactiveprogrammingexample.R
import android.tutorial.reactiveprogrammingexample.utils.CommonConstants
import android.util.Log
import android.view.View
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_example.*

/**
 * Created by tsomone on 21/06/18.
 */
class FilterExampleActivity : AppCompatActivity() {
    private val TAG = FilterExampleActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example)
    }

    /*
     * simple example by using filter operator to emit only even value
     */
    fun doSomework(view: View) {
        Observable.just(1, 2, 3, 4, 5, 6)
                .filter({ t1 -> t1 % 2 == 0 }).subscribe(getObserver())
    }

    private fun getObserver(): Observer<Int> {
        return object : Observer<Int> {
            override fun onNext(value: Int) {
                textView.append(" onNext : ")
                textView.append(CommonConstants.NEW_LINE)
                textView.append(" value : $value")
                textView.append(CommonConstants.NEW_LINE)
                Log.d(TAG, " onNext ")
                Log.d(TAG, " value : $value")
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
