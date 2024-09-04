package link.limecode.reddit.lite.util

val emailFormat = Regex("^[A-Za-z](.*)(@)(.+)(\\.)(.+)")

fun String.isValidEmail(): Boolean = emailFormat.matches(this)