package entity

data class Chat (
    val id: Int,
    val messages: List<Message>,
    val members: List<String>,
) {
    constructor(): this(
        id = 1,
        messages = emptyList(),
        members = emptyList(),
    )
}

data class Message (
    val id: Int,
    val timestamp: String,
    val text: String,
    val sender: String,
//    val attachments: Array<File>
)