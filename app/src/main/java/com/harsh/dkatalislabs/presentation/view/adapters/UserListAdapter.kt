package com.harsh.dkatalislabs.presentation.view.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.harsh.dkatalislabs.R
import com.harsh.dkatalislabs.model.entities.User
import com.harsh.dkatalislabs.presentation.view.adapters.UserListAdapter.LoginViewHolder
import com.harsh.dkatalislabs.util.inflate
import com.harsh.dkatalislabs.util.loadDate
import com.harsh.dkatalislabs.util.loadUrl
import com.harsh.dkatalislabs.util.toTitleCase
import kotlinx.android.synthetic.main.item_fav_user.view.contactDetails
import kotlinx.android.synthetic.main.item_fav_user.view.locationDetails
import kotlinx.android.synthetic.main.item_fav_user.view.regDetails
import kotlinx.android.synthetic.main.item_fav_user.view.secDetails
import kotlinx.android.synthetic.main.item_fav_user.view.userDetails
import kotlinx.android.synthetic.main.item_fav_user.view.userImage

class UserListAdapter(
    private val logins: MutableList<User>
) : RecyclerView.Adapter<LoginViewHolder>() {

    override fun getItemCount() = logins.size

    override fun onBindViewHolder(holder: LoginViewHolder, position: Int) =
        holder.bind(logins[position])

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        LoginViewHolder(parent.inflate(R.layout.item_fav_user))

    fun addUsers(newUsers: List<User>) {
        logins.clear()
        logins.addAll(newUsers)
        notifyDataSetChanged()
    }

    class LoginViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(user: User) = with(itemView) {
            userImage.loadUrl(user.picture)
            userDetails.text =
                "${user.name.title}. ${user.name.first} ${user.name.last}".toTitleCase()
            regDetails.text = user.registered.loadDate()
            locationDetails.text = user.location.city.toTitleCase()
            contactDetails.text = user.cell
            secDetails.text = user.SSN
        }
    }
}