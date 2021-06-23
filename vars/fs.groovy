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