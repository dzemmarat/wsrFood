package ru.example.wsrfood.ui.person

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import ru.example.wsrfood.databinding.FragmentPersonBinding
import ru.example.wsrfood.ui.core.BaseFragment
import ru.example.wsrfood.viewmodel.core.EmptyViewModel

class PersonFragment: BaseFragment<EmptyViewModel, FragmentPersonBinding>() {

    override val viewModel: EmptyViewModel by viewModels()

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPersonBinding =
        FragmentPersonBinding.inflate(inflater, container, false)

    override fun setupViews() {

    }
}