#!/usr/bin/env groovy
package com.example 


class Docker implements Serializable {

    def script 
    Docker(script) {
         this.script = script 
    }
    def buildDockerImgae(String imageName){
        script.echo "Building the docker image..."
    script.withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')])
            {
                script.sh "docker build -t $imageName ."
                script.sh "echo $script.PASS | docker login -u $script.USER --password-stdin"
                script.sh "docker push $imageName"
            }
    }
}
