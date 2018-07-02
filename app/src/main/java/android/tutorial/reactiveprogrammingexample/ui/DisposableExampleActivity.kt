package android.tutorial.reactiveprogrammingexample.ui

import android.os.Bundle
import android.os.SystemClock
import android.support.v7.app.AppCompatActivity
import android.tutorial.reactiveprogrammingexample.R
import android.tutorial.reactiveprogrammingexample.utils.CommonConstants
import android.util.Log
import android.view.View
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_example.*

/**
 * Created by tsomone on 11/06/18.
 */
class DisposableExampleActivity : AppCompatActivity() {
    private val TAG = DisposableExampleActivity::class.java.simpleName
    private val disposables: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear() // do not send event after activity has been destroyed
    }

    /*
     * Example to understand how to use disposables.
     * disposables is cleared in onDestroy of this activity.
     */
    fun doSomework(view: View) {
        disposables.add(sampleObservable()
                // Run on a background thread
                .subscribeOn(Schedulers.io())
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getObserver())
        )
    }

    private fun getObserver(): DisposableObserver<String> {
        return object : DisposableObserver<String>() {
            override fun onComplete() {
                textView.append(" onComplete")
                textView.append(CommonConstants.NEW_LINE)
                Log.d(TAG, "onComplete")
            }

            override fun onNext(value: String) {
                textView.append(" onNext : value : $value")
                textView.append(CommonConstants.NEW_LINE)
                Log.d(TAG, "onNext value : $value")
            }

            override fun onError(e: Throwable) {
                textView.append(" onError : ${e.message}")
                textView.append(CommonConstants.NEW_LINE)
                Log.d(TAG, "onError : ${e.message}")
            }
        }
    }

    private fun sampleObservable(): Observable<String> {
        return Observable.defer {
            SystemClock.sleep(2000) // Do some long running operation
            Observable.just("one", "two", "three", "four", "five")
        }
    }
}
