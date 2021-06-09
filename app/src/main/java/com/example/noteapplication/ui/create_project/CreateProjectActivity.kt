package com.example.noteapplication.ui.create_project

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import com.example.noteapplication.R
import com.example.noteapplication.base.BaseActivity
import com.example.noteapplication.data.model.PrimaryColor
import com.example.noteapplication.databinding.ActivityCreateProjectBinding
import com.example.noteapplication.visible
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CreateProjectActivity : BaseActivity<CreateProjectViewModel, ActivityCreateProjectBinding>(
        CreateProjectViewModel::class
), PickerColorListener {

    override fun getViewBinding(): ActivityCreateProjectBinding
            = ActivityCreateProjectBinding.inflate(layoutInflater)

    var selectedColor: PrimaryColor? = null

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_create_project, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_create -> createProject()
        }
        return true
    }

    private fun createProject() {
        val projectName = binding.etInputProjectTitle.text.toString()
        viewModel.createProject(projectName, selectedColor?.id)
    }

    override fun setupViews() {
        setupToolbar()
        setupColorPicker()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = resources.getString(R.string.create_project)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun setupColorPicker() {
        binding.btnSelectColor.setOnClickListener {
            val bottomSheetDialogFragment: BottomSheetDialogFragment =
                ColorPickerBottomSheetDialogFragment()
            bottomSheetDialogFragment.isCancelable = true
            bottomSheetDialogFragment.show(
                supportFragmentManager,
                bottomSheetDialogFragment.tag
            )
        }
    }

    override fun subscribeToLiveData() {
        viewModel.createResult.observe(this, Observer {
            if (it == true) finish()
        })
    }

    companion object {
        fun instance(context: Context) {
            val intent = Intent(context, CreateProjectActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun choosedColor(colors: MutableList<PrimaryColor>) {
        colors.forEach { if (it.isSelected) setupSelectedViews(it) }
    }

    private fun setupSelectedViews(item: PrimaryColor) {
        selectedColor = item
        binding.colorView.visible()
        binding.colorView.background.setColorFilter(Color.parseColor(item.hexCode), PorterDuff.Mode.SRC_ATOP)
        binding.btnSelectColor.text = "Change color"
    }
}