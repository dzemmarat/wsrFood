package ru.example.wsrfood.ui.signin

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.wajahatkarim3.easyvalidation.core.view_ktx.validator
import ru.example.wsrfood.R
import ru.example.wsrfood.databinding.FragmentSignInBinding
import ru.example.wsrfood.ui.core.BaseFragment
import ru.example.wsrfood.viewmodel.core.Status
import ru.example.wsrfood.viewmodel.signin.SignInViewModel

class SignIn : BaseFragment<SignInViewModel, FragmentSignInBinding>() {

    override val viewModel: SignInViewModel by viewModels()

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSignInBinding =
        FragmentSignInBinding.inflate(inflater, container, false)

    override fun setupViews() {
        with(binding){
            btnLogin.setOnClickListener {
                tietEmail.text.
                    toString()
                    .validator()
                    .validEmail()
                    .addErrorCallback {
                        viewModel.isValid = false
                    }
                    .check()

                tietPassword.text
                    .toString()
                    .validator()
                    .nonEmpty()
                    .addErrorCallback {
                        viewModel.isValid = false
                    }
                    .check()

                if (!viewModel.isValid) showErrorDialog("Неверный логин или пароль")
                else {
                    viewModel.postAuth(
                        tietEmail.text.toString(),
                        tietPassword.text.toString()
                    )
                    observeResponse(viewModel.authRequest) {
                        when (it.status) {
                            Status.SUCCESS -> {
                                findNavController().navigate(R.id.action_signIn_to_mainFragment)
                            }
                            Status.ERROR -> {
                                showErrorDialog()
                            }
                        }
                    }
                }
            }
        }
    }
}