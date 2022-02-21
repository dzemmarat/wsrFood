package ru.example.wsrfood.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.coroutines.flow.collect
import ru.example.wsrfood.databinding.FragmentMainBinding
import ru.example.wsrfood.ui.core.BaseFragment
import ru.example.wsrfood.ui.core.DelegationAdapter
import ru.example.wsrfood.ui.main.adapter.DishesDelegate
import ru.example.wsrfood.viewmodel.main.MainViewModel

class MainFragment : BaseFragment<MainViewModel, FragmentMainBinding>() {

    override val viewModel: MainViewModel by viewModels()
    private val dishesAdapter by lazy { DelegationAdapter() }

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMainBinding =
        FragmentMainBinding.inflate(inflater, container, false)

    override fun setupViews() {
        viewModel.setupDatabase(requireContext())
        viewModel.getDishes()
        mainActivity.showBottomMenu()

        dishesAdapter.delegatesManager.addDelegate(DishesDelegate())
        binding.rvDishes.apply {
            adapter = dishesAdapter
            layoutManager = GridLayoutManager(context, 2)
        }

        lifecycleScope.launchWhenStarted {
            viewModel.dishes.collect {
                dishesAdapter.items = it
            }
        }
    }
}