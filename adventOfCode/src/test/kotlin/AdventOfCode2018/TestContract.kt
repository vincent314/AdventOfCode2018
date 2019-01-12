package AdventOfCode2018

import org.amshove.kluent.shouldBe
import org.junit.Test
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

fun Any?.doSomething() {
    require(this is Collection<*>)
    println(this.size)
}

@UseExperimental(ExperimentalContracts::class)
fun Int?.isValid(): Boolean {
    contract{
        returns(true) implies (this@isValid != null)
    }
    return this != null && this > 0
}

fun double(value:Int) = value * 2

class TestContract {
    @Test
    fun testDoSomething() {
        val a = 5
        a.isValid() shouldBe true
        val b:Int? = null
        if(b.isValid()){
           double(b) shouldBe 4
        }
    }
}