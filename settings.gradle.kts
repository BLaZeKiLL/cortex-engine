rootProject.name = "cortex"

include("engine")

include("engine:utils")
include("engine:core")
include("engine:resource")

include("model-viewer")
project(":model-viewer").projectDir = file("game/model-viewer")
