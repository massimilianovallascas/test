def call(def fileName) {
    def dynamicStages = readOrderFromFile(fileName)

    echo "[INFO] Config file $fileName contains " + dynamicStages.size() + " steps"
    
    dynamicStages.each { s -> 
        stageName = "$s"
        stage(stageName) {
            steps {
            script {
                milestone()
                terraform.init()
                milestone()
                terraform.plan()
                milestone()
                terraform.apply()
                milestone()
            }
            }
        }
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