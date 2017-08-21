package com.naibeck.room.ui

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.naibeck.room.Injection
import com.naibeck.room.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_user.*

/**
 * Esta clase se basa en el ejemplo de BasicRxJavaKotlinSample de Google
 * {@linktourl https://github.com/googlesamples/android-architecture-components/tree/master/BasicRxJavaSampleKotlin }
 */
class UserActivity : AppCompatActivity() {

    private lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: UserViewModel

    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        viewModelFactory = Injection.provideUserViewModel(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(UserViewModel::class.java)
        update_button.setOnClickListener { updateUser() }
    }

    override fun onStart() {
        super.onStart()

        disposable.add(viewModel.userName()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    this.user_name_text.text = it
                }, this::handleError))
    }

    override fun onStop() {
        super.onStop()
        
        disposable.clear()
    }

    private fun updateUser() {
        val username = user_update_input.text.toString()
        update_button.isEnabled = false

        disposable.add(viewModel.updateUser(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    update_button.isEnabled = true
                }, this::handleError))
    }

    private fun handleError(error: Throwable) {
        Exception("An error occurred while trying to get username", error)
    }
}
