rootProject.name = "cortex"

include("engine")

include("engine:core")

include("model-viewer")
project(":model-viewer").projectDir = file("game/model-viewer")
