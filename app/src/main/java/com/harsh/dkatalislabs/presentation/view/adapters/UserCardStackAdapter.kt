package com.harsh.dkatalislabs.presentation.view.adapters

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener
import com.harsh.dkatalislabs.R
import com.harsh.dkatalislabs.model.entities.User
import com.harsh.dkatalislabs.presentation.view.fragments.UserInfoFragment
import com.harsh.dkatalislabs.util.CircleImageView
import com.harsh.dkatalislabs.util.inflate
import com.harsh.dkatalislabs.util.loadDate
import com.harsh.dkatalislabs.util.loadUrl
import com.harsh.dkatalislabs.util.toTitleCase
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx

class UserCardStackAdapter(
    val context: Context, private var spots: List<User> = emptyList()
) : RecyclerView.Adapter<UserCardStackAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_user))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userData = spots[position]
        holder.userImage.loadUrl(userData.picture)
        holder.bottomBar.onNavigationItemSelectedListener =
            OnNavigationItemSelectedListener { item ->
                val fragment: Fragment
                when (item.itemId) {
                    R.id.action_user -> {
                        fragment = UserInfoFragment()
                        val bundle = Bundle()
                        bundle.putString("title", "User Details")
                        bundle.putString(
                            "sub_title",
                            "${userData.name.title}. ${userData.name.first} ${userData.name.last}".toTitleCase()
                        )
                        fragment.arguments = bundle
                        loadFragment(context, fragment)
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.action_calendar -> {
                        fragment = UserInfoFragment()
                        val bundle = Bundle()
                        bundle.putString("title", "Registered")
                        bundle.putString("sub_title", userData.registered.loadDate())
                        fragment.arguments = bundle
                        loadFragment(context, fragment)
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.action_map -> {
                        fragment = UserInfoFragment()
                        val bundle = Bundle()
                        bundle.putString("title", "Location")
                        bundle.putString("sub_title", userData.location.city)
                        fragment.arguments = bundle
                        loadFragment(context, fragment)
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.action_call -> {
                        fragment = UserInfoFragment()
                        val bundle = Bundle()
                        bundle.putString("title", "Contact Info")
                        bundle.putString("sub_title", userData.cell)
                        fragment.arguments = bundle
                        loadFragment(context, fragment)
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.action_lock -> {
                        fragment = UserInfoFragment()
                        val bundle = Bundle()
                        bundle.putString("title", "Security")
                        bundle.putString("sub_title", userData.SSN)
                        fragment.arguments = bundle
                        loadFragment(context, fragment)
                        return@OnNavigationItemSelectedListener true
                    }
                    else -> {
                        fragment = UserInfoFragment()
                        val bundle = Bundle()
                        bundle.putString("title", "User Name")
                        bundle.putString("sub_title", userData.username)
                        fragment.arguments = bundle
                        loadFragment(context, fragment)
                        return@OnNavigationItemSelectedListener true
                    }
                }
            }
        holder.bottomBar.selectedItemId = R.id.action_user
    }

    override fun getItemCount(): Int {
        return spots.size
    }

    fun setSpots(spots: List<User>) {
        this.spots = spots
    }

    fun getSpots(): List<User> {
        return spots
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val userImage: CircleImageView = view.findViewById(R.id.userImage)
        val bottomBar: BottomNavigationViewEx = view.findViewById(R.id.bottom_bar)
    }

    private fun loadFragment(context: Context, fragment: Fragment) { // load fragment
        val transaction: FragmentTransaction =
            (context as AppCompatActivity).supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
