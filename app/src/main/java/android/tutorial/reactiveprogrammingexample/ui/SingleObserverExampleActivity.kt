package android.tutorial.reactiveprogrammingexample.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.tutorial.reactiveprogrammingexample.R
import android.tutorial.reactiveprogrammingexample.utils.CommonConstants
import android.util.Log
import android.view.View
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_example.*

/**
 * Created by tsomone on 11/06/18.
 */
class SingleObserverExampleActivity : AppCompatActivity() {
    private val TAG = SingleObserverExampleActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example)
    }

    /*
     * simple example using SingleObserver
     */
    fun doSomework(view: View) {
        Single.just("Amit").subscribe(getSingleObserver())
    }

    private fun getSingleObserver(): SingleObserver<String> {
        return object : SingleObserver<String> {
            override fun onSuccess(value: String) {
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