package com.harsh.dkatalislabs.presentation.view.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DiffUtil
import com.harsh.dkatalislabs.BuildConfig
import com.harsh.dkatalislabs.R
import com.harsh.dkatalislabs.model.entities.User
import com.harsh.dkatalislabs.presentation.presenters.UserPresenter
import com.harsh.dkatalislabs.presentation.view.UserView
import com.harsh.dkatalislabs.presentation.view.adapters.UserCardStackAdapter
import com.harsh.dkatalislabs.presentation.view.adapters.UserDiffCallback
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.CardStackView
import com.yuyakaido.android.cardstackview.Direction
import com.yuyakaido.android.cardstackview.Direction.Right
import com.yuyakaido.android.cardstackview.StackFrom
import com.yuyakaido.android.cardstackview.SwipeableMethod
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_user.toolbar
import java.lang.reflect.Method
import javax.inject.Inject

/**
 * Author: Harsh Mittal
 * Date:   2020-02-14
 * Github: https://github.com/harshmittal2810
 */
class UsersActivity : BaseActivity(), UserView, CardStackListener {

    @Inject
    lateinit var presenter: UserPresenter
    lateinit var userData: User

    private val cardStackView by lazy { findViewById<CardStackView>(R.id.card_stack_view) }
    private val manager by lazy { CardStackLayoutManager(this, this) }
    private val adapter by lazy { UserCardStackAdapter(this, mutableListOf()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_user)
        setSupportActionBar(toolbar)
        presenter.attachView(this)
        presenter.getRandomUser()
        setupCardStackView()
        showDebugDBAddressLogToast(this)
    }

    private fun showDebugDBAddressLogToast(context: Context) {
        if (BuildConfig.DEBUG) {
            try {
                val debugDB = Class.forName("com.amitshekhar.DebugDB")
                val getAddressLog: Method = debugDB.getMethod("getAddressLog")
                val value: Any = getAddressLog.invoke(null)
                Toast.makeText(context, value as String, Toast.LENGTH_LONG).show()
            } catch (ignore: Exception) {
                ignore.printStackTrace()
            }
        }
    }

    private fun setupCardStackView() {
        initialize()
    }

    private fun initialize() {
        manager.setStackFrom(StackFrom.Top)
        manager.setVisibleCount(1)
        manager.setTranslationInterval(8.0f)
        manager.setScaleInterval(0.95f)
        manager.setSwipeThreshold(0.3f)
        manager.setMaxDegree(20.0f)
        manager.setDirections(Direction.HORIZONTAL)
        manager.setCanScrollHorizontal(true)
        manager.setCanScrollVertical(true)
        manager.setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
        manager.setOverlayInterpolator(LinearInterpolator())
        cardStackView.layoutManager = manager
        cardStackView.adapter = adapter
        cardStackView.itemAnimator.apply {
            if (this is DefaultItemAnimator) {
                supportsChangeAnimations = false
            }
        }
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean { // Handle item selection
        return when (item.itemId) {
            R.id.action_favourite -> {
                startActivity(Intent(this, FavUsersActivity::class.java))
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun addUsers(userData: User) {
        this.userData = userData
        addFirst(userData)
    }

    override fun showEmptyListError() {

    }

    override fun showError(error: String) {

    }

    override fun hideEmptyListError() {

    }

    override fun showToastError() {

    }

    override fun clearList() {

    }

    override fun onCardDragging(direction: Direction, ratio: Float) {
        Log.d("CardStackView", "onCardDragging: d = ${direction.name}, r = $ratio")
    }

    override fun onCardSwiped(direction: Direction) {
        Log.d("CardStackView", "onCardSwiped: p = ${manager.topPosition}, d = $direction")
        if (direction == Right) {
            presenter.saveUser(userData)
        }

        if (manager.topPosition == adapter.itemCount) {
            presenter.getRandomUser()
        }
    }

    override fun onCardRewound() {
        Log.d("CardStackView", "onCardRewound: ${manager.topPosition}")
    }

    override fun onCardCanceled() {
        Log.d("CardStackView", "onCardCanceled: ${manager.topPosition}")
    }

    override fun onCardAppeared(view: View, position: Int) {
        Log.d("CardStackView", "onCardAppeared: ($position)")
    }

    override fun onCardDisappeared(view: View, position: Int) {
        Log.d("CardStackView", "onCardDisappeared: ($position)")
    }

    private fun addFirst(userData: User) {
        val old = adapter.getSpots()
        val new = mutableListOf<User>().apply {
            addAll(old)
            if (size > 0) {
                for (i in 0 until size) {
                    add(manager.topPosition, userData)
                }
            } else {
                add(manager.topPosition, userData)
            }
        }
        val callback = UserDiffCallback(old, new)
        val result = DiffUtil.calculateDiff(callback)
        adapter.setSpots(new)
        result.dispatchUpdatesTo(adapter)
    }

}