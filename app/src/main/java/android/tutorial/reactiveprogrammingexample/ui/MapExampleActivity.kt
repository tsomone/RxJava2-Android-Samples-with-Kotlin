package android.tutorial.reactiveprogrammingexample.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.tutorial.reactiveprogrammingexample.R
import android.tutorial.reactiveprogrammingexample.model.ApiUser
import android.tutorial.reactiveprogrammingexample.model.User
import android.tutorial.reactiveprogrammingexample.utils.CommonConstants
import android.tutorial.reactiveprogrammingexample.utils.Utils
import android.util.Log
import android.view.View
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_example.*

/**
 * Created by tsomone on 11/06/18.
 */
class MapExampleActivity : AppCompatActivity() {
    private val TAG = MapExampleActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example)
    }

    /*
    * Here we are getting ApiUser Object from api server
    * then we are converting it into User Object because
    * may be our database support User Not ApiUser Object
    * Here we are using Map Operator to do that
    */
    fun doSomework(view: View) {
        getObservable()
                // Run on a background thread
                .subscribeOn(Schedulers.io())
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .map { apiUserList -> Utils.convertApiUserListToUserList(apiUserList) }
                .subscribe(getObserver())
    }

    private fun getObservable(): Observable<ArrayList<ApiUser>> {
        return Observable.create { e ->
            if (!e.isDisposed) {
                e.onNext(Utils.getApiUserList())
                e.onComplete()
            }
        }
    }

    private fun getObserver(): Observer<ArrayList<User>> {
        return object : Observer<ArrayList<User>> {
            override fun onComplete() {
                textView.append(" onComplete")
                textView.append(CommonConstants.NEW_LINE)
                Log.d(TAG, " onComplete")
            }

            override fun onSubscribe(d: Disposable) {
                Log.d(TAG, " onSubscribe : ${d.isDisposed}")
            }

            override fun onNext(userList: ArrayList<User>) {
                textView.append(" onNext")
                textView.append(CommonConstants.NEW_LINE)
                for (user in userList) {
                    textView.append(" firstname : ${user.firstname}")
                    textView.append(CommonConstants.NEW_LINE)
                }
                Log.d(TAG, " onNext : ${userList.size}")
            }

            override fun onError(e: Throwable) {
                textView.append(" onError : ${e.message}")
                textView.append(CommonConstants.NEW_LINE)
                Log.d(TAG, " onError : ${e.message}")
            }
        }
    }
}