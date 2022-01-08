package com.example.monothon.api.model

data class Face(
    val age: Age,
    val emotion: Emotion,
    val gender: Gender,
    val landmark: Landmark,
    val pose: Pose,
    val roi: Roi
)