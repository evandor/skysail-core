def call() {
    lock('e2e') {
        sh "./gradlew --stacktrace -Dkvb.env=CI -Doracle.net.tns_admin=/opt/kvb/oracle ${env.PROJECT_NAME}-e2e:farmIntegrationTest --refresh-dependencies"
    }
}
