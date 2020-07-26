package com.lancer.passwordhelper.ui.activity.edit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.lancer.passwordhelper.R
import com.lancer.passwordhelper.base.BaseActivity
import com.lancer.passwordhelper.bean.Card
import com.lancer.passwordhelper.databinding.ActivityEditBinding

class EditActivity : BaseActivity<ActivityEditBinding>() {
    override fun initView() {
    }

    override fun initData() {
        val card: Card = intent.getSerializableExtra("list") as Card
        Log.d("tag", card.toString())
    }

    override fun initLayout(): Int = R.layout.activity_edit

}