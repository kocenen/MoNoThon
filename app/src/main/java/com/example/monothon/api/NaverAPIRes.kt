package com.example.monothon.api

import com.example.monothon.api.model.Face
import com.example.monothon.api.model.Info

data class NaverAPIRes(
    val info: Info,
    val faces: List<Face>?
)