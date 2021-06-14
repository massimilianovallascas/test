def call(def fileName) {
    def dynamicStages = readOrderFromFile(fileName)

    echo "[INFO] Config file $fileName contains " + dynamicStages.size() + " steps"
    
    dynamicStages.each { s -> 
        stageName = "$s"
        script {
            stage(stageName) {
                milestone()
                terraform.init()
                milestone()
                terraform.plan()
                milestone()
                terraform.apply()
                milestone()
            }
            post {
                success {
                    terraform.clean()
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