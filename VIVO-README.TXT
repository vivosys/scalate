vivosys repository: git@github.com/vivosys/scalate.git
URL https://github.com/vivosys/scalate

1. Add upstream repository

    git remote add upstream git@github.com:scalate/scalate

2. Change the version number

    find . -name "pom.xml" | xargs sed -i~ 's/1.6.0-SNAPSHOT/1.6.0.vivo_4/'

3. Change the WAR plugin in the sample to use the 2.2 release

4. Deploy instructions.  The profile "download" is needed for JRebel to work.

    REPO_ID=vivosys-repo
    REPO_URL=https://tools.vivosys.com/artifactory/ext-release-local
    mvn \
       -Dmaven.test.skip=true
       -Dgpg.skip 
       -Pdownload \
       -DaltDeploymentRepository=$REPO_ID::default::$REPO_URL \
       -pl scalate-util,scalate-core,scalate-test,scalate-pegdown \
       source:jar javadoc:jar install deploy
