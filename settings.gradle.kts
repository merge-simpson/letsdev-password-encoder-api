rootProject.name = "letsdev-password-encoder-api"

val portModule: String by settings
val exceptionModule: String by settings

//include("modules")

include(portModule)
findProject(portModule)?.name = "letsdev-password-encoder-port"

include(exceptionModule)
findProject(exceptionModule)?.name = "letsdev-password-encoder-exception"
