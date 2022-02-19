package ru.example.wsrfood.ui.signup

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.wajahatkarim3.easyvalidation.core.view_ktx.validator
import ru.example.wsrfood.R
import ru.example.wsrfood.databinding.FragmentSignUpBinding
import ru.example.wsrfood.ui.core.BaseFragment
import ru.example.wsrfood.viewmodel.core.Status
import ru.example.wsrfood.viewmodel.signup.SignUpViewModel

class SignUp : BaseFragment<SignUpViewModel, FragmentSignUpBinding>() {

    override val viewModel: SignUpViewModel by viewModels()

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSignUpBinding =
        FragmentSignUpBinding.inflate(inflater, container, false)

    override fun setupViews() {
        with(binding) {
            btnLogin.setOnClickListener {
                tietEmail.text.toString()
                    .validator()
                    .validEmail()
                    .addErrorCallback { viewModel.isValid = false }
                    .check()

                tietPassword.text.toString()
                    .validator()
                    .nonEmpty()
                    .addErrorCallback { viewModel.isValid = false }
                    .check()

                tietFullName.text.toString()
                    .validator()
                    .nonEmpty()
                    .addErrorCallback { viewModel.isValid = false }
                    .check()

                tietPhoneNumber.text.toString()
                    .validator()
                    .nonEmpty()
                    .addErrorCallback { viewModel.isValid = false }
                    .check()

                if (!viewModel.isValid) showErrorDialog("Проверьте правильность заполнения полей")
                else {
                    viewModel.postRegister(
                        tietEmail.text.toString(),
                        tietPassword.text.toString(),
                        tietFullName.text.toString()
                    )
                    observeResponse(viewModel.authRequest) {
                        when (it.status) {
                            Status.SUCCESS -> {
                                findNavController().navigate(R.id.action_signUp_to_mainFragment)
                            }
                            Status.ERROR -> {
                                showErrorDialog()
                            }
                        }
                    }
                }
            }

            btnCancel.setOnClickListener { findNavController().navigate(R.id.action_signUp_to_signIn) }
        }
    }
}