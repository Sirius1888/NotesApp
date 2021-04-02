package com.example.noteapplication

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.Toast


fun Context.showToast(message: String?) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun View.visible() {

}




//Примеры:
/* fun setupViews() {
    val count = 100
    var sum = "$count сом"
    var sumExtension = count.toString().toSom()
}

fun View.toGone() {
    this.visibility = View.GONE
}

tv_name.visibility = View.GONE
tv_name.toGone()

fun showToast1(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}
 */