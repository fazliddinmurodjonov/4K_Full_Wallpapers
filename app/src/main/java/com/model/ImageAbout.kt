package com.model

import java.io.Serializable

class ImageAbout : Serializable {
    var image:String?=null
    var imageType: String? = null
    var imagePopular: Boolean? = null
    var imageLiked: Boolean? = null
    var imageOfWebSite: String? = null
    var imageOfAuthor: String? = null
    var imageDownload: Int? = null
    var imageSize: String? = null

    constructor(
        image: String?,
        imageType: String?,
        imagePopular: Boolean?,
        imageLiked: Boolean?,
        imageOfWebSite: String?,
        imageOfAuthor: String?,
        imageDownload: Int?,
        imageSize: String?
    ) {
        this.image = image
        this.imageType = imageType
        this.imagePopular = imagePopular
        this.imageLiked = imageLiked
        this.imageOfWebSite = imageOfWebSite
        this.imageOfAuthor = imageOfAuthor
        this.imageDownload = imageDownload
        this.imageSize = imageSize
    }
}