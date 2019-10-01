folder("$BUName"){}
folder("$BUName/$ProductName"){}
pipelineJob("$BUName/$ProductName/CICD_$AppName"){
    parameters{
        stringParam("AppRepo", "$ApplicationRepo", "Git URL")
        stringParam("UnitTestTool", "$UnitTestRun", "")
        stringParam("AppName", "$AppName", "Application Name")
        stringParam("NodeName", "$NodeName", "Jenkins node name")

        activeChoiceParam('Branch'){
            choiceType('SINGLE_SELECT')
            filterable()
            description('Select the branch name to be built')
            groovyScript{
                script('["master", "develop"]')
                fallbackScript('"Fallback script choice"')
            }
        }
    }
    definition{
        cps{
            def jobScript = readFileFromWorkspace('cit.groovy')
            script(jobScript)
            def approvals = org.jenkinsci.plugins.scriptsecurity.scripts.SecurityApproval.get()
            approvals.approveScript(approvals.hash(jobScript,"groovy"))
        }
    }
}
