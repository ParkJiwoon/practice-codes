package com.bcp0109.springwebfluxkotlin.jdkdynamic

import org.junit.jupiter.api.Test
import java.lang.reflect.Proxy

class JdkDynamicProxyTest {

    @Test
    fun test() {
        val target = FooImpl()
        val handler = TimeInvocationHandler(target)

        val proxy: FooInterface = Proxy.newProxyInstance(
            FooInterface::class.java.classLoader,
            arrayOf(FooInterface::class.java),
            handler
        ) as FooInterface

        proxy.call()
        println("targetClass = ${target.javaClass}")
        println("proxyClass = ${proxy.javaClass}")
    }
}
