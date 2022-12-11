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
import ru.karpenko.dzen.presentation.util.CARDS_ON_LANDSCAPE_ORIENTATION
import ru.karpenko.dzen.presentation.util.CARDS_ON_PORTRAIT_ORIENTATION
import ru.karpenko.dzen.presentation.util.isLandscapeOrientation

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
        val cardsOnPage =
            if (requireContext().isLandscapeOrientation()) CARDS_ON_LANDSCAPE_ORIENTATION
            else CARDS_ON_PORTRAIT_ORIENTATION

        binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        binding.categoriesRecyclerView.apply {
            adapter = categoriesAdapter
            layoutManager = GridLayoutManager(requireContext(), cardsOnPage)
        }

        categoriesAdapter?.submitList(
            listOf(
                Category(1, "SPORT", "ABOUT SPORT", R.drawable.sport_32),
                Category(2, "HEALTH", "ABOUT HEALTH", R.drawable.health),
                Category(3, "BUSINESS", "ABOUT BUSINESS", R.drawable.business),
                Category(4, "CAR", "ABOUT CARS", R.drawable.car_svgrepo_acom),
                Category(5, "GAME", "ABOUT GAMES", R.drawable.games_svgrepo_com__1_),
                Category(6, "IT", "ABOUT IT", R.drawable.laptop_svgrepo_com),
                Category(7, "WEATHER", "ABOUT WHETHER", R.drawable.bad_weather_svgrepo_com),
                Category(8, "MUSIC", "ABOUT MUSIC", R.drawable.music_svgrepo_com),
                Category(9, "PEOPLE", "ABOUT PEOPLE", R.drawable.people_svgrepo_com),
            )
        )
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        categoriesAdapter = null
    }
}