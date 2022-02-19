package ru.example.wsrfood.ui.onnoarding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import ru.example.wsrfood.databinding.FragmentOnboardingOneBinding
import ru.example.wsrfood.ui.core.BaseFragment
import ru.example.wsrfood.viewmodel.core.EmptyViewModel

class OnBoardingFragmentOne: BaseFragment<EmptyViewModel, FragmentOnboardingOneBinding>() {

    override val viewModel: EmptyViewModel by viewModels()

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentOnboardingOneBinding =
        FragmentOnboardingOneBinding.inflate(inflater, container, false)

    override fun setupViews() {

    }
}