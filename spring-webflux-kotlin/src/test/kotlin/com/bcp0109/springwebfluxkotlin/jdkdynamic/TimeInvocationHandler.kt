package com.bcp0109.springwebfluxkotlin.jdkdynamic

import org.springframework.util.StopWatch
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method

class TimeInvocationHandler(
    private val target: Any?
) : InvocationHandler {

    override fun invoke(proxy: Any?, method: Method?, args: Array<Any>?): Any? {
        println("TimeProxy 실행")
        val stopWatch = StopWatch()
        stopWatch.start()

        val result = method!!.invoke(target, *args.orEmpty())

        stopWatch.stop()
        println("TimeProxy 종료 resultTime=${stopWatch.totalTimeSeconds}")
        return result
    }
}
