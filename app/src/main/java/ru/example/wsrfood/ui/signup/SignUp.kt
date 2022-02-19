package ru.example.wsrfood.ui.signup

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.wajahatkarim3.easyvalidation.core.view_ktx.validator
import ru.example.wsrfood.R
import ru.example.wsrfood.databinding.FragmentOnboardingOneBinding
import ru.example.wsrfood.databinding.FragmentSignUpBinding
import ru.example.wsrfood.ui.core.BaseFragment
import ru.example.wsrfood.viewmodel.core.EmptyViewModel
import ru.example.wsrfood.viewmodel.signup.SignUpViewModel
import java.util.regex.Pattern

class SignUp: BaseFragment<SignUpViewModel, FragmentSignUpBinding>() {

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
            }

            btnCancel.setOnClickListener { findNavController().navigate(R.id.action_signUp_to_signIn) }
        }
    }
}