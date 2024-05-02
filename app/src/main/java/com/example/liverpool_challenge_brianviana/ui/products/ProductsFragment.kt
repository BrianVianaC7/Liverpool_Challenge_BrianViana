package com.example.liverpool_challenge_brianviana.ui.products

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.liverpool_challenge_brianviana.R
import com.example.liverpool_challenge_brianviana.databinding.FragmentProductsBinding
import com.example.liverpool_challenge_brianviana.ui.products.adapter.ProductsAdapter
import com.example.liverpool_challenge_brianviana.ui.utils.SortOptions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ProductsFragment : Fragment() {

    private lateinit var productsAdapter: ProductsAdapter
    private val productViewModel by viewModels<ProductsViewModel>()
    private var _binding: FragmentProductsBinding? = null
    private val binding get() = _binding!!
    private var isLoading = false
    private var isActivate = false
    private var currentSortOption: SortOptions = SortOptions.PREDEFINED



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        initProducts()
        initUIState()
        initRecyclerView()
        initFilter()
        initListener()
        initLoader()
    }

    private fun initListener() {
        val sortFilter = activity?.findViewById<ImageView>(R.id.ivFilter)
        sortFilter?.setOnClickListener {
            initSort()
        }
    }

    private fun initProducts() {
        productViewModel.getAllProducts("")
    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                productViewModel.products.collect {
                    if (it.isEmpty()) {
                        binding.isEmptyProducts.isVisible = true
                    } else {
                        binding.isEmptyProducts.isVisible = false
                        productsAdapter.updateList(it)
                    }
                }
            }
        }
    }

    private fun initRecyclerView() {
        productsAdapter = ProductsAdapter(emptyList())
        val layoutManager = GridLayoutManager(context, 1)
        binding.rvProducts.apply {
            this.layoutManager = layoutManager
            adapter = productsAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val visibleItemCount = layoutManager.childCount
                    val totalItemCount = layoutManager.itemCount
                    val firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

                    if (!isLoading && totalItemCount <= (firstVisibleItem + visibleItemCount)) {
                        isLoading = true
                        productViewModel.loadMoreProducts(currentSortOption.value)
                    }
                    isLoading = false
                }
            })
        }
    }

    private fun initFilter() {
        binding.etFilterEt.addTextChangedListener { productFilter ->
            val filteredList = productViewModel.products.value.filter { products ->
                Log.d("Products", "product: ${products.productId} ${products.productDisplayName}")
                products.productDisplayName!!.lowercase().contains(productFilter.toString().trim().lowercase())
            }
            productsAdapter.updateList(filteredList)
            binding.isEmptyProducts.isVisible = filteredList.isEmpty()
        }
    }

    private fun initSort() {
        val options = SortOptions.entries.map { it.value }.toTypedArray()

        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Selecciona un filtro")
            .setItems(options) { dialog, which ->
                currentSortOption = SortOptions.entries.toTypedArray()[which]
                productViewModel.getAllProducts(currentSortOption.value)
                binding.rvProducts.layoutManager?.scrollToPosition(0)
                dialog.dismiss()
            }.setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()
            }
        builder.create().show()
    }


    private fun initLoader() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                productViewModel.isLoading.collect { loading ->
                    if (loading) {
                        binding.pbar.isVisible = true
                        binding.isEmptyProducts.isVisible = false
                    } else {
                        binding.pbar.isVisible = false
                    }
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}