def call() {
    deleteDir()

    checkout scm

    if ("${env.BRANCH_NAME}".startsWith("release")) {
        def branchMajorMinorVersion = "${env.BRANCH_NAME}".substring("${env.BRANCH_NAME}".lastIndexOf("/") + 1)

        withCredentials([usernamePassword(credentialsId: '629c9138-7aa6-4c46-ace8-bc39b4251356',
                usernameVariable: 'ACCESS_TOKEN_USERNAME',
                passwordVariable: 'ACCESS_TOKEN_PASSWORD',)]) {
            // to be able to get the latest tag we must first fetch all existing tags
            sh "git fetch --tags https://$ACCESS_TOKEN_USERNAME:$ACCESS_TOKEN_PASSWORD@git.kvb.local/git/$env.PROJECT_NAME"
        }

        // get latest tag of current branch. '|| true' is added to prevent the build from failing if no tag exists for current branch
        def latestTag = sh script: '(git tag | fgrep ' + branchMajorMinorVersion + '. | sort -V | tail -1) || true', returnStdout: true

        def latestTagExists = latestTag != null && latestTag.trim().length() > 0
        def tagMajorMinorVersion = (latestTagExists ? latestTag.substring(0, latestTag.lastIndexOf('.')) : 0)

        if (!latestTagExists || (latestTagExists && !tagMajorMinorVersion.equalsIgnoreCase(branchMajorMinorVersion))) {
            nextPatchVersion = 0
        } else {
            nextPatchVersion = Integer.parseInt(latestTag.tokenize('.').last().trim()) + 1
        }

        env.BUILD_VERSION = "${branchMajorMinorVersion}.${nextPatchVersion}"
        currentBuild.displayName = "${env.BUILD_VERSION}"
    }
}
