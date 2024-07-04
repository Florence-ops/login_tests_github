pipeline {
    agent any
    environment {
        JAVA_HOME = "${WORKSPACE}/jdk-11"
        PATH = "${env.JAVA_HOME}/bin:${env.PATH}"
    }
    stages {
        stage('Setup JDK') {
            steps {
                sh '''
                wget https://download.java.net/java/GA/jdk11/13/GPL/openjdk-11.0.2_linux-x64_bin.tar.gz
                tar -xzf openjdk-11.0.2_linux-x64_bin.tar.gz
                mv jdk-11.0.2 ${JAVA_HOME}
                '''
            }
        }
    }
    triggers {
        githubPush()
    }
    stages {
        
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Florence-ops/github_login_tests.git'
            }
        }
        stage('Build') {
            steps {
                script {
                    // Use Maven to build the project
                    echo $MAVEN_OPTS
                    sh 'mvn clean install'
                    sh 'mvn -B -DskipTests clean package' 
                }
            }
        }
        stage('Test') {
            steps {
                script {
                    // Use Maven to run tests
                    sh 'mvn test'
                }
            }
        }
        stage('Deploy') {
            steps {
                // Add your deploy steps here
                echo 'Deploying...'
            }
        }
    }
}



