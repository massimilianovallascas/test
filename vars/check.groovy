def commitHasTag(String expectedBranch) {
    def tag = null

    if (env.BRANCH_NAME == expectedBranch) {
        lock('commitHasTag') {
            tag = sh (
                returnStdout: true,
                script: 'git fetch --tags && git tag --points-at HEAD | awk NF'
            ).trim()

            if (tag) {
                echo "Deploying version ${TAG}"
            } else {
                echo error("No tag found for the current commit on ${expectedBranch}")
            }
        }
    }

    return tag
}

def deployContinue() {
    if (env.BRANCH_NAME != 'development') {
        timeout(time: 1, unit: 'MINUTES') {
            input message: "You are executing this scripts agains the ${params.TARGET_ENVIRONMENT} environment. Should we continue?"
        }
    }
}

def versionExists(String expectedBranch, String version) {
    if (env.BRANCH_NAME == expectedBranch && !version) {
        error("When deploying to ${expectedBranch} you must specify a version.")
    }
}

