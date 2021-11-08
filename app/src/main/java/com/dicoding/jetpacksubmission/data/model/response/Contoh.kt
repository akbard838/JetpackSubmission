package com.dicoding.jetpacksubmission.data.model.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Contoh(
    val subjectName: String?,
    var isSelected: Boolean?
) : Parcelable