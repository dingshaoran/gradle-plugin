package com.visitor

import java.io.IOException
import org.json.JSONObject

class DemoK {


    private fun demo() {
        val aa = JSONObject("aaaa")
        System.out.println(aa)
        throw IOException()
    }

    fun invoke() {
        demo()
    }
}