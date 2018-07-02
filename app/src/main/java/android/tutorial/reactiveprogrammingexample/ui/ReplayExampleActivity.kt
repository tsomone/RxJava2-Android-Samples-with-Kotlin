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
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_example.*

/**
 * Created by tsomone on 21/06/18.
 */
class ReplayExampleActivity : AppCompatActivity() {
    private val TAG = ReplayExampleActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example)
    }

    /*
     * Using replay operator, replay ensure that all observers see the same sequence
     * of emitted items, even if they subscribe after the Observable has begun emitting items
     */
    fun doSomework(view: View) {
        val source = PublishSubject.create<Int>()
        val connectableObservable = source.replay(3) // bufferSize = 3 to retain 3 values to replay
        connectableObservable.connect() // connecting the connectableObservable

        connectableObservable.subscribe(getFirstObserver())

        source.onNext(1)
        source.onNext(2)
        source.onNext(3)
        source.onNext(4)
        source.onComplete()

        /*
         * it will emit 2, 3, 4 as (count = 3), retains the 3 values for replay
         */
        connectableObservable.subscribe(getSecondObserver())
    }

    private fun getFirstObserver(): Observer<Int> {
        return object : Observer<Int> {
            override fun onNext(value: Int) {
                textView.append(" First onNext : value : $value")
                textView.append(CommonConstants.NEW_LINE)
                Log.d(TAG, " First onNext : value : $value")
            }

            override fun onComplete() {
                textView.append(" First onComplete")
                textView.append(CommonConstants.NEW_LINE)
                Log.d(TAG, " First onComplete")
            }

            override fun onSubscribe(d: Disposable) {
                Log.d(TAG, " First onSubscribe : ${d.isDisposed}")
            }

            override fun onError(e: Throwable) {
                textView.append(" First onError : ${e.message}")
                textView.append(CommonConstants.NEW_LINE)
                Log.d(TAG, " First onError : ${e.message}")
            }
        }
    }

    private fun getSecondObserver(): Observer<Int> {
        return object : Observer<Int> {
            override fun onNext(value: Int) {
                textView.append(" Second onNext : value : $value")
                textView.append(CommonConstants.NEW_LINE)
                Log.d(TAG, " Second onNext : value : $value")
            }

            override fun onComplete() {
                textView.append(" Second onComplete")
                textView.append(CommonConstants.NEW_LINE)
                Log.d(TAG, " Second onComplete")
            }

            override fun onSubscribe(d: Disposable) {
                textView.append(" Second onSubscribe : isDisposed :${d.isDisposed}")
                textView.append(CommonConstants.NEW_LINE)
                Log.d(TAG, " Second onSubscribe : ${d.isDisposed}")
            }

            override fun onError(e: Throwable) {
                textView.append(" Second onError : ${e.message}")
                textView.append(CommonConstants.NEW_LINE)
                Log.d(TAG, " Second onError : ${e.message}")
            }
        }
    }
}
