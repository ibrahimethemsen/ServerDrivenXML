package com.ibrahimethemsen.serverdrivenxml.model

import com.google.gson.annotations.SerializedName

data class ContainerMock(
    @SerializedName("container_mock_title")
    val containerMockTitle : String = "Container Mock"
)