package gvgulov.knowledgegraph.file

import gvgulov.knowledgegraph.utils.getLogger
import java.io.File
import java.util.*

class PhotoUploader(
    private val parentFolder: String = "uploads"
) {
    private val logger = getLogger()
    private val directoryForSave = File(parentFolder)

    fun uploadPhoto(photo: File): String {
        val uuidPhotoName = UUID.randomUUID().toString()
        val extension = photo.extension
        val fullName = "$uuidPhotoName.$extension"
        val destination = File(directoryForSave, fullName)

        photo.copyTo(destination, overwrite = true)
        logger.info("Photo uploaded successfully to $destination")
        return fullName
    }


    fun getPhotoByUUID(uuid: String): File? {
        if (!directoryForSave.exists() || !directoryForSave.isDirectory) {
            logger.info("Directory $parentFolder does not exist or is not a directory")
            return null
        }

        val photos = directoryForSave.listFiles { _, file ->
            file.startsWith(uuid) && (file.endsWith(".jpg") || file.endsWith(".jpeg") || file.endsWith(".png"))
        }

        return photos.singleOrNull()
    }

}