package com.dicoding.jetpacksubmission.utils

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.InsetDrawable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import androidx.annotation.NonNull
import androidx.appcompat.app.AlertDialog
import com.dicoding.jetpacksubmission.R
import kotlinx.android.synthetic.main.layout_alert_dialog.view.*

fun showCancelableAlertDialog(
    context: Context,
    title : String? = null,
    message : String? = null,
    positive: String? = null, @NonNull positiveListener: (() -> Unit)? = null,
    negative: String? = null, @NonNull negativeListener: (() -> Unit)? = null
) {
    val viewWithoutIcon : View = LayoutInflater.from(context).inflate(R.layout.layout_alert_dialog, null)

    with(viewWithoutIcon) {
        tvTitle.text = title

        if (message != null) {
            tvMessage.visible()
            tvMessage.text = message
        }

        if(positive != null) {
            btnAdd.visible()
            btnAdd.text = positive
        }

        if(negative != null) {
            btnCancel.visible()
            btnCancel.text = negative
        }
    }

    val builderWithoutIcon = AlertDialog.Builder(context).setView(viewWithoutIcon)
    val dialogWithoutIcon = builderWithoutIcon.create()

    dialogWithoutIcon.apply {
        window?.apply {
            setLayout(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT
            )
            setGravity(Gravity.CENTER)
            val inset = InsetDrawable(ColorDrawable(Color.TRANSPARENT), 60)
            setBackgroundDrawable(inset)
        }
        setCancelable(true)
        show()
    }

    with(viewWithoutIcon) {
        btnAdd.onClick {
            dialogWithoutIcon.dismiss()
            positiveListener?.invoke()
        }
        btnCancel.onClick {
            dialogWithoutIcon.dismiss()
            negativeListener?.invoke()
        }
    }
}