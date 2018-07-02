package android.tutorial.reactiveprogrammingexample.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.tutorial.reactiveprogrammingexample.R
import android.tutorial.reactiveprogrammingexample.utils.CommonConstants
import android.util.Log
import android.view.View
import io.reactivex.Observable
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_example.*

/**
 * Created by tsomone on 22/06/18.
 */
class LastExampleActivity : AppCompatActivity() {
    private val TAG = LastExampleActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example)
    }

    /*
     * emits only the last item emitted by the Observable.
     */
    fun doSomework(view: View) {
        Observable.just("A1", "A2", "A3", "A4", "A5", "A6")
                // the default item ("A1") to emit if the source ObservableSource is empty
                .last("A1")
                // Run on a background thread
                .subscribeOn(Schedulers.io())
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver())
    }

    private fun getObserver(): SingleObserver<String> {
        return object : SingleObserver<String> {
            override fun onSuccess(value: String) {
                textView.append(" onNext : value : $value")
                textView.append(CommonConstants.NEW_LINE)
                Log.d(TAG, " onNext : value : $value")
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