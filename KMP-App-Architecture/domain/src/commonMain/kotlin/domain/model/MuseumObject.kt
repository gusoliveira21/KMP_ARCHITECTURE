package domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MuseumObject(
    @SerialName("objectID") val objectID: Int,
    @SerialName("title") val title: String,
    @SerialName("artistDisplayName") val artistDisplayName: String,
    @SerialName("medium") val medium: String,
    @SerialName("dimensions") val dimensions: String,
    @SerialName("objectURL") val objectURL: String,
    @SerialName("objectDate") val objectDate: String,
    @SerialName("primaryImage") val primaryImage: String,
    @SerialName("primaryImageSmall") val primaryImageSmall: String,
    @SerialName("repository") val repository: String,
    @SerialName("department") val department: String,
    @SerialName("creditLine") val creditLine: String
)