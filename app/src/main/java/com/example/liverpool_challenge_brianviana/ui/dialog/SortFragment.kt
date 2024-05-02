package com.example.liverpool_challenge_brianviana.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.liverpool_challenge_brianviana.R
import com.example.liverpool_challenge_brianviana.databinding.FragmentSortBinding
import com.example.liverpool_challenge_brianviana.ui.dialog.adapter.SortAdapter
import com.example.liverpool_challenge_brianviana.ui.utils.SortOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SortFragment : DialogFragment() {

    private lateinit var sortAdapter: SortAdapter
    private var _binding: FragmentSortBinding? = null
    private val binding get() = _binding!!
    var onSortOptionSelected: ((SortOptions) -> Unit)? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        initListener()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val sortOptionsList = SortOptions.entries
        sortAdapter = SortAdapter(sortOptionsList) { sortOption ->
            onSortOptionSelected?.invoke(sortOption)
        }
        binding.rvSort.apply {
            layoutManager = GridLayoutManager(context, 1)
            adapter = sortAdapter
        }
    }


    private fun initListener() {
        binding.ivClose.setOnClickListener {
            dismiss()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.HalfScreenDialog)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSortBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}