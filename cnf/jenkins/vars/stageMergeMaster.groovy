def call() {
    if ("${env.BRANCH_NAME}".startsWith("release")) {
        withCredentials([usernamePassword(credentialsId: '629c9138-7aa6-4c46-ace8-bc39b4251356',
                usernameVariable: 'ACCESS_TOKEN_USERNAME',
                passwordVariable: 'ACCESS_TOKEN_PASSWORD',)]) {
            lock("${env.PROJECT_NAME}-merge") {
                try {
                    // We need git reset because sometimes the gradle wrapper file has been changed and
                    // the checkout of master would fail
                    sh "git reset --hard"
                    sh "git checkout master"
                    sh "git merge origin/${env.BRANCH_NAME}"
                    sh "git push origin master"
                } catch (any) {
                    // If there is an error doing the merge we don't want the build to fail.
                    // Try-catch is used because modifying the currentBuild.result to a better state
                    // (e.g. failure -> unstable) isn't possible due to implementation restrictions
                    // in jenkins
                    currentBuild.result = 'UNSTABLE'
                    echo "Merge in branch master failed"
                }
            }
        }
    } else {
        echo "Skipped merge, because this is no release branch"
    }
}
