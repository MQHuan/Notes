export ANDROID_HOME=/home/mqh/Android/Sdk
export JAVA_HOME=/opt/jdk1.8.0_77
export JRE_HOME=${JAVA_HOME}/jre
export CLASSPATH=.:${JAVA_HOME}/lib:${JRE_HOME}/lib
export PATH=${JAVA_HOME}/bin:$ANDROID_HOME/platforms:$ANDROID_HOME/platform-tools:$ANDROID_HOME/tools:$PATH
export GRAPHVIZ_DOT=/usr/local/bin/dot
alias c='cd'
alias as='cd /home/mqh/androidTools/android-studio/bin ; ./studio.sh'
alias els='cd /home/mqh/androidTools/eclipse/eclipse ; ./eclipse'
alias home='cd /home/mqh ; ls'
alias b='cd ..'
alias elsp='cd /home/mqh/workspace/eclipse/ ; ls'
alias asp='cd /home/mqh/AndroidStudioProjects ; ls'

export ANDROID_HVPROTO=ddm
