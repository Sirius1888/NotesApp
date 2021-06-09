package com.example.noteapplication.ui.create_project

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.noteapplication.ColorType
import com.example.noteapplication.R
import com.example.noteapplication.data.model.PrimaryColor
import com.example.noteapplication.databinding.BottomSheetColorPickerBinding
import com.example.noteapplication.databinding.ItemColorBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

interface PickerColorListener {
    fun choosedColor(colors: MutableList<PrimaryColor>)
}

class ColorPickerBottomSheetDialogFragment : BottomSheetDialogFragment(),
    ColorAdapter.ClickListener {

    private var listener: PickerColorListener? = null
    private var pickerColors: MutableList<PrimaryColor> = ColorType.getNotesPallette()
    private var binding : BottomSheetColorPickerBinding? = null
    private var adapter: ColorAdapter? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = BottomSheetColorPickerBinding.inflate(LayoutInflater.from(requireContext()))
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as PickerColorListener
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    private fun setupRecyclerView() {
        adapter = ColorAdapter(this)
        binding?.recyclerView?.adapter = adapter
        binding?.recyclerView?.layoutManager = GridLayoutManager(requireContext(), COUNT_OF_ROW)
        adapter?.addItems(pickerColors)
    }

    override fun onItemClick(item: PrimaryColor, position: Int) {
        pickerColors.forEach { it.isSelected = false }
        pickerColors[position].isSelected = true
        adapter?.addItems(pickerColors)
    }

    companion object {
        const val COUNT_OF_ROW = 6
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        dialog.cancel()
        dialog.dismiss()
        listener?.choosedColor(pickerColors)
    }
}