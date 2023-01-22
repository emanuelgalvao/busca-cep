package com.emanuelgalvao.buscacep

import android.content.DialogInterface
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.emanuelgalvao.buscacep.databinding.ActivityMainBinding
import com.emanuelgalvao.buscacep.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_activity_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.menu_info) {
            showMessageDialog(getString(R.string.TITLE_INFO), getString(R.string.HELP_INFO))
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider.NewInstanceFactory().create(MainViewModel::class.java)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setupObservers()
        setupListeners()

        setContentView(binding.root)
    }

    private fun setupObservers() {
        viewModel.requesting.observe(this) { requesting ->
            binding.pbRequesting.visibility = if (requesting) View.VISIBLE else View.GONE
            binding.tlCep.visibility = if (requesting) View.GONE else View.VISIBLE
            binding.btSearchCep.visibility = if (requesting) View.GONE else View.VISIBLE
        }

        viewModel.success.observe(this) { cepModel ->

            val cepDetails = getString(
                R.string.CEP_SUCCESS_DETAILS,
                cepModel.cep,
                cepModel.logradouro,
                cepModel.complemento,
                cepModel.bairro,
                cepModel.localidade,
                cepModel.uf,
                cepModel.ibge,
                cepModel.gia,
                cepModel.ddd,
                cepModel.siafi)

            showMessageDialog(getString(R.string.TITLE_SUCCESS), cepDetails)
        }

        viewModel.error.observe(this) { errorStatus ->
            showMessageDialog(getString(R.string.TITLE_ERROR), errorStatus.message)
        }

        viewModel.validation.observe(this) { validationStatus ->
            showMessageDialog(getString(R.string.TITLE_VALIDATION), validationStatus.message)
        }
    }

    private fun setupListeners() {
        binding.btSearchCep.setOnClickListener {
            searchCep()
        }
    }

    private fun searchCep() {
        val cep = binding.etCep.text.toString()
        viewModel.searchCep(cep)
    }

    private fun showMessageDialog(title: String, message: String) {

        val builder: AlertDialog.Builder = AlertDialog.Builder(this)

        builder.setTitle(title)
        builder.setMessage(message)

        builder.setNegativeButton(getString(R.string.CLOSE)) { dialog: DialogInterface, _: Int ->
            dialog.cancel()
        }

        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()
    }
}