package ru.example.wsrfood.ui.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import ru.example.wsrfood.databinding.FragmentHistoryBinding
import ru.example.wsrfood.ui.core.BaseFragment
import ru.example.wsrfood.viewmodel.core.EmptyViewModel

class HistoryFragment: BaseFragment<EmptyViewModel, FragmentHistoryBinding>() {

    override val viewModel: EmptyViewModel by viewModels()

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHistoryBinding =
        FragmentHistoryBinding.inflate(inflater, container, false)

    override fun setupViews() {

    }
}