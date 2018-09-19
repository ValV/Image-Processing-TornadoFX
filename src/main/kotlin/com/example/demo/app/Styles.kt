package com.example.demo.app

import javafx.scene.text.FontWeight
import tornadofx.*

class Styles : Stylesheet() {
    companion object {
        val heading by cssclass()
        val container by cssclass()
        val footer by cssclass()
    }

    init {
        label and heading {
            padding = box(10.px)
            fontSize = 16.px
            fontWeight = FontWeight.BOLD
        }
        container {
            padding = box(4.px, 4.px)
        }
        footer {
            padding = box(4.px, 0.px)
        }
    }
}