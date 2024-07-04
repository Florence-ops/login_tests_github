pipeline {
    agent any
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
                    sh 'mvn clean install'
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
