package com.zcf.composeexercise

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import org.junit.Assert
import org.junit.Test

class JsonTest {

  enum class Color { BLACK, WHITE }

  @Serializable
  data class Brush(val foreground: Color? = Color.BLACK, val background: Color?)

  @Test(expected = SerializationException::class)
  fun `Json serialization - Invalid Enum values - throws exception`() {
    val json = Json

    val brush = json.decodeFromString<Brush>("""{"foreground":"pink", "background":"purple"}""")
    println(brush)
  }

  @Test
  fun `Json serialization - Invalid Enum values - returns default or null`() {
    val json = Json {
      coerceInputValues = true
      explicitNulls = false
    }

    val brush = json.decodeFromString<Brush>("""{"foreground":"pink", "background":"purple"}""")
    println(brush)
    Assert.assertEquals(brush, Brush(foreground = Color.BLACK, background = null))
  }
}