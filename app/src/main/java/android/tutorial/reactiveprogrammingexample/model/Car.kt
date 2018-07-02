package android.tutorial.reactiveprogrammingexample.model

import io.reactivex.Observable

/**
 * Created by tsomone on 21/06/18.
 */
class Car {
    private var brand: String? = null

    fun setBrand(brand: String) {
        this.brand = brand
    }

    fun brandDeferObservable(): Observable<String> {
        return Observable.defer { Observable.just(brand!!) }
    }
}
