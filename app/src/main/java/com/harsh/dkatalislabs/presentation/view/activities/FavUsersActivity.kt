package com.harsh.dkatalislabs.presentation.view.activities

import android.os.Bundle
import android.util.TypedValue
import androidx.recyclerview.widget.LinearLayoutManager
import com.harsh.dkatalislabs.R
import com.harsh.dkatalislabs.model.entities.User
import com.harsh.dkatalislabs.presentation.presenters.FavUserPresenter
import com.harsh.dkatalislabs.presentation.view.FavUserView
import com.harsh.dkatalislabs.presentation.view.adapters.UserListAdapter
import com.harsh.dkatalislabs.util.MyDividerItemDecoration
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_fav_user.contentLoading
import kotlinx.android.synthetic.main.activity_fav_user.recyclerView
import kotlinx.android.synthetic.main.activity_user.toolbar
import javax.inject.Inject

/**
 * Author: Harsh Mittal
 * Date:   2020-02-17
 * Github: https://github.com/harshmittal2810
 */
class FavUsersActivity : BaseActivity(), FavUserView {

    @Inject
    lateinit var presenter: FavUserPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_fav_user)
        setSupportActionBar(toolbar)
        presenter.attachView(this)
        presenter.getRandomUserList()
        initAdapter()

        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        supportActionBar?.title = "Favourite User"

    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    private val adapter by lazy {
        val userList = mutableListOf<User>()
        UserListAdapter(userList)
    }

    private fun initAdapter() {
        val space =
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20f, resources.displayMetrics)
                .toInt()
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(
            MyDividerItemDecoration(this, MyDividerItemDecoration.VERTICAL_LIST, space)
        )
        recyclerView.adapter = adapter
    }

    override fun showLoading() {
        contentLoading.show()
    }

    override fun hideLoading() {
        contentLoading.hide()
    }

    override fun addUsers(userData: List<User>) {
        adapter.addUsers(userData.reversed())
    }

    override fun showEmptyListError() {

    }

}