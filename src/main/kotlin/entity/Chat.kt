package entity

data class Chat (
    val _id: Int,
    val messages: List<Message>,
    val members: List<String>,
) {
    constructor(): this(
        _id = 1,
        messages = emptyList(),
        members = emptyList(),
    )
}

data class Message (
    val timestamp: String,
    val text: String,
    val sender: String,
//    val attachments: Array<File>
)