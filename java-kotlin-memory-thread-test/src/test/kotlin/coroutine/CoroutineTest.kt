package coroutine

import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CoroutineTest {

    @Test
    fun `Coroutine 은 쓰레드 갯수를 증가시키지 않음`() {
        runBlocking {
            val beforeThreadCount = Thread.activeCount()
            val jobs = mutableListOf<Job>()

            // 1000 개의 서브루틴 Job 생성
            repeat(1000) {
                jobs += launch {
                    delay(1000L)
                }
            }

            val afterThreadCount = Thread.activeCount()
            assertEquals(beforeThreadCount, afterThreadCount)

            // 코루틴 전부 종료
            jobs.forEach { it.join() }
        }
    }

    @Test
    fun `Thread 는 쓰레드 갯수를 증가시킴`() {
        val beforeThreadCount = Thread.activeCount()
        val threads = mutableListOf<Thread>()

        // 1000 개의 쓰레드 생성
        repeat(1000) {
            threads += Thread {
                Thread.sleep(1000L)
            }.also {
                it.start()
            }
        }

        val afterThreadCount = Thread.activeCount()
        assertEquals(afterThreadCount - beforeThreadCount, 1000)

        // 쓰레드 전부 종료
        threads.forEach { it.join() }
    }
}
