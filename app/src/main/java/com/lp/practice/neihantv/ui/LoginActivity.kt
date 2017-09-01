package com.lp.practice.neihantv.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import com.lp.practice.neihantv.R
import com.lp.practice.neihantv.utils.showToast
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    var list = listOf("li", "li1", "li2").toMutableList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        Observable.fromArray(list)
        initListener()
    }

    private fun initListener() {
        button.setOnClickListener {
            showToast("提交成功")
        }
        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                Observable.just(s.toString())
                        .subscribeOn(Schedulers.io())
                        .filter {
                            t ->
                            list.contains(t)
                        }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })
    }
}
