package com.ibrahimethemsen.serverdrivenxml.model

import com.google.gson.annotations.SerializedName
import com.ibrahimethemsen.serverdrivenxml.DEF_FLOAT
import com.ibrahimethemsen.serverdrivenxml.DEF_STRING

data class ContainerActivity(
    @SerializedName("container_activity_title")
    val title : String = String.DEF_STRING,
    @SerializedName("container_activity_title_size")
    val titleSize : Float = Float.DEF_FLOAT,
    @SerializedName("container_activity_title_color")
    val titleColor : String = String.DEF_STRING,
    @SerializedName("container_activity_end_title")
    val endTitle : String = String.DEF_STRING,
    @SerializedName("container_activity_end_click")
    val endClick : String = String.DEF_STRING,
    @SerializedName("container_activity_item")
    val adapterActivity : AdapterActivity = AdapterActivity()
)
