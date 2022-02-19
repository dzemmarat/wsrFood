package ru.example.wsrfood.ui.onnoarding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.example.wsrfood.R
import ru.example.wsrfood.databinding.FragmentOnboardingOneBinding
import ru.example.wsrfood.databinding.FragmentOnboardingTwoBinding
import ru.example.wsrfood.ui.core.BaseFragment
import ru.example.wsrfood.viewmodel.core.EmptyViewModel

class OnBoardingFragmentTwo: BaseFragment<EmptyViewModel, FragmentOnboardingTwoBinding>() {

    override val viewModel: EmptyViewModel by viewModels()

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentOnboardingTwoBinding =
        FragmentOnboardingTwoBinding.inflate(inflater, container, false)

    override fun setupViews() {
        with(binding) {
            btnSignIn.setOnClickListener {
                findNavController().navigate(R.id.action_onBoardingFragmentContainer_to_signIn)
            }
            btnSignUp.setOnClickListener {
                findNavController().navigate(R.id.action_onBoardingFragmentContainer_to_signUp)
            }
        }
    }
}