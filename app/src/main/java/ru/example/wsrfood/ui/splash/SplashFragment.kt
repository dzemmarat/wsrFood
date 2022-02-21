package ru.example.wsrfood.ui.splash

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.example.wsrfood.R
import ru.example.wsrfood.databinding.FragmentSplashBinding
import ru.example.wsrfood.extensions.gone
import ru.example.wsrfood.extensions.isOnline
import ru.example.wsrfood.extensions.showToast
import ru.example.wsrfood.ui.core.BaseFragment
import ru.example.wsrfood.viewmodel.core.Status
import ru.example.wsrfood.viewmodel.splash.SplashViewModel

class SplashFragment : BaseFragment<SplashViewModel, FragmentSplashBinding>() {

    override val viewModel: SplashViewModel by viewModels()

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSplashBinding =
        FragmentSplashBinding.inflate(inflater, container, false)

    override fun setupViews() {
        viewModel.setupDatabase(requireContext())

        if (!requireContext().isOnline()) {
            binding.progressBar.gone()
            Handler(Looper.getMainLooper()).postDelayed({
                findNavController().navigate(R.id.action_splashFragment_to_mainFragment)
            }, 2000)
        }

        viewModel.getVersion()

        observeVersions()
        observeDishes()
    }

    private fun observeVersions() {
        observeResponse(viewModel.version) {
            when (it.status) {
                Status.SUCCESS -> {
                    if (!viewModel.isVersionsEquals.value) {
                        viewModel.getDishes()
                        viewModel.insertVersionsInDb()
                    } else {
                        findNavController().navigate(R.id.action_splashFragment_to_onBoardingFragmentContainer)
                    }
                }
                Status.ERROR -> {
                    showErrorDialog()
                }
                else -> {}
            }
        }
    }

    private fun observeDishes() {
        observeResponse(viewModel.dishes) {
            when (it.status) {
                Status.SUCCESS -> {
                    viewModel.insertDishesInDb()
                    showToast(it.data.toString())
                }
                Status.ERROR -> {
                    showErrorDialog()
                }
                else -> {}
            }
        }
    }
}