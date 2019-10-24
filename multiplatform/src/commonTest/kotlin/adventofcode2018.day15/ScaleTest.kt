package adventofcode2018.day15

import kotlin.js.JsName
import kotlin.test.Test
import kotlin.test.assertEquals

class ScaleTest {
    @Test
    @JsName("correctValue")
    fun `should get a correct value`(){
        val scale = Scale(0.0, 5.0, 0.0, 100.0)
        assertEquals(0.0, scale[0.0])
        assertEquals(20.0, scale[1.0])
        assertEquals(50.0, scale[2.5])
        assertEquals(40.0, scale[2.0])
        assertEquals(100.0, scale[5.0])
    }
}
