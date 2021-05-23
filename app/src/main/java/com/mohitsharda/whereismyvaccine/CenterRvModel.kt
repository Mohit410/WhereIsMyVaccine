package com.mohitsharda.whereismyvaccine

data class CenterRvModel(
    val centerName: String,
    val centerAddress: String,
    val centerFromTiming: String,
    var centerToTiming: String,
    var feeType: String,
    var ageLimit: Int,
    var vaccineName: String,
    var availableCapacity: Int
)