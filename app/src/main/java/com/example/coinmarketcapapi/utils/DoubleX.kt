package com.example.coinmarketcapapi.utils

import java.text.DecimalFormat

//Separating the number by commas.

fun Double.format(): String =
    DecimalFormat("#.##").apply {
        isGroupingUsed = true
        groupingSize = 3
    }.format(this)
