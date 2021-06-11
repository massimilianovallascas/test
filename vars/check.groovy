def commitHasTag(String expectedBranch) {
    def tag = null

    if (env.BRANCH_NAME == expectedBranch) {
        lock('commitHasTag') {
            tag = sh (
                returnStdout: true,
                script: 'git fetch --tags && git tag --points-at HEAD | awk NF'
            ).trim()

            if (tag) {
                echo "Deploying to ${expectedBranch} => TAG: ${TAG}"
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

def versionExists(String expectedBranch) {
    if (params.TARGET_ENVIRONMENT == expectedBranch && !params.version) {
        error("When deploying to ${expectedBranch} you must specify a version.")
    }
}

