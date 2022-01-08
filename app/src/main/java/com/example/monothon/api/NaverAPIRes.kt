package com.example.monothon.api

import com.example.monothon.api.model.Face
import com.example.monothon.api.model.Info

data class NaverAPIRes(
    val faces: List<Face>,
    val info: Info
)