package com.example.demo.view

import com.example.demo.controller.Filters
import com.example.demo.app.Events.*
import javafx.collections.FXCollections
import javafx.scene.image.Image
import javafx.scene.image.WritableImage
import javafx.scene.layout.Priority
import tornadofx.*

class MainView : View("Pixel Manipulation Demo") {

    private val img0 = Image("/images/photo.jpg")
    private val img1 = Image("/images/pixeldrawing.png")
    private val img2 = Image("/images/this_is_sparta.jpg")
    private val wImg0 = WritableImage(img0.pixelReader, img0.width.toInt(), img0.height.toInt())
    private val wImg1 = WritableImage(img1.pixelReader, img1.width.toInt(), img1.height.toInt())
    private val wImg2 = WritableImage(img2.pixelReader, img2.width.toInt(), img2.height.toInt())

    private val filter: Filters by inject()

    override val root = borderpane {
        addClass("container")
        center { imageview (img0) { subscribe<CommandSetFilter> { image = it.image } } }
        bottom {
            hbox {
                addClass("footer")
                label { text = "Select Filter..." }
                region { hgrow = Priority.ALWAYS }
                choicebox<String> {
                    items = FXCollections.observableArrayList(
                            "Duller Pixels",
                            "Brighter Pixels",
                            "Shift Pixels Up",
                            "Shift Pixels Down",
                            //"Top Edge",
                            //"Bottom Edge",
                            "Side Edge",
                            "Horizontal Edge First",
                            "Horizontal Edge"
                    )
                    selectionModel.selectedItemProperty().addListener { _, _, new ->
                        val image = when (new) {
                            "Duller Pixels" -> filter.makePixelsDuller(wImg0)
                            "Brighter Pixels" -> filter.makePixelsBrighter(wImg0)
                            "Shift Pixels Up" -> filter.shiftPixelsUp(wImg0.pixelReader)
                            "Shift Pixels Down" -> filter.shiftPixelsDown(wImg0.pixelReader)
                            //"Top Edge" -> filter.topEdge(wImg0.pixelReader)
                            //"Bottom Edge" -> filter.bottomEdge(wImg0.pixelReader)
                            "Side Edge" -> filter.sideEdge(wImg0.pixelReader)
                            "Horizontal Edge First" -> filter.horizontalEdgeFirst(wImg0.pixelReader)
                            else -> filter.horizontalEdge(wImg2.pixelReader)
                        }
                        fire(CommandSetFilter(image))
                    }
                }
            }
        }
    }
}
