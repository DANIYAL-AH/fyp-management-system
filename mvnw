#!/bin/sh
basedir=$(dirname "$(readlink -f "$0")")

jar="$basedir/.mvn/wrapper/maven-wrapper.jar"
launcher="org.apache.maven.wrapper.MavenWrapperMain"

if [ ! -f "$jar" ]; then
    echo "Error: $jar not found."
    exit 1
fi

exec java "-Dmaven.multiModuleProjectDirectory=$basedir" -cp "$jar" "$launcher" "$@"
