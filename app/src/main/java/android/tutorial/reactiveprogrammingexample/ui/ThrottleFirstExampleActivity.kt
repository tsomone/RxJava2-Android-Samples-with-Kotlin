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
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_example.*
import java.util.concurrent.TimeUnit


/**
 * Created by tsomone on 22/06/18.
 */
class ThrottleFirstExampleActivity : AppCompatActivity() {
    private val TAG = ThrottleFirstExampleActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example)
    }

    /*
    * Using throttleFirst() -> if the source Observable has emitted no items since
    * the last time it was sampled, the Observable that results from this operator
    * will emit no item for that sampling period.
    */
    fun doSomework(view: View) {
        getObservable()
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                // Run on a background thread
                .subscribeOn(Schedulers.io())
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver())
    }

    private fun getObservable(): Observable<Int> {
        return Observable.create { emitter ->
            // send events with simulated time wait
            Thread.sleep(0)
            emitter.onNext(1) // deliver
            emitter.onNext(2) // skip
            Thread.sleep(505)
            emitter.onNext(3) // deliver
            Thread.sleep(99)
            emitter.onNext(4) // skip
            Thread.sleep(100)
            emitter.onNext(5) // skip
            emitter.onNext(6) // skip
            Thread.sleep(305)
            emitter.onNext(7) // deliver
            Thread.sleep(510)
            emitter.onComplete()
        }
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
