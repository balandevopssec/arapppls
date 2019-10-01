node("$NodeName"){
    wrks = env.WORKSPACE
    stage("Prepare"){
        println("Preparing...")
        git(
            url: "git@github.com:balandevopssec/arapppls.git",
            branch: "master"
        )
        dir('config'){
            git(
                url: "git@github.com:balandevopssec/arappconf.git",
                branch: "master"
            )
        }
        println("Prepared..!")
    }
    stage("Cloning"){
        println("Cloning the Application Repository from Git to workspace...")
        load 'app/clone.groovy'
        println("Cloning the App is completed..!")
    }
    stage("Build"){
        println("Building the application...")
        load 'app/build.groovy'
        println("Builded the App..!")
    }
}