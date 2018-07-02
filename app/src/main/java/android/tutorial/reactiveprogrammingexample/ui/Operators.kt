package android.tutorial.reactiveprogrammingexample.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.tutorial.reactiveprogrammingexample.R
import android.view.View

class Operators : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_operators)
    }

    fun startSimpleActivity(view: View) {
        startActivity(Intent(this, SimpleExampleActivity::class.java))
    }

    fun startMapActivity(view: View) {
        startActivity(Intent(this, MapExampleActivity::class.java))
    }

    fun startZipActivity(view: View) {
        startActivity(Intent(this, ZipExampleActivity::class.java))
    }

    fun startDisposableActivity(view: View) {
        startActivity(Intent(this, DisposableExampleActivity::class.java))
    }

    fun startTakeActivity(view: View) {
        startActivity(Intent(this, TakeExampleActivity::class.java))
    }

    fun startTimerActivity(view: View) {
        startActivity(Intent(this, TimerExampleActivity::class.java))
    }

    fun startIntervalActivity(view: View) {
        startActivity(Intent(this, IntervalExampleActivity::class.java))
    }

    fun startSingleObserverActivity(view: View) {
        startActivity(Intent(this, SingleObserverExampleActivity::class.java))
    }

    fun startCompletableObserverActivity(view: View) {
        startActivity(Intent(this, CompletableObserverExampleActivity::class.java))
    }

    fun startFlowableActivity(view: View) {
        startActivity(Intent(this, FlowableExampleActivity::class.java))
    }

    fun startReduceActivity(view: View) {
        startActivity(Intent(this, ReduceExampleActivity::class.java))
    }

    fun startBufferActivity(view: View) {
        startActivity(Intent(this, BufferExampleActivity::class.java))
    }

    fun startFilterActivity(view: View) {
        startActivity(Intent(this, FilterExampleActivity::class.java))
    }

    fun startSkipActivity(view: View) {
        startActivity(Intent(this, SkipExampleActivity::class.java))
    }

    fun startScanActivity(view: View) {
        startActivity(Intent(this, ScanExampleActivity::class.java))
    }

    fun startReplayActivity(view: View) {
        startActivity(Intent(this, ReplayExampleActivity::class.java))
    }

    fun startConcatActivity(view: View) {
        startActivity(Intent(this, ConcatExampleActivity::class.java))
    }

    fun startMergeActivity(view: View) {
        startActivity(Intent(this, MergeExampleActivity::class.java))
    }

    fun startDeferActivity(view: View) {
        startActivity(Intent(this, DeferExampleActivity::class.java))
    }

    fun startDistinctActivity(view: View) {
        startActivity(Intent(this, DistinctExampleActivity::class.java))
    }

    fun startLastActivity(view: View) {
        startActivity(Intent(this, LastExampleActivity::class.java))
    }

    fun startReplaySubjectActivity(view: View) {
        startActivity(Intent(this, ReplaySubjectExampleActivity::class.java))
    }

    fun startPublishSubjectActivity(view: View) {
        startActivity(Intent(this, PublishSubjectExampleActivity::class.java))
    }

    fun startBehaviorSubjectActivity(view: View) {
        startActivity(Intent(this, BehaviorSubjectExampleActivity::class.java))
    }

    fun startAsyncSubjectActivity(view: View) {
        startActivity(Intent(this, AsyncSubjectExampleActivity::class.java))
    }

    fun startThrottleFirstActivity(view: View) {
        startActivity(Intent(this, ThrottleFirstExampleActivity::class.java))
    }

    fun startThrottleLastActivity(view: View) {
        startActivity(Intent(this, ThrottleLastExampleActivity::class.java))
    }

    fun startDebounceActivity(view: View) {
        startActivity(Intent(this, DebounceExampleActivity::class.java))
    }

    fun startDelayActivity(view: View) {
        startActivity(Intent(this, DelayExampleActivity::class.java))
    }

    fun startSwitchMapActivity(view: View) {
        startActivity(Intent(this, SwitchMapExampleActivity::class.java))
    }
}
