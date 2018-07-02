package android.tutorial.reactiveprogrammingexample.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.tutorial.reactiveprogrammingexample.R
import android.tutorial.reactiveprogrammingexample.utils.CommonConstants
import android.util.Log
import android.view.View
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.synthetic.main.activity_example.*

/**
 * Created by tsomone on 22/06/18.
 */
class BehaviorSubjectExampleActivity : AppCompatActivity() {
    private val TAG = BehaviorSubjectExampleActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example)
    }

    /*
     * When an observer subscribes to a BehaviorSubject, it begins by emitting the item most
     * recently emitted by the source Observable (or a seed/default value if none has yet been
     * emitted) and then continues to emit any other items emitted later by the source Observable(s).
     * It is different from Async Subject as async emits the last value (and only the last value)
     * but the Behavior Subject emits the last and the subsequent values also.
     */
    fun doSomework(view: View) {
        val source = BehaviorSubject.create<Int>()

        source.subscribe(getFirstObserver()) // it will get 1, 2, 3, 4 and onComplete

        source.onNext(1)
        source.onNext(2)
        source.onNext(3)

        /*
         * it will emit 3(last emitted), 4 and onComplete for second observer also.
         */
        source.subscribe(getSecondObserver())

        source.onNext(4)
        source.onComplete()
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
