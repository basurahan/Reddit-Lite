package link.limecode.reddit.lite.backend.util

val emailFormat = Regex("^[A-Za-z](.*)(@)(.+)(\\.)(.+)")

fun String.isValidEmail(): Boolean = emailFormat.matches(this)