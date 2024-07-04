pipeline {
    agent any
    environment {
        JAVA_HOME = "${WORKSPACE}/jdk-11"
        PATH = "${env.JAVA_HOME}/bin:${env.PATH}"
    }
    stages {
        stage('Install Dependencies') {
            steps {
                sh '''
                # Ensure wget or curl is installed
                if ! command -v curl &> /dev/null
                then
                    apt-get update && apt-get install -y curl
                fi

                # Download and extract JDK
                curl -L -o openjdk-11.0.2_linux-x64_bin.tar.gz https://download.java.net/java/GA/jdk11/13/GPL/openjdk-11.0.2_linux-x64_bin.tar.gz
                tar -xzf openjdk-11.0.2_linux-x64_bin.tar.gz
                mv jdk-11.0.2 ${JAVA_HOME}
                '''
            }
        }
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Florence-ops/github_login_tests.git'
            }
        }
        stage('Build') {
            steps {
                echo 'Building...'
                sh 'mvn clean install'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing...'
                sh 'mvn test'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying...'
                // Add deployment steps here
            }
        }
    }
    post {
        always {
            cleanWs()
        }
    }
}
