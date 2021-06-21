def call(def fileName, Closure c) {
    def dynamicStages = readOrderFromFile(fileName)

    echo "[INFO] Config file $fileName contains " + dynamicStages.size() + " steps"
    
    dynamicStages.each { s -> 
        stageName = "$s"
        // terraform.stage(stageName)
        c(stageName)
    }
}

def readOrderFromFile(def fileName) {
    def data = []
    if (fileExists(fileName)) {
        data = readFile(fileName).readLines()
        // def data = readYaml(file: fileName)
    }

    return data
}