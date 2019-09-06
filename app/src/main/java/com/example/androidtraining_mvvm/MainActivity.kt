package com.example.androidtraining_mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), LifecycleObserver {
    private lateinit var lifecycleRegistry: LifecycleRegistry
    private lateinit var contributorViewmodel: ContributorViewmodel
    private lateinit var adapter: ContributorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycleRegistry = LifecycleRegistry(this)
        setupListView()
        setupViewModel()
    }

    private fun setupListView(){
        adapter = ContributorAdapter(this, contributorViewmodel.getContributors().value ?: listOf())
        lw_main_contributors.adapter = adapter
    }

    private fun setupViewModel() {
        contributorViewmodel = ViewModelProviders
            .of(this)
            .get(ContributorViewmodel::class.java)
        contributorViewmodel.getContributors().observe(
            this,
            object : Observer<List<Contributor>> {
                override fun onChanged(contributors: List<Contributor>?) {
                    updateListView(contributors ?: listOf())
                }
            }
        )
    }

    private fun updateListView(contributors: List<Contributor>){
        adapter.notifyDataSetChanged()
    }
}
