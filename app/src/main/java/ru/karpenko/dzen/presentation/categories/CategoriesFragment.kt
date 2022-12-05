package ru.karpenko.dzen.presentation.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import ru.karpenko.dzen.R
import ru.karpenko.dzen.databinding.FragmentCategoriesBinding
import ru.karpenko.dzen.domain.model.Category

class CategoriesFragment : Fragment() {

    private var categoriesAdapter: CategoriesAdapter? = null
    private lateinit var binding: FragmentCategoriesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        categoriesAdapter = CategoriesAdapter {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        binding.categoriesRecyclerView.apply {
            adapter = categoriesAdapter
            layoutManager = GridLayoutManager(requireContext(), 3)
        }

        categoriesAdapter?.submitList(
            listOf(
                Category(1, "SPORT", "ABOUT SPORT", R.drawable.sport_32),
                Category(2, "HEALTH", "ABOUT HEALTH", R.drawable.health),
                Category(3, "BUSINESS", "ABOUT BUSINESS", R.drawable.business)
            )
        )
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        categoriesAdapter = null
    }
}