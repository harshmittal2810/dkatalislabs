package com.harsh.dkatalislabs.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.harsh.dkatalislabs.R.layout
import kotlinx.android.synthetic.main.fragment_user_info.tv_sub_title
import kotlinx.android.synthetic.main.fragment_user_info.tv_title

class UserInfoFragment : Fragment() {
    private var title: String = ""
    private var subtitle: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // get title
        title = arguments?.getString("title") ?: ""
        subtitle = arguments?.getString("sub_title") ?: ""
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return View.inflate(context, layout.fragment_user_info, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_title.text = title
        tv_sub_title.text = subtitle
    }
}