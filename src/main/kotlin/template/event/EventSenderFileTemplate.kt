package template.event

import template.FileTemplate
import java.util.*

class EventSenderFileTemplate(private val eventName: String) : FileTemplate() {
    override val resultFileName: String
        get() = "${eventName}EventSenderImpl"

    override fun getProperties(templateProperties: Properties): Properties? {
        templateProperties["EVENT_NAME"] = eventName
        return templateProperties
    }

    override val templateFileName: String = "EventSenderTemplate"
}