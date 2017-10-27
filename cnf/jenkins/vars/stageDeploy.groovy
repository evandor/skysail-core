def call() {
    if ("${env.BRANCH_NAME}".startsWith("release")) {
        sh "./gradlew --stacktrace -Pversion=${env.BUILD_VERSION} uploadArchives"
        withCredentials([usernamePassword(credentialsId: '629c9138-7aa6-4c46-ace8-bc39b4251356',
                usernameVariable: 'ACCESS_TOKEN_USERNAME',
                passwordVariable: 'ACCESS_TOKEN_PASSWORD',)]) {
            sh "git tag -m '' ${env.BUILD_VERSION}"
            sh "git remote set-url origin https://$ACCESS_TOKEN_USERNAME:$ACCESS_TOKEN_PASSWORD@git.kvb.local/git/${env.PROJECT_NAME}"
            sh "git push --tags"
        }
        lock("${env.PROJECT_NAME}-deploy") {
            sh "ssh -T kvbapp@odin-mp 'odin --set confirm=no target:mp_${env.PROJECT_NAME},IG1 deploy_container_app_restart:${env.BUILD_VERSION}'"
        }
    } else {
        echo "Skipped Deploy, because this is no release branch"
    }
}
