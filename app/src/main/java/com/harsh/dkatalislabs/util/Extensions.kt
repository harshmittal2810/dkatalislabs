package com.harsh.dkatalislabs.util

import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import java.util.Calendar
import java.util.Locale

fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}

fun ImageView.loadUrl(url: String) {
    Glide.with(context).load(url).into(this)
}

fun String.loadDate(): String {
    val timestamp = this.toLong()
    val calendar = Calendar.getInstance(Locale.ENGLISH)
    calendar.timeInMillis = timestamp * 1000L
    return DateFormat.format("dd-MM-yyyy", calendar).toString()
}

fun String.toTitleCase(): String {
    if (this.isEmpty()) {
        return ""
    }
    var space = true
    val builder = StringBuilder(this)
    val len = builder.length
    for (i in 0 until len) {
        val c = builder[i]
        if (space) {
            if (!Character.isWhitespace(c)) {
                builder.setCharAt(i, Character.toTitleCase(c))
                space = false
            }
        } else if (Character.isWhitespace(c)) {
            space = true
        } else {
            builder.setCharAt(i, Character.toLowerCase(c))
        }
    }
    return builder.toString()
}