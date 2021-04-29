package com.example.noteapplication.ui.create_project

import android.content.Context
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import com.example.noteapplication.R
import com.example.noteapplication.base.BaseActivity
import com.example.noteapplication.showToast

class CreateProjectActivity : BaseActivity<CreateProjectViewModel>(
        R.layout.activity_create_project,
        CreateProjectViewModel::class.java
) {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_create_project, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_create -> showToast("CLICK CREATE")
        }
        return true
    }

    override fun setupViews() {
        supportActionBar?.title = resources.getString(R.string.create_project)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


    override fun subscribeToLiveData() {

    }
}