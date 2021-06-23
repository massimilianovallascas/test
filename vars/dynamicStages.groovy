def fromList(List dynamicStages, Closure c) {
    dynamicStages.each { s -> 
        stageName = "$s"
        c(stageName)
    }
}

def fromTxtFile(String fileName, String fileType, Closure c) {
    def dynamicStages = fs.readFromTxtFile(fileName)

    echo "[INFO] Config file $fileName contains " + dynamicStages.size() + " steps"
    
    fromList(dynamicStages, c)
}

def fromYmlFile(String fileName, Closure c) {
    def data = fs.readFromYmlFile(fileName)
    def dynamicStages = data.order

    echo "[INFO] Config file $fileName contains " + dynamicStages.size() + " steps"
    
    fromList(dynamicStages, c)
}
