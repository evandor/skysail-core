def call() {
    sh "chmod +x gradlew"
    sh "./gradlew -Pversion=${env.BUILD_VERSION} --stacktrace --continue -Pkvb.env=CI -Dkvb.env=CI -Doracle.net.tns_admin=/opt/kvb/oracle clean build"
}
