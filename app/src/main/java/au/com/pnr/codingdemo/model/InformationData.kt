package au.com.pnr.codingdemo.model

import com.google.gson.annotations.SerializedName

data class InformationData(
    @SerializedName("title")
    val title: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("imageHref")
    val imageHref: String?
)