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
 * Created by tsomone on 20/06/18.
 */
class BufferExampleActivity : AppCompatActivity() {
    private val TAG = BufferExampleActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example)
    }

    /*
    * simple example using buffer operator - bundles all emitted values into a list
    */
    fun doSomework(view: View) {
        Observable.just("one", "two", "three", "four", "five")
                .buffer(3, 1)
                .subscribe(getObserver())
    }

    private fun getObserver(): Observer<List<String>> {
        return object : Observer<List<String>> {
            override fun onComplete() {
                textView.append(" onComplete")
                textView.append(CommonConstants.NEW_LINE)
                Log.d(TAG, " onComplete")
            }

            override fun onSubscribe(d: Disposable) {
                Log.d(TAG, " onSubscribe : ${d.isDisposed}")
            }

            override fun onNext(stringList: List<String>) {
                textView.append(" onNext : ${stringList.size}")
                textView.append(CommonConstants.NEW_LINE)
                for (value in stringList) {
                    textView.append(" value : $value")
                    textView.append(CommonConstants.NEW_LINE)
                }
                Log.d(TAG, " onNext : ${stringList.size}")
            }

            override fun onError(e: Throwable) {
                textView.append(" onError : ${e.message}")
                textView.append(CommonConstants.NEW_LINE)
                Log.d(TAG, " onError : ${e.message}")
            }
        }
    }
}