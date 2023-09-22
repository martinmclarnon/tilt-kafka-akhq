package com.mmcl.model

import lombok.Getter
import lombok.Setter
import java.util.*


class Order {
    @Getter
    @Setter
    private val orderID: String? = null

    @Getter
    @Setter
    private val dateOfCreation: Date? = null

    @Getter
    @Setter
    private val content: String? = null
}