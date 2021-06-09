def call() {
    def data = currentBuild.getBuildCauses()[0]
    return "${data.shortDescription} / ${data.userId}"
}