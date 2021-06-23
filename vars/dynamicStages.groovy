def fromList(List dynamicStages, Closure c) {
    dynamicStages.each { s -> 
        stageName = "$s"
        c(stageName)
    }
}

def fromFile(String fileName, String fileType, Closure c) {
    def dynamicStages = readFromFile(fileName, fileType)

    echo "[INFO] Config file $fileName contains " + dynamicStages.size() + " steps"
    
    fromList(dynamicStages, c)
}

def readFromFile(String fileName, String fileType = "yml") {
    def data = []

    if (fileExists(fileName)) {
        if (fileType == "txt") {
            data = readFile(fileName).readLines()
        }

        if (fileType == "yml" || fileType == "yaml") {
            data = readYaml(file: fileName)
            data = data.order
        }
    }

    return data
}
