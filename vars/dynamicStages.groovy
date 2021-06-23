def fromList(List dynamicStages, Closure c) {
    dynamicStages.each { s -> 
        stageName = "$s"
        c(stageName)
    }
}

def fromTxtFile(String fileName, String fileType, Closure c) {
    def dynamicStages = readFromTxtFile(fileName)

    echo "[INFO] Config file $fileName contains " + dynamicStages.size() + " steps"
    
    fromList(dynamicStages, c)
}

def fromYmlFile(String fileName, Closure c) {
    def data = readFromYmlFile(fileName)
    def dynamicStages = data.order
    println data
    println dynamicStages
    echo "[INFO] Config file $fileName contains " + dynamicStages.size() + " steps"
    
    fromList(dynamicStages, c)
}

def readFromTxtFile(String fileName) {
    def data = []

    if (fileExists(fileName)) {
        data = readFile(fileName).readLines()
    }

    return data
}

def readFromYmlFile(String fileName) {
    def data = []

    if (fileExists(fileName)) {
        data = readYaml(file: fileName)
    }

    return data
}