rootProject.name = "cortex"

include("engine")

include("engine:core")
include("engine:utils")
include("engine:shader")
include("engine:resource")
include("engine:importer")
include("engine:renderer")

include("model-viewer")
project(":model-viewer").projectDir = file("game/model-viewer")

