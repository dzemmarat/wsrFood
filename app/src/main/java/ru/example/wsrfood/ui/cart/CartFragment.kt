package ru.example.wsrfood.ui.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import ru.example.wsrfood.databinding.FragmentCartBinding
import ru.example.wsrfood.ui.core.BaseFragment
import ru.example.wsrfood.viewmodel.core.EmptyViewModel

class CartFragment: BaseFragment<EmptyViewModel, FragmentCartBinding>() {

    override val viewModel: EmptyViewModel by viewModels()

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCartBinding =
        FragmentCartBinding.inflate(inflater, container, false)

    override fun setupViews() {

    }
}