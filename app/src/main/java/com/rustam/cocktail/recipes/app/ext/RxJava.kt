package com.rustam.cocktail.recipes.app.ext

import androidx.lifecycle.MutableLiveData
import com.mvasilova.cocktailrecipes.app.platform.State
import io.reactivex.rxjava3.core.Single

fun <T> Single<T>.handleState(state: MutableLiveData<State>): Single<T> =
    doOnSubscribe { state.value = State.Loading }
        .doOnError { state.value = State.Error(it) }
        .doAfterSuccess { state.value = State.Loaded }